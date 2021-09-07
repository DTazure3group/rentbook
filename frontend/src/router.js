
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import RentalManager from "./components/RentalManager"

import BookManager from "./components/BookManager"

import PaymentManager from "./components/PaymentManager"

import AlertManager from "./components/AlertManager"


import Mypage from "./components/Mypage"
import PointManager from "./components/PointManager"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/rentals',
                name: 'RentalManager',
                component: RentalManager
            },

            {
                path: '/books',
                name: 'BookManager',
                component: BookManager
            },

            {
                path: '/payments',
                name: 'PaymentManager',
                component: PaymentManager
            },

            {
                path: '/alerts',
                name: 'AlertManager',
                component: AlertManager
            },


            {
                path: '/mypages',
                name: 'Mypage',
                component: Mypage
            },
            {
                path: '/points',
                name: 'PointManager',
                component: PointManager
            },



    ]
})
