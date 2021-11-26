<template>
  <v-app dark>
    <Menu />
    <v-main id="main">
      <router-view />
      <snackbar />
    </v-main>
  </v-app>
</template>

<script lang="ts">
import Vue from "vue";
import Menu from "./components/common/Menu.vue";
import Snackbar from "./components/common/Snackbar.vue";

export default Vue.extend({
  name: "App",

  components: {
    Menu,
    Snackbar,
  },
});
</script>

<style lang="scss" src="@/assets/main.scss"></style>
