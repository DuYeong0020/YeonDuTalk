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
        <v-subheader>친구 {{ friends.length || 0 }}명</v-subheader>

        <v-list-item
          v-for="(friend, i) in friends"
          :key="i"
          class="freind-item"
          @click="startChat(friend.id)"
        >
          <v-list-item-avatar>
            <v-img
              :alt="`${friend.name} avatar`"
              src="@/assets/default-avatar.png"
            ></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title v-text="friend.name"></v-list-item-title>
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
    };
  },

  computed: {
    ...mapState(["user", "friends"]),
  },

  async created() {
    await this.fetchFriends();
  },

  methods: {
    ...mapActions(["fetchFriends"]),

    startChat(id: number) {
      this.$router.push(`/chatroom/${id}`);
    },
  },
});
</script>
