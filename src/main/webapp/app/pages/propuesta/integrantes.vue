<template>
    <div class="row">
        <div class="col-sm-4">
            <menu-lateral></menu-lateral>
        </div>
        <div class="col-sm-8">
            <form @submit.prevent="save()">
                <div class="row">

                    <div class="row">
                        <div class="col-12">
                            <div class="form-group">
                                <label class="form-control-label" for="proyecto-documento">Documento</label>
                                <input type="text" class="form-control" name="titulo" id="proyecto-documento"
                                />
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-group">
                                <label class="form-control-label" for="proyecto-nombre">Nombres</label>
                                <input type="text" class="form-control" name="titulo" id="proyecto-nombre"
                                />
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-group">
                                <label class="form-control-label" for="proyecto-apellido">Apellidos</label>
                                <input type="text" class="form-control" name="titulo" id="proyecto-apellido"
                                />
                            </div>
                            <button type="submit" id="save-entity" class="btn btn-primary">
                                <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                            </button>
                        </div>

                        <!-- ADR   -->
                        <div class="col-md-6 col-12">
                            <b-form-group
                                label="Asesor"
                                label-for="usuario"
                            >
                                <b-form-select :options="users" v-model="proyecto.asesorId" text-field="nombresApellidos" value-field="id" id="proyecto-asesorId">

                                </b-form-select>
                            </b-form-group>
                        </div>
                        <div class="col-md-6 col-12">
                            ROLES DE MODALIDAD
                        </div>
                    </div>
                </div>
            </form>

        </div>
    </div>
</template>

<script lang="ts">
    import { Component, Inject, Vue } from 'vue-property-decorator';
    import MenuLateral from '@/components/propuesta/menu_lateral.vue';
    import AlertService from '@/shared/alert/alert.service';
    import UsuarioService from '@/entities/usuario/usuario.service';
    import { IUser } from '@/shared/model/user.model';

    @Component({
        components: { MenuLateral }
    })
    export default class Integrantes extends Vue {

        @Inject('usuarioService') private usuarioService: () => UsuarioService;

        public users: IUser[] = [];

        beforeRouteEnter(to, from, next) {
            next(vm => {
                if (to.params.proyectoId) {
                    vm.retrieveProyecto(to.params.proyectoId);
                }
                console.log('Iniciando componente...');
                /*if (to.params.cicloPropedeuticoId) {
                    vm.retrieveCicloPropedeutico(to.params.cicloPropedeuticoId);
                }*/
                vm.initRelationships();

                console.log('Carga usuarios...' + this.users);
            });
        }

        initRelationships() {

            //Obteniendo los usuarios asesores
            this.usuarioService()
                .retrieveAsesores()
                .then(res => {
                    res.data.forEach((item) => {
                        item.nombresApellidos = item.firstName + ' ' + item.lastName;
                        this.users.push(item);
                    });

                });

        }

    }

</script>

<style scoped>

</style>
