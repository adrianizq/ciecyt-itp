<template>

    <div class="row">

        <div class="col-sm-4">
            <menu-lateral :proyectoId='$route.params.proyectoId'></menu-lateral>
        </div>
        <div class="col-sm-8">
           <form @submit.prevent="save()">
                <div class="row">
                     <div class="col-12" v-for="(ep, e) in elementosProyecto" :key="e">
                       

                         <div class="form-group">

                           <!-- <b-form-textarea rows="5"  max-rows="10" class="form-control" 
                            
                                   v-model="ep.elementoProyectoProyectoDescripcion" />
                            -->

                        </div>

                       <b-form-group
                            :label="ep.elementoProyectoElementoElemento"
                            :label-for="`ep-${i}`" 
                            :description="ep.elementoProyectoProyectoDescripcion"
                       >
                       <div class="form-group" >

                            <b-form-textarea rows="5"  max-rows="10" class="form-control" :name="`ep-${i}`"
                            :id="`ep-${i}`" 
                                   v-model="ep.dato" />


                        </div>

                        </b-form-group>
                    </div>
                </div>


                <div>

                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>

<!--
                    <router-link :to="{name: 'PropuestaIntegrantesView', query: {proyectoId: this.proyecto.id}}"  tag="button" class="btn btn-primary">
                                <font-awesome-icon icon="save"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.save')">Save</span>
                            </router-link>
-->

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
import MenuLateral from '@/components/propuesta/menu_lateral.vue';
import AlertService from '@/shared/alert/alert.service';
import ElementoService from '@/entities/elemento/elemento.service';
import { IElemento, Elemento } from '@/shared/model/elemento.model';
import ElementoProyectoService from '@/entities/elemento-proyecto/elemento-proyecto.service';
import { IElementoProyecto, ElementoProyecto } from '@/shared/model/elemento-proyecto.model';
import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
import ProyectoService from '@/entities/proyecto/proyecto.service';


    const validations: any = {};

   @Component({
        components: { MenuLateral },
        validations
    })


export default class Elementos extends Vue {


   @Inject('proyectoService') private proyectoService: () => ProyectoService;
   @Inject('elementoService') private elementoService: () => ElementoService;
   @Inject('elementoProyectoService') private elementoProyectoService: () => ElementoProyectoService;
   @Inject('alertService') private alertService: () => AlertService;


    public elements: IElemento[] = [];
    public elementosProyecto: IElementoProyecto[] =[];
    //public elemProy: ElementoProyecto;
    public proyecto: IProyecto = new Proyecto();
    public proyId: any = null;
    public modalidadId: number = 0;

    public isSaving = false;


        beforeRouteEnter(to, from, next) {
            next(vm => {

                    vm.initRelationships();
            });
        }

        public save(): void {//debo guardar un elemento proyecto
            try {
                this.isSaving = true;

                for (let e of this.elementosProyecto) {
                    //Actualizando el integrante

                    if (e.id) {
                        this.elementoProyectoService().update(e); //envio un elemento
                    } else {
                        //Creando un nuevo integrante
                        this.elementoProyectoService().create(e)
                        .then(param => {
                            this.$router.push({ name: 'PropuestaPresupuestoView',params:{ proyectoId: this.proyId}});
                        });
                    }
                }

            } catch (e) {
                //TODO: mostrar mensajes de error
            }
        }

        async initRelationships() {
           try {


               this.proyId = parseInt(this.$route.params.proyectoId);


                //this.proyecto = await this.proyectoService().find(this.proyId);
                this.proyecto = await this.proyectoService().find(this.proyId);

                this.modalidadId = this.proyecto.proyectoModalidadId;


            ///////////////////////////////////////////////////////7
              //recuperar las elementosProyecto enviando un idProyecto (api)
            //
            
            this.elementoProyectoService()
                .retrieveElementoProyecto(this.proyId)
                .then(res=> {

                    this.elementosProyecto = res.data;
                    console.log(res.data);
                })
            ////////////////////////////////////////////////////77    

              
            //Obtenienedo los elementos de acuerdo a la modalidad
            this.elementoService()
                .retrieveElementosModalidad( this.modalidadId)
                .then(res => {
                    this.elements = res.data;


                    //copiar los datos de elementos a elemento-proyecto
           

             this.elements.forEach(e => {
                  var elemProy: IElementoProyecto = new ElementoProyecto();
                  elemProy.elementoProyectoElementoElemento= e.elemento;
                  elemProy.elementoProyectoProyectoDescripcion = e.descripcion;
                  elemProy.elementoProyectoElementoId = e.id;
                  elemProy.elementoProyectoProyectoId = this.proyId;
                  this.elementosProyecto.push(elemProy);

                  //console.log("dentro del ciclo");

            });

                });
            }
            catch(e){
              console.log("error al recuperar la informacion de elemento ");
            }

        }

}
</script>

<style scoped>
</style>
