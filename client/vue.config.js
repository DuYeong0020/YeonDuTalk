module.exports = {
  transpileDependencies: ["vuetify"],
  pluginOptions: {
    electronBuilder: {
      nodeIntegration: true,
    },
  },
  devServer: {
    port: 3000,
    proxy: {
      "/api/": {
        target: "http://localhost:8080",
      },
    },
  },
};
