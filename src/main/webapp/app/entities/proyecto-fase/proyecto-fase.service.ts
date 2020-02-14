import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IProyectoFase } from '@/shared/model/proyecto-fase.model';

const baseApiUrl = 'api/proyecto-fases';

export default class ProyectoFaseService {
  public find(id: number): Promise<IProyectoFase> {
    return new Promise<IProyectoFase>(resolve => {
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

  public create(entity: IProyectoFase): Promise<IProyectoFase> {
    return new Promise<IProyectoFase>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IProyectoFase): Promise<IProyectoFase> {
    return new Promise<IProyectoFase>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
