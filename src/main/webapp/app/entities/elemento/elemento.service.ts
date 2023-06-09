import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IElemento } from '@/shared/model/elemento.model';

const baseApiUrl = 'api/elementos';

export default class ElementoService {
  public find(id: number): Promise<IElemento> {
    return new Promise<IElemento>(resolve => {
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

  public create(entity: IElemento): Promise<IElemento> {
    return new Promise<IElemento>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function (res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IElemento): Promise<IElemento> {
    return new Promise<IElemento>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function (res) {
        resolve(res.data);
      });
    });
  }

  public retrieveElementosModalidad(id: number, paginationQuery?: any): Promise<any> {
    return new Promise<any>(resolve => {
      axios.get('api/elemento-modalidad' + `/${id}` + `?${buildPaginationQueryOpts(paginationQuery)}`).then(function (res) {
        resolve(res);
      });
    });
  }
}
