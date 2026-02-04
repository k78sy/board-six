import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: () => import('../views/BoardList.vue'),
    },
    {
      path: '/mod/:id',
      component: () => import('../views/BoardWriteMod.vue'),
    },
    {
      path: '/write',
      component: () => import('../views/BoardWriteMod.vue'),
    },
    {
      path: '/detail/:id',
      component: () => import('../views/BoardDetail.vue'),
    },
  ],
})

export default router
