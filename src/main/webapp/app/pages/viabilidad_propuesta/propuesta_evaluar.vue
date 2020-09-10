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
                     <div class="col-12" v-for="(ep, e) in proyectoRespuests" :key="e">
                       

                    

                         <b-form-group
                            :label="ep.proyectoRespuestasPreguntaPregunta"
                            :label-for="`ep-${i}`" 
                          
                       >

                        
                       <div class="form-group" >

                            <b-form-textarea rows="5"  max-rows="10" class="form-control" :name="`ep-${i}`"
                            :id="`ep-${i}`" 
                                   v-model="ep.respuesta"   />
                             


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
import ProyectoRespuestasService from '@/entities/proyecto-respuestas/proyecto-respuestas.service';
import { IProyectoRespuestas, ProyectoRespuestas } from '@/shared/model/proyecto-respuestas.model';
import PreguntaService from '@/entities/pregunta/pregunta.service';
import { IPregunta, Pregunta } from '@/shared/model/pregunta.model';
import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
import ProyectoService from '@/entities/proyecto/proyecto.service';


    const validations: any = {};

   @Component({
        components: { MenuLateral },
        validations
    })


export default class PropuestaEvaluar extends Vue {


   @Inject('proyectoService') private proyectoService: () => ProyectoService;
   @Inject('proyectoRespuestasService') private proyectoRespuestasService: () => ProyectoRespuestasService;
   @Inject('preguntaService') private preguntaService: () => PreguntaService;
   @Inject('alertService') private alertService: () => AlertService;


    public pregunts: IPregunta[] = [];
    public proyectoRespuests: IProyectoRespuestas[] =[];
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

                for (let e of this.proyectoRespuests) {
                    //Actualizando el integrante

                    if (e.id) {
                        this.proyectoRespuestasService().update(e); //envio un elemento
                    } else {
                        //Creando un nuevo integrante
                        this.proyectoRespuestasService().create(e)
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
               //console.log(this.proyId); //funciona


                //this.proyecto = await this.proyectoService().find(this.proyId);
               await this.proyectoService()
                    .find(this.proyId)
                    .then(res=> {
                        this.proyecto = res;
                    })

                this.modalidadId = this.proyecto.proyectoModalidadId;

 
              //recuperar las elementosProyecto enviando un idProyecto (api)
            //
            
              this.proyectoRespuestasService()
                .retrieveProyectoRespuestas(this.proyId)   //recup los proyresp con un idproy
                .then(res=> {

                    this.proyectoRespuests = res.data;

                   
               
                });
            ////////////////////////////////////////////////////77    

                       //Obtenienedo los elementos de acuerdo a la modalidad
           
              this.preguntaService()
                //.retrieveElementosModalidad( this.modalidadId) 
                .retrievePreguntasModalidad( this.modalidadId) //recup pregs por molalid 
                //.retrieve( this.modalidadId)
                .then(res => {
                    this.pregunts = res.data;
                  //copiar los datos de pregunta a proyecto-respuestas
                  this.pregunts.forEach(e => {
                 
                  var pregProy: IProyectoRespuestas = new ProyectoRespuestas();
                  pregProy.proyectoRespuestasPreguntaPregunta= e.pregunta;
                 
                  pregProy.proyectoRespuestasPreguntaId = e.id;
                  //elemProy.elemento = e.elemento;
                  pregProy.proyectoRespuestasProyectoId = this.proyId;
                  this.proyectoRespuests.push(pregProy);
                  

                  }); 
                   console.log(this.proyectoRespuests);
                 });
            
            ///////////////////////////////////////////////////////7

              

            }
            catch(e){
              console.log("error al recuperar la informacion de elemento ");
            }

        }

}
</script>

<style scoped>
</style>
