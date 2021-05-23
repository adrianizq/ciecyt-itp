<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral :proyectoId="$route.params.proyectoId"></menu-lateral>
    </div>
    
   <!-- <div class="col-sm-8"  v-if="!proyecto.preEnviado"> -->
    <div class="col-sm-8">
      <form @submit.prevent="save()">
        <div class="row">
          <div class="col-12">
            
            <div class="form-group">
              <label class="form-control-label" for="proyecto-titulo"></label>
               <h2>Retroalimentación</h2>

              <!-- <ul>
               <li>Título {{proyecto.titulo}} </li>
                 <li v-for="l in integrants" v-bind:key="l">{{l.integranteProyectoRolesModalidadRol}}: {{l.integranteProyectoUserLogin}}</li>
                 <ol></ol>
            
              </ul> -->
             </div>
          </div>

         <div class="col-12">    <!-------------------------correcciones asesor ------->
                    <b-card  
                      border-variant="primary"
                      header-bg-variant="light"
                      body-bg-variant="light"
                     header-text-variant="info">  
                
                 
                       <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.adjuntoRetroalimentacion.correcionesAsesor')" for="adjunto-proyecto-fase-archivo">Correcciones del Asesor</label>
                        
                        <div>
                            <div v-if="adjuntoAsesorRetroalimentacion.id"  class="form-text text-danger clearfix">
                               <a class="pull-left" v-on:click="this.descargarAsesorRetro" v-text="$t('entity.action.open')">open </a>
                                <span class="pull-left">{{adjuntoAsesorRetroalimentacion.nombreArchivoOriginal }} <br /> {{adjuntoAsesorRetroalimentacion.archivoContentType}}, {{byteSize(adjuntoAsesorRetroalimentacion.file)}}</span>
                                
                            </div> 
                 
                        </div>
                        
                    </div> 
                   </b-card>
                  <hr></hr>
                </div> 


         <div class="col-12">   <!-------------------------correcciones ------->
                    <b-card  
                      border-variant="primary"
                      header-bg-variant="light"
                      body-bg-variant="light"
                     header-text-variant="info">  
                
                  
                       <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.adjuntoRetroalimentacion.correcionesPropuesta')" for="adjunto-proyecto-fase-archivo">Archivo</label>
                        
                        <div>
                            <div v-if="adjuntoRetroalimentacion.id"  class="form-text text-danger clearfix">
                               <a class="pull-left" v-on:click="this.descargarRetro" v-text="$t('entity.action.open')">open </a>
                                <span class="pull-left">{{adjuntoRetroalimentacion.nombreArchivoOriginal }} <br /> {{adjuntoRetroalimentacion.archivoContentType}}, {{byteSize(adjuntoRetroalimentacion.file)}}</span>
                                
                            </div> 
                 
                        </div>
                        
                    </div> 
                   </b-card>
                  <hr></hr>
                </div> 
               

        </div>

        <div>
        <!--
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>

          <button type="submit" id="save-entity" class="btn btn-primary" :disabled='isDisabled'>
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Enviar</span>
          </button> -->
        </div>
      </form>
    </div> 
   
  </div>
</template>

<script lang="ts">
import { Component, Inject, Vue } from 'vue-property-decorator';
import { mixins } from 'vue-class-component';

import MenuLateral from '@/components/propuesta/menu_lateral.vue';
import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
import { IUser } from '@/shared/model/user.model';

import ProyectoService from '@/entities/proyecto/proyecto.service';
import AlertService from '@/shared/alert/alert.service';
import { IIntegranteProyecto } from '@/shared/model/integrante-proyecto.model';

import { IAdjuntoProyectoFase, AdjuntoProyectoFase } from '@/shared/model/adjunto-proyecto-fase.model';
import AdjuntoProyectoFaseService from '@/entities/adjunto-proyecto-fase/adjunto-proyecto-fase.service';

import FasesService from '@/entities/fases/fases.service';
import { IFases, Fases } from '@/shared/model/fases.model';

