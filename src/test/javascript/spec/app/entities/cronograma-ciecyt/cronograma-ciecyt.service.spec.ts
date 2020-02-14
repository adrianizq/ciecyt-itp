/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_FORMAT } from '@/shared/date/filters';
import CronogramaCiecytService from '@/entities/cronograma-ciecyt/cronograma-ciecyt.service';
import { CronogramaCiecyt } from '@/shared/model/cronograma-ciecyt.model';

const mockedAxios: any = axios;
jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn()
}));

describe('Service Tests', () => {
  describe('CronogramaCiecyt Service', () => {
    let service: CronogramaCiecytService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new CronogramaCiecytService();
      currentDate = new Date();

      elemDefault = new CronogramaCiecyt(0, 'AAAAAAA', currentDate, currentDate, 'AAAAAAA');
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
      it('should create a CronogramaCiecyt', async () => {
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

      it('should update a CronogramaCiecyt', async () => {
        const returnedFromService = Object.assign(
          {
            tituloCronograma: 'BBBBBB',
            fechaInicio: format(currentDate, DATE_FORMAT),
            fechaFin: format(currentDate, DATE_FORMAT),
            observaciones: 'BBBBBB'
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
      it('should return a list of CronogramaCiecyt', async () => {
        const returnedFromService = Object.assign(
          {
            tituloCronograma: 'BBBBBB',
            fechaInicio: format(currentDate, DATE_FORMAT),
            fechaFin: format(currentDate, DATE_FORMAT),
            observaciones: 'BBBBBB'
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
      it('should delete a CronogramaCiecyt', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });
    });
  });
});
