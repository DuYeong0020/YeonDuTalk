import Vue from "vue";
import Vuetify from "vuetify/lib/framework";

Vue.use(Vuetify);

export default new Vuetify({
  options: {
    customProperties: true,
  },
  theme: {
    themes: {
      light: {
        primary: "#335b56",
        secondary: "#003a3d",
        accent: "#ffc8c6",
        warning: "#ff8083",
        error: "#c1323b",
      },
    },
  },
});
