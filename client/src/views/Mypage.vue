<template>
  <v-container class="text-center">
    <v-avatar rounded size="120">
      <v-img src="@/assets/default-avatar.png"></v-img> </v-avatar
    ><v-form class="multi-col-validation">
      <v-card-text class="pt-8">
        <v-row>
          <v-col cols="12" md="6">
            <v-text-field
              v-model="userInfo.userId"
              outlined
              dense
              readonly
              label="아이디"
            ></v-text-field>
          </v-col>

          <v-col cols="12" md="6">
            <v-text-field
              v-model="userInfo.username"
              outlined
              dense
              label="이름"
            ></v-text-field>
          </v-col>
        </v-row>
      </v-card-text>
      <v-card-text>
        <v-btn color="primary" tile> 변경사항 저장 </v-btn>
      </v-card-text>
    </v-form>
  </v-container>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  data() {
    return {
      userInfo: {
        userId: "dudu",
        username: "최두영",
      },
    };
  },
});
</script>

<style></style>
