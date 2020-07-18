<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral></menu-lateral>
    </div>
    <div class="col-sm-8">
      <div class="row">
        <div class="col-12">
          <div class="form-group">
            <label class="form-control-label" for="proyecto-documento">Descripción</label>
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

            <div class="col-sm-3 col-3">
              <!--<b-form-group label="Entidad Financiadora" label-for="modalidad">-->
                <b-dropdown
                  split
                  split-variant="outline-primary"
                  variant="primary"
                  text="Entidad Financiadora"
                  class="m-2"
                >
                  <b-dropdown-item href="#">Action</b-dropdown-item>
                  <b-dropdown-item href="#">Another action</b-dropdown-item>
                  <b-dropdown-item href="#">Something else here...</b-dropdown-item>
                </b-dropdown>
              </b-form-group>
            </div>
          </div>
        </div>
        <div class="col-12 text-left">
          <button type="submit" id="save-entity" class="btn btn-primary">
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;
            <span>Más Elementos</span>
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
export default class Presupuesto extends Vue {}
</script>

<style scoped>
</style>
