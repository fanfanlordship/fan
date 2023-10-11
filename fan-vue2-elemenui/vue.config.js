const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/api': {   // 表示拦截以/api开头的请求路径
        target: `http://127.0.0.1:8088`,
        changeOrigin: true,     // 是否开启跨域
        ws: true,
        pathRewrite: {
          '^/api': '',  // 重写api，把api变成空字符，因为我们真正请求的路径是没有api的
        },
      }
    }
  }
});
