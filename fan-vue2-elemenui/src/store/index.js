import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        // 当前打开的界面路由，初始值为主页的路由
        editableTabsValue: '/home',
        // 存放当前打开的标签页的数组，初始时只有首页
        editableTabs: [{
            // 标签页名
            title: '首页',
            // 标签页路由
            name: '/home',
            // 该标签页是否可关闭，这里是首页标签页不可被关闭
            close: false
        }]
    },
    mutations: {
        // 增加标签页方法
        ADD_TABS: (state, tab) => {
            // 在editableTabs中查找此界面是否已打开，否进入if
            if (state.editableTabs.findIndex(e => e.name === tab.path) === -1) {
                // 添加当前标签页进入editableTabs
                state.editableTabs.push({
                    title: tab.name,
                    name: tab.path,
                    // 使除了首页标签页外其他都可关闭
                    close: true
                })
            }
            // 添加标签页后默认打开
            state.editableTabsValue = tab.path;
        },
        // 切换标签页方法
        CHANGE_TABS: (state, name) => {
            // 将需打开标签页路由赋予editableTabsvalue
            state.editableTabsValue = name
        },
    }
});

export default store;
