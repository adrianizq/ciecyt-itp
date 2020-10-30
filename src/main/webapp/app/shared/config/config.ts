import Vuex from 'vuex';
import VueI18n from 'vue-i18n';
import JhiFormatter from './formatter';
import { setupAxiosInterceptors } from '@/shared/config/axios-interceptor';

import { library } from '@fortawesome/fontawesome-svg-core';
import { faSort } from '@fortawesome/free-solid-svg-icons/faSort';
import { faEye } from '@fortawesome/free-solid-svg-icons/faEye';
import { faSync } from '@fortawesome/free-solid-svg-icons/faSync';
import { faBan } from '@fortawesome/free-solid-svg-icons/faBan';
import { faTrash } from '@fortawesome/free-solid-svg-icons/faTrash';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons/faArrowLeft';
import { faSave } from '@fortawesome/free-solid-svg-icons/faSave';
import { faPlus } from '@fortawesome/free-solid-svg-icons/faPlus';
import { faPencilAlt } from '@fortawesome/free-solid-svg-icons/faPencilAlt';
import { faUser } from '@fortawesome/free-solid-svg-icons/faUser';
import { faHdd } from '@fortawesome/free-solid-svg-icons/faHdd';
import { faTachometerAlt } from '@fortawesome/free-solid-svg-icons/faTachometerAlt';
import { faHeart } from '@fortawesome/free-solid-svg-icons/faHeart';
import { faList } from '@fortawesome/free-solid-svg-icons/faList';
import { faTasks } from '@fortawesome/free-solid-svg-icons/faTasks';
import { faBook } from '@fortawesome/free-solid-svg-icons/faBook';
import { faLock } from '@fortawesome/free-solid-svg-icons/faLock';
import { faSignInAlt } from '@fortawesome/free-solid-svg-icons/faSignInAlt';
import { faSignOutAlt } from '@fortawesome/free-solid-svg-icons/faSignOutAlt';
import { faThList } from '@fortawesome/free-solid-svg-icons/faThList';
import { faUserPlus } from '@fortawesome/free-solid-svg-icons/faUserPlus';
import { faWrench } from '@fortawesome/free-solid-svg-icons/faWrench';
import { faAsterisk } from '@fortawesome/free-solid-svg-icons/faAsterisk';
import { faFlag } from '@fortawesome/free-solid-svg-icons/faFlag';
import { faBell } from '@fortawesome/free-solid-svg-icons/faBell';
import { faHome } from '@fortawesome/free-solid-svg-icons/faHome';
import { faTimesCircle } from '@fortawesome/free-solid-svg-icons/faTimesCircle';
import { faSearch } from '@fortawesome/free-solid-svg-icons/faSearch';
import { faRoad } from '@fortawesome/free-solid-svg-icons/faRoad';
import { faCloud } from '@fortawesome/free-solid-svg-icons/faCloud';
import { faBars } from '@fortawesome/free-solid-svg-icons/faBars';
import { faTimes } from '@fortawesome/free-solid-svg-icons/faTimes';

import VueCookie from 'vue-cookie';
import Vuelidate from 'vuelidate';
import Vue2Filters from 'vue2-filters';

import * as filters from '@/shared/date/filters';

export function initVueApp(vue) {
  vue.use(VueCookie);
  vue.use(Vuelidate);
  vue.use(Vue2Filters);
  setupAxiosInterceptors(() => console.log('Unauthorized!'));
  filters.initFilters();
}

export function initFortAwesome(vue) {
  library.add(
    faSort,
    faEye,
    faSync,
    faBan,
    faTrash,
    faArrowLeft,
    faSave,
    faPlus,
    faPencilAlt,
    faUser,
    faTachometerAlt,
    faHeart,
    faList,
    faTasks,
    faBook,
    faHdd,
    faLock,
    faSignInAlt,
    faSignOutAlt,
    faWrench,
    faThList,
    faUserPlus,
    faAsterisk,
    faFlag,
    faBell,
    faHome,
    faRoad,
    faCloud,
    faTimesCircle,
    faSearch,
    faBars,
    faTimes
  );
}

export function initI18N(vue) {
  vue.use(VueI18n);
  return new VueI18n({
    silentTranslationWarn: true,
    formatter: new JhiFormatter(),
  });
}

