export interface IPregunta {
  id?: number;
  encabezado?: string;
  descripcion?: string;
  preguntaElemento?: string;
  preguntaElementoId?: number;
  pregunta?: string;
  preguntaTipoPreguntaTipoPregunta?: string;
  preguntaTipoPreguntaId?: number;
  preguntaModalidadModalidad?: string;
  preguntaModalidadId?: number;
  preguntaRolesModalidadRol?: string;
  preguntaRolesModalidadId?: number;
  puntajeMaximo?: number;
}

export class Pregunta implements IPregunta {
  constructor(
    public id?: number,
    public encabezado?: string,
    public descripcion?: string,
    public preguntaElemento?: string,
    public preguntaElementoId?: number,
    public pregunta?: string,
    public preguntaTipoPreguntaTipoPregunta?: string,
    public preguntaTipoPreguntaId?: number,
    public preguntaModalidadModalidad?: string,
    public preguntaModalidadId?: number,
    public preguntaRolesModalidadRol?: string,
    public preguntaRolesModalidadId?: number,
    public puntajeMaximo?: number
  ) {}
}
