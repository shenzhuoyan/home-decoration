<template>
  <div class="main">
      <div>
        <span class="firstTitle">专属设计大咖/</span><span class="secondTitle">选对设计师才能让家居大放异彩</span>
        <el-divider content-position="right"></el-divider>
        <el-row>
            <el-col :span="8" v-for="(item, index) in designers" :key="index" style="width:30%;margin-left:20px;cursor: pointer;">
                <el-card :body-style="{ padding: '0px' }" >
                  <img :src="item.picture" class="image" @click="open(item.id)">
                  <div style="padding: 14px;text-align: center">
                      <div style="font-size: 30px;" @click="open(item.id)">{{item.title}}</div>
                  </div>
                </el-card>
            </el-col>
        </el-row>
      </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'GoodDesigner',
    data(){
        return {
            //以下为后端内容
            designers:[]
        }
    },

    mounted(){
      axios.get("/designerIntroduce").then(res=>{
        this.designers=res.data
      })
    },
    methods:{
      open(id){
        const routeData = this.$router.resolve({
          name:'notice'
        })
        sessionStorage.setItem("url", '/designerIntroduce/get?id='+id)
        window.open(routeData.href, '_blank')
      }
    }
}
</script>

<style scoped>
a{
    text-decoration: none
}
  .firstTitle{
    font-size: 30px;
  }

  .secondTitle{
    font-size: 15px;
    color:grey;
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