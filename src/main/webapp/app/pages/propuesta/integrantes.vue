<template>
    <div class="row">
        <div class="col-sm-4">
            <menu-lateral></menu-lateral>
        </div>
        <div class="col-sm-8">
           <form @submit.prevent="save()"> 
                <div class="row">
                    <div class="col-12">
                      
                            <h1>Este es el Proyectoid {{$route.query.proyectoId}}</h1>
                        <div class="form-group" v-if="proyecto.id">
                            <label for="id" v-text="$t('global.field.id')">ID</label>
                            <input type="text" class="form-control" id="id" name="id"
                                   v-model="proyecto.id" readonly/>
                        </div>

                        <div class="form-group">
                            <label class="form-control-label " v-text="$t('ciecytApp.proyecto.titulo')" for="proyecto-titulo">Titulo</label>
                            <input type="text" class="form-control" name="titulo" id="proyecto-titulo"
                                   v-model="proyecto.titulo"/>
                        </div>


                    </div>


                    <!-- ADR   -->
                    <div class="col-md-6 col-12">
                        <b-form-group
                            label="Integrante"
                            label-for="usuario"
                        >
                           
                            <b-form-select :options="users" v-model="proyecto.estudianteId" text-field="nombresApellidos" value-field="id" id="proyecto-estudianteId" >

                            </b-form-select>
                        </b-form-group>
                    </div>


                </div>

              
                <div>

                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" class="btn btn-primary">
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
    import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

    const validations: any = {
        proyecto: {
            titulo: {},
            url: {},
            lugarEjecucion: {},
            duracion: {},
            fechaIni: {},
            fechaFin: {},
            contrapartidaPesos: {},
            contrapartidaEspecie: {},
            palabrasClave: {},
            convocatoria: {}
        }
    };

    @Component({
        components: { MenuLateral },
        validations
    })

    export default class PropuestaIntegrantes extends Vue {
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
        public proyId: any;

        public isSaving = false;
        
        
        beforeRouteEnter(to, from, next) {
             //this.proyectoId = this.$route.params.proyectoId;
              

              next(vm => {
                if (to.params.proyectoId) {
                    vm.retrieveProyecto(to.params.proyectoId);
                    //this.proyectoId = to.params.proyectoId;
                    //console.log(to.router.params.proyectoId);
                }
                /*if (to.params.cicloPropedeuticoId) {
                    vm.retrieveCicloPropedeutico(to.params.cicloPropedeuticoId);
                }*/
                vm.initRelationships();
            });
        }

        public save(): void {
            this.isSaving = true;
            if (this.proyecto.id) {
                this.proyectoService()
                .update(this.proyecto)
                .then(param => {
                    this.isSaving = false;
                    this.$router.go(-1);
                    //console.log(this.proyecto.id);
                    //this.$router.go("http://localhost:9000/propuesta/integrantes")
                    const message = this.$t('ciecytApp.proyecto.updated', { param: param.id });
                    this.alertService().showAlert(message, 'info');
                });
            } /*else {
            this.proyectoService()
                .create(this.proyecto)
                .then(param => {
                    this.isSaving = false;
                    //this.$router.go(-1);
                    // this.$router.go("http://localhost:9000/propuesta/integrantes/" + `${proyecto.getId()}` )
                    this.$router.push({ name: 'PropuestaIntegrantesView', params: { proyectoId: `${this.proyecto.id}` } })
                    const message = this.$t('ciecytApp.proyecto.created', { param: param.id });
                    this.alertService().showAlert(message, 'success');
                });
            }*/
        }


      

        /*
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
        */
        initRelationships() {
             //Obtenienedo las modalidades
             //this.$route.query.name
             this.proyId = this.$route.query.proyectoId;
            this.proyectoService()
                .find(this.proyId)
                .then(res => {
                    this.proyecto=res;
                });
            /*
            //Obtenienedo las modalidades
            this.modalidadService()
                .retrieve()
                .then(res => {
                    this.modalidads = res.data;
                });

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
            */
            //Obteniendo los usuarios asesores
            this.usuarioService()
                .retrieveEstudiantes()
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
