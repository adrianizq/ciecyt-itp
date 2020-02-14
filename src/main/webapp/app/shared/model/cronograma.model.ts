export interface ICronograma {
  id?: number;
  actividad?: string;
  duracion?: number;
  fechaInicio?: Date;
  fechaFin?: Date;
  cronogramaProyectoTitulo?: string;
  cronogramaProyectoId?: number;
}

export class Cronograma implements ICronograma {
  constructor(
    public id?: number,
    public actividad?: string,
    public duracion?: number,
    public fechaInicio?: Date,
    public fechaFin?: Date,
    public cronogramaProyectoTitulo?: string,
    public cronogramaProyectoId?: number
  ) {}
}
