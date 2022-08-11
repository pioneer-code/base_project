<template>
    <div class="homework">
        <div class="homework-title">
            <div class="biaoti h-sub-nav">
                <div class="techer" v-if="type == 'T'">
                    <span class="sp" @click="hSubNavIndex = 0" :class="{'span-active':hSubNavIndex == 0}">批改作业</span>
                    <span class="sp" @click="hSubNavIndex = 1" :class="{'span-active':hSubNavIndex == 1}">发布作业</span>
                </div>
                <div class="student" v-if="type == 'S'">
                    <span v-if="type == 'S'" style="border-right:none;" :class="{'span-active':hSubNavIndex == 0, 'sp':true}"> 我的作业 </span>
                </div>
            </div>
        </div>
        <div class="teacher" v-if="type == 'T'" >     
            <div class="work-body pigai" v-show="hSubNavIndex == 0">
                <students v-if="showStudents" v-on:changeST="changeST()"></students>
                <div class="work-item" v-for="(item, index) in gradeWorks" :key="index">
                    <h3 style="padding-bottom:2px">{{item.wordName}}</h3>
                    <div>发布人id：{{item.teacherId}}</div>
                    <div>时间：{{item.startTime}}---{{item.endTime}}</div>
                    <div>简介：{{item.info}}</div>
                    <div class="edit" @click="pigai(item.wordId)">
                        <el-link>查看<i class="el-icon-view el-icon--right"></i> </el-link>
                    </div>   
                </div>
            </div>
            <div class="work-body fabu" v-show="hSubNavIndex == 1">
                <div class="work-item" v-for="(item, index) in gradeWorks" :key="index">
                    <h3 style="padding-bottom:2px">{{item.wordName}}</h3>
                    <div>发布人id：{{item.teacherId}}</div>
                    <div>时间：{{item.startTime}}---{{item.endTime}}</div>
                    <div>简介：{{item.info}}</div>
                    <div class="edit" >
                        <el-button type="primary" icon="el-icon-edit" circle @click="addContent(item.wordId)"></el-button>
                        <el-button type="danger" icon="el-icon-delete" circle @click="deleteContent(item.wordId)"></el-button>
                    </div>          
                </div>
                <div class="add-introduce work-item" @click="addIntroduce()">
                    添加新作业简介
                </div>
                <div class="jianjie-form" v-if="addingIn">
                    <div class="jj-form-title">
                        <span>添加新作业简介</span>
                    </div>
                    <div class="jj-form-body">
                        <el-form ref="form" :model="form" label-width="80px">
                          <el-form-item label="作业名称">
                            <el-input v-model="form.word_name"></el-input>
                          </el-form-item>
                          <el-form-item label="完成时间">
                            <el-col :span="11">
                              <el-date-picker type="date" placeholder="作业开始" v-model="form.start_time" style="width: 100%;"></el-date-picker>
                            </el-col>
                            <el-col class="line" :span="1">-</el-col>
                            <el-col :span="11">
                              <el-date-picker type="date" placeholder="作业结束" v-model="form.end_time" style="width: 100%;"></el-date-picker>
                            </el-col>
                          </el-form-item>
                          <el-form-item label="作业描述">
                            <el-input type="textarea" v-model="form.info"></el-input>
                          </el-form-item>
                          <el-form-item>
                            <el-button type="primary" @click="onSubmit">立即创建</el-button>
                            <el-button @click="guanbi">取消</el-button>
                          </el-form-item>
                        </el-form>
                    </div>
                    
                </div>
                <div class="jianjie-form" v-if="addingNe">
                    <div class="jj-form-title">
                        <span>添加新作业内容</span>
                    </div>
                    <div class="jj-form-body">
                        <el-form ref="form2" :model="form2" label-width="80px">
                          <el-form-item label="题号">
                            <el-input v-model="form2.subject_id"></el-input>
                          </el-form-item>
                          <el-form-item label="题目类型">
                            <el-input v-model="form2.category"></el-input>
                          </el-form-item>
                          <el-form-item label="问题">
                            <el-input type="textarea" v-model="form2.problem"></el-input>
                          </el-form-item>
                          <el-form-item>
                            <el-button type="primary" @click="submitContent">立即创建</el-button>
                            <el-button @click="guanbiContent">取消</el-button>
                          </el-form-item>
                        </el-form>
                    </div>
                </div>
            </div>
        </div>
        <div class="student" v-if="type == 'S'">
            <div class="work-body pigai" v-show="hSubNavIndex == 0">
                <div class="work-item" v-for="(item, index) in gradeWorks" :key="index">
                    <h3 style="padding-bottom:2px">{{item.wordName}}</h3>
                    <div>发布人id：{{item.teacherId}}</div>
                    <div>时间：{{item.startTime}}---{{item.endTime}}</div>
                    <div>简介：{{item.info}}</div>
                    <div class="edit" @click="tianxie(item.wordId)">
                       <el-button type="primary" icon="el-icon-edit" circle ></el-button>
                    </div>   
                </div>
            </div>
        </div>
        
    </div>
