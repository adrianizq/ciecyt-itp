import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class FacultadUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.facultad.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  codigoFacultadInput: ElementFinder = element(by.css('input#facultad-codigoFacultad'));

  facultadInput: ElementFinder = element(by.css('input#facultad-facultad'));
}
