<template>
  <div id="main" >
      
    <div id="center-box">
        <el-card class="box-card" style="height:100%" shadow="hover">
            <p style="font-size:50px">Z家装</p>
            <table style="width:100%;margin-top:60px;margin-bottom:60px">
                <tr>
                    <td style="width:20%">
                        用户名:
                    </td>
                    <td style="width:80%">
                        <el-input v-model="name"></el-input>
                    </td>
                </tr>
                <tr style="height:30px">
                    <td>
                    </td>
                    <td>
                        
                    </td>
                </tr>
                <tr style="margin-top:20px">
                    <td style="width:20%">
                        密码:
                    </td>
                    <td style="width:80%">
                        <el-input v-model="password" show-password></el-input>
                    </td>
                </tr>
                
                
            </table>
                <el-button type="primary" @click="login">登录</el-button>
                
            
        </el-card>
    </div>

  </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'LoginPage',
    created(){
        if(sessionStorage.getItem("isLogin"))
        this.$router.push({
            path:'/admin',
            }).catch(err => {err})
    },
    data(){
        return {
           name:'',
           password:''
        }
    },
    methods:{
        login(){
            axios.get("/adminLogin?name="+this.name+"&password="+this.password).then(res=>{
                if(res.data){
                    sessionStorage.setItem("isLogin",true)
                    sessionStorage.setItem("token",res.data)
                    //axios.defaults.headers.common['token'] = res.data
                    this.$router.push({
                        path:'/admin',
                    }).catch(err => {err})
                }
                else
                this.$notify.error({
                title: '登录失败',
                message: '账号或密码错误'
                });
            }).catch(()=>{
                this.$notify.error({
                    title: '登录失败',
                    message: '网络异常'
                    });
            })
        }
    }
}
</script>

<style scoped>
    #main{
        height:100vh;
        width:100%;
        background:url('~@/assets/login.jpg');
    }
    #center-box{
        width:400px;
        height:500px;
        position: absolute;
        top: 50%;
        left: 50%;
        margin: -250px 0 0 -200px;
        text-align: center;
    }
</style>