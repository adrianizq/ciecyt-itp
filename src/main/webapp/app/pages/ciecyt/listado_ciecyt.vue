<template>
<div class="row">

        
            <menu-lateral-ciecyt :proyectoId='$route.params.proyectoId'></menu-lateral-ciecyt>
      
    <div>
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
        <div>{{username}} con id {{userid}} {{autoridades}}</div>
        <div class="table-responsive" v-if="proyects && proyects.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('titulo')"><span v-text="$t('ciecytApp.proyecto.titulo')">Titulo</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                     <th><span >Jurado Viabilidad</span></th>
                     <th><span >Asesor</span></th>
                    <!-- <th v-on:click="changeOrder('tipo')"><span v-text="$t('ciecytApp.proyecto.tipo')">Tipo</span> <font-awesome-icon icon="sort"></font-awesome-icon></th> -->
                   
                </tr>
                </thead>
                <tbody>
                <!-- JURADO ---------------------------------->
                <tr v-for="proyecto in proyects"
                    :key="proyecto.id">
                    <td>
                        <router-link :to="{name: 'AsignarJuradoView', params: {proyectoId: proyecto.id}}">{{proyecto.id}}</router-link>
                    </td>

                    <td>{{proyecto.titulo}}</td>
                    <!-- <td>{{proyecto.tipo}}</td> -->
                   
                
                    
                    <td class="text-right">
                        <div class="btn-group" >
                            <router-link :to="{name: 'AsignarJuradoView', params: {proyectoId: proyecto.id}}" tag="button" class="btn btn-info btn-sm details">
                               <b-icon-pencil-square></b-icon-pencil-square>&nbsp;
                                <span class="d-none d-md-inline" >Jurado Viabilidad</span>
                            </router-link>
                         </div>
                    </td>

                     <td class="text-right">
                        <div class="btn-group" >
                            <router-link :to="{name: 'AsignarAsesorView', params: {proyectoId: proyecto.id}}" tag="button" class="btn btn-info btn-sm details">
                               <b-icon-pencil-square></b-icon-pencil-square>&nbsp;
                                <span class="d-none d-md-inline" >Asesor</span>
                            </router-link>
                         </div>
                    </td>


                </tr>
               


                </tbody>
            </table>
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




import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
import ProyectoService from '@/entities/proyecto/proyecto.service';


    const validations: any = {};

   @Component({
        components: { MenuLateralCiecyt },
        validations
    })

export default class ListadoCiecyt extends Vue {
   @Inject('proyectoService') private proyectoService: () => ProyectoService;
 
   @Inject('alertService') private alertService: () => AlertService;


    
   //  public elementosProyecto: IElementoProyecto[] =[];
    public proyects: IProyecto[] = [];
   
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
    if (this.autoridades.includes("ROLE_CIECYT")){
    this.proyectoService()
      //.retrieveProyectoIntegrante(this.userid,paginationQuery) //todos los roles no borrar
      //.retrieveProyectoIntegranteAuthority(this.userid,"ROLE_ASESOR",paginationQuery)
      .retrieve(paginationQuery)
      .then(
        res => {
          this.proyects = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
      }//del if ROLE_ASESOR

      



      
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

  public get userid(): string {
    return this.$store.getters.account ? this.$store.getters.account.id : '';
  }

public get authorities(): string {
    console.log(this.$store.getters.account);
    return this.$store.getters.account ? this.$store.getters.account.authorities : '';
  }



}
</script>

<style scoped>
</style>
