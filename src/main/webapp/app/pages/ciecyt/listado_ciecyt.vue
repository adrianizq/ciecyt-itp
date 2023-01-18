<template>
<div class="row">
 <div class="col-sm-4">
        
            <menu-lateral-ciecyt :proyectoId='$route.params.proyectoId'></menu-lateral-ciecyt>
 </div>     
    <div class="col-sm-8">
        <h2 id="page-heading">
            <span id="proyecto-heading">Proyectos y propuestas</span>
            
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && proyects && proyects.length === 0">
            <span>No se encontraron proyectos</span>
        </div>
       
        <div >
            <table class="table table-responsive table-striped table-hover table-fixed"
            
            v-if="proyects && proyects.length > 0"
            >
                <thead>
                <tr>
                    <th  v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th  v-on:click="changeOrder('titulo')"><span v-text="$t('ciecytApp.proyecto.titulo')">Titulo</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                     <th  v-on:click="changeOrder('modalidad')"><span >Modalidad</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                     <th ><span>Jurado Viabilidad</span></th>
                     <th ><span >Jurado</span></th>
                     <th ><span >Asesor</span></th>

                     <!--
                         <th class="col-md-1" v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th class="col-md-3" v-on:click="changeOrder('titulo')"><span v-text="$t('ciecytApp.proyecto.titulo')">Titulo</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                     <th class="col-md-2" v-on:click="changeOrder('modalidad')"><span >Modalidad</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                     <th class="col-md-2"><span>Jurado Viabilidad</span></th>
                     <th class="col-md-2"><span >Jurado</span></th>
                     <th class="col-md-2"><span >Asesor</span></th>
                     -->
                    <!-- <th v-on:click="changeOrder('tipo')"><span v-text="$t('ciecytApp.proyecto.tipo')">Tipo</span> <font-awesome-icon icon="sort"></font-awesome-icon></th> -->
                   
                </tr>
                </thead>
                <tbody>
                <!-- JURADO ---------------------------------->
                <tr v-for="proyecto in proyects"
                    :key="proyecto.id">
                    
                    <td>{{proyecto.id}}</td>
                    <td>{{proyecto.titulo}}</td>
                     <td>{{proyecto.proyectoModalidadModalidad}}</td> 
                   
                
                    <!------ JuradoViabilidad ---->
                     <td class="text-right">
                        <div class="btn-group" v-if="!proyecto.tieneJuradoViabilidad&&proyecto.enviado">
                            <router-link :to="{name: 'AsignarJuradoViabilidadView', params: {proyectoId: proyecto.id}}" tag="button" class="btn btn-info btn-sm details">
                               <b-icon-person-fill></b-icon-person-fill>&nbsp;
                                <span class="d-none d-md-inline" >Asignar</span>
                            </router-link>
                         </div>

                         <div class="btn-group" v-if="proyecto.tieneJuradoViabilidad&&proyecto.enviado&&proyecto.viabilidad!=`VIABLE`">
                            <router-link :to="{name: 'AsignarJuradoViabilidadView', params: {proyectoId: proyecto.id}}" tag="button" class="btn btn-secondary btn-sm">
                                <b-icon-person-fill></b-icon-person-fill>&nbsp;
                                <span class="d-none d-md-inline"  >Cambiar</span>
                            </router-link>
                         </div>
                    </td>

                    <!------ Jurado ---->
                     <td class="text-right">
                        <div class="btn-group" v-if="!proyecto.tieneJurado&&proyecto.viabilidad==`VIABLE`">
                            <router-link :to="{name: 'AsignarJuradoView', params: {proyectoId: proyecto.id}}" tag="button" class="btn btn-info btn-sm details">
                                <b-icon-people-fill></b-icon-people-fill>&nbsp;
                                <span class="d-none d-md-inline" >Asignar</span>
                            </router-link>
                         </div>

                         <div class="btn-group" v-if="proyecto.tieneJurado&&proyecto.viabilidad==`VIABLE`">
                            <router-link :to="{name: 'AsignarJuradoView', params: {proyectoId: proyecto.id}}" tag="button" class="btn btn-secondary btn-sm">
                               <b-icon-people-fill></b-icon-people-fill>&nbsp;
                                <span  class="d-none d-md-inline">Cambiar </span>
                            </router-link>
                         </div>
                    </td>
                    <!---ASESor -->
                    <td class="text-right">
                        <div class="btn-group" v-if="!proyecto.tieneAsesor">
                            <router-link :to="{name: 'AsignarAsesorView', params: {proyectoId: proyecto.id}}" tag="button" class="btn btn-info btn-sm details">
                               <b-icon-person></b-icon-person>&nbsp;
                                <span class="d-none d-md-inline" >Asignar</span>
                            </router-link>
                         </div>

                         <div class="btn-group" v-if="proyecto.tieneAsesor">
                            <router-link :to="{name: 'AsignarAsesorView', params: {proyectoId: proyecto.id}}" tag="button" class="btn btn-secondary btn-sm">
                                <b-icon-person></b-icon-person>&nbsp;
                                <span class="d-none d-md-inline" >Cambiar </span>
                            </router-link>
                         </div>
                    </td>

                    <!-------------------------------------------------
                     <td class="text-right">
                        <div class="btn-group" >
                            <router-link :to="{name: 'AsignarAsesorView', params: {proyectoId: proyecto.id}}" tag="button" class="btn btn-info btn-sm details">
                               <b-icon-pencil-square></b-icon-pencil-square>&nbsp;
                                <span class="d-none d-md-inline" >Asesor</span>
                            </router-link>
                         </div>
                    </td>
                    --> 


                </tr>
               


                </tbody>
            </table>

            <button  @click="downloadPdf" >Descargar Informe</button>
        </div>
        
        <div v-show="proyects && proyects.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
    </div>
