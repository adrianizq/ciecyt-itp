import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class ProductoUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.producto.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  productoInput: ElementFinder = element(by.css('input#producto-producto'));
}
