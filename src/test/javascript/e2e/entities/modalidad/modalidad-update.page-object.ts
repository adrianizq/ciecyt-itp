import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class ModalidadUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.modalidad.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  modalidadInput: ElementFinder = element(by.css('input#modalidad-modalidad'));

  modalidadAcuerdoSelect = element(by.css('select#modalidad-modalidadAcuerdo'));
}
