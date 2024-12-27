<template>
  <div>
    <el-row>
      <el-col :span="4" :offset="4">
        <el-image :size="50" :src="'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"></el-image>
      </el-col>
      <el-col :span="16">
        <el-descriptions title="用户信息" column="1">
          <el-descriptions-item label="昵称">{{ detail.name }}</el-descriptions-item>
          <el-descriptions-item label="手机号">{{ detail.mobile }}</el-descriptions-item>
          <el-descriptions-item label="电子邮箱">{{ detail.email }}</el-descriptions-item>
          <el-descriptions-item label="注册时间">{{
              detail.registerTime === null ? '-' : detail.registerTime
            }}
          </el-descriptions-item>
        </el-descriptions>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      detail: {
        name: null,
        mobile: null,
        email: null,
        registerTime: null
      }
    }
  },
  created() {
    this.getUserDetail();
  },
  methods: {
    getUserDetail() {
      this.$axios.post('/fan-cloud-nacos-auth/auth/user/info').then(res => {
        const {data} = res.data;
        console.log(data)
        this.detail.name = data?.name ?? "-";
        this.detail.mobile = data?.mobile ?? "-";
        this.detail.email = data?.email ?? "-";
        this.detail.registerTime = data?.createTime ?? "-";
      });
    }
  }
}
</script>

<style scoped>

</style>
