<!--
 * @Author: wyh 1370804207@qq.com
 * @Date: 2023-05-16 11:56:04
 * @LastEditors: wyh 1370804207@qq.com
 * @LastEditTime: 2023-05-16 16:07:27
 * @FilePath: \tisystem-frontend\src\components\Shouye.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div>
    <div>
    <el-image
      style="width: 1450px; height: 500px"
      :src="url"
      :fit="fit"></el-image>
    </div>
  </div>
</template>
  
  <script>
  export default {
    data() {
      return {
        fits: 'contain',
        url:require('../assets/shouye1.jpg')
      }
    }
  }
</script>
