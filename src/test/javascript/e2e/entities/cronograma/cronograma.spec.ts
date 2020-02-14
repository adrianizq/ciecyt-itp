/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import CronogramaComponentsPage, { CronogramaDeleteDialog } from './cronograma.page-object';
import CronogramaUpdatePage from './cronograma-update.page-object';
import CronogramaDetailsPage from './cronograma-details.page-object';

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

describe('Cronograma e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: CronogramaUpdatePage;
  let detailsPage: CronogramaDetailsPage;
  let listPage: CronogramaComponentsPage;
  let deleteDialog: CronogramaDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load Cronogramas', async () => {
    await navBarPage.getEntityPage('cronograma');
    listPage = new CronogramaComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create Cronograma page', async () => {
      await listPage.createButton.click();
      updatePage = new CronogramaUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.cronograma.home.createOrEditLabel/);
    });

    it('should create and save Cronogramas', async () => {
      await updatePage.actividadInput.sendKeys('actividad');
      expect(await updatePage.actividadInput.getAttribute('value')).to.match(/actividad/);

      await updatePage.duracionInput.sendKeys('5');
      expect(await updatePage.duracionInput.getAttribute('value')).to.eq('5');

      await updatePage.fechaInicioInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaInicioInput.getAttribute('value')).to.eq('2001-01-01');

      await updatePage.fechaFinInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaFinInput.getAttribute('value')).to.eq('2001-01-01');

      // await  selectLastOption(updatePage.cronogramaProyectoSelect);

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

        deleteDialog = new CronogramaDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.cronograma.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details Cronograma page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new CronogramaDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit Cronograma page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.actividadInput.clear();
        await updatePage.actividadInput.sendKeys('modified');
        expect(await updatePage.actividadInput.getAttribute('value')).to.match(/modified/);

        await clear(updatePage.duracionInput);
        await updatePage.duracionInput.sendKeys('6');
        expect(await updatePage.duracionInput.getAttribute('value')).to.eq('6');

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
