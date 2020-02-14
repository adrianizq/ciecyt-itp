import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class PreguntaUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.pregunta.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  encabezadoInput: ElementFinder = element(by.css('input#pregunta-encabezado'));

  descripcionInput: ElementFinder = element(by.css('input#pregunta-descripcion'));

  preguntaInput: ElementFinder = element(by.css('input#pregunta-pregunta'));

  preguntaTipoPreguntaSelect = element(by.css('select#pregunta-preguntaTipoPregunta'));

  preguntaModalidadSelect = element(by.css('select#pregunta-preguntaModalidad'));

  preguntaRolesModalidadSelect = element(by.css('select#pregunta-preguntaRolesModalidad'));
}
