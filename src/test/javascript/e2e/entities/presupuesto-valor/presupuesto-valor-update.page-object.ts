import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class PresupuestoValorUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.presupuestoValor.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  descripcionInput: ElementFinder = element(by.css('input#presupuesto-valor-descripcion'));

  justificacionInput: ElementFinder = element(by.css('input#presupuesto-valor-justificacion'));

  cantidadInput: ElementFinder = element(by.css('input#presupuesto-valor-cantidad'));

  valorUnitarioInput: ElementFinder = element(by.css('input#presupuesto-valor-valorUnitario'));

  especieInput: ElementFinder = element(by.css('input#presupuesto-valor-especie'));

  dineroInput: ElementFinder = element(by.css('input#presupuesto-valor-dinero'));

  presupuestoValorRubroSelect = element(by.css('select#presupuesto-valor-presupuestoValorRubro'));

  presupuestoValorProyectoSelect = element(by.css('select#presupuesto-valor-presupuestoValorProyecto'));

  presupuestoValorEntidadSelect = element(by.css('select#presupuesto-valor-presupuestoValorEntidad'));
}
