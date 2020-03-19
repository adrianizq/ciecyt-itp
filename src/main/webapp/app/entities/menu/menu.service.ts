import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IMenu } from '@/shared/model/menu.model';

const baseApiUrl = 'api/menus';

export default class MenuService {
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
