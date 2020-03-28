import { Component, Vue, Inject } from 'vue-property-decorator';

import RolMenuService from '@/entities/rol-menu/rol-menu.service';
import MenuService from '@/entities/menu/menu.service';
import { MenuBar } from '@/shared/model/menu.model';
import { IRolMenu } from '@/shared/model/rol-menu.model';

@Component
export default class RolMenu extends Vue {
  @Inject('rolMenuService') private rolMenuService: () => RolMenuService;
  @Inject('menuService') private menuService: () => MenuService;

  menus: MenuBar[] = [];
  rolMenu: IRolMenu[] = [];

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.rol) {
        vm.retrieveRolMenu(to.params.rol);
      }
    });
  }

  created() {
    this.menuService()
      .all()
      .then(res => {
        res.map(menu => {
          if (!this.rolMenu[menu.id]) {
            this.rolMenu[menu.id] = {
              permitirAcceso: false,
              permitirCrear: false,
              permitirEditar: false,
              permitirEliminar: false,
              rolMenuMenuId: menu.id,
              authName: this.rolName
            };
          }

          menu.children.map(submenu => {
            if (!this.rolMenu[submenu.id]) {
              this.rolMenu[submenu.id] = {
                permitirAcceso: false,
                permitirCrear: false,
                permitirEditar: false,
                permitirEliminar: false,
                rolMenuMenuId: submenu.id,
                authName: this.rolName
              };
            }
          });
        });

        this.menus = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public retrieveRolMenu(rol) {
    this.rolMenuService()
      .fromRol(rol)
      .then(res => {
        this.rolMenu = [...this.rolMenu, ...res];
      });
  }

  get rolName() {
    return this.$route.params.rol;
  }

  public selectAllSubmenu(menu, action) {
    let newState: boolean;

    switch (action) {
      case 'acceso': {
        newState = this.rolMenu[menu.id].permitirAcceso;
        break;
      }
      case 'crear': {
        newState = this.rolMenu[menu.id].permitirCrear;
        break;
      }
      case 'editar': {
        newState = this.rolMenu[menu.id].permitirEditar;
        break;
      }
      case 'eliminar': {
        newState = this.rolMenu[menu.id].permitirEliminar;
        break;
      }
    }

    menu.children.map(submenu => {
      switch (action) {
        case 'acceso': {
          this.rolMenu[submenu.id].permitirAcceso = newState;
          break;
        }
        case 'crear': {
          this.rolMenu[submenu.id].permitirCrear = newState;
          break;
        }
        case 'editar': {
          this.rolMenu[submenu.id].permitirEditar = newState;
          break;
        }
        case 'eliminar': {
          this.rolMenu[submenu.id].permitirEliminar = newState;
          break;
        }
      }
    });
  }
}
