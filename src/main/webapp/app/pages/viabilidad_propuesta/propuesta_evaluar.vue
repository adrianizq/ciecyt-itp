<template>

    <div class="row">

        <div class="col-sm-8">
           <form @submit.prevent="save()">
                <div class="row">
                     <div class="col-12" v-for="(ep, i) in proyectoRespuests" :key="ep.id">
    
                    <b-card  
       
                    border-variant="primary"
        
                    header-bg-variant="light"
                     body-bg-variant="light"
                    header-text-variant="info">
                  
                     <label>{{ep.encabezado}} Instituto</label>
                     <b-form-group
                            :label="ep.elemento"
                            :label-for="`ep-${i}`" 
                            :description="ep.proyectoRespuestasPreguntaPregunta"
                                                   
                       >
                       <div class="form-group" >
                           

                            
                            <b-form-textarea rows="2"  max-rows="10" class="form-control" :name="`ep-${i}`"
                            :id="`ep-${i}`" v-if="ep.elemento"
                                   v-model="ep.elemento"   />
                       </div>
                       </b-form-group>

                        
                       <!--- dato  -->
                          <b-form-group>
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
                          <div> Tipo de pregunta {{ep.preguntaTipoPreguntaTipoPregunta}} </div>
    
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
    public proyectoRespuestasDatos: boolean;
    public mounted(): void {
    }

        beforeRouteEnter(to, from, next) {
            next(vm => {
                    
                    vm.initRelationships();
            });
        }

        public save(): void {//debo guardar un elemento proyecto
            try {
                //this.pregunts[0].preguntaTipoPreguntaTipoPregunta
                //this.enumRespuestas.
                this.isSaving = true;
                for (let e of this.proyectoRespuests) {
                    if (e.id) {
                     // if (e.proyectoRespuestasProyectoId==this.proyId) {
                        this.proyectoRespuestasService().update(e)
                        .then(param => {
                            //this.$router.push({ name: 'PropuestaPresupuestoView',params:{ proyectoId: this.proyId}});
                            (<any>this).$router.go(0);
                        });
                      
                    } else {
                        
                        this.proyectoRespuestasService().create(e)
                        .then(param => {
                            //this.$router.push({ name: 'PropuestaPresupuestoView',params:{ proyectoId: this.proyId}});
                            (<any>this).$router.go(0);
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

                let res= await this.elementoProyectoService()
                .retrieveElementoProyecto(this.proyId)   //recup los ElementosProyecto con un idproy
                 this.elementoProyects = res.data;

                res= await this.proyectoRespuestasService()
                .retrieveProyectoRespuestas(this.proyId)   //recup los proyresp con un idproy
                this.proyectoRespuests = res.data;
                if (this.proyectoRespuests.length>0){
                        this.proyectoRespuestasDatos=true;
                    }
                else{
                        this.proyectoRespuestasDatos=false;
                    }
                    console.log(this.proyectoRespuestasDatos);
               
                

              //Obtenienedo los elementos de acuerdo a la modalidad
              //if (this.proyectoRespuests.length==0){
                res = await  this.preguntaService()
                //.retrievePreguntasModalidad( this.modalidadId) //recup pregs por molalid 
                .retrievePreguntasModalidadyFase( this.modalidadId, 1) //recup pregs por molalid y fase
                
                    this.pregunts = res.data;
                    /////////////////////////////////77
                                   this.pregunts.forEach(e => {
                  var proyResp: IProyectoRespuestas = new ProyectoRespuestas();
                  proyResp.proyectoRespuestasPreguntaPregunta= e.pregunta;
                  proyResp.proyectoRespuestasPreguntaId = e.id;
                  //elemProy.elemento = e.elemento;
                  proyResp.proyectoRespuestasProyectoId = this.proyId;
                  //ubicar un elemento, no esta en proyectoRespuestas
                  proyResp.elemento = e.preguntaElemento;
                  proyResp.preguntaTipoPreguntaId = e.preguntaTipoPreguntaId;
                  proyResp.preguntaTipoPreguntaTipoPregunta = e.preguntaTipoPreguntaTipoPregunta;
                  proyResp.encabezado = e.encabezado;
                  this.elementoProyects.forEach(x => {
                      console.log("Entra al ciclo elementoProyecto");
                    if (x.elementoProyectoElementoId == e.preguntaElementoId){
                         proyResp.dato = x.dato;    
                    }
                  });
                  if (!this.proyectoRespuestasDatos){
                   this.proyectoRespuests.push(proyResp);
                  }
                  else{
                      this.proyectoRespuests.forEach(p => {
                          if (p.proyectoRespuestasPreguntaId==e.id){
                               p.preguntaTipoPreguntaId = proyResp.preguntaTipoPreguntaId;  
                               p.preguntaTipoPreguntaTipoPregunta = proyResp.preguntaTipoPreguntaTipoPregunta; 
                               p.encabezado = proyResp.encabezado;
                               p.dato = proyResp.dato; 
                               console.log(p.proyectoRespuestasPreguntaId + " igual?" + e.id);
                               console.log(p.encabezado);
                          }
                      });
                  
                  }
                }); //fin del foreach pregunts
                    //////////////////////////////////77
               


              //recuperar las elementosProyecto enviando un idProyecto (api)
          
      
                

                //if (this.proyectoRespuests.length==0){
                  //ciclo para copiar los datos de pregunta a proyecto-respuestas
               
 
                   //console.log(this.proyectoRespuests);
                //}//del if

               console.log(this.proyectoRespuests);
            }
            catch(e){
              console.log("error al recuperar la informacion de elemento ");
            }
        }
        
}
</script>

<style scoped>
</style>
