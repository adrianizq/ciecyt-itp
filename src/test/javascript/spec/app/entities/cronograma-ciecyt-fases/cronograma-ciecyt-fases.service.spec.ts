/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_FORMAT } from '@/shared/date/filters';
import CronogramaCiecytFasesService from '@/entities/cronograma-ciecyt-fases/cronograma-ciecyt-fases.service';
import { CronogramaCiecytFases } from '@/shared/model/cronograma-ciecyt-fases.model';

const mockedAxios: any = axios;
jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn()
}));

describe('Service Tests', () => {
  describe('CronogramaCiecytFases Service', () => {
    let service: CronogramaCiecytFasesService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new CronogramaCiecytFasesService();
      currentDate = new Date();

      elemDefault = new CronogramaCiecytFases(0, currentDate, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            inicioFase: format(currentDate, DATE_FORMAT),
            finFase: format(currentDate, DATE_FORMAT)
          },
          elemDefault
        );
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });
      it('should create a CronogramaCiecytFases', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            inicioFase: format(currentDate, DATE_FORMAT),
            finFase: format(currentDate, DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            inicioFase: currentDate,
            finFase: currentDate
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should update a CronogramaCiecytFases', async () => {
        const returnedFromService = Object.assign(
          {
            inicioFase: format(currentDate, DATE_FORMAT),
            finFase: format(currentDate, DATE_FORMAT),
            textoExplicativo: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            inicioFase: currentDate,
            finFase: currentDate
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });
      it('should return a list of CronogramaCiecytFases', async () => {
        const returnedFromService = Object.assign(
          {
            inicioFase: format(currentDate, DATE_FORMAT),
            finFase: format(currentDate, DATE_FORMAT),
            textoExplicativo: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            inicioFase: currentDate,
            finFase: currentDate
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });
      it('should delete a CronogramaCiecytFases', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });
    });
  });
});
