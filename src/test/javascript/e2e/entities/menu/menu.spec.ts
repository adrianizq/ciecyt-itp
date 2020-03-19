/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import MenuComponentsPage, { MenuDeleteDialog } from './menu.page-object';
import MenuUpdatePage from './menu-update.page-object';
import MenuDetailsPage from './menu-details.page-object';

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

describe('Menu e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: MenuUpdatePage;
  let detailsPage: MenuDetailsPage;
  let listPage: MenuComponentsPage;
  let deleteDialog: MenuDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load Menus', async () => {
    await navBarPage.getEntityPage('menu');
    listPage = new MenuComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create Menu page', async () => {
      await listPage.createButton.click();
      updatePage = new MenuUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.menu.home.createOrEditLabel/);
    });

    it('should create and save Menus', async () => {
      await updatePage.nombreInput.sendKeys('nombre');
      expect(await updatePage.nombreInput.getAttribute('value')).to.match(/nombre/);

      await updatePage.urlInput.sendKeys('url');
      expect(await updatePage.urlInput.getAttribute('value')).to.match(/url/);

      await updatePage.iconoInput.sendKeys('icono');
      expect(await updatePage.iconoInput.getAttribute('value')).to.match(/icono/);

      const selectedActivo = await updatePage.activoInput.isSelected();
      if (selectedActivo) {
        await updatePage.activoInput.click();
        expect(await updatePage.activoInput.isSelected()).to.be.false;
      } else {
        await updatePage.activoInput.click();
        expect(await updatePage.activoInput.isSelected()).to.be.true;
      }

      // await  selectLastOption(updatePage.menuPadreSelect);

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

        deleteDialog = new MenuDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.menu.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details Menu page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new MenuDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit Menu page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.nombreInput.clear();
        await updatePage.nombreInput.sendKeys('modified');
        expect(await updatePage.nombreInput.getAttribute('value')).to.match(/modified/);

        await updatePage.urlInput.clear();
        await updatePage.urlInput.sendKeys('modified');
        expect(await updatePage.urlInput.getAttribute('value')).to.match(/modified/);

        await updatePage.iconoInput.clear();
        await updatePage.iconoInput.sendKeys('modified');
        expect(await updatePage.iconoInput.getAttribute('value')).to.match(/modified/);

        const selectedActivo = await updatePage.activoInput.isSelected();
        if (selectedActivo) {
          await updatePage.activoInput.click();
          expect(await updatePage.activoInput.isSelected()).to.be.false;
        } else {
          await updatePage.activoInput.click();
          expect(await updatePage.activoInput.isSelected()).to.be.true;
        }

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