import { IAdjuntoRetroalimentacion, AdjuntoRetroalimentacion } from '@/shared/model/adjunto-retroalimentacion.model';
import AdjuntoRetroalimentacionService from '@/entities/adjunto-retroalimentacion/adjunto-retroalimentacion.service';

import JhiDataUtils from '@/shared/data/data-utils.service';

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
    file: {},
  },
   adjuntoRetroalimentacion: {
    nombreAdjunto: {},
    fechaCreacion: {},
    fechaModificacion: {},
    estadoAdjunto: {},
    adjuntoRetroalimentacion: {},
    nombreArchivoOriginal: {},
    archivo: {},
    fechaInicio: {},
    fechaFin: {},
    file: {},
  },
    };


@Component({
  components: { MenuLateral },
  
})
export default class Retroalimentacion extends mixins(JhiDataUtils){
  @Inject('proyectoService') private proyectoService: () => ProyectoService;
  @Inject('adjuntoProyectoFaseService') private adjuntoProyectoFaseService: () => AdjuntoProyectoFaseService;
  @Inject('adjuntoRetroalimentacionService') private adjuntoRetroalimentacionService: () => AdjuntoRetroalimentacionService;
   @Inject('fasesService') private fasesService: () => FasesService;

  @Inject('alertService') private alertService: () => AlertService;

  public integrants:IIntegranteProyecto[]= [];
  public terms:Boolean=false;
  
  public proyecto: IProyecto = new Proyecto();
  public proyId: string = null;
  public isSaving = false;

    //public adjuntoProyectoFass:IAdjuntoProyectoFase[] =[];
    //public adjuntoProyectoFase: IAdjuntoProyectoFase = new AdjuntoProyectoFase();

    public adjuntoRetroalimentacions:IAdjuntoRetroalimentacion[] =[];
    public adjuntoRetroalimentacion: IAdjuntoRetroalimentacion = new AdjuntoRetroalimentacion();
    public adjuntoAsesorRetroalimentacions:IAdjuntoRetroalimentacion[] =[];
    public adjuntoAsesorRetroalimentacion: IAdjuntoRetroalimentacion = new AdjuntoRetroalimentacion();
    
    public fase: IFases = new Fases();
    public  authority: any="ROLE_VIABILIDAD";
    public nombreFase: any = "Propuesta";
    public  authorityAsesor: any="ROLE_ASESOR";
  //public fasePropuesta: IFases = new Fases();
  //public faseProyecto: IFases = new Fases();

