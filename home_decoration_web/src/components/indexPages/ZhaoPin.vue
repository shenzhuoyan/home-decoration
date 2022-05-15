<template>
    <div class="main">
        <div v-show="isList" style="text-align: center;">
            <el-descriptions :title="'岗位名称: '+item.title" v-for="item, index in zhaoPins" :key="index" style="border: grey solid 1px; background-color:#d3dce6;margin-bottom:20px" :content-style="contentStyle" :label-style="{fontSize:20}"> 
                <el-descriptions-item label="工作地点">{{item.address}}</el-descriptions-item>
                <el-descriptions-item label="学历要求">
                    <el-tag size="big">{{item.education | educationFilter}}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="人数">{{item.num}}人</el-descriptions-item>
                <el-descriptions-item label="岗位描述">{{item.describe}}</el-descriptions-item>
                <el-descriptions-item label="岗位要求">{{item.text}}</el-descriptions-item>
                <el-descriptions-item label="申请此职位"><el-button type="primary" size="small" @click="dengJi(item.id)">登记信息</el-button></el-descriptions-item>
            </el-descriptions>
            <div style="margin-top: 100px;margin-buttom:200px">
                <el-button v-show="pageNumber>2&&currentPage!=1" @click="upload(1)">第一页</el-button>
                <el-button v-for="index in pageNumber" :key="index" @click="upload(index)" plain>{{index}}</el-button>
                <el-button v-show="pageNumber>2&&currentPage!=pageNumber" @click="upload(pageNumber)">最后一页</el-button>
            </div>
        </div>
        <div v-show="!isList">
            <el-descriptions title="申请本岗位">
                <el-descriptions-item label="姓名">
                    <el-input  placeholder="请输入内容" v-model="yingPin.name"></el-input>
                </el-descriptions-item>
                <el-descriptions-item label="性别">
                    <el-radio-group v-model="yingPin.sex">
                        <el-radio-button label="1">男</el-radio-button>
                        <el-radio-button label="0">女</el-radio-button>
                    </el-radio-group>
                </el-descriptions-item>
                <el-descriptions-item label="出生年份">
                    <el-input type='number' placeholder="请输入内容" v-model="yingPin.birthYear"></el-input>
                </el-descriptions-item>
                <el-descriptions-item label="文化程度">
                     <el-select v-model="yingPin.education" placeholder="请选择">
                        <el-option
                        v-for="item in edus"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </el-descriptions-item>
                <el-descriptions-item label="毕业院校">
                    <el-input  placeholder="填写全称" v-model="yingPin.school"></el-input>
                </el-descriptions-item>
                <el-descriptions-item label="联系方式">
                    <el-input  placeholder="标注类型" v-model="yingPin.phone"></el-input>
                </el-descriptions-item>
                <el-descriptions-item label="技能">
                    <el-input  autosize type="textarea" placeholder="请输入内容" v-model="yingPin.skill"></el-input>
                </el-descriptions-item>
            </el-descriptions>
            <el-button type="primary" @click="isList=true">返回</el-button>
            <el-button type="danger" @click="submit">提交</el-button>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    
    name:'ZhaoPin',
    data(){
        return{
            isList:true,
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
            //
            zhaoPins:[
                {id:1,title:'开发工程师',describe:'好好干就是了',address:'苏州市吴中区',education:3,num:4,text:'熟练使用自己的手'}
            ],
            pageNumber:0,
            currentPage:1,
            yingPin:{
                zhaoPin:{id:-1},
                name:'',
                sex:1,
                birthYear:0,
                education:0,
                school:'',
                phone:'',
                skill:''
            },
        }
    },
    methods:{
        dengJi(id){
            this.yingPin.zhaoPin.id=id
            this.isList=false;
        },
        upload(num){
            axios.get('/zhaoPin/page').then(res=>{
                this.pageNumber=res.data
            })
            axios.get('/zhaoPin?num='+num).then(res=>{
                this.zhaoPins=res.data
                this.currentPage=num
            })
        },
        submit(){
            axios.post('/yingPin/add',this.yingPin).then(res=>{
                if(res.data){
                    this.$notify({
                        title: '成功',
                        message: '成功',
                        type: 'success'
                    })
                    const temp = this.yingPin.zhaoPin.id
                    this.yingPin={
                        zhaoPin:{id:temp},
                        id:-1,
                        name:'',
                        sex:1,
                        birthYear:0,
                        education:0,
                        school:'',
                        phone:'',
                        skill:''
                    }
                }
                else 
                this.$notify({
                    title: '警告',
                    message: '失败',
                    type: 'warning'
                })
            },
            ()=>{
                this.$notify({
                    title: '警告',
                    message: '失败',
                    type: 'warning'
                })
            }
            )
        }
    },
    mounted(){
        this.upload(1);
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
    }
}
</script>

<style scoped>
a{
    text-decoration: none
}
.main{
    margin-left:15%;
    margin-right:15%;
    margin-top: 50px;
    min-height: 590px;
  }

</style>