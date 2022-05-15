<template>
    <div id="main">
        <el-skeleton :rows="17" animated v-show="good.id<0"/>
        <div id="title" v-show="good.id!=-1">
            <h1>{{good.title}}</h1>
            <p>简介：{{good.introduction}}</p>
            <div @click="toDesigner(good.designer.id)" id="designer">主设计师：{{good.designer.title}}</div>
            <div>参考价：￥{{good.price}}</div>
        </div>
        
        <el-card class="box-card">
            <div v-html="good.content"></div>
        </el-card>
        
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'GoodDetails',
    data(){
        return{
            good:{id:-1,title:'',introduction:'',designer:{title:'',id:-1},content:''},
        }
    },
    mounted(){
        axios.get("/good/get?id="+sessionStorage.getItem("goodId")).then(res=>{
            this.good=res.data
        })
    },
    methods:{
        toDesigner(id){
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
    #main{
        margin-left: 15%;
        margin-right:15%;
        min-height:calc(100vh - 331px);
    }
    #title{
        text-align: center;
        margin-bottom: 100px;
    }
    #designer{
        font-size: 20px;
        cursor:pointer;
    }
</style>