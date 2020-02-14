/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_FORMAT } from '@/shared/date/filters';
import ProyectoFaseService from '@/entities/proyecto-fase/proyecto-fase.service';
import { ProyectoFase } from '@/shared/model/proyecto-fase.model';

const mockedAxios: any = axios;
jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn()
}));

describe('Service Tests', () => {
  describe('ProyectoFase Service', () => {
    let service: ProyectoFaseService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new ProyectoFaseService();
      currentDate = new Date();

      elemDefault = new ProyectoFase(0, 'AAAAAAA', false, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            fechaCumplimiento: format(currentDate, DATE_FORMAT)
          },
          elemDefault
        );
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });
      it('should create a ProyectoFase', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaCumplimiento: format(currentDate, DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaCumplimiento: currentDate
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should update a ProyectoFase', async () => {
        const returnedFromService = Object.assign(
          {
            titulo: 'BBBBBB',
            cumplida: true,
            fechaCumplimiento: format(currentDate, DATE_FORMAT),
            observaciones: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaCumplimiento: currentDate
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });
      it('should return a list of ProyectoFase', async () => {
        const returnedFromService = Object.assign(
          {
            titulo: 'BBBBBB',
            cumplida: true,
            fechaCumplimiento: format(currentDate, DATE_FORMAT),
            observaciones: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaCumplimiento: currentDate
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });
      it('should delete a ProyectoFase', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });
    });
  });
});
