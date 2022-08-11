<template>
    <div class="nav-zone">
        <div class="n-top">
            <div class="n-touxiang">
                {{touxiang}}
            </div>
            <div class="n-uname">{{uname}}</div>
        </div>
        <div class="n-items">
            <div class="st" v-if="type != 'A'">
                <div class="n-item" v-for="(item, index) in navItems" :key="index" @click="changeIndex($event, index)">
                    {{item}}
                </div>
                <div class="n-item" v-if="type == 'T'" @click="changeIndex($event, 3)"> 添加学生</div>
            </div>
            <div class="a" v-if="type == 'A'">
                <div class="n-item" v-for="(item, index) in navItemsA" :key="index" @click="changeIndex($event, index)">
                    {{item}}
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name:'mynav',
    props:{
        type:'',
    },
    data(){
        return{
            navItems:['课程安排','课件资源','作业'],
            navItemsA:['学生信息','老师信息','课表信息','作业信息','课程资源信息','添加新成员'],
            curIndex:0,
            uname:sessionStorage.getItem('uname'),
            touxiang:'',
        }
    },
    methods:{
        changeIndex(e, index){
            this.curIndex = index
            let curr = $(e.target);
            curr.addClass('n-item-active').siblings().removeClass('n-item-active')
            this.$emit('changeNav', index)
        }
    },
    
    mounted(){
        let curnode = $('.n-item').eq(0);
        curnode.addClass('n-item-active').siblings().removeClass('n-item-active')
        if(this.type == 'T') this.touxiang = '教师'
        else if (this.type == 'S') this.touxiang = '学生'
        else this.touxiang = '管理员'
    }
}
</script>
<style lang="">
    .n-item-active{
        background-color: rgb(89,101,201);
    }
    .nav-zone{
        position: fixed;
        top: 108px;
        left: 18px;
        width: 185px;
        min-height: 80vh;
        border-radius: 18px;
        background-color: rgb(77,88,181);
        color: white;
    }
    .n-top{
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        align-items: center;
        height: 185px;
        border-bottom: 1px solid rgb(224, 224, 224);
    }
    .n-top .n-touxiang{
        width: 95px;
        height: 95px;
        border-radius: 50%;
        border: 1px solid gainsboro;
        overflow: hidden;
        margin-top: 30px;
        text-align: center;
        line-height: 95px;
        font-size: 28px;
    }
    .n-touxiang img{
        width: 100%;
        height: 100%;
    }
    .n-items .n-item{
        height: 58px;
        line-height: 58px;
        padding: 0 18px;
    }
    .n-item:hover{
        background-color: rgb(36,57,169);
    }

</style>