</template>

<script lang="ts">


import { mixins } from 'vue-class-component';


import Vue2Filters from 'vue2-filters';

import AlertService from '@/shared/alert/alert.service';



import { Component, Inject, Vue } from 'vue-property-decorator';
import MenuLateralCiecyt from '@/components/ciecyt/menu_lateral_ciecyt.vue';


import UserManagementService from '@/admin/user-management/user-management.service';
import { IUser, User } from '@/shared/model/user.model';

import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
import ProyectoService from '@/entities/proyecto/proyecto.service';
import { IIntegranteProyecto, IntegranteProyecto } from '@/shared/model/integrante-proyecto.model';
import IntegranteProyectoService from '@/entities/integrante-proyecto/integrante-proyecto.service';
import { jsPDF } from "jspdf";


    const validations: any = {};

   @Component({
        components: { MenuLateralCiecyt },
        validations
    })

export default class ListadoCiecyt extends Vue {
   @Inject('proyectoService') private proyectoService: () => ProyectoService;

    @Inject('integranteProyectoService') private integranteProyectoService: () => IntegranteProyectoService;

    @Inject('userService') private userManagementService: () => UserManagementService;
 
   @Inject('alertService') private alertService: () => AlertService;

   //doc = new jsPDF();
   



    
   //  public elementosProyecto: IElementoProyecto[] =[];
    public proyects: IProyecto[] = [];

    public users: IUser[] = [];
   
   
    public proyId: any = null;
   

  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public isFetching = false;
  public dismissCountDown: number = this.$store.getters.dismissCountDown;
  public dismissSecs: number = this.$store.getters.dismissSecs;
  public alertType: string = this.$store.getters.alertType;
  public alertMessage: any = this.$store.getters.alertMessage;
  public autoridades: any =  this.$store.getters.account.authorities;


public getAlertFromStore() {
    this.dismissCountDown = this.$store.getters.dismissCountDown;
    this.dismissSecs = this.$store.getters.dismissSecs;
    this.alertType = this.$store.getters.alertType;
    this.alertMessage = this.$store.getters.alertMessage;
  }

  public countDownChanged(dismissCountDown: number) {
    this.alertService().countDownChanged(dismissCountDown);
    this.getAlertFromStore();
  }

  public mounted(): void {
    this.retrieveAllProyectos();
    
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllProyectos();
  }

  public retrieveAllProyectos(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort()
    };
    //if (this.autoridades.includes("ROLE_CIECYT")||this.autoridades.includes("ROLE_ADMIN")){
    this.proyectoService()
  
