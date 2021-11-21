<template>
  <v-app dark>
    <Menu />
    <v-main id="main">
      <router-view />
    </v-main>
  </v-app>
</template>

<script lang="ts">
import Vue from "vue";
import Menu from "./components/common/Menu.vue";

export default Vue.extend({
  name: "App",

  components: {
    Menu,
  },
});
</script>

<style lang="scss" src="@/assets/main.scss"></style>
