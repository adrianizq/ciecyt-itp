import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class EntidadUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.entidad.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  entidadInput: ElementFinder = element(by.css('input#entidad-entidad'));

  nitInput: ElementFinder = element(by.css('input#entidad-nit'));
}
