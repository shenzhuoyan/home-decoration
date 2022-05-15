<template>
   <div id="main">

      <div id="show" v-show="!isEdit">
        <div style="margin-bottom: 20px"><el-button type="primary" @click="isEdit=true">修改</el-button></div>
        <div class="block">
            <el-timeline>
                <el-timeline-item :timestamp="item.publicTime" placement="top"  v-for="item in preText" :key="item.id">
                    <el-card v-text="item.content" style="height:30px">
                    </el-card>
                </el-timeline-item>
            </el-timeline>
        </div>


      </div>
      <div id="edit" v-show="isEdit">
           
        <el-input
            placeholder="请输入内容"
            v-model="text"
            clearable>
        </el-input>
           <div style="text-align: center;margin-top:20px"><el-button type="primary" @click="back">返回</el-button><el-button type="primary" @click="submit">点击提交</el-button></div>
      </div>
      
  </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'TextManage',
    data(){
        return{
            text:'',
            isEdit:false,
            preText:[],
            name:''
        }
    },
    mounted(){
        /*
        this.upName = this.name.charAt(0).toUpperCase() + this.name.slice(1);
        axios.get('/admin/all'+this.upName).then(res=>{
            this.preText=res.data
        })
        console.log(this.upName)
*/
        this.$bus.$on('update',name=>{
            this.name=name
            //首字母转大写
           /* this.upName = this.name.charAt(0).toUpperCase() + this.name.slice(1)*/
            this.updateModel()
        })
    },
    beforeDestroy(){
         this.$bus.$off('update')
      },
    methods: {
        submit(){
            this.$confirm('此操作将更新官网信息，是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios.post('/admin/'+this.name+'/add', {[this.name]:this.text}).then(
                res => {
                    if(res.data){
                        this.$notify({
                            title: '成功',
                            message: '更新成功',
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
            }).catch(() => {     
            });
            
               
        },
        updateModel(){
            axios.get('/admin/'+this.name+'/all').then(res=>{
                this.preText=res.data
            })
            this.isEdit=false
        },
        back(){
            this.isEdit=false
            this.updateModel()
        },
    },
}
</script>

<style scoped>
    #main{
        margin-top:200px;
        width:50%;
        margin-left:25%;
        margin-right:25%;
    }
</style>