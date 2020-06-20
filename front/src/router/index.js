//路由

import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Common from '@/components/Common'
import ShowList from '@/components/ShowList'
import ShowCDCList from '@/components/ShowCDCList'

Vue.use(Router)

const router =  new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/common',
      name: 'Common',
      component: Common,
      meta:{
        requireAuth: true
      }
    },
    {
        path: '/showlist',
        name: 'ShowList',
        component: ShowList,
        meta:{
          requireAuth: true
        }
    },
    {
      path: '/showcdclist',
      name: 'ShowCDCList',
      component: ShowCDCList,
      meta:{
        requireAuth: true
      }
  },
  ]
})

//路由守卫
router.beforeEach((to, from, next) => {
  if(to.meta.requireAuth){
    if(to.name == "Common"){
      if(getCookie('common') == 'true')
        next();
      else
        next({name: 'Login'});

    }
    
    if(to.name == "ShowList"){
      if(getCookie('hospital') == 'true')
        next();
      else
        next({name: 'Login'});
      }

    if(to.name == "ShowCDCList"){
      if(getCookie('cdc') == 'true')
        next();
      else
        next({name: 'Login'});
    }
  }else{
    next();
  }
})

//获取cookie
function getCookie(cname) {
  var name = cname + "=";
  var cookie = document.cookie;

  var ca = cookie.split('; ');
  for(var i = 0; i <ca.length; i++) {
  var c = ca[i];
  
  if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
      }
  }
  return "";
} 

export default router;
