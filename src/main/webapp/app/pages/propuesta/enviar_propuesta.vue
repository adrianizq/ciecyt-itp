<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral :proyectoId="$route.params.proyectoId"></menu-lateral>
    </div>
    <!--<div class="col-sm-8"  v-if="!proyecto.enviado"> -->
    <div class="col-sm-8">
      <form @submit.prevent="save()">
        <div class="row">
          <div class="col-12">
            
            <div class="form-group">
              <label class="form-control-label" for="proyecto-titulo">
               <h2>Enviar la Propuesta</h2><br />

               <ul>
               <li>Título {{proyecto.titulo}} </li>
            <li v-for="l in integrants" v-bind:key="l">{{l.integranteProyectoRolesModalidadRol}}: {{l.integranteProyectoUserLogin}}</li>
             <ol></ol>
            
        </ul>
                Se va a enviar la Propuesta al CIECYT, para ser revisada por un Jurado de Viabilidad. <br />
                Para realizar esta operación, debe haber diligenciado correctamente los datos de su propuesta
                <br /> 

                <br />Si está de acuerdo marque la opcion
                <strong>Enviar para Vibilidad </strong>
                y de click en el boton <strong>Enviar </strong></label
              >

              <b-form-checkbox type="checkbox" 
              class="form-control" 
              name="terms"
              value="terms"
              id='terms'
              v-model='terms'
        
              >
                Enviar para Viabilidad
              </b-form-checkbox>
            </div>
          </div>

                    <!-------------------------DESCARGAR ----------------------->
                     <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.adjuntoProyectoFase.archivo')" for="adjunto-proyecto-fase-archivo">Archivo</label>
                        <div>
                           <div v-if="adjuntoProyectoFase.file" class="form-text text-danger clearfix">
                           <!--<div class="form-text text-danger clearfix">-->
                               <a class="pull-left" v-on:click="this.descargar" v-text="$t('entity.action.open')">open</a><br> 
                                <!--<a class="pull-left" v-on:click="this.descargar">open</a><br></br>-->
                                <span class="pull-left">{{adjuntoProyectoFase.archivoContentType}}, {{adjuntoProyectoFase.file}}, {{byteSize(adjuntoProyectoFase.file)}}</span>
                                <!--de cerrar-->
                                <!--<button type="button" v-on:click="adjuntoProyectoFase.file=null;adjuntoProyectoFase.archivoContentType=null;"
                                        class="btn btn-secondary btn-xs pull-right">
                                    <font-awesome-icon icon="times"></font-awesome-icon>
                                </button> -->
                            </div>
                            <!--  boton seleccionar archivo -->
                            <input type="file" ref="file_archivo" id="file_archivo" v-on:change="setFileData($event, adjuntoProyectoFase, 'archivo', false)" v-text="$t('entity.action.addblob')"/>
                        </div>
                        
                        <input type="hidden" class="form-control" name="archivo" id="adjunto-proyecto-fase-archivo"
                            :class="{'valid': !$v.adjuntoProyectoFase.archivo.$invalid, 'invalid': $v.adjuntoProyectoFase.archivo.$invalid }" v-model="$v.adjuntoProyectoFase.archivo.$model" />
                        <input type="hidden" class="form-control" name="archivoContentType" id="adjunto-proyecto-fase-archivoContentType"
                            v-model="adjuntoProyectoFase.archivoContentType" />
                            
                    </div>
          <!---------------------------->
        </div>

        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>

          <button type="submit" id="save-entity" class="btn btn-primary" :disabled='isDisabled'>
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Enviar</span>
          </button>
        </div>
      </form>
    </div>
   <!--<div class="col-sm-8"  v-if="proyecto.enviado">
     <h2>Enviar la Propuesta</h2><br />
    La Propuesta ya ha sido enviada para su revisión
    </div> -->
  </div> 
</template>

<script lang="ts">
import { Component, Inject, Vue } from 'vue-property-decorator';

import MenuLateral from '@/components/propuesta/menu_lateral.vue';
import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
import { IUser } from '@/shared/model/user.model';
import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import ProyectoService from '@/entities/proyecto/proyecto.service';
import AlertService from '@/shared/alert/alert.service';
import { IIntegranteProyecto } from '@/shared/model/integrante-proyecto.model';
import { IAdjuntoProyectoFase, AdjuntoProyectoFase } from '@/shared/model/adjunto-proyecto-fase.model';
import AdjuntoProyectoFaseService from '@/entities/adjunto-proyecto-fase/adjunto-proyecto-fase.service';

