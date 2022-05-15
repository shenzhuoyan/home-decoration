<template>
  <div class="main">
      <el-row>
  <el-col :span="8" v-for="item in goodList" :key="item.id">
    <el-card :body-style="{ padding: '0px' }" shadow="hover" style="width:400px;margin-bottom:40px;cursor: pointer;" >
      <img :src="item.picture" class="image" @click="look(item.id)">
      <div style="padding: 14px;" @click="look(item.id)">
        <h2>{{item.title}}</h2>
        <div class="bottom clearfix">
          <span>{{item.introduction}}</span>
        </div>
      </div>
    </el-card>
  </el-col>
</el-row>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name:'GoodMall',
  data(){
    return{
      goodList:[]
    }
  },
  mounted(){
    axios.get("/good/all").then(res=>{
      this.goodList=res.data
    })

  },
  methods:{
    look(id){
      sessionStorage.setItem("goodId",id)
      const routeData = this.$router.resolve({
          name:'gooddetail'
        })
      window.open(routeData.href, '_blank')
    }
  }
}
</script>

<style scoped>
a{
    text-decoration: none
}
.main{
    margin-left:15%;
    margin-right:15%;
    margin-top: 50px;
    height: 650px;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }


  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
</style>