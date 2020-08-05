/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_FORMAT } from '@/shared/date/filters';
import CronogramaService from '@/entities/cronograma/cronograma.service';
import { Cronograma } from '@/shared/model/cronograma.model';

const mockedAxios: any = axios;
jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn()
}));

describe('Service Tests', () => {
  describe('Cronograma Service', () => {
    let service: CronogramaService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new CronogramaService();
      currentDate = new Date();

      elemDefault = new Cronograma(0, 'AAAAAAA', 0, currentDate, currentDate, 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            fechaInicio: format(currentDate, DATE_FORMAT),
            fechaFin: format(currentDate, DATE_FORMAT)
          },
          elemDefault
        );
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });
      it('should create a Cronograma', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaInicio: format(currentDate, DATE_FORMAT),
            fechaFin: format(currentDate, DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaInicio: currentDate,
            fechaFin: currentDate
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should update a Cronograma', async () => {
        const returnedFromService = Object.assign(
          {
            actividad: 'BBBBBB',
            duracion: 1,
            fechaInicio: format(currentDate, DATE_FORMAT),
            fechaFin: format(currentDate, DATE_FORMAT),
            ordenVista: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaInicio: currentDate,
            fechaFin: currentDate
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });
      it('should return a list of Cronograma', async () => {
        const returnedFromService = Object.assign(
          {
            actividad: 'BBBBBB',
            duracion: 1,
            fechaInicio: format(currentDate, DATE_FORMAT),
            fechaFin: format(currentDate, DATE_FORMAT),
            ordenVista: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaInicio: currentDate,
            fechaFin: currentDate
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });
      it('should delete a Cronograma', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });
    });
  });
});
