import Vue from 'vue'
import Router from 'vue-router'
import login from '@/pages/login'
import home from '@/pages/home'
import admin from '@/pages/admin'
import test from '@/components/test'
import workContents from '@/components/workContents'

Vue.use(Router)

export default new Router({
    routes: [{
            path: '/',
            name: 'login',
            component: login
        },
        {
            path: '/home',
            name: 'home',
            component: home
        },
        {
            path: '/admin',
            name: 'admin',
            component: admin
        },
        {
            path: '/test',
            name: 'test',
            component: test
        },
        {
            path: '/workContents/',
            name: 'workContents',
            component: workContents,
        }
    ]
})