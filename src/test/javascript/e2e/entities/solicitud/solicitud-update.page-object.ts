import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class SolicitudUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.solicitud.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  estadoInput: ElementFinder = element(by.css('input#solicitud-estado'));

  asuntoInput: ElementFinder = element(by.css('input#solicitud-asunto'));

  textoSolicitudInput: ElementFinder = element(by.css('input#solicitud-textoSolicitud'));

  fechaSolicitudInput: ElementFinder = element(by.css('input#solicitud-fechaSolicitud'));

  solicitudIntegranteProyectoSelect = element(by.css('select#solicitud-solicitudIntegranteProyecto'));
}
