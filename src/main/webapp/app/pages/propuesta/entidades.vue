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
            >Entidades que financian el proyecto</label>
            <input type="text" class="form-control" name="titulo" id="proyecto-documento" />
          </div>
        </div>
        <div class="col-12">
          <div class="form-group">
           <!-- <label class="form-control-label" for="proyecto-nombre">Nombres</label>-->
            <b-dropdown
              split
              split-variant="outline-primary"
              variant="primary"
              text="Entidad"
              class="col-6"
            >
              <b-dropdown-item href="#">Action</b-dropdown-item>
              <b-dropdown-item href="#">Another action</b-dropdown-item>
              <b-dropdown-item href="#">Something else here...</b-dropdown-item>
            </b-dropdown>
          </div>
        </div>
        <div class="col-12">
          <div class="form-group">
            <label class="form-control-label" for="proyecto-apellido">Valor</label>
            <input type="text" class="form-control" name="titulo" id="proyecto-apellido" />
          </div>
          <button type="submit" id="save-entity" class="btn btn-primary">
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;
            <span >Nueva Entidad</span>
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
export default class Entidades extends Vue {}
</script>

<style scoped>
</style>
