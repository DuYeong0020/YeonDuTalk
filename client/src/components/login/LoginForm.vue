<template>
  <v-container>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-text-field
        v-model="userId"
        prepend-icon="mdi-account"
        label="아이디"
        :rules="userIdRules"
        type="text"
        color="accent"
        dark
        outlined
        required
      ></v-text-field>
      <v-text-field
        v-model="userPassword"
        prepend-icon="mdi-lock"
        label="비밀번호"
        :rules="passwordRules"
        type="password"
        color="accent"
        dark
        outlined
        required
      ></v-text-field>

      <v-btn color="warning" x-large block :disabled="!valid" @click="login"
        >로그인</v-btn
      >
      <v-btn color="warning" text link to="/signup" block class="mt-4"
        >계정이 없으신가요?</v-btn
      >
    </v-form>
  </v-container>
</template>

<script lang="ts">
import Vue from "vue";
import { mapMutations } from "vuex";
import { login } from "@/api/user";
import axios, { AxiosError } from "axios";

export default Vue.extend({
  data() {
    return {
      valid: true,
      userId: "",
      userIdRules: [(v: string) => !!v || "아이디를 입력해주세요"],
      userPassword: "",
      passwordRules: [(v: string) => !!v || "비밀번호를 입력해주세요"],
    };
  },

  methods: {
    ...mapMutations(["SHOW_SNACKBAR"]),
    async login() {
      if (!this.$refs.form?.validate()) return;
      try {
        const res = await login({
          userId: this.userId,
          userPassword: this.userPassword,
        });
        this.$router.push("/friends");
        this.SHOW_SNACKBAR(`안녕하세요 ${res.data.name}님!`);
      } catch (error) {
        if (axios.isAxiosError(error)) this.SHOW_SNACKBAR(error.message);
        else console.error(error);
      }
    },
  },
});
</script>
