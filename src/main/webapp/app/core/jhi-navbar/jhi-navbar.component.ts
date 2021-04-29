import { Component, Inject, Vue } from 'vue-property-decorator';
import { VERSION } from '@/constants';
import LoginService from '@/account/login.service';
import AccountService from '@/account/account.service';
import TranslationService from '@/locale/translation.service';

import MenuService from '@/entities/menu/menu.service';
import { IMenu, MenuBar } from '@/shared/model/menu.model';

@Component
export default class JhiNavbar extends Vue {
  @Inject('loginService')
  private loginService: () => LoginService;
  @Inject('translationService') private translationService: () => TranslationService;

  @Inject('accountService') private accountService: () => AccountService;

  @Inject('menuService')
  private menuService: () => MenuService;

  ///public version = VERSION ? 'v' + VERSION : '';
  public version = 'Software para la Gestión de los Trabajos de Grado';
  private currentLanguage = this.$store.getters.currentLanguage;
  private languages: any = this.$store.getters.languages;
  public menus: MenuBar[] = [];
  public menusCiecyt: MenuBar[] = [];
  public menusJurado: MenuBar[] = [];
  public menusAsesor: MenuBar[] = [];

  created() {
    this.menuService()
      .all()
      .then(res => {
        this.menus = res;
      });
    this.menuService()
      .ciecyt()
      .then(res => {
        this.menusCiecyt = res;
      });

    this.menuService()
      .jurado()
      .then(res => {
        this.menusJurado = res;
      });

    this.menuService()
      .asesor()
      .then(res => {
        this.menusAsesor = res;
      });

    this.translationService().refreshTranslation(this.currentLanguage);
  }

  public subIsActive(input) {
    const paths = Array.isArray(input) ? input : [input];
    return paths.some(path => {
      return this.$route.path.indexOf(path) === 0; // current path starts with this path string
    });
  }

  public changeLanguage(newLanguage: string): void {
    this.translationService().refreshTranslation(newLanguage);
  }

  public isUrl(url: string): boolean {
    if (url.indexOf('()') === -1) {
      return true;
    }

    return false;
  }

  public actionMenu(callback: string): void {
    callback = callback.replace('()', '');
    this[callback]();
  }

  public isActiveLanguage(key: string): boolean {
    return key === this.$store.getters.currentLanguage;
  }

  public logout(): void {
    localStorage.removeItem('jhi-authenticationToken');
    sessionStorage.removeItem('jhi-authenticationToken');
    this.$store.commit('logout');
    // this.$router.push('/');
    window.location.href = '';
  }

  public openLogin(): void {
    this.loginService().openLogin((<any>this).$root);
  }

  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public hasAnyAuthority(authorities: any): boolean {
    return this.accountService().hasAnyAuthority(authorities);
  }

  public get swaggerEnabled(): boolean {
    return this.$store.getters.activeProfiles.indexOf('swagger') > -1;
  }

  public get inProduction(): boolean {
    return this.$store.getters.activeProfiles.indexOf('prod') > -1;
  }
}
