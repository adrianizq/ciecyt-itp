import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class CronogramaUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.cronograma.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  actividadInput: ElementFinder = element(by.css('input#cronograma-actividad'));

  duracionInput: ElementFinder = element(by.css('input#cronograma-duracion'));

  fechaInicioInput: ElementFinder = element(by.css('input#cronograma-fechaInicio'));

  fechaFinInput: ElementFinder = element(by.css('input#cronograma-fechaFin'));

  cronogramaProyectoSelect = element(by.css('select#cronograma-cronogramaProyecto'));
}
