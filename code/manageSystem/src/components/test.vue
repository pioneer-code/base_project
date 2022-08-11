<template>
    <div>
        <button @click="test">POST</button>
    </div>
</template>
<script>
export default {
    name:'test',
    methods:{
        test(){
            this.$axios.request({
                // url:'/api/workDetails/select_word',
                // method:'POST',
                // params:{'id':'1520193101', 'grade':'人工智能192'}


                // url:'/api/workDetails/select_word_teacher',
                // method:'POST',
                // params:{'student_id':'1520193101', 'word_id':'123','teacher_id':'222' }

                url:'/sources/select_grade',
                method:'POST',
                params:{'student_id':'1520193101', 'word_id':'123','teacher_id':'222' }
            }).then((res)=>{
                console.log(res);
            })
        }
    }
}
</script>