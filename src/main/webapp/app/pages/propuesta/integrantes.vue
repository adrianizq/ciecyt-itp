<template>
    <div class="row">
        <div class="col-sm-4">
            <menu-lateral></menu-lateral>
        </div>
        <div class="col-sm-8">
           <form @submit.prevent="save()"> 
                <div class="row">
                    <div class="col-12">
                        <div><h1>{{this.cantEstudiantes}}</h1></div>

                        <div>
                             <span v-for="n in this.cantEstudiantes">{{ n }} </span>
                        </div>
                      <!--
                        <div class="form-group" v-if="proyecto.id">
                            <label for="id" v-text="$t('global.field.id')">ID</label>
                            <input type="text" class="form-control" id="id" name="id"
                                   v-model="integranteProyecto.id" readonly/>
                        </div>
                        -->
<!--
                        <div class="form-group">
                            <label class="form-control-label " v-text="$t('ciecytApp.proyecto.titulo')" for="proyecto-titulo">Titulo</label>
                            <input type="text" class="form-control" name="titulo" id="proyecto-titulo"
                                   v-model="proyecto.titulo"/>
                        </div>
-->

                    </div>


                   
                    <div class="col-md-6 col-12" >
                         <span v-for="ip in this.integrantesProyecto">
                       
                       <b-form 
                            label="Integrante"
                            label-for="usuario"
                        >
                            
                            <b-form-select  :options="users" v-model="ip.integranteProyectoUserId" text-field="nombresApellidos" value-field="id" id="proyecto-estudianteId" >
                              </b-form-select>
                               </b-form>

                               <button type="submit" id="save-entity" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Agregar</span>
                    </button>

                                 </span>
                                   
                    </div>
               
                 


                </div>

              
                <div>

                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    


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
    import FacultadService from '@/entities/facultad/facultad.service';
    import { IFacultad } from '@/shared/model/facultad.model';
    import LineaInvestigacionService from '@/entities/linea-investigacion/linea-investigacion.service';
    import { ILineaInvestigacion } from '@/shared/model/linea-investigacion.model';
    import UsuarioService from '@/entities/usuario/usuario.service';
    import { IUsuario } from '@/shared/model/usuario.model';
    import { IUser } from '@/shared/model/user.model';
    //ADR
    import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
    import ProyectoService from '@/entities/proyecto/proyecto.service';

    import { IIntegranteProyecto, IntegranteProyecto } from '@/shared/model/integrante-proyecto.model';
    import IntegranteProyectoService from '@/entities/integrante-proyecto/integrante-proyecto.service';

    import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

    const validations: any = {
        integranteProyecto: {
                
            integrante: {},
            descripcion: {},
            integranteProyectoUserLogin: {},
            integranteProyectoUserId: {},
            integranteProyectoProyectoTitulo: {},
            integranteProyectoProyectoId: {},
            integranteProyectoRolesModalidadRol: {},
            integranteProyectoRolesModalidadId: {},
        }
    };









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
        public integranteProyecto = new IntegranteProyecto();
        public user: number = null;
        public nombresApellidos: string = null;
        public proyecto: IProyecto = new Proyecto();
        public proyId: any;
        public isSaving = false;
        public modalidadId: number =0;
        public n: number =0;
        public cantEstudiantes: number=0;
        
          beforeCreate(){
          //this.dato++; da 0
        }

        created(){
          //this.dato++; //da 1
         // this.modalidadId = this.proyecto.proyectoModalidadId;
        }

        mounted(){
          
        }
        
        beforeRouteEnter(to, from, next) {
            // this.dato++;  //error vue-router.esm.js?8c4f:1924 TypeError: Cannot read property 'dato' of undefined
             //this.proyectoId = this.$route.params.proyectoId;
            //this.dato++; //undefined
            //console.log("beforeRouteEnter");
            //console.log("beforeRouteEnter" + this.dato);

              next(vm => {
                if (to.params.proyectoId) {
                    vm.retrieveProyecto(to.params.proyectoId);
                    
                }
                
                vm.initRelationships();

               

            });
        }

        public save(): void {
            this.isSaving = true;
            //if (this.proyecto.id) {
                /*this.proyectoService()
                .update(this.proyecto)
                .then(param => {
                    this.isSaving = false;
                    this.$router.go(-1);
                    //console.log(this.proyecto.id);
                    //this.$router.go("http://localhost:9000/propuesta/integrantes")
                    const message = this.$t('ciecytApp.proyecto.updated', { param: param.id });
                    this.alertService().showAlert(message, 'info');
                });*/
                

            //} else {

            // this.modalidadId = this.proyecto.proyectoModalidadId;
            //console.log(this.modalidadId); //funciona
           

                 this.integranteProyecto.integranteProyectoProyectoId =   this.proyId;
                 //this.integranteProyecto.integranteProyectoUserId = this.integrantesProyecto[elegido].integranteProyectoUserId;
                this.integranteProyectoService()
                .create(this.integranteProyecto)
                .then(param => {
                    this.isSaving = false;
                    //this.$router.go(-1);
                    // this.$router.go("http://localhost:9000/propuesta/integrantes/" + `${proyecto.getId()}` )
                    //this.$router.push({ name: 'PropuestaIntegrantesView', params: { proyectoId: `${this.proyecto.id}` } })
                    const message = this.$t('ciecytApp.integranteProyecto.created', { param: param.id });
                    //this.alertService().showAlert(message, 'success');
                });
                
         
           
           // }
        }


      

        initRelationships() {
            this.proyId = this.$route.query.proyectoId;
            this.proyectoService()
                .find(this.proyId)
                .then(res => {
                    this.proyecto=res;
                     this.modalidadId = res.proyectoModalidadId;
                });
           
            
                       //Obteniendo las lineas de investigacion
            this.rolesModalidadService()
                .retrieve()
                .then(res => {
                    this.rolesModalidads = res.data;
                    const newArray = this.rolesModalidads.filter((value, index) => {
                          if (value.rolesModalidadModalidadId == this.modalidadId && value.rol=="Estudiante") return true;
                    });
                    this.cantEstudiantes = newArray[0].cantidad;
                    //crear los elementos para 
                    for (var i = 0 ;i < this.cantEstudiantes; i++ ){
                        this.integrantesProyecto[i] = new IntegranteProyecto();
                    }
                });
            //Obteniendo los usuarios asesores
            this.usuarioService()
                .retrieveEstudiantes()
                .then(res => {
                    res.data.forEach((item) => {
                        item.nombresApellidos = item.firstName + ' ' + item.lastName;
                        this.users.push(item);
                    });

                });

            //cargar variables
            
            
        }




        
    }
</script>

<style scoped>

</style>
