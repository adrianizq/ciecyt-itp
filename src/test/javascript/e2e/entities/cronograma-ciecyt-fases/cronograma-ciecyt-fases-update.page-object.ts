import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class CronogramaCiecytFasesUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.cronogramaCiecytFases.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  inicioFaseInput: ElementFinder = element(by.css('input#cronograma-ciecyt-fases-inicioFase'));

  finFaseInput: ElementFinder = element(by.css('input#cronograma-ciecyt-fases-finFase'));

  textoExplicativoInput: ElementFinder = element(by.css('input#cronograma-ciecyt-fases-textoExplicativo'));

  cronogramaCiecytFasesCronogramaCiecytSelect = element(by.css('select#cronograma-ciecyt-fases-cronogramaCiecytFasesCronogramaCiecyt'));

  cronogramaCiecytFasesFasesSelect = element(by.css('select#cronograma-ciecyt-fases-cronogramaCiecytFasesFases'));
}
