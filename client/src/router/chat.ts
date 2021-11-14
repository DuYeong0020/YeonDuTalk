import { RouteConfig } from "vue-router";

const routes: Array<RouteConfig> = [
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

export default routes;
