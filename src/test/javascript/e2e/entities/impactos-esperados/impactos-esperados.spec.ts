/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import ImpactosEsperadosComponentsPage, { ImpactosEsperadosDeleteDialog } from './impactos-esperados.page-object';
import ImpactosEsperadosUpdatePage from './impactos-esperados-update.page-object';
import ImpactosEsperadosDetailsPage from './impactos-esperados-details.page-object';

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

describe('ImpactosEsperados e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: ImpactosEsperadosUpdatePage;
  let detailsPage: ImpactosEsperadosDetailsPage;
  let listPage: ImpactosEsperadosComponentsPage;
  let deleteDialog: ImpactosEsperadosDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load ImpactosEsperados', async () => {
    await navBarPage.getEntityPage('impactos-esperados');
    listPage = new ImpactosEsperadosComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create ImpactosEsperados page', async () => {
      await listPage.createButton.click();
      updatePage = new ImpactosEsperadosUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.impactosEsperados.home.createOrEditLabel/);
    });

    it('should create and save ImpactosEsperados', async () => {
      await updatePage.impactoInput.sendKeys('impacto');
      expect(await updatePage.impactoInput.getAttribute('value')).to.match(/impacto/);

      await updatePage.plazoInput.sendKeys('5');
      expect(await updatePage.plazoInput.getAttribute('value')).to.eq('5');

      await updatePage.indicadorInput.sendKeys('indicador');
      expect(await updatePage.indicadorInput.getAttribute('value')).to.match(/indicador/);

      await updatePage.supuestosInput.sendKeys('supuestos');
      expect(await updatePage.supuestosInput.getAttribute('value')).to.match(/supuestos/);

      await updatePage.ordenVistaInput.sendKeys('5');
      expect(await updatePage.ordenVistaInput.getAttribute('value')).to.eq('5');

      // await  selectLastOption(updatePage.impactosEsperadoProyectoSelect);

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

        deleteDialog = new ImpactosEsperadosDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.impactosEsperados.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details ImpactosEsperados page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new ImpactosEsperadosDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit ImpactosEsperados page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.impactoInput.clear();
        await updatePage.impactoInput.sendKeys('modified');
        expect(await updatePage.impactoInput.getAttribute('value')).to.match(/modified/);

        await clear(updatePage.plazoInput);
        await updatePage.plazoInput.sendKeys('6');
        expect(await updatePage.plazoInput.getAttribute('value')).to.eq('6');

        await updatePage.indicadorInput.clear();
        await updatePage.indicadorInput.sendKeys('modified');
        expect(await updatePage.indicadorInput.getAttribute('value')).to.match(/modified/);

        await updatePage.supuestosInput.clear();
        await updatePage.supuestosInput.sendKeys('modified');
        expect(await updatePage.supuestosInput.getAttribute('value')).to.match(/modified/);

        await clear(updatePage.ordenVistaInput);
        await updatePage.ordenVistaInput.sendKeys('6');
        expect(await updatePage.ordenVistaInput.getAttribute('value')).to.eq('6');

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
