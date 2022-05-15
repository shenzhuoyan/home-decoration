<template>
  <div class="main">
    <span class="firstTitle">通知公告/</span><span class="secondTitle">最新的通知，确保交流的畅通</span>
    <el-divider content-position="right"></el-divider>
    <div v-for="item in notice" :key="item.id" @click="handleCurrentChange(item.id)" style="cursor: pointer; height:40px;border-bottom:#E8E8E8 solid 1px;margin-bottom:20px">
      <div style="width:40%;float:left;font-size:18px;margin-left:50px" >{{item.title}}</div>
      <div style="width:30%;float:left;"><el-tag :type="item.status|statusTag">{{item.status | statusFilter}}</el-tag></div>
      <div style="width:20%;float:left;font-size:18px;color:grey;">{{item.date}}</div>
    </div>
    <div style="margin-top: 20px;text-align:center;">
      <el-button v-show="pageNumber>2&&currentPage!=1" @click="upload(1)">第一页</el-button>
      <el-button v-for="index in pageNumber" :key="index" @click="upload(index)" plain>{{index}}</el-button>
      <el-button v-show="pageNumber>2&&currentPage!=pageNumber" @click="upload(pageNumber)">最后一页</el-button>
    </div>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name:'GongLue',
  data(){
    return {
      notice:[
            {id:-1,date:'', title:''},
          ],
        pageNumber:0,
        currentPage:1,
    }
  },
  mounted(){
     this.upload(1)
  },
  methods:{
    upload(num){
      axios.get("/noticeList/page").then(res=>{
        this.pageNumber=res.data
      })
      axios.get("/noticeList?page="+num).then(res=>{
        this.notice=res.data
        this.currentPage=num
      })
    },
    handleCurrentChange(id){
       const routeData = this.$router.resolve({
          name:'notice'
        })
        sessionStorage.setItem("url",'/noticeList/get?id='+id)
        window.open(routeData.href, '_blank')
    }
  } ,
  filters:{
    statusFilter(value){
      switch(value){
        case 0: return '已删除'
        case 1: return '通知'
        case 2: return '重要'
        case 3: return '置顶'
        default: return '未知'
      }
    },
    statusTag(value){
      switch(value){
        case 0: return 'danger'
        case 1: return 'success'
        case 2: return 'info'
        case 3: return 'warning'
        default: return 'danger'
      }
    }
  }  
}
</script>

<style scoped>
  .main{
    min-height:calc(100vh - 381px);
    margin-left:15%;
    margin-right:15%;
    margin-top: 50px;
  }
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
 
</style>