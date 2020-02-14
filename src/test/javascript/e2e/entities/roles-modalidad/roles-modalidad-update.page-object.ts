import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class RolesModalidadUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.rolesModalidad.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  rolInput: ElementFinder = element(by.css('input#roles-modalidad-rol'));

  cantidadInput: ElementFinder = element(by.css('input#roles-modalidad-cantidad'));

  calificadorInput: ElementFinder = element(by.css('input#roles-modalidad-calificador'));
  rolesModalidadModalidadSelect = element(by.css('select#roles-modalidad-rolesModalidadModalidad'));
}
