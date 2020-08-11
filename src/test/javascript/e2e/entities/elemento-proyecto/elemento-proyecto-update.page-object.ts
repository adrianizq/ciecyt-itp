import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class ElementoProyectoUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.elementoProyecto.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  datoInput: ElementFinder = element(by.css('input#elemento-proyecto-dato'));

  elementoProyectoProyectoDescripcionInput: ElementFinder = element(by.css('input#elemento-proyecto-elementoProyectoProyectoDescripcion'));

  elementoProyectoElementoSelect = element(by.css('select#elemento-proyecto-elementoProyectoElemento'));

  elementoProyectoProyectoSelect = element(by.css('select#elemento-proyecto-elementoProyectoProyecto'));
}
