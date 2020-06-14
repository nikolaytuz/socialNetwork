<template id="">
  <div class="container-fluid poln back">
    <Logo position="1" />
    <div class="row">
      <div class="col heading text-center">
        <p>восстановление пароля</p>
      </div>
    </div>
    <div class="row">
      <div class="col-4 login__box mx-auto text-center px-0 pb-5">
        <div class="pt-3 mb-4 pb-3 poys">
          <p class="m-0">Введите логин, и ссылка на изменение пароля придет вам на почту.</p>
        </div>
        <div v-if="error==1" class="w-75 mx-auto login__box py-3 pink lighten-4 mt-2">
          <p v-if="error==1" class="m-0">Введите правильные данные!</p>
        </div>
        <div v-if="sucs" class="w-75 mx-auto login__box p-3 green accent-1 lighten-4 mt-2">
          <p v-if="sucs" class="m-0">На вашу почту отправленна ссылка для сброса пароля!</p>
        </div>
        <div v-if="error==2" class="w-75 mx-auto login__box py-3 rgba-yellow-light mt-2">
          <p v-if="error==2" class="m-0">Заполните все поля!</p>
        </div>



        <div class>
          <input
            type="text"
            autofocus
            class="w-75 login__input text-center p-2"
            v-model="param.username"
            placeholder="Login"
          />
        </div>
        <div class="container w-75">
          <div class="mt-5 row">
            <div class="col-5 pr-2">
              <a href="/#/login" class="login__button py-2 w-100 waves-effect waves-light">Назад</a>
            </div>
            <div class="col-7 pl-2">
              <a
                v-if="!dis"
                class="login__button py-2 w-100 waves-effect waves-light"
                @click="changepass()"
              >Восстановить</a>
              <a v-if="dis" class="login__button py-2 w-100 waves-effect waves-light">Ждите...</a>
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
  mounted() {
    axios.post("/testauth").then(response => {
      if (response.data.status) {
        this.$router.push("/");
      }
    }).catch();
  },
  data() {
    return {
      error: false,
      param: {},
      dis: false,
      sucs: false
    };
  },
  methods: {
    changepass() {
      if (this.param.username) {
        this.dis = true;
        axios.post("/changepassword", this.param).then(response => {
          if (response.data.status) {
            this.dis = false;
            this.sucs = true;
            this.error = false;
          } else {
            this.dis = false;
            this.error = 1;
          }
        }).catch();
      } else {
        this.dis = false;

        this.error = 2;
      }
    }
  }
};
</script>


<style scoped>
.poys {
  background: #4f5662;
  color: #e3f8fa;
  border-radius: 5px 5px 0px 0px;
  font-weight: normal;
  font-size: 20px;
}
.py-6 {
  padding: 70px 0px;
}
a:active {
  color: #fff;
  background: #26c6da;
}
.login__button {
  outline: none;
  font-size: 25px;
  font-weight: normal;
  color: #e3f8fa !important;
  background: #4f5662;
  border-radius: 10px;
  border: 1px solid #e1e6ec;
  box-shadow: 2px 2px 6px rgba(163, 177, 198, 0.5),
    -2px -2px 6px rgba(255, 255, 255, 0.5);
}
.login__input {
  background: #ffffff;
  margin-top: 30px;
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
