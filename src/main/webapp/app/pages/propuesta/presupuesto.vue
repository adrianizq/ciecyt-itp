<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral></menu-lateral>
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
                  <b-form-group label="Entidad Financiadora" label-for="usuario">
                    <b-form-select
                      text-field="nombresApellidos"
                      value-field="id"
                      id="proyecto-asesorId"
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
import Component from 'vue-class-component';
import { Vue } from 'vue-property-decorator';
import MenuLateral from '@/components/propuesta/menu_lateral.vue';

@Component({
  components: { MenuLateral }
})
export default class Presupuesto extends Vue {
  presupuestos = [{ mensaje: 'mundo' }];
  nuevo_presupuesto() {
    //contar++;
    //console.log(contar);
    //document.getElementById('mostrar').innerHTML = contar;
    this.presupuestos.push({
      mensaje: 'mundo'
    });
  }
}
</script>

<style scoped>
</style>
