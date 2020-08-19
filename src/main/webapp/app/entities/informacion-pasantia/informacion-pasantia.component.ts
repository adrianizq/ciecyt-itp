import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IInformacionPasantia } from '@/shared/model/informacion-pasantia.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import InformacionPasantiaService from './informacion-pasantia.service';

@Component({
  mixins: [Vue2Filters.mixin]
})
export default class InformacionPasantia extends mixins(AlertMixin) {
  @Inject('informacionPasantiaService') private informacionPasantiaService: () => InformacionPasantiaService;
  private removeId: number = null;

  public informacionPasantias: IInformacionPasantia[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllInformacionPasantias();
  }

  public clear(): void {
    this.retrieveAllInformacionPasantias();
  }

  public retrieveAllInformacionPasantias(): void {
    this.isFetching = true;

    this.informacionPasantiaService()
      .retrieve()
      .then(
        res => {
          this.informacionPasantias = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IInformacionPasantia): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeInformacionPasantia(): void {
    this.informacionPasantiaService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('ciecytVueApp.informacionPasantia.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllInformacionPasantias();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
