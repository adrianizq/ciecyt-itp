import axios from 'axios';

import { IInformacionPasantia } from '@/shared/model/informacion-pasantia.model';

const baseApiUrl = 'api/informacion-pasantias';

export default class InformacionPasantiaService {
  public find(id: number): Promise<IInformacionPasantia> {
    return new Promise<IInformacionPasantia>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IInformacionPasantia): Promise<IInformacionPasantia> {
    return new Promise<IInformacionPasantia>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: IInformacionPasantia): Promise<IInformacionPasantia> {
    return new Promise<IInformacionPasantia>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
