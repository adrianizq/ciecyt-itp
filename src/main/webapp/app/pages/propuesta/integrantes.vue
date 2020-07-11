<template>
    <div class="row">
        <div class="col-sm-4">
            <menu-lateral></menu-lateral>
        </div>
        <div class="col-sm-8">
            <form @submit.prevent="save()"> 
            <div class="row">
            En integrantes


             <!-- ADR   -->
                <div class="col-md-6 col-12">
                    <b-form-group
                        label="Asesor"
                        label-for="usuario"
                    >
                        <b-form-select :options="users" v-model="proyecto.asesorId" text-field="nombresApellidos" value-field="id" id="proyecto-asesorId" >

                        </b-form-select>
                    </b-form-group>
                </div>
            </div> 
            </form>

        </div>
    </div>
</template>

<script lang="ts">
    import { Component, Inject, Vue } from 'vue-property-decorator';
    import MenuLateral from '@/components/propuesta/menu_lateral.vue';
    import AlertService from '@/shared/alert/alert.service';
    import UsuarioService from '@/entities/usuario/usuario.service';
    import { IUser } from '@/shared/model/user.model';
   

    @Component({
        components: { MenuLateral }
    })
    export default class Integrantes extends Vue {

         @Inject('usuarioService') private usuarioService: () => UsuarioService;

         public users: IUser[] = [];

         beforeRouteEnter(to, from, next) {
            next(vm => {
                if (to.params.proyectoId) {
                    vm.retrieveProyecto(to.params.proyectoId);
                }
                /*if (to.params.cicloPropedeuticoId) {
                    vm.retrieveCicloPropedeutico(to.params.cicloPropedeuticoId);
                }*/
                vm.initRelationships();
            });
        }

        initRelationships() {
            
            //Obteniendo los usuarios asesores
            this.usuarioService()
                .retrieveAsesores()
                .then(res => {
                    res.data.forEach((item) => {
                        item.nombresApellidos = item.firstName + ' ' + item.lastName;
                        this.users.push(item);
                    });

                });

        }

    }

</script>

<style scoped>

</style>
