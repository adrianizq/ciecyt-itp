<template>

    <div class="row">
        <div class="col-sm-4">
            <menu-lateral-nueva :proyectoId='$route.params.proyectoId'></menu-lateral-nueva>
        </div>
        <div class="col-sm-8">
            <form @submit.prevent="save()">
                <div class="row">
                    <div class="col-12">


                        <div class="form-group" v-if="proyecto.id">
                            <label for="id" v-text="$t('global.field.id')">ID</label>
                            <input type="text" class="form-control" id="id" name="id"
                                   v-model="proyecto.id" readonly/>
                        </div>

                        <div class="form-group" :class="{ 'form-group--error': $v.proyecto.titulo.$error }">
                            <label class="form-control-label " v-text="$t('ciecytApp.proyecto.titulo')" for="proyecto-titulo">Titulo</label>
                            <input type="text" class="form-control" name="titulo" id="proyecto-titulo"
                                   v-model="proyecto.titulo"
                                      @input="setTitulo($event.target.value)"

                                      placeholder="Ingrese el Titulo del Proyecto"
                                   />
                                <div class="error" v-if="!$v.proyecto.titulo.required&&!iniciandoTitulo">El Título es requerido</div>
                        </div>
                        <!--
                        <div class="form-group"  :class="{ 'form-group--error': $v.proyecto.url.$error }">
                            <label class="form-control-label " v-text="$t('ciecytApp.proyecto.url')" for="proyecto-url">Url</label>
                            <input type="text" class="form-control" name="url" id="proyecto-url"
                                   v-model="proyecto.url"
                                        placeholder="Ingrese la Url del proyecto si existe"
                                   />
                            <div class="error" v-if="!$v.proyecto.url.url">La URL no es válida, ej: http://www.itp.edu.co</div>       
                                
                        </div>     
                        
                        <div class="form-group"  >
                            <label class="form-control-label " v-text="$t('ciecytApp.proyecto.lugarEjecucion')" for="proyecto-url">Lugar de Ejecución</label>
                            <input type="text" class="form-control" name="lugar-ejecucion" id="proyecto-lugar-ejecucion"
                                   v-model="proyecto.lugarEjecucion"
                                        placeholder="Ingrese el lugar (ciduad, ubicación) donde se ejecutará el proyecto"
                                   />
                        </div>   

                        <div class="form-group"  >
                            <label class="form-control-label " v-text="$t('ciecytApp.proyecto.duracion')" for="proyecto-duracion">Duración en meses</label>
                            <input type="text" class="form-control" name="duracion" id="proyecto-duracion"
                                   v-model="proyecto.duracion"
                                        placeholder="Duración en meses"
                                   />
                        </div>        

                         <div class="form-group"  >
                            <label class="form-control-label " v-text="$t('ciecytApp.proyecto.tipo')" for="proyecto-tipo">Tipo de Investigación</label>
                            <input type="text" class="form-control" name="tipo" id="proyecto-tipo"
                                   v-model="proyecto.tipo"
                                        placeholder="Investigación Básica, Investigación Aplicada, Desarrollo Tecnológico o Experimental, etc"
                                   />
                        </div>        

                       
              <div class="form-group">
                
                <label for="datepicker-sm">Fecha de Inicio</label>
                 <b-form-datepicker size="sm-6" local="ESP" 
                    id="fecha-inicio"
                    name="fecha-inicio"
                    value="value"
                    v-model="proyecto.fechaIni">
                </b-form-datepicker>
                         
                 <label for="datepicker-lg">Fecha de Finalización</label>
                 <b-form-datepicker size="sm-6" local="ESP"
                    id="fecha-fin"
                    name="fecha-fin" 
                    value="value"
                    v-model="proyecto.fechaFin">
                  </b-form-datepicker>
              
                </div>
                                             
                                 

                         <div class="form-group"  :class="{ 'form-group--error': $v.proyecto.palabrasClave.$error }">
                            <label class="form-control-label " v-text="$t('ciecytApp.proyecto.palabrasClave')" for="proyecto-palabras-clave">Palabras Clave</label>
                            <textarea   rows="3" cols="6" class="form-control" name="palabrasClave" id="proyecto-palabras-clave"
                                   v-model="proyecto.palabrasClave"
                                   @input="setPalabrasClave($event.target.value)"
                                    placeholder="Ingrese las palabras clave separadas por comas"/>
                                     <div class="error" v-if="!$v.proyecto.palabrasClave.required&&!iniciandoPalabrasClave">Las palabra clave son requeridas</div>
                        </div>

                          <div class="form-group"  >
                            <label class="form-control-label " v-text="$t('ciecytApp.proyecto.referencias')" for="proyecto-referencias">Referencias</label>
                            <textarea   rows="3" cols="6" class="form-control" name="referencias" id="proyecto-referencias"
                                   v-model="proyecto.referencias"
                                  
                                    placeholder="Ingrese las referencias bibliográficas"/>
                                     
                        </div>
                       
