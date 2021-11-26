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
      label="비밀번호"
      outlined
      required
    ></v-text-field>

    <v-text-field
      v-model="password2"
      class="mt-5"
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

    <v-btn
      :disabled="!valid"
      color="primary"
      block
      text
      small
      @click="$router.go(-1)"
    >
      이미 계정이 있어요
    </v-btn>
  </v-form>
</template>

<script lang="ts">
import Vue from "vue";
import { userIdRules, passwordRules, usernameRules } from "@/utils/validation";

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

  methods: {
    signup() {
      this.$refs.form?.validate();
    },
  },
});
</script>
