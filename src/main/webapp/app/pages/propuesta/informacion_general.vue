<template>
    <div class="row">
        <div class="col-sm-4">
            <menu-lateral></menu-lateral>
        </div>
        <div class="col-sm-8">
            <form>
                <div class="row">
                    <div class="col-12">
                       <!-- Jeison 
                        <div class="form-group">
                            <label class="form-control-label" for="titulo_proyecto">Titulo del proyecto</label>
                            <input type="text" class="form-control" name="titulo_proyecto" id="titulo_proyecto"
                            v-model=proyecto.titulo />
                        </div>
                        -->
                        
                       <div class="form-group">
                            <label class="form-control-label" v-text="$t('ciecytApp.proyecto.titulo')" for="proyecto-titulo">Titulo</label>
                            <input type="text" class="form-control" name="titulo" id="proyecto-titulo"
                                 v-model=proyecto.titulo />
                        </div>
                    </div>

                    <div class="col-md-6 col-12">
                        <b-form-group
                            label="Modalidad"
                            label-for="modalidad"
                        >
                            <b-form-select :options="modalidads" text-field="modalidad" value-field="id" id="modalidad">

                            </b-form-select>
                        </b-form-group>
                    </div>

                    <div class="col-md-6 col-12">
                        <b-form-group
                            label="Facultad"
                            label-for="facultad"
                        >
                            <b-form-select :options="facultades" v-model="facultad" text-field="facultad" value-field="id" id="facultad">

                            </b-form-select>
                        </b-form-group>
                    </div>

                    <div class="col-md-6 col-12">
                        <b-form-group
                            label="Linea de investigación"
                            label-for="linea_investigacion"
                        >
                            <b-form-select text-field="linea" v-model="linea_investigacion" value-field="id" id="linea_investigacion">
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
                            <b-form-select text-field="linea" value-field="id" id="linea_investigacion">
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
                            <!--
                            <b-form-select :options="users" v-model="user" text-field="firstName" value-field="id" id="user">
                             -->    
                            <b-form-select :options="users" v-model="user" text-field = "nombresApellidos"  value-field="id" id="user">
                               
                            </b-form-select>
                        </b-form-group>
                    </div>


                </div>

<!-- 
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                     <button type="submit" id="save-entity" :disabled="$v.proyecto.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
-->
                <div>
                    
                    <button type="submit" id="save-entity"  class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="save">Save</span>
                    </button>
                    

                    
                </div>

            </form>
        </div>
    </div>
</template>

<script lang="ts">
    import Component from 'vue-class-component';
    import { Inject, Vue } from 'vue-property-decorator';
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
    import ProyectoService from  '@/entities/proyecto/proyecto.service';


    

    @Component({
        components: { MenuLateral }
        
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
        //public users: IUsuario[] = [];
        public users: IUser[] = [];

        public linea_investigacion: number = null
        public facultad: number = null
        public user: number = null
        public nombresApellidos: string = null
        public proyecto: IProyecto = new Proyecto();

     public isSaving = false;

        beforeRouteEnter(to, from, next) {
            next(vm => {
                if (to.params.proyectoId) {
                    vm.retrieveProyecto(to.params.proyectoId);
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
                    const message = this.$t('ciecytApp.proyecto.updated', { param: param.id });
                    this.alertService().showAlert(message, 'info');
                });
            } else {
            this.proyectoService()
                .create(this.proyecto)
                .then(param => {
                this.isSaving = false;
                this.$router.go(-1);
                const message = this.$t('ciecytApp.proyecto.created', { param: param.id });
                this.alertService().showAlert(message, 'success');
                });
            }
  }


        get LineasInvestigacion(){
            return this.lineas_investigacion.filter(linea => {
                return (!linea.lineaPadreId && linea.lineaInvestigacionFacultadId == this.facultad)
            })
        }

        get SubLineas(){
            return this.lineas_investigacion.filter(linea => {
                return (linea.lineaPadreId == this.linea_investigacion && linea.lineaPadreId)
            })
        }

        //get NombresApellidos(){
        //      return this.user.firstName + " " + this.user.lastName
              
        //    }

        
        initRelationships() {
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
                    this.lineas_investigacion = res.data
                });

            //Obteniendo los usuarios asesores
            this.usuarioService()
                .retrieveAsesores()
                .then(res => {
                    //this.users = res.data;
                    //console.log(res.data)
                     res.data.forEach((item) => { 
                     item.nombresApellidos = item.firstName + " " + item.lastName 
                     this.users.push(item);
                     })
                     //console.log(this.users)
                });
            
        }
    }
</script>

<style scoped>

</style>
