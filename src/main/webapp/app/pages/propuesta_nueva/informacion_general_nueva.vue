<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral-nueva :proyectoId="$route.params.proyectoId"></menu-lateral-nueva>
    </div>
    <div class="col-sm-8">
      <form @submit.prevent="save()">
        <div class="row">
          <div class="col-12">
            <div class="form-group" v-if="proyecto.id">
              <label for="id" v-text="$t('global.field.id')">ID</label>
              <input type="text" class="form-control" id="id" name="id" v-model="proyecto.id" readonly />
            </div>

            <div class="form-group">
              <label class="form-control-label" v-text="$t('ciecytApp.proyecto.titulo')" for="proyecto-titulo">Titulo</label>
              <input
                type="text"
                class="form-control"
                name="titulo"
                id="proyecto-titulo"
                v-model="proyecto.titulo"
                :class="{
                  'is-invalid': $v.proyecto.titulo.$error,
                  'is-valid': !$v.proyecto.titulo.$invalid,
                }"
                placeholder="Ingrese el Titulo del Proyecto"
              />
              <div class="text-danger" v-if="!$v.proyecto.titulo.required">Este campo es requerido</div>
            </div>
         
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ciecytApp.proyecto.lugarEjecucion')" for="proyecto-url"
                >Lugar de Ejecución</label
              >
              <input
                type="text"
                class="form-control"
                name="lugar-ejecucion"
                id="proyecto-lugar-ejecucion"
                :class="{
                  'is-invalid': $v.proyecto.lugarEjecucion.$error,
                  'is-valid': !$v.proyecto.lugarEjecucion.$invalid,
                }"
                v-model="proyecto.lugarEjecucion"
                placeholder="Ingrese el lugar (ciduad, ubicación) donde se ejecutará el proyecto"
              />
              <div class="text-danger" v-if="!$v.proyecto.lugarEjecucion.required">Este campo es requerido</div>
            </div>
          
            <div class="form-group">
              <label for="datepicker-sm">Fecha de Inicio</label>
              <b-form-datepicker
                size="sm-6"
                local="ESP"
                id="fecha-inicio"
                name="fecha-inicio"
                value="value"
                :class="{
                  'is-invalid': $v.proyecto.fechaIni.$error,
                  'is-valid': !$v.proyecto.fechaIni.$invalid,
                }"
                v-model="proyecto.fechaIni"
              >
              </b-form-datepicker>
              <div class="text-danger" v-if="!$v.proyecto.fechaIni.required">Este campo es requerido</div>

              <label for="datepicker-lg">Fecha de Finalización</label>
              <b-form-datepicker
                size="sm-6"
                local="ESP"
                id="fecha-fin"
                name="fecha-fin"
                value="value"
                :class="{
                  'is-invalid': $v.proyecto.fechaFin.$error,
                  'is-valid': !$v.proyecto.fechaFin.$invalid,
                }"
                v-model="proyecto.fechaFin"
              >
              </b-form-datepicker>
              <div class="text-danger" v-if="!$v.proyecto.fechaFin.required">Este campo es requerido</div>
            </div>

          </div>

          <div class="col-md-6 col-12">
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ciecytApp.proyecto.proyectoModalidad')" for="proyecto-modalidad"
                >Modalidad</label
              >
              <b-form-select
                :options="modalidads"
                text-field="modalidad"
                value-field="id"
                id="modalidad"
                v-model="proyecto.proyectoModalidadId"
                 @input="setModalidad"
                :class="{
                  'is-invalid': $v.proyecto.proyectoModalidadId.$error,
                  'is-valid': !$v.proyecto.proyectoModalidadId.$invalid,
                }"
              >
              </b-form-select>
              <div class="text-danger" v-if="!$v.proyecto.proyectoModalidadId.required">Este campo es requerido</div>
            </div>
          </div>

          <div class="col-md-6 col-12">
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ciecytApp.proyecto.facultad')" for="proyecto-facultad">Facultad</label>
              <b-form-select
                :options="facultades"
                text-field="facultad"
                value-field="id"
                id="facultad"
                v-model="proyecto.facultadId"
                :class="{
                  'is-invalid': $v.proyecto.facultadId.$error,
                  'is-valid': !$v.proyecto.facultadId.$invalid,
                }"
              >
              </b-form-select>
              <div class="text-danger" v-if="!$v.proyecto.facultadId.required">Este campo es requerido</div>
            </div>
          </div>

          <!-- ////////////7777777777777 -->
          <div class="col-md-6 col-12">
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ciecytApp.programa.programa')" for="proyecto-programa">Programa</label>
              <b-form-select
                text-field="programa"
                value-field="id"
                id="programa"
                v-model="proyecto.proyectoProgramaId"
                :class="{
                  'is-invalid': $v.proyecto.proyectoProgramaId.$error,
                  'is-valid': !$v.proyecto.proyectoProgramaId.$invalid,
                }"
              >
                <option v-for="(selectOption, indexOpt) in Programas" :key="indexOpt" :value="selectOption.id">
                  ({{ selectOption.id }}) {{ selectOption.programa }}
                </option>
              </b-form-select>
              <div class="text-danger" v-if="!$v.proyecto.proyectoProgramaId.required">Este campo es requerido</div>
            </div>
          </div>
          <!--   ///////////Linea//////////////-->

          <div class="col-md-6 col-12" >
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('ciecytApp.proyecto.proyectoLineaInvestigacion')"
                for="proyecto-linea-investigacion"
                >Linea de Investigación</label
              >
              <b-form-select :disabled="!modalidad.contieneLinea"
              text-field="linea" value-field="id" id="linea_investigacion" v-model="proyecto.proyectoLineaInvestigacionId">
                <option v-for="(selectOption, indexOpt) in LineasInvestigacion" :key="indexOpt" :value="selectOption.id">
                  ({{ selectOption.codigoLinea }}) {{ selectOption.linea }}
                </option>
              </b-form-select>
            </div>
          </div>

          <div class="col-md-6 col-12" >
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ciecytApp.proyecto.subLineaLineaInvestigacion')" for="sub-linea-investigacion"
                >Sublinea de Investigación</label
              >
              <b-form-select :disabled="!modalidad.contieneLinea"
                text-field="linea"
                value-field="id"
                id="sub_linea_linea_investigacion"
                v-model="proyecto.subLineaLineaInvestigacionId"
              >
                <option v-for="(selectOption, indexOpt) in SubLineas" :key="indexOpt" :value="selectOption.id">
                  ({{ selectOption.codigoLinea }}) {{ selectOption.linea }}
                </option>
              </b-form-select>
            </div>
          </div>


          <div class="col-md-6 col-12">
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ciecytApp.proyecto.asesor')" for="asesor">Asesor</label>

              <!--<b-form-select
                :options="users"
                text-field="nombresApellidos"
                value-field="id"
                id="proyecto-asesorId"
                v-model="proyecto.asesorId"
                :class="{
                  'is-invalid': $v.proyecto.asesorId.$error,
                  'is-valid': !$v.proyecto.asesorId.$invalid,
                }"
              >
              </b-form-select> -->
                 <model-select 
                            :options="options"
                             placeholder="busque por nombre o cedula"
                             v-model="proyecto.asesorId"
                             :class="{
                             'is-invalid': $v.proyecto.asesorId.$error,
                             'is-valid': !$v.proyecto.asesorId.$invalid,
                             }"
                            >
                        </model-select>
              <div class="text-danger" v-if="!$v.proyecto.asesorId.required">Este campo es requerido</div>
            </div>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>

          <button type="submit" id="save-entity" class="btn btn-primary">
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
import { IModalidad, Modalidad } from '@/shared/model/modalidad.model';
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
import ProgramaService from '@/entities/programa/programa.service';
import { IPrograma, Programa } from '@/shared/model/programa.model';

