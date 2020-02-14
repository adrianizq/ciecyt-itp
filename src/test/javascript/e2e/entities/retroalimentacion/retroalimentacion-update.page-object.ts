import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class RetroalimentacionUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.retroalimentacion.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  tituloInput: ElementFinder = element(by.css('input#retroalimentacion-titulo'));

  retroalimentacionInput: ElementFinder = element(by.css('input#retroalimentacion-retroalimentacion'));

  fechaRetroalimentacionInput: ElementFinder = element(by.css('input#retroalimentacion-fechaRetroalimentacion'));

  estadoRetroalimentacionInput: ElementFinder = element(by.css('input#retroalimentacion-estadoRetroalimentacion'));

  estadoProyectoFaseInput: ElementFinder = element(by.css('input#retroalimentacion-estadoProyectoFase'));

  retroalimentacionProyectoFaseSelect = element(by.css('select#retroalimentacion-retroalimentacionProyectoFase'));

  retroalimentacionUserSelect = element(by.css('select#retroalimentacion-retroalimentacionUser'));
}
