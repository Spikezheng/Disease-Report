<template>
<el-card class="box-card">
    <div class="grid-content bg-purple"><span class="name">健康表格查询</span>
    </div>
    <div class="grid-content-second">
      <el-button
          size="mini"
          @click="renewList"
          >刷新</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="logout"
          >退出</el-button>
    </div>
    <el-table
    :data="list"
    style="width: 100%">
    <el-table-column type="expand">
      <template slot-scope="forms">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="电话号码">
            <span>{{ forms.row.phoneNumber }}</span>
          </el-form-item>
          <el-form-item label="居住地址">
            <span>{{ forms.row.address }}</span>
          </el-form-item>
          <el-form-item label="病情描述">
            <span>{{ forms.row.description }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
      label="姓名"
      prop="name">
    </el-table-column>
    <el-table-column
      label="身份证号"
      prop="idNumber">
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="forms">
        <el-button
          size="mini"
          type="danger"
          @click="onDel(forms.row)"
          >删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</el-card>
</template>

<script>
    export default {
        name: "ShowList",
        data() {
            return {
                list: [],
            }
        },
        methods: {

            //删除无效数据
            onDel(form) {
                this.$axios.post("/hospitalChartDel", {
                    id: form.id,
                    name: form.name,
                    age: form.age,
                    idNumber: form.idNumber,
                    phoneNumber: form.phoneNumber,
                    address: form.address,
                    date: form.date2,
                    description: form.description
                }).then(successResponse => {
                    if (successResponse.data.code === 200) {
                        alert("删除成功!")

                        this.$axios.get("/getCDCCharts"
                        ).then(successResponse => {
                        this.list = successResponse.data;
                        }).catch(failResponse =>{
                    
              })
                    }
                }).catch(failResponse =>{
                    
                })
            },
            
            //获取后台数据
            renewList(){
              this.$axios.get("/getCDCCharts"
              ).then(successResponse => {
                this.list = successResponse.data;
                alert("刷新成功!")
              }).catch(failResponse =>{
                    
              })
            },

            //退出
            logout(){
              this.$options.methods.delCookie("cdc", this);
              this.$router.push({path: '/'});
            },

            //获取cookie
            getCookie(cname) {
                var name = cname + "=";
                var cookie = document.cookie;

                var ca = cookie.split('; ');
                for(var i = 0; i <ca.length; i++) {
                var c = ca[i];
                
                if (c.indexOf(name) == 0) {
                    return c.substring(name.length, c.length);
                    }
                }
                return "";
            },

            //删除cookie
            delCookie(cname, that) {
                var exp = new Date();
                exp.setTime(exp.getTime() - 1);
                // 这里需要判断一下cookie是否存在
                var c = that.$options.methods.getCookie(cname);
                if (c != ""){
                    document.cookie = cname + "=" + c + ";expires=" + exp.toGMTString();
                }
            }

        },

        mounted(){

          //预先加载数据
          this.$axios.get("/getCDCCharts"
              ).then(successResponse => {
                this.list = successResponse.data;
              }).catch(failResponse =>{
                    
              })
        }
    }
</script>

<style scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

  .el-card.box-card.is-always-shadow{
    width: 80%;
    margin: auto;
    margin-bottom: 18vh;
  }

  .grid-content.bg-purple{
    font-size: 25px;
    padding-top: 20px;
    padding-bottom: 20px;
  }

  .grid-content-second{
    padding-bottom: 40px;
  }
</style>
