import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IMenu, MenuBar, MenuChildren } from '@/shared/model/menu.model';

const baseApiUrl = 'api/menus';

export default class MenuService {
  /* public all(): Promise<MenuBar[]> {
    return new Promise<MenuBar[]>(resolve => {
      const paginationQuery = {
        page: 0,
        size: 100,
        sort: ['id,asc'],
      };*/

  public all(): Promise<MenuBar[]> {
    return new Promise<MenuBar[]>(resolve => {
      const paginationQuery = {
        page: 0,
        size: 100,
        sort: ['id,asc'],
      };
      this.retrieveNoPage().then(res => {
        const menus: IMenu[] = res.data;
        const parent: MenuBar[] = [];
        menus.map(menu => {
          if (!menu.menuPadreId) {
            delete menu.menuPadreNombre;
            const children: MenuChildren[] = [];
            parent.push({
              ...menu,
            });
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
        resolve(parent);
      });
    });
  }
  ///
  public ciecyt(): Promise<MenuBar[]> {
    return new Promise<MenuBar[]>(resolve => {
      this.retrieveMenusRol('CIECYT').then(res => {
        const menusCiecyt: IMenu[] = res.data;
        const parent: MenuBar[] = [];
        menusCiecyt.map(menusCiecyt => {
          if (!menusCiecyt.menuPadreId) {
            delete menusCiecyt.menuPadreNombre;
            const children: MenuChildren[] = [];
            parent.push({
              ...menusCiecyt,
            });
          }
        });
        parent.map(par => {
          par.children = [];
          menusCiecyt.map(menusCiecyt => {
            if (menusCiecyt.menuPadreId === par.id) {
              par.children.push(menusCiecyt);
            }
          });
        });
        resolve(parent);
      });
    });
  }
  //////////////
  public jurado(): Promise<MenuBar[]> {
    return new Promise<MenuBar[]>(resolve => {
      this.retrieveMenusRol('JURADO').then(res => {
        const menusJurado: IMenu[] = res.data;
        const parent: MenuBar[] = [];
        menusJurado.map(menuJurado => {
          if (!menuJurado.menuPadreId) {
            delete menuJurado.menuPadreNombre;
            const children: MenuChildren[] = [];
            parent.push({
              ...menuJurado,
            });
          }
        });
        parent.map(par => {
          par.children = [];
          menusJurado.map(menuJurado => {
            if (menuJurado.menuPadreId === par.id) {
              par.children.push(menuJurado);
            }
          });
        });
        resolve(parent);
      });
    });
  }

  //////////////
  public asesor(): Promise<MenuBar[]> {
    return new Promise<MenuBar[]>(resolve => {
      this.retrieveMenusRol('ASESOR').then(res => {
        const menusAsesor: IMenu[] = res.data;
        const parent: MenuBar[] = [];
        menusAsesor.map(menusAsesor => {
          if (!menusAsesor.menuPadreId) {
            delete menusAsesor.menuPadreNombre;
            const children: MenuChildren[] = [];
            parent.push({
              ...menusAsesor,
            });
          }
        });
        parent.map(par => {
          par.children = [];
          menusAsesor.map(menusAsesor => {
            if (menusAsesor.menuPadreId === par.id) {
              par.children.push(menusAsesor);
            }
          });
        });
        resolve(parent);
      });
    });
  }

  public find(id: number): Promise<IMenu> {
    return new Promise<IMenu>(resolve => {
      axios.get(`${baseApiUrl}/${id}`).then(function (res) {
        resolve(res.data);
      });
    });
  }

  public retrieve(paginationQuery?: any): Promise<any> {
    return new Promise<any>(resolve => {
      axios.get(baseApiUrl + `?${buildPaginationQueryOpts(paginationQuery)}`).then(function (res) {
        resolve(res);
      });
    });
  }
  public retrieveNoPage(): Promise<any> {
    return new Promise<any>(resolve => {
      axios.get(`api/menus-no-page`).then(function (res) {
        resolve(res);
      });
    });
  }

  public retrieveMenusRol(rol?: any): Promise<any> {
    return new Promise<any>(resolve => {
      axios.get(`api/menus-rol/${rol}`).then(function (res) {
        resolve(res);
      });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>(resolve => {
      axios.delete(`${baseApiUrl}/${id}`).then(function (res) {
        resolve(res);
      });
    });
  }

  public create(entity: IMenu): Promise<IMenu> {
    return new Promise<IMenu>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function (res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IMenu): Promise<IMenu> {
    return new Promise<IMenu>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function (res) {
        resolve(res.data);
      });
    });
  }
}
