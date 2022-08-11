<template>
    <div>
        <div class="top-banner">
            <h3>课程管理系统后台</h3>
            <div class="b-right">
                <span class="b-name">你好，{{uname}}</span>
                <span class="logout" @click="logout">退出登录</span>
            </div>
        </div>
        <mynav v-on:changeNav="changeNavIndex" :type="type"></mynav>
        <zcform :type="type" v-show="curNavIndex === 5"></zcform>
        <homeworks v-show="curNavIndex === 3"></homeworks>
        <schemes v-show="curNavIndex === 2"></schemes> 
        <sources v-show="curNavIndex === 4"></sources> 
        <students v-show="curNavIndex === 0"></students>
        <teachers v-show="curNavIndex === 1"></teachers>
    </div> 
</template>
<script>

import zcform from '../components/zcform.vue'
import mynav from '../components/mynav.vue'
import homeworks from './homeworks.vue'
import schemes from './schemes.vue'
import sources from './sources.vue'
import students from './students.vue'
import teachers from './teachers.vue'
export default {
    name:'admin',
    components:{mynav, zcform,homeworks, schemes, sources, students, teachers},
    data(){
        return {
            curNavIndex:0,
            uname:sessionStorage.getItem('uname'),
            id:sessionStorage.getItem('id'),
            type:sessionStorage.getItem('type')

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
    mounted(){
        console.log(typeof this.type);
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
        background: url(../assets/images/navbg.png) no-repeat;
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
        color: rgb(255, 97, 97);
        cursor: pointer;
    }
</style>