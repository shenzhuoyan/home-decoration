<template>
  <div id="main">
    <el-tabs type="border-card"  @tab-click="switchPage">
      <el-tab-pane label="商品管理">
          <el-collapse @change="handleChange" accordion>
            <el-collapse-item  :name="good.id" v-for="good in goods" :key="good.id">
                <template slot="title">
                <div style="float:left;width:70%">{{good.title}}</div>
                <div style="float:left;width:10%"><el-tag>￥{{good.price}}</el-tag></div>
                <div style="float:left;width:10%"><el-button type="danger" size="small" @click="del(good.id)">删除</el-button></div>
                </template>
                <div id="title" v-show="good.id!=-1">
                    <p>简介：{{good.introduction}}</p>
                    <div id="designer">主设计师：{{good.designer.title}}</div>
                    <p>封面图:</p>
                    <img :src="good.picture" width="200px"/>
                </div>
                
                <el-card class="box-card">
                    <div v-html="good.content"></div>
                </el-card>
            </el-collapse-item>
        </el-collapse>
      </el-tab-pane>
      <el-tab-pane label="发布商品">
        <div class="demo-input-suffix">
            <table style="width:100%;">
                <tr>
                    <td style="width:50px">标题</td>
                    <td ><el-input v-model="good.title" placeholder="请输入内容"></el-input></td>
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
                            <img v-if="good.picture" :src="good.picture" class="avatar">
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
                        v-model="good.introduction">
                        </el-input>
                    </td>
                </tr>
                <tr>
                    <td>参考价</td>
                    <td><el-input v-model="good.price" placeholder="请输入内容"></el-input></td>
                </tr>
                <tr>
                    <td>设计师</td>
                    <td>
                        <el-select v-model="good.designer.id" placeholder="请选择">
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
                        <vue-editor useCustomImageHandler v-model="good.content" @image-added="handleImageAdded"/>
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
    name:'GoodManage',
    data(){
        return{
            activeName:'1',
            goods:[{id:-1,title:'',introduction:'',designer:{title:'',id:-1},content:'',picture:'',price:0}],
            good:{id:-1,title:'',introduction:'',designer:{title:'',id:-1},content:'',picture:'',price:0},
            designers:[],
        }
    },
    mounted(){
        axios.get("/admin/good/all").then(res=>{
            this.goods=res.data
        })
        axios.get("/designerIntroduce").then(res=>{
            this.designers=res.data
        })
    },
    methods:{
        success(){
        this.$notify({
            title: '成功',
            message: '操作成功',
            type: 'success'
        })
      },
      upload(){
        axios.get("/admin/good/all").then(res=>{
            this.goods=res.data
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
          axios.get("/admin/good/del?id="+id).then(res=>{
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
                axios.post('/admin/good/add', this.good).then(
                    res => {
                        if(res.data){
                            this.$notify({
                                title: '成功',
                                message: '发布成功',
                                type: 'success'
                            })
                            this.good={id:1,title:'',introduction:'',designer:{title:'',id:-1},content:'',picture:'',price:0}
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
                this.good.picture=res.data
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