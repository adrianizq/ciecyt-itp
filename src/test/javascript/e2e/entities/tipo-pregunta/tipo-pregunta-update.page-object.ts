import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class TipoPreguntaUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.tipoPregunta.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  tipoPreguntaInput: ElementFinder = element(by.css('input#tipo-pregunta-tipoPregunta'));

  tipoDatoInput: ElementFinder = element(by.css('input#tipo-pregunta-tipoDato'));
}
