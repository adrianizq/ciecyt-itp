import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import TipoPreguntaService from '../tipo-pregunta/tipo-pregunta.service';
import { ITipoPregunta } from '@/shared/model/tipo-pregunta.model';

import ModalidadService from '../modalidad/modalidad.service';
import { IModalidad } from '@/shared/model/modalidad.model';

import RolesModalidadService from '../roles-modalidad/roles-modalidad.service';
import { IRolesModalidad } from '@/shared/model/roles-modalidad.model';

import FasesService from '../fases/fases.service';
import { IFases } from '@/shared/model/fases.model';

import ElementoService from '../elemento/elemento.service';
import { IElemento, Elemento } from '@/shared/model/elemento.model';

import AlertService from '@/shared/alert/alert.service';
import { IPregunta, Pregunta } from '@/shared/model/pregunta.model';
import PreguntaService from './pregunta.service';

import { IPreguntaModalidad, PreguntaModalidad } from '@/shared/model/pregunta-modalidad.model';
import PreguntaModalidadService from '@/entities/pregunta-modalidad/pregunta-modalidad.service';

const validations: any = {
  pregunta: {
    encabezado: {},
    descripcion: {},
    pregunta: {},
    puntajeMaximo: {},
    preguntaElementoId: {},
  },
};

@Component({
  validations,
})
export default class PreguntaUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('preguntaService') private preguntaService: () => PreguntaService;
  public pregunta: IPregunta;
  public modalidadesAsignadas: any = [];

  @Inject('tipoPreguntaService') private tipoPreguntaService: () => TipoPreguntaService;

  public tipoPreguntas: ITipoPregunta[] = [];

  @Inject('modalidadService') private modalidadService: () => ModalidadService;

  public modalidads: IModalidad[] = [];

  @Inject('rolesModalidadService') private rolesModalidadService: () => RolesModalidadService;

  public rolesModalidads: IRolesModalidad[] = [];

  @Inject('fasesService') private fasesService: () => FasesService;

  public fass: IFases[] = [];

  @Inject('elementoService') private elementoService: () => ElementoService;

  @Inject('preguntaModalidadService') private preguntaModalidadService: () => PreguntaModalidadService;

  public elements: IElemento[] = [];

  public preguntasModalidsPreguntaId: IPreguntaModalidad[] = [];

  public elemento: IElemento = new Elemento();

  public preguntaId: any;

  public isSaving = false;

  public selected: any = [];

  public constructor() {
    super();
    this.pregunta = new Pregunta();
    this.pregunta.listPreguntaModalidadDTO = [];
  }

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.preguntaId) {
        //vm.retrievePregunta(to.params.preguntaId);
        vm.initRelationships(to.params.preguntaId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;

    //console.log(this.pregunta.elementoId); //si reporta

    if (this.pregunta.preguntaElementoId) {
      this.elements.forEach(item => {
        if (item.id == this.pregunta.preguntaElementoId) {
          //this.elemento = item;
          this.pregunta.preguntaElemento = item.elemento; //error al guardar
        }
      });
    }

    //
    if (this.pregunta.preguntaFaseId) {
      this.fass.forEach(item => {
        if (item.id == this.pregunta.preguntaFaseId) {
          //this.elemento = item;
          this.pregunta.preguntaFase = item.fase; //error al guardar
        }
      });
    }

    //  console.log(this.pregunta.preguntaElemento);
    //}

    //borrar funciona
    if (this.pregunta.id) {
      this.preguntasModalidsPreguntaId.forEach(pm => {
        this.preguntaModalidadService().delete(pm.id);
      });

      //crear todavia no funciona

      for (var i = 0; i < this.modalidadesAsignadas.length; i++) {
        //this.modalidadesAsignadas.forEach(element => {
        let pr = new PreguntaModalidad();
        pr.preguntaId = this.preguntaId;
        pr.modalidad2Id = parseInt(this.modalidadesAsignadas[i]);

        console.log(pr);
        this.preguntaModalidadService()
          .create(pr)
          .then(param => {
            this.isSaving = false;
          });
      }

      this.preguntaService()
        .update(this.pregunta)
        .then(param => {
          this.isSaving = false;
          // this.$router.go(-1);
          //(<any>this).$router.go(0);
          const message = this.$t('ciecytApp.pregunta.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
      /////////

      // console.log(this.selected);
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
    //console.log(this.modalidadesAsignadas);
  }

  get Elementos() {
    return this.elements.filter(elemento => {
      return elemento.elementoModalidadId == this.pregunta.preguntaModalidadId;
    });
  }

  get Roles() {
    return this.rolesModalidads.filter(rol => {
      return rol.rolesModalidadModalidadId == this.pregunta.preguntaModalidadId;
    });
  }

  get Fases() {
    return this.fass.filter(fase => {
      return fase.fasesModalidadId == this.pregunta.preguntaModalidadId;
    });
  }

  // public retrievePregunta(preguntaId): void {

  // }

  public previousState(): void {
    this.$router.go(-1);
  }

  async initRelationships(preguntaId) {
    let res = await this.preguntaService()
      .find(preguntaId)
      .then(res => {
        this.pregunta = res;
        this.preguntaId = res.id;
      });
    res = await this.tipoPreguntaService()
      .retrieve()
      .then(res => {
        this.tipoPreguntas = res.data;
      });
    res = await this.modalidadService()
      .retrieve()
      .then(res => {
        this.modalidads = res.data;
      });
    res = await this.modalidadService()
      .retrieveModalidadPregunta(parseInt(preguntaId))
      .then(res => {
        this.modalidadesAsignadas = res.data;
        /*this.modalidadesAsignadas.forEach(modal => {
          console.log("Asignando" + modal)
          this.pregunta.listPreguntaModalidadDTO.push(modal);
        });*/
        //this.pregunta.listPreguntaModalidadDTO = this.modalidadesAsignadas;
      });

    //se obtienen las preguntasModalidad de la pregunta actual
    res = await this.preguntaModalidadService()
      .retrievePreguntaModalidadIdPregunta(parseInt(preguntaId))
      .then(res => {
        this.preguntasModalidsPreguntaId = res.data;
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
    this.fasesService()
      .retrieve()
      .then(res => {
        this.fass = res.data;
      });
  }
  /*
  setRolesModalidad(value) {
    this.rolesModalidads.filter(rol => {
      return rol == this.pregunta.preguntaModalidadId;
    });
  }

  setFases(value) {
    this.fass.filter(fase => {
      return fase == this.pregunta.preguntaModalidadId;
    });
  }*/
}
