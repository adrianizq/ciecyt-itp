<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('ciecytApp.cicloPropedeutico.home.title')" id="ciclo-propedeutico-heading">Ciclo Propedeuticos</span>
            <router-link :to="{name: 'CicloPropedeuticoCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-ciclo-propedeutico">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('ciecytApp.cicloPropedeutico.home.createLabel')">
                    Create a new Ciclo Propedeutico
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && cicloPropedeuticos && cicloPropedeuticos.length === 0">
            <span v-text="$t('ciecytApp.general.notFound')">No cicloPropedeuticos found</span>
        </div>
        <div class="table-responsive" v-if="cicloPropedeuticos && cicloPropedeuticos.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('ciclo')"><span v-text="$t('ciecytApp.cicloPropedeutico.ciclo')">Ciclo</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('modalidadId')"><span v-text="$t('ciecytApp.cicloPropedeutico.modalidad')">Modalidad</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('cicloPropedeuticoAcuerdoAcuerdo')"><span v-text="$t('ciecytApp.cicloPropedeutico.cicloPropedeuticoAcuerdo')">Ciclo Propedeutico Acuerdo</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="cicloPropedeutico in cicloPropedeuticos"
                    :key="cicloPropedeutico.id">
                    <td>
                        <router-link :to="{name: 'CicloPropedeuticoView', params: {cicloPropedeuticoId: cicloPropedeutico.id}}">{{cicloPropedeutico.id}}</router-link>
                    </td>
                    <td>{{cicloPropedeutico.ciclo}}</td>
                    <td>
                        <div v-if="cicloPropedeutico.modalidadId">
                            <router-link :to="{name: 'ModalidadView', params: {modalidadId: cicloPropedeutico.modalidadId}}">{{cicloPropedeutico.modalidadId}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="cicloPropedeutico.cicloPropedeuticoAcuerdoId">
                            <router-link :to="{name: 'AcuerdoView', params: {acuerdoId: cicloPropedeutico.cicloPropedeuticoAcuerdoId}}">{{cicloPropedeutico.cicloPropedeuticoAcuerdoAcuerdo}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'CicloPropedeuticoView', params: {cicloPropedeuticoId: cicloPropedeutico.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'CicloPropedeuticoEdit', params: {cicloPropedeuticoId: cicloPropedeutico.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(cicloPropedeutico)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="ciecytApp.cicloPropedeutico.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-cicloPropedeutico-heading" v-bind:title="$t('ciecytApp.cicloPropedeutico.delete.question')">Are you sure you want to delete this Ciclo Propedeutico?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-cicloPropedeutico" v-text="$t('entity.action.delete')" v-on:click="removeCicloPropedeutico()">Delete</button>
            </div>
        </b-modal>
        <div v-show="cicloPropedeuticos && cicloPropedeuticos.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./ciclo-propedeutico.component.ts">
</script>
