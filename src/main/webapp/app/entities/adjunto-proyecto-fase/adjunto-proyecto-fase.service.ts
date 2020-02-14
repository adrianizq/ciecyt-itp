import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IAdjuntoProyectoFase } from '@/shared/model/adjunto-proyecto-fase.model';

const baseApiUrl = 'api/adjunto-proyecto-fases';

export default class AdjuntoProyectoFaseService {
  public find(id: number): Promise<IAdjuntoProyectoFase> {
    return new Promise<IAdjuntoProyectoFase>(resolve => {
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

  public create(entity: IAdjuntoProyectoFase): Promise<IAdjuntoProyectoFase> {
    return new Promise<IAdjuntoProyectoFase>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IAdjuntoProyectoFase): Promise<IAdjuntoProyectoFase> {
    return new Promise<IAdjuntoProyectoFase>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
