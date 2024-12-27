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
          <el-button type="success" size="small" icon="el-icon-top" circle @click="moveUp(row.row)"></el-button>
          <el-button type="danger" size="small" icon="el-icon-bottom" circle @click="moveDown(row.row)"></el-button>
          <el-button type="primary" size="small" icon="el-icon-edit" circle @click="aa(row.row)"></el-button>
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
    },
    moveUp(rowData) {
      if (rowData.id === null) {
        return;
      }

      console.log(rowData)

      this.$axios.post('/fan-cloud-nacos-auth/auth/menus/move/up', {id: rowData.id}).then(res => {
        console.log(res)
        if (res.data.code === '200') {
          this.$message({message: '操作成功', type: 'success'});
        }
        this.searchData();
      });
    },
    moveDown(rowData) {
      if (rowData.id === null) {
        return;
      }

      this.$axios.post('/fan-cloud-nacos-auth/auth/menus/move/down', {id: rowData.id}).then(res => {
        console.log(res)
        if (res.data.code === '200') {
          this.$message({message: '操作成功', type: 'success'});
        }
        this.searchData();
      });
    }
  }
}
</script>

<style>
</style>
