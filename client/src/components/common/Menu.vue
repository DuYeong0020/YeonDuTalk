<template>
  <v-navigation-drawer app permanent width="55" v-if="$route.meta.menu">
    <v-list nav>
      <v-list-item
        v-for="menu in menuTop"
        :key="menu.title"
        link
        :to="menu.link"
      >
        <v-list-item-icon>
          <v-icon>{{ menu.icon }}</v-icon>
        </v-list-item-icon>
      </v-list-item>
    </v-list>
    <template v-slot:append>
      <v-list-item
        v-for="menu in menuBottom"
        :key="menu.title"
        link
        :to="menu.link"
      >
        <v-list-item-icon>
          <v-icon>{{ menu.icon }}</v-icon>
        </v-list-item-icon>
      </v-list-item>
    </template>
  </v-navigation-drawer>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  data() {
    return {
      menuTop: [
        { link: "/friends", icon: "mdi-account-supervisor" },
        // { link: "/chat-list", icon: "mdi-message" },
      ],
      menuBottom: [
        { link: "/", icon: "mdi-logout" },
        { link: "/mypage", icon: "mdi-cog" },
      ],
    };
  },

  methods: {},
});
</script>

<style></style>
