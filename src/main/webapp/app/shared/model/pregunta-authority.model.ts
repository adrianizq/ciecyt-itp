export interface IPreguntaModalidad {
  id?: number;
  pregunta3Id?: number;
  authorityName?: number;
}

export class PreguntaModalidad implements IPreguntaModalidad {
  constructor(public id?: number, public pregunta3Id?: number, public authorityName?: number) {}
}