      .retrieveAllProyectosIntegrantes()
      .then(
        res => {
          this.proyects = res.data;
        

          /*for (let i = 0; i < this.proyects.length; i++) {
            for (let j=0; j< this.proyects[i].listaIntegrantes.length; j++){
               this.proyects[i].tieneJurado=false;
              if (this.proyects[i].listaIntegrantes[j].integranteProyectoRolesModalidadRol=="Jurado")
                this.proyects[i].tieneJurado=true;
            }
          } */

/*for (let i = 0; i < this.proyects.length; i++) {
        this.integranteProyectoService().retrieveJuradosProyectoNoPage(this.proyects[i].id,"Jurado").
        then((response) => {
                      
         this.proyects[i].tieneJurado=true;
            //resolve()
       // }, (response) => {
          
       // });
        //resolve()
    }*/


        },
        err => {
          this.isFetching = false;
        }

        
        
      );

     this.userManagementService()
      .retrieveAll()
      .then(res => {
        this.users = res.data;
      });
      //this.integrantesProyectoJurados=null;
      //Promise.all(Promise).then((this.proyects)=>{
      //    this.proyects.forEach(p => {
      //      this.integrantesProyectoJurados.push(this.integranteProyectoService().retrieveJuradosProyecto(p.id,"Jurado", paginationQuery));
      //  });
      //  console.log( this.integrantesProyectoJurados)



      //}//del if ROLE_ASESOR

      



      
    }

    

  public prepareRemove(instance: IProyecto): void {
    this.removeId = instance.id;
  }

  public removeProyecto(): void {
    this.proyectoService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('ciecytApp.proyecto.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllProyectos();
        this.closeDialog();
      });
    }

    public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllProyectos();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }



    public isSaving = false;


        beforeRouteEnter(to, from, next) {
            next(vm => {

                    vm.initRelationships();
            });
        }

public get username(): string {
    return this.$store.getters.account ? this.$store.getters.account.login : '';
  }

  public firstName(login): string {
    var res="";
       this.users.forEach(u => {
      if(u.login==login){
        res=u.firstName.toString();
        //console.log(res);
       }
    });
    return res;
  }

  public lastName(login): string {
    var res="";
       this.users.forEach(u => {
      if(u.login==login){
        res=u.lastName.toString();
     }
    });
    return res;
  }


  public get userid(): string {
    return this.$store.getters.account ? this.$store.getters.account.id : '';
  }

public get authorities(): string {
    console.log(this.$store.getters.account);
    return this.$store.getters.account ? this.$store.getters.account.authorities : '';
  }

  
