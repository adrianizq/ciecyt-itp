<template>

    <div class="row">
        <div class="col-sm-4">
            <menu-lateral-pasantia :proyectoId='$route.params.proyectoId'></menu-lateral-pasantia>
        </div>
        <div class="col-sm-8">
            <form @submit.prevent="save()">
                <div class="row">
                    <div class="col-12">
<!--
                       <div class="form-group" v-if="informacionPasantia.id">
                            <label for="id" v-text="$t('global.field.id')">ID</label>
                            <input type="text" class="form-control" id="id" name="id"
                                   v-model="informacionPasantia.id" readonly/>
                        </div>
-->
                        <div class="form-group" >
                            <label class="form-control-label " v-text="$t('ciecytApp.informacionPasantia.duracionHoras')" for="informacion-pasantia-duracion-horas">Duracion en Horas</label>
                            <input type="text" class="form-control" name="duracion-horas" id="informacion-pasantia-duracion-horas"
                                   v-model="informacionPasantia.duracionHoras"
                                      @input="setDuracionHoras($event.target.value)"

                                      placeholder="Horas de pasantía: tecnico 480, tecnólogo 640, profesional 880"
                                   />
                                <div class="error" v-if="!$v.informacionPasantia.duracionHoras.required&&!iniciandoDuracionHoras">Las horas de la pasantia son requeridas</div>
                        </div>

                        <div class="form-group" >
                            <label class="form-control-label " v-text="$t('ciecytApp.informacionPasantia.direccion')" for="informacion-pasantia-direccion">Direccion </label>
                            <input type="text" class="form-control" name="direccion" id="informacion-pasantia-direccion"
                                   v-model="informacionPasantia.direccion"
                                      @input="setDireccion($event.target.value)"

                                      placeholder="Ingrese la direccion donde realizará la pasantía"
                                   />
                                <div class="error" v-if="!$v.informacionPasantia.direccion.required&&!iniciandoDireccion">La direccion del sitio de pasantía es requerida</div>
                        </div>

                        
                        <div class="form-group" >
                            <label class="form-control-label " for="informacion-pasantia-email">Email </label>
                            <input type="text" class="form-control" name="email" id="informacion-pasantia-enail"
                                   v-model="informacionPasantia.email"
                                     

                                      placeholder="Ingrese el email de la empresa"
                                   />
                                
                        </div>


                                
                        <div  class="form-group" >
                        
                            <span>Dias a laborar</span>
                            <table style="width:100%">
                             <tbody>
          
                                    <tr>
                                        <td > Lunes </td>
                                        <td > Martes </td>
                                        <td > Miércoles </td>
                                        <td > Jueves </td>
                                        <td > Viernes </td>
                                        <td > Sábado </td>
                                        <td > Domingo </td>
                                    </tr>
                            <tr>
                                <td>
                                <input type="checkbox" class="form-control" name="dias" id="informacion-pasantia-lunes"
                                    v-model="informacionPasantia.lunes" />
                                </td>
                                <td>
                                <input type="checkbox" class="form-control" name="dias" id="informacion-pasantia-lunes"
                                v-model="informacionPasantia.martes"/>
                                </td>
                                 <td>
                                <input type="checkbox" class="form-control" name="dias" id="informacion-pasantia-lunes"
                                v-model="informacionPasantia.miercoles"/>
                                </td>
                                <td>
                                <input type="checkbox" class="form-control" name="dias" id="informacion-pasantia-lunes"
                                v-model="informacionPasantia.jueves"/>
                                </td>
                                <td>
                                <input type="checkbox" class="form-control" name="dias" id="informacion-pasantia-lunes"
                                v-model="informacionPasantia.viernes"/>
                                </td>
                                <td>
                                <input type="checkbox" class="form-control" name="dias" id="informacion-pasantia-lunes"
                                v-model="informacionPasantia.sabado"/>
                                </td>
                                <td>
                                <input type="checkbox" class="form-control" name="dias" id="informacion-pasantia-lunes"
                                v-model="informacionPasantia.domingo"/>
                            </td>
                    </tr>
                </tbody>
            </table>

                           

                        </div>
                                
                        

                        <div class="form-group" >
                            <label class="form-control-label " for="informacion-pasantia-horas-mes">Horas al Mes </label>
                            <input type="text" class="form-control" name="horas-mes" id="informacion-pasantia-horas-mes"
                                   v-model="informacionPasantia.horasMes"
                                     

                                      placeholder="Ingrese las horas de dedicación mensual"
                                   />
                                
                        </div>
                    </div>
                </div>

                <div>

                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>

                    
                    <button type="submit" id="save-entity" 
                    class="btn btn-primary" 
                    >
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>

                </div>

            </form>
        </div>
    </div>
