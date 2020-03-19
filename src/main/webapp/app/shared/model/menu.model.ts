export interface IMenu {
  id?: number;
  nombre?: string;
  url?: string;
  icono?: string;
  activo?: boolean;
  menuPadreNombre?: string;
  menuPadreId?: number;
}

export class Menu implements IMenu {
  constructor(
    public id?: number,
    public nombre?: string,
    public url?: string,
    public icono?: string,
    public activo?: boolean,
    public menuPadreNombre?: string,
    public menuPadreId?: number
  ) {
    this.activo = this.activo || false;
  }
}
