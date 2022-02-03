<template>
  <div>
    Predicciones con base en el Tipo de Investigación
    <br />

    

    <b-tabs content-class="mt-3" >
    <div :key="key" v-for="(item, key) in investigacionTips">
      <b-tab :title="item.investigacionTipo" active>
       <div v-html="prediccions[key]"></div>
      </b-tab>
      
    </div>
    <b-tab title="Summary"  active>
      <div v-html="prediccions[investigacionTips.length]"></div>
      </b-tab>
     
    </b-tabs>

   

    <div></div>
  </div>
</template>

<script lang="ts">
import { Component, Inject, Vue } from 'vue-property-decorator';
import AlertService from '@/shared/alert/alert.service';

//import HtmlCodeViewer from 'vue-html-code-viewer'
//Vue.use(HtmlCodeViewer)

//import MenuLateral from '@/components/propuesta/menu_lateral.vue';
import PrediccionesService from '@/entities/predicciones/predicciones.service';
import { IInvestigacionTipo, InvestigacionTipo } from '@/shared/model/investigacion-tipo.model';
import InvestigacionTipoService from '@/entities/investigacion-tipo/investigacion-tipo.service';
//import { IModalidad } from '@/shared/model/modalidad.model';
//import FacultadService from '@/entities/facultad/facultad.service';
//import { IFacultad } from '@/shared/model/facultad.model';
//import LineaInvestigacionService from '@/entities/linea-investigacion/linea-investigacion.service';
//import { ILineaInvestigacion } from '@/shared/model/linea-investigacion.model';
//import UsuarioService from '@/entities/usuario/usuario.service';
//import { IUsuario } from '@/shared/model/usuario.model';
//import { IUser } from '@/shared/model/user.model';

//import { id } from 'date-fns/locale';

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
  //public isSaving = false;

  //public submitStatus: string = 'PENDING';
  //console.log("entrando a initrelation");

  beforeRouteEnter(to, from, next) {
    next(async vm => {
      vm.initRelationships();
    });
  }
  ////////////////////////////////////////
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

  //////////////////////////////////////
}
</script>

<style scoped>