import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class ProductoProyectoUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.productoProyecto.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  aplicaInput: ElementFinder = element(by.css('input#producto-proyecto-aplica'));

  descripcionInput: ElementFinder = element(by.css('input#producto-proyecto-descripcion'));

  productoProyectoProductoSelect = element(by.css('select#producto-proyecto-productoProyectoProducto'));

  productoProyectoProyectoSelect = element(by.css('select#producto-proyecto-productoProyectoProyecto'));
}