</template>
<script>


import students from './students.vue'
export default {
    name:'homework',
    components:{students},
    props:{
        type:''
    },
    data(){
        return {
           hSubNavIndex:0,
           gradeWorks:[{
               id:'222',
               grade:'人工智能192',
               word_name:'java作业',
               start_time:'2002-2-13',
               end_time:'2022-2-13',
               info:'要求不高',
               word_id:'426337e4844541a08459e65af6f5fd6a'
           }],
           addingIn:false,
           addingNe:false,
           showStudents:false,

           form: {
                word_name: '',
                grade:sessionStorage.getItem('grade'),
                start_time: '',
                end_time: '',
                info: '',
                id:sessionStorage.getItem('id')
          },
          form2:{
              word_id:'',
              subject_id:'',
              student_id:'111',
              problem:' ',
              answer:'',
              category:'',
              score_get:0,
              score:100,
              submit:0,
              evaluation:'',
          },
        }
    },
    methods:{
        getGradeWorks(){
            this.$axios.request({
                url:'/workInfo/get_workInfo/',
                method:'POST',
                params:{'grade':sessionStorage.getItem('grade')}
            }).then((res)=>{
                this.gradeWorks = res.data.datas
            }).catch((err)=>{
                console.log(err);
            })
        },
        addIntroduce(){
            this.addingIn = true
        },
        addContent(w){
            this.addingNe = true;
            this.form2.word_id = w
        },
        guanbi(){
            this.addingIn = false
        },
        guanbiContent(){
            this.addingNe = false
        },
        pigai(w_id){
            this.showStudents = true
            sessionStorage.setItem('pigai_word_id', w_id)
        },
        changeST(e){
            this.showStudents = e
        },
        tianxie(w_id){
            sessionStorage.setItem('w_id', w_id)
            this.$router.push({name:'workContents', params:{w_id:w_id}})
        },
        deleteContent(w){
            this.$confirm('确定要删除该简介下的所有作业吗').then(()=>{
                this.$axios({
                    url:'/workDetails/delete_word',
                    method:'POST',
                    params:{'word_id':w}
                }).then((res)=>{
                    this.getGradeWorks()
                    this.$alert('成功删除')
                    }).catch(()=>{
                        this.$alert('删除失败')
                    })
            })
        },
        async submitContent(){
            this.$axios.request({
                url:'/workInfo/word_content/',
                method:'POST',
                params:this.form2
            }).then((res)=>{
                this.$alert('添加成功')
                this.form2.subject_id = this.form2.problem = this.form2.category = ''
            }).catch((err)=>{
                this.$alert('添加失败, 题号已存在')
            })

        },
        async onSubmit() {
            this.form.start_time = this.form.start_time.getFullYear() + '-' + (this.form.start_time.getMonth() + 1) + '-' + this.form.start_time.getDate();
            this.form.end_time = this.form.end_time.getFullYear() + '-' + (this.form.end_time.getMonth() + 1) + '-' + this.form.end_time.getDate();
            let res = await this.$axios.request({
                url:'/workInfo/word_information/',
                method:'POST',
                params:this.form
            })
            if(res.data.word_id) {
                this.$alert('添加成功')
                this.getGradeWorks()
            }
            else this.$alert('添加失败')
            console.log(res);
            this.form = {
                word_name: '',
                grade:'',
                start_time: '',
                end_time: '',
                info: '',
                id:sessionStorage.getItem('id')
            }  
      }
    },
    mounted(){
        
    },
    created(){
        this.getGradeWorks()
    }
    
}
</script>
<style lang="">
    .homework{
        background-color: rgb(245, 245, 245);
        padding-top: 100px;
        width: 80%;
        max-width: 1180px;
        min-width: 1200px;
        min-height: 888px;
        margin-left: 260px;
    }
    .homework-title{
        position: relative;
        height: 50px;
        padding-left: 25px;
        line-height: 50px;
        font-size: 20px;
        border-bottom: 2px solid #b3b3b3;
    }
    .biaoti span{
        cursor: pointer;
        padding: 0 15px 13px 15px;

    }
    .biaoti span:nth-child(1){
        border-right: #b3b3b3 2px solid;
    }
    .span-active{
        color: rgb(97,112,195);
        /* border-bottom: 3px solid rgb(97,112,195); */
    }
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
        color: #9e9e9e;
    }
    .jianjie-form{
        position: fixed;
        z-index: 9;
        left: 50%;
        transform: translateX(-50%);
        width: 480px;
        background-color: #fff;
        border: 1px solid rgb(64,158,255);
        border-radius: 10px;
        box-shadow: 0 0 500px rgba(188, 204, 219, 0.6);
        
    }
    .jianjie-form .jj-form-title, .jianjie-form .jj-form-body{
        padding: 18px;
    }
    .jianjie-form .jj-form-title{
        border-bottom: 1px solid rgb(64,158,255);
    }


</style>