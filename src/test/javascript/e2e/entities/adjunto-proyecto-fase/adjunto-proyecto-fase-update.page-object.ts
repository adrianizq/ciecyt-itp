import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class AdjuntoProyectoFaseUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.adjuntoProyectoFase.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  nombreAdjuntoInput: ElementFinder = element(by.css('input#adjunto-proyecto-fase-nombreAdjunto'));

  fechaCreacionInput: ElementFinder = element(by.css('input#adjunto-proyecto-fase-fechaCreacion'));

  fechaModificacionInput: ElementFinder = element(by.css('input#adjunto-proyecto-fase-fechaModificacion'));

  estadoAdjuntoInput: ElementFinder = element(by.css('input#adjunto-proyecto-fase-estadoAdjunto'));

  adjuntoProyectoFaseInput: ElementFinder = element(by.css('input#adjunto-proyecto-fase-adjuntoProyectoFase'));

  nombreArchivoOriginalInput: ElementFinder = element(by.css('input#adjunto-proyecto-fase-nombreArchivoOriginal'));

  fechaInicioInput: ElementFinder = element(by.css('input#adjunto-proyecto-fase-fechaInicio'));

  fechaFinInput: ElementFinder = element(by.css('input#adjunto-proyecto-fase-fechaFin'));

  adjuntoProyectoFaseProyectoFaseSelect = element(by.css('select#adjunto-proyecto-fase-adjuntoProyectoFaseProyectoFase'));
}
