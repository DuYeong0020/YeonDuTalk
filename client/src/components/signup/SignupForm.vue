<template>
  <v-form ref="form" v-model="valid" lazy-validation>
    <v-text-field
      v-model="userId"
      class="mt-5"
      :rules="userIdRules"
      label="아이디"
      outlined
      required
    ></v-text-field>

    <v-text-field
      v-model="password1"
      class="mt-5"
      :rules="passwordRules"
      type="password"
      label="비밀번호"
      outlined
      required
    ></v-text-field>

    <v-text-field
      v-model="password2"
      class="mt-5"
      type="password"
      :rules="passwordRules"
      label="비밀번호 확인"
      outlined
      required
    ></v-text-field>

    <v-text-field
      v-model="username"
      class="mt-5"
      :rules="usernameRules"
      label="이름"
      outlined
      required
    ></v-text-field>

    <v-btn
      :disabled="!valid"
      color="primary"
      block
      x-large
      outlined
      @click="signup"
    >
      회원가입
    </v-btn>

    <v-btn color="primary" block text small @click="$router.go(-1)">
      이미 계정이 있어요
    </v-btn>
  </v-form>
</template>

<script lang="ts">
import Vue from "vue";
import { userIdRules, passwordRules, usernameRules } from "@/utils/validation";
import { signup } from "@/api/user";
import { mapMutations } from "vuex";
import axios from "axios";

export default Vue.extend({
  data: () => ({
    valid: true,
    userId: "",
    userIdRules,
    password1: "",
    password2: "",
    passwordRules,
    username: "",
    usernameRules,
  }),

  computed: {
    form(): Vue & { validate: () => boolean } {
      return this.$refs.form as Vue & { validate: () => boolean };
    },
  },

  methods: {
    ...mapMutations(["SHOW_SNACKBAR"]),

    async signup() {
      if (!this.form) return;
      try {
        await signup({
          userId: this.userId,
          userPassword: this.password2,
          userName: this.username,
        });
        this.$router.go(-1);
        this.SHOW_SNACKBAR(`${this.username}님 회원가입을 환영합니다!`);
      } catch (error) {
        if (axios.isAxiosError(error)) this.SHOW_SNACKBAR(error.message);
        else console.error(error);
      }
    },
  },
});
</script>
