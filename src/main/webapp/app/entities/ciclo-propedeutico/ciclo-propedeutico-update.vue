<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="ciecytApp.cicloPropedeutico.home.createOrEditLabel" v-text="$t('ciecytApp.cicloPropedeutico.home.createOrEditLabel')">Create or edit a CicloPropedeutico</h2>
                <div>
                    <div class="form-group" v-if="cicloPropedeutico.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="cicloPropedeutico.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.cicloPropedeutico.ciclo')" for="ciclo-propedeutico-ciclo">Ciclo</label>
                        <input type="text" class="form-control" name="ciclo" id="ciclo-propedeutico-ciclo"
                            :class="{'valid': !$v.cicloPropedeutico.ciclo.$invalid, 'invalid': $v.cicloPropedeutico.ciclo.$invalid }" v-model="$v.cicloPropedeutico.ciclo.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-bind:value="$t('ciecytApp.cicloPropedeutico.modalidad')" for="ciclo-propedeutico-modalidad">Modalidad</label>
                        <select class="form-control" id="ciclo-propedeutico-modalidad" name="modalidad" v-model="cicloPropedeutico.modalidadId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="modalidadOption.id" v-for="modalidadOption in modalidads" :key="modalidadOption.id">{{modalidadOption.modalidad}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-bind:value="$t('ciecytApp.cicloPropedeutico.cicloPropedeuticoAcuerdo')" for="ciclo-propedeutico-cicloPropedeuticoAcuerdo">
                            Acuerdo
                        </label>
                        <select class="form-control" id="ciclo-propedeutico-cicloPropedeuticoAcuerdo" name="cicloPropedeuticoAcuerdo" v-model="cicloPropedeutico.cicloPropedeuticoAcuerdoId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="acuerdoOption.id" v-for="acuerdoOption in acuerdos" :key="acuerdoOption.id">{{acuerdoOption.acuerdo}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.cicloPropedeutico.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./ciclo-propedeutico-update.component.ts">
</script>
