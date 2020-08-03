/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import EntidadFinanciadoraComponentsPage, { EntidadFinanciadoraDeleteDialog } from './entidad-financiadora.page-object';
import EntidadFinanciadoraUpdatePage from './entidad-financiadora-update.page-object';
import EntidadFinanciadoraDetailsPage from './entidad-financiadora-details.page-object';

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

describe('EntidadFinanciadora e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: EntidadFinanciadoraUpdatePage;
  let detailsPage: EntidadFinanciadoraDetailsPage;
  let listPage: EntidadFinanciadoraComponentsPage;
  let deleteDialog: EntidadFinanciadoraDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load EntidadFinanciadoras', async () => {
    await navBarPage.getEntityPage('entidad-financiadora');
    listPage = new EntidadFinanciadoraComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create EntidadFinanciadora page', async () => {
      await listPage.createButton.click();
      updatePage = new EntidadFinanciadoraUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.entidadFinanciadora.home.createOrEditLabel/);
    });

    it('should create and save EntidadFinanciadoras', async () => {
      await updatePage.valorInput.sendKeys('5');
      expect(await updatePage.valorInput.getAttribute('value')).to.eq('5');

      const selectedAprobada = await updatePage.aprobadaInput.isSelected();
      if (selectedAprobada) {
        await updatePage.aprobadaInput.click();
        expect(await updatePage.aprobadaInput.isSelected()).to.be.false;
      } else {
        await updatePage.aprobadaInput.click();
        expect(await updatePage.aprobadaInput.isSelected()).to.be.true;
      }

      // await  selectLastOption(updatePage.entidadFinanciadoraEntidadSelect);
      // await  selectLastOption(updatePage.entidadFinanciadoraProyectoSelect);

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

        deleteDialog = new EntidadFinanciadoraDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.entidadFinanciadora.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details EntidadFinanciadora page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new EntidadFinanciadoraDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit EntidadFinanciadora page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await clear(updatePage.valorInput);
        await updatePage.valorInput.sendKeys('6');
        expect(await updatePage.valorInput.getAttribute('value')).to.eq('6');

        const selectedAprobada = await updatePage.aprobadaInput.isSelected();
        if (selectedAprobada) {
          await updatePage.aprobadaInput.click();
          expect(await updatePage.aprobadaInput.isSelected()).to.be.false;
        } else {
          await updatePage.aprobadaInput.click();
          expect(await updatePage.aprobadaInput.isSelected()).to.be.true;
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
