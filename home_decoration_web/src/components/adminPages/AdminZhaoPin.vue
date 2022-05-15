<template>
    <div class="main">
            <el-tabs type="border-card" @tab-click="switchPage">
                <el-tab-pane label="正在公布">
                    <zhao-pin-table :pageNumber="pageNumber" :re="re" :upload="upload" :zhaoPins="zhaoPins"></zhao-pin-table>
                </el-tab-pane>

                <el-tab-pane label="已删除">
                    <el-descriptions :title="'岗位名称: '+item.title" v-for="item, index in delZhaoPins" :key="index" style="border: grey solid 1px; background-color:#d3dce6;margin-bottom:20px" :content-style="contentStyle" :label-style="{fontSize:20}">
                        <el-descriptions-item label="工作地点">{{item.address}}</el-descriptions-item>
                        <el-descriptions-item label="学历要求">
                            <el-tag size="big">{{item.education | educationFilter}}</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item label="人数">{{item.num}}人</el-descriptions-item>
                        <el-descriptions-item label="岗位描述">{{item.describe}}</el-descriptions-item>
                        <el-descriptions-item label="岗位要求">{{item.text}}</el-descriptions-item>
                        <el-descriptions-item label="操作"><el-button :type="item.status==0?'primary':'danger'" size="small" @click="re(item.id,item.status)">{{item.status | but}}</el-button></el-descriptions-item>
                    </el-descriptions>
                    <div style="margin-top: 20px;text-align:center;">
                        <el-button v-show="delNumber>2&&delCurrentPage!=1" @click="getDelPage(1)">第一页</el-button>
                        <el-button v-for="index in delNumber" :key="index" @click="getDelPage(index)" plain>{{index}}</el-button>
                        <el-button v-show="delNumber>2&&delCurrentPage!=delNumber" @click="getDelPage(delNumber)">最后一页</el-button>
                    </div>
                </el-tab-pane>

                <el-tab-pane label="发布招聘">
                    <el-descriptions title="发布招聘信息"  style="border: grey solid 1px; background-color:#d3dce6" :content-style="contentStyle" :label-style="{fontSize:20}">
                    <el-descriptions-item label="岗位名称">
                        <el-input v-model="zhaoPin.title" placeholder="请输入内容"></el-input></el-descriptions-item>
                        <el-descriptions-item label="工作地点"><el-input v-model="zhaoPin.address" placeholder="请输入内容"></el-input></el-descriptions-item>
                        <el-descriptions-item label="学历要求">
                            <el-select v-model="zhaoPin.education" placeholder="请选择">
                                <el-option
                                v-for="item,index in edus"
                                :key="index"
                                :label="item.label"
                                :value="item.value">
                                </el-option>
                            </el-select>
                        </el-descriptions-item>
                        <el-descriptions-item label="人数"><el-input v-model="zhaoPin.num" placeholder="请输入内容"></el-input></el-descriptions-item>
                        <el-descriptions-item label="岗位描述"><el-input type="textarea" autosize v-model="zhaoPin.describe" placeholder="请输入内容"></el-input></el-descriptions-item>
                        <el-descriptions-item label="岗位要求"><el-input type="textarea" autosize v-model="zhaoPin.text" placeholder="请输入内容"></el-input></el-descriptions-item>
                    </el-descriptions>
                    <div style="margin-top: 40px;width:100%;text-align:center;"><el-button type="primary" @click="submit">发布</el-button></div>
                </el-tab-pane>
            </el-tabs>
    </div>
</template>

<script>
import axios from 'axios'
import ZhaoPinTable from './zujian/ZhaoPinTable.vue';
export default {
  components: { ZhaoPinTable },
    name:'AdminZhaoPin',
    data(){
        return{
            id:-1,
            contentStyle:{
                fontSize: 20,
                color: 'black'
            },
            eduValue:0,
            edus:[
                {label:'高中以下',value:0},
                {label:'高中',value:1},
                {label:'大专',value:2},
                {label:'本科',value:3},
                {label:'硕士',value:4},
                {label:'博士',value:5},
            ],

            zhaoPin:{
                title:'',
                describe:'',
                address:'',
                education:0,
                num:'',
                text:''
            },
            delZhaoPins:[],
            zhaoPins:[],
            pageNumber:0,
            delNumber:0,
            currentPage:1,
            delCurrentPage:1,

        }
    },
    mounted(){
        this.upload(1);
    },
    methods:{
        
        upload(num){
            axios.get('/zhaoPin/page').then(res=>{
                this.pageNumber=res.data
            })
            axios.get('/zhaoPin?num='+num).then(res=>{
                this.zhaoPins=res.data
                this.currentPage=num
            })
        },
        switchPage(tab){
            if(tab.index==0){
                this.upload(1)
            }else if(tab.index==1){
                this.getDelPage(1)
            }
        },
        re(id,status){
            if(status==0)//下架了就重新上架
            var type='re'
            else type='rm'
            axios.get('/admin/zhaoPin/'+type+'?id='+id).then(res=>{
                if(res.data){
                    this.$notify({
                        title: '成功',
                        message: '成功',
                        type: 'success'
                    })
                    this.upload(1)
                    if(status==0)
                        this.getDelPage(1)
                    else this.upload(1)
                }
                else this.$notify({
                    title: '警告',
                    message: '失败',
                    type: 'warning'
                })
            })
            
        },
        getDelPage(num){
            axios.get('/admin/zhaoPin/deletedPage').then(res=>{
                this.delNumber=res.data
            })
            axios.get('/admin/zhaoPin/deleted?num='+num).then(res=>{
                this.delZhaoPins=res.data
                this.delCurrentPage=num
            })
        },
        submit(){
            axios.post('/admin/zhaoPin/add', this.zhaoPin).then(
                res => {
                    if(res.data){
                        this.$notify({
                            title: '成功',
                            message: '发布成功',
                            type: 'success'
                        })
                        this.zhaoPin={
                            title:'',
                            describe:'',
                            address:'',
                            education:0,
                            num:'',
                            text:''
                        }
                        
                    }
                    else this.$notify({
                        title: '警告',
                        message: '发布失败',
                        type: 'warning'
                    })
                })
               
        },
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
    }
}
</script>

<style scoped>
a{
    text-decoration: none
}
.main{
    min-height: 650px;
  }

</style>