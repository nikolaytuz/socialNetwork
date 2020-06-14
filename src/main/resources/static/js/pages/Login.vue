<template id="">
  <div class="container-fluid poln back">
    <Logo position=1 />
    <div class="row">
      <div class="col heading text-center">
        <p>Авторизация</p>
      </div>
    </div>
    <div class="row">
      <div class="col-4 login__box mx-auto text-center py-5">
        <div v-if="error==1" class="w-75 mx-auto login__box py-3 pink lighten-4 mb-4">
          <p v-if="error==1" class="m-0">Введите правильные данные!</p>

        </div>
        <div v-if="error==2" class="w-75  mx-auto login__box py-3 rgba-yellow-light mb-4">
          <p v-if="error==2" class="m-0">Заполните все поля!</p>
        </div>
        <div class="">
          <input type="text" autofocus class="w-75 login__input back text-center p-2" v-model="param.username" placeholder="Login" />
        </div>
        <div class="mt-4">
          <input type="text" class="w-75 login__input back text-center p-2" v-model="param.password"  placeholder="Password" />
        </div>
        <div class="mt-4">
          <router-link to="/changepass" class="login__newpass">
            <b>Забыли пароль?</b>
          </router-link>
        </div>
        <div class="container w-75">
          <div class="mt-5 row">
            <div class="col-7 pr-2">
              <a href="/#/registration" class="login__button py-2 w-100 waves-effect waves-light ">Регистрация</a>
            </div>
            <div class="col-5 pl-2">
              <a class="login__button py-2 w-100 waves-effect waves-light" @click="login">Войти</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>





<script type="text/javascript">
import Logo from "components/Logo.vue";

export default {
  components: {
    Logo
  },
  mounted(){
     axios.post("/testauth").then((response)=>{
        if(response.data.status){
          this.$router.push('/')
        }
      }).catch()
  },
  data() {
    return {
      error: false,
      param: {},

    };
  },
  methods:{
    login(){
      if(this.param.username && this.param.password){
        axios.post("/loginrest", this.param).then((response)=>{
          // console.log(response.data);
          if (response.data.status) {
            this.$router.push('/')
          }else{
            this.error = 1;
          }
          ;
        }).catch()
      }else{
        this.error = 2;
      }
    }
  },
};
</script>


<style scoped>
.py-6{
  padding: 70px 0px;
}
a:active{
  color: #fff;
  background: #26C6DA;
}
.login__button {
  outline: none;
  font-size: 25px;
  font-weight: normal;
  color: #4f5662!important;
  background: #e1e6ec;
  border-radius: 10px;
  border: 1px solid #e1e6ec;
  box-shadow: 2px 2px 6px rgba(163, 177, 198, 0.5),
    -2px -2px 6px rgba(255, 255, 255, 0.5);
}
.login__input {
  font-size: 25px;
  outline: none;
  border-radius: 5px;
  border: 1px solid #e1e6ec;
  box-shadow: inset 2px 2px 8px rgba(163, 177, 198, 0.5),
    inset -2px -2px 8px rgba(255, 255, 255, 0.5);
}
.login__newpass {
  color: #4f5662;
  font-size: 25px;
}
.login__box {
  border-radius: 5px;
  box-shadow: -6px -6px 10px rgba(255, 255, 255, 0.5),
    2px 2px 16px rgba(163, 177, 198, 0.5);
}
.poln {
  min-height: 100vh;
}
.back {
  background: #e1e6ec;
}
.heading {
  margin-top: 70px;
  font-style: normal;
  font-weight: 700;
  font-size: 80px;
  color: #4f5662;
}
</style>
