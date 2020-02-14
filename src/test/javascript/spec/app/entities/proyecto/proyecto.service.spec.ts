/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_FORMAT } from '@/shared/date/filters';
import ProyectoService from '@/entities/proyecto/proyecto.service';
import { Proyecto } from '@/shared/model/proyecto.model';

const mockedAxios: any = axios;
jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn()
}));

describe('Service Tests', () => {
  describe('Proyecto Service', () => {
    let service: ProyectoService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new ProyectoService();
      currentDate = new Date();

      elemDefault = new Proyecto(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, currentDate, 0, 0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            fechaIni: format(currentDate, DATE_FORMAT),
            fechaFin: format(currentDate, DATE_FORMAT)
          },
          elemDefault
        );
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });
      it('should create a Proyecto', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaIni: format(currentDate, DATE_FORMAT),
            fechaFin: format(currentDate, DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaIni: currentDate,
            fechaFin: currentDate
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
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
            convocatoria: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaIni: currentDate,
            fechaFin: currentDate
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
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
            convocatoria: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaIni: currentDate,
            fechaFin: currentDate
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });
      it('should delete a Proyecto', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });
    });
  });
});
