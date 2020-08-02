export interface IElementoProyecto {
  id?: number;
  dato?: string;
  elementoProyectoElementoElemento?: string;
  elementoProyectoElementoId?: number;
  elementoProyectoProyectoTitulo?: string;
  elementoProyectoProyectoDescripcion?: string;
  elementoProyectoProyectoId?: number;
}

export class ElementoProyecto implements IElementoProyecto {
  constructor(
    public id?: number,
    public dato?: string,
    public elementoProyectoElementoElemento?: string,
    public elementoProyectoElementoId?: number,
    public elementoProyectoProyectoTitulo?: string,
    public elementoProyectoProyectoDescripcion?: string,
    public elementoProyectoProyectoId?: number
  ) {}
}
