/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import AdjuntoRetroalimentacionComponentsPage, { AdjuntoRetroalimentacionDeleteDialog } from './adjunto-retroalimentacion.page-object';
import AdjuntoRetroalimentacionUpdatePage from './adjunto-retroalimentacion-update.page-object';
import AdjuntoRetroalimentacionDetailsPage from './adjunto-retroalimentacion-details.page-object';

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

describe('AdjuntoRetroalimentacion e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: AdjuntoRetroalimentacionUpdatePage;
  let detailsPage: AdjuntoRetroalimentacionDetailsPage;
  let listPage: AdjuntoRetroalimentacionComponentsPage;
  let deleteDialog: AdjuntoRetroalimentacionDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load AdjuntoRetroalimentacions', async () => {
    await navBarPage.getEntityPage('adjunto-retroalimentacion');
    listPage = new AdjuntoRetroalimentacionComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create AdjuntoRetroalimentacion page', async () => {
      await listPage.createButton.click();
      updatePage = new AdjuntoRetroalimentacionUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.adjuntoRetroalimentacion.home.createOrEditLabel/);
    });

    it('should create and save AdjuntoRetroalimentacions', async () => {
      await updatePage.nombreAdjuntoInput.sendKeys('nombreAdjunto');
      expect(await updatePage.nombreAdjuntoInput.getAttribute('value')).to.match(/nombreAdjunto/);

      await updatePage.fechaCreacionInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaCreacionInput.getAttribute('value')).to.eq('2001-01-01');

      await updatePage.fechaModificacionInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaModificacionInput.getAttribute('value')).to.eq('2001-01-01');

      await updatePage.estadoAdjuntoInput.sendKeys('5');
      expect(await updatePage.estadoAdjuntoInput.getAttribute('value')).to.eq('5');

      await updatePage.adjuntoRetroalimentacionInput.sendKeys('adjuntoRetroalimentacion');
      expect(await updatePage.adjuntoRetroalimentacionInput.getAttribute('value')).to.match(/adjuntoRetroalimentacion/);

      await updatePage.nombreArchivoOriginalInput.sendKeys('nombreArchivoOriginal');
      expect(await updatePage.nombreArchivoOriginalInput.getAttribute('value')).to.match(/nombreArchivoOriginal/);

      await updatePage.fechaInicioInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaInicioInput.getAttribute('value')).to.eq('2001-01-01');

      await updatePage.fechaFinInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaFinInput.getAttribute('value')).to.eq('2001-01-01');

      // await  selectLastOption(updatePage.adjuntoRetroalimentacionRetroalimentacionSelect);

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

        deleteDialog = new AdjuntoRetroalimentacionDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.adjuntoRetroalimentacion.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details AdjuntoRetroalimentacion page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new AdjuntoRetroalimentacionDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit AdjuntoRetroalimentacion page, fetch data and update', async () => {
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

        await updatePage.adjuntoRetroalimentacionInput.clear();
        await updatePage.adjuntoRetroalimentacionInput.sendKeys('modified');
        expect(await updatePage.adjuntoRetroalimentacionInput.getAttribute('value')).to.match(/modified/);

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
