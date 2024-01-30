<template>
  <el-container style="height: 98vh; border: 1px solid #eee">
    <el-header style="text-align: right; font-size: 12px;height: 80px">

      <div class="fan-main-head">
        <el-dropdown>
          <el-avatar :size="50"
                     :src="'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"></el-avatar>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>用户信息</el-dropdown-item>
            <el-dropdown-item>修改密码</el-dropdown-item>
            <el-dropdown-item>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>

    <el-container>
      <el-aside width="15rem" style="background-color: #f7f8fa">
        <el-menu background-color="#f7f8fa" active-text-color="#ffd04b" :default-openeds="['1']" style="height: 100%">
          <menu-tree :menuList="menuList" :click-menu="openView"></menu-tree>
        </el-menu>
      </el-aside>

      <el-main class="el-main">
        <el-tabs type="border-card" closable v-model="activeName" @tab-remove="tabRemove" style="height: 100%">
          <el-tab-pane v-for="item in tabs" :name="item.name" :key="item.name" style="height: 100%">
            <span slot="label">
              <i :class="item.icon"></i>&nbsp;&nbsp;{{ item.title }}
            </span>
            <iframe :src="item.router"></iframe>
          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import MenuTree from "@/components/MenuTree.vue";

export default {
  components: {
    MenuTree
  },
  data() {
    return {
      menuList: [],
      tabs: [],
      activeName: null
    };
  },
  created() {
    this.initMenus();
  },
  methods: {
    initMenus() {
      this.$axios.post('/fan-cloud-nacos-auth/auth/menus').then(res => {
        const resData = res.data.data;
        this.menuList = resData;
      });
    },
    openView(e) {
      let hasTab = false;
      this.tabs.forEach(item => {
        if (item.name === e.id) {
          this.activeName = e.id;
          hasTab = true;
        }
      })
      if (!hasTab) {
        this.tabs.push({
          title: e.name,
          name: e.id,
          router: e.router,
          icon: e.icon
        });
        this.activeName = e.id;
      }
    },
    tabRemove(name) {
      const tmp = [];
      this.tabs.forEach((item, index) => {
        if (item.name !== name) {
          tmp.push(item);
        } else {
          const nextTab = this.tabs[index + 1] || this.tabs[index - 1];
          if (nextTab) {
            this.activeName = nextTab.name;
          }
        }
      })
      this.tabs = tmp;
    }

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
