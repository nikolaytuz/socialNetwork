<template id="">
  <div class="container-fluid poln back">
    <Logo position="1" />
    <div class="row">
      <div class="col heading text-center">
        <p>Регистрация</p>
      </div>
    </div>
    <div class="row pb-4">
      <div class="col-4 login__box mx-auto text-center py-6">
        <div class="container w-75">
          <div class="row">
            <div class="col-4 px-0 pr-3">
              <a
                :class="'login__button py-2 w-100 waves-effect waves-light '+ (page==1?'active':'')"
              >1</a>
            </div>
            <div class="col-4 px-2">
              <a
                :class="'login__button py-2 w-100 waves-effect waves-light '+ (page==2?'active':'')"
              >2</a>
            </div>
            <div class="col-4 px-0 pl-3">
              <a
                :class="'login__button py-2 w-100 waves-effect waves-light '+ (page==3?'active':'')"
              >3</a>
            </div>
          </div>
        </div>

        <div v-if="page==1">
          <div class="mt-4">
            <input
              type="email"
              v-model="param.email"
              class="w-75 login__input back text-center p-2"
              placeholder="email"
            />
          </div>
          <div class="mt-4">
            <input
              type="text"
              v-model="param.username"
              class="w-75 login__input back text-center p-2"
              placeholder="username"
            />
          </div>
          <div class="mt-4">
            <input
              type="text"
              v-model="param.password"
              class="w-75 login__input back text-center p-2"
              placeholder="password"
            />
          </div>
          <div class="mt-4 h52"></div>
        </div>

        <div v-if="page==2">
          <div class="mt-4">
            <input
              type="text"
              v-model="param.name"
              class="w-75 login__input back text-center p-2"
              placeholder="Имя"
            />
          </div>
          <div class="mt-4">
            <input
              type="text"
              v-model="param.famile"
              class="w-75 login__input back text-center p-2"
              placeholder="Фамилия"
            />
          </div>
          <div class="mt-4">
            <input
              type="text"
              v-model="param2.scholl"
              class="w-75 login__input back text-center p-2"
              placeholder="Школа"
            />
          </div>
          <div class="mt-4">
            <input
              type="text"
              v-model="param2.sity"
              class="w-75 login__input back text-center p-2"
              placeholder="Город"
            />
          </div>
        </div>

        <div v-if="page==3">
          <div class="mt-4">
            <div class="login__img mx-auto">
              <img v-if="!param2.avatar" src="/images/dog.png" class="w-50 my-2" alt />
              <!-- <Avatar v-if="param.image" :image="param.image"/> -->
              <img v-if="param2.avatar" :src="'..'+param2.avatar" class="w-100 rounded" alt="">
            </div>
          </div>
          <div class="mt-4">
            <div class="custom-file w-75">
              <input
                type="file"
                class="custom-file-input"
                id="file"
                ref="file"
                @change="handleFileUpload()"
                lang="ru"
              />
              <label
                class="custom-file-label login__button text-left back"
                for="customFileLang"
              >Загрузить аватарку</label>
            </div>
          </div>
        </div>

        <div class="container w-75">
          <div class="mt-5 row">
            <div class="col-6 px-0 pr-3">
              <a href="/#/login" class="login__button py-2 w-100 waves-effect waves-light">Войти</a>
            </div>
            <div class="col-6 px-0 pl-3">
              <a
                @click="next()"
                class="login__button py-2 w-100 waves-effect waves-light"
              >{{disabled?"Ждите...":"Далее"}}</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>





<script type="text/javascript">
import Logo from "components/Logo.vue";
import { log } from "util";

