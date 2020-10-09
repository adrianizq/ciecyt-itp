<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="cicloPropedeutico">
                <h2 class="jh-entity-heading"><span v-text="$t('ciecytApp.cicloPropedeutico.detail.title')">CicloPropedeutico</span> {{cicloPropedeutico.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('ciecytApp.cicloPropedeutico.ciclo')">Ciclo</span>
                    </dt>
                    <dd>
                        <span>{{cicloPropedeutico.ciclo}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ciecytApp.cicloPropedeutico.modalidad')">Modalidad</span>
                    </dt>
                    <dd>
                        <div v-if="cicloPropedeutico.modalidadId">
                            <router-link :to="{name: 'ModalidadView', params: {modalidadId: cicloPropedeutico.modalidadId}}">{{cicloPropedeutico.modalidadId}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('ciecytApp.cicloPropedeutico.cicloPropedeuticoAcuerdo')">Ciclo Propedeutico Acuerdo</span>
                    </dt>
                    <dd>
                        <div v-if="cicloPropedeutico.cicloPropedeuticoAcuerdoId">
                            <router-link :to="{name: 'AcuerdoView', params: {cicloPropedeuticoAcuerdoId: cicloPropedeutico.cicloPropedeuticoAcuerdoId}}">{{cicloPropedeutico.cicloPropedeuticoAcuerdoAcuerdo}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="cicloPropedeutico.id" :to="{name: 'CicloPropedeuticoEdit', params: {cicloPropedeuticoId: cicloPropedeutico.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./ciclo-propedeutico-details.component.ts">
</script>
