import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IFases } from '@/shared/model/fases.model';

const baseApiUrl = 'api/fases';

export default class FasesService {
  public find(id: number): Promise<IFases> {
    return new Promise<IFases>(resolve => {
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

  public create(entity: IFases): Promise<IFases> {
    return new Promise<IFases>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function (res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IFases): Promise<IFases> {
    return new Promise<IFases>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function (res) {
        resolve(res.data);
      });
    });
  }
}