export default {
  components: {
    Logo,
  },
  mounted(){
    axios.post("/testauth").then((response)=>{
        if(response.data.status){
          console.log("Залогиннен");

          this.$router.push('/')
        }
      }).catch()
  },
  data() {
    return {
      page:1,
      param: {
        username: '',
      },
      param2:{
        avatar: false,
      },
      disabled: false,
      file: ""
    };
  },
  methods: {
    handleFileUpload: function() {
      let formData = new FormData();
      formData.append("file", this.$refs.file.files[0]);
      // console.log(formData);
      this.file = this.$refs.file.files[0];
      axios.post( '/uploudhoto',
                formData,
                {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
              }
            ).then((response)=>{
              this.sleep(500).then(() => {
                this.param2.avatar = response.data.url
              });
              // console.log(response.data);
            }).catch((error)=>{
              alert("Возможно вы пытаетесь загрузить слишком большое изображение.");
            })
    },
    sleep (time) {
      return new Promise((resolve) => setTimeout(resolve, time));
    },
    next() {
      if(this.page == 3 && this.param2.avatar){
         this.newsettings()
      }
      if (this.page == 2 && !this.disabled && this.param.username != null && this.param.password != null && this.param.email != null && this.param.name != null && this.param.famile != null && this.param2.scholl != null && this.param2.sity != null) {
        // axios.post("/test", this.param).then((response)=>{console.log(response.data)})
        if(!this.validEmail(this.param.email)){
          this.page = 1
          alert("Введите правильную почту!")
        }else{
          this.disabled = true
          this.newuser()
        }

      }
      if (this.page == 1 && this.param.username != null && this.param.password != null && this.param.email != null) {
        this.page = 2;
      }

    },
    newuser(){
        axios.post("/newuser", this.param).then((response)=>{
            // console.log(response);
            if(!response.data.done){
              alert("Пользователь с таким username уже существует!")
              this.page = 1;
              this.disabled = false
            }else{
              this.sendEmail()
              this.login()
            }
            }).catch((error)=>{
              // console.log(error);
            })
    },
    sendEmail(){
      axios.post("/sendemail", {username: this.param.username}).then((response)=>{
        // console.log(response.data)
      }).catch((error)=>{
        console.log("Активация не отправлена на почту. Измените почтовый сервис в properties!");
      })
    },
    login(){
      axios.post("/loginrest", {username: this.param.username, password: this.param.password}).then((response)=>{
        console.log("Вы залогинены!");

        this.page = 3;
        this.disabled = false;
        // console.log(response.data);



      })
    },
    newsettings(){
        axios.post("/newStatic", this.param2).then((response)=>{
          this.page = 3;
          this.disabled = false;
          this.$router.push('/')
      })
    },
    validEmail: function (email) {
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    }
  }
};
</script>


<style scoped>
.h52 {
  height: 52px;
}
.py-6 {
  padding: 50px 0px;
}
.active {
  color: #fff !important;
  background: #26c6da !important;
}
input:focus {
  background: #ffffff78;
}
a:active {
  color: #fff !important;
  background: #26c6da;
}

.login__button {
  outline: none;
  font-size: 23px;
  font-weight: normal;
  color: #4f5662;
  background: #e1e6ec;
  border-radius: 5px;
  border: 1px solid #e1e6ec;
  box-shadow: 2px 2px 6px rgba(163, 177, 198, 0.5),
    -2px -2px 6px rgba(255, 255, 255, 0.5);
}
.login__input {
  font-size: 23px;
  outline: none;
  border-radius: 5px;
  border: 1px solid #e1e6ec;
  box-shadow: inset 2px 2px 8px rgba(163, 177, 198, 0.5),
    inset -2px -2px 8px rgba(255, 255, 255, 0.5);
}
.login__img {
  width: 80%;
  min-width: 400px;
  min-height: 218px;
  border-radius: 5px;
  border: 1px solid #e1e6ec;
  box-shadow: inset 2px 2px 8px rgba(163, 177, 198, 0.5),
    inset -2px -2px 8px rgba(255, 255, 255, 0.5);
}
.login__newpass {
  color: #4f5662;
  font-size: 23px;
}
.login__box {
  /* min-height: 570px; */
  min-width: 500px;
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
