import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class ElementoUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.elemento.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  elementoInput: ElementFinder = element(by.css('input#elemento-elemento'));

  descripcionInput: ElementFinder = element(by.css('input#elemento-descripcion'));

  elementoFormatoSelect = element(by.css('select#elemento-elementoFormato'));

  elementoModalidadSelect = element(by.css('select#elemento-elementoModalidad'));
}
