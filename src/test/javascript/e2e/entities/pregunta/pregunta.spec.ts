/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import PreguntaComponentsPage, { PreguntaDeleteDialog } from './pregunta.page-object';
import PreguntaUpdatePage from './pregunta-update.page-object';
import PreguntaDetailsPage from './pregunta-details.page-object';

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

describe('Pregunta e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: PreguntaUpdatePage;
  let detailsPage: PreguntaDetailsPage;
  let listPage: PreguntaComponentsPage;
  let deleteDialog: PreguntaDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load Preguntas', async () => {
    await navBarPage.getEntityPage('pregunta');
    listPage = new PreguntaComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create Pregunta page', async () => {
      await listPage.createButton.click();
      updatePage = new PreguntaUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.pregunta.home.createOrEditLabel/);
    });

    it('should create and save Preguntas', async () => {
      await updatePage.encabezadoInput.sendKeys('encabezado');
      expect(await updatePage.encabezadoInput.getAttribute('value')).to.match(/encabezado/);

      await updatePage.descripcionInput.sendKeys('descripcion');
      expect(await updatePage.descripcionInput.getAttribute('value')).to.match(/descripcion/);

      await updatePage.preguntaInput.sendKeys('pregunta');
      expect(await updatePage.preguntaInput.getAttribute('value')).to.match(/pregunta/);

      // await  selectLastOption(updatePage.preguntaTipoPreguntaSelect);
      // await  selectLastOption(updatePage.preguntaModalidadSelect);
      // await  selectLastOption(updatePage.preguntaRolesModalidadSelect);

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

        deleteDialog = new PreguntaDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.pregunta.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details Pregunta page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new PreguntaDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit Pregunta page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.encabezadoInput.clear();
        await updatePage.encabezadoInput.sendKeys('modified');
        expect(await updatePage.encabezadoInput.getAttribute('value')).to.match(/modified/);

        await updatePage.descripcionInput.clear();
        await updatePage.descripcionInput.sendKeys('modified');
        expect(await updatePage.descripcionInput.getAttribute('value')).to.match(/modified/);

        await updatePage.preguntaInput.clear();
        await updatePage.preguntaInput.sendKeys('modified');
        expect(await updatePage.preguntaInput.getAttribute('value')).to.match(/modified/);

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
