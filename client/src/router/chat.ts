import { RouteConfig } from "vue-router";

const routes: Array<RouteConfig> = [
  {
    path: "/friends",
    name: "Friends",
    component: () => import("../views/Friends.vue"),
    meta: { menu: true },
  },
  {
    path: "/chat-list",
    name: "ChatList",
    component: () => import("../views/ChatList.vue"),
    meta: { menu: true },
  },
  {
    path: "/mypage",
    name: "Mypage",
    component: () => import("../views/Mypage.vue"),
    meta: { menu: true },
  },
  {
    path: "/chatroom",
    name: "ChatRoom",
    component: () => import("../views/ChatRoom.vue"),
    meta: { menu: true },
  },
];

export default routes;
