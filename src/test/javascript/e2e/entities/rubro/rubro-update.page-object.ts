import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class RubroUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.rubro.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  rubroInput: ElementFinder = element(by.css('input#rubro-rubro'));
}
