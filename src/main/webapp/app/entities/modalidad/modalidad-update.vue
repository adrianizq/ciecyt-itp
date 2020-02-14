<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="ciecytApp.modalidad.home.createOrEditLabel" v-text="$t('ciecytApp.modalidad.home.createOrEditLabel')">Create or edit a Modalidad</h2>
                <div>
                    <div class="form-group" v-if="modalidad.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="modalidad.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.modalidad.modalidad')" for="modalidad-modalidad">Modalidad</label>
                        <input type="text" class="form-control" name="modalidad" id="modalidad-modalidad"
                            :class="{'valid': !$v.modalidad.modalidad.$invalid, 'invalid': $v.modalidad.modalidad.$invalid }" v-model="$v.modalidad.modalidad.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-bind:value="$t('ciecytApp.modalidad.modalidadAcuerdo')" for="modalidad-modalidadAcuerdo">Modalidad Acuerdo</label>
                        <select class="form-control" id="modalidad-modalidadAcuerdo" name="modalidadAcuerdo" v-model="modalidad.modalidadAcuerdoId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="acuerdoOption.id" v-for="acuerdoOption in acuerdos" :key="acuerdoOption.id">{{acuerdoOption.acuerdo}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.modalidad.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./modalidad-update.component.ts">
</script>
