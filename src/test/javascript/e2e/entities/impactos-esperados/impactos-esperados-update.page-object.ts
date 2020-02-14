import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class ImpactosEsperadosUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.impactosEsperados.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  impactoInput: ElementFinder = element(by.css('input#impactos-esperados-impacto'));

  plazoInput: ElementFinder = element(by.css('input#impactos-esperados-plazo'));

  indicadorInput: ElementFinder = element(by.css('input#impactos-esperados-indicador'));

  supuestosInput: ElementFinder = element(by.css('input#impactos-esperados-supuestos'));

  impactosEsperadoProyectoSelect = element(by.css('select#impactos-esperados-impactosEsperadoProyecto'));
}
