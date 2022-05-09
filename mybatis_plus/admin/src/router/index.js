import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import Emps from '../components/emp/Emps'
import EmpHistory from '../components/emp/EmpHistory'
import Departments from '../components/department/Departments.vue'
import DepHistory from '../components/department/DepHistory.vue'
import Approved from '../components/approve/Approved.vue'
import NotApproved from '../components/approve/NotApproved.vue'
import SignedIn from '../components/SignInStatus/SignedIn'
import NotSignIn from '../components/SignInStatus/NotSignIn'

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  {
      path: '/home',
      component: Home,
      redirect: '/meetingEmps',
      children: [
          { path: '/Welcome', component: Welcome },
          { path: '/meetingEmps', component: Emps },
            { path: '/meetingEmpHistory', component: EmpHistory },
          { path: '/department', component: Departments },
          { path: '/depHistory', component: DepHistory },
          { path: '/approved', component: Approved },
          { path: '/notApproved', component: NotApproved },
          { path: '/SignedIn', component: SignedIn },
          { path: '/NotSignIn', component: NotSignIn }
      ]
  }
]

const router = new VueRouter({
  routes
})

// 挂载路由导航守卫
// router.beforeEach((to, from, next) => {
//   // to 表示将要访问的路径
//   // from 表示从哪个路径跳转而来
//   // next 是一个函数，表示放行
//   // next() 直接放行 next('/login') 强制跳转
//   if (to.path === '/login') return next()
//   // 获取token
//   const tokenStr = window.sessionStorage.getItem('token')
//   if (!tokenStr) return next('/login')
//   next()
// })

export default router
