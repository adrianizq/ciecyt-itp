import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class CronogramaCiecytUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.cronogramaCiecyt.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  tituloCronogramaInput: ElementFinder = element(by.css('input#cronograma-ciecyt-tituloCronograma'));

  fechaInicioInput: ElementFinder = element(by.css('input#cronograma-ciecyt-fechaInicio'));

  fechaFinInput: ElementFinder = element(by.css('input#cronograma-ciecyt-fechaFin'));

  observacionesInput: ElementFinder = element(by.css('input#cronograma-ciecyt-observaciones'));

  cronogramaCiecytModalidadSelect = element(by.css('select#cronograma-ciecyt-cronogramaCiecytModalidad'));
}
