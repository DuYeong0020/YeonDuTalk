<template>
  <v-navigation-drawer app permanent width="55" v-if="$route.meta.menu">
    <v-list nav>
      <v-list-item v-for="item in items" :key="item.title" link :to="item.link">
        <v-list-item-icon>
          <v-icon>{{ item.icon }}</v-icon>
        </v-list-item-icon>
      </v-list-item>
    </v-list>
    <template v-slot:append>
      <v-list-item link to="/mypage">
        <v-list-item-icon>
          <v-icon>mdi-cog</v-icon>
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
      items: [
        { link: "/friends", icon: "mdi-account-supervisor" },
        { link: "/chat-list", icon: "mdi-message" },
      ],
    };
  },
});
</script>

<style></style>
