/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import RetroalimentacionComponentsPage, { RetroalimentacionDeleteDialog } from './retroalimentacion.page-object';
import RetroalimentacionUpdatePage from './retroalimentacion-update.page-object';
import RetroalimentacionDetailsPage from './retroalimentacion-details.page-object';

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

describe('Retroalimentacion e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: RetroalimentacionUpdatePage;
  let detailsPage: RetroalimentacionDetailsPage;
  let listPage: RetroalimentacionComponentsPage;
  let deleteDialog: RetroalimentacionDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load Retroalimentacions', async () => {
    await navBarPage.getEntityPage('retroalimentacion');
    listPage = new RetroalimentacionComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create Retroalimentacion page', async () => {
      await listPage.createButton.click();
      updatePage = new RetroalimentacionUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.retroalimentacion.home.createOrEditLabel/);
    });

    it('should create and save Retroalimentacions', async () => {
      await updatePage.tituloInput.sendKeys('titulo');
      expect(await updatePage.tituloInput.getAttribute('value')).to.match(/titulo/);

      await updatePage.retroalimentacionInput.sendKeys('retroalimentacion');
      expect(await updatePage.retroalimentacionInput.getAttribute('value')).to.match(/retroalimentacion/);

      await updatePage.fechaRetroalimentacionInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaRetroalimentacionInput.getAttribute('value')).to.eq('2001-01-01');

      await updatePage.estadoRetroalimentacionInput.sendKeys('01-01-2001');
      expect(await updatePage.estadoRetroalimentacionInput.getAttribute('value')).to.eq('2001-01-01');

      await updatePage.estadoProyectoFaseInput.sendKeys('5');
      expect(await updatePage.estadoProyectoFaseInput.getAttribute('value')).to.eq('5');

      // await  selectLastOption(updatePage.retroalimentacionProyectoFaseSelect);
      // await  selectLastOption(updatePage.retroalimentacionUserSelect);

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

        deleteDialog = new RetroalimentacionDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.retroalimentacion.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details Retroalimentacion page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new RetroalimentacionDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit Retroalimentacion page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.tituloInput.clear();
        await updatePage.tituloInput.sendKeys('modified');
        expect(await updatePage.tituloInput.getAttribute('value')).to.match(/modified/);

        await updatePage.retroalimentacionInput.clear();
        await updatePage.retroalimentacionInput.sendKeys('modified');
        expect(await updatePage.retroalimentacionInput.getAttribute('value')).to.match(/modified/);

        await updatePage.fechaRetroalimentacionInput.clear();
        await updatePage.fechaRetroalimentacionInput.sendKeys('01-01-2019');
        expect(await updatePage.fechaRetroalimentacionInput.getAttribute('value')).to.eq('2019-01-01');

        await updatePage.estadoRetroalimentacionInput.clear();
        await updatePage.estadoRetroalimentacionInput.sendKeys('01-01-2019');
        expect(await updatePage.estadoRetroalimentacionInput.getAttribute('value')).to.eq('2019-01-01');

        await clear(updatePage.estadoProyectoFaseInput);
        await updatePage.estadoProyectoFaseInput.sendKeys('6');
        expect(await updatePage.estadoProyectoFaseInput.getAttribute('value')).to.eq('6');

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
