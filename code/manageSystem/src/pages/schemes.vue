<template>
    <div class="all schemes">
        <div class="all-title">
            <div class="biaoti">课表信息</div>
            <div class="showTjBtn" v-if="type == 'A'">
                <el-button type="primary" @click="showTianjia = true">添加新课程安排</el-button>
            </div>  
            <div class="zhuangshi"></div>
        </div>
        <div class="all-body">
            <div class="bianji-form" v-show="showBianji">
                <div class="cbj-form-title">
                    编辑
                </div>
                <div class="cbj-form-body">
                    <el-form ref="bjForm" :model="bjForm" label-width="80px">
                        <el-form-item label="学期">
                          <el-input type="text" v-model="bjForm.team"></el-input>
                        </el-form-item>
                        <el-form-item label="周数">
                          <el-input type="text" v-model="bjForm.week"></el-input>
                        </el-form-item>
                        <el-form-item label="班级">
                          <el-input type="text" v-model="bjForm.grade"></el-input>
                        </el-form-item>
                        <el-form-item label="第一节">
                          <el-input type="text" v-model="bjForm.first"></el-input>
                        </el-form-item>
                        <el-form-item label="第二节">
                          <el-input type="text" v-model="bjForm.second"></el-input>
                        </el-form-item>
                        <el-form-item label="第三节">
                          <el-input type="text" v-model="bjForm.third"></el-input>
                        </el-form-item>
                        <el-form-item label="第四节">
                          <el-input type="text" v-model="bjForm.fourth"></el-input>
                        </el-form-item>
                        <el-form-item label="第五节">
                          <el-input type="text" v-model="bjForm.fifth"></el-input>
                        </el-form-item>
                        <el-form-item>
                          <el-button type="primary" @click="tijiaoBj()">提交</el-button>
                          <el-button @click="showBianji = false">取消</el-button>
                        </el-form-item>
                  </el-form>
                </div>
            </div>
            <div class="bianji-form" v-show="showTianjia">
                <div class="cbj-form-title">
                    添加新课程安排
                </div>
                <div class="cbj-form-body">
                    <el-form ref="tjForm" :model="tjForm" label-width="80px">
                        <el-form-item label="日期">
                            <el-col >
                              <el-date-picker type="date" placeholder="选择日期" v-model="tjForm.calendar" style="width: 100%;"></el-date-picker>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="学期">
                          <el-input type="text" v-model="tjForm.team"></el-input>
                        </el-form-item>
                        <el-form-item label="周数">
                          <el-input type="text" v-model="tjForm.week"></el-input>
                        </el-form-item>
                        <el-form-item label="班级">
                          <el-input type="text" v-model="tjForm.grade"></el-input>
                        </el-form-item>
                        <el-form-item label="第一节">
                          <el-input type="text" v-model="tjForm.first"></el-input>
                        </el-form-item>
                        <el-form-item label="第二节">
                          <el-input type="text" v-model="tjForm.second"></el-input>
                        </el-form-item>
                        <el-form-item label="第三节">
                          <el-input type="text" v-model="tjForm.third"></el-input>
                        </el-form-item>
                        <el-form-item label="第四节">
                          <el-input type="text" v-model="tjForm.fourth"></el-input>
                        </el-form-item>
                        <el-form-item label="第五节">
                          <el-input type="text" v-model="tjForm.fifth"></el-input>
                        </el-form-item>
                        <el-form-item>
                          <el-button type="primary" @click="tijiaoTj()">提交</el-button>
                          <el-button @click="showTianjia = false">取消</el-button>
                        </el-form-item>
                  </el-form>
                </div>
            </div> 
            <div class="table-zone">
                <el-table
                :data="gradeCourses"
                border
                height="480"
                style="width: 100%">
                <el-table-column
                  prop="calendar"
                  label="日期"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="team"
                  label="学期"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="grade"
                  label="班级"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="first"
                  label="第一大节">
                </el-table-column>
                <el-table-column
                  prop="second"
                  label="第二大节">
                </el-table-column>
                <el-table-column
                  prop="third"
                  label="第三大节">
                </el-table-column>
                <el-table-column
                  prop="fourth"
                  label="第四大节">
                </el-table-column>
                <el-table-column
                  prop="fifth"
                  label="第五大节">
                </el-table-column>
                <el-table-column
                  fixed="right"
                  label="操作"
                  width="100">
                  <template slot-scope="scope">
                    <el-button @click="bianji(scope.row)" type="text" size="small">编辑</el-button>
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
            type:sessionStorage.getItem('type'),
            gradeCourses:[],
            grade : sessionStorage.getItem('grade'),
            showBianji:false,
            showTianjia:false,
            bjForm:{
                calendar:'',
                team:'',
                week:'',
                grade:sessionStorage.getItem('grade'),
                first:'',
                second:'',
                third:'',
                fourth:'',
                fifth:'',
            },
            tjForm:{
                calendar:'',
                team:'',
                week:'',
                grade:'',
                first:'',
                second:'',
                third:'',
                fourth:'',
                fifth:'',
            }
        }
    },
    methods:{
        getGradeCourses(){
            this.$axios.request({
                url:'/timetable/select_all/',
                method:'POST',
                params:{'grade':'this.grade'}
            }).then(res=>{
                this.gradeCourses = res.data.datas
                for(let i of this.gradeCourses){
                    i.calendar = i.calendar.split(' ')[0]
                }
            }).catch(err=>{
                console.log(err);
            })
        },
        bianji(e1){
            console.log(e1);
            this.showBianji = true
            this.bjForm.calendar = e1.calendar
            this.bjForm.week = e1.week
            this.bjForm.team = e1.team
            this.bjForm.first = e1.first
            this.bjForm.second = e1.second
            this.bjForm.third = e1.third
            this.bjForm.fourth = e1.fourth
            this.bjForm.fifth = e1.fifth
        },
        tijiaoBj(){
            this.$axios.request({
                url:'/timetable/update',
                method:'POST',
                params:this.bjForm
            }).then(res=>{
                this.getGradeCourses()
                this.$alert('修改成功')
                this.showBianji = false
            }).catch(err=>{
                this.$alert('修改失败, 请稍后再试')
            })
        },
        tijiaoTj(){
            this.tjForm.calendar = this.tjForm.calendar.getFullYear() + '-' + (this.tjForm.calendar.getMonth() + 1) + '-' + this.tjForm.calendar.getDate();
            this.$axios.request({
                url:'/timetable/add',
                method:'POST',
                params:this.tjForm
            }).then(res=>{
                this.$alert('添加成功')
                this.tjForm = {
                calendar:'',
                team:'',
                week:'',
                grade:sessionStorage.getItem('grade'),
                first:'',
                second:'',
                third:'',
                fourth:'',
                fifth:'',
            }
                this.showTianjia = false
                this.getGradeCourses()
            }).catch(err=>{
                this.$alert('添加失败，该日期已安排课程，修改后再试')
                this.showTianjia = false
                console.log(err);
            })
        }
    },
    mounted() {
        this.getGradeCourses()
    },
}
</script>
<style lang="">
    .showTjBtn{
        position: absolute;
        top: -2px;
        right: 35px;
    }
    .bianji-form{
        position: fixed;
        top: 130px;
        z-index: 10;
        min-width: 600px;
        background-color: #fff;
        border-radius: 10px;
        left: 50%;
        transform: translateX(-45%);
        border: 1px solid rgb(64,158,255);
        box-shadow:  0 0 60px rgba(0, 0, 0, 0.1);
    }
    .bianji-form .cbj-form-title, .bianji-form .cbj-form-body{
        padding: 10px;
    }
    .bianji-form .cbj-form-title{
        border-bottom: 1px solid rgb(64,158,255);
        padding-left: 18px;
    }
</style>