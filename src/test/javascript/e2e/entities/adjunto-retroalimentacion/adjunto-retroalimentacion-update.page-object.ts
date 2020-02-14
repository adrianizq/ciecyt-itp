import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class AdjuntoRetroalimentacionUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.adjuntoRetroalimentacion.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  nombreAdjuntoInput: ElementFinder = element(by.css('input#adjunto-retroalimentacion-nombreAdjunto'));

  fechaCreacionInput: ElementFinder = element(by.css('input#adjunto-retroalimentacion-fechaCreacion'));

  fechaModificacionInput: ElementFinder = element(by.css('input#adjunto-retroalimentacion-fechaModificacion'));

  estadoAdjuntoInput: ElementFinder = element(by.css('input#adjunto-retroalimentacion-estadoAdjunto'));

  adjuntoRetroalimentacionInput: ElementFinder = element(by.css('input#adjunto-retroalimentacion-adjuntoRetroalimentacion'));

  nombreArchivoOriginalInput: ElementFinder = element(by.css('input#adjunto-retroalimentacion-nombreArchivoOriginal'));

  fechaInicioInput: ElementFinder = element(by.css('input#adjunto-retroalimentacion-fechaInicio'));

  fechaFinInput: ElementFinder = element(by.css('input#adjunto-retroalimentacion-fechaFin'));

  adjuntoRetroalimentacionRetroalimentacionSelect = element(
    by.css('select#adjunto-retroalimentacion-adjuntoRetroalimentacionRetroalimentacion')
  );
}
