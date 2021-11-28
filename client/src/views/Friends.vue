<template>
  <v-container>
    <v-subheader>나</v-subheader>
    <v-list-item>
      <v-list-item-avatar>
        <v-img alt="내 아바타" src="@/assets/default-avatar.png"></v-img>
      </v-list-item-avatar>
      <v-list-item-content>
        <v-list-item-title>{{ user.name }}</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-list subheader>
      <v-list-item-group v-model="selected" active-class="primary--text">
        <v-subheader>친구 {{ recent.length }}명</v-subheader>

        <v-list-item v-for="(chat, i) in recent" :key="i" class="freind-item">
          <v-list-item-avatar>
            <v-img
              :alt="`${chat.title} avatar`"
              src="@/assets/default-avatar.png"
            ></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title v-text="chat.title"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-container>
</template>

<script lang="ts">
import Vue from "vue";
import { mapState, mapActions } from "vuex";

export default Vue.extend({
  data() {
    return {
      selected: null,
      recent: [
        {
          title: "일연권",
        },
        {
          title: "이연권",
        },
        {
          title: "삼연권",
        },
        {
          title: "사연권",
        },
      ],
    };
  },

  computed: {
    ...mapState(["user"]),
  },

  async created() {
    await this.fetchFriends();
  },

  methods: {
    ...mapActions(["fetchFriends"]),
  },
});
</script>
