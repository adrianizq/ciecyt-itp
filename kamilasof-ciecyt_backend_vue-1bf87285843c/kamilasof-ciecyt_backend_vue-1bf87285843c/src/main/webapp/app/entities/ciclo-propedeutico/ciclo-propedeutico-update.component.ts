import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import ModalidadService from '../modalidad/modalidad.service';
import { IModalidad } from '@/shared/model/modalidad.model';

import AcuerdoService from '../acuerdo/acuerdo.service';
import { IAcuerdo } from '@/shared/model/acuerdo.model';

import AlertService from '@/shared/alert/alert.service';
import { ICicloPropedeutico, CicloPropedeutico } from '@/shared/model/ciclo-propedeutico.model';
import CicloPropedeuticoService from './ciclo-propedeutico.service';

const validations: any = {
  cicloPropedeutico: {
    ciclo: {},
  },
};

@Component({
  validations,
})
export default class CicloPropedeuticoUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('cicloPropedeuticoService') private cicloPropedeuticoService: () => CicloPropedeuticoService;
  public cicloPropedeutico: ICicloPropedeutico = new CicloPropedeutico();

  @Inject('modalidadService') private modalidadService: () => ModalidadService;

  public modalidads: IModalidad[] = [];

  @Inject('acuerdoService') private acuerdoService: () => AcuerdoService;

  public acuerdos: IAcuerdo[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.cicloPropedeuticoId) {
        vm.retrieveCicloPropedeutico(to.params.cicloPropedeuticoId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.cicloPropedeutico.id) {
      this.cicloPropedeuticoService()
        .update(this.cicloPropedeutico)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ciecytApp.cicloPropedeutico.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.cicloPropedeuticoService()
        .create(this.cicloPropedeutico)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ciecytApp.cicloPropedeutico.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveCicloPropedeutico(cicloPropedeuticoId): void {
    this.cicloPropedeuticoService()
      .find(cicloPropedeuticoId)
      .then(res => {
        this.cicloPropedeutico = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.modalidadService()
      .retrieve()
      .then(res => {
        this.modalidads = res.data;
      });
    this.acuerdoService()
      .retrieve()
      .then(res => {
        this.acuerdos = res.data;
      });
  }
}
