import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class FasesUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.fases.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  faseInput: ElementFinder = element(by.css('input#fases-fase'));

  notificableInput: ElementFinder = element(by.css('input#fases-notificable'));
  fasesModalidadSelect = element(by.css('select#fases-fasesModalidad'));
}
