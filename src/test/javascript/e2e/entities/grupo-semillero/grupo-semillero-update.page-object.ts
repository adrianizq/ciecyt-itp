import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class GrupoSemilleroUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.grupoSemillero.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  nombreInput: ElementFinder = element(by.css('input#grupo-semillero-nombre'));

  tipoInput: ElementFinder = element(by.css('input#grupo-semillero-tipo'));
}
