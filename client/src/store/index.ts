import Vue from "vue";
import Vuex from "vuex";
import { fetchFriends } from "@/api/friends";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: {
      id: null as unknown as number,
      name: "",
    },
    snackbar: {
      message: "",
      show: false,
    },
    friends: [],
  },
  getters: {
    getUserPk(state) {
      return state.user.id;
    },
  },
  mutations: {
    SHOW_SNACKBAR({ snackbar }, message) {
      snackbar.message = message;
      snackbar.show = true;
    },

    FETCH_USER_INFO(state, userInfo) {
      state.user = userInfo;
    },

    FETCH_USER_FRIENDS(state, friendList) {
      state.friends = friendList;
    },
  },
  actions: {
    async fetchFriends({ commit, state: { user } }) {
      await fetchFriends(user.id);
    },
  },
  modules: {},
});
