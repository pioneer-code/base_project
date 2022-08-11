<template>
    <div class="all stus">
        <div class="all-title">
            <div class="biaoti">学生信息</div>
            <div class="zhuangshi"></div>
        </div>
        <div class="all-body">
            <div class="table-zone">
                <el-table
                :data="teachers"
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
        return{
            teachers:[]
        }
    },
    methods: {
        getSteachers(){
            this.$axios.request({
                url:'/user/select_identity',
                method:'POST',
                params:{'identity':'T'}
            }).then(res=>{
                this.teachers = res.data.datas
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
    mounted() {
        this.getSteachers()
    },
}
</script>