<template>
    <div class="row">
        <div class="col-sm-4">
            <menu-lateral-pasantia :proyectoId='$route.params.proyectoId'></menu-lateral-pasantia>
        </div>
        <div class="col-sm-8">
            <form @submit.prevent="save()">
                <div class="row">
                    <!-- <div>{{userid}}</div> -->
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

    import MenuLateralPasantia from '@/components/propuesta_pasantia/menu_lateral_pasantia.vue';
    import RolesModalidadService from '@/entities/roles-modalidad/roles-modalidad.service';
    import { IRolesModalidad } from '@/shared/model/roles-modalidad.model';
    import UsuarioService from '@/entities/usuario/usuario.service';
    import { IUser } from '@/shared/model/user.model';
    import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
    import ProyectoService from '@/entities/proyecto/proyecto.service';

    import { IIntegranteProyecto, IntegranteProyecto } from '@/shared/model/integrante-proyecto.model';
    import IntegranteProyectoService from '@/entities/integrante-proyecto/integrante-proyecto.service';

    import LoginService from '@/account/login.service';

    const validations: any = {};

    @Component({
        components: { MenuLateralPasantia },
        validations
    })

    export default class PropuestaIntegrantes extends Vue {
        @Inject('usuarioService') private usuarioService: () => UsuarioService;
        @Inject('proyectoService') private proyectoService: () => ProyectoService;
        @Inject('integranteProyectoService') private integranteProyectoService: () => IntegranteProyectoService;
        @Inject('rolesModalidadService') private rolesModalidadService: () => RolesModalidadService;
        @Inject('alertService') private alertService: () => AlertService;
        ///////////////////////////////////////////7777
        @Inject('loginService')
        

        public users: IUser[] = [];
        public rolesModalidad: IRolesModalidad;
        public integrantesProyecto: IIntegranteProyecto[] = [];
        public user: number = null;
        public proyecto: IProyecto = new Proyecto();
        public proyId?: any;
        public isSaving = false;
        public modalidadId: number = 0;
        public n: number = 0;
        public cantEstudiantes: number = 0;
        public rolModalidadId?: number =0;


        ///////////////////////////////////////////////////////////////////777
      
  private loginService: () => LoginService;
  public autoridadElegida: any = null;
  public openLogin(): void {
    this.loginService().openLogin((<any>this).$root);
  }
  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }
  public get username(): string {
    return this.$store.getters.account ? this.$store.getters.account.login : '';
  }
  public get userid(): number {
    return this.$store.getters.account ? this.$store.getters.account.id : '';
  }
  public get authorities(): string {
    //console.log(this.$store.getters.account);
    return this.$store.getters.account ? this.$store.getters.account.authorities : '';
  }
        ////////////////////////////////////////////////////////////////////7

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
            this.$router.push({ name: 'PropuestaPasantiaInformacionGeneraEditlView', params: { proyectoId: this.proyId } });
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
                                this.$router.push({ name: 'PropuestaPasantiaInformacionEmpresaView', params: { proyectoId: this.proyId } });
                            });
                    }
                     var proyId: string = String(this.proyId);
                     this.$router.push({ name: 'PropuestaPasantiaInformacionEmpresaView', params: { proyectoId: proyId } });

                }

            } catch (e) {
                //TODO: mostrar mensajes de error
            }
        }

         async initRelationships() {
            try {
                //Obteniendo los usuarios estudiantes
                
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

                /*await this.proyectoService().find(this.proyId).then
                    (res=> {
                            this.proyecto = res;
                    });
                */
                this.modalidadId = this.proyecto.proyectoModalidadId;

                
                            
                 await this.integranteProyectoService()
                    .retrieveEstudiantesProyecto(this.proyId )
                    .then(res => {
                       this.integrantesProyecto = res.data;
                       console.log(res.data);
                   });
                    
                  if(this.integrantesProyecto.length==0){  
                  await this.rolesModalidadService()
                    .findRolModalidad("Estudiante", this.modalidadId )
                    .then(res => {
                        this.rolesModalidad = res;
                        this.cantEstudiantes = res.cantidad;
                        this.rolModalidadId = res.id;
                        
                         for (var i = 0; i < this.cantEstudiantes; i++) {
                            let integrante = new IntegranteProyecto();

                            if(i==0){//el primer usuario es el usuario actual
                            integrante.integranteProyectoUserId = this.userid;
                            }
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