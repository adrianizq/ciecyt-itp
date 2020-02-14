export interface IEntidadFinanciadora {
  id?: number;
  valor?: number;
  aprobada?: boolean;
  entidadFinanciadoraEntidadEntidad?: string;
  entidadFinanciadoraEntidadId?: number;
}

export class EntidadFinanciadora implements IEntidadFinanciadora {
  constructor(
    public id?: number,
    public valor?: number,
    public aprobada?: boolean,
    public entidadFinanciadoraEntidadEntidad?: string,
    public entidadFinanciadoraEntidadId?: number
  ) {
    this.aprobada = this.aprobada || false;
  }
}
