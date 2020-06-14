<template>
  <div class="container-fluid posfix  menu__box">
    <div class="row py-4">
      <div class="col-12 pl-44">
        <p class="name m-0 p-0">{{namePage}}</p>
      </div>
      <router-link to="/lenta" class="col-12 mt-3 d-flex pl-44">
        <img src="/images/poster.png" height="25" alt />
        <span class="mx-2 p-0">Новости</span>
      </router-link>
      <router-link to="/friends" class="col-12 mt-2 d-flex pl-44">
        <img src="/images/team.png" height="25" alt />
        <span class="mx-2 p-0">Друзья</span>
      </router-link>
      <a href="/#/messages" class="col-12 mt-2 d-flex pl-44">
        <img src="/images/chat.png" height="25" alt />
        <span class="mx-2 p-0">Сообщения</span>
      </a>
      <router-link to="/" class="col-12 mt-2 d-flex pl-44">
        <img src="/images/account.png" height="25" alt />
        <span class="mx-2 p-0">Моя страница</span>
      </router-link>
      <a href="/#/setting" class="col-12 mt-2 d-flex pl-44">
        <img src="/images/cogwheel.png" height="25" alt />
        <span class="mx-2 p-0">Настройки</span>
      </a>
      <hr class="mt-3 mb-2" />
      <a @click="logout()" class="col-12 mt-2 d-flex pl-44">
        <img src="/images/exit.png" height="25" alt />
        <span class="mx-2 p-0">Выход</span>
      </a>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    namePage: {
      type: String,
      default: "Моя страница"
    }
  },
  created() {
    axios.post("/testauth").then(response => {
      if (!response.data.status) {
        this.$router.push("/login");
      }
    });
  },
  methods: {
    logout() {
      axios.post("/logout").then(response => {
        // this.$router.push('login')
        window.location.reload("/");
      });
    }
  },
  data() {
    return {};
  }
};
</script>


<style media="screen" scoped>
a {
  color: #4f5662;
}
a:hover {
  color: #00bcd4;
}
p {
  font-weight: normal;
}
hr {
  width: 330px;
  border: 1px solid rgba(163, 177, 198, 0.5);
}
span {
  font-weight: normal;
}
.name {
  color: #00bcd4 !important;
  font-size: 25px;
  font-weight: normal;
}
.pl-44 {
  padding-left: 35px;
}

.menu__box {
  margin-top: 21px;
  background: #f4f4f4;
  box-shadow: -4px -4px 10px rgba(255, 255, 255, 0.5),
    2px 2px 10px rgba(163, 177, 198, 0.5);
  border-radius: 5px;
}
</style>