public downloadPdf(){

  //http://raw.githack.com/MrRio/jsPDF/master/docs/index.html

  //const doc = new jsPDF('p','in','letter')
  const doc = new jsPDF()

  var sizes = [12, 16, 20]
, fonts = [['Times','Roman'],['Helvetica',''], ['Times','Italic']]
, font, size, lines
, margin = 0.5 // inches on a 8.5 x 11 inch sheet.
, verticalOffset = margin
, loremipsum = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus id eros turpis. Vivamus tempor urna vitae sapien mollis molestie. Vestibulum in lectus non enim bibendum laoreet at at libero. Etiam malesuada erat sed sem blandit in varius orci porttitor. Sed at sapien urna. Fusce augue ipsum, molestie et adipiscing at, varius quis enim. Morbi sed magna est, vel vestibulum urna. Sed tempor ipsum vel mi pretium at elementum urna tempor. Nulla faucibus consectetur felis, elementum venenatis mi mollis gravida. Aliquam mi ante, accumsan eu tempus vitae, viverra quis justo.\n\nProin feugiat augue in augue rhoncus eu cursus tellus laoreet. Pellentesque eu sapien at diam porttitor venenatis nec vitae velit. Donec ultrices volutpat lectus eget vehicula. Nam eu erat mi, in pulvinar eros. Mauris viverra porta orci, et vehicula lectus sagittis id. Nullam at magna vitae nunc fringilla posuere. Duis volutpat malesuada ornare. Nulla in eros metus. Vivamus a posuere libero.'

  doc.setDrawColor(0, 255, 0)
	.setLineWidth(1/72)
	.line(margin, margin, margin, 11 - margin)
  .line(8.5 - margin, margin, 8.5-margin, 11-margin)

  var pageHeight= doc.internal.pageSize.height;
  doc.text("Proyectos y propuestas CIECYT", 10, 10);
  //this.doc.text(pageHeight.toString(), 10, 10);
   var y = 0;
  doc.setFontSize(10);
  doc.line(10, 12, 200, 12);
 /* for(var i =0; i < 1000; i++) {  //se comprobo para varias paginas
    this.doc.text("hola" + " i=" + i + " y=" + y,10,15+(y*5));
    if (y >= pageHeight-245)
        {
        this.doc.addPage();
        y = 0 // Restart height position
        } 
    y++;
  }*/
  /*********************************************** */
 for(let i =0; i < this.proyects.length; i++) {
  ////verificar nueva pagina
   if (y >= pageHeight-245)
        {
        doc.addPage();
        y = 0 // Restart height position
        } 
    doc.text(
      this.proyects[i].id.toString() 
      , 10,15+(y*5));
    y++;

   // var longitudTitulo =this.proyects[i].titulo.length;
   // var lineasTitulo = parseInt(longitudTitulo/100) +1;

    var arrayLinea = this.proyects[i].titulo.match(/.{1,110}/g);
    
    arrayLinea.forEach(l => {
      doc.text(
       l 
      
     // + " "  + this.proyects[i].programa.toString() 
    , 10,15+(y*5));
    y++;
    });

    
    doc.text(
       this.proyects[i].programa.toString() 
       + " "  + this.proyects[i].facultadId.toString()
    , 10,15+(y*5));
    y++;
    if(this.proyects[i].listaIntegrantesProyecto.length>0){
    for(let j =0; j < this.proyects[i].listaIntegrantesProyecto.length; j++) {
      ////verificar nueva pagina
       if (y >= pageHeight-245)
        {
        doc.addPage();
        y = 0 // Restart height position
        } 
      if (this.proyects[i].listaIntegrantesProyecto[j].integranteProyectoUserLogin!=null){
        let fn = this.firstName(this.proyects[i].listaIntegrantesProyecto[j].integranteProyectoUserLogin)
        let ln = this.lastName(this.proyects[i].listaIntegrantesProyecto[j].integranteProyectoUserLogin)
     
      doc.text(
      this.proyects[i].listaIntegrantesProyecto[j].integranteProyectoUserLogin.toString()
      + " " + this.proyects[i].listaIntegrantesProyecto[j].integranteProyectoRolesModalidadRol.toString() 
      + " " + fn
      + " " + ln

      
      ,15,15+(y*5));
      }
      y++;
    }
 }

///////////////////////////////
 if(this.proyects[i].fechaEnvioPropuesta!=null){
    doc.text(
      "Fecha de envío de la propuesta: "  + this.proyects[i].fechaEnvioPropuesta
    , 10,15+(y*5));
    }
    else{
      doc.text(
      "Fecha de envío de la propuesta: "  + "NO ENVIADA"
    , 10,15+(y*5));
    }
    y++;

    ////////////////////////////////////////////////////////////

///////////////////////////////
if(this.proyects[i].viable==null){
    doc.text(
      "Viablilidad: "  + "Sin asignar Viabilidad"
    , 10,15+(y*5));
}
else if(this.proyects[i].viable==false){
      doc.text(
      "Viablilidad: "  + "NO ES VIABLE"
    , 10,15+(y*5));
    }
else{
  doc.text(
      "Viablilidad: "  + "VIABLE"
    , 10,15+(y*5));
}
    y++;

    ////////////////////////////////////////////////////////////


    //////////////////////////////
if(this.proyects[i].nota==0 || this.proyects[i].nota==null){
    doc.text(
      "Nota: "  + "Sin asignar Nota"
    , 10,15+(y*5));
}
else if(this.proyects[i].nota>0){
      doc.text(
      "Nota: "  + this.proyects[i].nota
    , 10,15+(y*5));
    }

    y++;

    ////////////////////////////////////////////////////////////

    doc.line(10, 15+(y*5) , 200, 15+(y*5));
    y++;
}




doc.save("a4.pdf");
}
</script>

<style scoped>
</style>
