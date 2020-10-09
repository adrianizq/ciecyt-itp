export interface ILineaInvestigacion {
  id?: number;
  linea?: string;
  codigoLinea?: string;
  lineaPadreLinea?: string;
  lineaPadreId?: number;
  lineaInvestigacionFacultadFacultad?: string;
  lineaInvestigacionFacultadId?: number;
}

export class LineaInvestigacion implements ILineaInvestigacion {
  constructor(
    public id?: number,
    public linea?: string,
    public codigoLinea?: string,
    public lineaPadreLinea?: string,
    public lineaPadreId?: number,
    public lineaInvestigacionFacultadFacultad?: string,
    public lineaInvestigacionFacultadId?: number
  ) {}
}
