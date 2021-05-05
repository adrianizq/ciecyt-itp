<template>

    <div class="row">

        <div class="col-sm-8">
           <form @submit.prevent="save()">
                <div class="row">
                   
                  
              <table> 
               <tr><td><h2>Envío de la Propuesta - Asesor</h2></td></tr>
               <tr><td>Título: {{proyecto.titulo}} </td></tr>
               <tr><td>Programa: {{proyecto.programa}} </td></tr>
                <tr><td> &nbsp; </td></tr>
             </table>
             <br />   <br />
           
                    <div class="col-12" v-for="(ep, i) in proyectoRespuests" :key="i">
                    <b-card  
                      border-variant="primary"
                      header-bg-variant="light"
                      body-bg-variant="light"
                     header-text-variant="info">
                     <div class="text-secondary"> Tipo de pregunta {{ep.preguntaTipoPreguntaTipoPregunta}} </div>
                     <label  class="p-3 mb-2 bg-info text-white container-fluid">{{ep.encabezado}} </label>
                     
                     <b-form-group
                            :label="ep.elemento"
                            :label-for="`ep-${i}`" 
                            :description="ep.proyectoRespuestasPreguntaPregunta"
                                                   
                       >
                       <div class="form-group" >
                           

                            
                            <b-form-textarea rows="2"  max-rows="10" class="form-control" :name="`ep-${i}`"
                            :id="`ep-${i}`" v-if="ep.elemento"
                                   v-model="ep.elemento"  disabled="true"  />
                       </div>
                       </b-form-group>

                        
                       <!--- dato  -->
                          <b-form-group>
                       <div class="form-group" >

                            <b-form-textarea rows="2"  max-rows="10" class="form-control" :name="`ep-${i}`"
                            :id="`ep-${i}` " 
                                   v-model="ep.dato"  v-if="ep.dato!=null" readonly="true" />
                            </div>
                       </b-form-group>

                        <!-- TIPOS Pregunta--------------------------------------------->
                        <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.proyectoRespuestas.respuesta')" for="proyecto-respuestas-respuesta">Respuesta</label>
                        <select class="form-control" c  v-model="ep.respuesta" 
                          id="proyecto-respuestas-respuesta"
                          v-if="ep.preguntaTipoPreguntaTipoPregunta==`Cumple NoCumple NoAplica`" >
                            <option value="CUMPLE" v-bind:label="$t('ciecytApp.EnumRespuestas.CUMPLE')">CUMPLE</option>
                            <option value="NO_CUMPLE" v-bind:label="$t('ciecytApp.EnumRespuestas.NO_CUMPLE')">NO_CUMPLE</option>
                            <option value="NO_APLICA" v-bind:label="$t('ciecytApp.EnumRespuestas.NO_APLICA')">NO_APLICA</option>
                        </select>
                        
                        <select class="form-control" name="respuesta"  v-model.bool="ep.siNo" 
                          id="proyecto-respuestas-respuesta"
                          v-if="ep.preguntaTipoPreguntaTipoPregunta==`Si o No`" >
                            <option value="true" v-bind:label="$t('ciecytApp.EnumRespuestas.SI')">SI</option>
                            <option value="false" v-bind:label="$t('ciecytApp.EnumRespuestas.NO')">NO</option>
                        </select>

                        <b-form-input  type="range" min="0" v-bind:max="ep.puntajeMaximo" step="0.1"
                         v-if="ep.preguntaTipoPreguntaTipoPregunta==`Nota (con puntaje)`" 
                         v-model="ep.respuestaNumero">
                          <div class="mt-2">Nota: {{ ep.respuestaNumero }}</div></button>
                        </b-form-input>
                         

                        <b-form-textarea  
                         v-if="ep.preguntaTipoPreguntaTipoPregunta==`Libre (sin puntaje ni viabilidad)`" 
                         v-model="ep.respuestaTexto">
                        </b-form-textarea>
                  
                        </div>

                     </b-card>
                     <hr>
                          
    
                    </div> <!-- fin del for each -->
                    <!-- ------------------------------------------->
                    <div class="col-12" >
                    <b-card  
                      border-variant="primary"
                      header-bg-variant="light"
                      body-bg-variant="light"
                     header-text-variant="info">
                    <b-form-group 
                    description="Si tiene comentarios o sugerencias adicionales sobre la propuesta diligencie este apartado">
                    <label class="form-control-label" 
                    v-text="$t('ciecytApp.proyecto.recomendaciones')" for="proyecto-recomendaciones">Recomendaciones</label>
                       
                     <div class="form-group" >
                       <b-form-textarea  class="form-control" name="proyecto-recomendaciones"
                                   v-model="proyecto.recomendaciones"  />
                        </div>
                       </b-form-group>
                       </b-card>
                       </div>
                    <!-- ------------------------------------------->
                    
                     
                </div>
              

