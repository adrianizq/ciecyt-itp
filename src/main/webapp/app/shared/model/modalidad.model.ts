import { ICicloPropedeutico } from '@/shared/model/ciclo-propedeutico.model';

export interface IModalidad {
  id?: number;
  modalidad?: string;
  modalidadCicloPropedeuticos?: ICicloPropedeutico[];
  modalidadAcuerdoAcuerdo?: string;
  modalidadAcuerdoId?: number;
}

export class Modalidad implements IModalidad {
  constructor(
    public id?: number,
    public modalidad?: string,
    public modalidadCicloPropedeuticos?: ICicloPropedeutico[],
    public modalidadAcuerdoAcuerdo?: string,
    public modalidadAcuerdoId?: number
  ) {}
}
