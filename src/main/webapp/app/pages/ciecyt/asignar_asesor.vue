<template>
    <div class="row">
        <div class="col-sm-4">
            <menu-lateral-ciecyt :proyectoId='$route.params.proyectoId'></menu-lateral-ciecyt>
        </div>
        <div class="col-sm-8">
            <form @submit.prevent="save()">
                <div class="row">
                    <div class="col-12" v-for="(integrante, i) in integrantesProyecto" :key="i">
                        <b-form-group
                            :label="`Asesor # ${i + 1}`"
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
                        <button type="button" id="cancel" class="btn btn-secondary" v-on:click="back">
                            <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;Volver
                        </button>
                        <button type="button" id="save" class="btn btn-primary" v-on:click="save()">
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

    import MenuLateralCiecyt from '@/components/ciecyt/menu_lateral_ciecyt.vue';
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
        components: { MenuLateralCiecyt },
        validations
    })

    export default class PropuestaAsesores extends Vue {
        @Inject('usuarioService') private usuarioService: () => UsuarioService;
        @Inject('proyectoService') private proyectoService: () => ProyectoService;
        @Inject('integranteProyectoService') private integranteProyectoService: () => IntegranteProyectoService;
        @Inject('rolesModalidadService') private rolesModalidadService: () => RolesModalidadService;
        @Inject('alertService') private alertService: () => AlertService;

        public users: IUser[] = [];
        public rolesModalidad: IRolesModalidad;
        public integrantesProyecto: IIntegranteProyecto[] = [];
        public user: number = null;
        public proyecto: IProyecto = new Proyecto();
        public proyId?: any;
        public isSaving = false;
        public modalidadId: number = 0;
        public n: number = 0;
        public cantAsesores: number = 0;
        public rolModalidadId?: number =0;

//public proyId: string = null;

        beforeRouteEnter(to, from, next) {
            next(async vm => {

                vm.initRelationships();

                
                

            });
        }
       
        mounted() {
            this.proyId = this.$route.params.proyectoId;
             

          
        }
        beforeMount() {
            
       
          
        }

        public back() {
             this.$router.go(-1);
           // this.$router.push({ name: 'PropuestaListadoCiecytView', params: { proyectoId: this.proyId } });
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
                               // this.$router.push({ name: 'PropuestaElementosView', params: { proyectoId: this.proyId } });
                            });
                    }
                     var proyId: string = String(this.proyId);
                    // this.$router.push({ name: 'PropuestaElementosView', params: { proyectoId: proyId } });

                }

            } catch (e) {
                //TODO: mostrar mensajes de error
            }
        }

         async initRelationships() {
            try {
                //Obteniendo los usuarios estudiantes
                
                this.usuarioService()
                    .retrieveAsesores()
                    .then(res => {
                      
                        res.data.forEach((item) => {
                            item.nombresApellidos = item.firstName + ' ' + item.lastName;
                            this.users.push(item);

                        });

                    });
                
               
                  

                this.proyId = parseInt(this.$route.params.proyectoId);

                this.proyecto = await this.proyectoService().find(this.proyId);
                //console.log(this.proyecto);

                /*await this.proyectoService().find(this.proyId).then
                    (res=> {
                            this.proyecto = res;
                    });
                */
                this.modalidadId = this.proyecto.proyectoModalidadId;

                
                            
                 await this.integranteProyectoService()
                    .retrieveAsesoresProyecto(this.proyId )
                    .then(res => {
                       this.integrantesProyecto = res.data;
                       //console.log(res.data);
                   });
                    
                  if(this.integrantesProyecto.length==0){  
                  await this.rolesModalidadService()
                    .findRolModalidad("Asesor", this.modalidadId )
                    .then(res => {
                        this.rolesModalidad = res;
                        this.cantAsesores = res.cantidad;
                        this.rolModalidadId = res.id;

                        console.log( this.cantAsesores);
                        
                         for (var i = 0; i < this.cantAsesores; i++) {
                            let integrante = new IntegranteProyecto();

                            integrante.integranteProyectoProyectoId = this.proyId;
                            integrante.integranteProyectoRolesModalidadId = this.rolModalidadId;

                            this.integrantesProyecto.push(integrante);
                            
                           
                        }
                        

                      
                    });
               
                  }
               

               
               

            } catch (e) {

            }
        }

    }
</script>

<style scoped>