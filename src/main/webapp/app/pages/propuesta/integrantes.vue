<template>
    <div class="row">
        <div class="col-sm-4">
            <menu-lateral :proyectoId='$route.params.proyectoId'></menu-lateral>
        </div>
        <div class="col-sm-8">
            <form @submit.prevent="save()">
                <div class="row">
                    <div class="col-12" v-for="(integrante, i) in integrantesProyecto" :key="i">
                        <b-form-group
                            :label="`Integrante # ${i + 1}`"
                            :label-for="`integrante-${i}`"
                        >
                            <b-form-select
                                :options="users"
                                text-field="nombresApellidos"
                                value-field="id" :id="`integrante-${i}`" v-model="integrante.integranteProyectoUserId">

                            </b-form-select>
                        </b-form-group>
                    </div>

                </div>


                <div class="row">
                    <div class="col-12">
                        <!--<button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                        </button>-->
                        <button type="button" id="cancel-save" class="btn btn-primary" v-on:click="save()">
                            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Guardar</span>
                        </button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</template>

<script lang="ts">
    import { Component, Inject, Vue } from 'vue-property-decorator';
    import AlertService from '@/shared/alert/alert.service';

    import MenuLateral from '@/components/propuesta/menu_lateral.vue';
    import RolesModalidadService from '@/entities/roles-modalidad/roles-modalidad.service';
    import { IRolesModalidad } from '@/shared/model/roles-modalidad.model';
    import UsuarioService from '@/entities/usuario/usuario.service';
    import { IUser } from '@/shared/model/user.model';
    import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
    import ProyectoService from '@/entities/proyecto/proyecto.service';

    import { IIntegranteProyecto, IntegranteProyecto } from '@/shared/model/integrante-proyecto.model';
    import IntegranteProyectoService from '@/entities/integrante-proyecto/integrante-proyecto.service';

    const validations: any = {};

    @Component({
        components: { MenuLateral },
        validations
    })

    export default class PropuestaIntegrantes extends Vue {
        @Inject('usuarioService') private usuarioService: () => UsuarioService;
        @Inject('proyectoService') private proyectoService: () => ProyectoService;
        @Inject('integranteProyectoService') private integranteProyectoService: () => IntegranteProyectoService;
        @Inject('rolesModalidadService') private rolesModalidadService: () => RolesModalidadService;
        @Inject('alertService') private alertService: () => AlertService;

        public users: IUser[] = [];
        public rolesModalidads: IRolesModalidad[] = [];
        public integrantesProyecto: IIntegranteProyecto[] = [];
        public user: number = null;
        public proyecto: IProyecto = new Proyecto();
        public proyId?: any;
        public isSaving = false;
        public modalidadId: number = 0;
        public n: number = 0;
        public cantEstudiantes: number = 0;
        public rolModalidadId?: number;
//public proyId: string = null;

        beforeRouteEnter(to, from, next) {
            next(async vm => {

                vm.initRelationships();

            });
        }

        public save(): void {
            try {
                this.isSaving = true;
                for (let integrante of this.integrantesProyecto) {
                    //Actualizando el integrante
                    if (integrante.id) {
                        this.integranteProyectoService().update(integrante);
                    } else {
                        //Creando un nuevo integrante
                        this.integranteProyectoService().create(integrante)
                        .then(param => {
                            this.$router.push({ name: 'PropuestaElementosView',params:{ proyectoId: this.proyId}});
                        });
                    }
                    console.log(this.proyId);
                    console.log('holasss');
                    console.log(parseInt(this.$route.params.proyectoId))
                    var proyId: string = String(this.proyId);
                   // this.proyId = toString(this.$route.params.proyectoId);

                   this.$router.push({ name: 'PropuestaElementosView',params:{ proyectoId: proyId}});


                }

            } catch (e) {
                //TODO: mostrar mensajes de error
            }
        }

        async initRelationships() {
            try {
                //Obteniendo los usuarios asesores
                this.usuarioService()
                    .retrieveEstudiantes()
                    .then(res => {
                        res.data.forEach((item) => {
                            item.nombresApellidos = item.firstName + ' ' + item.lastName;
                            this.users.push(item);
                        });

                    });

                this.proyId = parseInt(this.$route.params.proyectoId);

                this.proyecto = await this.proyectoService().find(this.proyId);

                this.modalidadId = this.proyecto.proyectoModalidadId;

                //Obteniendo las lineas de investigacion
                this.rolesModalidadService()
                    .retrieve()
                    .then(res => {
                        this.rolesModalidads = res.data;
                        const newArray = this.rolesModalidads.filter((value, index) => {
                            if (value.rolesModalidadModalidadId == this.modalidadId && value.rol == 'Estudiante') {
                                return true;
                            }
                        });
                        this.cantEstudiantes = newArray[0].cantidad;
                        this.rolModalidadId = newArray[0].id;

                        //crear los elementos para
                        for (var i = 0; i < this.cantEstudiantes; i++) {
                            let integrante = new IntegranteProyecto();

                            integrante.integranteProyectoProyectoId = this.proyId;
                            integrante.integranteProyectoRolesModalidadId = this.rolModalidadId;

                            this.integrantesProyecto.push(integrante);
                        }

                    });

            } catch (e) {

            }
        }

    }
</script>

<style scoped>

</style>
