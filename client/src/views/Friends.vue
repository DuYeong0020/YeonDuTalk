<template>
  <v-container>
    <v-subheader>나</v-subheader>
    <v-list-item>
      <v-list-item-avatar>
        <v-img
          alt="내 아바타"
          src="https://cdn.vuetifyjs.com/images/lists/1.jpg"
        ></v-img>
      </v-list-item-avatar>
      <v-list-item-content>
        <v-list-item-title>이연권</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-list subheader>
      <v-list-item-group v-model="selected" active-class="primary--text">
        <v-subheader>친구 {{ recent.length }}명</v-subheader>

        <v-list-item
          v-for="chat in recent"
          :key="chat.title"
          class="freind-item"
        >
          <v-list-item-avatar>
            <v-img :alt="`${chat.title} avatar`" :src="chat.avatar"></v-img>
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
export default Vue.extend({
  data() {
    return {
      selected: 2,
      recent: [
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
          title: "Jason Oner",
        },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg",
          title: "Mike Carlson",
        },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg",
          title: "Cindy Baker",
        },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/4.jpg",
          title: "Ali Connors",
        },
      ],
    };
  },
});
</script>
