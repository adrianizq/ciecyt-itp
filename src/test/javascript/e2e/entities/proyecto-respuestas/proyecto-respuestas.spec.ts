/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import ProyectoRespuestasComponentsPage, { ProyectoRespuestasDeleteDialog } from './proyecto-respuestas.page-object';
import ProyectoRespuestasUpdatePage from './proyecto-respuestas-update.page-object';
import ProyectoRespuestasDetailsPage from './proyecto-respuestas-details.page-object';

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

describe('ProyectoRespuestas e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: ProyectoRespuestasUpdatePage;
  let detailsPage: ProyectoRespuestasDetailsPage;
  let listPage: ProyectoRespuestasComponentsPage;
  let deleteDialog: ProyectoRespuestasDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load ProyectoRespuestas', async () => {
    await navBarPage.getEntityPage('proyecto-respuestas');
    listPage = new ProyectoRespuestasComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create ProyectoRespuestas page', async () => {
      await listPage.createButton.click();
      updatePage = new ProyectoRespuestasUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.proyectoRespuestas.home.createOrEditLabel/);
    });

    it('should create and save ProyectoRespuestas', async () => {
      await selectLastOption(updatePage.respuestaSelect);

      await updatePage.observacionesInput.sendKeys('observaciones');
      expect(await updatePage.observacionesInput.getAttribute('value')).to.match(/observaciones/);

      const selectedViable = await updatePage.viableInput.isSelected();
      if (selectedViable) {
        await updatePage.viableInput.click();
        expect(await updatePage.viableInput.isSelected()).to.be.false;
      } else {
        await updatePage.viableInput.click();
        expect(await updatePage.viableInput.isSelected()).to.be.true;
      }

      // await  selectLastOption(updatePage.proyectoRespuestasPreguntaSelect);
      // await  selectLastOption(updatePage.proyectoRespuestasProyectoSelect);

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

        deleteDialog = new ProyectoRespuestasDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.proyectoRespuestas.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details ProyectoRespuestas page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new ProyectoRespuestasDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit ProyectoRespuestas page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.observacionesInput.clear();
        await updatePage.observacionesInput.sendKeys('modified');
        expect(await updatePage.observacionesInput.getAttribute('value')).to.match(/modified/);

        const selectedViable = await updatePage.viableInput.isSelected();
        if (selectedViable) {
          await updatePage.viableInput.click();
          expect(await updatePage.viableInput.isSelected()).to.be.false;
        } else {
          await updatePage.viableInput.click();
          expect(await updatePage.viableInput.isSelected()).to.be.true;
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
