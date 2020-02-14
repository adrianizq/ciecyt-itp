export interface IAdjuntoProyectoFase {
  id?: number;
  nombreAdjunto?: string;
  fechaCreacion?: Date;
  fechaModificacion?: Date;
  estadoAdjunto?: number;
  adjuntoProyectoFase?: string;
  nombreArchivoOriginal?: string;
  fechaInicio?: Date;
  fechaFin?: Date;
  adjuntoProyectoFaseProyectoFaseTitulo?: string;
  adjuntoProyectoFaseProyectoFaseId?: number;
}

export class AdjuntoProyectoFase implements IAdjuntoProyectoFase {
  constructor(
    public id?: number,
    public nombreAdjunto?: string,
    public fechaCreacion?: Date,
    public fechaModificacion?: Date,
    public estadoAdjunto?: number,
    public adjuntoProyectoFase?: string,
    public nombreArchivoOriginal?: string,
    public fechaInicio?: Date,
    public fechaFin?: Date,
    public adjuntoProyectoFaseProyectoFaseTitulo?: string,
    public adjuntoProyectoFaseProyectoFaseId?: number
  ) {}
}
