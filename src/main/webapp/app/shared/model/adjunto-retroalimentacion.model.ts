export interface IAdjuntoRetroalimentacion {
  id?: number;
  nombreAdjunto?: string;
  fechaCreacion?: Date;
  fechaModificacion?: Date;
  estadoAdjunto?: number;
  adjuntoRetroalimentacion?: string;
  nombreArchivoOriginal?: string;
  authority?: string;
  fechaInicio?: Date;
  fechaFin?: Date;
  archivo?: any;
  file?: any;
  archivoContentType?: string;
  adjuntoRetroalimentacionFaseId?: number;
  adjuntoRetroalimentacionProyectoId?: number;
}

export class AdjuntoRetroalimentacion implements IAdjuntoRetroalimentacion {
  constructor(
    public id?: number,
    public nombreAdjunto?: string,
    public fechaCreacion?: Date,
    public fechaModificacion?: Date,
    public estadoAdjunto?: number,
    public adjuntoRetroalimentacion?: string,
    public nombreArchivoOriginal?: string,
    public authority?: string,
    public fechaInicio?: Date,
    public fechaFin?: Date,
    public archivo?: any,
    public file?: any,
    public archivoContentType?: string,
    public adjuntoRetroalimentacionFaseId?: number,
    public adjuntoRetroalimentacionProyectoId?: number
  ) {}
}
