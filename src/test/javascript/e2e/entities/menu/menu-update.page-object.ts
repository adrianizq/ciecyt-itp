import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class MenuUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.menu.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  nombreInput: ElementFinder = element(by.css('input#menu-nombre'));

  urlInput: ElementFinder = element(by.css('input#menu-url'));

  iconoInput: ElementFinder = element(by.css('input#menu-icono'));

  activoInput: ElementFinder = element(by.css('input#menu-activo'));
  menuPadreSelect = element(by.css('select#menu-menuPadre'));
}
