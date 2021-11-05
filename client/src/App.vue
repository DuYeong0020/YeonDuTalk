<template>
  <v-app>
    <Menu />
    <v-main>
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

  data: () => ({}),
});
</script>

<style lang="scss"></style>
