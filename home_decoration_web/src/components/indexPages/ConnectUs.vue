<template>
  <div class="footer">
      <img src="/img/logo.png" class="logo"/>
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
        <div class="footer-right">
          <h2>联系我们</h2>
          <p>咨询热线：{{hotPhone}}</p>
          <p>备案号：{{beiAnHao}}</p>
          <p>总部地址：{{address}}</p>
        </div>
      </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'ConnectUs',
    data(){
        return {
            //这几个从vuex取
            hotPhone:'1234-567-890',
            beiAnHao:'12345',
            address:'xxxx'
        }
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
    }
}
</script>

<style scoped>
.footer {
    background-color: #E9EEF3;
    color: black;
    text-align: center;
    padding-top: 200px;
    min-height:calc(100vh - 531px);
  }
  a{
      text-decoration: none
  }
  .footer > div{
    float: left;
    height: 190px;
    margin-left: 200px;
  }
    .logo{
        width:200px;
        float: left;
        margin-left:300px;
    }


  .footer > div > p > a{
    color: grey;
  }

  .footer > div > p{
    color: grey;
  }
 
</style>