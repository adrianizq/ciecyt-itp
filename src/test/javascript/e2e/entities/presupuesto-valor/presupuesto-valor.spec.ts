/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import PresupuestoValorComponentsPage, { PresupuestoValorDeleteDialog } from './presupuesto-valor.page-object';
import PresupuestoValorUpdatePage from './presupuesto-valor-update.page-object';
import PresupuestoValorDetailsPage from './presupuesto-valor-details.page-object';

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

describe('PresupuestoValor e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: PresupuestoValorUpdatePage;
  let detailsPage: PresupuestoValorDetailsPage;
  let listPage: PresupuestoValorComponentsPage;
  let deleteDialog: PresupuestoValorDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load PresupuestoValors', async () => {
    await navBarPage.getEntityPage('presupuesto-valor');
    listPage = new PresupuestoValorComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create PresupuestoValor page', async () => {
      await listPage.createButton.click();
      updatePage = new PresupuestoValorUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.presupuestoValor.home.createOrEditLabel/);
    });

    it('should create and save PresupuestoValors', async () => {
      await updatePage.descripcionInput.sendKeys('descripcion');
      expect(await updatePage.descripcionInput.getAttribute('value')).to.match(/descripcion/);

      await updatePage.justificacionInput.sendKeys('justificacion');
      expect(await updatePage.justificacionInput.getAttribute('value')).to.match(/justificacion/);

      await updatePage.cantidadInput.sendKeys('5');
      expect(await updatePage.cantidadInput.getAttribute('value')).to.eq('5');

      await updatePage.valorUnitarioInput.sendKeys('5');
      expect(await updatePage.valorUnitarioInput.getAttribute('value')).to.eq('5');

      await updatePage.especieInput.sendKeys('5');
      expect(await updatePage.especieInput.getAttribute('value')).to.eq('5');

      await updatePage.dineroInput.sendKeys('5');
      expect(await updatePage.dineroInput.getAttribute('value')).to.eq('5');

      // await  selectLastOption(updatePage.presupuestoValorRubroSelect);
      // await  selectLastOption(updatePage.presupuestoValorProyectoSelect);
      // await  selectLastOption(updatePage.presupuestoValorEntidadSelect);

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

        deleteDialog = new PresupuestoValorDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.presupuestoValor.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details PresupuestoValor page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new PresupuestoValorDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit PresupuestoValor page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.descripcionInput.clear();
        await updatePage.descripcionInput.sendKeys('modified');
        expect(await updatePage.descripcionInput.getAttribute('value')).to.match(/modified/);

        await updatePage.justificacionInput.clear();
        await updatePage.justificacionInput.sendKeys('modified');
        expect(await updatePage.justificacionInput.getAttribute('value')).to.match(/modified/);

        await clear(updatePage.cantidadInput);
        await updatePage.cantidadInput.sendKeys('6');
        expect(await updatePage.cantidadInput.getAttribute('value')).to.eq('6');

        await clear(updatePage.valorUnitarioInput);
        await updatePage.valorUnitarioInput.sendKeys('6');
        expect(await updatePage.valorUnitarioInput.getAttribute('value')).to.eq('6');

        await clear(updatePage.especieInput);
        await updatePage.especieInput.sendKeys('6');
        expect(await updatePage.especieInput.getAttribute('value')).to.eq('6');

        await clear(updatePage.dineroInput);
        await updatePage.dineroInput.sendKeys('6');
        expect(await updatePage.dineroInput.getAttribute('value')).to.eq('6');

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
