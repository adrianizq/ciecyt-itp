<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral></menu-lateral>
    </div>
    <div class="col-sm-8">
      <div :key="key" v-for="(item, key) in cronogramas">
        <b-card>
          <div class="row">
            <div class="col-12">
              <div class="form-group">
                <label
                  class="form-control-label"
                  for="proyecto-documento"
                >Cronograma de Actividades de la Propuesta</label>
                <input type="text" class="form-control" name="titulo" id="proyecto-documento" />
              </div>
            </div>
            <div class="col-12">
              <div class="form-group">
                <label class="form-control-label" for="proyecto-nombre">Actividad</label>
                <input type="text" class="form-control" name="titulo" id="proyecto-nombre" />
              </div>
            </div>
            <div class="col-3">
              <div class="form-group">
                <label class="form-control-label" for="proyecto-apellido">Duración</label>
                <input type="text" class="form-control" name="titulo" id="proyecto-apellido" />
              </div>
            </div>
            <div class="col-4">
              <div class="form-group">
                <label class="form-control-label" for="proyecto-apellido">Fecha</label>

                <label for="datepicker-sm">Fecha de Inicio</label>
                <b-form-datepicker id="datepicker-sm" size="sm-6" local="en"></b-form-datepicker>

                <!--<div>
              <b-calendar v-model="value" :min="min" :max="max" locale="en"></b-calendar>
                </div>-->
              </div>
            </div>
            <div class="col-4">
              <label for="datepicker-lg">Fecha de Finalización</label>
              <b-form-datepicker id="datepicker-sm" size="sm-6" local="ESP"></b-form-datepicker>
            </div>
          </div>
        </b-card>
        <hr />
      </div>

      <button type="submit" id="save-entity" class="btn btn-primary float-right">
        <font-awesome-icon :icon="['fas', 'save']"></font-awesome-icon>&nbsp;
        <span>Guardar</span>
      </button>
      <button
        type="submit"
        id="save-entity"
        class="btn btn-primary float-right"
        @click="nuevo_cronograma()"
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
//import { CalendarPlugin } from 'bootstrap-vue';
@Component({
  components: { MenuLateral }
})
export default class Cronograma extends Vue {
  cronogramas = [{ mensaje: 'mundo' }];
  nuevo_cronograma() {
    //contar++;
    this.cronogramas.push({
      mensaje: 'mundo'
    });
  }
  data() {
    const now = new Date();
    const today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    // 15th two months prior
    const minDate = new Date(today);
    minDate.setMonth(minDate.getMonth() - 2);
    minDate.setDate(15);
    // 15th in two months
    const maxDate = new Date(today);
    maxDate.setMonth(maxDate.getMonth() + 2);
    maxDate.setDate(15);

    return {
      value: '',
      min: minDate,
      max: maxDate
    };
  }
}
</script>

<style scoped>
</style>
