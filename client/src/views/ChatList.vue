<template>
  <v-container>
    <v-list two-line subheader>
      <v-subheader>채팅</v-subheader>
      <v-list-item-group v-model="selected" active-class="primary--text">
        <template v-for="(item, index) in items">
          <v-list-item :key="item.title">
            <v-list-item-avatar>
              <v-img :alt="`${item.title} avatar`" :src="item.avatar"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title v-text="item.title"></v-list-item-title>

              <v-list-item-subtitle
                v-text="item.subtitle"
              ></v-list-item-subtitle>
            </v-list-item-content>

            <v-list-item-action>
              <v-list-item-action-text
                v-text="item.action"
              ></v-list-item-action-text>
              <v-badge color="pink" content="6" inline> </v-badge>
            </v-list-item-action>
          </v-list-item>

          <v-divider v-if="index < items.length - 1" :key="index"></v-divider>
        </template>
      </v-list-item-group>
    </v-list>
  </v-container>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  data() {
    return {
      selected: null,
      items: [
        {
          title: "이연권",
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
          action: "15 min",
          subtitle: `I'll be in your neighborhood doing errands this weekend. Do you want to hang out?`,
        },
        {
          title: "이연권",
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
          action: "2 hr",
          subtitle: `Wish I could come, but I'm out of town this weekend.`,
        },
        {
          title: "이연권",
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
          action: "6 hr",
          subtitle: "Do you have Paris recommendations? Have you ever been?",
        },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
          title: "이연권",
          action: "12 hr",
          subtitle:
            "Have any ideas about what we should get Heidi for her birthday?",
        },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
          title: "이연권",
          action: "18hr",
          subtitle:
            "We should eat this: Grate, Squash, Corn, and tomatillo Tacos.",
        },
      ],
    };
  },
});
</script>

<style></style>
