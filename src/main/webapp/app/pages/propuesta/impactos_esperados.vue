<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral></menu-lateral>
    </div>
    <div class="col-sm-8">
      <div :key="key" v-for="(item, key) in impactos">
        <b-card>
          <div class="row">
            <div class="col-12"></div>
            <div class="col-12">
              <div class="form-group">
                <label class="form-control-label" for="proyecto-nombre">Impacto</label>
                <label class="float-right" id="contar">#{{key+1}}</label>

                <input type="text" class="form-control" name="titulo" id="proyecto-nombre" />
              </div>
            </div>
            <div class="col-3">
              <div class="form-group">
                <label class="form-control-label" for="proyecto-apellido">Plazo</label>
                <input type="text" class="form-control" name="titulo" id="proyecto-apellido" />
              </div>
            </div>
            <div class="col-12">
              <div class="form-group">
                <label class="form-control-label" for="proyecto-apellido">Indicador</label>
                <input type="text" class="form-control" name="titulo" id="proyecto-apellido" />
              </div>
            </div>
            <div class="col-12">
              <div class="form-group">
                <label class="form-control-label" for="proyecto-apellido">Supuestos</label>
                <input type="text" class="form-control" name="titulo" id="proyecto-apellido" />
              </div>
            </div>
          </div>
        </b-card>
        <hr>
      </div>
      <button type="submit" id="save-entity" class="btn btn-primary float-right">
        <font-awesome-icon :icon="['fas', 'save']"></font-awesome-icon>&nbsp;
        <span>Nuevo Impacto</span>
      </button>
      <button
        type="submit"
        id="save-entity"
        class="btn btn-primary float-right"
        @click="nuevo_impacto()"
      >
        <font-awesome-icon :icon="['fas', 'plus']"></font-awesome-icon>&nbsp;
        <span></span>
      </button>
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
export default class Impactos_esperados extends Vue {
  impactos = [{ mensaje: 'mundo' }];
  nuevo_impacto() {
    //contar++;
    this.impactos.push({
      mensaje: 'mundo'
    });
  }
}
</script>

<style scoped>
</style>
