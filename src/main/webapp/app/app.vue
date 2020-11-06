<template>
  <div id="app">
    <ribbon></ribbon>
    <div id="app-header">
      <jhi-navbar></jhi-navbar>
    </div>
    
    <div class="container-fluid">
      <div class="card jh-card">
      <div id="app-header2">
     <!-- <jhi-navbar2></jhi-navbar2> -->
    </div> 
        <router-view></router-view>
      </div>
      <b-modal id="login-page" hide-footer lazy>
        <span slot="modal-title" id="login-title" v-text="$t('login.title')">Sign in</span>
        <login-form></login-form>
      </b-modal>

      <jhi-footer></jhi-footer>
    </div>
  </div>
</template>

<script lang="ts" src="./app.component.ts">
</script>

<style>
    .btn-switch{
        display: inline-grid;
    }
</style>
