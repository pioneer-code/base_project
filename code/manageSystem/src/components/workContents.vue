<template>
    <div class="work-contents">
        <div class="tianxie-form" v-if="showTx">
            <div class="tx-form-title">
                <span>输入答案</span>
            </div>
            <div class="tx-form-body">
                <el-form ref="txForm" :model="txForm" label-width="80px">
                  <el-form-item label="答案">
                    <el-input type="textarea" v-model="txForm.answer"></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="tijiaoTx()">提交</el-button>
                    <el-button @click="showTx = false">取消</el-button>
                  </el-form-item>
                </el-form>
            </div>
        </div>
        <div class="tianxie-form" v-if="showPg">
            <div class="tx-form-title">
                <span>输入分值及评价</span>
            </div>
            <div class="tx-form-body">
                <el-form ref="pgForm" :model="pgForm" label-width="80px">
                  <el-form-item label="得分">
                    <el-input type="text" v-model="pgForm.score_get"></el-input>
                  </el-form-item>
                  <el-form-item label="评价">
                    <el-input type="textarea" v-model="pgForm.evaluation"></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="tijiaoPg()">提交</el-button>
                    <el-button @click="showPg = false">取消</el-button>
                  </el-form-item>
                </el-form>
            </div>
        </div>
        <div class="content-zone">
            <div class="content-title">
                <div class="student" v-if="type == 'S'">
                    <span :class="{'span-active':conIndex == 0}" @click="conIndex = 0">全部作业内容</span>
                    <span :class="{'span-active':conIndex == 1}" @click="conIndex = 1">已批改作业</span>
                </div>
                <div class="teacher" v-if="type == 'T'">
                    <span style="border:none; color:rgb(97,112,195)">已提交作业</span>
                </div>
            </div>
            <div class="content-body">
                <div class="student" v-if="type == 'S'">
                    <div class="one" v-show="conIndex == 0">
                        <div class="none-a" v-if="allCons.length == 0">该作业简介下还没有发布题目</div>
                        <div class="content-item" v-for="(item , index) in allCons" :key="index">
                            <h3>{{index+1}}.{{item.problems}}</h3>
                            <span>题目类型：{{item.category}}</span>
                            <span>提交状态：<i v-if="item.submit == 0">未交</i> <i v-if="item.submit == 2">已交</i><i v-if="item.submit == 3">已批改</i></span>
                            <span>分值：{{item.score}}</span>
                            <div class="tianxie">
                                <el-button @click="tianxie(item.subjectId)" type="primary" icon="el-icon-edit" circle :disabled="item.submit == 2 || item.submit == 3"></el-button>
                            </div>
                        </div>
                    </div>
                    <div class="two" v-show="conIndex == 1">
                        <div class="none-c" v-if="checkedCons.length == 0">该作业简介下还没已批改的题目</div>
                        <div class="content-item" v-for="(item, index) in checkedCons" :key="index">
                            <h3>{{index+1}}.{{item.problems}}</h3>
                            <span>题目类型：{{item.category}}</span>
                            <span>分值：{{item.score}}</span>
                            <span>我的得分：{{item.scoreGet}}</span>
                            <span style="color:rgb(0, 0, 0)">我的答案：{{item.answer}}</span>
                            <span style="color:rgb(0, 0, 0)">老师评价：{{item.evaluation}}</span>
                        </div>
                    </div>
                </div>
                <div class="teacher" v-if="type == 'T'">
                    <div class="none-tijiao" v-if="submitedcCons.length == 0">该学生没有待批改作业</div>
                    <div class="content-item" v-for="(item, index) in submitedcCons" :key="index">
                        <h3>{{index+1}}.{{item.problems}}</h3>
                        <span>题目类型：{{item.category}}</span>
                        <span>分值：{{item.score}}</span>
                        <span style="color:rgb(0, 0, 0)">学生答案：{{item.answer}}</span>
                        <div class="tianxie" @click="pigai(item.subjectId)">
                            <el-button type="primary" icon="el-icon-edit" circle></el-button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name:'workContents',
    data(){
        return{
            conIndex:0,
            showTx:false,
            showPg:false,
            stu_id:sessionStorage.getItem('stu_id'), //老师批改的学生的id
            w_id:sessionStorage.getItem('w_id'), // 学生浏览的作业简介
            pigai_word_id:sessionStorage.getItem('pigai_word_id'), // 老师批改的作业简介
            u_id:sessionStorage.getItem('id'), // 当前用户的id
            type:sessionStorage.getItem('type'),
            allCons:[],
            submitedcCons:[],
            checkedCons:[],
            txForm:{
                word_id:sessionStorage.getItem('w_id'),
                subject_id:'',
                student_id:sessionStorage.getItem('id'),
                answer:'',
                submit:2
            },
            pgForm:{
                student_id:sessionStorage.getItem('stu_id'),
                subject_id:'',
                score_get:'',
                word_id:sessionStorage.getItem('pigai_word_id'),
                evaluation:''
            }
            
        }
    },
    methods:{
        getStuContents(u_id,word_id, submit){
            this.$axios.request({
                url:'/workDetails/get_by_submit',
                method:'POST',
                params:{'student_id':u_id,'word_id':word_id, 'submit':submit}
            }).then(res=>{
                if(submit == 3){
                    this.checkedCons = res.data.datas
                } else if(submit == 2) {
                    this.submitedcCons = res.data.datas
                }
                console.log(res);
            }).catch(err=>{
                console.log(err);
            })
        },
        getAllContents(word_id){
            this.$axios.request({
                url:'/workDetails/get_by_wordId/',
                method:'POST',
                params:{'word_id':word_id, 'student_id':this.u_id}
            }).then(res=>{
                this.allCons = res.data.datas
                console.log(this.allCons);
            }).catch(err=>{
                console.log(err);
            })
        },
        tianxie(subjectId){
            this.showTx = true
            this.txForm.subject_id = subjectId
        },
        tijiaoTx(){
            this.$axios.request({
                url:'/workDetails/update_word_student/',
                method:'POST',
                params:this.txForm
            }).then(res=>{
                this.$alert('提交成功')
                this.showTx = false
                this.txForm.answer = ''
                this.getAllContents(this.w_id)
            }).catch(err=>{
                console.log(err);
            })
        },
        pigai(subject_id){
            this.showPg = true
            this.pgForm.subject_id = subject_id
        },
        tijiaoPg(){
            this.$axios.request({
                url:'/workDetails/update_word_teacher/',
                method:'POST',
                params:this.pgForm
            }).then(res=>{
                this.pgForm.score_get = this.pgForm.evaluation = ''
                this.showPg = false
                this.getStuContents(this.stu_id, this.pigai_word_id, 2)
                this.$alert('提交成功')
            }).catch(err=>{
                this.$alert('批改失败，请稍后再试')
            })
        }
    },
    created(){
        // let w_id = this.$route.params.w_id;
        if(this.type == 'T'){
            this.getStuContents(this.stu_id, this.pigai_word_id, 2)
        } else if (this.type == 'S'){
            this.getAllContents(this.w_id)
            this.getStuContents(this.u_id,this.w_id, 3)
        }
    }
}
</script>
<style lang="">
    .span-active{
        color: rgb(97,112,195);
    }
    .work-contents{
        background-color: rgb(248, 248, 248);
    }
    .work-contents .content-zone{
        width: 1000px;
        min-height: 800px;
        position: relative;
        left: 50%;
        transform: translateX(-50%);
        background-color: #fff;
    }
    .content-zone .content-title, .content-zone .content-body{
        padding: 18px;
    }
    .content-zone .content-title{
        border-bottom: 1px solid skyblue;
    }
    .content-title span{
        padding: 0 10px;
        cursor: pointer;
    }
    .content-title span:nth-child(1){
        border-right: 2px solid gray;  
    }
    .content-body .content-item{
        display: flex;
        flex-direction: column;
        position: relative;
        min-height: 48px;
        border: 1px solid skyblue;
        margin: 15px;
        padding: 8px;
    }
    .content-item .tianxie{
        position: absolute;
        top: 12px;
        right: 8px;
    }
    .content-item span{
        font-size: 13px;
        color: rgb(179, 179, 179);
        margin: 2px;   
    }
    .tianxie-form{
        position: fixed;
        top: 160px;
        left: 50%;
        z-index: 8;
        transform: translateX(-50%);
        width: 480px;
        min-height: 200px;
        background-color: #fff;
        border: 1px solid rgb(64,158,255);
        border-radius: 10px;
        box-shadow: 0 0 500px rgba(188, 204, 219, 0.6);
        
    }
    .tianxie-form .tx-form-title, .tianxie-form .tx-form-body{
        padding: 18px;
    }
    .tianxie-form .tx-form-title{
        border-bottom: 1px solid rgb(64,158,255);
    }
</style>