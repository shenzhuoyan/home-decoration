<template>
    <div>
        <el-descriptions :title="'岗位名称: '+item.title" v-for="item, index in zhaoPins" :key="index" style="border: grey solid 1px; background-color:#d3dce6;margin-bottom:20px" :content-style="contentStyle" :label-style="{fontSize:20}">
            <el-descriptions-item label="工作地点">{{item.address}}</el-descriptions-item>
            <el-descriptions-item label="学历要求">
                <el-tag size="big">{{item.education | educationFilter}}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="人数">{{item.num}}人</el-descriptions-item>
            <el-descriptions-item label="岗位描述">{{item.describe}}</el-descriptions-item>
            <el-descriptions-item label="岗位要求">{{item.text}}</el-descriptions-item>
            <el-descriptions-item label="操作">
                <el-button :type="item.status==0?'primary':'danger'" size="small" @click="re(item.id,item.status)">{{item.status | but}}</el-button>
                <el-button plain @click="showCeLan(item.id,item.title)">应聘列表</el-button>    
            </el-descriptions-item>
        </el-descriptions>
        
        <div style="margin-top: 20px;text-align:center;">
            <el-button v-show="pageNumber>2&&currentPage!=1" @click="upload(1)">第一页</el-button>
            <el-button v-for="index in pageNumber" :key="index" @click="upload(index)" plain>{{index}}</el-button>
            <el-button v-show="pageNumber>2&&currentPage!=pageNumber" @click="upload(pageNumber)">最后一页</el-button>
        </div>

        <ce-lan :title="title" :yingPins="yingPins"></ce-lan>
    </div>
</template>

<script>
import CeLan from './CeLan.vue'
import axios from 'axios'
export default {
  components: { CeLan },

    name:'ZhaoPinTable',
    props:['pageNumber','zhaoPins','upload','re'],
    data(){
        return{
            currentPage:-1,
            title:'',
            yingPins:[],
            show:false,
            contentStyle:{
                fontSize: 20,
                color: 'black'
            },
        }
    },
    methods:{
        showCeLan(id,title){
            axios.get('/admin/yingPin/get?zhaoId='+id).then(res=>{
                this.yingPins=res.data
            })
            this.title=title
            this.$bus.$emit('showCeLan')
        }
    },
    filters:{
        educationFilter(value){
            switch(value) {
                case 0:
                    return '不限'
                case 1:
                    return '高中'
                case 2:
                    return '大专'
                case 3:
                    return '本科'
                case 4:
                    return '硕士'
                case 5:
                    return '博士'
                default:
                    return '不限'
            } 
        },
        but(value){
            return value==0?'重新发布':'关闭招聘'
        }
    },
}
</script>

<style>

</style>