import { numeric, required, minLength, maxLength, between, url } from 'vuelidate/lib/validators';
import { id } from 'date-fns/esm/locale';
import { IIntegranteProyecto, IntegranteProyecto } from '@/shared/model/integrante-proyecto.model';
//import { id } from 'date-fns/locale';
import 'vue-search-select/dist/VueSearchSelect.css'
import { ModelSelect} from 'vue-search-select'

const validations: any = {
  proyecto: {
    //id: {},
    titulo: { required, maxLength: maxLength(100000) },
    //palabrasClave: { required, maxLength: maxLength(100000) },
    proyectoModalidadId: { required },
    facultadId: { required },
    //proyectoLineaInvestigacionId:  { required, between: between(1, 100000000)},
    //subLineaLineaInvestigacionId:  { required, between: between(1, 100000000)},
    proyectoProgramaId: { required },
    asesorId: { required },
    //url: { url},
    lugarEjecucion: { required },
    //duracion: {},
    fechaIni: { required },
    fechaFin: { required },
    //contrapartidaPesos: {},
    //contrapartidaEspecie: {},
    //convocatoria: {}
  },
  integranteProyecto: {
    integranteProyectoUserId: { required },
  },
};

@Component({
  components: { MenuLateralNueva, ModelSelect },

  validations,
})
export default class PropuestaInformacionGeneral extends Vue {
  @Inject('modalidadService') private modalidadService: () => ModalidadService;
  @Inject('facultadService') private facultadService: () => FacultadService;
  @Inject('lineaInvestigacionService') private lineaInvestigacionService: () => LineaInvestigacionService;
  @Inject('usuarioService') private usuarioService: () => UsuarioService;
  @Inject('proyectoService') private proyectoService: () => ProyectoService;
  @Inject('programaService') private programaService: () => ProgramaService;

