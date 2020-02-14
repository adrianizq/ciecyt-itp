import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class CategorizacionUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.categorizacion.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  categoriaInput: ElementFinder = element(by.css('input#categorizacion-categoria'));

  descripcionInput: ElementFinder = element(by.css('input#categorizacion-descripcion'));

  categorizacionProyectoSelect = element(by.css('select#categorizacion-categorizacionProyecto'));
}
