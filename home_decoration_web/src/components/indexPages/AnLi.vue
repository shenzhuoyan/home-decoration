<template>
  <div class="main">
      <div>
        <span class="firstTitle">家装案例/</span><span class="secondTitle">精挑细选500例优秀案例</span>
        <el-divider content-position="right"><slot></slot></el-divider>
        <div>
          <div v-for="(item, index) in anLi" :key="index"  class="anliP" @click="look(item.id)">
            <img :src="item.picture"/>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'AnLi',
    data(){
        return {
            //以下为后端内容
            anLi:[{src:'/img/anli/1.png',id:0}]
        }
    },
    props:['num'],
    mounted(){
      if(this.num==6){
        axios.get('/anLi/six').then(res=>{
          this.anLi=res.data
        })
      }else{
      axios.get('/anLi/all').then(res=>{
        this.anLi=res.data
      })
      }
    },
    methods:{
      look(id){
        sessionStorage.setItem("anLiId",id)
        const routeData = this.$router.resolve({
            name:'anlidetail'
          })
        window.open(routeData.href, '_blank')
      },
    }
}
</script>

<style scoped>
.anliP{
  margin-top:20px;
  margin-right:10px;
  margin-left:10px;
   float: left;
   width:calc(33% - 20px);
   height: 250px;
   cursor:pointer;
 }

 .anliP > img {
   width:100%;
   height: 100%;
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
    .main{
    margin-left:15%;
    margin-right:15%;
    margin-top: 50px;
    height: 650px;
  }
</style>