export function initVueXStore(vue) {
  vue.use(Vuex);
  return new Vuex.Store({
    state: {
      dismissSecs: 0,
      dismissCountDown: 0,
      alertType: '',
      alertMessage: {},
      logon: false,
      userIdentity: null,
      authenticated: false,
      ribbonOnProfiles: '',
      activeProfiles: '',
      currentLanguage: localStorage.getItem('currentLanguage') || 'es',
      languages: {
        en: { name: 'English' },
        es: { name: 'Español' },
        // jhipster-needle-i18n-language-key-pipe - JHipster will add/remove languages in this object
      },
      /*Menu Lateral Tesis*/
      menu_lateral: [
        {
          title: 'Información General',
          description: 'Ingrese aquí los datos generales de la propuesta',
          to: '/propuesta/informacion-general',
        },
        {
          title: 'Integrantes',
          description: 'En esta sección ingrese los integrantes de la propuesta Nota: Cada modalidad tiene un maximo de integrantes',
          to: '/propuesta/integrantes',
        },
        {
          title: 'Elementos',
          description:
            'En este apartado diligencie: Resumen, Problema, Pregunta de Investigación, Objetivos, Marco de Referencia y Metodología esta sección ingrese los integrantes de la propuesta Nota: Cada modalidad tiene un maximo de integrantes',
          to: '/propuesta/elementos',
        },
        {
          title: 'Presupuesto',
          description: 'Presupuesto',
          to: '/propuesta/presupuesto',
        },
        {
          title: 'Resultados Esperados',
          description: 'Resultados o Productos Esperados del Proyecto',
          to: '/propuesta/resultados_esperados',
        },
        {
          title: 'Impactos Esperados',
          description: 'Impactos Esperados del Proyecto',
          to: '/propuesta/impactos_esperados',
        },
        {
          title: 'Entidades Financiadoras',
          description: 'Entidades Financiadoras',
          to: '/propuesta/entidades',
        },
        {
          title: 'Cronograma',
          description: 'Cronograma de Actividades',
          to: '/propuesta/cronograma',
        },
        {
          title: 'Enviar la Propuesta',
          description: 'En esta sección enviará su propuesta al Ciecyt',
          to: '/propuesta/enviar_propuesta',
        },
      ],

      //Menu Lateral Linea //
      /*Menu Lateral*/
      menu_lateral_linea: [
        {
          title: 'Información General',
          description: 'Ingrese aquí los datos generales de la propuesta',
          to: '/propuesta-linea/informacion-general-linea',
        },
        {
          title: 'Integrantes',
          description: 'En esta sección ingrese los integrantes de la propuesta Nota: Cada modalidad tiene un maximo de integrantes',
          to: '/propuesta-linea/integrantes-linea',
        },
        {
          title: 'Elementos',
          description:
            'En este apartado diligencie: Resumen, Problema, Pregunta de Investigación, Objetivos, Marco de Referencia y Metodología esta sección ingrese los integrantes de la propuesta Nota: Cada modalidad tiene un maximo de integrantes',
          to: '/propuesta-linea/elementos-linea',
        },
        {
          title: 'Presupuesto',
          description: 'Presupuesto',
          to: '/propuesta-linea/presupuesto-linea',
        },
        {
          title: 'Resultados Esperados',
          description: 'Resultados o Productos Esperados del Proyecto',
          to: '/propuesta-linea/resultados_esperados-linea',
        },
        {
          title: 'Impactos Esperados',
          description: 'Impactos Esperados del Proyecto',
          to: '/propuesta-linea/impactos_esperados-linea',
        },
        {
          title: 'Entidades Financiadoras',
          description: 'Entidades Financiadoras',
          to: '/propuesta-linea/entidades-linea',
        },
        {
          title: 'Cronograma',
          description: 'Cronograma de Actividades',
          to: '/propuesta-linea/cronograma-linea',
        },
        {
          title: 'Estado de Propuestas',
          description: 'Muestra el estado de sus propuestas',
          to: '/propuesta-linea/estado_propuestas-linea',
        },
      ],

      /*Menu Lateral Pasantia*/
      menu_lateral_pasantia: [
        {
          title: 'Informacion General',
          description: 'Ingrese aquí los datos generales de la propuesta de pasantía',
          to: '/propuesta-pasantia/informacion-general-pasantia',
        },
        {
          title: 'Integrantes',
          description: 'En esta sección ingrese los integrantes de la propuesta Nota: Cada modalidad tiene un maximo de integrantes',
          to: '/propuesta-pasantia/integrantes-pasantia',
        },
        {
          title: 'Información de la Empresa',
          description: 'En este apartado diligencie: La información de la Empresa donde realizará su pasantia',
          to: '/propuesta-pasantia/informacion-empresa',
        },
        {
          title: 'Elementos de la Pasantía',
          description: 'Elementos de Pasantía, estos elementos son calificables por los jurados',
          to: '/propuesta-pasantia/elementos-pasantia',
        },
        {
          title: 'Cronograma',
          description: 'En el cronograma se deben crear items para: avance del 50%, informe final 100% y sustentacion',
          to: '/propuesta-pasantia/cronograma-pasantia',
        },
        {
          title: 'Enviar la Propuesta',
          description: 'En esta sección enviará su propuesta al Ciecyt',
          to: '/propuesta-pasantia/enviar-propuesta-pasantia',
        },
      ],
      /*Menu Lateral Diplomado*/
      menu_lateral_diplomado: [
        {
          title: 'Informacion General Diplomado',
          description: 'Ingrese aquí los datos generales del diplomado',
          to: '/propuesta-diplomado/informacion-general-diplomado',
        },
        {
          title: 'Integrante(s)',
          description: 'En esta sección ingrese los integrantes del Diplomado Nota: Cada modalidad tiene un maximo de integrantes',
          to: '/propuesta-diplomado/integrantes-diplomado',
        },
        {
          title: 'Elementos del Diplomado',
          description: 'Elementos de Diplomado, estos elementos son calificables por los jurados',
          to: '/propuesta-diplomado/elementos-diplomado',
        },
        {
          title: 'Cronograma',
          description: 'En el cronograma se deben crear items para: avance del 50%, informe final 100% y sustentacion',
          to: '/propuesta-diplomado/cronograma-diplomado',
        },
        {
          title: 'Enviar la Propuesta',
          description: 'En esta sección enviará su propuesta al Ciecyt',
          to: '/propuesta-diplomado/enviar-propuesta-diplomado',
        },
      ],

      menu_lateral_listado: [
        {
          title: 'Jurado',
          description: 'Espacio para evalular las propuestas a cargo',
          to: '/viabilidad-propuesta/listado',
        },
        {
          title: 'Asesor',
          description: 'Espacio para revisar las propuestas a cargo',
          to: '/viabilidad-propuesta/listado-asesor',
        },
      ],
      menu_lateral_ciecyt: [
        {
          title: 'Ciecyt',
          description: 'Espacio para revisar las propuestas del Ciecyt',
          to: '/ciecyt/listado-ciecyt',
        },
        {
          title: 'Jurado(s) de Viabilidad',
          description: 'Ingrese el jurado o jurados de viabilidad',
          to: '/ciecyt/asignar-jurado',
        },
        {
          title: 'Asesor(es)',
          description: 'Ingrese el asesor o asesores',
          to: '/ciecyt/asignar-asesor',
        },
      ],
      menu_lateral_nueva: [
        /* {
          title: 'Mis propuestas',
          description: 'Propuestas creadas',
          to: '/propuesta-nueva/propuestas-investigador',
        },*/
        {
          title: 'Propuesta Nueva',
          description: 'Espacio para crear las propuestas del Ciecyt',
          to: '/propuesta-nueva/informacion-general-nueva',
        },
        {
          title: 'Integrantes',
          description: 'Integrantes de la Propuesta',
          to: '/propuesta-nueva/integrantes-nueva',
        },
        {
          title: 'Jurado de Viabilidad',
          description: 'Jurados Viabilidad del proyecto',
          to: '/propuesta-nueva/viabilidad-nueva',
        },

        ///propuesta-nueva/asignar-jurado-nueva/
        {
          title: 'Jurados',
          description: 'Jurados del proyecto',
          to: '/propuesta-nueva/jurado-nueva',
        },
      ],
    },
    mutations: {
      initAlert(state) {
        state.dismissSecs = 0;
        state.dismissCountDown = 0;
        state.alertType = '';
        state.alertMessage = {};
      },
      setAlertType(state, alertType) {
        state.alertType = alertType;
      },
      setAlertMessage(state, alertMessage) {
        state.dismissSecs = 5;
        state.dismissCountDown = 5;
        state.alertMessage = alertMessage;
      },
      countDownChanged(state, newCountDown) {
        state.dismissCountDown = newCountDown;
      },
      currentLanguage(state, newLanguage) {
        state.currentLanguage = newLanguage;
        localStorage.setItem('currentLanguage', newLanguage);
      },
      authenticate(state) {
        state.logon = true;
      },
      authenticated(state, identity) {
        state.userIdentity = identity;
        state.authenticated = true;
        state.logon = false;
      },
      logout(state) {
        state.userIdentity = null;
        state.authenticated = false;
        state.logon = false;
      },
      setActiveProfiles(state, profile) {
        state.activeProfiles = profile;
      },
      setRibbonOnProfiles(state, ribbon) {
        state.ribbonOnProfiles = ribbon;
      },
    },
    getters: {
      dismissSecs: state => state.dismissSecs,
      dismissCountDown: state => state.dismissCountDown,
      alertType: state => state.alertType,
      alertMessage: state => state.alertMessage,
      currentLanguage: state => state.currentLanguage,
      languages: state => state.languages,
      logon: state => state.logon,
      account: state => state.userIdentity,
      authenticated: state => state.authenticated,
      activeProfiles: state => state.activeProfiles,
      ribbonOnProfiles: state => state.ribbonOnProfiles,
      menuLateral: state => state.menu_lateral,
      menuLateralPasantia: state => state.menu_lateral_pasantia,
      menuLateralDiplomado: state => state.menu_lateral_diplomado,
      menuLateralLinea: state => state.menu_lateral_linea,
      menuLateralListado: state => state.menu_lateral_listado,
      menuLateralCiecyt: state => state.menu_lateral_ciecyt,
      menuLateralNueva: state => state.menu_lateral_nueva,
    },
  });
}
