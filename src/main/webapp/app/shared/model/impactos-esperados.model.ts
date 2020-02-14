export interface IImpactosEsperados {
  id?: number;
  impacto?: string;
  plazo?: number;
  indicador?: string;
  supuestos?: string;
  impactosEsperadoProyectoTitulo?: string;
  impactosEsperadoProyectoId?: number;
}

export class ImpactosEsperados implements IImpactosEsperados {
  constructor(
    public id?: number,
    public impacto?: string,
    public plazo?: number,
    public indicador?: string,
    public supuestos?: string,
    public impactosEsperadoProyectoTitulo?: string,
    public impactosEsperadoProyectoId?: number
  ) {}
}
