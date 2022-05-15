<template>
  <div id="main">
      <div>
        <span class="firstTitle">总览/</span><span class="secondTitle">后台详情</span>
        <el-divider></el-divider>
        <el-row>
            <el-col :span="8" v-for="(item, index) in utils" :key="index"  style="width:170px;margin-top: 20px;margin-left:20px">
                <el-card :body-style="{ padding: '0px' }" style="height: 160px;">
                <div  style="text-align:center"><i :class="item.icon" style="font-size:100px"/></div>
                <div style="padding: 14px;text-align:center">
                    <el-button plain @click="to(item.href)">{{item.title}}</el-button>
                    <!--
                    <div class="bottom clearfix">
                    <span>{{item.text}}</span>
                    <el-button type="text" class="button">操作按钮</el-button>
                    </div>-->
                </div>
                </el-card>
            </el-col>
        </el-row>
      </div>
  </div>
</template>

<script>
export default {
    name:'AdminHome',
    data(){
        return {
            //以下为后端内容
            utils:[
                {
                    title:'公司简介',
                    icon:'el-icon-platform-eleme',
                    href:'adminintroduce'
                },
                {
                    title:'咨询热线',
                    icon:'el-icon-eleme',
                    href:'adminintroduce'
                },
                {
                    title:'备案号',
                    icon:'el-icon-s-tools',
                    href:'adminintroduce'
                },
                {
                    title:'总部地址',
                    icon:'el-icon-user-solid',
                    href:'adminintroduce'
                },
                {
                    title:'发布设计师',
                    icon:'el-icon-s-goods',
                    href:'designermanage'
                },
                {
                    title:'设计师列表',
                    icon:'el-icon-s-goods',
                    href:'designermanage'
                },
                {
                    title:'发布招聘',
                    icon:'el-icon-warning-outline',
                    href:'adminzhaopin'
                },
                {
                    title:'应聘列表',
                    icon:'el-icon-picture-outline',
                    href:'yingpin'
                },
                {
                    title:'发布案例',
                    icon:'el-icon-video-camera',
                    href:'anlimanage'
                },
                {
                    title:'案例列表',
                    icon:'el-icon-s-order',
                    href:'anlimanage'
                },
                {
                    title:'发布产品',
                    icon:'el-icon-s-promotion',
                    href:'goodmanage'
                },
                {
                    title:'产品列表',
                    icon:'el-icon-s-shop',
                    href:'goodmanage'
                },
                {
                    title:'发布通知',
                    icon:'el-icon-bell',
                    href:'noticemanage'
                },
                {
                    title:'通知列表',
                    icon:'el-icon-s-order',
                    href:'noticemanage'
                },
                {
                    title:'轮播图',
                    icon:'el-icon-picture-outline',
                    href:'bannermanage'
                },
            ]
        }
    },
    methods:{
        to(name){
            this.$router.push({
                name
            }).catch(err => {err})
        }
    },
    mounted(){
        if(!sessionStorage.getItem("alerted")){
            this.$alert('管理员每个操作都会立即改变官网的显示效果，所以操作时请务必谨慎', '注意', {
                confirmButtonText: '确定',
                });
            sessionStorage.setItem("alerted",true)
        }
    }
}
</script>

<style scoped>
     a{
        text-decoration: none
    }
  .firstTitle{
    font-size: 30px;
  }

  .secondTitle{
    font-size: 15px;
    color:grey;
  }
#main{
    margin-left:15%;
    margin-right:15%;
    margin-top: 50px;
  }
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .image {
    width: 250px;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
</style>