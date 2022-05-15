<template>
    <el-drawer
        :title="title"
        direction="rtl"
        size="50%"
        :visible.sync="show">
        <el-divider></el-divider>
        <div v-for="item in yingPins" :key="item.id" v-show="yingPins.length>0">
            <el-descriptions style="margin-left:20px">
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
        <el-empty description="没有人申请该职位" v-show="yingPins.length<=0"></el-empty>
    </el-drawer>
</template>

<script>
export default {
    name:'CeLan',
    data(){
        return{
            show:false
        }
    },
    props:['yingPins','title'],
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
        }
    },
    mounted(){
        this.$bus.$on('showCeLan',()=>{this.show=true})
    },
    beforeDestroy(){
         this.$bus.$off('showCeLan')
      }
}
</script>

<style>

</style>