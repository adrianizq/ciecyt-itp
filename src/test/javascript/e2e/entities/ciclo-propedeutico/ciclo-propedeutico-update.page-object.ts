import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class CicloPropedeuticoUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.cicloPropedeutico.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  cicloInput: ElementFinder = element(by.css('input#ciclo-propedeutico-ciclo'));

  modalidadSelect = element(by.css('select#ciclo-propedeutico-modalidad'));

  cicloPropedeuticoAcuerdoSelect = element(by.css('select#ciclo-propedeutico-cicloPropedeuticoAcuerdo'));
}
