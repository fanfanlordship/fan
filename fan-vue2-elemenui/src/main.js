import Vue from 'vue';
import ElementUI from 'element-ui';
import App from './App.vue';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router/index';
import require from './util/http';
import store from './store/index';

Vue.config.productionTip = false;
Vue.use(ElementUI);

// 将 axios 挂载到Vue原型对象中，实现数据共享，节约内存空间。
// 此时在任何页面都可以使用axios获取数据
Vue.prototype.$axios = require;

new Vue({
    store,
    require,
    render: h => h(App),
    router
}).$mount('#app');

// module.exports = {
//     devServer: {
//         host: "0.0.0.0",
//         port: 80,
//         open: true,
//         proxy: {
//             "/dev-api": {
//                 target: `http://127.0.0.1:8088`,
//                 changeOrigin: true,
//                 ws: true,
//                 pathRewrite: {
//                     ["^/api"]: "",
//                 },
//             },
//         },
//     },
// };
