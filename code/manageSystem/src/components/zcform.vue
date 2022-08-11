<template>
    <div class="zcform">
        <div class="zc-title">
            <span>添加新成员</span>
        </div>
        <el-form label-position="left" label-width="80px" :model="zcdata" ref="zcform">
            <el-form-item label="id" :rules="{required: true, message: '不能为空', trigger: 'blur'}" prop= "id">
              <el-input v-model="zcdata.id"></el-input>
            </el-form-item >
            <el-form-item  v-if="type == 'A'" label="班级" :rules="{required: true, message: '不能为空', trigger: 'blur'}" prop= "grade">
              <el-input v-model="zcdata.grade"></el-input>
            </el-form-item>
            <el-form-item v-if="type == 'A'" label="用户类型" :rules="{required: true, message: '不能为空', trigger: 'blur'}" prop= "identity">
              <el-input v-model="zcdata.identity"></el-input>
            </el-form-item>
            <el-form-item label="用户名" :rules="{required: true, message: '不能为空', trigger: 'blur'}" prop= "username">
              <el-input v-model="zcdata.username"></el-input>
            </el-form-item>
            <el-form-item prop= "password" label="密码" :rules="{required: true, message: '密码不能为空', trigger: 'blur'}">
              <el-input v-model="zcdata.password"></el-input>
            </el-form-item>
        </el-form>
        <el-button type="primary" @click="zc('zcform')" size="medium">提交</el-button>
    </div>
</template>
<script>
export default {
    name:'zcform',
    props:{
        type:''
    },
    data(){
        return {
            zcdata:{
                id:'',
                grade:'',
                username:'',
                password:'',
                identity:'',
            }
        }
    },
    methods:{
        zc(formName){
            this.$refs[formName].validate((valid)=>{
                if (valid){
                    this.$axios.request({
                        url:'/user/register/',
                        method:'POST',
                        params:this.zcdata
                    }).then((res)=>{
                        if(res.data.msg == '注册成功'){
                            this.$alert('注册成功');
                            this.zcdata = {                
                                id:'',
                                grade:'',
                                username:'',
                                password:'',
                                identity:'',
                                }
                            if(this.type == "T") this.zcdata.grade = sessionStorage.getItem('grade')  
                        } else this.$alert('用户id已存在， 注册失败');
                    })
                }
            })
            
        }
    },
    mounted(){
        if(this.type == 'A') this.zcdata.grade = ''
        else {
            this.zcdata.identity = 'S'
            this.zcdata.grade = sessionStorage.getItem('grade')
    }
    }
}
</script>
<style lang="">
    .zcform{
        position: fixed;
        z-index: 5;
        width: 480px;
        padding: 0 25px 38px 25px;
        left: 50%;
        transform: translateX(-50%);
        margin-top: 180px;
        background-color: rgb(255, 255, 255);
        box-shadow: 0 0 50px rgba(209, 207, 207, 0.3);
        border: 1px solid rgb(64,158,255);
        border-radius: 10px;
    }
    .zc-title{
        height: 45px;
        line-height: 45px;
        margin-bottom: 25px;
        border-bottom: 1px solid rgb(64,158,255);
        /* background-color: rgb(64,158,255); */
    }
</style>