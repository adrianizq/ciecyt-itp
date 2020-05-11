import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IMenu, MenuBar, MenuChildren } from '@/shared/model/menu.model';

const baseApiUrl = 'api/menus';

export default class MenuService {
  public all(): Promise<MenuBar[]> {
    return new Promise<MenuBar[]>(resolve => {
      const paginationQuery = {
        page: 0,
        size: 100,
        sort: ['id,asc']
      };

      this.retrieve(paginationQuery).then(res => {
        const menus: IMenu[] = res.data;

        const parent: MenuBar[] = [];

        menus.map(menu => {
          if (!menu.menuPadreId) {
            delete menu.menuPadreNombre;

            const children: MenuChildren[] = [];
            parent.push({
              ...menu
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

    /*return new Promise<IMenu>(resolve => {
      axios.get('api/menus-padre').then(res => {
        resolve(res.data);
      });
    });*/
  }

  public find(id: number): Promise<IMenu> {
    return new Promise<IMenu>(resolve => {
      axios.get(`${baseApiUrl}/${id}`).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public retrieve(paginationQuery?: any): Promise<any> {
    return new Promise<any>(resolve => {
      axios.get(baseApiUrl + `?${buildPaginationQueryOpts(paginationQuery)}`).then(function(res) {
        resolve(res);
      });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>(resolve => {
      axios.delete(`${baseApiUrl}/${id}`).then(function(res) {
        resolve(res);
      });
    });
  }

  public create(entity: IMenu): Promise<IMenu> {
    return new Promise<IMenu>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IMenu): Promise<IMenu> {
    return new Promise<IMenu>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
