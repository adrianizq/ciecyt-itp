/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import AdjuntoProyectoFaseComponentsPage, { AdjuntoProyectoFaseDeleteDialog } from './adjunto-proyecto-fase.page-object';
import AdjuntoProyectoFaseUpdatePage from './adjunto-proyecto-fase-update.page-object';
import AdjuntoProyectoFaseDetailsPage from './adjunto-proyecto-fase-details.page-object';

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

describe('AdjuntoProyectoFase e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: AdjuntoProyectoFaseUpdatePage;
  let detailsPage: AdjuntoProyectoFaseDetailsPage;
  let listPage: AdjuntoProyectoFaseComponentsPage;
  let deleteDialog: AdjuntoProyectoFaseDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load AdjuntoProyectoFases', async () => {
    await navBarPage.getEntityPage('adjunto-proyecto-fase');
    listPage = new AdjuntoProyectoFaseComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create AdjuntoProyectoFase page', async () => {
      await listPage.createButton.click();
      updatePage = new AdjuntoProyectoFaseUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.adjuntoProyectoFase.home.createOrEditLabel/);
    });

    it('should create and save AdjuntoProyectoFases', async () => {
      await updatePage.nombreAdjuntoInput.sendKeys('nombreAdjunto');
      expect(await updatePage.nombreAdjuntoInput.getAttribute('value')).to.match(/nombreAdjunto/);

      await updatePage.fechaCreacionInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaCreacionInput.getAttribute('value')).to.eq('2001-01-01');

      await updatePage.fechaModificacionInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaModificacionInput.getAttribute('value')).to.eq('2001-01-01');

      await updatePage.estadoAdjuntoInput.sendKeys('5');
      expect(await updatePage.estadoAdjuntoInput.getAttribute('value')).to.eq('5');

      await updatePage.adjuntoProyectoFaseInput.sendKeys('adjuntoProyectoFase');
      expect(await updatePage.adjuntoProyectoFaseInput.getAttribute('value')).to.match(/adjuntoProyectoFase/);

      await updatePage.nombreArchivoOriginalInput.sendKeys('nombreArchivoOriginal');
      expect(await updatePage.nombreArchivoOriginalInput.getAttribute('value')).to.match(/nombreArchivoOriginal/);

      await updatePage.fechaInicioInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaInicioInput.getAttribute('value')).to.eq('2001-01-01');

      await updatePage.fechaFinInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaFinInput.getAttribute('value')).to.eq('2001-01-01');

      // await  selectLastOption(updatePage.adjuntoProyectoFaseProyectoFaseSelect);

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

        deleteDialog = new AdjuntoProyectoFaseDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.adjuntoProyectoFase.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details AdjuntoProyectoFase page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new AdjuntoProyectoFaseDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit AdjuntoProyectoFase page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.nombreAdjuntoInput.clear();
        await updatePage.nombreAdjuntoInput.sendKeys('modified');
        expect(await updatePage.nombreAdjuntoInput.getAttribute('value')).to.match(/modified/);

        await updatePage.fechaCreacionInput.clear();
        await updatePage.fechaCreacionInput.sendKeys('01-01-2019');
        expect(await updatePage.fechaCreacionInput.getAttribute('value')).to.eq('2019-01-01');

        await updatePage.fechaModificacionInput.clear();
        await updatePage.fechaModificacionInput.sendKeys('01-01-2019');
        expect(await updatePage.fechaModificacionInput.getAttribute('value')).to.eq('2019-01-01');

        await clear(updatePage.estadoAdjuntoInput);
        await updatePage.estadoAdjuntoInput.sendKeys('6');
        expect(await updatePage.estadoAdjuntoInput.getAttribute('value')).to.eq('6');

        await updatePage.adjuntoProyectoFaseInput.clear();
        await updatePage.adjuntoProyectoFaseInput.sendKeys('modified');
        expect(await updatePage.adjuntoProyectoFaseInput.getAttribute('value')).to.match(/modified/);

        await updatePage.nombreArchivoOriginalInput.clear();
        await updatePage.nombreArchivoOriginalInput.sendKeys('modified');
        expect(await updatePage.nombreArchivoOriginalInput.getAttribute('value')).to.match(/modified/);

        await updatePage.fechaInicioInput.clear();
        await updatePage.fechaInicioInput.sendKeys('01-01-2019');
        expect(await updatePage.fechaInicioInput.getAttribute('value')).to.eq('2019-01-01');

        await updatePage.fechaFinInput.clear();
        await updatePage.fechaFinInput.sendKeys('01-01-2019');
        expect(await updatePage.fechaFinInput.getAttribute('value')).to.eq('2019-01-01');

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
