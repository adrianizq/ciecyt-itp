import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class EntidadFinanciadoraUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.entidadFinanciadora.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  valorInput: ElementFinder = element(by.css('input#entidad-financiadora-valor'));

  aprobadaInput: ElementFinder = element(by.css('input#entidad-financiadora-aprobada'));
  entidadFinanciadoraEntidadSelect = element(by.css('select#entidad-financiadora-entidadFinanciadoraEntidad'));
}
