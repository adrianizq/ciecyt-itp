import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class AcuerdoUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.acuerdo.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  acuerdoInput: ElementFinder = element(by.css('input#acuerdo-acuerdo'));

  versionInput: ElementFinder = element(by.css('input#acuerdo-version'));

  codigoInput: ElementFinder = element(by.css('input#acuerdo-codigo'));

  fechaInput: ElementFinder = element(by.css('input#acuerdo-fecha'));
}
