<!--
 * @Author: wyh 1370804207@qq.com
 * @Date: 2023-05-16 11:51:27
 * @LastEditors: wyh 1370804207@qq.com
 * @LastEditTime: 2023-05-16 17:04:44
 * @FilePath: \tisystem-frontend\src\layout\components\Sidebar\index.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div :class="{'has-logo':showLogo}">
    <logo v-if="showLogo" :collapse="isCollapse" />
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        router="true"
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="false"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <!-- <sidebar-item v-for="route in routes" :key="route.path" :item="route" :base-path="route.path" /> -->
        <el-menu-item index="/Shouye">
          <i class="el-icon-menu" />
          <span slot="title">首页</span>
        </el-menu-item>
        <el-menu-item index="/Qiandao">
          <i class="el-icon-menu" />
          <span slot="title">签到</span>
        </el-menu-item>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-location" />
            <span slot="title">体检管理</span>
          </template>
          <el-menu-item index="2-1">检查</el-menu-item>
          <el-menu-item index="2-2">上传结果</el-menu-item>
        </el-submenu>

        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-location" />
            <span slot="title">项目管理</span>
          </template>
          <el-menu-item index="/OneManage">单项管理</el-menu-item>
          <el-menu-item index="/PackageManage">套餐管理</el-menu-item>
        </el-submenu>


      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Logo from './Logo'
// import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'

export default {
  components: { Logo },
  computed: {
    ...mapGetters([
      'sidebar'
    ]),
    routes() {
      return this.$router.options.routes // 当前路由配置
    },
    activeMenu() {
      const route = this.$route
      const { meta, path } = route
      // if set path, the sidebar will highlight the path you set
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo
    },
    variables() {
      return variables
    },
    isCollapse() {
      return !this.sidebar.opened
    }
  }
}
</script>
