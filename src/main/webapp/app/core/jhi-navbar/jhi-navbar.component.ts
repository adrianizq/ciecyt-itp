import { Component, Inject, Vue } from 'vue-property-decorator';
import { VERSION } from '@/constants';
import LoginService from '@/account/login.service';
import AccountService from '@/account/account.service';
import TranslationService from '@/locale/translation.service';

import MenuService from '@/entities/menu/menu.service';
import { IMenu } from '@/shared/model/menu.model';

@Component
export default class JhiNavbar extends Vue {
  @Inject('loginService')
  private loginService: () => LoginService;
  @Inject('translationService') private translationService: () => TranslationService;

  @Inject('accountService') private accountService: () => AccountService;

  @Inject('menuService')
  private menuService: () => MenuService;

  public version = VERSION ? 'v' + VERSION : '';
  private currentLanguage = this.$store.getters.currentLanguage;
  private languages: any = this.$store.getters.languages;
  public menus: IMenu[] = [];

  created() {
    const paginationQuery = {
      page: 0,
      size: 50,
      sort: ['id,asc']
    };
    this.menuService()
      .retrieve(paginationQuery)
      .then(res => {
        const menus: IMenu[] = res.data;

        const parent = [];

        menus.map(menu => {
          if (!menu.menuPadreId) {
            parent.push(menu);
          }
        });

        parent.map(par => {
          par.children = [];

          menus.map(menu => {
            if (menu.menuPadreId === par.id) {
              par.children.push(menu);
            }
          });
        });

        this.menus = parent;
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
