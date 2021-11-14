import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    redirect: "/chatroom",
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("../views/Login.vue"),
  },
  {
    path: "/friends",
    name: "Friends",
    component: () => import("../views/Friends.vue"),
  },
  {
    path: "/chat-list",
    name: "ChatList",
    component: () => import("../views/ChatList.vue"),
  },
  {
    path: "/mypage",
    name: "Mypage",
    component: () => import("../views/Mypage.vue"),
  },
  {
    path: "/chatroom",
    name: "ChatRoom",
    component: () => import("../views/ChatRoom.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
