export interface IRolMenu {
  id?: number;
  permitirAcceso?: boolean;
  permitirCrear?: boolean;
  permitirEditar?: boolean;
  permitirEliminar?: boolean;
  rolMenuMenuNombre?: string;
  rolMenuMenuId?: number;
}

export class RolMenu implements IRolMenu {
  constructor(
    public id?: number,
    public permitirAcceso?: boolean,
    public permitirCrear?: boolean,
    public permitirEditar?: boolean,
    public permitirEliminar?: boolean,
    public rolMenuMenuNombre?: string,
    public rolMenuMenuId?: number
  ) {
    this.permitirAcceso = this.permitirAcceso || false;
    this.permitirCrear = this.permitirCrear || false;
    this.permitirEditar = this.permitirEditar || false;
    this.permitirEliminar = this.permitirEliminar || false;
  }
}
