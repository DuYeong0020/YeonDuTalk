<template>
  <v-container class="login">
    <v-row class="text-center">
      <v-col>
        <logo />
      </v-col>
      <login-form></login-form>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import Vue from "vue";
import Logo from "@/components/common/Logo.vue";
import LoginForm from "@/components/login/LoginForm.vue";

export default Vue.extend({
  name: "Home",

  components: {
    Logo,
    LoginForm,
  },
});
</script>

<style scoped>
.login {
  height: 100vh;
  background-color: #003a3d;
}
</style>
