import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class ResultadosEsperadosUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.resultadosEsperados.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  resultadoInput: ElementFinder = element(by.css('input#resultados-esperados-resultado'));

  indicadorInput: ElementFinder = element(by.css('input#resultados-esperados-indicador'));

  beneficiarioInput: ElementFinder = element(by.css('input#resultados-esperados-beneficiario'));

  resultadosEsperadosProyectoSelect = element(by.css('select#resultados-esperados-resultadosEsperadosProyecto'));
}
