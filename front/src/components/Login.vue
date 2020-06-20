<template>
    <el-card class="box-card">
        <el-row :gutter="20">
            <el-col :span="16"><div class="grid-content bg-purple"><span class="name">用户登录</span></div></el-col>
            <el-col :span="8">
                <div class="grid-content bg-purple">
                    <el-select v-model="user.userType" placeholder="选择用户类型">
                    <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                    </el-select>
                </div>
            </el-col>
        </el-row>
        <el-form :label-position="labelPosition" label-width="80px" :model="user">
            <el-form-item label="用户名">
                <el-input v-model="user.name" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="user.password" type="password" placeholder="请输入密码"></el-input>
            </el-form-item>
        </el-form>
        <el-button type="primary" plain @click="login">登录</el-button>
        <router-view/>
    </el-card>
</template>

<script>
    export default {
        name: "Login",
        data(){
            return {
                labelPosition: "left",
                user:{
                    name: "",
                    password: "",
                    userType: null,
                },
                options:[
                    {label: "普通用户", value: 1},
                    {label: "医生用户", value: 2},
                    {label: "市级卫健用户", value: 3},
                    {label: "省级卫健用户", value: 4},
                    {label: "CDC用户", value: 5}
                ]
            }
        },
        methods:{
            //登录函数
            login(){
                var type = this.user.userType;
                if(type == null)
                    return;
                
                var url = "";
                var path = "";
                var usertype = "";
                if(type == 1){
                    url = "/commonLogin";
                    path = "/common";
                    usertype = "common";
                }
                else if(type == 2){
                    url = "/hospitalLogin";
                    path = "/showlist";
                    usertype = "hospital";
                }
                else if(type == 3){
                    url = "/commonLogin";
                    path = "/common";
                    usertype = "common";
                }
                else if(type == 4){
                    url = "/commonLogin";
                    path = "/common";
                    usertype = "common";
                }
                else if(type == 5){
                    url = "/cdcLogin";
                    path = "/showcdclist";
                    usertype = "cdc";
                }

                this.$axios.post(url,{
                    name: this.user.name,
                    password: this.user.password
                }).then(successResponse => {
                    if (successResponse.data.code === 200) {
                        this.$options.methods.setCookie(usertype, "true", 1);
                        this.$router.push({path: path});
                    }else{
                        alert("登录失败！请重新登录")
                    }
                }).catch(failResponse =>{
                    alert("登录失败！请重新登录")
                })
            },

            //设置cookie
            setCookie(cname, cvalue, exdays) {
                var d = new Date();
                d.setTime(d.getTime() + (exdays*24*60*60*1000));
                var expires = "expires="+ d.toGMTString();
                document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
            },

        }
    }
</script>

<style scoped>
.name{
    font-size: 1.5rem;
    font-weight: bold;
}

.el-card.box-card.is-always-shadow{
    width: 500px;
    height: 300px;
    margin: auto;
}

.el-input.el-input--suffix{
    width: 80px;
}

.el-row {
    margin-bottom: 20px;
  }

.el-col {
    border-radius: 4px;
  }
</style>
