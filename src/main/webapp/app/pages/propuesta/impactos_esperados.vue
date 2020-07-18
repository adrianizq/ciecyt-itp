<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral></menu-lateral>
    </div>
    <div class="col-sm-8">
      <div class="row">
        <div class="col-12">
          <div class="form-group">
            <label
              class="form-control-label"
              for="proyecto-documento"
            >Impactos Esperados</label>
            <input type="text" class="form-control" name="titulo" id="proyecto-documento" />
          </div>
        </div>
        <div class="col-12">
          <div class="form-group">
            <label class="form-control-label" for="proyecto-nombre">Impacto</label>
            <input type="text" class="form-control" name="titulo" id="proyecto-nombre" />
          </div>
        </div>
        <div class="col-12">
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
          <button type="submit" id="save-entity" class="btn btn-primary">
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;
            <span >Nuevo Impacto</span>
          </button>
        </div>
      </div>
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
export default class Impactos_esperados extends Vue {}
</script>

<style scoped>
</style>
