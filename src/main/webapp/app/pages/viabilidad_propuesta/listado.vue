<template>

    <div class="row">

        <div class="col-sm-4">
            <menu-lateral-listado :proyectoId='$route.params.proyectoId'></menu-lateral-listado>
        </div>
        <div class="col-sm-8">
           <form @submit.prevent="save()">
                <div class="row">
                     <div class="col-12" v-for="(p, e) in proyects" :key="e">
                       

                         <div class="form-group">

                           <!-- <b-form-textarea rows="5"  max-rows="10" class="form-control" 
                            
                                   v-model="ep.elementoProyectoProyectoDescripcion" />
                            -->
                            <input type="text" disabled="true"  v-model="p.titulo" />

                        </div>

                       <b-form-group
                            :label="p.titulo"
                            :label-for="`p-${e}`" 
                            :tipo="e.tipo"
                       >

                   
                        </b-form-group>
                         
                    </div>
                 
                </div>


                <div>
<!--
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>



                    <button type="submit" id="save-entity" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
-->

                </div>

            </form>
        </div>
    </div>
</template>

<script lang="ts">
import { Component, Inject, Vue } from 'vue-property-decorator';
import MenuLateralListado from '@/components/propuesta_listado/menu_lateral_listado.vue';
import AlertService from '@/shared/alert/alert.service';



import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
import ProyectoService from '@/entities/proyecto/proyecto.service';


    const validations: any = {};

   @Component({
        components: { MenuLateralListado },
        validations
    })


export default class Listado extends Vue {


   @Inject('proyectoService') private proyectoService: () => ProyectoService;
  
   @Inject('alertService') private alertService: () => AlertService;


    
   //  public elementosProyecto: IElementoProyecto[] =[];
    public proyects: IProyecto[] = [];
    public proyId: any = null;
   

    public isSaving = false;


        beforeRouteEnter(to, from, next) {
            next(vm => {

                    vm.initRelationships();
            });
        }

        public save(): void {//debo guardar un elemento proyecto
            try {
                this.isSaving = true;

                for (let e of this.proyects) {
                    //Actualizando el integrante

                    if (e.id) {
                        this.proyectoService().update(e); //envio un elemento
                    } else {
                        //Creando un nuevo integrante
                        this.proyectoService().create(e)
                        .then(param => {
                            this.$router.push({ name: 'PropuestaEvaluarView',params:{ proyectoId: this.proyId}});
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
             /*  await this.proyectoService()
                    .find(this.proyId)
                    .then(res=> {
                        this.proyecto = res;
                    })

                this.modalidadId = this.proyecto.proyectoModalidadId;
            */
 
              //recuperar las elementosProyecto enviando un idProyecto (api)
            //
            
              this.proyectoService()
                .retrieve()
                .then(res=> {

                    this.proyects = res.data;
    
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
