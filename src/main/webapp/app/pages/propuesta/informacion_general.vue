<template>
    <div class="row">
        <div class="col-sm-4">
            <menu-lateral></menu-lateral>
        </div>
        <div class="col-sm-8">
            <form>
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label class="form-control-label" for="titulo_proyecto">Titulo del proyecto</label>
                            <input type="text" class="form-control" name="titulo_proyecto" id="titulo_proyecto" />
                        </div>
                    </div>

                    <div class="col-md-6 col-12">
                        <div class="form-group">
                            <label class="form-control-label" for="modalidad">Modalidad</label>
                            <input type="text" class="form-control" name="modalidad" id="modalidad" />
                        </div>
                    </div>

                    <div class="col-md-6 col-12">
                        <div class="form-group">
                            <label class="form-control-label" for="facultad">Facultad</label>
                            <input type="text" class="form-control" name="facultad" id="facultad" />
                        </div>
                    </div>

                    <div class="col-md-6 col-12">
                        <div class="form-group">
                            <label class="form-control-label" for="linea_investigacion">Linea de investigación</label>
                            <input type="text" class="form-control" name="linea_investigacion" id="linea_investigacion" />
                        </div>
                    </div>

                    <div class="col-md-6 col-12">
                        <div class="form-group">
                            <label class="form-control-label" for="sublinea">Sub linea</label>
                            <input type="text" class="form-control" name="sublinea" id="sublinea" />
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-group">
                            <label class="form-control-label" for="asesor">Asesor</label>
                            <input type="text" class="form-control" name="asesor" id="asesor" />
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>

<script lang="ts">
    import Component from 'vue-class-component';
    import { Vue } from 'vue-property-decorator';
    import MenuLateral from '@/components/propuesta/menu_lateral.vue';

    @Component({
        components: { MenuLateral }
    })
    export default class PropuestaInformacionGeneral extends Vue {

    }
</script>

<style scoped>

</style>
