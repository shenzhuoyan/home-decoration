import VueRouter from 'vue-router'

import IndexPage from '../components/indexPages/IndexPage'
import FirstPage from '../components/indexPages/FirstPage'
import AnLi from '../components/indexPages/AnLi'
import GongLue from '../components/indexPages/GongLue'
import GoodDesigner from '../components/indexPages/GoodDesigner'
import ConnectUs from '../components/indexPages/ConnectUs'
import AdminIndex from '../components/adminPages/AdminIndex'
import GoodMall from '../components/indexPages/GoodMall'
import ZhaoPin from '../components/indexPages/ZhaoPin'
import IntroduceMe from '../components/indexPages/IntroduceMe'
import AdminIntroduce from '../components/adminPages/AdminIntroduce'
import AdminHome from '../components/adminPages/AdminHome'
import TextManage from '../components/adminPages/zujian/TextManage'
import AdminZhaoPin from '../components/adminPages/AdminZhaoPin'
import YingPinList from '../components/adminPages/YingPinList'
import NoticePage from '../components/indexPages/NoticePage'
import NoticeManage from '../components/adminPages/NoticeManage'
import AdminDesigner from '../components/adminPages/AdminDesigner'
import GoodDetails from '../components/indexPages/GoodDetails'
import GoodManage from '../components/adminPages/GoodManage'
import AnLiDetail from '../components/indexPages/AnLiDetail'
import AnLiManage from '../components/adminPages/AnLiManage'
import BannerManage from '../components/adminPages/BannerManage'
import NoFound from '../components/NoFound'
import LoginPage from '../components/LoginPage'




const router= new VueRouter({
    mode: "history",
    routes:[
        {
            path:'/',
            component: IndexPage,
            meta:{
                title: 'Z家装-官网'
            },
            children:[
                {  
                    name:'index',
                   path: '', //不用加斜杠
                   component: FirstPage,
                   meta:{
                        title: 'Z家装-官网'
                    },
                },
                {
                    name:'anli',
                    path:'anli',
                    component: AnLi,
                    meta:{
                        title: 'Z家装-案例'
                    },
                },
                {
                    name:'gonglue',
                    path:'gonglue',
                    component: GongLue,
                    meta:{
                        title: 'Z家装-通知'
                    },
                },
                {
                    name: 'designer',
                    path:'designer',
                    component: GoodDesigner,
                    meta:{
                        title: 'Z家装-设计师'
                    },
                },
                {
                    name:'connectus',
                    path:'connectus',
                    component:ConnectUs,
                    meta:{
                        title: 'Z家装-联系我们'
                    },
                },
                {
                    name:'goodmall',
                    path:'goodmall',
                    component: GoodMall,
                    meta:{
                        title:'Z家装-产品'
                    }
                },
                {
                    name:'zhaopin',
                    path:'zhaopin',
                    component: ZhaoPin,
                    meta:{
                        title:'Z家装-招聘信息'
                    }
                },
                {
                    name:'introduce',
                    path:'introduce',
                    component: IntroduceMe,
                    meta:{
                        title:'Z家装-公司介绍'
                    }
                },
                {
                    name:'notice',
                    path:'notice',
                    component:NoticePage,
                    //props:true,
                    meta:{
                        title:'Z家装-详情'
                    }
                },
                {
                    name:'gooddetail',
                    path:'gooddetail',
                    component:GoodDetails,
                    meta:{
                        title:'Z家装-产品详情'
                    }
                },
                {
                    name:'anlidetail',
                    path:'anlidetail',
                    component:AnLiDetail,
                    meta:{
                        title:'Z家装-案例详情'
                    }
                },
               
                
             ]
        },
        {
            path:'/admin',
            component:AdminIndex,
            meta:{
                title: '后台管理',
                meta:{
                    title:'家装公司-后台管理',
                    requireAuth:true //此时表示进入这个路由是需要登录的
                }
            },
            children:[
                {
                    name:'adminhome',
                    path:'',
                    component:AdminHome,
                    meta:{
                        title:'家装公司-后台管理',
                        requireAuth:true //此时表示进入这个路由是需要登录的
                    }
                },
                {
                    name:'adminintroduce',
                    path:'introduce',
                    component: AdminIntroduce,
                    meta:{
                        title:'后台管理-公司介绍',
                        requireAuth:true //此时表示进入这个路由是需要登录的
                    }
                },
                {
                    name:'textmanage',
                    path:'textmanage',
                    component: TextManage,
                    meta:{
                        title:'后台管理-信息管理',
                        requireAuth:true //此时表示进入这个路由是需要登录的
                    }
                },
                {
                    name:'adminzhaopin',
                    path:'zhaopin',
                    component:AdminZhaoPin ,
                    meta:{
                        title:'后台管理-招聘管理',
                        requireAuth:true //此时表示进入这个路由是需要登录的
                    }
                },
                {
                    name:'yingpin',
                    path:'yingpin',
                    component:YingPinList ,
                    meta:{
                        title:'后台管理-应聘列表',
                        requireAuth:true //此时表示进入这个路由是需要登录的
                    }
                },
                {
                    name:'noticemanage',
                    path:'noticemanage',
                    component:NoticeManage,
                    meta:{
                        title:'后台管理-通知管理',
                        requireAuth:true //此时表示进入这个路由是需要登录的
                    }
                },
                {
                    name:'designermanage',
                    path:'designermanage',
                    component:AdminDesigner,
                    meta:{
                        title:'后台管理-设计师管理',
                        requireAuth:true //此时表示进入这个路由是需要登录的
                    }
                },
                {
                    name:'goodmanage',
                    path:'goodmanage',
                    component:GoodManage,
                    meta:{
                        title:'后台管理-产品管理',
                        requireAuth:true //此时表示进入这个路由是需要登录的
                    }
                },
                {
                    name:'anlimanage',
                    path:'anlimanage',
                    component:AnLiManage,
                    meta:{
                        title:'后台管理-案例管理',
                        requireAuth:true //此时表示进入这个路由是需要登录的
                    }
                },
                {
                    name:'bannermanage',
                    path:'bannermanage',
                    component:BannerManage,
                    meta:{
                        title:'后台管理-轮播图管理',
                        requireAuth:true //此时表示进入这个路由是需要登录的
                    }
                }
            ]
        },
        {
            path:'/404',
            name:'404',
            component: NoFound,
            meta:{
                title: 'Z家装-404'
            },
        },
        {
            path:'*',
            redirect:'/404'
        },
        {
            path:'/login',
            name:'login',
            component: LoginPage,
            meta:{
                title:'管理员登录'
            }
        }
    ]
})

//登录拦截
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {  // 判断该路由是否需要登录权限
        //如果需要就执行下面的代码
        
        var isLogin=sessionStorage.getItem("isLogin")
        console.log(isLogin)
        // 通过sessionStorage 获取当前的isLogin的值 将我们保存的isLogin的值赋给num,num是顺便取的名称，可以换
        //我们在登录界面，我们使用请求，请求成功后，我们就使用sessionStorage为‘isLogin’保存一个值  1，如果请求失败，就不保存‘isLogin’的值
        //如果请求成功，num的值就是1，请求失败就是null，所以下面进行判断
        if (isLogin) {  
            //如果登录了，就跳转到'/index'路径
            //next({path:'/admin'});
            next();
        }
		else {
            next({
                path: '/login',//返回登录界面
                // query: {redirect: to.fullPath}  
            })
        }
    }
    else {
        //如果不需要登录权限就直接跳转到该路由
        next();
    }
})
export default router