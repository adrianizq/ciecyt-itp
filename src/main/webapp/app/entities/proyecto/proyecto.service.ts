import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IProyecto } from '@/shared/model/proyecto.model';

const baseApiUrl = 'api/proyectos';

export default class ProyectoService {
  //public proyectoId: number;

  public find(id: number): Promise<IProyecto> {
    return new Promise<IProyecto>(resolve => {
      axios.get(`${baseApiUrl}/${id}`).then(function (res) {
        resolve(res.data);
      });
    });
  }

  public retrieveWithAsesor(idProy?: any, paginationQuery?: any): Promise<any> {
    return new Promise<any>(resolve => {
      axios.get('api/proyectosWithAsesor' + `/${idProy}` + `?${buildPaginationQueryOpts(paginationQuery)}`).then(function (res) {
        resolve(res);
      });
    });
  }

  public retrieveProyectoIntegrante(idUsuario?: any, paginationQuery?: any): Promise<any> {
    return new Promise<any>(resolve => {
      axios.get('api/proyectos-integrante' + `/${idUsuario}` + `?${buildPaginationQueryOpts(paginationQuery)}`).then(function (res) {
        resolve(res);
      });
    });
  }

  /* public findProyectoIntegrante(idProyecto?: any, paginationQuery?: any): Promise<any> {
    return new Promise<any>(resolve => {
      axios.get('api/proyectoIntegrantes' + `/${idProyecto}` + `?${buildPaginationQueryOpts(paginationQuery)}`).then(function (res) {
        resolve(res);
      });
    });
  }*/
  //proyectosIntegrantes

  public retrieveProyectoIntegranteAuthority(idUsuario?: any, authority?: any, paginationQuery?: any): Promise<any> {
    return new Promise<any>(resolve => {
      axios
        .get('api/proyectos-integrante' + `/${idUsuario}` + `/${authority}` + `?${buildPaginationQueryOpts(paginationQuery)}`)
        .then(function (res) {
          resolve(res);
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

  //recupera los proyectos con una lista de integrantes (diferente a la anterior )
  public retrieveAllProyectosIntegrantes(paginationQuery?: any): Promise<any> {
    return new Promise<any>(resolve => {
      axios.get('api/proyectosIntegrantes' + `?${buildPaginationQueryOpts(paginationQuery)}`).then(function (res) {
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

  public create(entity: IProyecto): Promise<IProyecto> {
    return new Promise<IProyecto>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function (res) {
        resolve(res.data);
        //this.proyectoId = entity.id;
        //this.proyectoId = 20;
      });
    });
  }

  public update(entity: IProyecto): Promise<IProyecto> {
    return new Promise<IProyecto>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function (res) {
        resolve(res.data);
      });
    });
  }
}
