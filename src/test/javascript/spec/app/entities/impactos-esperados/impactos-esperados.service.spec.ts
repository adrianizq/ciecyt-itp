/* tslint:disable max-line-length */
import axios from 'axios';

import * as config from '@/shared/config/config';
import {} from '@/shared/date/filters';
import ImpactosEsperadosService from '@/entities/impactos-esperados/impactos-esperados.service';
import { ImpactosEsperados } from '@/shared/model/impactos-esperados.model';

const mockedAxios: any = axios;
jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn()
}));

describe('Service Tests', () => {
  describe('ImpactosEsperados Service', () => {
    let service: ImpactosEsperadosService;
    let elemDefault;
    beforeEach(() => {
      service = new ImpactosEsperadosService();

      elemDefault = new ImpactosEsperados(0, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });
      it('should create a ImpactosEsperados', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should update a ImpactosEsperados', async () => {
        const returnedFromService = Object.assign(
          {
            impacto: 'BBBBBB',
            plazo: 1,
            indicador: 'BBBBBB',
            supuestos: 'BBBBBB',
            ordenVista: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });
      it('should return a list of ImpactosEsperados', async () => {
        const returnedFromService = Object.assign(
          {
            impacto: 'BBBBBB',
            plazo: 1,
            indicador: 'BBBBBB',
            supuestos: 'BBBBBB',
            ordenVista: 1
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });
      it('should delete a ImpactosEsperados', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });
    });
  });
});
