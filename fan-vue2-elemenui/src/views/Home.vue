<template>
  <el-container style="height: 98vh; border: 1px solid #eee">
    <el-header style="text-align: right; font-size: 12px;height: 80px">

      <div class="fan-main-head">
        <el-dropdown @command="handleCommand">
          <el-avatar :size="50"
                     :src="'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"></el-avatar>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="userDetail">个人信息</el-dropdown-item>
            <el-dropdown-item>修改密码</el-dropdown-item>
            <el-dropdown-item>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>

    <el-container>
      <el-aside width="15rem" style="background-color: #f7f8fa">
        <el-menu router background-color="#f7f8fa" active-text-color="#ffd04b" :default-openeds="['1']"
                 style="height: 100%">
          <menu-tree :menuList="menuList" :click-menu="openView"></menu-tree>
        </el-menu>
      </el-aside>

      <el-main class="el-main">
        <el-tabs type="border-card" v-model="editableTabsValue" @tab-remove="tabRemove" @tab-click="tabClick"
                 style="height: 100%">
          <el-tab-pane v-for="item in editableTabs" :closable="item.close" :name="item.name" :key="item.name"
                       style="height: 100%">
            <span slot="label">
              <i :class="item.icon"></i>&nbsp;&nbsp;{{ item.title }}
            </span>
            <keep-alive>
              <router-view></router-view>
            </keep-alive>
          </el-tab-pane>

        </el-tabs>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
// eslint-disable-next-line import/no-extraneous-dependencies
import {ref} from "vue";
import MenuTree from "@/components/MenuTree.vue";
// eslint-disable-next-line import/extensions
import router from "@/router";

export default {
  components: {
    MenuTree
  },
  data() {
    return {
      menuList: [],
      tabs: [],
      activeName: null,
      editableTabsValue: ref(this.$store.state.editableTabsValue),
      editableTabs: ref(this.$store.state.editableTabs)
    };
  },
  watch: {
    "$store.state": {
      deep: true,
      handler() {
        this.refreshTabs();
      }
    }
  },
  created() {
    this.initMenus();
  },
  methods: {
    initMenus() {
      this.$axios.post('/fan-cloud-nacos-auth/auth/menus').then(res => {
        this.menuList = res.data.data;
      });
    },
    handleCommand(command) {

      switch (command) {
        case "userDetail":
          console.log("打开个人信息")
          this.openView({
            id: "userDetail",
            name: "个人信息",
            router: "/home/userDetail",
            icon: "el-icon-s-custom"
          });
          break;
        default:
          break;
      }
    },
    openView(e) {
      this.$store.commit('ADD_TABS', e)
    },
    tabClick(tab) {
      // 对tab参数处理，以获得当前点击的标签页的路由
      const name = JSON.stringify(tab.paneName).replace('"', '').replace('"', '');
      // 调用切换方法切换标签页
      this.$store.commit('CHANGE_TABS', name);
      // 路由跳转以实现切换界面
      router.push(name)
    },
    tabRemove(targetName) {
      const tabs = this.editableTabs;
      let activeName = this.editableTabsValue;
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            const nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.name;
            }
          }
        });
      }
      this.$store.state.editableTabsValue = activeName;
      this.$store.state.editableTabs = tabs.filter(tab => tab.name !== targetName);
      router.push(activeName)
    },
    refreshTabs() {
      this.editableTabsValue = this.$store.state.editableTabsValue;
      this.editableTabs = this.$store.state.editableTabs;
    },

  }
};
</script>

<style>
.el-header {
  background: linear-gradient(90deg, #2958bd, #27282e);
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}

.el-main {
  padding: 0;
}

iframe {
  border-width: 0;
  width: 100%;
  height: 100%;
}

.fan-main-head {
  line-height: 100%;
  height: 100%;
  align-items: center;
  display: flex;
  flex-direction: row-reverse;
}

.el-tabs__content {
  height: 93%;
}

.el-tabs--border-card {
  background: #FFF;
  border: none;
  box-shadow: none;
}
</style>