import { IFases, Fases } from '@/shared/model/fases.model';
import FasesService from '@/entities/fases/fases.service';
import { resolve4 } from 'dns';

const validations: any = {
  adjuntoProyectoFase: {
    nombreAdjunto: {},
    fechaCreacion: {},
    fechaModificacion: {},
    estadoAdjunto: {},
    adjuntoProyectoFase: {},
    nombreArchivoOriginal: {},
    archivo: {},
    fechaInicio: {},
    fechaFin: {},
  },
};

@Component({
  validations, components: { MenuLateral },
})

//export default class EnviarPropuesta extends Vue {
  export default class EnviarPropuesta extends mixins(JhiDataUtils){
  @Inject('proyectoService') private proyectoService: () => ProyectoService;
  @Inject('adjuntoProyectoFaseService') private adjuntoProyectoFaseService: () => AdjuntoProyectoFaseService;
  @Inject('fasesService') private fasesService: () => FasesService;
  @Inject('alertService') private alertService: () => AlertService;
  
  public adjuntoProyectoFase: IAdjuntoProyectoFase = new AdjuntoProyectoFase();

  public integrants:IIntegranteProyecto[]= [];
   public fase:IFases= new Fases();
   // public fases: IFases = new Fases();
   public faseId:number;
  public terms:Boolean=false;


  public proyecto: IProyecto = new Proyecto();
  public proyId: string = null;
  public isSaving = false;
  public modalidadId: any;
  

  descargar() {
    //console.log('se hizo clic');
    this.adjuntoProyectoFaseService().downloadFile(this.adjuntoProyectoFase.id);
  }

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.proyectoId) {
        //vm.retrieveProyecto(to.params.proyectoId);
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
 
   /*
   this.proyecto.fechaEnvioPropuesta = new Date();
    this.proyecto.enviado = true;
    
    if (this.proyecto.id) {
      this.proyectoService()
        .update(this.proyecto)
        .then(param => {
          this.isSaving = false;
          (<any>this).$router.go(0);
          const message = this.$t('ciecytApp.proyecto.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.proyectoService()
        .create(this.proyecto)
        .then(param => {
          this.isSaving = false;

          this.proyId = String(param.id);

          (<any>this).$router.go(0);

          const message = 'Se ha creado un nuevo proyecto';
          this.alertService().showAlert(message, 'success');
        });
    }
*/
    this.adjuntoProyectoFase.proyectoFaseProyectoId = this.proyecto.id;
    this.adjuntoProyectoFase.adjuntoProyectoFaseFaseId = this.faseId;
    
    if(this.adjuntoProyectoFase.id) {
     console.log("Existe el adjunto");
      this.adjuntoProyectoFaseService()
        .update(this.adjuntoProyectoFase)
        .then(param => {
            (<any>this).$router.go(0);
          const message = this.$t('ciecytApp.adjuntoProyectoFase.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      console.log("NO Existe el adjunto");
      this.adjuntoProyectoFaseService()
        .create(this.adjuntoProyectoFase)
        .then(param => {
          this.isSaving = false;
           (<any>this).$router.go(0);
          const message = this.$t('ciecytApp.adjuntoProyectoFase.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
    //fin el guardar el adjunto

    

    
  }

  
  
  async initRelationships() {
    this.proyId = this.$route.params.proyectoId;

     let res = await this.proyectoService()
    .findProyectoIntegrantes(parseInt(this.$route.params.proyectoId))
      //.find(parseInt(this.$route.params.proyectoId))
      .then(res => {
        this.proyecto = res.data;
        this.modalidadId = this.proyecto.proyectoModalidadId;
        this.integrants = this.proyecto.listaIntegrantesProyecto;
        
        
      });

      res=  await this.fasesService().retrieveFaseModalidad("Propuesta", this.modalidadId)
      .then(res => {
        this.fase = res;  
        
      });


      res=  await this.adjuntoProyectoFaseService()
      .findAdjuntoProyectoFase(this.proyId,  this.fase.id)
      .then(res => {
        this.adjuntoProyectoFase = res;
      });
      
  }

  get isDisabled(){
    	return !this.terms;
    }
}
</script>

<style scoped>
</style>
