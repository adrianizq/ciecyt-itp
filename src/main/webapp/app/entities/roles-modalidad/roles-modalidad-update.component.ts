import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import ModalidadService from '../modalidad/modalidad.service';
import { IModalidad } from '@/shared/model/modalidad.model';

import AlertService from '@/shared/alert/alert.service';
import { IRolesModalidad, RolesModalidad } from '@/shared/model/roles-modalidad.model';
import RolesModalidadService from './roles-modalidad.service';

const validations: any = {
  rolesModalidad: {
    rol: {},
    cantidad: {},
    calificador: {}
  }
};

@Component({
  validations
})
export default class RolesModalidadUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('rolesModalidadService') private rolesModalidadService: () => RolesModalidadService;
  public rolesModalidad: IRolesModalidad = new RolesModalidad();

  @Inject('modalidadService') private modalidadService: () => ModalidadService;

  public modalidads: IModalidad[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.rolesModalidadId) {
        vm.retrieveRolesModalidad(to.params.rolesModalidadId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.rolesModalidad.id) {
      this.rolesModalidadService()
        .update(this.rolesModalidad)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ciecytApp.rolesModalidad.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.rolesModalidadService()
        .create(this.rolesModalidad)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ciecytApp.rolesModalidad.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveRolesModalidad(rolesModalidadId): void {
    this.rolesModalidadService()
      .find(rolesModalidadId)
      .then(res => {
        this.rolesModalidad = res;
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
  }
}
