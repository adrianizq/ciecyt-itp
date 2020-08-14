<template>

    <div class="row">
        <div class="col-sm-4">
            <menu-lateral :proyectoId='$route.params.proyectoId'></menu-lateral>
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
                        

                         <div class="form-group"  :class="{ 'form-group--error': $v.proyecto.palabrasClave.$error }">
                            <label class="form-control-label " v-text="$t('ciecytApp.proyecto.palabrasClave')" for="proyecto-palabras-clave">Palabras Clave</label>
                            <textarea   rows="3" cols="6" class="form-control" name="palabrasClave" id="proyecto-palabras-clave"
                                   v-model="proyecto.palabrasClave"
                                   @input="setPalabrasClave($event.target.value)"
                                    placeholder="Ingrese las palabras clave separadas por comas"/>
                                     <div class="error" v-if="!$v.proyecto.palabrasClave.required&&!iniciandoPalabrasClave">Las palabra clave son requeridas</div>
                        </div>
                       


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
                        <div class="form-group"  :class="{ 'form-group--error': $v.proyecto.facultad.$error }">
                               <label class="form-control-label "  v-text="$t('ciecytApp.proyecto.facultad')" for="proyecto-facultad">Facultad</label> 
                            <b-form-select :options="facultades"  text-field="facultad" value-field="id" id="facultad"
                            v-model="proyecto.facultadId"
                              @input="setFacultad"
                            >
                            </b-form-select>
                            <div class="error" v-if="!$v.proyecto.facultad.valor&&!iniciandoFacultad">La facultad es requerida</div>
                        </div>
                    </div>

                    <div class="col-md-6 col-12">
                        <b-form-group
                            label="Linea de investigación"
                            label-for="linea_investigacion"
                        >
                            <b-form-select text-field="linea" v-model="proyecto.proyectoLineaInvestigacionId" value-field="id" id="linea_investigacion">
                                <option v-for="(selectOption, indexOpt) in LineasInvestigacion"
                                        :key="indexOpt"
                                        :value="selectOption.id"
                                >
                                    ({{ selectOption.codigoLinea }}) {{ selectOption.linea }}
                                </option>
                            </b-form-select>
                        </b-form-group>
                    </div>

                    <div class="col-md-6 col-12">
                        <b-form-group
                            label="Sub línea"
                            label-for="sub_linea"
                        >
                            <b-form-select text-field="linea" v-model="proyecto.subLineaLineaInvestigacionId" value-field="id" id="sub_linea_linea_investigacion">
                                <option v-for="(selectOption, indexOpt) in SubLineas"
                                        :key="indexOpt"
                                        :value="selectOption.id"
                                >
                                    ({{ selectOption.codigoLinea }}) {{ selectOption.linea }}
                                </option>
                            </b-form-select>
                        </b-form-group>
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
   
    
    import MenuLateral from '@/components/propuesta/menu_lateral.vue';
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

   

    import { numeric, required, minLength, maxLength, between } from 'vuelidate/lib/validators';
    import { id } from 'date-fns/esm/locale';
    //import { id } from 'date-fns/locale';

    /*validators: {
        titulo: (value)=> {
    return value(value).required().titulo();
        }
    }*/

    const validations: any = {
        proyecto:{
            id: {},
            titulo: { required, maxLength: maxLength(100000) },
            palabrasClave: { required, maxLength: maxLength(100000) },
            proyectoModalidadId: { required, between: between(1, 10000000)},
            facultad:  {valor:0},
            url: {},
            lugarEjecucion: {},
            duracion: {},
            fechaIni: {},
            fechaFin: {},
            contrapartidaPesos: {},
            contrapartidaEspecie: {},
            convocatoria: {}
        }
           }
     
    
     

    @Component({
        components: { MenuLateral },
         
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
      
       

        public isSaving = false;

        public submitStatus:string =  'PENDING';
        public iniciandoTitulo: boolean = true;
        public iniciandoPalabrasClave: boolean = true;
        public iniciandoModalidad: boolean = true;
        public iniciandoFacultad: boolean = true;
        
        
          

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

/*if (this.$v.proyecto.proyectoModalidadId.$params.valor==null){
                 this.submitStatus = 'ERROR';
                 //console.log("Error en modalidad");
                 this.setModalidad(this.$v.proyecto.proyectoModalidadId.$params.valor);
            }*/
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
            //console.log(this.proyId);
            //Obtenienedo las modalidades
            this.modalidadService()
                .retrieve()
                .then(res => {
                    this.modalidads = res.data;
                    
                });

            //Obtenienedo los proyectos
          

           this.proyectoService()
                .retrieveWithAsesor(this.proyId)
                .then(res=> {

                    this.proyecto = res.data;
                    
                })

            
           



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

/*
          get tituloErrors () {
            const errors: string[] = []
            if (this.proyecto.titulo) return errors
            !titulo!.maxLength && errors.push('Character name must be at most 11 characters long')
            !titulo!.required && errors.push('Character name is required.')
            return errors
        } */

        

        
        setTitulo(value) {
        this.proyecto.titulo = value;
        this.$v.proyecto.titulo.$touch();
        //if(value){
            this.iniciandoTitulo= false;
            //console.log("titulo");
            //console.log(value);
         //   this.submitStatus = 'OK';
        //    }
        this.submitStatus='ERROR';
        }

        setPalabrasClave(value) {
        this.proyecto.palabrasClave = value;
        this.$v.proyecto.palabrasClave.$touch();
        //if(value){
            this.iniciandoPalabrasClave= false;
        //}
        this.submitStatus='ERROR';
        }

        setModalidad(value) {
            this.proyecto.proyectoModalidadModalidad = value;
            //if(value==0){
               this.iniciandoModalidad= false;
            //}
             //else{
                 this.submitStatus='ERROR';
                 //}
        }

          setFacultad(value) {
            this.proyecto.facultadId = value;
            if(value!=0){
            this.iniciandoFacultad= false;
            console.log("facultad");
            console.log(value);
            }
             else{this.submitStatus='ERROR';}
        }
        
        
        
    }

    
</script>

<style scoped>

</style>
