<template>
    <div class="all stus">
        <div class="all-title">
            <div class="biaoti">学生信息</div>
            <div class="zhuangshi"></div>
        </div>
        <div class="all-body">
            <div class="table-zone">
                <el-table
                :data="stus"
                border
                height="480"
                style="width: 100%">
                <el-table-column
                  prop="identity"
                  label="用户身份（S:学生 T:教师）"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="grade"
                  label="用户班级"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="id"
                  label="用户id">
                </el-table-column>
                <el-table-column
                  prop="username"
                  label="用户名">
                </el-table-column>
                <el-table-column
                  fixed="right"
                  label="操作"
                  width="100">
                  <template slot-scope="scope">
                    <el-button @click="delUser(scope.row)" type="text" size="small">删除</el-button>
                  </template>
                </el-table-column>
                </el-table>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data(){
        return {
            stus:[]
        }
    },
    methods:{
        getStus(){
            this.$axios.request({
                url:'/user/select_identity',
                method:'POST',
                params:{'identity':'S'}
            }).then(res=>{
                this.stus = res.data.datas
            }).catch(err=>{
                console.log(err);
            })
        },
        delUser(e){
            this.$confirm('确定删除该用户吗？').then(r=>{
                this.$axios.request({
                    url:'/user/delete/',
                    method:'POST',
                    params:{'id':e.id}
                }).then(res=>{
                    this.getStus()
                    this.$alert('删除成功')
                }).catch(err=>{
                    console.log(err);
                    this.$alert('删除失败，稍后再试')
                })
            })
        }
    },
    mounted(){
        this.getStus()
    }
}
</script>
<style lang="">
    .all{
        background-color: rgb(245, 245, 245);
        padding-top: 100px;
        width: 80%;
        max-width: 1180px;
        min-width: 1000px;
        margin-left: 260px;
    }
    .all-title{
        position: relative;
        height: 50px;
        padding-left: 25px;
        line-height: 50px;
        font-size: 20px;
        border-bottom: 2px solid #b3b3b3;
    }
    .all-body{
        padding: 20px;
        min-height: 560px;
    }
    .zhuangshi{
        position: absolute;
        top: 48px;
        width: 80px;
        height: 3px;
        background-color: rgb(116, 90, 199);
    }
    .table-zone{
        box-shadow: 0 0 50px rgb(202, 202, 202);
        padding: 10px;
    }
</style>