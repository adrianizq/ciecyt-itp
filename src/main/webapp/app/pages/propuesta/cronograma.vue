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
        <div class="col-12">
          <div class="form-group">
            <label class="form-control-label" for="proyecto-apellido">Duración</label>
            <input type="text" class="form-control" name="titulo" id="proyecto-apellido" />
          </div>
        </div>
        <div class="col-12">
          <div class="form-group">
            <label class="form-control-label" for="proyecto-apellido">Fecha</label>
            <div>
              <label for="datepicker-sm">Small date picker</label>
              <b-form-datepicker id="datepicker-sm" size="sm" local="en" class="mb-2"></b-form-datepicker>
              <label for="datepicker-lg">Large date picker</label>
              <b-form-datepicker id="datepicker-lg" size="lg" local="en"></b-form-datepicker>
            </div>
            <!--<div>
              <b-calendar v-model="value" :min="min" :max="max" locale="en"></b-calendar>
            </div>-->
          </div>
          <button type="submit" id="save-entity" class="btn btn-primary">
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;
            <span v-text="$t('entity.action.save')">Save</span>
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
//import { CalendarPlugin } from 'bootstrap-vue';
@Component({
  components: { MenuLateral }
})
export default class Cronograma extends Vue {
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
