import { createRouter, createWebHistory } from 'vue-router'
import TestCaseCreate from '../views/Testcases/CreateTestCase.vue'
import TestCaseView from '../views/Testcases/TestCaseList.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/testcases'
    },
    {
      path: '/testcases',
      name: 'testcases',
      component: TestCaseView
    },
    {
      path: '/testcases/create',
      name: 'testcasesCreate',
      component: TestCaseCreate
    },
    {
      path: '/ask',
      name: 'ask',
      component: () => import('../views/Ask.vue')
    }
  ]
})

export default router
