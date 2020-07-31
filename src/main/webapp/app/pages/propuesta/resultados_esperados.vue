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
                <input type="text" class="form-control" name="titulo" v-model="resultado" />
              </div>
            </div>

            <div class="col-12">
              <div class="form-group">
                <label class="form-control-label">Indicador</label>
                <input type="text" class="form-control" name="titulo" />
              </div>
            </div>
            <div class="col-12">
              <div class="form-group">
                <label class="form-control-label">Beneficiario</label>
                <input type="text" class="form-control" name="titulo" />
              </div>
            </div>
          </div>
        </b-card>
        <hr />
      </div>
      <div class="col-12 text-left">
        <button type="submit" id="save-entity" class="btn btn-primary float-right">
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
import Component from 'vue-class-component';
import { Vue } from 'vue-property-decorator';
import MenuLateral from '@/components/propuesta/menu_lateral.vue';
var contar = 1;
@Component({
  components: { MenuLateral }
})
export default class Resultados_esperados extends Vue {
  resultado = '';
  resultados = [{ mensaje: 'mundo' }];
  nuevo_resultado() {
    contar++;
    console.log(contar);
    //document.getElementById('mostrar').innerHTML = contar;
    this.resultados.push({
      mensaje: 'mundo'
    });
  }
}
</script>

<style scoped>
</style>
