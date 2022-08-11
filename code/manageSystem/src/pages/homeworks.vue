<template>
    <div class="all hmworks">
        <div class="all-title">
            <div class="biaoti">作业信息</div>
            <div class="zhuangshi"></div>
        </div>
        <div class="all-body work-body">
            <div class="work-body fabu">
                <div class="work-item" v-for="(item, index) in gradeWorks" :key="index">
                    <h3 style="padding-bottom:2px">{{item.wordName}}</h3>
                    <div>发布人id：{{item.teacherId}}</div>
                    <div>作业归属班级：{{item.grade}}</div>
                    <div>时间：{{item.startTime}}---{{item.endTime}}</div>
                    <div>简介：{{item.info}}</div>
                    <div class="edit" >
                        <el-button type="primary" icon="el-icon-edit" circle @click="addContent(item.wordId)"></el-button>
                        <el-button type="danger" icon="el-icon-delete" circle @click="deleteContent(item.wordId)"></el-button>
                    </div>          
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data(){
        return{
               gradeWorks:[{
               id:'222',
               grade:'人工智能192',
               word_name:'java作业',
               start_time:'2002-2-13',
               end_time:'2022-2-13',
               info:'要求不高',
               word_id:'426337e4844541a08459e65af6f5fd6a'
           }]
        }
    },
    methods:{
        getAllWorks(){
            this.$axios.request({
                url:'/workInfo/get_workInfo_all/',
                method:'POST',
            }).then((res)=>{
                this.gradeWorks = res.data.datas
            }).catch((err)=>{
                console.log(err);
            })
        },
        deleteContent(w){
            this.$confirm('确定要删除该简介下的所有作业吗').then(()=>{
                this.$axios({
                    url:'/workDetails/delete_word',
                    method:'POST',
                    params:{'word_id':w}
                }).then((res)=>{
                    this.getAllWorks()
                    this.$alert('成功删除')
                    }).catch(()=>{
                        this.$alert('删除失败')
                    })
            })
        },
    },
    mounted() {
        this.getAllWorks()
    },
}
</script>
<style lang="">
    .work-body{
        padding: 35px;
        display: flex;
        justify-content: flex-start;
        flex-wrap: wrap;

    }
    .work-body .work-item{
        position: relative;
        width: 300px;
        min-height: 120px;
        word-wrap: break-word;
        background-color: #fff;
        margin: 20px 25px;
        padding: 15px;
        box-shadow: 0 0 18px rgba(226, 226, 226, 0.2);
    }
    .work-item .edit{
        position: absolute;
        right: 12px;
        top: 66px;
    }
    .work-item div{
        font-size: 13px;
        padding: 2px 0;
        max-width: 180px;
        color: #9e9e9e;
    }
</style>