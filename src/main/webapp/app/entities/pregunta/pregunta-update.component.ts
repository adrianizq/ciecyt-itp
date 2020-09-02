import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import TipoPreguntaService from '../tipo-pregunta/tipo-pregunta.service';
import { ITipoPregunta } from '@/shared/model/tipo-pregunta.model';

import ModalidadService from '../modalidad/modalidad.service';
import { IModalidad } from '@/shared/model/modalidad.model';

import RolesModalidadService from '../roles-modalidad/roles-modalidad.service';
import { IRolesModalidad } from '@/shared/model/roles-modalidad.model';

import ElementoService from '../elemento/elemento.service';
import { IElemento, Elemento } from '@/shared/model/elemento.model';

import AlertService from '@/shared/alert/alert.service';
import { IPregunta, Pregunta } from '@/shared/model/pregunta.model';
import PreguntaService from './pregunta.service';

const validations: any = {
  pregunta: {
    encabezado: {},
    descripcion: {},
    pregunta: {}
  }
};

@Component({
  validations
})
export default class PreguntaUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('preguntaService') private preguntaService: () => PreguntaService;
  public pregunta: IPregunta = new Pregunta();

  @Inject('tipoPreguntaService') private tipoPreguntaService: () => TipoPreguntaService;

  public tipoPreguntas: ITipoPregunta[] = [];

  @Inject('modalidadService') private modalidadService: () => ModalidadService;

  public modalidads: IModalidad[] = [];

  @Inject('rolesModalidadService') private rolesModalidadService: () => RolesModalidadService;

  public rolesModalidads: IRolesModalidad[] = [];

  @Inject('elementoService') private elementoService: () => ElementoService;

  public elements: IElemento[] = [];

  public elemento: IElemento = new Elemento();

  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.preguntaId) {
        vm.retrievePregunta(to.params.preguntaId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.pregunta.id) {
      this.preguntaService()
        .update(this.pregunta)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ciecytApp.pregunta.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.preguntaService()
        .create(this.pregunta)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ciecytApp.pregunta.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  get Elementos() {
    return this.elements.filter(elemento => {
      return elemento.elementoModalidadId == this.pregunta.preguntaModalidadId;
    });
  }

  public retrievePregunta(preguntaId): void {
    this.preguntaService()
      .find(preguntaId)
      .then(res => {
        this.pregunta = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.tipoPreguntaService()
      .retrieve()
      .then(res => {
        this.tipoPreguntas = res.data;
      });
    this.modalidadService()
      .retrieve()
      .then(res => {
        this.modalidads = res.data;
      });
    this.rolesModalidadService()
      .retrieve()
      .then(res => {
        this.rolesModalidads = res.data;
      });
    this.elementoService()
      .retrieve()
      .then(res => {
        this.elements = res.data;
      });
  }
}
