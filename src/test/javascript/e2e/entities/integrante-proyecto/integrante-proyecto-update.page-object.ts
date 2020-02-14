import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class IntegranteProyectoUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.integranteProyecto.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  integranteInput: ElementFinder = element(by.css('input#integrante-proyecto-integrante'));

  descripcionInput: ElementFinder = element(by.css('input#integrante-proyecto-descripcion'));

  integranteProyectoUserSelect = element(by.css('select#integrante-proyecto-integranteProyectoUser'));

  integranteProyectoProyectoSelect = element(by.css('select#integrante-proyecto-integranteProyectoProyecto'));

  integranteProyectoRolesModalidadSelect = element(by.css('select#integrante-proyecto-integranteProyectoRolesModalidad'));
}
