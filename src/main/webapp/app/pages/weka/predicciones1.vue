<template>
  <div>
    Predicciones con base en el Tipo de Investigación - Método Naive Bayes
    <br />

    

    <b-tabs content-class="mt-3" >
    <div :key="key" v-for="(item, key) in investigacionTips">
      <b-tab :title="item.investigacionTipo" active>
       <div v-html="prediccions[key]"></div>
      </b-tab>
      
    </div>
    <b-tab title="Resumen"  active>
      <div v-html="prediccions[investigacionTips.length]"></div>
      </b-tab>
     
    </b-tabs>

   

    <div></div>
  </div>
</template>

<script lang="ts">
import { Component, Inject, Vue } from 'vue-property-decorator';
import AlertService from '@/shared/alert/alert.service';

//programa, modalidad, facultad, tipo

//import MenuLateral from '@/components/propuesta/menu_lateral.vue';
import PrediccionesService from '@/entities/predicciones/predicciones.service';
import { IInvestigacionTipo, InvestigacionTipo } from '@/shared/model/investigacion-tipo.model';
import InvestigacionTipoService from '@/entities/investigacion-tipo/investigacion-tipo.service';
//import { IPrograma, Programa } from '@/shared/model/programa.model';
//import ProgramaService from '@/entities/programa/programa.service';
//import { IModalidad } from '@/shared/model/modalidad.model';
//import FacultadService from '@/entities/facultad/facultad.service';
//import { IFacultad } from '@/shared/model/facultad.model';

const validations: any = {};

@Component({
  validations,
})
export default class Predicciones1 extends Vue {
  @Inject('prediccionesService') private prediccionesService: () => PrediccionesService;
  @Inject('investigacionTipoService') private investigacionTipoService: () => InvestigacionTipoService;
  //@Inject('lineaInvestigacionService') private lineaInvestigacionService: () => LineaInvestigacionService;

  //@Inject('investigacionTipoService') private investigacionTipoService: () => InvestigacionTipoService;

  @Inject('alertService') private alertService: () => AlertService;

  //public modalidads: IModalidad[] = [];
  //public facultades: IFacultad[] = [];
  //public programs: IPrograma[] = [];
  public investigacionTips: InvestigacionTipo[] = [];

  //public linea_investigacion: number = null;

  // public investTipos: String[] = [];

  public prediccions: String[] = [];

  beforeRouteEnter(to, from, next) {
    next(async vm => {
      vm.initRelationships();
    });
  }
  async initRelationships() {
    try {
      await this.prediccionesService()
        .retrieve()
        .then(res => {
          this.prediccions = res.data;
          for(var i=0; i<this.prediccions.length; i++){
            this.prediccions[i] = JSON.stringify(this.prediccions[i]);
            this.prediccions[i] = this.prediccions[i].replace(/\\n/g, '<br />');
          };
          
        });

      await this.investigacionTipoService()
        .retrieve()
        .then(res => {
          this.investigacionTips = res.data;
          
        });
    } catch (e) {}
  }
}
</script>

<style scoped>