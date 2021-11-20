import Vue from "vue";
import Vuetify from "vuetify/lib/framework";

Vue.use(Vuetify);

export default new Vuetify({
  options: {
    customProperties: true,
  },
  theme: {
    dark: true,
    themes: {
      dark: {
        primary: "#003a3d",
        secondary: "#335b56",
        accent: "#ffc8c6",
        warning: "#ff8083",
        error: "#c1323b",
      },
    },
  },
});