<div class="form-group">
                
                <br>Marque <strong>Enviar </strong> si la propuesta cumple con los requisitos establecidos por el Ciecyt.
                <br>Si la propuesta no cumple o está icompleta, marque <strong>No Enviar</strong> <br>
                <div  class="p-3 mb-2 bg-danger text-white container-fluid">
                <input type="radio" value="true" v-model="proyecto.enviado">
                <label for="uno">Enviar la Propuesta</label>
                <br>
                <input type="radio"  value="false" v-model="proyecto.enviado">
                <label for="Dos">No Enviar la Propuesta</label>
                <br>
                
                <br>
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
import FasesService from '@/entities/fases/fases.service';
import { IFases, Fases } from '@/shared/model/fases.model';


    const validations: any = {};

   @Component({
        components: { MenuLateral },
        validations
    })


export default class PropuestaEvaluar extends Vue {


   @Inject('proyectoService') private proyectoService: () => ProyectoService;
   @Inject('proyectoRespuestasService') private proyectoRespuestasService: () => ProyectoRespuestasService;
   @Inject('preguntaService') private preguntaService: () => PreguntaService;
   @Inject('fasesService') private fasesService: () => FasesService;
   @Inject('elementoProyectoService') private elementoProyectoService: () => ElementoProyectoService;
   @Inject('alertService') private alertService: () => AlertService;


    public pregunts: IPregunta[] = [];
    public fase: IFases = new Fases();
    public proyectoRespuests: IProyectoRespuestas[] =[];
    public elementoProyects: IElementoProyecto[]=[];
    public elemProy: ElementoProyecto;
    public proyecto: IProyecto = new Proyecto();
    public proyId: any = null;
    public modalidadId: number = 0;
    public enumRespuestas: EnumRespuestas;

    public isSaving = false;
    public proyectoRespuestasDatos: boolean;
    public  authority: any="ROLE_VIABILIDAD";
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

                 res= await this.fasesService()
                .retrieveFase("Propuesta")   //recup los ElementosProyecto con un idproy
                 this.fase = res.data;

                res= await this.proyectoRespuestasService()
                .retrieveProyectoRespuestas(this.proyId)   //recup los proyresp con un idproy
                this.proyectoRespuests = res.data;
                if (this.proyectoRespuests.length>0){
                        this.proyectoRespuestasDatos=true;
                    }
                else{
                        this.proyectoRespuestasDatos=false;
                    }
                    //console.log(this.proyectoRespuestasDatos);
               
                

              //Obtenienedo los elementos de acuerdo a la modalidad
              //if (this.proyectoRespuests.length==0){
                res = await  this.preguntaService()
                //.retrievePreguntasModalidad( this.modalidadId) //recup pregs por molalid 
                //.retrievePreguntasModalidadyFase( this.modalidadId, this.fase.id) //recup pregs por molalid y fase
                 .retrievePreguntasModalidadyFaseyAuthority(this.modalidadId, this.fase.id, this.authority)
                
                    this.pregunts = res.data;
                    /////////////////////////////////77
                this.pregunts.forEach(e => {
                  var proyResp: IProyectoRespuestas = new ProyectoRespuestas();
                  proyResp.proyectoRespuestasPreguntaPregunta= e.pregunta;
                  proyResp.proyectoRespuestasPreguntaId = e.id;
                  proyResp.proyectoRespuestasProyectoId = this.proyId;
                  //ubicar un elemento, no esta en proyectoRespuestas
                  proyResp.elemento = e.preguntaElemento;
                  proyResp.preguntaTipoPreguntaId = e.preguntaTipoPreguntaId;
                  proyResp.preguntaTipoPreguntaTipoPregunta = e.preguntaTipoPreguntaTipoPregunta;
                  proyResp.encabezado = e.encabezado;
                  proyResp.puntajeMaximo = e.puntajeMaximo;
                  this.elementoProyects.forEach(x => {
                      console.log("Entra al ciclo elementoProyecto");
                    if (x.elementoProyectoElementoId == e.preguntaElementoId){
                         proyResp.dato = x.dato;    
                    }
                  });
                  if (!this.proyectoRespuestasDatos){
                   this.proyectoRespuests.push(proyResp);
                  }
                  
                }); //fin del foreach pregunts
                    //////////////////////////////////77
               



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