  @Inject('alertService') private alertService: () => AlertService;

  public modalidads: IModalidad[] = [];
  public modalidad: IModalidad = new Modalidad();
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
  public programs: IPrograma[] = [];
  public programa: IPrograma = new Programa();
  public options : any = [];

  public isSaving = false;

  public submitStatus: string = 'PENDING';

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

    // this.$v.$touch();
    /* if (this.$v.$invalid) {
            
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

                */

    if (this.proyecto.id) {
      this.proyectoService()
        .update(this.proyecto)
        .then(param => {
          this.isSaving = false;
          this.$router.push({ name: 'PropuestaIntegrantesNuevaEditView', params: { proyectoId: this.proyecto.id.toString() } });
          const message = this.$t('ciecytApp.proyecto.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.proyectoService()
        .create(this.proyecto)
        .then(param => {
          this.isSaving = false;

          this.proyId = String(param.id);

          this.$router.push({ name: 'PropuestaIntegrantesNuevaEditView', params: { proyectoId: this.proyId } });

          const message = 'Se ha creado un nuevo proyecto';
          this.alertService().showAlert(message, 'success');
        });
    }
    this.submitStatus = 'PENDING';
    setTimeout(() => {
      this.submitStatus = 'OK';
    }, 500);
    //}
    //console.log(this.submitStatus);
  }

  get LineasInvestigacion() {
    return this.lineas_investigacion.filter(linea => {
      return !linea.lineaPadreId && linea.lineaInvestigacionProgramaId == this.proyecto.proyectoProgramaId
       && this.modalidad.contieneLinea;
    });
  }

  get SubLineas() {
    return this.lineas_investigacion.filter(linea => {
      return linea.lineaPadreId == this.proyecto.proyectoLineaInvestigacionId && linea.lineaPadreId;
    });
  }

/*get Modalidad() {
    return this.modalidads.filter(modalidad => {
      return modalidad.id == this.proyecto.proyectoModalidadId;
    });
  }*/

  get Programas() {
    return this.programs.filter(programa => {
      return programa.programaFacultadId == this.proyecto.facultadId;
    });
  }

  retrieveProyecto() {
    this.proyectoService()
      .find(parseInt(this.$route.params.proyectoId))
      .then(res => {
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

    this.programaService()
      .retrieve()
      .then(res => {
        this.programs = res.data;
        //this.setProgramas(res.data);
      });

    //obteniendo el programa que ya existe
    /*  this.programaService()
                .find(this.proyecto.proyectoProgramaId)
                .then(res => {
                    this.programa = res;
                    console.log(this.programa);
                    
                });*/

    //Obtenienedo el asesor del proyecto
    if (this.proyId) {
      this.proyectoService()
        .retrieveWithAsesor(this.proyId)
        .then(res => {
          this.proyecto = res.data;
          console.log(this.proyecto);
        });
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
        res.data.forEach(item => {

        if(item.firstName && item.lastName && item.userInfo ){
            if(item.userInfo.nuip)
               item.nombresApellidos = item.firstName + ' ' + item.lastName  + ' ' +  item.userInfo.nuip;
        }else if(item.firstName && item.lastName){
            item.nombresApellidos = item.firstName + ' ' + item.lastName;
        }
         this.users.push(item);
         this.options.push({value: item.id, text: item.nombresApellidos})
        });
      });
  }
  //metodos para las validaciones
  /*   setTitulo(value) {
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
*/
  setAsesor(value) {
    this.proyecto.asesorId = value;
  }

  setModalidad(value){
      this.modalidadService()
                .find(value)
                .then(res => {
                    this.modalidad = res;
                    //console.log(this.modalidad);
                    
                });
                this.proyecto.proyectoLineaInvestigacionId=null;
                this.proyecto.subLineaLineaInvestigacionId=null;
  }
}
</script>

<style scoped>
</style>
