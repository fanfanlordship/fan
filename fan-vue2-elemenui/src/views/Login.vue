<template>
  <div class="fan-wrapper">
    <div class="fan-login-card">
      <h1>还没想好写什么</h1>

      <el-form ref="form" :model="form" :rules="rules">
        <el-form-item prop="username">
          <el-input v-model="form.username" prefix-icon="el-icon-user-solid"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="form.password" prefix-icon="el-icon-lock" autocomplete="off"
                    show-password></el-input>
        </el-form-item>
        <el-form-item prop="captcha">

          <el-popover placement="right-start" width="200" trigger="click">
            <el-image :src="formConfig.captchaImage" fit="contain" style="width: 200px" @click="reCaptcha"></el-image>
            <el-input v-model="form.captcha" prefix-icon="el-icon-medal" autocomplete="off" slot="reference"></el-input>
          </el-popover>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit" style="width: 100%">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      form: {
        username: null,
        password: null,
        captcha: null,
        captchaKey: null
      },
      formConfig: {
        captchaImage: ''
      },
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      rules: {
        username: [
          {
            required: true,
            message: '请输入用户名',
            trigger: 'blur'
          },
        ],
        password: [
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur'
          },
        ],
        captcha: [
          {
            required: true,
            message: '请输入验证码',
            trigger: 'blur'
          },
        ],
      }
    };
  },
  created() {
    this.reCaptcha();

    // 开启监听键盘按下事件
    window.addEventListener('keydown', this.handkeyCode, true)
  },
  methods: {
    reCaptcha() {
      this.$axios.post('/fan-cloud-nacos-auth/auth/captcha')
          .then(res => {
            const resData = res.data.data;

            this.formConfig.captchaImage = resData.captcha;
            this.form.captchaKey = resData.key;
          });
    },
    onSubmit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.login();
          return true;
        }
        return false;
      });
    },
    login() {
      this.$axios.post('/fan-cloud-nacos-auth/auth/login', this.form)
          .then(res => {
            const resData = res.data.data;
            sessionStorage.setItem('accessToken', resData.accessToken);
            sessionStorage.setItem('refreshToken', resData.refreshToken);
            this.$router.push('/home');
          });
    },
    handkeyCode(e) {
      if (e.keyCode === 13) {
        // 触发了回车/空格键
        this.onSubmit()
      }
    }
  }
};
</script>

<style>
.fan-wrapper {
  background: url("@/assets/login-background.jpeg");
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100%;
  display: flex;
}

.fan-login-card {
  width: 26rem;
  background-color: aliceblue;
  margin: auto;
  border-radius: 20px;
  opacity: 0.7;
  padding: 0 1rem 1rem 1rem;
}

.fan-login-card h1 {
  text-align: center;
  color: #077366;
}

.el-popover--plain {
  padding: 5px 5px;
}

</style>
