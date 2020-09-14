<template>
<div class="row">

        
            <menu-lateral-listado :proyectoId='$route.params.proyectoId'></menu-lateral-listado>
      
    <div>
        <h2 id="page-heading">
            <span id="proyecto-heading">Mis propuestas</span>
            <!--<router-link :to="{name: 'PreguntaCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-pregunta">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('ciecytApp.pregunta.home.createLabel')">
                    Create a new Pregunta
                </span>
            </router-link> -->
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
        <div>{{username}} con id {{userid}}</div>
        <div class="table-responsive" v-if="proyects && proyects.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('titulo')"><span v-text="$t('ciecytApp.proyecto.titulo')">Titulo</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('tipo')"><span v-text="$t('ciecytApp.proyecto.tipo')">Tipo</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="proyecto in proyects"
                    :key="proyecto.id">
                    <td>
                        <router-link :to="{name: 'PropuestaEvaluarView', params: {proyectoId: proyecto.id}}">{{proyecto.id}}</router-link>
                    </td>

                    <td>{{proyecto.titulo}}</td>
                    <td>{{proyecto.tipo}}</td>
                   
                
                    
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'PropuestaEvaluarView', params: {proyectoId: proyecto.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.eval')">Evaluar</span>
                            </router-link>
  
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="ciecytApp.proyecto.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-proyecto-heading" v-bind:title="$t('ciecytApp.proyecto.delete.question')">Are you sure you want to delete this proyecto?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-proyecto" v-text="$t('entity.action.delete')" v-on:click="removeProyecto()">Delete</button>
            </div>
        </b-modal>
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
import MenuLateralListado from '@/components/propuesta_listado/menu_lateral_listado.vue';




import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
import ProyectoService from '@/entities/proyecto/proyecto.service';


    const validations: any = {};

   @Component({
        components: { MenuLateralListado },
        validations
    })

export default class Listado extends Vue {
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
    this.proyectoService()
      .retrieveProyectoIntegrante(this.userid,paginationQuery)
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
