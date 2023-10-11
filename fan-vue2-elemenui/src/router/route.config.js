/**
 * constantRouterMap
 * 没有权限要求的基本页
 * 所有角色都可以访问
 * 不需要动态判断权限的路由
 */
export const constantRouterMap = [
  {
    path: '/',
    name: 'Login',
    component: () => import('@/views/Login.vue'),
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('@/views/Home.vue'),
  },
];

// /**
//  * 动态菜单，走权限控制
//  *
//  * 可从后端获取
//  * 可分多个角色
//  * 这里分为二个角色 管理员admin  普通角色 visitor
//  */
// export const asyncRouterMap = [
//     {
//         path: "/onlyAdminCanSee",
//         name: "adminPage",
//         component: () => import('@/views/AdminPage'),
//         meta: {
//             title: "管理员页面",
//             keepalive: false,
//             roles: ["admin"],
//         },
//     },
//     {
//         path: "/onlyLoginUserCanSee",
//         name: "visitPage",
//         component: () => import('@/views/VisitorPage'),
//         meta: {
//             title: "用户界面",
//             keepalive: false,
//             roles: ["admin", "visitor"],
//         },
//     },
//     {path: '*', redirect: '/404', component: () => import('@/views/exception/404Page')}
// ];
