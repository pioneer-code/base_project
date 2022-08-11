<template>
    <div>
        <div class="top-banner">
            <h3>课程管理系统</h3>
            <div class="b-right">
                <span class="b-name">你好,{{uname}}</span>
                <span class="logout" @click="logout">退出登录</span>
            </div>
        </div>
        <mynav :type="type"  v-on:changeNav="changeNavIndex"></mynav>
        <courses :type="type" v-show="curNavIndex === 0"></courses>
        <material :type="type" v-show="curNavIndex === 1"></material>
        <homework :type="type" v-show="curNavIndex === 2"></homework>
        <zcform   :type="type" v-show="curNavIndex === 3"></zcform>
    </div>
</template>
<script>
import courses from '../components/courses.vue'
import material from '../components/material.vue'
import homework from '../components/homework.vue'
import zcform from '../components/zcform.vue'
import mynav from '../components/mynav.vue'
export default {
    name:'home',
    components:{mynav, courses, material, homework, zcform},
        data(){
        return {
            curNavIndex:0,
            type:sessionStorage.getItem('type'),
            uname:sessionStorage.getItem('uname'),
            grade:sessionStorage.getItem('grade'),
            id:sessionStorage.getItem('id'),
        }
    },
    methods:{
        changeNavIndex(data){
            this.curNavIndex = data
        },
        logout(){
            this.$confirm("是否退出系统").then(()=>{
                sessionStorage.clear();
                this.$router.push({name:'login'})
            })
        }
    },
    watch:{

    },
    created(){
        if(! sessionStorage.getItem('id')) {
            alert('请先登录！')
            this.$router.push({name:'login'})
        }
    },
    mounted(){

    }
}
</script>
<style lang="">
    .top-banner{
        position: fixed;
        z-index: 5;
        top: 0;
        height: 88px;
        width: 100%;
        background: url('../assets/images/navbg.png') no-repeat;
        background-size: cover;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0 20px;
        color: white;
        overflow: hidden;
    }
    .top-banner .b-right{
        display: flex;
        align-items: center;
        margin-right: 35px;
    }
    .b-name{
        margin-right: 15px;
    }
    .logout{
        color: rgb(253, 99, 28);
        cursor: pointer;
    }
</style>