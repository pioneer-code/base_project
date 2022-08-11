<template>
    <div class="material">
        <div class="course-title">
            <div class="biaoti">课件资料</div>
            <div class="zhuangshi"></div>
            <div class="show-up-load" v-if="type == 'T'">
                <el-button type="primary" @click="showUpLoad = true">上传新文件</el-button>
            </div>
        </div>

        <div class="materail-body">
            <div class="material-item" v-for="(item, index) in sources" :key="index">
                <h3>文件简介：{{item.info}}</h3>
                <span class="spn">文件归属科目：{{item.project}}</span>
                <span class="spn">上传人id：{{item.id}}</span>
                <span class="spn">文件归属班级：{{item.grade}}</span>
                <div class="down-load">
                    <el-link type="primary" :href="`/file/${(item.source)}`">点击下载</el-link>
                </div>
                <div class="del" v-if="type == 'T'">
                    <el-button type="danger" icon="el-icon-delete" circle @click="delSource(item.number)"></el-button>
                </div>
            </div>
        </div>
        <div class="up-load" v-show="showUpLoad">
            <form class="up-form" name="myform" action="http://119.23.241.76/sources/add" method="post" enctype="multipart/form-data">
                上传者id：<input type="text" name="id"><br>
                文件归属班级：<input type="text" name="grade"><br>
                文件归属科目：<input type="text" name="project"><br>
                文件简介信息：<input type="text" name="info"><br>
                File:<br>
                <input type="file" name="source"><br>
                <br>
                <input type="submit" name="submit" value="Commit" class="c-btn">
                <el-button type="danger" @click="showUpLoad = false">取消</el-button>
            </form>
        </div>
    </div>
</template>
<script>
export default {
    name:'material',
    props:{
        type:0,
        
    },
    data(){
        return {
            sources:[],
            grade:sessionStorage.getItem('grade'),
            showUpLoad:false
        }
    },
    methods:{
        getSources(){
            this.$axios.request({
                url:'/sources/select_grade/',
                method:'POST',
                params:{'grade':this.grade}
            }).then(res=>{
                this.sources = res.data.data
                console.log(res.data.data);
            }).catch(err=>{     
                console.log(err);
            })
        },
        // upLoade(){
        //     this.$router.push({'name':'home'})
        // },
        delSource(id){
            this.$confirm('确定删除该资源吗？').then(re=>{
                this.$axios.request({
                url:'/sources/delete',
                method:'POST',
                params:{'number':id}
            }).then(res=>{
                this.getSources()
                this.$alert('删除成功')
            }).catch(err=>{
                this.$alert('删除失败，稍后再试')
                console.log(err);
            })
            })

        }
    },
    mounted(){
        this.getSources()
    }

}
</script>
<style lang="">
    .material{
        background-color: rgb(243,243,243);
        padding-top: 120px;
        width: 80%;
        max-width: 1180px;
        min-width: 1000px;
        min-height: 700px;
        margin-left: 260px;
    }
    .course-title{
        position: relative;
        height: 50px;
        padding-left: 25px;
        line-height: 50px;
        font-size: 20px;
        border-bottom: 2px solid #b3b3b3;
    }
    .zhuangshi{
        position: absolute;
        top: 48px;
        width: 75px;
        height: 3px;
        background-color: rgb(116, 90, 199);
    }
    .materail-body{
        /* padding: 35px; */
        padding: 30px 50px;
    }
    .material-item{
        display: flex;
        position: relative;
        flex-direction: column;
        min-height: 80px;
        background-color: #fff;
        border-radius: 18px;
        margin: 18px 0;
        padding: 10px;
        word-break: break-all;
    }
    .material-item .spn{
        color: rgb(155, 155, 155);
        font-size: 13px;
        margin: 3px;
    }
    .down-load{
        position: absolute;
        top: 16px;
        right: 25px;
    }
    .show-up-load{
        position: absolute;
        top: -5px;
        right: 55px;
    }
    .up-load{
        min-width: 360px;
        position: fixed;
        left: 50%;
        transform: translateX(-50%);
        background-color: #fff;
        padding: 12px;
        border-radius: 10px;
        border: 1px solid rgb(64,158,255);
        top: 160px;
        box-shadow: 0 0 50px rgba(0, 0, 0, 0.1);
    }
    .up-form{
        display: flex;
        flex-direction: column;

    }
    .up-form input{
        height: 25px;
    }
    .up-form .c-btn{
        height: 35px;
        margin-bottom: 8px;
        /* margin: 10px; */
    }
    .del{
        position: absolute;
        top: 56px;
        right: 25px;
    }
</style>