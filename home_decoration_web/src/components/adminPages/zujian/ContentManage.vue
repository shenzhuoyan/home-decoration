<template>
    <div id="main">
        <el-input
            placeholder="标题"
            v-model="title"
            style="margin-bottom:20px"
            clearable>
        </el-input>
        <vue-editor useCustomImageHandler v-model="text" @image-added="handleImageAdded"/>
        <div style="text-align: center;margin-top:20px"><el-button type="primary" @click="submit">点击提交</el-button></div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'ContentManage',
    mounted(){

    },
    data(){
        return{
            text:'',
            title:''
        }
    },
    props:['url'],
    methods:{
        submit(){
        this.$confirm('提交后将不可更改，是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                axios.post(this.url,{title:this.title,content:this.text}).then(
                    res => {
                        if(res.data){
                            this.$notify({
                                title: '成功',
                                message: '发布成功',
                                type: 'success'
                            })
                            this.text=''
                            this.title=''
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
    }
}
</script>

<style scoped>

</style>