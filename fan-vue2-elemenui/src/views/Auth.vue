<template>
  <div>
    <el-table :data="tableData" row-key="id" :tree-props="{children: 'children'}" stripe border>
      <el-table-column type="index" width="50"></el-table-column>
      <el-table-column prop="name" label="权限名称" width="180"></el-table-column>
      <el-table-column label="图标" width="80">
        <template slot-scope="scope">
          <i :class="scope.row.icon"></i>
        </template>
      </el-table-column>
      <el-table-column prop="router" label="路由"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="row">
          <el-button type="success" size="small" icon="el-icon-top" circle></el-button>
          <el-button type="danger" size="small" icon="el-icon-bottom" circle></el-button>
          <el-button type="primary" size="small" icon="el-icon-edit" circle @click="aa(row)"></el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      currentRow: null
    }
  },
  created() {
    this.searchData();
  },
  methods: {
    searchData() {
      this.$axios.post('/fan-cloud-nacos-auth/auth/menus/data').then(res => {
        const resData = res.data.data;
        console.log(resData)
        this.tableData = resData;
      });
    },
    aa(a) {
      console.log(a)
    }
  }
}
</script>

<style>
</style>
