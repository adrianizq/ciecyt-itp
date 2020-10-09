<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="ciecytApp.adjuntoProyectoFase.home.createOrEditLabel" v-text="$t('ciecytApp.adjuntoProyectoFase.home.createOrEditLabel')">Create or edit a AdjuntoProyectoFase</h2>
                <div>
                    <div class="form-group" v-if="adjuntoProyectoFase.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="adjuntoProyectoFase.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.adjuntoProyectoFase.nombreAdjunto')" for="adjunto-proyecto-fase-nombreAdjunto">Nombre Adjunto</label>
                        <input type="text" class="form-control" name="nombreAdjunto" id="adjunto-proyecto-fase-nombreAdjunto"
                            :class="{'valid': !$v.adjuntoProyectoFase.nombreAdjunto.$invalid, 'invalid': $v.adjuntoProyectoFase.nombreAdjunto.$invalid }" v-model="$v.adjuntoProyectoFase.nombreAdjunto.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.adjuntoProyectoFase.fechaCreacion')" for="adjunto-proyecto-fase-fechaCreacion">Fecha Creacion</label>
                        <div class="input-group">
                            <input id="adjunto-proyecto-fase-fechaCreacion" type="date" class="form-control" name="fechaCreacion"  :class="{'valid': !$v.adjuntoProyectoFase.fechaCreacion.$invalid, 'invalid': $v.adjuntoProyectoFase.fechaCreacion.$invalid }"
                            v-model="$v.adjuntoProyectoFase.fechaCreacion.$model"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.adjuntoProyectoFase.fechaModificacion')" for="adjunto-proyecto-fase-fechaModificacion">Fecha Modificacion</label>
                        <div class="input-group">
                            <input id="adjunto-proyecto-fase-fechaModificacion" type="date" class="form-control" name="fechaModificacion"  :class="{'valid': !$v.adjuntoProyectoFase.fechaModificacion.$invalid, 'invalid': $v.adjuntoProyectoFase.fechaModificacion.$invalid }"
                            v-model="$v.adjuntoProyectoFase.fechaModificacion.$model"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.adjuntoProyectoFase.estadoAdjunto')" for="adjunto-proyecto-fase-estadoAdjunto">Estado Adjunto</label>
                        <input type="number" class="form-control" name="estadoAdjunto" id="adjunto-proyecto-fase-estadoAdjunto"
                            :class="{'valid': !$v.adjuntoProyectoFase.estadoAdjunto.$invalid, 'invalid': $v.adjuntoProyectoFase.estadoAdjunto.$invalid }" v-model.number="$v.adjuntoProyectoFase.estadoAdjunto.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.adjuntoProyectoFase.adjuntoProyectoFase')" for="adjunto-proyecto-fase-adjuntoProyectoFase">Adjunto Proyecto Fase</label>
                        <input type="text" class="form-control" name="adjuntoProyectoFase" id="adjunto-proyecto-fase-adjuntoProyectoFase"
                            :class="{'valid': !$v.adjuntoProyectoFase.adjuntoProyectoFase.$invalid, 'invalid': $v.adjuntoProyectoFase.adjuntoProyectoFase.$invalid }" v-model="$v.adjuntoProyectoFase.adjuntoProyectoFase.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.adjuntoProyectoFase.nombreArchivoOriginal')" for="adjunto-proyecto-fase-nombreArchivoOriginal">Nombre Archivo Original</label>
                        <input type="text" class="form-control" name="nombreArchivoOriginal" id="adjunto-proyecto-fase-nombreArchivoOriginal"
                            :class="{'valid': !$v.adjuntoProyectoFase.nombreArchivoOriginal.$invalid, 'invalid': $v.adjuntoProyectoFase.nombreArchivoOriginal.$invalid }" v-model="$v.adjuntoProyectoFase.nombreArchivoOriginal.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.adjuntoProyectoFase.fechaInicio')" for="adjunto-proyecto-fase-fechaInicio">Fecha Inicio</label>
                        <div class="input-group">
                            <input id="adjunto-proyecto-fase-fechaInicio" type="date" class="form-control" name="fechaInicio"  :class="{'valid': !$v.adjuntoProyectoFase.fechaInicio.$invalid, 'invalid': $v.adjuntoProyectoFase.fechaInicio.$invalid }"
                            v-model="$v.adjuntoProyectoFase.fechaInicio.$model"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.adjuntoProyectoFase.fechaFin')" for="adjunto-proyecto-fase-fechaFin">Fecha Fin</label>
                        <div class="input-group">
                            <input id="adjunto-proyecto-fase-fechaFin" type="date" class="form-control" name="fechaFin"  :class="{'valid': !$v.adjuntoProyectoFase.fechaFin.$invalid, 'invalid': $v.adjuntoProyectoFase.fechaFin.$invalid }"
                            v-model="$v.adjuntoProyectoFase.fechaFin.$model"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-bind:value="$t('ciecytApp.adjuntoProyectoFase.adjuntoProyectoFaseProyectoFase')" for="adjunto-proyecto-fase-adjuntoProyectoFaseProyectoFase">Adjunto Proyecto Fase Proyecto Fase</label>
                        <select class="form-control" id="adjunto-proyecto-fase-adjuntoProyectoFaseProyectoFase" name="adjuntoProyectoFaseProyectoFase" v-model="adjuntoProyectoFase.adjuntoProyectoFaseProyectoFaseId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="proyectoFaseOption.id" v-for="proyectoFaseOption in proyectoFases" :key="proyectoFaseOption.id">{{proyectoFaseOption.titulo}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.adjuntoProyectoFase.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./adjunto-proyecto-fase-update.component.ts">
</script>
