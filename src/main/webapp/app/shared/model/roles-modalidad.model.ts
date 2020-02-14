export interface IRolesModalidad {
  id?: number;
  rol?: string;
  cantidad?: number;
  calificador?: boolean;
  rolesModalidadModalidadModalidad?: string;
  rolesModalidadModalidadId?: number;
}

export class RolesModalidad implements IRolesModalidad {
  constructor(
    public id?: number,
    public rol?: string,
    public cantidad?: number,
    public calificador?: boolean,
    public rolesModalidadModalidadModalidad?: string,
    public rolesModalidadModalidadId?: number
  ) {
    this.calificador = this.calificador || false;
  }
}
