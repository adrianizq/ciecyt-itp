<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral :proyectoId='$route.params.proyectoId'></menu-lateral>
    </div>
    <div class="col-sm-8">
      <div class="row">
        <div class="col-12">
          <div class="form-group">
            <table class="float-center">
              <tr>
                <th class="col-4">Titulo</th>
                <th class="col-2">Estado</th>
                <th class="col-2">Tiempo</th>
                <th class="col-2">Faltante</th>
                <th class="col-2">Retraso</th>
              </tr>
            </table>
          </div>
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
export default class Elementos extends Vue {}
</script>

<style scoped>
</style>
