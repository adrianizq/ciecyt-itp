import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import FormatoService from '../formato/formato.service';
import { IFormato } from '@/shared/model/formato.model';

import ModalidadService from '../modalidad/modalidad.service';
import { IModalidad } from '@/shared/model/modalidad.model';

import AlertService from '@/shared/alert/alert.service';
import { IElemento, Elemento } from '@/shared/model/elemento.model';
import ElementoService from './elemento.service';

const validations: any = {
  elemento: {
    elemento: {}
  }
};

@Component({
  validations
})
export default class ElementoUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('elementoService') private elementoService: () => ElementoService;
  public elemento: IElemento = new Elemento();

  @Inject('formatoService') private formatoService: () => FormatoService;

  public formatoes: IFormato[] = [];

  @Inject('modalidadService') private modalidadService: () => ModalidadService;

  public modalidads: IModalidad[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.elementoId) {
        vm.retrieveElemento(to.params.elementoId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.elemento.id) {
      this.elementoService()
        .update(this.elemento)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ciecytApp.elemento.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.elementoService()
        .create(this.elemento)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ciecytApp.elemento.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveElemento(elementoId): void {
    this.elementoService()
      .find(elementoId)
      .then(res => {
        this.elemento = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.formatoService()
      .retrieve()
      .then(res => {
        this.formatoes = res.data;
      });
    this.modalidadService()
      .retrieve()
      .then(res => {
        this.modalidads = res.data;
      });
  }
}
