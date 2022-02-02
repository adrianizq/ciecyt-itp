<template>
   <div> Hola esta es la pagina de predicciones cambio!!!
   <br />
   {{ prediccions }}
   </div>
</template>

<script lang="ts">
import { Component, Inject, Vue } from 'vue-property-decorator';
import AlertService from '@/shared/alert/alert.service';

//import MenuLateral from '@/components/propuesta/menu_lateral.vue';
import PrediccionesService from '@/entities/predicciones/predicciones.service';
//import { IModalidad } from '@/shared/model/modalidad.model';
//import FacultadService from '@/entities/facultad/facultad.service';
//import { IFacultad } from '@/shared/model/facultad.model';
//import LineaInvestigacionService from '@/entities/linea-investigacion/linea-investigacion.service';
//import { ILineaInvestigacion } from '@/shared/model/linea-investigacion.model';
//import UsuarioService from '@/entities/usuario/usuario.service';
//import { IUsuario } from '@/shared/model/usuario.model';
//import { IUser } from '@/shared/model/user.model';

//import { id } from 'date-fns/locale';

 const validations: any = {};

    @Component({
        validations
    })
export default class Predicciones1 extends Vue {
  @Inject('prediccionesService') private prediccionesService: () => PrediccionesService;
  //@Inject('facultadService') private facultadService: () => FacultadService;
  //@Inject('lineaInvestigacionService') private lineaInvestigacionService: () => LineaInvestigacionService;

  //@Inject('investigacionTipoService') private investigacionTipoService: () => InvestigacionTipoService;

  @Inject('alertService') private alertService: () => AlertService;

  //public modalidads: IModalidad[] = [];
  //public facultades: IFacultad[] = [];
  //public programs: IPrograma[] = [];

  //public linea_investigacion: number = null;

  // public investTipos: String[] = [];

  public prediccions: string = null;
  //public isSaving = false;

  //public submitStatus: string = 'PENDING';
  //console.log("entrando a initrelation");
   
  beforeRouteEnter(to, from, next) {
            next(async vm => {
                vm.initRelationships();
            });
  }
  ////////////////////////////////////////
  async initRelationships() {
            try {
                
                 await this.prediccionesService()
                    .retrieve()
                    .then(res => {
                        this.prediccions = res;
                      
                    });
            } catch (e) {

            }
        }

  //////////////////////////////////////
}
</script>

<style scoped>