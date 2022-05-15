<template>
  <div id="main">


      
      <div id="show" v-show="!isEdit">

        <div style="margin-bottom: 20px"><el-button type="primary" @click="isEdit=true">修改</el-button></div>
        <div class="block">
            <el-timeline>
                <el-timeline-item :timestamp="item.publicTime" placement="top"  v-for="item in preDescribes" :key="item.id">
                <el-card v-html="item.content" style="min-height: 100px">
                </el-card>
                </el-timeline-item>
            </el-timeline>
        </div>
          <!--<div  v-for="item in preDescribes" :key="item.id">
              <p>{{item.publicTime}}</p>
              <div v-html="item.content"></div>
              <el-divider></el-divider>
          </div>
          -->
         

      </div>
      <div id="edit" v-show="isEdit">
           <vue-editor useCustomImageHandler v-model="text" @image-added="handleImageAdded"/>
           <div style="text-align: center;margin-top:20px"><el-button type="primary" @click="back">返回</el-button><el-button type="primary" @click="submit">点击提交</el-button></div>
      </div>
      
  </div>
</template>

<script>
import axios from "axios"
export default {
    name:'AdminIntroduce',
    data(){
        return{
            preDescribes:[],
            isEdit:false,
            //
            text:''
        }
    },
    mounted(){
        this.updateModel()
    },
    methods:{
        updateModel(){
            axios.get('/admin/describe/all').then(
        res => {
            this.preDescribes=res.data
        })
        },
        submit(){
        this.$confirm('此操作更新官网信息, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                axios.post('/admin/describe/add', {describe:this.text}).then(
                res => {
                    if(res.data){
                        this.$notify({
                            title: '成功',
                            message: '公司介绍更新成功',
                            type: 'success'
                        })
                        this.text=''
                    }
                    else this.$notify({
                        title: '警告',
                        message: '更新失败',
                        type: 'warning'
                    })
                })
                }).catch(() => {})

            
               
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
        back(){
            this.isEdit=false
            this.updateModel()
        }
    }
}
</script>

<style scoped>
    #main{
        margin-left: 15%;
        margin-right:15%;
    }
    #show{
        margin-bottom: 200px;
    }
    
</style>