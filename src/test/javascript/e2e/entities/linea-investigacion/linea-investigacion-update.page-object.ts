import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class LineaInvestigacionUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.lineaInvestigacion.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  lineaInput: ElementFinder = element(by.css('input#linea-investigacion-linea'));

  codigoLineaInput: ElementFinder = element(by.css('input#linea-investigacion-codigoLinea'));

  lineaPadreSelect = element(by.css('select#linea-investigacion-lineaPadre'));

  lineaInvestigacionFacultadSelect = element(by.css('select#linea-investigacion-lineaInvestigacionFacultad'));
}
