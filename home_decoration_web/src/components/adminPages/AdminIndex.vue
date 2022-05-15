<template>
  <div>
      <el-container style="height: 100vh">
        <el-header>
          <div class="title">
            <img src="/img/admin_logo.png" class="logo"/>
          </div>
        </el-header>
        <el-container style="height:calc(100% - 250px)">
          <el-aside width="300px">
            <el-menu
              default-active="0"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b"
              style="width:100%"
              >
              <el-menu-item index="0" @click="to('adminhome')">
                <i class="el-icon-house" style="font-size:28px"></i>
                <span slot="title">欢迎{{adminName}}</span>
              </el-menu-item>
              <el-submenu index="1">
                <template slot="title">
                  <i class="el-icon-mic" style="font-size:28px" ></i>
                  <span>首页管理</span>
                </template>
                  <el-menu-item index="1-1" @click="to('adminintroduce')">公司简介</el-menu-item>
                  <el-menu-item index="1-2" @click="toTextManage('hotLine')">咨询热线</el-menu-item>
                  <el-menu-item index="1-4" @click="toTextManage('beiAnNumber')">备案号</el-menu-item>
                  <el-menu-item index="1-5" @click="toTextManage('address')">总部地址</el-menu-item>
                  <el-menu-item index="1-6" @click="to('bannermanage')">轮播图管理</el-menu-item>
              </el-submenu>
              <el-submenu index="2">
                <template slot="title">
                  <i class="el-icon-message" style="font-size:28px"></i>
                  <span>求职管理</span>
                </template>
                  <el-menu-item index="2-1" @click="to('adminzhaopin')">招聘管理</el-menu-item>
                  <el-menu-item index="2-2" @click="to('yingpin')">应聘列表</el-menu-item>
              </el-submenu>
              <el-menu-item index="3" @click="to('anlimanage')">
                <i class="el-icon-star-off" style="font-size:28px"></i>
                <span slot="title">案例管理</span>
              </el-menu-item>
              <el-menu-item index="4" @click="to('goodmanage')">
                <i class="el-icon-goods" style="font-size:28px"></i>
                <span slot="title">产品管理</span>
              </el-menu-item>
             
              <el-menu-item index="5" @click="to('noticemanage')">
                <i class="el-icon-bell" style="font-size:28px"></i>
                <span slot="title">通知管理</span>
              </el-menu-item>
              <el-menu-item index="6" @click="to('designermanage')">
                <i class="el-icon-s-custom" style="font-size:28px"></i>
                <span slot="title">首页设计师</span>
              </el-menu-item>

              <el-menu-item index="7" @click="exit()">
                <i class="el-icon-circle-close" style="font-size:28px"></i>
                <span slot="title">退出</span>
              </el-menu-item>
            </el-menu>
          </el-aside>
          <el-container>
            <el-main style="background-color:white;">
              <router-view></router-view>
            </el-main>
          </el-container>
        </el-container>
        <el-footer>@2022 毕业设计 Z家装后台管理系统</el-footer>
      </el-container>
  </div>
</template>

<script>
export default {
  name:'AdminIndex',
  data(){
    return {
      adminName:'管理员',
    }
  },
  methods:{
    handleOpen() {
        //console.log(key, keyPath);
      },
      handleClose() {
        //console.log(key, keyPath);
      },
      to(name){
        this.$router.push({
          name
        }).catch(err => {err})
      },
      toTextManage(value){
        this.$router.push({
          name:'textmanage',
        }).catch(err=>{err})
        this.$nextTick(() => {this.$bus.$emit('update',value)
      });
        
      },
      exit(){
        sessionStorage.removeItem("isLogin")
        sessionStorage.removeItem("token")
        sessionStorage.removeItem("alerted")
        this.$router.push({
          path:'/'
        }).catch(err => {err})
      }
  }
}
</script>

<style scoped>
  .title{
    width:100%;
    height:250px;
    
  }

  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: white;
    text-align: center;
    line-height: 60px;
    background-color:#363636;
  }

  .el-aside {
    color: #333;
    line-height: 200px;
    background-color:#545c64; 
    overflow-x: hidden;
  }
  
  /*.el-main {
    background-color: #E9EEF3;
  }
  */
  body > .el-container {
    margin-bottom: 40px;
  }
  
  span{
    font-size: 25px;
  }
 
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

</style>