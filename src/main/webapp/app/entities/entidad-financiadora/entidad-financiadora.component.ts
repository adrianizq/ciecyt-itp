import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IEntidadFinanciadora } from '@/shared/model/entidad-financiadora.model';
import AlertService from '@/shared/alert/alert.service';

import EntidadFinanciadoraService from './entidad-financiadora.service';

@Component
export default class EntidadFinanciadora extends mixins(Vue2Filters.mixin) {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('entidadFinanciadoraService') private entidadFinanciadoraService: () => EntidadFinanciadoraService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  public entidadFinanciadoras: IEntidadFinanciadora[] = [];

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
    this.retrieveAllEntidadFinanciadoras();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllEntidadFinanciadoras();
  }

  public retrieveAllEntidadFinanciadoras(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.entidadFinanciadoraService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.entidadFinanciadoras = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IEntidadFinanciadora): void {
    this.removeId = instance.id;
  }

  public removeEntidadFinanciadora(): void {
    this.entidadFinanciadoraService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('ciecytApp.entidadFinanciadora.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllEntidadFinanciadoras();
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
    this.retrieveAllEntidadFinanciadoras();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
