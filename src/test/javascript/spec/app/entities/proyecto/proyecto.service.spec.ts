/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_FORMAT } from '@/shared/date/filters';
import ProyectoService from '@/entities/proyecto/proyecto.service';
import { Proyecto } from '@/shared/model/proyecto.model';

const mockedAxios: any = axios;
const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn(),
}));

describe('Service Tests', () => {
  describe('Proyecto Service', () => {
    let service: ProyectoService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new ProyectoService();
      currentDate = new Date();

      elemDefault = new Proyecto(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            fechaIni: format(currentDate, DATE_FORMAT),
            fechaFin: format(currentDate, DATE_FORMAT),
          },
          elemDefault
        );
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a Proyecto', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaIni: format(currentDate, DATE_FORMAT),
            fechaFin: format(currentDate, DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaIni: currentDate,
            fechaFin: currentDate,
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Proyecto', async () => {
        mockedAxios.post.mockReturnValue(Promise.reject(error));

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Proyecto', async () => {
        const returnedFromService = Object.assign(
          {
            titulo: 'BBBBBB',
            url: 'BBBBBB',
            lugarEjecucion: 'BBBBBB',
            duracion: 'BBBBBB',
            fechaIni: format(currentDate, DATE_FORMAT),
            fechaFin: format(currentDate, DATE_FORMAT),
            contrapartidaPesos: 1,
            contrapartidaEspecie: 1,
            palabrasClave: 'BBBBBB',
            convocatoria: 'BBBBBB',
            tipo: 'BBBBBB',
            referencias: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaIni: currentDate,
            fechaFin: currentDate,
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Proyecto', async () => {
        mockedAxios.put.mockReturnValue(Promise.reject(error));

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Proyecto', async () => {
        const returnedFromService = Object.assign(
          {
            titulo: 'BBBBBB',
            url: 'BBBBBB',
            lugarEjecucion: 'BBBBBB',
            duracion: 'BBBBBB',
            fechaIni: format(currentDate, DATE_FORMAT),
            fechaFin: format(currentDate, DATE_FORMAT),
            contrapartidaPesos: 1,
            contrapartidaEspecie: 1,
            palabrasClave: 'BBBBBB',
            convocatoria: 'BBBBBB',
            tipo: 'BBBBBB',
            referencias: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaIni: currentDate,
            fechaFin: currentDate,
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Proyecto', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Proyecto', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Proyecto', async () => {
        mockedAxios.delete.mockReturnValue(Promise.reject(error));

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
