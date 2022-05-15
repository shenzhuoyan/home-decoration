<template>
  <div class="block">
    <span class="demonstration"></span>
    <el-carousel height="560px" >
      <el-carousel-item v-for="(item,index) in bannerPictures" :key="index">
        <img :src="item.content" />
      </el-carousel-item>
    </el-carousel>
    <AnLi :num="6"><router-link to="/anli">更多案例</router-link></AnLi>
    <div id="notice">
      <span class="firstTitle">通知公告/</span><span class="secondTitle">最新的通知，确保交流的畅通</span>
      <el-divider content-position="right"><router-link to="/gonglue">更多通知</router-link></el-divider>
      <div class="notice-table">
        <h4>最新通知</h4>
        <div v-for="item in notice" :key="item.id" @click="handleCurrentChange(item.id)" style="cursor: pointer; height:30px;border-bottom:#E8E8E8 solid 1px;margin-bottom:20px">
          <div style="width:80%;float:left;font-size:18px" >{{item.title}}</div>
          <div style="width:20%;float:left;font-size:18px;color:grey;">{{item.date}}</div>
        </div>
      </div>
      <div class="fenge"></div>
      <div class="notice-table">
        <h4>重要通知</h4>
          <div v-for="item in allNotice" v-show="allNotice.length>0" :key="item.id" @click="handleCurrentChange(item.id)" style="cursor: pointer; height:30px;border-bottom:#E8E8E8 solid 1px;margin-bottom:20px;">
            <div style="width:80%;float:left;font-size:18px" >{{item.title}}</div>
            <div style="width:20%;float:left;font-size:18px;color:grey;">{{item.date}}</div>
          </div>
            <el-empty description="暂无重要通知" v-show="allNotice.length<=0"></el-empty>
      </div>
    </div>
    <GoodDesigner></GoodDesigner>
  </div>
</template>

<script>
import axios from 'axios'
import AnLi from "../indexPages/AnLi"
import GoodDesigner from '../indexPages/GoodDesigner'
export default {
  name: 'FirstPage',
  data(){
    return {

      notice:[
              {id:1,date:'2022-04-22', title:'啊啊巴巴爸爸吧阿爸阿爸爸爸阿'},
              {id:2,date:'2022-04-23', title:'啊啊巴巴爸爸吧阿爸阿爸爸爸阿巴阿巴阿巴'},
              {id:3,date:'2022-04-22', title:'啊啊巴巴爸爸吧爸爸阿巴阿巴阿巴'},
              {id:4,date:'2022-04-21', title:'啊啊巴 巴爸爸吧阿爸阿爸爸爸阿巴阿巴阿巴'},
              {id:5,date:'2022-04-21', title:'啊啊巴 巴爸爸吧阿爸阿爸爸爸阿巴阿巴阿巴'},
            ],
      //以下是后端数据
      bannerPictures:['/img/banner2.jpg','/img/banner3.jpg','/img/banner4.jpg'],
      anliPictures:['/img/anli/1.png','/img/anli/2.jpg','/img/anli/3.jpg','/img/anli/4.jpg','/img/anli/5.jpg'],
      allNotice:[],
    }
  },
  components:{AnLi, GoodDesigner,},
  mounted(){
    axios.get("/noticeList/newest").then(res=>{
      this.notice=res.data
    })
    axios.get("/noticeList/important").then(res=>{
      this.allNotice=res.data
    })

    axios.get("/banner").then(res=>{
      this.bannerPictures=res.data
    })
  },
  methods:{
   handleCurrentChange(id){
       const routeData = this.$router.resolve({
          name:'notice'
        })
        sessionStorage.setItem("url", '/noticeList/get?id='+id)
        window.open(routeData.href, '_blank')
    }
  }
}
</script>

<style scoped>

.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
     background-color: #99a9bf;
  }
  
   .firstTitle{
    font-size: 30px;
  }

  .secondTitle{
    font-size: 15px;
    color:grey;
  }
  .el-carousel__item:nth-child(2n+1) {
     background-color: #d3dce6;
  }

  #notice{
    min-height:calc(100vh - 381px);
    margin-left:15%;
    margin-right:15%;
    margin-top: 50px;
  }

.notice-table{
  width:49%;
  float:left;
  height:360px
}
.fenge{
  float:left;
  width:1px;
  height:450px;
  background-color: grey;
}
</style>