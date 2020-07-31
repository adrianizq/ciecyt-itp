<template>
  <div role="tablist">
    <div v-for="(item, index) in items" :key="index">
      <b-button
        :disabled='proyectoId ? false : true'
        block
        v-b-toggle="'accordion-' + index"
        variant="default"
        :to="item.to+'/'+proyectoId"
      >{{ item.title }}</b-button>
      <b-collapse
        :id="'accordion-' + index"
        :visible="isActive(item.to)"
        accordion="my-accordion"
        role="tabpanel"
        class="border rounded-bottom"
      >
        <b-card-body>
          <b-card-text>{{ item.description }}</b-card-text>
        </b-card-body>
      </b-collapse>
    </div>
  </div>
</template>

<script lang="ts">
import Component from 'vue-class-component';
import { Vue, Prop } from 'vue-property-decorator';

@Component
export default class PropuestaMenuLateral extends Vue {
  items = this.$store.getters.menuLateral;
  @Prop()
  proyectoId: number;

  isActive(to) {
    return to == this.$route.path;
  }
}
</script>

<style scoped>
</style>
