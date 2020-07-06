<template>
    <div class="row">
        <div class="col-sm-4">
            <menu-lateral></menu-lateral>
        </div>
        <div class="col-sm-8">
            <form>
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label class="form-control-label" for="titulo_proyecto">Titulo del proyecto</label>
                            <input type="text" class="form-control" name="titulo_proyecto" id="titulo_proyecto"/>
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
                            
                            <b-form-select :options="users" v-model="user" text-field="login" value-field="id" id="user">
                                 

                            </b-form-select>
                        </b-form-group>
                    </div>


                </div>
            </form>
        </div>
    </div>
</template>

<script lang="ts">
    import Component from 'vue-class-component';
    import { Inject, Vue } from 'vue-property-decorator';
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

    @Component({
        components: { MenuLateral }
    })
    export default class PropuestaInformacionGeneral extends Vue {
        @Inject('modalidadService') private modalidadService: () => ModalidadService;
        @Inject('facultadService') private facultadService: () => FacultadService;
        @Inject('lineaInvestigacionService') private lineaInvestigacionService: () => LineaInvestigacionService;
        @Inject('usuarioService') private usuarioService: () => UsuarioService;

        public modalidads: IModalidad[] = [];
        public facultades: IFacultad[] = [];
        public lineas_investigacion: ILineaInvestigacion[] = [];
        //public users: IUsuario[] = [];
        public users: IUser[] = [];

        public linea_investigacion: number = null
        public facultad: number = null
        public user: number = null

        beforeRouteEnter(to, from, next) {
            next(vm => {
                /*if (to.params.cicloPropedeuticoId) {
                    vm.retrieveCicloPropedeutico(to.params.cicloPropedeuticoId);
                }*/
                vm.initRelationships();
            });
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
                    this.users = res.data;
                });
        }
    }
</script>

<style scoped>

</style>
