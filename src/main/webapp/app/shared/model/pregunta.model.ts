export interface IPregunta {
  id?: number;
  encabezado?: string;
  descripcion?: string;
  elemento?: string;
  elementoId?: number;
  pregunta?: string;
  preguntaTipoPreguntaTipoPregunta?: string;
  preguntaTipoPreguntaId?: number;
  preguntaModalidadModalidad?: string;
  preguntaModalidadId?: number;
  preguntaRolesModalidadRol?: string;
  preguntaRolesModalidadId?: number;
}

export class Pregunta implements IPregunta {
  constructor(
    public id?: number,
    public encabezado?: string,
    public descripcion?: string,
    public elemento?: string,
    public elementoId?: number,
    public pregunta?: string,
    public preguntaTipoPreguntaTipoPregunta?: string,
    public preguntaTipoPreguntaId?: number,
    public preguntaModalidadModalidad?: string,
    public preguntaModalidadId?: number,
    public preguntaRolesModalidadRol?: string,
    public preguntaRolesModalidadId?: number
  ) {}
}
