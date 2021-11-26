<template>
  <v-snackbar v-model="snackbar.show">
    {{ snackbar.message }}
  </v-snackbar>
</template>

<script lang="ts">
import Vue from "vue";
import { mapState } from "vuex";

export default Vue.extend({
  computed: {
    ...mapState(["snackbar"]),
  },
});
</script>

<style></style>
