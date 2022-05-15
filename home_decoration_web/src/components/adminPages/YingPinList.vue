<template>
  <div class="main">
      <div v-for="item in yingPins" :key="item.id">
        <el-descriptions style="margin-left:20px" >
            <el-descriptions-item label="姓名">{{item.name}}</el-descriptions-item>
            <el-descriptions-item label="性别">{{item.sex==1?'男':'女'}}</el-descriptions-item>
            <el-descriptions-item label="出生年份">{{item.birthYear}}</el-descriptions-item>
            <el-descriptions-item label="文化程度">
                    <el-tag size="big">{{item.education | educationFilter}}</el-tag>
                </el-descriptions-item>
            <el-descriptions-item label="毕业院校">{{item.school}}</el-descriptions-item>
            <el-descriptions-item label="联系方式">{{item.phone}}</el-descriptions-item>
            <el-descriptions-item label="技能">{{item.skill}}</el-descriptions-item>
            
        </el-descriptions>
        <el-divider></el-divider>
      </div>
    <div style="margin-top: 100px;margin-buttom:200px">
        <el-button v-show="pageNumber>2&&currentPage!=1" @click="upload(1)">第一页</el-button>
        <el-button v-for="index in pageNumber" :key="index" @click="upload(index)" plain>{{index}}</el-button>
        <el-button v-show="pageNumber>2&&currentPage!=pageNumber" @click="upload(pageNumber)">最后一页</el-button>
    </div>
  </div>
</template>

<script>
import axios from "axios"
export default {
    name:'YingPinList',
    data(){
        return{
            pageNumber:0,
            yingPins:[],
            currentPage:-1,
        }
    },
    mounted(){
        this.upload(1)
    },
    methods:{
        upload(num){
            axios.get("/admin/yingPin/page").then(res=>{
                this.pageNumber=res.data
            })

            axios.get("/admin/yingPin?num="+num).then(res=>{
                this.yingPins=res.data
                this.currentPage=num
            })
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
        sexFilter(value){
            return value==1?'男':'女'
        }
    }

}
</script>

<style scoped>
    .main{
        margin-left: 15%;
        margin-right:15%;
        margin-top: 100px;
        text-align: center;
    }
</style>