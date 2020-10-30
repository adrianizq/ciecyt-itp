<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral :proyectoId="$route.params.proyectoId"></menu-lateral>
    </div>
    <div class="col-sm-8">
      <form @submit.prevent="save()">
        <div class="row">
          <div class="col-12">
            <!--
          <div class="form-group">
           <label class="form-control-label" v-text="$t('global.field.id')" for="proyecto-titulo">Id</label>
             <input disabled="true"
                type="text"
                class="form-control"
                name="id"
                id="proyecto-id"
                v-model="proyecto.id"             
                />
          </div>

           <div class="form-group">
             <label class="form-control-label" v-text="$t('ciecytApp.proyecto.titulo')" for="proyecto-titulo">Id</label>

              <input disabled="true"
                type="text"
                class="form-control"
                name="titulo"
                id="proyecto-titulo"
                v-model="proyecto.titulo"             
               />
           </div>
            -->

            

            <div class="form-group">
              <label class="form-control-label" for="proyecto-titulo">
               <h2>Enviar la Propuesta</h2><br /><br /><br />
                Se va a enviar la Propuesta al CIECYT, para ser revisada por un Jurado de Viabilidad. <br />
                Para realizar esta operación, debe haber diligenciado correctamente los datos de su propuesta
                <br /> <strong>{{ proyecto.titulo }} </strong>

                <br />Si está de acuerdo marque la opcion
                <strong>Enviar para Vibilidad </strong>
                y de click en el boton <strong>Enviar </strong></label
              >

              <b-form-checkbox type="checkbox" class="form-control" name="dias" id="informacion-pasantia-lunes" v-model="proyecto.enviado">
                Enviar para Viabilidad
              </b-form-checkbox>
            </div>
          </div>
        </div>

        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>

          <button type="submit" id="save-entity" class="btn btn-primary">
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Enviar</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Inject, Vue } from 'vue-property-decorator';

import MenuLateral from '@/components/propuesta/menu_lateral.vue';
import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
import ProyectoService from '@/entities/proyecto/proyecto.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  components: { MenuLateral },
})
export default class EnviarPropuesta extends Vue {
  @Inject('proyectoService') private proyectoService: () => ProyectoService;
  @Inject('alertService') private alertService: () => AlertService;

  public proyecto: IProyecto = new Proyecto();
  public proyId: string = null;
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.proyectoId) {
        vm.retrieveProyecto(to.params.proyectoId);
      }
      vm.initRelationships();
    });
  }

  getNow() {
    const today = new Date();
    const date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
    return date;
  }

  public save(): void {
    this.isSaving = true;
    //calcular la fecha actual para guardarla en
    //proyecto.fechaEnvioPropuesta

    this.proyecto.fechaEnvioPropuesta = new Date();
    console.log(this.proyecto.fechaEnvioPropuesta);
    //this.$v.$touch();
    //if (this.$v.$invalid) {
    //this.submitStatus = 'ERROR';
    //} else {

    if (this.proyecto.id) {
      this.proyectoService()
        .update(this.proyecto)
        .then(param => {
          this.isSaving = false;
          //this.$router.push({ name: 'PropuestaIntegrantesView', params: { proyectoId: this.proyecto.id.toString() } });
          const message = this.$t('ciecytApp.proyecto.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.proyectoService()
        .create(this.proyecto)
        .then(param => {
          this.isSaving = false;

          this.proyId = String(param.id);

          // this.$router.push({ name: 'PropuestaIntegrantesView', params: { proyectoId: this.proyId } });

          const message = 'Se ha creado un nuevo proyecto';
          this.alertService().showAlert(message, 'success');
        });
    }
    //this.submitStatus = 'PENDING';
    //setTimeout(() => {
    //  this.submitStatus = 'OK';
    //}, 500);
    //}
    // console.log(this.submitStatus);
  }

  retrieveProyecto() {
    this.proyectoService()
      .find(parseInt(this.$route.params.proyectoId))
      .then(res => {
        this.proyecto = res;
      });
  }

  initRelationships() {
    this.proyId = this.$route.params.proyectoId;
  }
}
</script>

<style scoped>
</style>
