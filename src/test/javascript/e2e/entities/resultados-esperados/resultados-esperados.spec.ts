/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import ResultadosEsperadosComponentsPage, { ResultadosEsperadosDeleteDialog } from './resultados-esperados.page-object';
import ResultadosEsperadosUpdatePage from './resultados-esperados-update.page-object';
import ResultadosEsperadosDetailsPage from './resultados-esperados-details.page-object';

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

describe('ResultadosEsperados e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: ResultadosEsperadosUpdatePage;
  let detailsPage: ResultadosEsperadosDetailsPage;
  let listPage: ResultadosEsperadosComponentsPage;
  let deleteDialog: ResultadosEsperadosDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load ResultadosEsperados', async () => {
    await navBarPage.getEntityPage('resultados-esperados');
    listPage = new ResultadosEsperadosComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create ResultadosEsperados page', async () => {
      await listPage.createButton.click();
      updatePage = new ResultadosEsperadosUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.resultadosEsperados.home.createOrEditLabel/);
    });

    it('should create and save ResultadosEsperados', async () => {
      await updatePage.resultadoInput.sendKeys('resultado');
      expect(await updatePage.resultadoInput.getAttribute('value')).to.match(/resultado/);

      await updatePage.indicadorInput.sendKeys('indicador');
      expect(await updatePage.indicadorInput.getAttribute('value')).to.match(/indicador/);

      await updatePage.beneficiarioInput.sendKeys('beneficiario');
      expect(await updatePage.beneficiarioInput.getAttribute('value')).to.match(/beneficiario/);

      // await  selectLastOption(updatePage.resultadosEsperadosProyectoSelect);

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

        deleteDialog = new ResultadosEsperadosDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.resultadosEsperados.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details ResultadosEsperados page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new ResultadosEsperadosDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit ResultadosEsperados page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.resultadoInput.clear();
        await updatePage.resultadoInput.sendKeys('modified');
        expect(await updatePage.resultadoInput.getAttribute('value')).to.match(/modified/);

        await updatePage.indicadorInput.clear();
        await updatePage.indicadorInput.sendKeys('modified');
        expect(await updatePage.indicadorInput.getAttribute('value')).to.match(/modified/);

        await updatePage.beneficiarioInput.clear();
        await updatePage.beneficiarioInput.sendKeys('modified');
        expect(await updatePage.beneficiarioInput.getAttribute('value')).to.match(/modified/);

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
