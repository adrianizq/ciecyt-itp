import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IAdjuntoRetroalimentacion } from '@/shared/model/adjunto-retroalimentacion.model';

const baseApiUrl = 'api/adjunto-retroalimentacions';

export default class AdjuntoRetroalimentacionService {
  public find(id: number): Promise<IAdjuntoRetroalimentacion> {
    return new Promise<IAdjuntoRetroalimentacion>(resolve => {
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

  public create(entity: IAdjuntoRetroalimentacion): Promise<IAdjuntoRetroalimentacion> {
    return new Promise<IAdjuntoRetroalimentacion>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IAdjuntoRetroalimentacion): Promise<IAdjuntoRetroalimentacion> {
    return new Promise<IAdjuntoRetroalimentacion>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
