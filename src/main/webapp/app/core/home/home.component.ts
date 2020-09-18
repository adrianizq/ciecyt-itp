import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import LoginService from '@/account/login.service';

@Component
export default class Home extends Vue {
  @Inject('loginService')
  private loginService: () => LoginService;

  public autoridadElegida: any = null;

  public openLogin(): void {
    this.loginService().openLogin((<any>this).$root);
  }

  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public get username(): string {
    return this.$store.getters.account ? this.$store.getters.account.login : '';
  }

  public get authorities(): string {
    console.log(this.$store.getters.account);
    return this.$store.getters.account ? this.$store.getters.account.authorities : '';
  }

  public setRuta(valor) {
    this.autoridadElegida = valor;
    console.log(valor);
  }

  public entrar() {
    //this.$router.push({ name: 'PropuestaPresupuestoView',params:{ proyectoId: this.proyId}});
    if (this.autoridadElegida == 'ROLE_JURADO') {
      this.$router.push('/viabilidad-propuesta/listado');
    }
    if (this.autoridadElegida == 'ROLE_ASESOR') {
      this.$router.push('/viabilidad-propuesta/listado-asesor');
    }
  }
}
