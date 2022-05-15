<template>
  <div id="main">
    <el-tabs type="border-card"  @tab-click="switchPage">
        <el-tab-pane label="管理">

            <el-collapse accordion v-for='pic in pictures' :key="pic.id">
                <el-collapse-item name="1">
                    <template slot="title">
                    <div style="width:90%">图片地址: {{pic.content}}</div>
                    <div style="width:10%"><el-button type="danger" @click="del(pic.id)">删除</el-button></div>
                    </template>
                    <img :src="pic.content" style="width:100%"/>
                </el-collapse-item>
            </el-collapse>
        </el-tab-pane>
        <el-tab-pane label="添加">
            <el-upload
            action
            :auto-upload="false"
            :on-change="uploadSectionFile"
            name="picture"
             :file-list="fileList">
                <el-button size="small" type="primary">选择图片</el-button>
            </el-upload>
            <el-button size="small" type="primary" @click="push">上传</el-button>
        </el-tab-pane>
        
    </el-tabs>

  </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'BannerManage',
    data(){
        return{
        pictures:[],
        fileList: [],

        }
    },
    mounted(){
        this.upload()
    },
    methods:{
        switchPage(tab){
            if(tab.index==1)
            this.upload()
        },
        upload(){
            axios.get("/banner").then(res=>{
                this.pictures=res.data
            })
        },
        uploadSectionFile(file){
          let formData = new FormData()
          formData.append('picture', file.raw)
          axios({
              url:'/admin/picture/push',
              method: 'POST',
              data: formData
          }).then(res => {//这两行是关键代码了。在鼠标位置插入图片，数据存的是url
                this.fileList.push({name:file.name,url:res.data})
            })
            .catch(err => {
                console.log(err)
            })
        },

        push(){
            console.log(this.fileList)
            for(let i=0;i<this.fileList.length;i++){undefined
                axios.get("/admin/banner/add?picture="+this.fileList[i].url)
            }
            this.$notify({
            title: '成功',
            message: '发布成功',
            type: 'success'
            })
            this.fileList=[]
            this.upload()
        },
        del(id){
           this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.get("/admin/banner/del?id="+id).then(res=>{
            if(res.data) {
                this.$notify({
                title: '成功',
                message: '发布成功',
                type: 'success'
                })
              this.upload()
            }
            else this.$notify({
                title: '警告',
                message: '失败',
                type: 'warning'
                })
          }).catch(()=>{this.failed()})
        })
        },
        
    }
}
</script>

<style scoped>
</style>