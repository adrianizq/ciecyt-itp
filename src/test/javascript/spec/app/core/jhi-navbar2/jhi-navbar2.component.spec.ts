import { createLocalVue, shallowMount, Wrapper } from '@vue/test-utils';
import JhiNavbar2 from '@/core/jhi-navbar2/jhi-navbar2.vue';
import JhiNavbarClass from '@/core/jhi-navbar2/jhi-navbar2.component';
import * as config from '@/shared/config/config';
import router from '@/router';

const localVue = createLocalVue();
config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-navbar', {});
localVue.component('b-navbar-nav', {});
localVue.component('b-dropdown-item', {});
localVue.component('b-collapse', {});
localVue.component('b-nav-item', {});
localVue.component('b-nav-item-dropdown', {});
localVue.component('b-navbar-toggle', {});
localVue.component('b-navbar-brand', {});
localVue.component('b-navbar-nav', {});

describe('JhiNavbar2', () => {
  let jhiNavbar2: JhiNavbarClass;
  let wrapper: Wrapper<JhiNavbarClass>;
  const loginService = { openLogin: jest.fn() };
  const accountService = { hasAnyAuthorityAndCheckAuth: jest.fn().mockImplementation(() => Promise.resolve(true)) };
  const translationService = { refreshTranslation: jest.fn() };

  beforeEach(() => {
    wrapper = shallowMount<JhiNavbarClass>(JhiNavbar2, {
      i18n,
      store,
      router,
      localVue,
      provide: {
        loginService: () => loginService,
        translationService: () => translationService,
        accountService: () => accountService,
      },
    });
    jhiNavbar2 = wrapper.vm;
  });
  it('should refresh translations', () => {
    expect(translationService.refreshTranslation).toHaveBeenCalled();
  });

  it('should have user data set after authentication', () => {
    store.commit('authenticated', { login: 'test' });

    expect(jhiNavbar2.authenticated).toBeTruthy();
  });

  it('should determine active route', () => {
    router.push('/toto');

    expect(jhiNavbar2.subIsActive('/titi')).toBeFalsy();
    expect(jhiNavbar2.subIsActive('/toto')).toBeTruthy();
    expect(jhiNavbar2.subIsActive(['/toto', 'toto'])).toBeTruthy();
  });

  it('should check for correct language', () => {
    store.commit('currentLanguage', 'fr');

    expect(jhiNavbar2.isActiveLanguage('en')).toBeFalsy();
    expect(jhiNavbar2.isActiveLanguage('fr')).toBeTruthy();
  });
});
