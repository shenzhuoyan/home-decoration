<template>
  <div id="main" v-html="text">
      
  </div>
</template>

<script>
import axios from "axios"
export default {
    name:'IntroduceMe',
    data(){
        return{
            text:'呐，这里呢，就是介绍咯'
        }
    },
    mounted(){
        axios.get('/describe').then(
        res => {
            this.text=res.data
        })
    }
}
</script>

<style scoped>
    #main{
        margin-left: 15%;
        margin-right:15%;
        margin-top: 200px;
        margin-bottom: 200px;
        min-height:calc(100vh - 531px);
    }
    
</style>