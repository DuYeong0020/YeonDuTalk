<template>
  <v-container class="signup">
    <h1 class="text-center primary--text">회원가입</h1>
    <signup-form />
  </v-container>
</template>

<script lang="ts">
import Vue from "vue";
import SignupForm from "@/components/signup/SignupForm.vue";

export default Vue.extend({
  components: {
    SignupForm,
  },
});
</script>

<style lang="scss" scoped></style>
