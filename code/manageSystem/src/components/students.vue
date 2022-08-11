<template>
    <div class="grade-students">
        <div class="table-zone">
            <div class="table-title">
                <div>班级学生</div>
                <div class="close-students">
                    <el-button type="danger" @click="closeST"><i class="el-icon-close" ></i></el-button>  
                </div>
            </div>
            <el-table
                :data="students"
                style="width: 100%"
                height="500"
                >
                <el-table-column
                  prop="grade"
                  label="班级"
                  width="240">
                </el-table-column>
                <el-table-column
                  prop="id"
                  label="用户id"
                  width="240">
                </el-table-column>
                <el-table-column
                  prop="username"
                  label="姓名">
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-link @click="handleEdit(scope.$index, scope.row)">查看<i class="el-icon-view el-icon--right"  ></i> </el-link>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>
<script>
export default {
    name:'students',
    data(){
        return{
        students:[]
        }
    },
    methods:{
        getStudents(){
            this.$axios.request({
                url:'/user/select_grade/',
                method:'POST',
                params:{'grade':sessionStorage.getItem('grade')}
            }).then(res=>{
                this.students = res.data.datas
            }).catch(err=>{
                console.log(err);
            })
        },
        goToContents(stu_id){
            sessionStorage.setItem('stu_id', stu_id)
            this.$router.push({name:'workContents', params:{stu_id:stu_id}})
        },
        handleEdit(e1, e2){
            // go to contents
            console.log(e1, e2);
            this.goToContents(e2.id)
        },
        closeST(){
            this.$emit('changeST', false)
        }
    },
    created(){
        this.getStudents()
    }
}
</script>
<style lang="">
    .grade-students{
        z-index: 8;
        min-height: 500px;
        min-width: 1000px;
        position: fixed;
        padding: 18px;
        background-color: #fff;
        border-radius: 10px;
        left: 50%;
        top: 105px;
        transform: translateX(-40%);
        box-shadow:  0 0 600px rgb(241, 241, 241);
    }
    .table-title{
        display: flex;
        justify-content: space-between;
        border-bottom: 1px solid rgb(238, 238, 238);
        padding: 10px;
        align-items: center;
    }
</style>