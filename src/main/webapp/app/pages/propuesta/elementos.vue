<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral :proyectoId='$route.params.proyectoId'></menu-lateral>
    </div>
    <div class="col-sm-8">
      <div class="col-12">
        <div class="form-group">
          <label class="form-control-label" for="proyecto-documento">Resúmen Ejecutivo</label>
          <b-form-textarea id="textarea-rows" placeholder="Resúmen Ejecutivo" rows="6"></b-form-textarea>
        </div>
      </div>

      <div class="col-12">
        <div class="form-group">
          <label class="form-control-label" for="proyecto-documento">Planteamiento del Problema</label>
          <b-form-textarea id="textarea-rows" placeholder="Planteamiento del Problema" rows="6"></b-form-textarea>
        </div>
      </div>

      <div class="col-12 text-left">
        <button type="submit" id="save-entity" class="btn btn-primary">
          <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;
          <span class="left">Más Elementos</span>
        </button>
        <button type="button" id="cancel-save" class="btn btn-primary" v-on:click="save()">
          <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;
          <span v-text="$t('entity.action.save')">Guardar</span>
        </button>
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
export default class Elementos extends Vue {}
</script>

<style scoped>
</style>
