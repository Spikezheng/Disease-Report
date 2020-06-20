<template>
    <el-card class="box-card">
        <div class="grid-content bg-purple"><span class="name">健康表格填写</span></div>
        <el-form ref="form" :model="form" label-width="100px">
            <el-form-item label="姓名">
                <el-input v-model="form.name"></el-input>
            </el-form-item>

            <el-form-item label="年龄">
                <el-input v-model="form.age"></el-input>
            </el-form-item>

            <el-form-item label="身份证号">
                <el-input v-model="form.idNumber"></el-input>
            </el-form-item>

            <el-form-item label="手机号码">
                <el-input v-model="form.phoneNumber"></el-input>
            </el-form-item>

            <el-form-item label="详细居住地址">
                <el-input v-model="form.address"></el-input>
            </el-form-item>

            <el-form-item label="填表时间">
                <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                    <el-col :span="11">
                    <el-time-picker type="fixed-time" placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
                </el-col>
            </el-form-item>

            <el-form-item label="病情症状描述">
                <el-input type="textarea" v-model="form.description"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="onSubmit">提交</el-button>
                <el-button @click="onCancel">取消</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script>
    export default {
        name: "Common",
        data() {
            return {
                form: {
                    name: '',
                    age: '',
                    idNumber:'',
                    phoneNumber: '',
                    address:'',
                    date1: '',
                    date2: '',
                    description: ''
                }
            }
        },
        methods: {

            //提交病情表格
            onSubmit() {
                this.$axios.post("/chartSubmit", {
                    name: this.form.name,
                    age: this.form.age,
                    idNumber: this.form.idNumber,
                    phoneNumber: this.form.phoneNumber,
                    address: this.form.address,
                    date: this.form.date2,
                    description: this.form.description
                }).then(successResponse => {
                    if (successResponse.data.code === 200) {
                        alert("表格提交成功!")

                        this.$options.methods.delCookie('common', this);
                        this.$router.push({path: '/'});
                    }
                }).catch(failResponse =>{
                    
                })
            },

            //退出取消
            onCancel(){
                alert("退出");

                this.$options.methods.delCookie('common', this);
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
        }
    }
</script>

<style scoped>
.el-card.box-card.is-always-shadow{
    width: 700px;
    margin: auto;
    margin-bottom: 18vh;
}

.el-form{
    width: 600px;
    margin: auto;
}

.grid-content.bg-purple{
    font-size: 25px;
    padding: 20px;
}
</style>
