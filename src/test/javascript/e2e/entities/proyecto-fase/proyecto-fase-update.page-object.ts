import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class ProyectoFaseUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.proyectoFase.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  tituloInput: ElementFinder = element(by.css('input#proyecto-fase-titulo'));

  cumplidaInput: ElementFinder = element(by.css('input#proyecto-fase-cumplida'));

  fechaCumplimientoInput: ElementFinder = element(by.css('input#proyecto-fase-fechaCumplimiento'));

  observacionesInput: ElementFinder = element(by.css('input#proyecto-fase-observaciones'));

  proyectoFaseFasesSelect = element(by.css('select#proyecto-fase-proyectoFaseFases'));

  proyectoFaseProyectoSelect = element(by.css('select#proyecto-fase-proyectoFaseProyecto'));
}
