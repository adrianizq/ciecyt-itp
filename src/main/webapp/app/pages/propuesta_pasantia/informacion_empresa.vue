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
                                      @input="setDuracionHoras"

                                      placeholder="Horas de pasantía: tecnico 480, tecnólogo 640, profesional 880"
                                   />
                                                      
                                <div class="error" v-if="!$v.informacionPasantia.duracionHoras.required&&!iniciandoDuracionHoras">Las horas de la pasantia son requeridas</div>
                        </div>

                        <div class="form-group" >
                            <label class="form-control-label " v-text="$t('ciecytApp.informacionPasantia.direccion')" for="informacion-pasantia-direccion">Direccion </label>
                            <input type="text" class="form-control" name="direccion" id="informacion-pasantia-direccion"
                                   v-model="informacionPasantia.direccion"
                                      @input="setDireccion"

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
                                        <td > Lun </td>
                                        <td > Mar </td>
                                        <td > Mie </td>
                                        <td > Jue </td>
                                        <td > Vie </td>
                                        <td > Sab </td>
                                        <td > Dom </td>
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
  <!-- --------------------------------------------------------------->
  <div  class="form-group" >
                        
                            <span>Apoyos de la empresa</span>
                            <table style="width:100%">
                             <tbody>
          
                                    <tr>
                                        <td > Bono Alimenticio </td>
                                        <td > Apoyo Económico </td>
                                        <td > Auxilio de Transporte </td>
                                        <td > Capacitación </td>
                                        
                                    </tr>
                            <tr>
                                <td>
                                <input type="checkbox" class="form-control" name="dias" id="informacion-pasantia-lunes"
                                    v-model="informacionPasantia.bonoAlimenticio" />
                                </td>
                                <td>
                                <input type="checkbox" class="form-control" name="dias" id="informacion-pasantia-lunes"
                                v-model="informacionPasantia.apoyoEconomico"/>
                                </td>
                                 <td>
                                <input type="checkbox" class="form-control" name="dias" id="informacion-pasantia-lunes"
                                v-model="informacionPasantia.auxilioTransporte"/>
                                </td>
                                <td>
                                <input type="checkbox" class="form-control" name="dias" id="informacion-pasantia-lunes"
                                v-model="informacionPasantia.capacitacion"/>
                                </td>
                               
                            </tr>
                            <tr>
                                <td colspan="4">
                                    Otro apoyo de la empresa
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                 <input type="text" class="form-control" name="horas-mes" id="informacion-pasantia-horas-mes"
                                   v-model="informacionPasantia.otroApoyo"
                                      placeholder="Otro tipo de apoyo"
                                   />
                                </td>
                            </tr>
                 </tbody>
                </table>

     </div>
     <hr>

       <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-nombre-empresa">Nombre de la empresa </label>
            <input type="text" class="form-control" name="nombre-empresa" id="informacion-pasantia-nombre-empresa"
             v-model="informacionPasantia.nombreEmpresa"
             placeholder="Nombre de la empresa donde realizará la pasantía"
            />
        </div>

        <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-nit-empresa">Nit de la empresa </label>
            <input type="text" class="form-control" name="nit-empresa" id="informacion-pasantia-nit-empresa"
             v-model="informacionPasantia.nitEmpresa"
             placeholder="NIT de la empresa donde realizará la pasantía"
            />
        </div>

        <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-direccion-empresa">Dirección de la empresa </label>
            <input type="text" class="form-control" name="direccion-empresa" id="informacion-pasantia-direccion-empresa"
             v-model="informacionPasantia.direccionEmpresa"
             placeholder="Direccion de la empresa"
            />
        </div>

        <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-municipio-empresa">Municipio de la empresa </label>
            <input type="text" class="form-control" name="municipio-empresa" id="informacion-pasantia-municipio-empresa"
             v-model="informacionPasantia.municipioEmpresa"
             placeholder="Municipio donde está ubicada la empresa"
            />
        </div>

         <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-telefono-contacto-empresa">Teléfono de contacto de la empresa </label>
            <input type="text" class="form-control" name="telefono-contacto-empresa" id="informacion-pasantia-telefono-contacto"
             v-model="informacionPasantia.telefonoContactoEmpresa"
             placeholder="Telefono de la empresa"
            />
        </div>

        <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-email-empresa">Email de la empresa </label>
            <input type="text" class="form-control" name="email-empresa" id="informacion-pasantia-email-contacto"
             v-model="informacionPasantia.emailEmpresa"
             placeholder="Correo electrónico de la empresa"
            />
        </div>

        <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-departamento-empresa">Departamento de la empresa </label>
            <input type="text" class="form-control" name="departamento-empresa" id="informacion-pasantia-departamento-empresa"
             v-model="informacionPasantia.departamentoEmpresa"
             placeholder="Departamento de la empresa"
            />
        </div>

        <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-sector-economico">Sector económico de la empresa </label>
            <input type="text" class="form-control" name="sector-economico" id="informacion-pasantia-sector-economico"
             v-model="informacionPasantia.sectorEconomicoEmpresa"
             placeholder="Sectores: primario, secundario, terciario"
            />
        </div>
        <hr>
        <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-representante-legal">Representante Legal de la empresa </label>
            <input type="text" class="form-control" name="representante-legal" id="informacion-pasantia-representante-legal"
             v-model="informacionPasantia.representanteLegalEmpresa"
             placeholder="Nombres y apellidos del representante legal de la empresa"
            />
        </div>

        <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-identificacion-representante-legal">Identificacion del Representante Legal de la empresa </label>
            <input type="text" class="form-control" name="identificacion-representante-legal" id="informacion-pasantia-identificacion-representante-legal"
             v-model="informacionPasantia.identificacionRepresentanteLegal"
             placeholder="Ejemplos: CC:110.323.232, CE:5.323.232"
            />
        </div>
        <hr>
        <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-asesor-empresa">Asesor de la empresa </label>
            <input type="text" class="form-control" name="asesor-empresa" id="informacion-pasantia-asesor-empresa"
             v-model="informacionPasantia.asesorEmpresa"
             placeholder="Nombres y Apellidos del Asesor asignado por parte de la empresa"
            />
        </div>
        <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-cargo-asesor-empresa">Cargo del asesor de la empresa </label>
            <input type="text" class="form-control" name="cargo-asesor-empresa" id="informacion-pasantia-cargo-asesor-empresa"
             v-model="informacionPasantia.cargoAsesorEmpresa"
             placeholder="Cargo del Asesor en la empresa"
            />
        </div>
        <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-email-asesor-empresa">Email del asesor de la empresa </label>
            <input type="text" class="form-control" name="email-asesor-empresa" id="informacion-pasantia-email-asesor-empresa"
             v-model="informacionPasantia.emailAsesorEmpresa"
             placeholder="Correo electrónico del Asesor de la empresa"
            />
        </div>
        <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-profesion-asesor-empresa">Profesión del asesor de la empresa </label>
            <input type="text" class="form-control" name="profesion-asesor-empresa" id="informacion-pasantia-profesion-asesor-empresa"
             v-model="informacionPasantia.profesionAsesorEmpresa"
             placeholder="Profesión del Asesor de la empresa"
            />
        </div>
        <div class="form-group" >
            <label class="form-control-label " for="informacion-pasantia-celular-asesor-empresa">Celular del asesor de la empresa </label>
            <input type="text" class="form-control" name="celular-asesor-empresa" id="informacion-pasantia-celular-asesor-empresa"
             v-model="informacionPasantia.celularAsesorEmpresa"
             placeholder="Número de celular del Asesor de la empresa"
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
            duracionHoras: { required,  between: between(100, 1000) },
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
        //public proyId: string = null;
        public proyId: any;
        
      
       

        public isSaving = false;

        public submitStatus:string =  'PENDING';
        public iniciandoDuracionHoras: boolean = true;
        public iniciandoDireccion: boolean = true;
        
        
          

        beforeRouteEnter(to, from, next) {
            next(vm => {
                if (to.params.proyectoId) {
                    this.proyId = this.$route.params.proyectoId;
                    vm.retrieveProyecto(to.params.proyectoId);
                }
                vm.initRelationships();
            });
        }

        public save(): void {
            this.isSaving = true;

            this.$v.$touch();
            if (this.$v.$invalid) {
            
                if(this.$v.informacionPasantia.duracionHoras.$invalid){
                    this.setDuracionHoras(0);
                }
            /*    if(this.$v.proyecto.palabrasClave.$invalid){
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
               // console.log("Error");
            }
            else{ 

                 
              
            if (this.informacionPasantia) {
                this.informacionPasantiaService()
                    .update(this.informacionPasantia)
                    .then(param => {
                        this.isSaving = false;
                        this.$router.push({ name: 'PropuestaPasantiaInformacionEmpresaView', params: { proyectoId: this.proyecto.id.toString() } });
                        const message = this.$t('ciecytApp.proyecto.updated', { param: param.id });
                        this.alertService().showAlert(message, 'info');
                    });
            } else {
               //console.log(this.proyId);
                this.informacionPasantia.informacionPasantiaProyectoId= parseInt(this.proyId);
                 //console.log(this.informacionPasantia);
                       
                this.informacionPasantiaService()
                    .create(this.informacionPasantia)  
                    .then(param => {
                       
                        this.isSaving = false;

                        //this.proyId = String(param.id);

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
        }
        //recupera un proyecto
        retrieveProyecto() {
             //console.log(this.proyId);
             this.proyectoService().find(this.proyId).then((res) => {
                this.proyecto = res;
            });
        }

        initRelationships() {

            //this.proyId = this.$route.params.proyectoId;
            //console.log(this.proyId);
    
            this.informacionPasantiaService()
                .findInformacionPasantiaProyecto(this.proyId)
                .then(res => {
                    this.informacionPasantia = res;
                    console.log(this.proyId);
                    
                });
        
    }
    //metodos para las validaciones
        setDuracionHoras(value) {
            //console.log(value);
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
