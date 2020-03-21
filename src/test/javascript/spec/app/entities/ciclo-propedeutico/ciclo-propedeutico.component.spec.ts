/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import CicloPropedeuticoComponent from '@/entities/ciclo-propedeutico/ciclo-propedeutico.vue';
import CicloPropedeuticoClass from '@/entities/ciclo-propedeutico/ciclo-propedeutico.component';
import CicloPropedeuticoService from '@/entities/ciclo-propedeutico/ciclo-propedeutico.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {}
  }
};

describe('Component Tests', () => {
  describe('CicloPropedeutico Management Component', () => {
    let wrapper: Wrapper<CicloPropedeuticoClass>;
    let comp: CicloPropedeuticoClass;
    let cicloPropedeuticoServiceStub: SinonStubbedInstance<CicloPropedeuticoService>;

    beforeEach(() => {
      cicloPropedeuticoServiceStub = sinon.createStubInstance<CicloPropedeuticoService>(CicloPropedeuticoService);
      cicloPropedeuticoServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<CicloPropedeuticoClass>(CicloPropedeuticoComponent, {
        store,
        i18n,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          cicloPropedeuticoService: () => cicloPropedeuticoServiceStub
        }
      });
      comp = wrapper.vm;
    });

    it('should be a Vue instance', () => {
      expect(wrapper.isVueInstance()).toBeTruthy();
    });

    it('Should call load all on init', async () => {
      // GIVEN
      cicloPropedeuticoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllCicloPropedeuticos();
      await comp.$nextTick();

      // THEN
      expect(cicloPropedeuticoServiceStub.retrieve.called).toBeTruthy();
      expect(comp.cicloPropedeuticos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      cicloPropedeuticoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(cicloPropedeuticoServiceStub.retrieve.called).toBeTruthy();
      expect(comp.cicloPropedeuticos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should not load a page if the page is the same as the previous page', () => {
      // GIVEN
      cicloPropedeuticoServiceStub.retrieve.reset();
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(cicloPropedeuticoServiceStub.retrieve.called).toBeFalsy();
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      cicloPropedeuticoServiceStub.retrieve.reset();
      cicloPropedeuticoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(cicloPropedeuticoServiceStub.retrieve.callCount).toEqual(3);
      expect(comp.page).toEqual(1);
      expect(comp.cicloPropedeuticos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.propOrder = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      cicloPropedeuticoServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeCicloPropedeutico();
      await comp.$nextTick();

      // THEN
      expect(cicloPropedeuticoServiceStub.delete.called).toBeTruthy();
      expect(cicloPropedeuticoServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});