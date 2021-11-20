<template>
  <v-container>
    <v-form>
      <v-text-field
        prepend-icon="mdi-account"
        label="아이디"
        type="text"
      ></v-text-field>
      <v-text-field
        prepend-icon="mdi-lock"
        label="비밀번호"
        type="password"
      ></v-text-field>

      <v-btn color="warning" x-large block link to="/friends">로그인</v-btn>
    </v-form>
  </v-container>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({});
</script>

<style></style>
