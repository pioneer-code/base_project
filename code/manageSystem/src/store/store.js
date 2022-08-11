import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const store = new Vuex.Store({
    state: {
        userType: 'S',
        curUser: {},
        studentWork: {}
    },
    mutations: {
        updateCurUser(state, newv) {
            state.curUser = newv
        },
        updateStudentWork(state, newv) {
            state.studentWork = newv
        }
    }
})
export default store