<template>
  <div id="main">
    <el-backtop :bottom="60">
      <div
        style="
           {
            height: 100%;
            width: 100%;
            background-color: #f2f5f6;
            box-shadow: 0 0 6px rgba(0, 0, 0, 0.12);
            text-align: center;
            line-height: 40px;
            color: #1989fa;
          }
        "
      >
        <i class="el-icon-top"></i>
      </div>
    </el-backtop>
    <div class="top-bar">
      <div id="top-bar-left">{{ dateFormat() }} 欢迎!</div>
      <div id="top-bar-right">
        <a href="">咨询热线:{{ hotPhone }} </a>
        <el-button type="primary" size="mini" @click="handleSelect('login')">后台管理</el-button>
      </div>
    </div>
    <div>
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#F8F8FF"
        text-color="black"
        active-text-color="black"
        style="padding-left: 20%"
      >
        <el-menu-item><img src="/img/logo.png" sty/></el-menu-item>
        <el-menu-item index="index">首页</el-menu-item>
        <el-menu-item index="introduce">公司简介</el-menu-item>
        <el-menu-item index="anli">家装案例</el-menu-item>
        <el-menu-item index="zhaopin">招聘信息</el-menu-item>
        <el-menu-item index="goodmall">产品信息</el-menu-item>
        <el-menu-item index="designer">设计师</el-menu-item>
        <el-menu-item index="gonglue">通知</el-menu-item>
        <el-menu-item index="connectus">联系我们</el-menu-item>
        <el-menu-item
          ><i class="el-icon-phone" style="font-size: 30px"></i
          ><span style="font-size: 25px" v-cloak>{{ hotPhone }}</span></el-menu-item
        >
      </el-menu>

      <el-main><router-view></router-view></el-main>
      <div class="footer">
        <div class="footer-left">
          <h2>关于我们</h2>
          <p><a href="/">联系我们</a></p>
          <p><a href="/">公司简介</a></p>
        </div>
        <div class="footer-center">
          <h2>在线商城</h2>
          <p><a href="/">淘宝旗舰店</a></p>
          <p><a href="/">京东旗舰店</a></p>
        </div>
        <div class="footer-right" v-cloak>
          <h2>联系我们</h2>
          <p>咨询热线：{{ hotPhone }}</p>
          <p>备案号：{{ beiAnHao }}</p>
          <p>总部地址：{{ address }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "IndexPage",
  data() {
    return {
      activeIndex: "1",
      //以下为后端数据
      date: "日期",
      hotPhone: "",
      beiAnHao: "",
      address: "",
    };
  },
  mounted(){
    axios.get('/hotLine').then(res=>{
      this.hotPhone=res.data
    })
    axios.get('/beiAnNumber').then(res=>{
      this.beiAnHao=res.data
    })
    axios.get('/address').then(res=>{
      this.address=res.data
    })
  },
  methods: {
    handleSelect(key) {
      this.$router.push({
          name:key,
        }).catch(err => {err})
      
    },
    dateFormat() {
      var date = new Date();
      var year = date.getFullYear();
      var month =
        date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1;
      var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      // var hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
      // var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
      // var seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
      return year + "年" + month + "月" + day + "日";
    },
  },
};
</script>

<style scoped>

.footer {
  background-color: #363636;
  color: white;
  /*text-align: center;*/
  height: 200px;
}
a {
  text-decoration: none;
}
.footer > div {
  float: left;
  height: 190px;
  margin-left: 20%;
}

.footer > div > p > a {
  color: grey;
}

.footer > div > p {
  color: grey;
}

.el-main {
  /*background-color: #e9eef3;*/
  color: #333;
}

.top-bar {
  height: 30px;
  color: white;
  background-color: #363636;
}

#top-bar-left {
  float: left;
  font-size: 18px;
  margin-left: 10%;
}

#top-bar-right {
  float: right;
  margin-right: 10%;
}

#top-bar-right > a {
  margin-right: 10px;
  color: white;
}
</style>