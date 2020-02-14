export interface IResultadosEsperados {
  id?: number;
  resultado?: string;
  indicador?: string;
  beneficiario?: string;
  resultadosEsperadosProyectoTitulo?: string;
  resultadosEsperadosProyectoId?: number;
}

export class ResultadosEsperados implements IResultadosEsperados {
  constructor(
    public id?: number,
    public resultado?: string,
    public indicador?: string,
    public beneficiario?: string,
    public resultadosEsperadosProyectoTitulo?: string,
    public resultadosEsperadosProyectoId?: number
  ) {}
}
