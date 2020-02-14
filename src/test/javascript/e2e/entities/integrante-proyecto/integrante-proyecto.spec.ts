/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import IntegranteProyectoComponentsPage, { IntegranteProyectoDeleteDialog } from './integrante-proyecto.page-object';
import IntegranteProyectoUpdatePage from './integrante-proyecto-update.page-object';
import IntegranteProyectoDetailsPage from './integrante-proyecto-details.page-object';

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

describe('IntegranteProyecto e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: IntegranteProyectoUpdatePage;
  let detailsPage: IntegranteProyectoDetailsPage;
  let listPage: IntegranteProyectoComponentsPage;
  let deleteDialog: IntegranteProyectoDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load IntegranteProyectos', async () => {
    await navBarPage.getEntityPage('integrante-proyecto');
    listPage = new IntegranteProyectoComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create IntegranteProyecto page', async () => {
      await listPage.createButton.click();
      updatePage = new IntegranteProyectoUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.integranteProyecto.home.createOrEditLabel/);
    });

    it('should create and save IntegranteProyectos', async () => {
      await updatePage.integranteInput.sendKeys('integrante');
      expect(await updatePage.integranteInput.getAttribute('value')).to.match(/integrante/);

      await updatePage.descripcionInput.sendKeys('descripcion');
      expect(await updatePage.descripcionInput.getAttribute('value')).to.match(/descripcion/);

      // await  selectLastOption(updatePage.integranteProyectoUserSelect);
      // await  selectLastOption(updatePage.integranteProyectoProyectoSelect);
      // await  selectLastOption(updatePage.integranteProyectoRolesModalidadSelect);

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

        deleteDialog = new IntegranteProyectoDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.integranteProyecto.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details IntegranteProyecto page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new IntegranteProyectoDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit IntegranteProyecto page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.integranteInput.clear();
        await updatePage.integranteInput.sendKeys('modified');
        expect(await updatePage.integranteInput.getAttribute('value')).to.match(/modified/);

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
