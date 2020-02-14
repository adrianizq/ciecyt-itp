/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import ProyectoComponentsPage, { ProyectoDeleteDialog } from './proyecto.page-object';
import ProyectoUpdatePage from './proyecto-update.page-object';
import ProyectoDetailsPage from './proyecto-details.page-object';

import {
  clear,
  click,
  getRecordsCount,
  isVisible,
  selectLastOption,
  waitUntilAllDisplayed,
  waitUntilAnyDisplayed,
  waitUntilCount,
  waitUntilDisplayed,
  waitUntilHidden
} from '../../util/utils';

const expect = chai.expect;

describe('Proyecto e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: ProyectoUpdatePage;
  let detailsPage: ProyectoDetailsPage;
  let listPage: ProyectoComponentsPage;
  let deleteDialog: ProyectoDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load Proyectos', async () => {
    await navBarPage.getEntityPage('proyecto');
    listPage = new ProyectoComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create Proyecto page', async () => {
      await listPage.createButton.click();
      updatePage = new ProyectoUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.proyecto.home.createOrEditLabel/);
    });

    it('should create and save Proyectos', async () => {
      await updatePage.tituloInput.sendKeys('titulo');
      expect(await updatePage.tituloInput.getAttribute('value')).to.match(/titulo/);

      await updatePage.urlInput.sendKeys('url');
      expect(await updatePage.urlInput.getAttribute('value')).to.match(/url/);

      await updatePage.lugarEjecucionInput.sendKeys('lugarEjecucion');
      expect(await updatePage.lugarEjecucionInput.getAttribute('value')).to.match(/lugarEjecucion/);

      await updatePage.duracionInput.sendKeys('duracion');
      expect(await updatePage.duracionInput.getAttribute('value')).to.match(/duracion/);

      await updatePage.fechaIniInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaIniInput.getAttribute('value')).to.eq('2001-01-01');

      await updatePage.fechaFinInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaFinInput.getAttribute('value')).to.eq('2001-01-01');

      await updatePage.contrapartidaPesosInput.sendKeys('5');
      expect(await updatePage.contrapartidaPesosInput.getAttribute('value')).to.eq('5');

      await updatePage.contrapartidaEspecieInput.sendKeys('5');
      expect(await updatePage.contrapartidaEspecieInput.getAttribute('value')).to.eq('5');

      await updatePage.palabrasClaveInput.sendKeys('palabrasClave');
      expect(await updatePage.palabrasClaveInput.getAttribute('value')).to.match(/palabrasClave/);

      await updatePage.convocatoriaInput.sendKeys('convocatoria');
      expect(await updatePage.convocatoriaInput.getAttribute('value')).to.match(/convocatoria/);

      // await  selectLastOption(updatePage.proyectoLineaInvestigacionSelect);
      // await  selectLastOption(updatePage.proyectoGrupoSemilleroSelect);
      // await  selectLastOption(updatePage.proyectoModalidadSelect);
      // await  selectLastOption(updatePage.facultadSelect);

      expect(await updatePage.saveButton.isEnabled()).to.be.true;
      await updatePage.saveButton.click();

      await waitUntilHidden(updatePage.saveButton);
      expect(await isVisible(updatePage.saveButton)).to.be.false;

      await waitUntilDisplayed(listPage.successAlert);
      expect(await listPage.successAlert.isDisplayed()).to.be.true;

      await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      expect(await listPage.records.count()).to.eq(beforeRecordsCount + 1);
    });

    describe('Details, Update, Delete flow', () => {
      after(async () => {
        const deleteButton = listPage.getDeleteButton(listPage.records.first());
        await click(deleteButton);

        deleteDialog = new ProyectoDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.proyecto.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details Proyecto page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new ProyectoDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit Proyecto page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.tituloInput.clear();
        await updatePage.tituloInput.sendKeys('modified');
        expect(await updatePage.tituloInput.getAttribute('value')).to.match(/modified/);

        await updatePage.urlInput.clear();
        await updatePage.urlInput.sendKeys('modified');
        expect(await updatePage.urlInput.getAttribute('value')).to.match(/modified/);

        await updatePage.lugarEjecucionInput.clear();
        await updatePage.lugarEjecucionInput.sendKeys('modified');
        expect(await updatePage.lugarEjecucionInput.getAttribute('value')).to.match(/modified/);

        await updatePage.duracionInput.clear();
        await updatePage.duracionInput.sendKeys('modified');
        expect(await updatePage.duracionInput.getAttribute('value')).to.match(/modified/);

        await updatePage.fechaIniInput.clear();
        await updatePage.fechaIniInput.sendKeys('01-01-2019');
        expect(await updatePage.fechaIniInput.getAttribute('value')).to.eq('2019-01-01');

        await updatePage.fechaFinInput.clear();
        await updatePage.fechaFinInput.sendKeys('01-01-2019');
        expect(await updatePage.fechaFinInput.getAttribute('value')).to.eq('2019-01-01');

        await clear(updatePage.contrapartidaPesosInput);
        await updatePage.contrapartidaPesosInput.sendKeys('6');
        expect(await updatePage.contrapartidaPesosInput.getAttribute('value')).to.eq('6');

        await clear(updatePage.contrapartidaEspecieInput);
        await updatePage.contrapartidaEspecieInput.sendKeys('6');
        expect(await updatePage.contrapartidaEspecieInput.getAttribute('value')).to.eq('6');

        await updatePage.palabrasClaveInput.clear();
        await updatePage.palabrasClaveInput.sendKeys('modified');
        expect(await updatePage.palabrasClaveInput.getAttribute('value')).to.match(/modified/);

        await updatePage.convocatoriaInput.clear();
        await updatePage.convocatoriaInput.sendKeys('modified');
        expect(await updatePage.convocatoriaInput.getAttribute('value')).to.match(/modified/);

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
