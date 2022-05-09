<template>
    <div>
        <!-- 面包屑导航区 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>员工管理</el-breadcrumb-item>
            <el-breadcrumb-item>员工信息</el-breadcrumb-item>
        </el-breadcrumb>

        <!-- 卡片视图区 -->
        <el-card>
            <el-row :gutter="20">
                <el-col :span="7">
                    <el-input placeholder="请输入员工名字" v-model="keyWord" clearable @clear="getRoomList">
                        <el-button slot="append" icon="el-icon-search" @click="selectRoom"></el-button>
                    </el-input>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" @click="addDialogVisible = true">添加员工</el-button>
                </el-col>
            </el-row>

            <!-- 员工列表区 -->
            <el-table :data="roomList" border stripe>
                <el-table-column type="index"></el-table-column>
                <el-table-column label="员工ID" prop="userId"></el-table-column>
                <el-table-column label="部门ID" prop="deptId"></el-table-column>
                <el-table-column label="名字" prop="userName"></el-table-column>
              <el-table-column label="邮箱" prop="email"></el-table-column>
              <el-table-column label="员工电话" prop="phone"></el-table-column>
              <el-table-column label="性别" prop="sex">
                <template #default="scope">
                  <span>{{scope.row.sex== 0 ? '女' :scope.row.sex== 1 ? '男':'未知'}}</span>
                </template>
              </el-table-column>
                <el-table-column label="操作" width="180px">
                    <template slot-scope="scope">
                        <!-- 修改按钮 -->
                        <el-tooltip class="item" effect="dark" content="修改员工信息" placement="top" :enterable="false">
                            <el-button size="mini" type="primary" icon="el-icon-edit" @click="showEditDialog(scope.row.userId)"></el-button>
                        </el-tooltip>
                        <!-- 删除按钮 -->
                        <el-tooltip class="item" effect="dark" content="删除员工" placement="top" :enterable="false">
                            <el-button size="mini" type="danger" icon="el-icon-delete" @click="removeRoomById(scope.row.userId)"></el-button>
                        </el-tooltip>
                    </template>
                </el-table-column>
            </el-table>

            <!-- 分页区域 -->
            <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="queryInfo.currentPage"
            :page-sizes="[5, 10]"
            :page-size="queryInfo.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
            </el-pagination>
        </el-card>

        <!-- 添加员工对话框 -->
        <el-dialog
        title="添加员工"
        :visible.sync="addDialogVisible"
        width="50%"
        @close="addDialogClosed">
        <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px">
            <el-form-item label="员工ID" prop="userId">
                <el-input v-model="addForm.userId"></el-input>
            </el-form-item>
          <el-form-item label="部门ID" prop="deptId">
            <el-input v-model="addForm.deptId"></el-input>
          </el-form-item>
          <el-form-item label="员工名字" prop="userName">
            <el-input v-model="addForm.userName"></el-input>
          </el-form-item>
          <el-form-item label="员工邮箱" prop="email">
            <el-input v-model="addForm.email"></el-input>
          </el-form-item>
          <el-form-item label="员工电话" prop="phone">
            <el-input v-model="addForm.phone"></el-input>
          </el-form-item>
          <el-form-item label="员工性别" prop="sex">
            <el-input v-model="addForm.sex"></el-input>
          </el-form-item>

        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="addDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addEmp">确 定</el-button>
        </span>
        </el-dialog>

        <!-- 修改员工信息对话框 -->
        <el-dialog
        title="修改员工信息"
        :visible.sync="editDialogVisible"
        width="50%"
        @close="editDialogClosed">
        <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="100px">
          <el-form-item label="员工ID" prop="userId">
            <el-input v-model="editForm.userId"></el-input>
          </el-form-item>
          <el-form-item label="部门ID" prop="deptId">
            <el-input v-model="editForm.deptId"></el-input>
          </el-form-item>
          <el-form-item label="员工名字" prop="userName">
            <el-input v-model="editForm.userName"></el-input>
          </el-form-item>
          <el-form-item label="员工邮箱" prop="email">
            <el-input v-model="editForm.email"></el-input>
          </el-form-item>
          <el-form-item label="员工电话" prop="phone">
            <el-input v-model="editForm.phone"></el-input>
          </el-form-item>
          <el-form-item label="员工性别" prop="sex">
            <el-input v-model="editForm.sex"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="editDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="eidtRoom">确 定</el-button>
        </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
  data () {
    // 验证手机号
    var checkMobile = (rule, value, cb) => {
      const regMobile = /^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$/
      if (regMobile.test(value)) {
        return cb()
      }
      cb(new Error('请输入格式正确的手机'))
    }
        return {
            queryInfo: {
                currentPage: 1,
                pageSize: 5
            },
            roomList: [
              // {
              //   userId: 1,
              //   deptId: 103,
              //   userName: 'admin',
              //   email: 'ry@163.com',
              //   phone: '15888888888',
              //   sex: 1
              // }
            ],
            total: 0,
            keyWord: '',
            addDialogVisible: false,
            editDialogVisible: false,
            addForm: {
              userId: 111,
              deptId: 103,
              userName: '小明',
              email: 'ry@163.com',
              phone: '15886899653',
              sex: 1
            },
            addFormRules: {
              userId: [
                    { required: true, message: '请输入员工ID', trigger: 'blur' },
                    { min: 3, max: 4, message: '员工号长度在3~4个数字之间', trigger: 'blur' }
                ],
              deptId: [
                { required: true, message: '请输入部门ID', trigger: 'blur' },
                { min: 3, max: 4, message: '员工号长度在3~4个数字之间', trigger: 'blur' }
              ],
              userName: [
                { required: true, message: '请输入员工名字', trigger: 'blur' },
                { min: 2, max: 4, message: '员工名字在2~4个字之间', trigger: 'blur' }
              ],
              email: [
                { required: true, message: '请输入员工邮箱', trigger: 'blur' }
                // { min: 3, max: 4, message: '员工名字在3~4个字之间', trigger: 'blur' }
              ],
              phone: [
        { required: true, message: '请输入手机号', trigger: 'blur' },
        { validator: checkMobile, trigger: 'blur' }
              ],
              sex: [
                { required: true, message: '请输入员工性别（0 代表男 1 代表女）', trigger: 'blur' }
                // { min: 2, max: 4, message: '0 代表难 1 代表女', trigger: 'blur' }
              ]
            },
            editForm: {},
            editFormRules: {
              userId: [
                { required: true, message: '请输入员工ID', trigger: 'blur' },
                { min: 3, max: 4, message: '员工号长度在3~4个数字之间', trigger: 'blur' }
              ],
              deptId: [
                { required: true, message: '请输入部门ID', trigger: 'blur' },
                { min: 3, max: 4, message: '员工号长度在3~4个数字之间', trigger: 'blur' }
              ],
              userName: [
                { required: true, message: '请输入员工名字', trigger: 'blur' },
                { min: 2, max: 4, message: '员工名字在2~4个字之间', trigger: 'blur' }
              ],
              email: [
                { required: true, message: '请输入员工邮箱', trigger: 'blur' }
                // { min: 3, max: 4, message: '员工名字在3~4个字之间', trigger: 'blur' }
              ],
              phone: [
                { required: true, message: '请输入手机号', trigger: 'blur' },
                { validator: checkMobile, trigger: 'blur' }
              ],
              sex: [
                { required: true, message: '请输入员工性别（0 代表男 1 代表女）', trigger: 'blur' }
                // { min: 2, max: 4, message: '0 代表难 1 代表女', trigger: 'blur' }
              ]
            }
        }
    },
    created () {
        this.getRoomList()
    },
    methods: {
        async getRoomList () {
            const { data: res } = await this.$http.get('/rooms', { params: this.queryInfo })
            if (res.code !== 100) {
                return this.$message.error('获取员工信息失败！')
            }
            this.roomList = res.extend.result.list
            this.total = res.extend.result.total
        },
        // // 监听pageSize改变的事情
        handleSizeChange (newSize) {
            this.queryInfo.pageSize = newSize
            this.getRoomList()
        },
    //     // 监听页码值改变的事件
        handleCurrentChange (newPage) {
            this.queryInfo.currentPage = newPage
            this.getRoomList()
        },
        // // 监听switch状态
        // async roomStatusChanged (roomInfo) {
        //     const { data: res } = await this.$http.put('/room', { roomId: roomInfo.roomId, roomStatus: roomInfo.roomStatus })
        //     if (res.code !== 100) {
        //         roomInfo.roomStatus = !roomInfo.roomStatus
        //         return this.$message.error('修改员工状态失败！')
        //     } else {
        //         this.$message.success('修改员工状态成功！')
        //     }
        // },

        async selectRoom () {
            const { data: res } = await this.$http.get('/room', { params: { userId: this.keyWord, currentPage: this.queryInfo.currentPage, pageSize: this.queryInfo.pageSize } })
            if (res.code !== 100) {
                return this.$message.error('查询员工信息失败！')
            }
            this.roomList = res.extend.result.list
            this.total = res.extend.result.total
            this.$message.success('查询员工信息成功！')
        },
        addDialogClosed () {
            this.$refs.addFormRef.resetFields()
        },
        editDialogClosed () {
            this.$refs.editFormRef.resetFields()
        },
      // 添加员工
        addEmp () {
            this.$refs.addFormRef.validate(async valid => {
                if (!valid) return
                const { data: res } = await this.$http.post('/room', { userId: this.addForm.userId, deptId: this.addForm.deptId, userName: this.addForm.userName, email: this.addForm.email, phone: this.addForm.phone, sex: this.addForm.sex })
                if (res.code !== 100) {
                    return this.$message.error('添加员工失败！')
                }
                this.$message.success('添加员工成功！')
                this.addDialogVisible = false
                this.getRoomList()
            })
        },
        async showEditDialog (userId) {
            const { data: res } = await this.$http.get('/room', { params: { userId: userId } })
            console.log(res)
            if (res.code !== 100) {
                return this.$message.error('查询员工信息失败！')
            }
            this.editForm = res.extend.result
            this.editDialogVisible = true
        },
        eidtRoom () {
            this.$refs.editFormRef.validate(async valid => {
                if (!valid) return
                const { data: res } = await this.$http.put('/room', this.editForm)
                if (res.code !== 100) {
                    return this.$message.error('修改员工信息失败！')
                } else {
                    this.editDialogVisible = false
                    this.getRoomList()
                    this.$message.success('修改员工信息成功！')
                }
            })
        },
        async removeRoomById (userId) {
            const confirmResult = await this.$confirm('此操作将永久删除该员工, 是否继续?',
            '提示',
            {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).catch(err => err)
            if (confirmResult !== 'confirm') {
                return this.$message.info('已取消删除')
            }
            const { data: res } = await this.$http.delete('/room', { data: { userId: userId } })
            if (res.code !== 100) {
                return this.$message.error('删除员工失败！')
            }
            this.$message.success('删除员工成功')
            this.getRoomList()
        }
    }
}
</script>

<style lang="less" scoped>

</style>
