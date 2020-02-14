import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class FormatoUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.formato.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  formatoInput: ElementFinder = element(by.css('input#formato-formato'));

  versionInput: ElementFinder = element(by.css('input#formato-version'));

  codigoInput: ElementFinder = element(by.css('input#formato-codigo'));

  fechaInput: ElementFinder = element(by.css('input#formato-fecha'));
}
