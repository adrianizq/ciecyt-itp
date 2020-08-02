<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral :proyectoId='$route.params.proyectoId'></menu-lateral>
    </div>
    <div class="col-sm-8">
      <div :key="key" v-for="(item, key) in resultados">
        <b-card>
          <div class="row">
            <!--java se coloco :key-->

            <div class="col-12">
              <div class="form-group">
                <label class="form-control-label">Resultados/Productos Esperados</label>
                <label class="float-right" id="contar">#{{key+1}}</label>
                <input type="text" class="form-control" name="resultado"
                 v-model="item.resultado" />
              </div>
            </div>

            <div class="col-12">
              <div class="form-group">
                <label class="form-control-label">Indicador</label>
                <input type="text" class="form-control" 
                name="indicador"
                v-model="item.indicador"
                 />
              </div>
            </div>
            <div class="col-12">
              <div class="form-group">
                <label class="form-control-label">Beneficiario</label>
                <input type="text" class="form-control" 
                name="beneficiario"
                v-model="item.beneficiario" />
              </div>
            </div>
          </div>
        </b-card>
        <hr />
      </div>
      <div class="col-12 text-left">
        <button type="submit" id="save-entity" 
         class="btn btn-primary float-right"
         @click="save()">
          <font-awesome-icon :icon="['fas', 'save']"></font-awesome-icon>&nbsp;
          <span>Nuevo Resultado o Producto</span>
        </button>
        <button
          type="submit"
          id="save-entity"
          class="btn btn-primary float-right"
          @click="nuevo_resultado()"
        >
          <font-awesome-icon :icon="['fas', 'plus']"></font-awesome-icon>&nbsp;
          <span></span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Inject, Vue } from 'vue-property-decorator';
import MenuLateral from '@/components/propuesta/menu_lateral.vue';
import { IResultadosEsperados, ResultadosEsperados } from '@/shared/model/resultados-esperados.model';
import ResultadosEsperadosService from '@/entities/resultados-esperados/resultados-esperados.service';
import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
import ProyectoService from '@/entities/proyecto/proyecto.service';

const validations: any = {};

var contar = 1;
@Component({
  components: { MenuLateral }
})
export default class Resultados_esperados extends Vue {

   @Inject('resultadosEsperadosService') private resultadosEsperadosService: () => ResultadosEsperadosService;
   @Inject('proyectoService') private proyectoService: () => ProyectoService;

 resultados = [];
  nuevo_resultado() {
   
    
    this.resultados.push({
      resultadosEsperadosProyectoId: this.proyId      
     });


 
  }
   public proyecto: IProyecto = new Proyecto();
   public proyId: any = null;
   public resultadosEsperads: IResultadosEsperados[] = [];
   public isSaving = false;
   
    beforeRouteEnter(to, from, next) {
            next(vm => {
              
                    vm.initRelationships();
                   
            });
    }

            public save(): void {//debo guardar un elemento proyecto
            try {
                this.isSaving = true;
                
                for (let e of this.resultados) {
                    //Actualizando el integrante
                     var resultado = new ResultadosEsperados();

                       
            
                    if (e.id) {
                        this.resultadosEsperadosService().update(e); //envio un elemento
                    } else {
                        
                        this.resultadosEsperadosService().create(e)
                        .then(param => {
                            this.$router.push({ name: 'PropuestaResultadosEsperadosView',params:{ proyectoId: this.proyId}});
                        });
                    }
                }

            } catch (e) {
                //TODO: mostrar mensajes de error
            }
        }


          async initRelationships() {
           try {

               this.nuevo_resultado() ; //crea una primera tarjeta
             this.proyId = parseInt(this.$route.params.proyectoId);

             //this.proyecto = await this.proyectoService().find(this.proyId);
             this.proyecto = await this.proyectoService().find(this.proyId);


              console.log(this.proyId);
            
            //recuperar los resultadosEperados enviando un idProyecto (api)
            //retrieveResultadosEsperadosProyecto
            
            this.resultadosEsperadosService()
                .retrieveResultadosEsperados(this.proyId)
                .then(res=> {

                    this.resultadosEsperads = res.data;
                    console.log(res.data);
                })
            
         


            }
            catch(e){ 
              console.log("error al recuperar la informacion de presupuesto ");
            }

            
            
        }
        }



</script>

<style scoped>
</style>
