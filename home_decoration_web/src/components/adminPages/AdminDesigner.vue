<template>
  <div id="main">
    <el-row v-show="!isEdit">
      <el-col :span="8" v-for="item in designers" :key="item.id" style="width:30%;margin-left:20px;">
          <el-card :body-style="{ padding: '0px' }" >
            <img :src="item.picture" class="image"/>
            <div style="padding: 14px;text-align: center">
              <span style="font-size: 30px;">
                {{item.title}}
              </span>
              <div class="bottom clearfix">
                <el-button type="primary" @click="edit(item)">编辑介绍</el-button>
              </div>
            </div>
          </el-card>
      </el-col>
    </el-row>
    <div v-show="isEdit">

    <el-upload
      class="avatar-uploader"
      action="/tt"
      :auto-upload="false"
      :on-change="uploadSectionFile"
      name="picture"
      :multiple="false"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      >
      <img v-if="currentImg" :src="currentImg" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>

      <el-input
            placeholder="标题"
            v-model="title"
            style="margin-bottom:20px"
            clearable>
        </el-input>
        <vue-editor useCustomImageHandler v-model="text" @image-added="handleImageAdded"/>
        <div style="text-align: center;margin-top:20px">
          <el-button type="primary" @click="submit">点击提交</el-button>
          <el-button plain @click="upload">取消</el-button>
        </div>
      
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
    name:"AdminDesigner",
    data(){
        return {
            //以下为后端内容
            designers:[],
            isEdit:false,
            text:'',
            title:'',
            currentId:-1,
            currentImg:'',
        }
    },
    mounted(){
      this.upload()
    },
    methods:{
      edit(designer){
        this.isEdit=true
        this.currentId=designer.id
        this.text=designer.content
        this.title=designer.title
        this.currentImg=designer.picture
      },
      upload(){
        this.isEdit=false
        axios.get("/designerIntroduce").then(res=>{
          this.designers=res.data
        })
      },
      submit(){
        this.$confirm('提交确认', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
          }).then(() => {
          axios.post('/admin/designerIntroduce/update', 
          {
            id:this.currentId,
            title:this.title,
            content:this.text,
            picture:this.currentImg
          }
          ).then(
              res => {
                  if(res.data){
                      this.$notify({
                          title: '成功',
                          message: '发布成功',
                          type: 'success'
                      })
                      this.text=''
                      this.title=''
                      this.currentImg=''
                      this.isEdit=false
                      this.upload()
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
                this.currentImg=res.data
            })
            .catch(err => {
                console.log(err)
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

      handleAvatarSuccess(res, file) {
        this.currentImg = URL.createObjectURL(file.raw);
      },
    }
}
</script>

<style scoped>
#main{
  margin-top: 100px;
  margin-left:15%;
  margin-right:15%;
  text-align:center;
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