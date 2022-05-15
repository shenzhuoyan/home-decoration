<template>
  <div id="main">
    <el-tabs type="border-card"  @tab-click="switchPage">
      <el-tab-pane label="案例管理">
          <el-collapse @change="handleChange" accordion>
            <el-collapse-item  :name="anLi.id" v-for="anLi in anLis" :key="anLi.id">
                <template slot="title">
                <div style="float:left;width:70%">{{anLi.title}}</div>
                <div style="float:left;width:10%"><el-tag>{{anLi.date}}</el-tag></div>
                <div style="float:left;width:10%"><el-button type="danger" size="small" @click="del(anLi.id)">删除</el-button></div>
                </template>
                <div id="title" v-show="anLi.id!=-1">
                    <p>简介：{{anLi.introduction}}</p>
                    <div id="designer">主设计师：{{anLi.designer.title}}</div>
                    <p>封面图:</p>
                    <img :src="anLi.picture" width="200px"/>
                </div>
                
                <el-card class="box-card">
                    <div v-html="anLi.content"></div>
                </el-card>
            </el-collapse-item>
        </el-collapse>
      </el-tab-pane>
      <el-tab-pane label="发布案例">
        <div class="demo-input-suffix">
            <table style="width:100%;">
                <tr>
                    <td style="width:50px">标题</td>
                    <td ><el-input v-model="anLi.title" placeholder="请输入内容"></el-input></td>
                </tr>
                <tr>
                    <td>图片</td>
                    <td>
                        <el-upload
                            class="avatar-uploader"
                            action
                            :auto-upload="false"
                            :on-change="uploadSectionFile"
                            name="picture"
                            :multiple="false"
                            :show-file-list="false"
                            :on-success="handleAvatarSuccess"
                            >
                            <img v-if="anLi.picture" :src="anLi.picture" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </td>
                </tr>
                <tr>
                    <td>简介</td>
                    <td><el-input
                        type="textarea"
                        autosize
                        placeholder="请输入内容"
                        v-model="anLi.introduction">
                        </el-input>
                    </td>
                </tr>
                <tr>
                    <td>设计师</td>
                    <td>
                        <el-select v-model="anLi.designer.id" placeholder="请选择">
                            <el-option
                            v-for="designer in designers"
                            :key="designer.id"
                            :label="designer.title"
                            :value="designer.id">
                            </el-option>
                        </el-select>
                    </td>
                </tr>
                <tr>
                    <td>内容</td>
                    <td>
                        <vue-editor useCustomImageHandler v-model="anLi.content" @image-added="handleImageAdded"/>
                        <div style="text-align: center;margin-top:20px"><el-button type="primary" @click="submit">点击提交</el-button></div>
                    </td>
                </tr>
          
            </table>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'AnLiManage',
    data(){
        return{
            activeName:'1',
            anLis:[{id:1,title:'',introduction:'',designer:{title:'',id:-1},content:'',picture:'',date:''}],
            anLi:{id:1,title:'',introduction:'',designer:{title:'',id:-1},content:'',picture:''},
            designers:[],
        }
    },
    mounted(){
        axios.get("/admin/anLi/all").then(res=>{
            this.anLis=res.data
        })
        axios.get("/designerIntroduce").then(res=>{
            this.designers=res.data
        })
    },
    methods:{
        success(){
        this.$notify({
            title: '成功',
            message: '发布成功',
            type: 'success'
        })
      },
      upload(){
        axios.get("/admin/anLi/all").then(res=>{
            this.anLis=res.data
        })
      },
      failed(){
        this.$notify({
          title: '警告',
          message: '失败',
          type: 'warning'
        })
      },
        handleChange(val) {
            console.log(val);
        },
        switchPage(tab){
           if(tab.index==0)
            this.upload()
        
        },
        del(id){
           this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.get("/admin/anLi/del?id="+id).then(res=>{
            if(res.data) {
              this.success()
              this.upload()
            }
            else this.failed()
          }).catch(()=>{this.failed()})
        })
        },
        handleImageAdded(file, Editor, cursorLocation, resetUploader) {
            //formDta是个对象，用来模拟表单的
            let formData = new FormData()
            formData.append('picture', file)
            axios({
                url:'/admin/picture/push',
                method: 'POST',
                data: formData
            })
            .then(res => {//这两行是关键代码了。在鼠标位置插入图片，数据存的是url
                Editor.insertEmbed(cursorLocation, 'image', res.data)
                resetUploader()
            })
            .catch(err => {
                console.log(err)
            })
        },
        submit(){
        this.$confirm('提交后将不可更改，是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                axios.post('/admin/anLi/add', this.anLi).then(
                    res => {
                        if(res.data){
                            this.$notify({
                                title: '成功',
                                message: '发布成功',
                                type: 'success'
                            })
                            this.anLi={id:1,title:'',introduction:'',designer:{title:'',id:-1},content:'',picture:''}
                        }
                        else this.$notify({
                            title: '警告',
                            message: '发布失败',
                            type: 'warning'
                        })
                    })
                }).catch(() => {
                    this.$notify({
                        title: '警告',
                        message: '就没发出去',
                        type: 'warning'
                    })
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
                this.anLi.picture=res.data
            })
            .catch(err => {
                console.log(err)
            })
        },
        handleAvatarSuccess(res, file) {
        this.currentImg = URL.createObjectURL(file.raw);
      },
    }
}
</script>

<style scoped>
    #title{
        text-align: center;
        
    }
    #designer{
        font-size: 20px;
    }
    .image {
    width: 100%;
    display: block;
  }

  .avatar-uploader  {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    margin: 50px;
  }
  .avatar-uploader:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>