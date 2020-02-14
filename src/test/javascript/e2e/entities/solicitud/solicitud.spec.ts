/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SolicitudComponentsPage, { SolicitudDeleteDialog } from './solicitud.page-object';
import SolicitudUpdatePage from './solicitud-update.page-object';
import SolicitudDetailsPage from './solicitud-details.page-object';

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

describe('Solicitud e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: SolicitudUpdatePage;
  let detailsPage: SolicitudDetailsPage;
  let listPage: SolicitudComponentsPage;
  let deleteDialog: SolicitudDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load Solicituds', async () => {
    await navBarPage.getEntityPage('solicitud');
    listPage = new SolicitudComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create Solicitud page', async () => {
      await listPage.createButton.click();
      updatePage = new SolicitudUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/ciecytApp.solicitud.home.createOrEditLabel/);
    });

    it('should create and save Solicituds', async () => {
      const selectedEstado = await updatePage.estadoInput.isSelected();
      if (selectedEstado) {
        await updatePage.estadoInput.click();
        expect(await updatePage.estadoInput.isSelected()).to.be.false;
      } else {
        await updatePage.estadoInput.click();
        expect(await updatePage.estadoInput.isSelected()).to.be.true;
      }

      await updatePage.asuntoInput.sendKeys('asunto');
      expect(await updatePage.asuntoInput.getAttribute('value')).to.match(/asunto/);

      await updatePage.textoSolicitudInput.sendKeys('textoSolicitud');
      expect(await updatePage.textoSolicitudInput.getAttribute('value')).to.match(/textoSolicitud/);

      await updatePage.fechaSolicitudInput.sendKeys('01-01-2001');
      expect(await updatePage.fechaSolicitudInput.getAttribute('value')).to.eq('2001-01-01');

      // await  selectLastOption(updatePage.solicitudIntegranteProyectoSelect);

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

        deleteDialog = new SolicitudDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/ciecytApp.solicitud.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details Solicitud page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new SolicitudDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit Solicitud page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        const selectedEstado = await updatePage.estadoInput.isSelected();
        if (selectedEstado) {
          await updatePage.estadoInput.click();
          expect(await updatePage.estadoInput.isSelected()).to.be.false;
        } else {
          await updatePage.estadoInput.click();
          expect(await updatePage.estadoInput.isSelected()).to.be.true;
        }

        await updatePage.asuntoInput.clear();
        await updatePage.asuntoInput.sendKeys('modified');
        expect(await updatePage.asuntoInput.getAttribute('value')).to.match(/modified/);

        await updatePage.textoSolicitudInput.clear();
        await updatePage.textoSolicitudInput.sendKeys('modified');
        expect(await updatePage.textoSolicitudInput.getAttribute('value')).to.match(/modified/);

        await updatePage.fechaSolicitudInput.clear();
        await updatePage.fechaSolicitudInput.sendKeys('01-01-2019');
        expect(await updatePage.fechaSolicitudInput.getAttribute('value')).to.eq('2019-01-01');

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