</template>
<script lang="ts">
    import { Component, Inject, Vue } from 'vue-property-decorator';
    import AlertService from '@/shared/alert/alert.service';
   
    
    import MenuLateralPasantia from '@/components/propuesta_pasantia/menu_lateral_pasantia.vue';
    import InformacionPasantiaService from '@/entities/informacion-pasantia/informacion-pasantia.service';
    import { IInformacionPasantia, InformacionPasantia } from '@/shared/model/informacion-pasantia.model';
    
    
    import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
    import ProyectoService from '@/entities/proyecto/proyecto.service';

   

    import { numeric, required, minLength, maxLength, between, url } from 'vuelidate/lib/validators';
    import { id } from 'date-fns/esm/locale';

    //import { id } from 'date-fns/locale';

   
    const validations: any = {
        informacionPasantia:{
            id: {},
            duracionHoras: { required, maxLength: maxLength(100000) },
            direccion: { required, maxLength: maxLength(100000) },
            
        }
    }
     
    
     

    @Component({
        components: { MenuLateralPasantia },
         
        validations
    })



    export default class PasantiaInformacionEmpresa extends Vue {
      
       
        @Inject('proyectoService') private proyectoService: () => ProyectoService;
        @Inject('informacionPasantiaService') private informacionPasantiaService: () => InformacionPasantiaService;
         
        @Inject('alertService') private alertService: () => AlertService;

        public informacionPasantia: IInformacionPasantia = new InformacionPasantia();
        
        public proyecto: IProyecto = new Proyecto();
        public proyId: any = null;
        
      
       

        public isSaving = false;

        public submitStatus:string =  'PENDING';
        public iniciandoDuracionHoras: boolean = true;
        public iniciandoDireccion: boolean = true;
        
        
          

        beforeRouteEnter(to, from, next) {
            next(vm => {
                if (to.params.proyectoId) {
                    vm.retrieveProyecto(to.params.proyectoId);
                }
                vm.initRelationships();
            });
        }

        public save(): void {
            this.isSaving = true;

            this.$v.$touch();
            if (this.$v.$invalid) {
            /*
                if(this.$v.proyecto.titulo.$invalid){
                    this.setTitulo("");
                }
                if(this.$v.proyecto.palabrasClave.$invalid){
                    this.setPalabrasClave("");
                }
                if(this.$v.proyecto.proyectoModalidadId.$invalid){
                    this.setModalidad(0);
                }
                if(this.$v.proyecto.facultadId.$invalid){
                    this.setFacultad(0);
                }
                if(this.$v.proyecto.proyectoLineaInvestigacionId.$invalid){
                    this.setLinea(0);
                }
                 if(this.$v.proyecto.subLineaLineaInvestigacionId.$invalid){
                    this.setSubLinea(0);
                }
                  if(this.$v.integranteProyecto.integranteProyectoUserId.$invalid){
                    console.log(this.$v);
                    this.setAsesor("");
                    
                }
            */
                this.submitStatus = 'ERROR';
                console.log("Error");
            }
            else{ 

                 
              
            if (this.informacionPasantia.id) {
                this.informacionPasantiaService()
                    .update(this.informacionPasantia)
                    .then(param => {
                        this.isSaving = false;
                        this.$router.push({ name: 'PropuestaPasantiaInformacionEmpresaView', params: { proyectoId: this.proyecto.id.toString() } });
                        const message = this.$t('ciecytApp.proyecto.updated', { param: param.id });
                        this.alertService().showAlert(message, 'info');
                    });
            } else {
               
                this.informacionPasantia.informacionPasantiaProyectoId= this.proyId;
                 //console.log(this.informacionPasantia);
                
                  
                this.informacionPasantiaService()
                    .create(this.informacionPasantia)  
                    .then(param => {
                       
                        this.isSaving = false;

                        //this.proyId = param.id;

                        this.$router.push({ name: 'PropuestaPasantiaInformacionEmpresaView', params: { proyectoId: this.proyId } });

                        const message = 'Se ha creado un nuevo elemento de pasantia';
                        this.alertService().showAlert(message, 'success');
                        
                        
                    });

            
            }
            this.submitStatus = 'PENDING';
            setTimeout(() => {
                this.submitStatus = 'OK';
                }, 500)
            }
            console.log(this.submitStatus);
        }

        retrieveProyecto() {
            this.proyectoService().find(parseInt(this.proyId)).then((res) => {
                this.proyecto = res;
            });
        }

        initRelationships() {

            this.proyId = this.$route.params.proyectoId;
    
            this.informacionPasantiaService()
                .findInformacionPasantiaProyecto( this.proyId)
                .then(res => {
                    this.informacionPasantia = res;
                    console.log(this.informacionPasantia);
                    
                });
        
    }
    //metodos para las validaciones
        setDuracionHoras(value) {
            this.iniciandoDuracionHoras= false;
            this.submitStatus='ERROR';
        }

        setDireccion(value) {
            this.iniciandoDireccion= false;
            this.submitStatus='ERROR';
        }

    

           
        
        
        
    }

    
</script>

<style scoped>

</style>
