import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class FichaTecnicaUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('ciecytApp.fichaTecnica.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  tituloProfesionalInput: ElementFinder = element(by.css('input#ficha-tecnica-tituloProfesional'));

  tituloPostgradoInput: ElementFinder = element(by.css('input#ficha-tecnica-tituloPostgrado'));

  experienciaInput: ElementFinder = element(by.css('input#ficha-tecnica-experiencia'));

  fichaTecnicaUserSelect = element(by.css('select#ficha-tecnica-fichaTecnicaUser'));
}
