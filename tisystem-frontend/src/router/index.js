/*
 * @Author: wyh 1370804207@qq.com
 * @Date: 2023-05-13 11:35:58
 * @LastEditors: wyh 1370804207@qq.com
 * @LastEditTime: 2023-05-22 21:19:25
 * @FilePath: \tisystem-frontend\src\router\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
/*
 * @Author: wyh 1370804207@qq.com
 * @Date: 2023-05-13 11:35:58
 * @LastEditors: wyh 1370804207@qq.com
 * @LastEditTime: 2023-05-16 11:54:17
 * @FilePath: \tisystem-frontend\src\router\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

import Shouye from '../components/Shouye'
import Qiandao from '../components/Qiandao'
import OneManage from '../components/OneManage'
import PackageManage from '../components/PackageManage'
/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '体检系统', icon: 'dashboard' }
    }]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true },
   
  {
    path: '/Shouye',
    component: Layout,
    redirect: '/Shouye',
    children: [{
      path: '/Shouye',
      name: 'Shouye',
      component:Shouye,
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },
  {
    path: '/Qiandao',
    component: Layout,
    redirect: '/Qiandao',
    children: [{
      path: '/Qiandao',
      name: 'Qiandao',
      component:Qiandao,
      meta: { title: '签到', icon: 'dashboard' }
    }]
  },
  {
    path: '/OneManage',
    component: Layout,
    redirect: '/OneManage',
    children: [{
      path: '/OneManage',
      name: 'OneManage',
      component:OneManage,
      meta: { title: 'OneManage', icon: 'dashboard' }
    }]
  },
  {
    path: '/PackageManage',
    component: Layout,
    redirect: '/PackageManage',
    children: [{
      path: '/PackageManage',
      name: 'PackageManage',
      component:PackageManage,
      meta: { title: 'PackageManage', icon: 'dashboard' }
    }]
  },
  {
    path: '/tjManage01',
    redirect: '/check',
    component: Layout,
    children: [{
      path: '/check',
      name: 'check',
      component: () => import('@/layout/components/check.vue'),
      meta: { title: '检查', icon: 'dashboard' }
    }]
  },
  {
    path: '/tjManage02',
    redirect: '/upload',
    component: Layout,
    children: [{
      path: '/upload',
      name: 'upload',
      component: () => import('@/layout/components/upload.vue'),
      meta: { title: '上传结果', icon: 'dashboard' }
    }]
  }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
