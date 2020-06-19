<template>
    <div class="row">
        <div class="col-sm-4">
            <menu-lateral></menu-lateral>
        </div>
        <div class="col-sm-8">
            En integrantes
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
    export default class Integrantes extends Vue {

    }
</script>

<style scoped>

</style>
