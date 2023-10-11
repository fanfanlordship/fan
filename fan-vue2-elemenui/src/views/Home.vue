<template>
  <el-container style="height: 500px; border: 1px solid #eee">
    <el-header style="text-align: right; font-size: 12px;height: 80px">
      <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 15px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>查看</el-dropdown-item>
          <el-dropdown-item>新增</el-dropdown-item>
          <el-dropdown-item>删除</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>王小虎</span>
    </el-header>


    <el-container>
      <el-aside width="15rem" height="100%" style="background-color: rgb(238, 241, 246)">
        <el-menu>
          <menu-tree :menuList="menuList"></menu-tree>
        </el-menu>
      </el-aside>

      <el-main>
        <el-table :data="tableData">
          <el-table-column prop="date" label="日期" width="140">
          </el-table-column>
          <el-table-column prop="name" label="姓名" width="120">
          </el-table-column>
          <el-table-column prop="address" label="地址">
          </el-table-column>
        </el-table>
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
      tableData: null
    };
  },
  created() {
    this.reCaptcha();
  },
  methods: {
    reCaptcha() {
      this.$axios.post('/fan-cloud-nacos-auth/auth/menus')
          .then(res => {
            const resData = res.data.data;
            this.menuList = resData;
            console.log(resData[0]);
          });
    },

  }
};
</script>

<style>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>
