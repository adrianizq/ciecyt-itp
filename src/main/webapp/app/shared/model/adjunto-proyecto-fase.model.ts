export interface IAdjuntoProyectoFase {
  id?: number;
  nombreAdjunto?: string;
  fechaCreacion?: Date;
  fechaModificacion?: Date;
  estadoAdjunto?: number;
  adjuntoProyectoFase?: string;
  nombreArchivoOriginal?: string;
  archivo?: any;
  archivoContentType?: string;
  fechaInicio?: Date;
  fechaFin?: Date;
  adjuntoProyectoFaseFaseFase?: string;
  adjuntoProyectoFaseFaseId?: number;
  proyectoFaseProyectoId?: number;
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
    public archivo?: any,
    public archivoContentType?: string,
    public fechaInicio?: Date,
    public fechaFin?: Date,
    public adjuntoProyectoFaseFaseFase?: string,
    public adjuntoProyectoFaseFaseId?: number,
    public proyectoFaseProyectoId?: number
  ) {}
}
