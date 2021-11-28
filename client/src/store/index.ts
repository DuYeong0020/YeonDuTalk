import Vue from "vue";
import Vuex from "vuex";
import { fetchFriends } from "@/api/friends";

Vue.use(Vuex);

interface Friend {
  id: number;
  name: string;
}

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
    friends: [] as Friend[],
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
      const userList = await fetchFriends(user.id);
      commit("FETCH_USER_FRIENDS", userList);
    },
  },
  modules: {},
});
