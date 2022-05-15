<template>
    <div id="main">
        <el-skeleton :rows="17" animated v-show="anLi.id<0"/>
        <div id="title" v-show="anLi.id!=-1">
            <h1>{{anLi.title}}</h1>
            <p>发布日期：{{anLi.date}}</p>
            <p>简介：{{anLi.introduction}}</p>
            <div @click="toDesigner(anLi.designer.id)" id="designer">主设计师：{{anLi.designer.title}}</div>
        </div>
        
        <el-card class="box-card">
            <div v-html="anLi.content"></div>
        </el-card>
        
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'AnLiDetail',
    data(){
        return{
            anLi:{id:-1,title:'',date:'',introduction:'',designer:{title:'',id:-1},content:''},
        }
    },
    mounted(){
        axios.get("/anLi/get?id="+sessionStorage.getItem("anLiId")).then(res=>{
            this.anLi=res.data
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