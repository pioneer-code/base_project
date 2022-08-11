<template>
    <div class="login-page">
        <div class="form-zone">
            <div class="login-title">用户登录</div>
            <div style="margin: 28px;"></div>
            <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign" ref="formLabelAlign">
              <el-form-item label="用户id" prop="id"
                :rules="[
                {required: true, message: '用户id不能为空', trigger: 'blur'},
                ]">
                <el-input v-model="formLabelAlign.id" type="text"></el-input>
              </el-form-item>
              <el-form-item prop= "password" label="密码" :rules="{required: true, message: '密码不能为空', trigger: 'blur'}">
                <el-input v-model="formLabelAlign.password" type="password" show-password></el-input>
              </el-form-item>
              <div style="margin: 38px"></div>
              <el-form-item size="medium">
                <el-button type="primary" @click="submitForm('formLabelAlign')" ref="login-button" >登录</el-button >
              </el-form-item>
            </el-form>
            <div class="register-url">
                <li>还没有账号？联系管理为你添加吧</li>
            </div>
        </div>
    </div>
</template>
<script>
 export default {
    data() {
      return {
        labelPosition: 'left',
        formLabelAlign: {
          id: '',
          password:'',
        }
      };
    },
    methods: {
        submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios.request({
              url:'/user/login',
              method:'POST',
              params:this.formLabelAlign
            }).then((res)=>{
              if(res.data.msg == '用户名或者密码错误') this.$alert('用户id或者密码错误')
              else {
                this.$store.commit('updateCurUser', res.data.user);
                console.log(this.$store.state.curUser);
                sessionStorage.setItem('id', res.data.user.id);
                sessionStorage.setItem('grade', res.data.user.grade)
                sessionStorage.setItem('uname', res.data.user.username),
                sessionStorage.setItem('type', res.data.user.identity)
                if(sessionStorage.getItem('type') == 'A') this.$router.push('admin')
                else this.$router.push('home');
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
    },
    mounted() {
    },
  }
</script>
<style lang="">
    .login-page{
        height: 55.25vw;
        width: 100vw;
        /* background-image: url("../assets/images/loginbg.png") no-repeat; */
        background: url("../assets/images/loginbg.png") center no-repeat;
        background-size: 100vw 56.25vw;
        background-position: 0 -80px;

    }
    .form-zone{
        position: relative;
        top: 200px;
        left: 50%;
        transform: translateX(-50%);
        width: 450px;
        height: 280px;
        padding: 15px 15px;
        background-color: #fff;
        border: 2px solid rgb(64,158,255);
        border-radius: 8px;
        box-shadow: 0 0 10px;
    }
    .login-title{
        text-align: center;
        margin: 5px 0;
        height: 52px;
        line-height: 52px;
        background-color: rgb(64,158,255);
        color: #fff;
        overflow: hidden;
    }
    .register-url li{
        list-style: none;
        position: relative;
        top: -50px;
        right: -180px;
        color: rgb(64,158,255);
        font-size: 15px;
        text-decoration: none;
    }
</style>