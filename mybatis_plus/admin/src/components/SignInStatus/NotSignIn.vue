<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>签到情况</el-breadcrumb-item>
      <el-breadcrumb-item>未签到</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <!-- tab标签页 -->
      <el-tabs v-model="activeName">
        <el-tab-pane label="未签到" name="accept">
          <el-table :data="acceptList" border stripe>
            <el-table-column type="index"></el-table-column>
            <el-table-column label="员工ID" rop="deptName"></el-table-column>
            <el-table-column label="部门ID" prop="deptName"></el-table-column>
            <el-table-column label="名字" prop="roomNo"></el-table-column>
            <!--                        <el-table-column label="申请原因" prop="meetingSize"></el-table-column>-->
            <el-table-column label="电话号码" prop="meetingTheme"></el-table-column>
          </el-table>

          <!-- 分页区域 -->
          <el-pagination
            @size-change="handleSizeChangeAccept"
            @current-change="handleCurrentChangeAccept"
            :current-page="acceptInfo.currentPage"
            :page-sizes="[5, 10]"
            :page-size="acceptInfo.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="acceptTotal">
          </el-pagination>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        activeName: 'accept',
        acceptList: [],
        rejectList: [],
        acceptInfo: {
          auditStatus: 1,
          pageSize: 5,
          currentPage: 1
        },
        rejectInfo: {
          auditStatus: 2,
          pageSize: 5,
          currentPage: 1
        },
        acceptTotal: 0,
        rejectTotal: 0
      }
    },
    created () {
      this.getAcceptList()
      this.getRejectList()
    },
    methods: {
      async getAcceptList () {
        const { data: res } = await this.$http.get('/apply/history', { params: this.acceptInfo })
        if (res.code !== 100) {
          return this.$message.error('获取申请列表失败！')
        } else {
          this.acceptList = res.extend.result.list
          this.acceptTotal = res.extend.result.total
        }
      },
      async getRejectList () {
        const { data: res } = await this.$http.get('/apply/history', { params: this.rejectInfo })
        if (res.code !== 100) {
          return this.$message.error('获取申请列表失败！')
        } else {
          this.rejectList = res.extend.result.list
          this.rejectTotal = res.extend.result.total
        }
      },
      handleSizeChangeAccept (newSize) {
        this.acceptInfo.pageSize = newSize
        this.getAcceptList()
      },
      handleCurrentChangeAccept (newPage) {
        this.acceptInfo.currentPage = newPage
        this.getAcceptList()
      },
      handleSizeChangeNotReject (newSize) {
        this.rejectInfo.pageSize = newSize
        this.getRejectList()
      },
      handleCurrentChangeNotReject (newPage) {
        this.rejectInfo.currentPage = newPage
        this.getRejectList()
      }
    }
  }
</script>
