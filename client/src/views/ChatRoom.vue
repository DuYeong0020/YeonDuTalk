<template>
  <div>
    <v-toolbar dense color="primary" dark>
      <v-avatar size="32" class="mr-2">
        <img src="@/assets/default-avatar.png" alt="기본아바타" />
      </v-avatar>
      <v-toolbar-title>{{ friend.name }}</v-toolbar-title>
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
              :right="user.name === item.userName"
              :left="user.name !== item.userName"
              shaped
              multi-line
              min-width="10"
              content-class="py-0"
            >
              <p class="ma-0">{{ item.content }}</p>
              <p class="ma-0 text-caption grey--text">
                {{ item.dateTime | dateTime }}
              </p>
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
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import { formatAMPM } from "@/utils/time";
import { mapState } from "vuex";

interface Message {
  userName: string;
  content: string;
  dateTime: string;
}

interface Friend {
  id: number;
  name: string;
}

interface SocketResponse {
  body: string;
}

export default Vue.extend({
  data() {
    return {
      friend: {} as Friend,
      stompClient: {
        send: (a: string, b: string, c: any) => {
          return;
        },
        connected: false,
        subscribe: Function,
      },
      connected: false,
      message: "",
      messages: [] as Message[],
    };
  },

  filters: {
    dateTime(time: Date) {
      return formatAMPM(new Date(time));
    },
  },

  computed: {
    ...mapState(["user", "friends"]),
  },

  created() {
    const friendId = Number(this.$route.params.id) as number;
    const friendData = this.friends.filter(
      (friend: Friend) => friendId === friend.id
    );
    this.friend = friendData[0];
    this.connect();
  },

  methods: {
    sendMessage() {
      if (!this.message || !this.stompClient || !this.stompClient.connected)
        return;
      const newMessage = {
        userName: this.user.name,
        content: this.message,
        dateTime: new Date(),
      };
      this.stompClient.send("/receive", JSON.stringify(newMessage), {});
      this.message = "";
    },

    connect() {
      const serverURL = process.env.VUE_APP_API_URL || "";
      const socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
      this.stompClient.connect(
        {},
        (frame: any) => {
          this.connected = true;
          console.log("소켓 연결 성공", frame);
          this.stompClient.subscribe("/send", (res: SocketResponse) => {
            const newMessage = JSON.parse(res.body);
            console.log(this.user.name, this.friend.name);
            if (
              newMessage.userName === this.user.name ||
              newMessage.userName === this.friend.name
            )
              this.messages.push(newMessage);
          });
        },
        (error: any) => {
          console.log("소켓 연결 실패", error);
          this.connected = false;
        }
      );
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
