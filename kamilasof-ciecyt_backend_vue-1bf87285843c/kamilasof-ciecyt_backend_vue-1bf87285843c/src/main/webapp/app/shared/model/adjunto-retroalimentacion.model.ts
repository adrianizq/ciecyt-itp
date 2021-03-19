export interface IAdjuntoRetroalimentacion {
  id?: number;
  nombreAdjunto?: string;
  fechaCreacion?: Date;
  fechaModificacion?: Date;
  estadoAdjunto?: number;
  adjuntoRetroalimentacion?: string;
  nombreArchivoOriginal?: string;
  fechaInicio?: Date;
  fechaFin?: Date;
  adjuntoRetroalimentacionRetroalimentacionTitulo?: string;
  adjuntoRetroalimentacionRetroalimentacionId?: number;
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
    public fechaInicio?: Date,
    public fechaFin?: Date,
    public adjuntoRetroalimentacionRetroalimentacionTitulo?: string,
    public adjuntoRetroalimentacionRetroalimentacionId?: number
  ) {}
}
