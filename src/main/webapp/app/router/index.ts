import Vue from 'vue';
import Component from 'vue-class-component';
Component.registerHooks([
  'beforeRouteEnter',
  'beforeRouteLeave',
  'beforeRouteUpdate' // for vue-router 2.2+
]);
import Router from 'vue-router';
const Home = () => import('../core/home/home.vue');
const Error = () => import('../core/error/error.vue');
const Register = () => import('../account/register/register.vue');
const Activate = () => import('../account/activate/activate.vue');
const ResetPasswordInit = () => import('../account/reset-password/init/reset-password-init.vue');
const ResetPasswordFinish = () => import('../account/reset-password/finish/reset-password-finish.vue');
const ChangePassword = () => import('../account/change-password/change-password.vue');
const Settings = () => import('../account/settings/settings.vue');
const JhiUserManagementComponent = () => import('../admin/user-management/user-management.vue');
const JhiUserManagementViewComponent = () => import('../admin/user-management/user-management-view.vue');
const JhiUserManagementEditComponent = () => import('../admin/user-management/user-management-edit.vue');
const JhiConfigurationComponent = () => import('../admin/configuration/configuration.vue');
const JhiDocsComponent = () => import('../admin/docs/docs.vue');
const JhiHealthComponent = () => import('../admin/health/health.vue');
const JhiLogsComponent = () => import('../admin/logs/logs.vue');
const JhiAuditsComponent = () => import('../admin/audits/audits.vue');
const JhiMetricsComponent = () => import('../admin/metrics/metrics.vue');
/* tslint:disable */
// prettier-ignore
const Proyecto = () => import('../entities/proyecto/proyecto.vue');
// prettier-ignore
const ProyectoUpdate = () => import('../entities/proyecto/proyecto-update.vue');
// prettier-ignore
const ProyectoDetails = () => import('../entities/proyecto/proyecto-details.vue');
// prettier-ignore
const LineaInvestigacion = () => import('../entities/linea-investigacion/linea-investigacion.vue');
// prettier-ignore
const LineaInvestigacionUpdate = () => import('../entities/linea-investigacion/linea-investigacion-update.vue');
// prettier-ignore
const LineaInvestigacionDetails = () => import('../entities/linea-investigacion/linea-investigacion-details.vue');
// prettier-ignore
const GrupoSemillero = () => import('../entities/grupo-semillero/grupo-semillero.vue');
// prettier-ignore
const GrupoSemilleroUpdate = () => import('../entities/grupo-semillero/grupo-semillero-update.vue');
// prettier-ignore
const GrupoSemilleroDetails = () => import('../entities/grupo-semillero/grupo-semillero-details.vue');
// prettier-ignore
const Facultad = () => import('../entities/facultad/facultad.vue');
// prettier-ignore
const FacultadUpdate = () => import('../entities/facultad/facultad-update.vue');
// prettier-ignore
const FacultadDetails = () => import('../entities/facultad/facultad-details.vue');
// prettier-ignore
const Modalidad = () => import('../entities/modalidad/modalidad.vue');
// prettier-ignore
const ModalidadUpdate = () => import('../entities/modalidad/modalidad-update.vue');
// prettier-ignore
const ModalidadDetails = () => import('../entities/modalidad/modalidad-details.vue');
// prettier-ignore
const Acuerdo = () => import('../entities/acuerdo/acuerdo.vue');
// prettier-ignore
const AcuerdoUpdate = () => import('../entities/acuerdo/acuerdo-update.vue');
// prettier-ignore
const AcuerdoDetails = () => import('../entities/acuerdo/acuerdo-details.vue');
// prettier-ignore
const CicloPropedeutico = () => import('../entities/ciclo-propedeutico/ciclo-propedeutico.vue');
// prettier-ignore
const CicloPropedeuticoUpdate = () => import('../entities/ciclo-propedeutico/ciclo-propedeutico-update.vue');
// prettier-ignore
const CicloPropedeuticoDetails = () => import('../entities/ciclo-propedeutico/ciclo-propedeutico-details.vue');
// prettier-ignore
const ResultadosEsperados = () => import('../entities/resultados-esperados/resultados-esperados.vue');
// prettier-ignore
const ResultadosEsperadosUpdate = () => import('../entities/resultados-esperados/resultados-esperados-update.vue');
// prettier-ignore
const ResultadosEsperadosDetails = () => import('../entities/resultados-esperados/resultados-esperados-details.vue');
// prettier-ignore
const Producto = () => import('../entities/producto/producto.vue');
// prettier-ignore
const ProductoUpdate = () => import('../entities/producto/producto-update.vue');
// prettier-ignore
const ProductoDetails = () => import('../entities/producto/producto-details.vue');
// prettier-ignore
const ProductoProyecto = () => import('../entities/producto-proyecto/producto-proyecto.vue');
// prettier-ignore
const ProductoProyectoUpdate = () => import('../entities/producto-proyecto/producto-proyecto-update.vue');
// prettier-ignore
const ProductoProyectoDetails = () => import('../entities/producto-proyecto/producto-proyecto-details.vue');
// prettier-ignore
const ImpactosEsperados = () => import('../entities/impactos-esperados/impactos-esperados.vue');
// prettier-ignore
const ImpactosEsperadosUpdate = () => import('../entities/impactos-esperados/impactos-esperados-update.vue');
// prettier-ignore
const ImpactosEsperadosDetails = () => import('../entities/impactos-esperados/impactos-esperados-details.vue');
// prettier-ignore
const Cronograma = () => import('../entities/cronograma/cronograma.vue');
// prettier-ignore
const CronogramaUpdate = () => import('../entities/cronograma/cronograma-update.vue');
// prettier-ignore
const CronogramaDetails = () => import('../entities/cronograma/cronograma-details.vue');
// prettier-ignore
const Rubro = () => import('../entities/rubro/rubro.vue');
// prettier-ignore
const RubroUpdate = () => import('../entities/rubro/rubro-update.vue');
// prettier-ignore
const RubroDetails = () => import('../entities/rubro/rubro-details.vue');
// prettier-ignore
const PresupuestoValor = () => import('../entities/presupuesto-valor/presupuesto-valor.vue');
// prettier-ignore
const PresupuestoValorUpdate = () => import('../entities/presupuesto-valor/presupuesto-valor-update.vue');
// prettier-ignore
const PresupuestoValorDetails = () => import('../entities/presupuesto-valor/presupuesto-valor-details.vue');
// prettier-ignore
const Entidad = () => import('../entities/entidad/entidad.vue');
// prettier-ignore
const EntidadUpdate = () => import('../entities/entidad/entidad-update.vue');
// prettier-ignore
const EntidadDetails = () => import('../entities/entidad/entidad-details.vue');
// prettier-ignore
const EntidadFinanciadora = () => import('../entities/entidad-financiadora/entidad-financiadora.vue');
// prettier-ignore
const EntidadFinanciadoraUpdate = () => import('../entities/entidad-financiadora/entidad-financiadora-update.vue');
// prettier-ignore
const EntidadFinanciadoraDetails = () => import('../entities/entidad-financiadora/entidad-financiadora-details.vue');
// prettier-ignore
const Elemento = () => import('../entities/elemento/elemento.vue');
// prettier-ignore
const ElementoUpdate = () => import('../entities/elemento/elemento-update.vue');
// prettier-ignore
const ElementoDetails = () => import('../entities/elemento/elemento-details.vue');
// prettier-ignore
const ElementoProyecto = () => import('../entities/elemento-proyecto/elemento-proyecto.vue');
// prettier-ignore
const ElementoProyectoUpdate = () => import('../entities/elemento-proyecto/elemento-proyecto-update.vue');
// prettier-ignore
const ElementoProyectoDetails = () => import('../entities/elemento-proyecto/elemento-proyecto-details.vue');
// prettier-ignore
const Formato = () => import('../entities/formato/formato.vue');
// prettier-ignore
const FormatoUpdate = () => import('../entities/formato/formato-update.vue');
// prettier-ignore
const FormatoDetails = () => import('../entities/formato/formato-details.vue');
// prettier-ignore
const TipoPregunta = () => import('../entities/tipo-pregunta/tipo-pregunta.vue');
// prettier-ignore
const TipoPreguntaUpdate = () => import('../entities/tipo-pregunta/tipo-pregunta-update.vue');
// prettier-ignore
const TipoPreguntaDetails = () => import('../entities/tipo-pregunta/tipo-pregunta-details.vue');
// prettier-ignore
const Pregunta = () => import('../entities/pregunta/pregunta.vue');
// prettier-ignore
const PreguntaUpdate = () => import('../entities/pregunta/pregunta-update.vue');
// prettier-ignore
const PreguntaDetails = () => import('../entities/pregunta/pregunta-details.vue');
// prettier-ignore
const ProyectoRespuestas = () => import('../entities/proyecto-respuestas/proyecto-respuestas.vue');
// prettier-ignore
const ProyectoRespuestasUpdate = () => import('../entities/proyecto-respuestas/proyecto-respuestas-update.vue');
// prettier-ignore
const ProyectoRespuestasDetails = () => import('../entities/proyecto-respuestas/proyecto-respuestas-details.vue');
// prettier-ignore
const RolesModalidad = () => import('../entities/roles-modalidad/roles-modalidad.vue');
// prettier-ignore
const RolesModalidadUpdate = () => import('../entities/roles-modalidad/roles-modalidad-update.vue');
// prettier-ignore
const RolesModalidadDetails = () => import('../entities/roles-modalidad/roles-modalidad-details.vue');
// prettier-ignore
const Fases = () => import('../entities/fases/fases.vue');
// prettier-ignore
const FasesUpdate = () => import('../entities/fases/fases-update.vue');
// prettier-ignore
const FasesDetails = () => import('../entities/fases/fases-details.vue');
// prettier-ignore
const ProyectoFase = () => import('../entities/proyecto-fase/proyecto-fase.vue');
// prettier-ignore
const ProyectoFaseUpdate = () => import('../entities/proyecto-fase/proyecto-fase-update.vue');
// prettier-ignore
const ProyectoFaseDetails = () => import('../entities/proyecto-fase/proyecto-fase-details.vue');
// prettier-ignore
const CronogramaCiecyt = () => import('../entities/cronograma-ciecyt/cronograma-ciecyt.vue');
// prettier-ignore
const CronogramaCiecytUpdate = () => import('../entities/cronograma-ciecyt/cronograma-ciecyt-update.vue');
// prettier-ignore
const CronogramaCiecytDetails = () => import('../entities/cronograma-ciecyt/cronograma-ciecyt-details.vue');
// prettier-ignore
const CronogramaCiecytFases = () => import('../entities/cronograma-ciecyt-fases/cronograma-ciecyt-fases.vue');
// prettier-ignore
const CronogramaCiecytFasesUpdate = () => import('../entities/cronograma-ciecyt-fases/cronograma-ciecyt-fases-update.vue');
// prettier-ignore
const CronogramaCiecytFasesDetails = () => import('../entities/cronograma-ciecyt-fases/cronograma-ciecyt-fases-details.vue');
// prettier-ignore
const IntegranteProyecto = () => import('../entities/integrante-proyecto/integrante-proyecto.vue');
// prettier-ignore
const IntegranteProyectoUpdate = () => import('../entities/integrante-proyecto/integrante-proyecto-update.vue');
// prettier-ignore
const IntegranteProyectoDetails = () => import('../entities/integrante-proyecto/integrante-proyecto-details.vue');
// prettier-ignore
const Solicitud = () => import('../entities/solicitud/solicitud.vue');
// prettier-ignore
const SolicitudUpdate = () => import('../entities/solicitud/solicitud-update.vue');
// prettier-ignore
const SolicitudDetails = () => import('../entities/solicitud/solicitud-details.vue');
// prettier-ignore
const AdjuntoProyectoFase = () => import('../entities/adjunto-proyecto-fase/adjunto-proyecto-fase.vue');
// prettier-ignore
const AdjuntoProyectoFaseUpdate = () => import('../entities/adjunto-proyecto-fase/adjunto-proyecto-fase-update.vue');
// prettier-ignore
const AdjuntoProyectoFaseDetails = () => import('../entities/adjunto-proyecto-fase/adjunto-proyecto-fase-details.vue');
// prettier-ignore
const Retroalimentacion = () => import('../entities/retroalimentacion/retroalimentacion.vue');
// prettier-ignore
const RetroalimentacionUpdate = () => import('../entities/retroalimentacion/retroalimentacion-update.vue');
// prettier-ignore
const RetroalimentacionDetails = () => import('../entities/retroalimentacion/retroalimentacion-details.vue');
// prettier-ignore
const AdjuntoRetroalimentacion = () => import('../entities/adjunto-retroalimentacion/adjunto-retroalimentacion.vue');
// prettier-ignore
const AdjuntoRetroalimentacionUpdate = () => import('../entities/adjunto-retroalimentacion/adjunto-retroalimentacion-update.vue');
// prettier-ignore
const AdjuntoRetroalimentacionDetails = () => import('../entities/adjunto-retroalimentacion/adjunto-retroalimentacion-details.vue');
// prettier-ignore
const FichaTecnica = () => import('../entities/ficha-tecnica/ficha-tecnica.vue');
// prettier-ignore
const FichaTecnicaUpdate = () => import('../entities/ficha-tecnica/ficha-tecnica-update.vue');
// prettier-ignore
const FichaTecnicaDetails = () => import('../entities/ficha-tecnica/ficha-tecnica-details.vue');
// prettier-ignore
const Categorizacion = () => import('../entities/categorizacion/categorizacion.vue');
// prettier-ignore
const CategorizacionUpdate = () => import('../entities/categorizacion/categorizacion-update.vue');
// prettier-ignore
const CategorizacionDetails = () => import('../entities/categorizacion/categorizacion-details.vue');
// prettier-ignore
const Usuario = () => import('../entities/usuario/usuario.vue');
// prettier-ignore
const UsuarioUpdate = () => import('../entities/usuario/usuario-update.vue');
// prettier-ignore
const UsuarioDetails = () => import('../entities/usuario/usuario-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

Vue.use(Router);

// prettier-ignore
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/forbidden',
      name: 'Forbidden',
      component: Error,
      meta: { error403: true }
    },
    {
      path: '/not-found',
      name: 'NotFound',
      component: Error,
      meta: { error404: true }
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/activate',
      name: 'Activate',
      component: Activate
    },
    {
      path: '/reset/request',
      name: 'ResetPasswordInit',
      component: ResetPasswordInit
    },
    {
      path: '/reset/finish',
      name: 'ResetPasswordFinish',
      component: ResetPasswordFinish
    },
    {
      path: '/account/password',
      name: 'ChangePassword',
      component: ChangePassword,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/account/settings',
      name: 'Settings',
      component: Settings,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/admin/user-management',
      name: 'JhiUser',
      component: JhiUserManagementComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/user-management/new',
      name: 'JhiUserCreate',
      component: JhiUserManagementEditComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/user-management/:userId/edit',
      name: 'JhiUserEdit',
      component: JhiUserManagementEditComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/user-management/:userId/view',
      name: 'JhiUserView',
      component: JhiUserManagementViewComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/docs',
      name: 'JhiDocsComponent',
      component: JhiDocsComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/audits',
      name: 'JhiAuditsComponent',
      component: JhiAuditsComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/jhi-health',
      name: 'JhiHealthComponent',
      component: JhiHealthComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/logs',
      name: 'JhiLogsComponent',
      component: JhiLogsComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/jhi-metrics',
      name: 'JhiMetricsComponent',
      component: JhiMetricsComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/jhi-configuration',
      name: 'JhiConfigurationComponent',
      component: JhiConfigurationComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    }
    ,
    {
      path: '/entity/proyecto',
      name: 'Proyecto',
      component: Proyecto,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/proyecto/new',
      name: 'ProyectoCreate',
      component: ProyectoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/proyecto/:proyectoId/edit',
      name: 'ProyectoEdit',
      component: ProyectoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/proyecto/:proyectoId/view',
      name: 'ProyectoView',
      component: ProyectoDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/linea-investigacion',
      name: 'LineaInvestigacion',
      component: LineaInvestigacion,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/linea-investigacion/new',
      name: 'LineaInvestigacionCreate',
      component: LineaInvestigacionUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/linea-investigacion/:lineaInvestigacionId/edit',
      name: 'LineaInvestigacionEdit',
      component: LineaInvestigacionUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/linea-investigacion/:lineaInvestigacionId/view',
      name: 'LineaInvestigacionView',
      component: LineaInvestigacionDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/grupo-semillero',
      name: 'GrupoSemillero',
      component: GrupoSemillero,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/grupo-semillero/new',
      name: 'GrupoSemilleroCreate',
      component: GrupoSemilleroUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/grupo-semillero/:grupoSemilleroId/edit',
      name: 'GrupoSemilleroEdit',
      component: GrupoSemilleroUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/grupo-semillero/:grupoSemilleroId/view',
      name: 'GrupoSemilleroView',
      component: GrupoSemilleroDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/facultad',
      name: 'Facultad',
      component: Facultad,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/facultad/new',
      name: 'FacultadCreate',
      component: FacultadUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/facultad/:facultadId/edit',
      name: 'FacultadEdit',
      component: FacultadUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/facultad/:facultadId/view',
      name: 'FacultadView',
      component: FacultadDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/modalidad',
      name: 'Modalidad',
      component: Modalidad,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/modalidad/new',
      name: 'ModalidadCreate',
      component: ModalidadUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/modalidad/:modalidadId/edit',
      name: 'ModalidadEdit',
      component: ModalidadUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/modalidad/:modalidadId/view',
      name: 'ModalidadView',
      component: ModalidadDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/acuerdo',
      name: 'Acuerdo',
      component: Acuerdo,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/acuerdo/new',
      name: 'AcuerdoCreate',
      component: AcuerdoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/acuerdo/:acuerdoId/edit',
      name: 'AcuerdoEdit',
      component: AcuerdoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/acuerdo/:acuerdoId/view',
      name: 'AcuerdoView',
      component: AcuerdoDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/ciclo-propedeutico',
      name: 'CicloPropedeutico',
      component: CicloPropedeutico,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/ciclo-propedeutico/new',
      name: 'CicloPropedeuticoCreate',
      component: CicloPropedeuticoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/ciclo-propedeutico/:cicloPropedeuticoId/edit',
      name: 'CicloPropedeuticoEdit',
      component: CicloPropedeuticoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/ciclo-propedeutico/:cicloPropedeuticoId/view',
      name: 'CicloPropedeuticoView',
      component: CicloPropedeuticoDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/resultados-esperados',
      name: 'ResultadosEsperados',
      component: ResultadosEsperados,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/resultados-esperados/new',
      name: 'ResultadosEsperadosCreate',
      component: ResultadosEsperadosUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/resultados-esperados/:resultadosEsperadosId/edit',
      name: 'ResultadosEsperadosEdit',
      component: ResultadosEsperadosUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/resultados-esperados/:resultadosEsperadosId/view',
      name: 'ResultadosEsperadosView',
      component: ResultadosEsperadosDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/producto',
      name: 'Producto',
      component: Producto,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/producto/new',
      name: 'ProductoCreate',
      component: ProductoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/producto/:productoId/edit',
      name: 'ProductoEdit',
      component: ProductoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/producto/:productoId/view',
      name: 'ProductoView',
      component: ProductoDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/producto-proyecto',
      name: 'ProductoProyecto',
      component: ProductoProyecto,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/producto-proyecto/new',
      name: 'ProductoProyectoCreate',
      component: ProductoProyectoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/producto-proyecto/:productoProyectoId/edit',
      name: 'ProductoProyectoEdit',
      component: ProductoProyectoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/producto-proyecto/:productoProyectoId/view',
      name: 'ProductoProyectoView',
      component: ProductoProyectoDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/impactos-esperados',
      name: 'ImpactosEsperados',
      component: ImpactosEsperados,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/impactos-esperados/new',
      name: 'ImpactosEsperadosCreate',
      component: ImpactosEsperadosUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/impactos-esperados/:impactosEsperadosId/edit',
      name: 'ImpactosEsperadosEdit',
      component: ImpactosEsperadosUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/impactos-esperados/:impactosEsperadosId/view',
      name: 'ImpactosEsperadosView',
      component: ImpactosEsperadosDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/cronograma',
      name: 'Cronograma',
      component: Cronograma,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/cronograma/new',
      name: 'CronogramaCreate',
      component: CronogramaUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/cronograma/:cronogramaId/edit',
      name: 'CronogramaEdit',
      component: CronogramaUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/cronograma/:cronogramaId/view',
      name: 'CronogramaView',
      component: CronogramaDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/rubro',
      name: 'Rubro',
      component: Rubro,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/rubro/new',
      name: 'RubroCreate',
      component: RubroUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/rubro/:rubroId/edit',
      name: 'RubroEdit',
      component: RubroUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/rubro/:rubroId/view',
      name: 'RubroView',
      component: RubroDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/presupuesto-valor',
      name: 'PresupuestoValor',
      component: PresupuestoValor,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/presupuesto-valor/new',
      name: 'PresupuestoValorCreate',
      component: PresupuestoValorUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/presupuesto-valor/:presupuestoValorId/edit',
      name: 'PresupuestoValorEdit',
      component: PresupuestoValorUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/presupuesto-valor/:presupuestoValorId/view',
      name: 'PresupuestoValorView',
      component: PresupuestoValorDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/entidad',
      name: 'Entidad',
      component: Entidad,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/entidad/new',
      name: 'EntidadCreate',
      component: EntidadUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/entidad/:entidadId/edit',
      name: 'EntidadEdit',
      component: EntidadUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/entidad/:entidadId/view',
      name: 'EntidadView',
      component: EntidadDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/entidad-financiadora',
      name: 'EntidadFinanciadora',
      component: EntidadFinanciadora,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/entidad-financiadora/new',
      name: 'EntidadFinanciadoraCreate',
      component: EntidadFinanciadoraUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/entidad-financiadora/:entidadFinanciadoraId/edit',
      name: 'EntidadFinanciadoraEdit',
      component: EntidadFinanciadoraUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/entidad-financiadora/:entidadFinanciadoraId/view',
      name: 'EntidadFinanciadoraView',
      component: EntidadFinanciadoraDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/elemento',
      name: 'Elemento',
      component: Elemento,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/elemento/new',
      name: 'ElementoCreate',
      component: ElementoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/elemento/:elementoId/edit',
      name: 'ElementoEdit',
      component: ElementoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/elemento/:elementoId/view',
      name: 'ElementoView',
      component: ElementoDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/elemento-proyecto',
      name: 'ElementoProyecto',
      component: ElementoProyecto,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/elemento-proyecto/new',
      name: 'ElementoProyectoCreate',
      component: ElementoProyectoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/elemento-proyecto/:elementoProyectoId/edit',
      name: 'ElementoProyectoEdit',
      component: ElementoProyectoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/elemento-proyecto/:elementoProyectoId/view',
      name: 'ElementoProyectoView',
      component: ElementoProyectoDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/formato',
      name: 'Formato',
      component: Formato,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/formato/new',
      name: 'FormatoCreate',
      component: FormatoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/formato/:formatoId/edit',
      name: 'FormatoEdit',
      component: FormatoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/formato/:formatoId/view',
      name: 'FormatoView',
      component: FormatoDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/tipo-pregunta',
      name: 'TipoPregunta',
      component: TipoPregunta,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/tipo-pregunta/new',
      name: 'TipoPreguntaCreate',
      component: TipoPreguntaUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/tipo-pregunta/:tipoPreguntaId/edit',
      name: 'TipoPreguntaEdit',
      component: TipoPreguntaUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/tipo-pregunta/:tipoPreguntaId/view',
      name: 'TipoPreguntaView',
      component: TipoPreguntaDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/pregunta',
      name: 'Pregunta',
      component: Pregunta,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/pregunta/new',
      name: 'PreguntaCreate',
      component: PreguntaUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/pregunta/:preguntaId/edit',
      name: 'PreguntaEdit',
      component: PreguntaUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/pregunta/:preguntaId/view',
      name: 'PreguntaView',
      component: PreguntaDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/proyecto-respuestas',
      name: 'ProyectoRespuestas',
      component: ProyectoRespuestas,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/proyecto-respuestas/new',
      name: 'ProyectoRespuestasCreate',
      component: ProyectoRespuestasUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/proyecto-respuestas/:proyectoRespuestasId/edit',
      name: 'ProyectoRespuestasEdit',
      component: ProyectoRespuestasUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/proyecto-respuestas/:proyectoRespuestasId/view',
      name: 'ProyectoRespuestasView',
      component: ProyectoRespuestasDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/roles-modalidad',
      name: 'RolesModalidad',
      component: RolesModalidad,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/roles-modalidad/new',
      name: 'RolesModalidadCreate',
      component: RolesModalidadUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/roles-modalidad/:rolesModalidadId/edit',
      name: 'RolesModalidadEdit',
      component: RolesModalidadUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/roles-modalidad/:rolesModalidadId/view',
      name: 'RolesModalidadView',
      component: RolesModalidadDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/fases',
      name: 'Fases',
      component: Fases,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/fases/new',
      name: 'FasesCreate',
      component: FasesUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/fases/:fasesId/edit',
      name: 'FasesEdit',
      component: FasesUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/fases/:fasesId/view',
      name: 'FasesView',
      component: FasesDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/proyecto-fase',
      name: 'ProyectoFase',
      component: ProyectoFase,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/proyecto-fase/new',
      name: 'ProyectoFaseCreate',
      component: ProyectoFaseUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/proyecto-fase/:proyectoFaseId/edit',
      name: 'ProyectoFaseEdit',
      component: ProyectoFaseUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/proyecto-fase/:proyectoFaseId/view',
      name: 'ProyectoFaseView',
      component: ProyectoFaseDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/cronograma-ciecyt',
      name: 'CronogramaCiecyt',
      component: CronogramaCiecyt,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/cronograma-ciecyt/new',
      name: 'CronogramaCiecytCreate',
      component: CronogramaCiecytUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/cronograma-ciecyt/:cronogramaCiecytId/edit',
      name: 'CronogramaCiecytEdit',
      component: CronogramaCiecytUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/cronograma-ciecyt/:cronogramaCiecytId/view',
      name: 'CronogramaCiecytView',
      component: CronogramaCiecytDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/cronograma-ciecyt-fases',
      name: 'CronogramaCiecytFases',
      component: CronogramaCiecytFases,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/cronograma-ciecyt-fases/new',
      name: 'CronogramaCiecytFasesCreate',
      component: CronogramaCiecytFasesUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/cronograma-ciecyt-fases/:cronogramaCiecytFasesId/edit',
      name: 'CronogramaCiecytFasesEdit',
      component: CronogramaCiecytFasesUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/cronograma-ciecyt-fases/:cronogramaCiecytFasesId/view',
      name: 'CronogramaCiecytFasesView',
      component: CronogramaCiecytFasesDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/integrante-proyecto',
      name: 'IntegranteProyecto',
      component: IntegranteProyecto,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/integrante-proyecto/new',
      name: 'IntegranteProyectoCreate',
      component: IntegranteProyectoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/integrante-proyecto/:integranteProyectoId/edit',
      name: 'IntegranteProyectoEdit',
      component: IntegranteProyectoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/integrante-proyecto/:integranteProyectoId/view',
      name: 'IntegranteProyectoView',
      component: IntegranteProyectoDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/solicitud',
      name: 'Solicitud',
      component: Solicitud,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/solicitud/new',
      name: 'SolicitudCreate',
      component: SolicitudUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/solicitud/:solicitudId/edit',
      name: 'SolicitudEdit',
      component: SolicitudUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/solicitud/:solicitudId/view',
      name: 'SolicitudView',
      component: SolicitudDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/adjunto-proyecto-fase',
      name: 'AdjuntoProyectoFase',
      component: AdjuntoProyectoFase,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/adjunto-proyecto-fase/new',
      name: 'AdjuntoProyectoFaseCreate',
      component: AdjuntoProyectoFaseUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/adjunto-proyecto-fase/:adjuntoProyectoFaseId/edit',
      name: 'AdjuntoProyectoFaseEdit',
      component: AdjuntoProyectoFaseUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/adjunto-proyecto-fase/:adjuntoProyectoFaseId/view',
      name: 'AdjuntoProyectoFaseView',
      component: AdjuntoProyectoFaseDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/retroalimentacion',
      name: 'Retroalimentacion',
      component: Retroalimentacion,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/retroalimentacion/new',
      name: 'RetroalimentacionCreate',
      component: RetroalimentacionUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/retroalimentacion/:retroalimentacionId/edit',
      name: 'RetroalimentacionEdit',
      component: RetroalimentacionUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/retroalimentacion/:retroalimentacionId/view',
      name: 'RetroalimentacionView',
      component: RetroalimentacionDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/adjunto-retroalimentacion',
      name: 'AdjuntoRetroalimentacion',
      component: AdjuntoRetroalimentacion,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/adjunto-retroalimentacion/new',
      name: 'AdjuntoRetroalimentacionCreate',
      component: AdjuntoRetroalimentacionUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/adjunto-retroalimentacion/:adjuntoRetroalimentacionId/edit',
      name: 'AdjuntoRetroalimentacionEdit',
      component: AdjuntoRetroalimentacionUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/adjunto-retroalimentacion/:adjuntoRetroalimentacionId/view',
      name: 'AdjuntoRetroalimentacionView',
      component: AdjuntoRetroalimentacionDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/ficha-tecnica',
      name: 'FichaTecnica',
      component: FichaTecnica,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/ficha-tecnica/new',
      name: 'FichaTecnicaCreate',
      component: FichaTecnicaUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/ficha-tecnica/:fichaTecnicaId/edit',
      name: 'FichaTecnicaEdit',
      component: FichaTecnicaUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/ficha-tecnica/:fichaTecnicaId/view',
      name: 'FichaTecnicaView',
      component: FichaTecnicaDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/categorizacion',
      name: 'Categorizacion',
      component: Categorizacion,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/categorizacion/new',
      name: 'CategorizacionCreate',
      component: CategorizacionUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/categorizacion/:categorizacionId/edit',
      name: 'CategorizacionEdit',
      component: CategorizacionUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/categorizacion/:categorizacionId/view',
      name: 'CategorizacionView',
      component: CategorizacionDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/entity/usuario',
      name: 'Usuario',
      component: Usuario,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/usuario/new',
      name: 'UsuarioCreate',
      component: UsuarioUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/usuario/:usuarioId/edit',
      name: 'UsuarioEdit',
      component: UsuarioUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/entity/usuario/:usuarioId/view',
      name: 'UsuarioView',
      component: UsuarioDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ]
});
