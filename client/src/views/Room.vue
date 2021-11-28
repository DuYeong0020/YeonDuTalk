<template>
  <div>
    <v-toolbar dense>
      <v-avatar size="32" class="mr-2">
        <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
      </v-avatar>
      <v-toolbar-title>이연권</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon @click="$router.go(-1)">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
    </v-toolbar>
    <v-container fluid class="chat-box">
      <v-list>
        <v-list-item v-for="(item, i) in messages" :key="i" class="message-box">
          <v-list-item-content>
            <v-snackbar
              :timeout="-1"
              :value="true"
              color="primary"
              outlined
              absolute
              right
              shaped
              multi-line
              min-width="10"
              content-class="py-0"
            >
              <p class="ma-0">{{ item.message }}</p>
              <p class="ma-0 text-caption grey--text">오전 11:11</p>
            </v-snackbar>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-container>
    <v-container>
      <v-row class="text-center" dense no-gutters>
        <v-col cols="10">
          <v-text-field
            v-model="message"
            label="메세지 전송"
            outlined
            dense
            @keypress.enter="sendMessage"
          ></v-text-field>
        </v-col>
        <v-col>
          <v-btn icon @click="sendMessage">
            <v-icon> mdi-send </v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  data() {
    return {
      message: "",
      messages: [{ message: "하하", timestamp: "2021-11-27 11:11" }],
    };
  },

  methods: {
    async sendMessage() {
      const message = this.message;
      if (!message) return;
      this.messages.push({ message, timestamp: "2021-11-27 11:11" });
      this.message = "";
    },
  },
});
</script>
<style lang="scss" scoped>
.chat-box {
  height: 80vh;
  overflow: scroll;
}

.message-box {
  margin-top: 1.4rem;
}
</style>
