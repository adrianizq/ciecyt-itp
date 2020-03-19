import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IRolMenu } from '@/shared/model/rol-menu.model';

const baseApiUrl = 'api/rol-menus';

export default class RolMenuService {
  public find(id: number): Promise<IRolMenu> {
    return new Promise<IRolMenu>(resolve => {
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

  public create(entity: IRolMenu): Promise<IRolMenu> {
    return new Promise<IRolMenu>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IRolMenu): Promise<IRolMenu> {
    return new Promise<IRolMenu>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
