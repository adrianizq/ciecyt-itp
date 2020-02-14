/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import FichaTecnicaComponentsPage, { FichaTecnicaDeleteDialog } from './ficha-tecnica.page-object';
import FichaTecnicaUpdatePage from './ficha-tecnica-update.page-object';
import FichaTecnicaDetailsPage from './ficha-tecnica-details.page-object';

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

describe('FichaTecnica e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: FichaTecnicaUpdatePage;
  let detailsPage: FichaTecnicaDetailsPage;
  let listPage: FichaTecnicaComponentsPage;
  let deleteDialog: FichaTecnicaDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load FichaTecnicas', async () => {
    await navBarPage.getEntityPage('ficha-tecnica');
    listPage = new FichaTecnicaComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create FichaTecnica page', async () => {
      await listPage.createButton.click();
      updatePage = new FichaTecnicaUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.fichaTecnica.home.createOrEditLabel/);
    });

    it('should create and save FichaTecnicas', async () => {
      await updatePage.tituloProfesionalInput.sendKeys('tituloProfesional');
      expect(await updatePage.tituloProfesionalInput.getAttribute('value')).to.match(/tituloProfesional/);

      await updatePage.tituloPostgradoInput.sendKeys('tituloPostgrado');
      expect(await updatePage.tituloPostgradoInput.getAttribute('value')).to.match(/tituloPostgrado/);

      await updatePage.experienciaInput.sendKeys('experiencia');
      expect(await updatePage.experienciaInput.getAttribute('value')).to.match(/experiencia/);

      // await  selectLastOption(updatePage.fichaTecnicaUserSelect);

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

        deleteDialog = new FichaTecnicaDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.fichaTecnica.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details FichaTecnica page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new FichaTecnicaDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit FichaTecnica page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.tituloProfesionalInput.clear();
        await updatePage.tituloProfesionalInput.sendKeys('modified');
        expect(await updatePage.tituloProfesionalInput.getAttribute('value')).to.match(/modified/);

        await updatePage.tituloPostgradoInput.clear();
        await updatePage.tituloPostgradoInput.sendKeys('modified');
        expect(await updatePage.tituloPostgradoInput.getAttribute('value')).to.match(/modified/);

        await updatePage.experienciaInput.clear();
        await updatePage.experienciaInput.sendKeys('modified');
        expect(await updatePage.experienciaInput.getAttribute('value')).to.match(/modified/);

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
