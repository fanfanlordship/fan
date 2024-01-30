<template>
  <el-container direction="vertical"
                style="background-color: #f1f1e7;border-radius: 28px;height: 100%;box-shadow: 0 10px 20px #888888">
    <el-header height="100px" style="margin-top: 8vh">
      <el-row>
        <el-col :span="12" :offset="1" style="line-height: 100px;font-weight: bold">这里是名字</el-col>
        <el-col :span="6" :offset="2">
          <el-avatar :size="100"
                     :src="'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"></el-avatar>
        </el-col>
      </el-row>
    </el-header>
    <el-main style="margin-top: 30px">
      <el-row>
        <el-col :span="22" :offset="1">
          <el-input
              style="border-radius: 30px;height: 50px;line-height: 50px"
              placeholder="Search"
              prefix-icon="el-icon-search"
              v-model="searchText">
          </el-input>
        </el-col>
      </el-row>

      <el-row style="margin-top: 30px">
        <el-col :span="22" :offset="1">
          <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 17px;">
            <el-breadcrumb-item v-for="(item,index) in show.titleArr" :key="index">{{ item }}</el-breadcrumb-item>
          </el-breadcrumb>
        </el-col>
      </el-row>

      <el-row style="margin-top: 30px" :gutter="2">
        <el-col :offset="1" :span="7" v-for="item in show.list" :key="item.id">
          <el-button plain style="border: 1px solid transparent;margin-bottom: 10px;border-radius: 20px"
                     @click="clickMenu(item)">
            <i :class="item.icon" style="font-size: 30px;padding-bottom: 5px"></i>
            <br/>
            <span>
            {{ item.name }}
          </span>
          </el-button>
        </el-col>
      </el-row>
    </el-main>
    <el-footer style="text-align: center">
      <el-row>
        <el-button circle v-for="item in menuList" :key="item.id" icon="el-icon-edit" :title="item.name"
                   @click="clickMenusHead(true,item)" :disabled="show.headButton===item.id"></el-button>
        <el-button circle icon="el-icon-user-solid" @click="clickMenusHead(true,null)"
                   :disabled="show.headButton==='fan_user_self'"></el-button>
      </el-row>
    </el-footer>
  </el-container>
</template>
<script>

export default {
  name: 'fanHome',
  props: {
    clickMenu: Function
  },
  data() {
    return {
      menuList: [],
      show: {
        titleArr: [],
        list: [],
        headButton: null
      },
      userButtons: [{
        id: "-1",
        name: "用户信息",
        icon: "el-icon-s-custom"
      }, {
        id: "-2",
        name: "修改密码",
        icon: "el-icon-lock"
      }, {
        id: "-3",
        name: "退出登录",
        icon: "el-icon-switch-button"
      }],
      searchText: null
    }
  },
  created() {
    this.getMenus();
  },
  methods: {
    getMenus() {
      this.$axios.post('/fan-cloud-nacos-auth/auth/menus').then(res => {
        const resData = res.data.data;
        this.menuList = resData;

        // 默认点开第一个
        this.clickMenusHead(true, this.menuList[0])
      });
    },
    clickMenusHead(isHead, menu) {
      if (isHead) {
        this.show.titleArr = [menu == null ? "账户" : menu.name];
      } else {
        this.show.titleArr.push(menu.name)
      }

      this.show.list = menu == null ? this.userButtons : menu.children;
      this.show.headButton = menu == null ? "fan_user_self" : menu.id;
    }
  }
};
</script>
