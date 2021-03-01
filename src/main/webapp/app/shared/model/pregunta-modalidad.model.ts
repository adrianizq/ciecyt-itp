export interface IPreguntaModalidad {
  id?: number;
  preguntaId?: number;
  modalidadId?: number;
}

export class PreguntaModalidad implements IPreguntaModalidad {
  constructor(public id?: number, public preguntaId?: number, public modalidadId?: number) {}
}
