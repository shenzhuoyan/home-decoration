<template>
    <div id="main">
        <el-skeleton :rows="17" animated v-show="notice.id<0"/>
        <div id="title" v-show="notice.id!=-1">
            <h1>{{notice.title}}</h1>
            <p id="date">发布时间：{{notice.date}}</p>
            
        </div>
        <div v-html="notice.content"></div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'NoticePage',
    data(){
        return{
            notice:{id:-1},
        }
    },
    mounted(){
        axios.get(sessionStorage.getItem('url')).then(res=>{
            this.notice=res.data
        })
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
    }

    #date{
        color: grey;
    }
</style>