import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IIntegranteProyecto } from '@/shared/model/integrante-proyecto.model';

const baseApiUrl = 'api/integrante-proyectos';

export default class IntegranteProyectoService {
  public find(id: number): Promise<IIntegranteProyecto> {
    return new Promise<IIntegranteProyecto>(resolve => {
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

  public create(entity: IIntegranteProyecto): Promise<IIntegranteProyecto> {
    return new Promise<IIntegranteProyecto>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IIntegranteProyecto): Promise<IIntegranteProyecto> {
    return new Promise<IIntegranteProyecto>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
