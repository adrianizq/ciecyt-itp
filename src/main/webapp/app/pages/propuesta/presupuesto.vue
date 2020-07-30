<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral :proyectoId='$route.params.proyectoId'></menu-lateral>
    </div>
    <div class="col-sm-8">
      <div :key="key" v-for="(item, key) in presupuestos">
        <b-card>
          <div class="row">
            <div class="col-12">
              <div class="form-group">
                <label class="form-control-label" for="proyecto-documento">Descripción</label>

                <label class="float-right" id="contar">#{{key+1}}</label>
                <b-form-textarea id="textarea-rows" placeholder="Descripción" rows="2"></b-form-textarea>
              </div>
            </div>


                <div class="col-md-3 col-3">
                  <b-form-group label="Rubro" label-for="rubro">
                    <b-form-select :options="rubros"
                      text-field="rubro"
                      value-field="id"
                      id="rubro"
                    ></b-form-select>
                  </b-form-group>
                </div>


            <div class="col-12">
              <div class="form-group">
                <label class="form-control-label" for="proyecto-documento">Justificación</label>
                <b-form-textarea id="textarea-rows" placeholder="Justificación" rows="4"></b-form-textarea>
              </div>
            </div>
            <div class="col-12 justify-content-center align-items-center">
              <div class="row">
                <div class="col-sm-3 col-3 align-items-center">
                  <b-form-group label="Cantidad" label-for="modalidad">
                    <input type="text" class="form-control" id="id" name="id" />
                  </b-form-group>
                </div>

                <div class="col-sm-3 col-3">
                  <b-form-group label="Valor Unitario" label-for="modalidad">
                    <input type="text" class="form-control" id="id" name="id" />
                  </b-form-group>
                </div>

                <div class="col-sm-3 col-3">
                  <b-form-group label="Valor Total" label-for="modalidad">
                    <input type="text" class="form-control" id="id" name="id" />
                  </b-form-group>
                </div>

                <div class="col-md-3 col-3">
                  <b-form-group label="Entidad Financiadora" label-for="entidad">
                    <b-form-select :options="entidads"
                      text-field="entidad"
                      value-field="id"
                      id="entidad"
                    ></b-form-select>
                  </b-form-group>
                </div>
              </div>
            </div>
            <div class="col-12 justify-content-center align-items-center">
              <div class="row">
                <!--<div class="col-sm-4 col-4">
              <b-form-radio
                aria-orientation="horizontal"
                name="some-radios"
                value="A"
              >Contrapartida En Especie</b-form-radio>
              <b-form-radio aria-orientation="horizontal" name="some-radios" value="B">En Efectivo</b-form-radio>
                </div>-->
                <div>
                  <b-form-radio-group id="radio-group-2" name="radio-sub-component">
                    <b-form-radio value="first">Contrapartida En Especie</b-form-radio>
                    <b-form-radio value="second">En Efectivo</b-form-radio>
                    <!--<b-form-radio value="third" disabled>This one is Disabled</b-form-radio>
                    <b-form-radio :value="{ fourth: 4 }">This is the 4th radio</b-form-radio>-->
                  </b-form-radio-group>
                </div>
              </div>
            </div>
          </div>
        </b-card>
        <hr />
      </div>
      <div class="col-12 text-left">
        <button type="submit" id="save-entity" class="btn btn-primary float-right">
          <font-awesome-icon :icon="['fas', 'save']"></font-awesome-icon>&nbsp;
          <span>Nuevo Rubro</span>
        </button>
        <button
          type="submit"
          id="save-entity"
          class="btn btn-primary float-right"
          @click="nuevo_presupuesto()"
        >
          <font-awesome-icon :icon="['fas', 'plus']"></font-awesome-icon>&nbsp;
          <span></span>
        </button>
      </div>
      <br />
      <br />
      <label class="form-control-label float-right" for="proyecto-documento">
        Total.&nbsp;&nbsp;&nbsp;&nbsp;
        </label>
      <div class="col-12 text-left row float-right"></div>
    </div>
  </div>
</template>

<script lang="ts">

import { Component, Inject, Vue } from 'vue-property-decorator';
import MenuLateral from '@/components/propuesta/menu_lateral.vue';
import AlertService from '@/shared/alert/alert.service';

import { IPresupuestoValor, PresupuestoValor } from '@/shared/model/presupuesto-valor.model';
import PresupuestoValorService from '@/entities/presupuesto-valor/presupuesto-valor.service';
import { IEntidad, Entidad } from '@/shared/model/entidad.model';
import EntidadService from '@/entities/entidad/entidad.service';
import { IRubro, Rubro } from '@/shared/model/rubro.model';
import RubroService from '@/entities/rubro/rubro.service';
import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
import ProyectoService from '@/entities/proyecto/proyecto.service';

const validations: any = {};



@Component({
  components: { MenuLateral }
})
export default class Presupuesto extends Vue {

  @Inject('presupuestoValorService') private presupuestoValorService: () => PresupuestoValorService;
  @Inject('entidadService') private entidadService: () => EntidadService;
  @Inject('rubroService') private rubroService: () => RubroService;
  @Inject('proyectoService') private proyectoService: () => ProyectoService;
   
  
  @Inject('alertService') private alertService: () => AlertService;


  presupuestos = [{ mensaje: 'mundo' }];
  nuevo_presupuesto() {
    //contar++;
    //console.log(contar);
    //document.getElementById('mostrar').innerHTML = contar;
    this.presupuestos.push({
      mensaje: 'mundo'
    });
  }


    public entidads: IEntidad[] = [];
    public rubros: IRubro[] = [];
    public presupuestoValors: IPresupuestoValor[] =[];
    //public elemProy: ElementoProyecto;
    public proyecto: IProyecto = new Proyecto();
    public proyId: any = null;
    public modalidadId: number = 0;

    public entidad: number = null;
    public rubro: number = null;

    public isSaving = false;


        beforeRouteEnter(to, from, next) {
            next(vm => {
              
                    vm.initRelationships();
            });
        }

        async initRelationships() {
           try {


             this.proyId = parseInt(this.$route.params.proyectoId);

              
                //this.proyecto = await this.proyectoService().find(this.proyId);

                //this.modalidadId = this.proyecto.proyectoModalidadId;   

              console.log(this.proyId);
            //Obtenienedo las entidades
            this.entidadService()
                .retrieve()
                .then(res => {
                    this.entidads = res.data;

            console.log("inicializando entidades");
            });

             //Obtenienedo las entidades
            this.rubroService()
                .retrieve()
                .then(res => {
                    this.rubros = res.data;

            console.log("inicializando rubros");
            });

            //recuperar los presupuestos enviando un idProyecto (api)
            //retrievePresupuetoProyecto
            this.presupuestoValorService()
                .retrievePresupuetoProyecto(this.proyId)
                .then(res=> {

                    this.presupuestoValors = res.data;
                    console.log(this.proyId);
                })

                 //Obteniendo las lineas de investigacion
            this.entidadService()
                .retrieve()
                .then(res => {
                    this.entidads = res.data;
                });


            }
            catch(e){ 
              console.log("error al recuperar la informacion de presupuesto ");
            }

            
            
        }



}
</script>

<style scoped>
</style>
