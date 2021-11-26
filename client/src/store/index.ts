import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    snackbar: {
      message: "",
      show: true,
    },
  },
  mutations: {
    SHOW_SNACKBAR({ snackbar }, message) {
      snackbar.message = message;
      snackbar.show = true;
    },
  },
  actions: {},
  modules: {},
});