-->

                    </div>

                  <div class="col-md-6 col-12">
                        <div class="form-group"  :class="{ 'form-group--error': $v.proyecto.proyectoModalidadId.$error }">
                               <label class="form-control-label "  v-text="$t('ciecytApp.proyecto.proyectoModalidad')" for="proyecto-modalidad">Modalidad</label>                 
                            <b-form-select :options="modalidads" text-field="modalidad" value-field="id" id="modalidad" 
                            v-model="proyecto.proyectoModalidadId"
                             @input="setModalidad"
                            >
                            </b-form-select>
                           
                                <div class="error" v-if="!$v.proyecto.proyectoModalidadId.required && !iniciandoModalidad">Una modalidad de trabajo de grado es requerida</div>
                        </div>
                    </div>

                    <div class="col-md-6 col-12">
                        <div class="form-group"  :class="{ 'form-group--error': $v.proyecto.facultadId }">
                               <label class="form-control-label "  v-text="$t('ciecytApp.proyecto.facultad')" for="proyecto-facultad">Facultad</label> 
                            <b-form-select :options="facultades"  text-field="facultad" value-field="id" id="facultad"
                            v-model="proyecto.facultadId"
                              @input="setFacultad"
                            >
                            </b-form-select>
                            <div class="error" v-if="!$v.proyecto.facultadId.required && !iniciandoFacultad">La facultad es requerida</div>
                        </div>
                    </div>
<!--
                    <div class="col-md-6 col-12">
                      <div class="form-group"  :class="{ 'form-group--error': $v.proyecto.proyectoLineaInvestigacionId }">
                            <label class="form-control-label "  v-text="$t('ciecytApp.proyecto.proyectoLineaInvestigacion')" for="proyecto-linea-investigacion">Linea de Investigación</label> 
                            <b-form-select text-field="linea" value-field="id" id="linea_investigacion"
                            v-model="proyecto.proyectoLineaInvestigacionId"
                             @input="setLinea"
                             >
                                <option v-for="(selectOption, indexOpt) in LineasInvestigacion"
                                        :key="indexOpt"
                                        :value="selectOption.id"
                                >
                                    ({{ selectOption.codigoLinea }}) {{ selectOption.linea }}
                                </option>
                            </b-form-select>
                             <div class="error" v-if="!$v.proyecto.proyectoLineaInvestigacionId.required && !iniciandoLinea">Una Línea de Investigación es requerida</div>
                        </div>
                    </div>

                    <div class="col-md-6 col-12">
                            <div class="form-group"  :class="{ 'form-group--error': $v.proyecto.subLineaLineaInvestigacionId }">
                            <label class="form-control-label "  v-text="$t('ciecytApp.proyecto.subLineaLineaInvestigacion')" for="sub-linea-investigacion">Sublinea de Investigación</label> 
                            <b-form-select text-field="linea" value-field="id" id="sub_linea_linea_investigacion"
                                v-model="proyecto.subLineaLineaInvestigacionId" 
                                 @input="setSubLinea"
                                >
                                <option v-for="(selectOption, indexOpt) in SubLineas"
                                        :key="indexOpt"
                                        :value="selectOption.id"
                                >
                                    ({{ selectOption.codigoLinea }}) {{ selectOption.linea }}
                                </option>
                            </b-form-select>
                            <div class="error" v-if="!$v.proyecto.subLineaLineaInvestigacionId.required && !iniciandoSubLinea">Una Sublínea de Investigación es requerida</div>
                        </div>
                    </div>
