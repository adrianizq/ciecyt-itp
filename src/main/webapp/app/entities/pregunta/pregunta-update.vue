<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="ciecytApp.pregunta.home.createOrEditLabel" v-text="$t('ciecytApp.pregunta.home.createOrEditLabel')">Create or edit a Pregunta</h2>
                <div>
                    <div class="form-group" v-if="pregunta.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="pregunta.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.pregunta.encabezado')" for="pregunta-encabezado">Encabezado</label>
                        <input type="text" class="form-control" name="encabezado" id="pregunta-encabezado"
                            :class="{'valid': !$v.pregunta.encabezado.$invalid, 'invalid': $v.pregunta.encabezado.$invalid }" v-model="$v.pregunta.encabezado.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.pregunta.descripcion')" for="pregunta-descripcion">Descripcion</label>
                        <input type="text" class="form-control" name="descripcion" id="pregunta-descripcion"
                            :class="{'valid': !$v.pregunta.descripcion.$invalid, 'invalid': $v.pregunta.descripcion.$invalid }" v-model="$v.pregunta.descripcion.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.pregunta.pregunta')" for="pregunta-pregunta">Pregunta</label>
                        <input type="text" class="form-control" name="pregunta" id="pregunta-pregunta"
                            :class="{'valid': !$v.pregunta.pregunta.$invalid, 'invalid': $v.pregunta.pregunta.$invalid }" v-model="$v.pregunta.pregunta.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-bind:value="$t('ciecytApp.pregunta.preguntaTipoPregunta')" for="pregunta-preguntaTipoPregunta">Tipo Pregunta</label>
                        <select class="form-control" id="pregunta-preguntaTipoPregunta" name="preguntaTipoPregunta" v-model="pregunta.preguntaTipoPreguntaId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="tipoPreguntaOption.id" v-for="tipoPreguntaOption in tipoPreguntas" :key="tipoPreguntaOption.id">{{tipoPreguntaOption.tipoPregunta}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-bind:value="$t('ciecytApp.pregunta.preguntaModalidad')" for="pregunta-preguntaModalidad">Modalidad</label>
                        <select class="form-control" id="pregunta-preguntaModalidad" 
                        name="preguntaModalidad" v-model="pregunta.preguntaModalidadId"
                        >
                            <option v-bind:value="null"></option>
                            <option v-bind:value="modalidadOption.id" v-for="modalidadOption in modalidads" :key="modalidadOption.id">{{modalidadOption.modalidad}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-bind:value="$t('ciecytApp.pregunta.preguntaRolesModalidad')" for="pregunta-preguntaRolesModalidad">Rol Modalidad</label>
                       
                        <b-form-select text-field="pregunta-roles-modalidad" value-field="id" id="rol"
                            v-model="pregunta.preguntaRolesModalidadId"
                            
                             >
                            <option v-for="(selectOption, indexOpt) in Roles"
                                        :key="indexOpt"
                                        :value="selectOption.id"
                                >
                                    ({{ selectOption.id }}) {{ selectOption.rol }}
                                </option>
                        </b-form-select>
                    </div>
                </div>

                <!--------------------------------------------->
                 <div class="col-md-6 col-12">
                      <div class="form-group" >
                            <label class="form-control-label "  for="pregunta-elemento">Elemento</label> 
                            <b-form-select text-field="pregunta-elemento" value-field="id" id="elemento"
                            v-model="pregunta.preguntaElementoId"
                            
                             >
                                <option v-for="(selectOption, indexOpt) in Elementos"
                                        :key="indexOpt"
                                        :value="selectOption.id"
                                >
                                    ({{ selectOption.id }}) {{ selectOption.elemento }}
                                </option>
                            </b-form-select>
                            
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ciecytApp.pregunta.puntajeMaximo')" for="pregunta-puntaje-maximo">Puntaje Máximo</label>
                        <input type="text" class="form-control" name="puntaje-maximo" id="pregunta-puntaje-maximo"
                            :class="{'valid': !$v.pregunta.puntajeMaximo.$invalid, 'invalid': $v.pregunta.pregunta.$invalid }" v-model="$v.pregunta.puntajeMaximo.$model" />
                    </div>
                <!--------------------------------------------->
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.pregunta.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./pregunta-update.component.ts">
</script>
