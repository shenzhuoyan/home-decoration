<template>
  <div>
    <el-tabs type="border-card"  @tab-click="switchPage">
      <el-tab-pane label="通知管理">
        <el-table
          :data="tableData"
          stripe
          style="width: 100%"
          v-loading="loading">
          <el-table-column
            prop="title"
            label="标题">
          </el-table-column>
          <el-table-column
            prop="date"
            label="日期"
            width="200">
          </el-table-column>
          <el-table-column
          label="状态">
          <template slot-scope="scope">
            <el-tag :type="scope.row.status|statusTag">{{scope.row.status | statusFilter}}</el-tag>
          </template>
          </el-table-column>
          <el-table-column label="操作" width="400">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="look(scope.row.id)">查看</el-button>
              <el-button
              size="mini"
              @click="resume(scope.row.id,1)"
              :disabled="scope.row.status==1">恢复</el-button>
              <el-button
              size="mini"
              @click="important(scope.row.id)"
              :disabled="scope.row.status==2">设为重要</el-button>
              <el-button
              size="mini"
              @click="top(scope.row.id)"
              :disabled="scope.row.status==3">置顶</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="del(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div style="margin-top: 20px;text-align:center;">
            <el-button v-show="pageNumber>2&&currentPage!=1" @click="upload(1)">第一页</el-button>
            <el-button v-for="index in pageNumber" :key="index" @click="upload(index)" plain>{{index}}</el-button>
            <el-button v-show="pageNumber>2&&currentPage!=pageNumber" @click="upload(pageNumber)">最后一页</el-button>
        </div>
      </el-tab-pane>


      <el-tab-pane label="已删除">
        <el-table
          :data="delTableData"
          stripe
          style="width: 100%">
          <el-table-column
            prop="title"
            label="标题">
          </el-table-column>
          <el-table-column
            prop="date"
            label="日期"
            width="200">
          </el-table-column>
          <el-table-column label="操作" width="200">
            <template slot-scope="scope">
              
              <el-button
                size="mini"
                type="danger"
                @click="resume(scope.row.id,2)">重新发布</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div style="margin-top: 100px;margin-buttom:200px;width:100%;text-align:center">
          <el-button v-show="delNumber>2&&delCurrentPage!=1" @click="getDelPage(1)">第一页</el-button>
          <el-button v-for="index in delNumber" :key="index" @click="getDelPage(index)" plain>{{index}}</el-button>
          <el-button v-show="delNumber>2&&delCurrentPage!=delNumber" @click="getDelPage(delNumber)">最后一页</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="发布通知">
        <content-manage url="/admin/noticeList/add"></content-manage>
      </el-tab-pane>
    </el-tabs>
     
  </div>
</template>

<script>
import ContentManage from './zujian/ContentManage.vue'
import axios from 'axios'
export default {
  components: { ContentManage },
    name:'NoticeManage',
    data(){
      return{
        tableData:[
          {id:-1,title:'11',date:'1111',status:-1}
        ],
        pageNumber:0,
        currentPage:1,
        delNumber:0,
        delTableData:[],
        delCurrentPage:1,
        loading:false
      }
    },
    methods:{
      success(){
        this.$notify({
            title: '成功',
            message: '发布成功',
            type: 'success'
        })
      },
      failed(){
        this.$notify({
          title: '警告',
          message: '失败',
          type: 'warning'
        })
      },
      del(id){
        this.loadStatus()
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.get("/admin/noticeList/rm?id="+id).then(res=>{
            if(res.data) {
              this.success()
              this.upload(1)
            }
            else this.failed()
          }).catch(()=>{this.failed()})
        })



       
      },
      resume(id,p){
        this.loadStatus()
        axios.get("/admin/noticeList/resume?id="+id).then(res=>{
          if(res.data) {
            this.success()
            if(p==1)
              this.upload(1)
            else this.getDelPage(1)
          }
          else this.failed()
        }).catch(()=>{this.failed()})
      },
      look(id){
        const routeData = this.$router.resolve({
          name:'notice'
        })
        sessionStorage.setItem("url", '/noticeList/get?id='+id)
        window.open(routeData.href, '_blank')
      },

      loadStatus(){
        this.loading=true
        setTimeout(() => {
          this.loading=false
        }, 300)
      },
      important(id){
        this.loadStatus()    
        axios.get("/admin/noticeList/up?id="+id).then(res=>{
          if(res.data) {
            this.success()
            this.upload(1)
            
          }
          else this.failed()
        }).catch(()=>{this.failed()})
        
      },
      top(id){
        this.loadStatus()
        axios.get("/admin/noticeList/top?id="+id).then(res=>{
          if(res.data) {
            this.success()
            this.upload(1)
          }
          else this.failed()
        }).catch(()=>{this.failed()})
      },
      upload(num){
        axios.get("/noticeList/page").then(res=>{
            this.pageNumber=res.data
        })
        axios.get("/noticeList?page="+num).then(res=>{
            this.tableData=res.data
            this.currentPage=num
        })
      },

      getDelPage(num){
        axios.get("/admin/noticeList/deletedPage").then(res=>{
            this.delNumber=res.data
        })

        axios.get("/admin/noticeList/deleted?page="+num).then(res=>{
            this.delTableData=res.data
            this.delCurrentPage=num
        })
      },
      switchPage(tab){
        this.loadStatus()
          if(tab.index==0){
              this.upload(1)
          }else if(tab.index==1){
              this.getDelPage(1)
          }
      },
    },
    mounted(){
      this.upload(1)
    },
    filters:{
      statusFilter(value){
        switch(value){
          case 0: return '已删除'
          case 1: return '已发布'
          case 2: return '重要'
          case 3: return '置顶'
          default: return '未知'
        }
      },
      statusTag(value){
        switch(value){
          case 0: return 'danger'
          case 1: return 'success'
          case 2: return 'info'
          case 3: return 'warning'
          default: return 'danger'
        }
      }
    }
}
</script>

<style>

</style>