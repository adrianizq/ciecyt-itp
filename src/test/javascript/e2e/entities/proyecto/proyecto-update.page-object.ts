import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class ProyectoUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.proyecto.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  tituloInput: ElementFinder = element(by.css('input#proyecto-titulo'));

  urlInput: ElementFinder = element(by.css('input#proyecto-url'));

  lugarEjecucionInput: ElementFinder = element(by.css('input#proyecto-lugarEjecucion'));

  duracionInput: ElementFinder = element(by.css('input#proyecto-duracion'));

  fechaIniInput: ElementFinder = element(by.css('input#proyecto-fechaIni'));

  fechaFinInput: ElementFinder = element(by.css('input#proyecto-fechaFin'));

  contrapartidaPesosInput: ElementFinder = element(by.css('input#proyecto-contrapartidaPesos'));

  contrapartidaEspecieInput: ElementFinder = element(by.css('input#proyecto-contrapartidaEspecie'));

  palabrasClaveInput: ElementFinder = element(by.css('input#proyecto-palabrasClave'));

  convocatoriaInput: ElementFinder = element(by.css('input#proyecto-convocatoria'));

  proyectoLineaInvestigacionSelect = element(by.css('select#proyecto-proyectoLineaInvestigacion'));

  proyectoGrupoSemilleroSelect = element(by.css('select#proyecto-proyectoGrupoSemillero'));

  proyectoModalidadSelect = element(by.css('select#proyecto-proyectoModalidad'));

  facultadSelect = element(by.css('select#proyecto-facultad'));

  subLineaLineaInvestigacionSelect = element(by.css('select#proyecto-subLineaLineaInvestigacion'));
}
