import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class UsuarioUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.usuario.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  usuarioInput: ElementFinder = element(by.css('input#usuario-usuario'));

  descripcionInput: ElementFinder = element(by.css('input#usuario-descripcion'));
}