-->
                    <!-- ADR   -->
                    <!--
                    <div class="col-md-6 col-12">
                          <div class="form-group"  :class="{ 'form-group--error': $v.integranteProyecto.integranteProyectoUserId }">
                            <label class="form-control-label "  v-text="$t('ciecytApp.proyecto.asesor')" for="asesor">Asesor</label> 

                            <b-form-select :options="users" text-field="nombresApellidos" value-field="id" id="proyecto-asesorId"
                            v-model="proyecto.asesorId" 
                              @input="setAsesor"
                            >
                            </b-form-select>
                            <div class="error" v-if="!$v.integranteProyecto.integranteProyectoUserId.required && !iniciandoAsesor">El proyecto debe tener un asesor</div>
                        </div>
                    </div>
                    -->

                </div>


                <div>

                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>

                    <button type="submit" id="save-entity" 
                    class="btn btn-primary"
                    :disabled="this.submitStatus === 'PENDING'"
                    >
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>

                    <p class="typo__p" v-if="this.submitStatus === 'ERROR'">¡Existen campos sin llenar!.</p>
                   
      


                </div>

            </form>
        </div>
    </div>
</template>

<script lang="ts">
    import { Component, Inject, Vue } from 'vue-property-decorator';
    import AlertService from '@/shared/alert/alert.service';
   
    
    import MenuLateralNueva from '@/components/propuesta_nueva/menu_lateral_nueva.vue';
    import ModalidadService from '@/entities/modalidad/modalidad.service';
    import { IModalidad } from '@/shared/model/modalidad.model';
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

   

    import { numeric, required, minLength, maxLength, between, url } from 'vuelidate/lib/validators';
    import { id } from 'date-fns/esm/locale';
