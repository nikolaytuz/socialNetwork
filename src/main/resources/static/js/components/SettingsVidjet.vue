<template>
  <div v-if="profile.about" class="container-fluid box p-4">
    <div class="row">
      <div class="col">
        <h3>Персональные настроки</h3>
        <hr />
      </div>
    </div>
    <div class="row mb-2">
      <div class="col d-flex justify-content-between">
        <span class="ml-2">Дата рождения:</span>
        <input
          type="date"
          v-model="profile.about.date"
          id="defaultLoginFormEmail"
          class="form-control w-50 mx-3"
          placeholder="15-05-2000"
        />
      </div>
    </div>
    <div class="row mb-2">
      <div class="col d-flex justify-content-between">
        <span class="ml-2">Город:</span>
        <input
          type="text"
          id="sity"
          v-model="profile.about.sity"
          class="form-control w-50 mx-3"
          placeholder="Город"
        />
      </div>
    </div>
    <div class="row mb-2">
      <div class="col d-flex justify-content-between">
        <span class="ml-2">Школа:</span>
        <input
          type="text"
          id="scholl"
          v-model="profile.about.scholl"
          class="form-control w-50 mx-3"
          placeholder="Школа"
        />
      </div>
    </div>
    <div class="row mb-2">
      <div class="col d-flex justify-content-between">
        <span class="ml-2">Дата начала обучения:</span>
        <input
          type="date"
          id="yars"
          v-model="profile.about.date_begin_scholl"
          class="form-control w-50 mx-3"
          placeholder="12-12-2000"
        />
      </div>
    </div>
    <div class="row mb-2">
      <div class="col d-flex justify-content-between">
        <span class="ml-2">Дата окончания школы:</span>
        <input
          type="date"
          id="yars"
          v-model="profile.about.date_close_scholl"
          class="form-control w-50 mx-3"
          placeholder="12-12-2000"
        />
      </div>
    </div>

    <div class="row">
      <div class="col">
        <button @click="setStatist()" class="btn btn-cyan">Сохранить</button>
      </div>
    </div>

    <div class="row mt-4 pt-4">
      <div class="col">
        <h3>Настроки аккаунта</h3>
        <hr />
      </div>
    </div>
    <div class="row mb-2">
      <div class="col d-flex justify-content-between">
        <span class="ml-2">Старый пароль:</span>
        <input
          v-model="param.oldpassword"
          type="text"
          id="password"
          class="form-control w-50 mx-3"
          placeholder="старый пароль"
        />
      </div>
    </div>
    <div class="row mb-2">
      <div class="col d-flex justify-content-between">
        <span class="ml-2">Новый пароль:</span>
        <input
          type="text"
          id="newpassword"
          v-model="param.newpassword"
          class="form-control w-50 mx-3"
          placeholder="новый пароль"
        />
      </div>
    </div>
    <div class="row">
      <div class="col">
        <button @click="newPassword()" class="btn btn-cyan">Изменить</button>
      </div>
    </div>


  </div>
</template>

<script>
export default {
  mounted() {
    this.getProfile();
  },
  data() {
    return {
      profile: {},
      param: {}
    };
  },
  methods: {
    getProfile() {
      axios.post("/getprofile", {}).then(res => {
        this.profile = res.data;
      });
    },
    setStatist() {
      axios.post("/setstatic", this.profile.about).then(res => {
        this.getProfile();
      });
    },
    newPassword() {
      if (this.param.oldpassword && this.param.newpassword) {
        axios.post("/newpassword", this.param).then(res => {
          this.param = {};
          // console.log(res.data);
        });
      }
    }
  }
};
</script>

<style scoped>
span {
  font-weight: normal;
}
.box {
  margin-top: 23px;
  background: #f4f4f4;
  box-shadow: -4px -4px 10px rgba(255, 255, 255, 0.5),
    2px 2px 10px rgba(163, 177, 198, 0.5);
  border-radius: 5px;
}
</style>
