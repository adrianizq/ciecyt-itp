/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import ElementoProyectoComponentsPage, { ElementoProyectoDeleteDialog } from './elemento-proyecto.page-object';
import ElementoProyectoUpdatePage from './elemento-proyecto-update.page-object';
import ElementoProyectoDetailsPage from './elemento-proyecto-details.page-object';

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

describe('ElementoProyecto e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: ElementoProyectoUpdatePage;
  let detailsPage: ElementoProyectoDetailsPage;
  let listPage: ElementoProyectoComponentsPage;
  let deleteDialog: ElementoProyectoDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load ElementoProyectos', async () => {
    await navBarPage.getEntityPage('elemento-proyecto');
    listPage = new ElementoProyectoComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create ElementoProyecto page', async () => {
      await listPage.createButton.click();
      updatePage = new ElementoProyectoUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.elementoProyecto.home.createOrEditLabel/);
    });

    it('should create and save ElementoProyectos', async () => {
      await updatePage.datoInput.sendKeys('dato');
      expect(await updatePage.datoInput.getAttribute('value')).to.match(/dato/);

      await updatePage.elementoProyectoProyectoDescripcionInput.sendKeys('elementoProyectoProyectoDescripcion');
      expect(await updatePage.elementoProyectoProyectoDescripcionInput.getAttribute('value')).to.match(
        /elementoProyectoProyectoDescripcion/
      );

      // await  selectLastOption(updatePage.elementoProyectoElementoSelect);
      // await  selectLastOption(updatePage.elementoProyectoProyectoSelect);

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

        deleteDialog = new ElementoProyectoDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.elementoProyecto.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details ElementoProyecto page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new ElementoProyectoDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit ElementoProyecto page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.datoInput.clear();
        await updatePage.datoInput.sendKeys('modified');
        expect(await updatePage.datoInput.getAttribute('value')).to.match(/modified/);

        await updatePage.elementoProyectoProyectoDescripcionInput.clear();
        await updatePage.elementoProyectoProyectoDescripcionInput.sendKeys('modified');
        expect(await updatePage.elementoProyectoProyectoDescripcionInput.getAttribute('value')).to.match(/modified/);

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
