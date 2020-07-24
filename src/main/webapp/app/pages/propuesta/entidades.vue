<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral></menu-lateral>
    </div>
    <div class="col-sm-8">
      <div :key="key" v-for="(item, key) in entidades">
        <b-card>
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
            <div class="col-3">
              <div class="form-group">
                <!-- <label class="form-control-label" for="proyecto-nombre">Nombres</label>-->
                <b-form-group label="Entidad " label-for="usuario">
                  <b-form-select
                    text-field="nombresApellidos"
                    value-field="id"
                    id="proyecto-asesorId"
                  ></b-form-select>
                </b-form-group>
              </div>
            </div>
            <div class="col-3">
              <div class="form-group">
                <label class="form-control-label" for="proyecto-apellido">Valor</label>
                <input type="text" class="form-control" name="titulo" id="proyecto-apellido" />
              </div>
            </div>
            <div class="col-12">
              <b-form-radio-group id="radio-group-2" name="radio-sub-component">
                Estado &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <b-form-radio value="first">Aprobada</b-form-radio>
                <b-form-radio value="second">En Trámite</b-form-radio>
                <!--<b-form-radio value="third" disabled>This one is Disabled</b-form-radio>
                <b-form-radio :value="{ fourth: 4 }">This is the 4th radio</b-form-radio>-->
              </b-form-radio-group>
            </div>
            
          </div>
          <hr>
        </b-card>
        <hr>
      </div>
      <button type="submit" id="save-entity" class="btn btn-primary float-right" >
              <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;
              <span>Nueva Entidad</span>
            </button>
            <button type="submit" id="save-entity" class="btn btn-primary float-right" @click="nuevo_entidad()">
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
export default class Entidades extends Vue {
  entidades = [{ mensaje: 'mundo' }];
  nuevo_entidad() {
    //contar++;
    this.entidades.push({
      mensaje: 'mundo'
    });
  }
}
</script>

<style scoped>
</style>
