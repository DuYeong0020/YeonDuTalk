<template>
  <v-app dark>
    <Menu v-if="$route.path !== '/login'" />
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

  data: () => ({}),
});
</script>

<style lang="scss">
#main {
  background-color: #003a3d;
}
</style>
