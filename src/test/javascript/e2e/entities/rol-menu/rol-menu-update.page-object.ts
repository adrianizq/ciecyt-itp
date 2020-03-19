import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class RolMenuUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.rolMenu.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  permitirAccesoInput: ElementFinder = element(by.css('input#rol-menu-permitirAcceso'));

  permitirCrearInput: ElementFinder = element(by.css('input#rol-menu-permitirCrear'));

  permitirEditarInput: ElementFinder = element(by.css('input#rol-menu-permitirEditar'));

  permitirEliminarInput: ElementFinder = element(by.css('input#rol-menu-permitirEliminar'));
  rolMenuMenuSelect = element(by.css('select#rol-menu-rolMenuMenu'));
}
