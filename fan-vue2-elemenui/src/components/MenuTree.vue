<template>
  <div>
    <div v-for="item in menuList" :key="item.id">
      <!-- 分为两种方式渲染：有子菜单和没有子菜单-->
      <el-submenu v-if="item.children != null" :index="item.id">
        <template slot="title">
          <i :class="item.icon"></i>
          {{ item.name }}
        </template>
        <!-- 有子菜单的继续遍历（递归）-->
        <menu-tree :menuList="item.children" :click-menu="clickMenu"></menu-tree>
      </el-submenu>
      <!-- 没有子菜单-->
      <el-menu-item v-else @click="clickMenu(item)" :index="item.id">
        <template slot="title">
          <i :class="item.icon"></i>
          {{ item.name }}
        </template>
      </el-menu-item>
    </div>
  </div>
</template>
<script>
import MenuTree from "@/components/MenuTree.vue";

export default {
  name: 'menuTree',
  components: {
    MenuTree
  },
  props: {
    menuList: {
      type: Array,
    },
    clickMenu: Function
  },
};
</script>
