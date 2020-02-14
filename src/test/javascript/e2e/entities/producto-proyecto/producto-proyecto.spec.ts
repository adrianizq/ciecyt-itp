/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import ProductoProyectoComponentsPage, { ProductoProyectoDeleteDialog } from './producto-proyecto.page-object';
import ProductoProyectoUpdatePage from './producto-proyecto-update.page-object';
import ProductoProyectoDetailsPage from './producto-proyecto-details.page-object';

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

describe('ProductoProyecto e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: ProductoProyectoUpdatePage;
  let detailsPage: ProductoProyectoDetailsPage;
  let listPage: ProductoProyectoComponentsPage;
  let deleteDialog: ProductoProyectoDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load ProductoProyectos', async () => {
    await navBarPage.getEntityPage('producto-proyecto');
    listPage = new ProductoProyectoComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create ProductoProyecto page', async () => {
      await listPage.createButton.click();
      updatePage = new ProductoProyectoUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.productoProyecto.home.createOrEditLabel/);
    });

    it('should create and save ProductoProyectos', async () => {
      const selectedAplica = await updatePage.aplicaInput.isSelected();
      if (selectedAplica) {
        await updatePage.aplicaInput.click();
        expect(await updatePage.aplicaInput.isSelected()).to.be.false;
      } else {
        await updatePage.aplicaInput.click();
        expect(await updatePage.aplicaInput.isSelected()).to.be.true;
      }

      await updatePage.descripcionInput.sendKeys('descripcion');
      expect(await updatePage.descripcionInput.getAttribute('value')).to.match(/descripcion/);

      // await  selectLastOption(updatePage.productoProyectoProductoSelect);
      // await  selectLastOption(updatePage.productoProyectoProyectoSelect);

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

        deleteDialog = new ProductoProyectoDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.productoProyecto.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details ProductoProyecto page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new ProductoProyectoDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit ProductoProyecto page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        const selectedAplica = await updatePage.aplicaInput.isSelected();
        if (selectedAplica) {
          await updatePage.aplicaInput.click();
          expect(await updatePage.aplicaInput.isSelected()).to.be.false;
        } else {
          await updatePage.aplicaInput.click();
          expect(await updatePage.aplicaInput.isSelected()).to.be.true;
        }

        await updatePage.descripcionInput.clear();
        await updatePage.descripcionInput.sendKeys('modified');
        expect(await updatePage.descripcionInput.getAttribute('value')).to.match(/modified/);

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
