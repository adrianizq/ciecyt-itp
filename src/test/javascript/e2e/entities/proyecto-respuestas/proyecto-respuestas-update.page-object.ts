import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class ProyectoRespuestasUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.proyectoRespuestas.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  respuestaSelect = element(by.css('select#proyecto-respuestas-respuesta'));

  observacionesInput: ElementFinder = element(by.css('input#proyecto-respuestas-observaciones'));

  viableInput: ElementFinder = element(by.css('input#proyecto-respuestas-viable'));
  proyectoRespuestasPreguntaSelect = element(by.css('select#proyecto-respuestas-proyectoRespuestasPregunta'));

  proyectoRespuestasProyectoSelect = element(by.css('select#proyecto-respuestas-proyectoRespuestasProyecto'));
}
