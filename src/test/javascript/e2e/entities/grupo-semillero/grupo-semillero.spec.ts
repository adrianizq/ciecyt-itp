/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import GrupoSemilleroComponentsPage, { GrupoSemilleroDeleteDialog } from './grupo-semillero.page-object';
import GrupoSemilleroUpdatePage from './grupo-semillero-update.page-object';
import GrupoSemilleroDetailsPage from './grupo-semillero-details.page-object';

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

describe('GrupoSemillero e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: GrupoSemilleroUpdatePage;
  let detailsPage: GrupoSemilleroDetailsPage;
  let listPage: GrupoSemilleroComponentsPage;
  let deleteDialog: GrupoSemilleroDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load GrupoSemilleros', async () => {
    await navBarPage.getEntityPage('grupo-semillero');
    listPage = new GrupoSemilleroComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create GrupoSemillero page', async () => {
      await listPage.createButton.click();
      updatePage = new GrupoSemilleroUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.grupoSemillero.home.createOrEditLabel/);
    });

    it('should create and save GrupoSemilleros', async () => {
      await updatePage.nombreInput.sendKeys('nombre');
      expect(await updatePage.nombreInput.getAttribute('value')).to.match(/nombre/);

      const selectedTipo = await updatePage.tipoInput.isSelected();
      if (selectedTipo) {
        await updatePage.tipoInput.click();
        expect(await updatePage.tipoInput.isSelected()).to.be.false;
      } else {
        await updatePage.tipoInput.click();
        expect(await updatePage.tipoInput.isSelected()).to.be.true;
      }

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

        deleteDialog = new GrupoSemilleroDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.grupoSemillero.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details GrupoSemillero page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new GrupoSemilleroDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit GrupoSemillero page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.nombreInput.clear();
        await updatePage.nombreInput.sendKeys('modified');
        expect(await updatePage.nombreInput.getAttribute('value')).to.match(/modified/);

        const selectedTipo = await updatePage.tipoInput.isSelected();
        if (selectedTipo) {
          await updatePage.tipoInput.click();
          expect(await updatePage.tipoInput.isSelected()).to.be.false;
        } else {
          await updatePage.tipoInput.click();
          expect(await updatePage.tipoInput.isSelected()).to.be.true;
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
