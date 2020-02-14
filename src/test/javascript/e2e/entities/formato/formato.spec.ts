/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import FormatoComponentsPage, { FormatoDeleteDialog } from './formato.page-object';
import FormatoUpdatePage from './formato-update.page-object';
import FormatoDetailsPage from './formato-details.page-object';

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

describe('Formato e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: FormatoUpdatePage;
  let detailsPage: FormatoDetailsPage;
  let listPage: FormatoComponentsPage;
  let deleteDialog: FormatoDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load Formatoes', async () => {
    await navBarPage.getEntityPage('formato');
    listPage = new FormatoComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create Formato page', async () => {
      await listPage.createButton.click();
      updatePage = new FormatoUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.formato.home.createOrEditLabel/);
    });

    it('should create and save Formatoes', async () => {
      await updatePage.formatoInput.sendKeys('formato');
      expect(await updatePage.formatoInput.getAttribute('value')).to.match(/formato/);

      await updatePage.versionInput.sendKeys('version');
      expect(await updatePage.versionInput.getAttribute('value')).to.match(/version/);

      await updatePage.codigoInput.sendKeys('codigo');
      expect(await updatePage.codigoInput.getAttribute('value')).to.match(/codigo/);

      await updatePage.fechaInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaInput.getAttribute('value')).to.eq('2001-01-01');

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

        deleteDialog = new FormatoDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.formato.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details Formato page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new FormatoDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit Formato page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.formatoInput.clear();
        await updatePage.formatoInput.sendKeys('modified');
        expect(await updatePage.formatoInput.getAttribute('value')).to.match(/modified/);

        await updatePage.versionInput.clear();
        await updatePage.versionInput.sendKeys('modified');
        expect(await updatePage.versionInput.getAttribute('value')).to.match(/modified/);

        await updatePage.codigoInput.clear();
        await updatePage.codigoInput.sendKeys('modified');
        expect(await updatePage.codigoInput.getAttribute('value')).to.match(/modified/);

        await updatePage.fechaInput.clear();
        await updatePage.fechaInput.sendKeys('01-01-2019');
        expect(await updatePage.fechaInput.getAttribute('value')).to.eq('2019-01-01');

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
