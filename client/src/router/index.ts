import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";

import userRoutes from "./user";
import chatRoutes from "./chat";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    redirect: "/login",
  },
  ...userRoutes,
  ...chatRoutes,
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
