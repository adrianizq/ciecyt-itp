export const enum EnumRespuestas {
  CUMPLE = 'CUMPLE',
  NO_CUMPLE = 'NO_CUMPLE',
  NO_APLICA = 'NO_APLICA',
}

export interface IProyectoRespuestas {
  id?: number;
  respuesta?: EnumRespuestas;
  observaciones?: string;
  viable?: boolean;
  proyectoRespuestasPreguntaPregunta?: string;
  proyectoRespuestasPreguntaId?: number;
  proyectoRespuestasProyectoTitulo?: string;
  proyectoRespuestasProyectoId?: number;
  elemento?: string;
  dato?: string;
}

export class ProyectoRespuestas implements IProyectoRespuestas {
  constructor(
    public id?: number,
    public respuesta?: EnumRespuestas,
    public observaciones?: string,
    public viable?: boolean,
    public proyectoRespuestasPreguntaPregunta?: string,
    public proyectoRespuestasPreguntaId?: number,
    public proyectoRespuestasProyectoTitulo?: string,
    public proyectoRespuestasProyectoId?: number,
    public elemento?: string,
    public dato?: string
  ) {
    this.viable = this.viable || false;
  }
}
