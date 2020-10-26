<template>

    <div class="row">
<!--
        <div class="col-sm-4">
            <menu-lateral :proyectoId='$route.params.proyectoId'></menu-lateral>
        </div>
-->
        <div class="col-sm-8">
           <form @submit.prevent="save()">
                <div class="row">
                     <div class="col-12" v-for="(ep, i) in proyectoRespuests" :key="i">
    
                    <b-card  
       
                    border-variant="primary"
        
                    header-bg-variant="light"
                     body-bg-variant="light"
                    header-text-variant="info">
                      

                     <b-form-group
                            :label="ep.elemento"
                            :label-for="`ep-${i}`" 
                            :description="ep.proyectoRespuestasPreguntaPregunta"
                                                   
                       >
                       <div class="form-group" >

                            <b-form-textarea rows="2"  max-rows="10" class="form-control" :name="`ep-${i}`"
                            :id="`ep-${i}`" 
                                   v-model="ep.elemento"   />
                       </div>
                       </b-form-group>


                       <!--- dato  -->
                          <b-form-group
                            
                                                   
                       >
                       <div class="form-group" >

                            <b-form-textarea rows="2"  max-rows="10" class="form-control" :name="`ep-${i}`"
                            :id="`ep-${i}`" 
                                   v-model="ep.dato"   />
                            </div>
                       </b-form-group>

                        <!----------------------------------------------->
                        <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.proyectoRespuestas.respuesta')" for="proyecto-respuestas-respuesta">Respuesta</label>
                        <select class="form-control" name="respuesta"  v-model="ep.respuesta" id="proyecto-respuestas-respuesta" >
                            <option value="CUMPLE" v-bind:label="$t('ciecytApp.EnumRespuestas.CUMPLE')">CUMPLE</option>
                            <option value="NO_CUMPLE" v-bind:label="$t('ciecytApp.EnumRespuestas.NO_CUMPLE')">NO_CUMPLE</option>
                            <option value="NO_APLICA" v-bind:label="$t('ciecytApp.EnumRespuestas.NO_APLICA')">NO_APLICA</option>
                        </select>
                    </div>

                     </b-card>
                     <hr>
                       <!------------------------------------------------->
    
                    </div>
 
                     
                </div>
              

<div class="form-group">
  <label class="form-control-label" for="proyecto-respuestas-viabilidad">Viabilidad</label>
                     <input
                        type="checkbox"
                        class="form-control"
                        name="proyecto-respuestas-viabilidad"
                        id="proyecto-respuestas-viabilidad"
                        v-model="proyecto.viable"
                      />
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
import ProyectoRespuestasService from '@/entities/proyecto-respuestas/proyecto-respuestas.service';
import { EnumRespuestas, IProyectoRespuestas, ProyectoRespuestas } from '@/shared/model/proyecto-respuestas.model';
import PreguntaService from '@/entities/pregunta/pregunta.service';
import { IPregunta, Pregunta } from '@/shared/model/pregunta.model';
import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
import ProyectoService from '@/entities/proyecto/proyecto.service';
import { IElementoProyecto, ElementoProyecto } from '@/shared/model/elemento-proyecto.model';
import ElementoProyectoService from '@/entities/elemento-proyecto/elemento-proyecto.service';


    const validations: any = {};

   @Component({
        components: { MenuLateral },
        validations
    })


export default class PropuestaEvaluar extends Vue {


   @Inject('proyectoService') private proyectoService: () => ProyectoService;
   @Inject('proyectoRespuestasService') private proyectoRespuestasService: () => ProyectoRespuestasService;
   @Inject('preguntaService') private preguntaService: () => PreguntaService;
   @Inject('elementoProyectoService') private elementoProyectoService: () => ElementoProyectoService;
   @Inject('alertService') private alertService: () => AlertService;


    public pregunts: IPregunta[] = [];
    public proyectoRespuests: IProyectoRespuestas[] =[];
    public elementoProyects: IElementoProyecto[]=[];
    public elemProy: ElementoProyecto;
    public proyecto: IProyecto = new Proyecto();
    public proyId: any = null;
    public modalidadId: number = 0;
    public enumRespuestas: EnumRespuestas;

    public isSaving = false;


        beforeRouteEnter(to, from, next) {
            next(vm => {
                    
                    vm.initRelationships();
            });
        }

        public save(): void {//debo guardar un elemento proyecto
            try {
                //this.enumRespuestas.
                this.isSaving = true;
                for (let e of this.proyectoRespuests) {
                    if (e.id) {
                        this.proyectoRespuestasService().update(e); 
                    } else {
                        this.proyectoRespuestasService().create(e)
                        .then(param => {
                            this.$router.push({ name: 'PropuestaPresupuestoView',params:{ proyectoId: this.proyId}});
                        });
                    }
                }
                

            } catch (e) {
                //TODO: mostrar mensajes de error
            }

            //actualizar el proyecto para que se guarde la viabilidad
             try {
                this.proyectoService().update(this.proyecto);

                  } catch (e) {
                //TODO: mostrar mensajes de error
            }
        }

        async initRelationships() {
           try {
               this.proyId = parseInt(this.$route.params.proyectoId);
               this.proyecto = await this.proyectoService().find(this.proyId);
               this.modalidadId = this.proyecto.proyectoModalidadId;
              //recuperar las elementosProyecto enviando un idProyecto (api)
          
              this.proyectoRespuestasService()
                .retrieveProyectoRespuestas(this.proyId)   //recup los proyresp con un idproy
                .then(res=> {

                    this.proyectoRespuests = res.data;
                });
               await this.elementoProyectoService()
                .retrieveElementoProyecto(this.proyId)   //recup los proyresp con un idproy
                .then(res=> {

                    this.elementoProyects = res.data;
                    //console.log(this.elementoProyects);
                });
              //Obtenienedo los elementos de acuerdo a la modalidad
              await this.preguntaService()
                .retrievePreguntasModalidad( this.modalidadId) //recup pregs por molalid 
                .then(res => {
                    this.pregunts = res.data;
                  //cliclo para copiar los datos de pregunta a proyecto-respuestas
                  this.pregunts.forEach(e => {
                  var proyResp: IProyectoRespuestas = new ProyectoRespuestas();
                  proyResp.proyectoRespuestasPreguntaPregunta= e.pregunta;
                  proyResp.proyectoRespuestasPreguntaId = e.id;
                  //elemProy.elemento = e.elemento;
                  proyResp.proyectoRespuestasProyectoId = this.proyId;
                  //ubicar un elemento, no esta en proyectoRespuestas
                  proyResp.elemento = e.elemento;
                
                  //this.elemProy = this.buscarElementoProyecto(e.elementoId);
                  this.elementoProyects.forEach(x => {
                      console.log("Entra al ciclo elementoProyecto");
                    if (x.elementoProyectoElementoId == e.elementoId){
                         proyResp.dato = x.dato;    
                    }
                  });

                 
                  this.proyectoRespuests.push(proyResp);
                  }); 
                   //console.log(this.proyectoRespuests);
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
