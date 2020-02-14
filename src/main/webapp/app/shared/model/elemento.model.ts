export interface IElemento {
  id?: number;
  elemento?: string;
  elementoFormatoFormato?: string;
  elementoFormatoId?: number;
  elementoModalidadModalidad?: string;
  elementoModalidadId?: number;
}

export class Elemento implements IElemento {
  constructor(
    public id?: number,
    public elemento?: string,
    public elementoFormatoFormato?: string,
    public elementoFormatoId?: number,
    public elementoModalidadModalidad?: string,
    public elementoModalidadId?: number
  ) {}
}
