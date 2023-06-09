import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IEntidad } from '@/shared/model/entidad.model';

const baseApiUrl = 'api/entidads';

export default class EntidadService {
  public find(id: number): Promise<IEntidad> {
    return new Promise<IEntidad>(resolve => {
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

  public delete(id: number): Promise<any> {
    return new Promise<any>(resolve => {
      axios.delete(`${baseApiUrl}/${id}`).then(function (res) {
        resolve(res);
      });
    });
  }

  public create(entity: IEntidad): Promise<IEntidad> {
    return new Promise<IEntidad>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function (res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IEntidad): Promise<IEntidad> {
    return new Promise<IEntidad>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function (res) {
        resolve(res.data);
      });
    });
  }
}