    //public  authorityJurado: any="ROLE_JURADO";
     //public  authorityViabilidad: any="ROLE_VIABILIDAD";
     //public nombreFasePropuesta: any = "Propuesta";
    //public nombreFaseProyecto: any = "Proyecto";

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.proyectoId) {
        vm.retrieveProyecto(to.params.proyectoId);
      }
      vm.initRelationships();
    });
  }

  /* descargar() {
        //console.log('se hizo clic');
        this.adjuntoProyectoFaseService().downloadFile(this.adjuntoProyectoFase.id, this.adjuntoProyectoFase.nombreArchivoOriginal);
     } */

     descargarRetro() {
        //console.log('se hizo clic');
        this.adjuntoRetroalimentacionService().downloadFile(this.adjuntoRetroalimentacion.id, this.adjuntoRetroalimentacion.nombreArchivoOriginal);
     }

       descargarAsesorRetro() {
        //console.log('se hizo clic');
        this.adjuntoRetroalimentacionService().downloadFile(this.adjuntoAsesorRetroalimentacion.id, this.adjuntoAsesorRetroalimentacion.nombreArchivoOriginal);
     }

     eliminarRetro(ob) {
    console.log('entro a eliminar');
    this.adjuntoRetroalimentacionService().delete(this.adjuntoRetroalimentacion.id);
    //this.adjuntoProyectoFass=null;
    this.adjuntoRetroalimentacions = null;
        //this.isSaving = false;
           (<any>this).$router.go(0);
  }


   /* asignarData(event, entity, field, isImage){
     var fileData =  event.target.files[0];
    this.adjuntoProyectoFase.nombreArchivoOriginal= fileData.name;
    console.log(this.adjuntoProyectoFase.nombreArchivoOriginal);

    this.setFileData(event, entity, field, isImage)
    
  } */

  asignarDataRetro(event, entity, field, isImage){
     var fileData =  event.target.files[0];
    this.adjuntoRetroalimentacion.nombreArchivoOriginal= fileData.name;
    console.log(this.adjuntoRetroalimentacion.nombreArchivoOriginal);

    this.setFileData(event, entity, field, isImage)
    
  }

   asignarDataAsesorRetro(event, entity, field, isImage){
     var fileData =  event.target.files[0];
    this.adjuntoAsesorRetroalimentacion.nombreArchivoOriginal= fileData.name;
    console.log(this.adjuntoAsesorRetroalimentacion.nombreArchivoOriginal);

    this.setFileData(event, entity, field, isImage)
    
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
    this.proyecto.preEnviado = true;

    if (this.proyecto.id) {
      this.proyectoService()
        .update(this.proyecto)
        .then(param => {
          this.isSaving = false;
          //this.$router.push({ name: 'PropuestaIntegrantesView', params: { proyectoId: this.proyecto.id.toString() } });
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

          // this.$router.push({ name: 'PropuestaIntegrantesView', params: { proyectoId: this.proyId } });
          (<any>this).$router.go(0);

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
    .findProyectoIntegrantes(parseInt(this.$route.params.proyectoId))
      //.find(parseInt(this.$route.params.proyectoId))
      .then(res => {
        this.proyecto = res.data;
        this.integrants = this.proyecto.listaIntegrantesProyecto;
        console.log( res.data.listaIntegrantesProyecto);
        
      });
  }

  async initRelationships() {
    this.proyId = this.$route.params.proyectoId;

     /////////////////////////////////////////////////////////7
     let res= await this.fasesService()
                .retrieveFase(this.nombreFase)   
                 this.fase = res.data;
           
          
      /* res=  await this.adjuntoProyectoFaseService()
      .findAdjuntoProyectoFase(this.proyId,  this.fase.id)
      .then(res => {
        this.adjuntoProyectoFass = res.data;
        if(this.adjuntoProyectoFass.length==0){
         this.adjuntoProyectoFase =  new AdjuntoProyectoFase();
        }
        else{
          this.adjuntoProyectoFase = this.adjuntoProyectoFass[0];
        }
         console.log(this.adjuntoProyectoFass);
        
      }); */

      res=  await this.adjuntoRetroalimentacionService()
      .findAdjuntoRetroalimentacionProyectoFaseAuthority(this.proyId,  this.fase.id, this.authority)
      .then(res => {
        this.adjuntoRetroalimentacions = res.data;
        if(this.adjuntoRetroalimentacions.length==0){
         this.adjuntoRetroalimentacion =  new AdjuntoRetroalimentacion();
        }
        else{
          this.adjuntoRetroalimentacion = this.adjuntoRetroalimentacions[0];
        }
       
      });
/////////////////////////
         res=  await this.adjuntoRetroalimentacionService()
      .findAdjuntoRetroalimentacionProyectoFaseAuthority(this.proyId,  this.fase.id, this.authorityAsesor)
      .then(res => {
        this.adjuntoAsesorRetroalimentacions = res.data;
        if(this.adjuntoAsesorRetroalimentacions.length==0){
         this.adjuntoAsesorRetroalimentacion =  new AdjuntoRetroalimentacion();
        }
        else{
          this.adjuntoAsesorRetroalimentacion = this.adjuntoAsesorRetroalimentacions[0];
        }
       
      });
     
 
     
  }

  get isDisabled(){
    	return !this.terms;
    }
}
</script>

<style scoped>
</style>