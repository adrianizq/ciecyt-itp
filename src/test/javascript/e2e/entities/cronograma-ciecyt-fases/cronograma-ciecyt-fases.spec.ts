/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import CronogramaCiecytFasesComponentsPage, { CronogramaCiecytFasesDeleteDialog } from './cronograma-ciecyt-fases.page-object';
import CronogramaCiecytFasesUpdatePage from './cronograma-ciecyt-fases-update.page-object';
import CronogramaCiecytFasesDetailsPage from './cronograma-ciecyt-fases-details.page-object';

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

describe('CronogramaCiecytFases e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: CronogramaCiecytFasesUpdatePage;
  let detailsPage: CronogramaCiecytFasesDetailsPage;
  let listPage: CronogramaCiecytFasesComponentsPage;
  let deleteDialog: CronogramaCiecytFasesDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load CronogramaCiecytFases', async () => {
    await navBarPage.getEntityPage('cronograma-ciecyt-fases');
    listPage = new CronogramaCiecytFasesComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create CronogramaCiecytFases page', async () => {
      await listPage.createButton.click();
      updatePage = new CronogramaCiecytFasesUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.cronogramaCiecytFases.home.createOrEditLabel/);
    });

    it('should create and save CronogramaCiecytFases', async () => {
      await updatePage.inicioFaseInput.sendKeys('01-01-2001');
      expect(await updatePage.inicioFaseInput.getAttribute('value')).to.eq('2001-01-01');

      await updatePage.finFaseInput.sendKeys('01-01-2001');
      expect(await updatePage.finFaseInput.getAttribute('value')).to.eq('2001-01-01');

      await updatePage.textoExplicativoInput.sendKeys('textoExplicativo');
      expect(await updatePage.textoExplicativoInput.getAttribute('value')).to.match(/textoExplicativo/);

      // await  selectLastOption(updatePage.cronogramaCiecytFasesCronogramaCiecytSelect);
      // await  selectLastOption(updatePage.cronogramaCiecytFasesFasesSelect);

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

        deleteDialog = new CronogramaCiecytFasesDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.cronogramaCiecytFases.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details CronogramaCiecytFases page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new CronogramaCiecytFasesDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit CronogramaCiecytFases page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.inicioFaseInput.clear();
        await updatePage.inicioFaseInput.sendKeys('01-01-2019');
        expect(await updatePage.inicioFaseInput.getAttribute('value')).to.eq('2019-01-01');

        await updatePage.finFaseInput.clear();
        await updatePage.finFaseInput.sendKeys('01-01-2019');
        expect(await updatePage.finFaseInput.getAttribute('value')).to.eq('2019-01-01');

        await updatePage.textoExplicativoInput.clear();
        await updatePage.textoExplicativoInput.sendKeys('modified');
        expect(await updatePage.textoExplicativoInput.getAttribute('value')).to.match(/modified/);

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