import { IIntegranteProyecto, IntegranteProyecto } from '@/shared/model/integrante-proyecto.model';
    //import { id } from 'date-fns/locale';

   
    const validations: any = {
        proyecto:{
            id: {},
            titulo: { required, maxLength: maxLength(100000) },
            palabrasClave: { required, maxLength: maxLength(100000) },
            proyectoModalidadId: { required, between: between(1, 100000000)},
            facultadId:  { required, between: between(1, 100000000)},
            proyectoLineaInvestigacionId:  { required, between: between(1, 100000000)},
            subLineaLineaInvestigacionId:  { required, between: between(1, 100000000)},
            //asesorId:  { required, between: between(1, 100000000)},
            url: { url},
            lugarEjecucion: {},
            duracion: {},
            fechaIni: {},
            fechaFin: {},
            contrapartidaPesos: {},
            contrapartidaEspecie: {},
            convocatoria: {}
        },
        integranteProyecto:{
            integranteProyectoUserId:  { required, between: between(1, 100000000)},
        }
           }
     
    
     

    @Component({
        components: { MenuLateralNueva },
         
        validations
    })



    export default class PropuestaInformacionGeneral extends Vue {
        @Inject('modalidadService') private modalidadService: () => ModalidadService;
        @Inject('facultadService') private facultadService: () => FacultadService;
        @Inject('lineaInvestigacionService') private lineaInvestigacionService: () => LineaInvestigacionService;
        @Inject('usuarioService') private usuarioService: () => UsuarioService;
        @Inject('proyectoService') private proyectoService: () => ProyectoService;
         
        @Inject('alertService') private alertService: () => AlertService;

        public modalidads: IModalidad[] = [];
        public facultades: IFacultad[] = [];
        public lineas_investigacion: ILineaInvestigacion[] = [];
        public users: IUser[] = [];

        public linea_investigacion: number = null;
        public facultad: number = null;
        public user: number = null;
        public nombresApellidos: string = null;
        public proyecto: IProyecto = new Proyecto();
        public proyId: string = null;
        public integranteProyecto: IIntegranteProyecto = new IntegranteProyecto();
      
       

        public isSaving = false;

        public submitStatus:string =  'PENDING';
        public iniciandoTitulo: boolean = true;
        public iniciandoPalabrasClave: boolean = true;
        public iniciandoModalidad: boolean = true;
        public iniciandoFacultad: boolean = true;
        public iniciandoLinea: boolean = true;
        public iniciandoSubLinea: boolean = true;
        public iniciandoAsesor: boolean = true;
        
        
          

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
            
                this.submitStatus = 'ERROR';
            }
            else{ 

                
              
            if (this.proyecto.id) {
                this.proyectoService()
                    .update(this.proyecto)
                    .then(param => {
                        this.isSaving = false;
                        this.$router.push({ name: 'PropuestaIntegrantesView', params: { proyectoId: this.proyecto.id.toString() } });
                        const message = this.$t('ciecytApp.proyecto.updated', { param: param.id });
                        this.alertService().showAlert(message, 'info');
                    });
            } else {
                this.proyectoService()
                    .create(this.proyecto)
                    .then(param => {
                        this.isSaving = false;

                        this.proyId = String(param.id);

                        this.$router.push({ name: 'PropuestaIntegrantesView', params: { proyectoId: this.proyId } });

                        const message = 'Se ha creado un nuevo proyecto';
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

        get LineasInvestigacion() {
            return this.lineas_investigacion.filter(linea => {
                return (!linea.lineaPadreId && linea.lineaInvestigacionFacultadId == this.proyecto.facultadId);
            });
        }

        get SubLineas() {
            return this.lineas_investigacion.filter(linea => {
                return (linea.lineaPadreId == this.proyecto.proyectoLineaInvestigacionId && linea.lineaPadreId);
            });
        }

        retrieveProyecto() {
            this.proyectoService().find(parseInt(this.$route.params.proyectoId)).then((res) => {
                this.proyecto = res;
            });
        }

        initRelationships() {

            this.proyId = this.$route.params.proyectoId;
    
            this.modalidadService()
                .retrieve()
                .then(res => {
                    this.modalidads = res.data;
                    
                });

            //Obtenienedo el asesor del proyecto
            if(this.proyId){
            this.proyectoService()
                .retrieveWithAsesor(this.proyId)
                .then(res=> {

                    this.proyecto = res.data;
                    
                })
            }
  
            //Obteniendo las facultadas
            this.facultadService()
                .retrieve()
                .then(res => {
                    this.facultades = res.data;
                });

            //Obteniendo las lineas de investigacion
            this.lineaInvestigacionService()
                .retrieve()
                .then(res => {
                    this.lineas_investigacion = res.data;
                });

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
    //metodos para las validaciones
        setTitulo(value) {
            this.iniciandoTitulo= false;
            this.submitStatus='ERROR';
        }

        setPalabrasClave(value) {
            this.iniciandoPalabrasClave= false;
            this.submitStatus='ERROR';
        }

        setModalidad(value) {
            this.iniciandoModalidad= false;
            this.submitStatus='ERROR';
         }

          setFacultad(value) {
             this.iniciandoFacultad= false;
             this.submitStatus='ERROR';
          }

          setLinea(value) {
             this.iniciandoLinea= false;
             this.submitStatus='ERROR';
          }

           setSubLinea(value) {
             this.iniciandoSubLinea= false;
             this.submitStatus='ERROR';
          }

            setAsesor(value) {
             //console.log($v); 
             this.integranteProyecto.integranteProyectoUserId = value;
             this.iniciandoAsesor= false;
             this.submitStatus='ERROR';
          }
        
        
        
    }

    
</script>

<style scoped>

</style>
