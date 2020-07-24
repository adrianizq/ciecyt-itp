<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral></menu-lateral>
    </div>
    <div class="col-sm-8">
      
        <div class="row">
          <div :key="key" v-for="(item, key) in integrantes">
            <b-card>
              <div class="row">
                <div class="col-12">
                  <div class="form-group">
                    <b-form-radio-group id="radio-group-2" name="radio-sub-component">
                      <b-form-radio value="first">Documento</b-form-radio>
                      <b-form-radio value="second">Codigo ITP</b-form-radio>
                      <label class="float-right" id="contar">#{{key+1}}</label>
                      <!--<b-form-radio value="third" disabled>This one is Disabled</b-form-radio>
                      <b-form-radio :value="{ fourth: 4 }">This is the 4th radio</b-form-radio>-->
                    </b-form-radio-group>
                    
                  </div>
                </div>
                <div class="col-12">
                  <div class="form-group">
                    <input type="text" class="form-control" name="titulo" id="proyecto-documento" />
                  </div>
                </div>
                <div class="col-12">
                  <div class="form-group">
                    <label class="form-control-label" for="proyecto-nombre">Nombres</label>
                    <input type="text" class="form-control" name="titulo" id="proyecto-nombre" />
                  </div>
                </div>
                <div class="col-12">
                  <div class="form-group">
                    <label class="form-control-label" for="proyecto-apellido">Apellidos</label>
                    <input type="text" class="form-control" name="titulo" id="proyecto-apellido" />
                  </div>
                </div>
              </div>
            </b-card>
            <hr>
          </div>
          
        </div>
        <button
            type="submit"
            id="save-entity"
            class="btn btn-primary float-right"
          >
            <font-awesome-icon :icon="['fas', 'save']"></font-awesome-icon>&nbsp;
            <span>Nuevo Integrante</span>
          </button>
        <button
            type="submit"
            id="save-entity"
            class="btn btn-primary float-right"
            @click="nuevo_integrante()"
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
export default class Integrantes extends Vue {
  resultado = '';
  integrantes = [{ mensaje: 'mundo' }];
  nuevo_integrante() {
    //contar++;
    //console.log(contar);
    //document.getElementById('mostrar').innerHTML = contar;
    this.integrantes.push({
      mensaje: 'mundo'
    });
  }
}
</script>

<style scoped>
</style>
