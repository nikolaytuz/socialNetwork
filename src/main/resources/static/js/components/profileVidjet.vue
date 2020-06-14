<template>
  <div v-if="profile.about" class="container-fluid profile__box">
    <div class="row">
      <div class="col">
        <div>
          <img :src="profile.about.avatar" class="profile__img" alt />
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col pl-44 mt-4">
        <p class="name m-0">{{profile.name}} {{profile.famile}}</p>
        <p v-if="profile.about.scholl" class="m-0 profile_data mt-1">
          Школа:
          <b>{{profile.about.scholl}}</b>
        </p>
        <p v-if="profile.about.date_close_scholl" class="m-0 profile_data">
          Дата окончания:
          <b>{{profile.about.date_close_scholl}}</b>
        </p>
         <p v-if="profile.about.sity" class="m-0 profile_data">
          Город:
          <b>{{profile.about.sity}}</b>
        </p>
         <p v-if="profile.about.date" class="m-0 profile_data">
          Дата родждения:
          <b>{{profile.about.date}}</b>
        </p>

      </div>
    </div>
    <hr />
    <div class="row" style="box-shadow: 0px 3px 7px -4px #00000050;">
      <div class="col-4 text-center profile-stat">
        <span class>{{profile.about.friend_count}}</span>
        <p>Друзья</p>
      </div>
      <div class="col-4 text-center profile-stat">
        <span class>{{profile.about.subscribe_count}}</span>
        <p>Подписчики</p>
      </div>
      <div class="col-4 text-center profile-stat">
        <span class>{{profile.about.image_count}}</span>
        <p>Фотографии</p>
      </div>
    </div>
    <div class="row bggr py-3" v-if="namepage == 'Профиль'">
      <div class="col-12 d-flex justify-content-between">
        <a @click="addfriend()" v-if="!itfriend" class="w-50 text-center text-white rounded butt mr-2">Отправить заявку</a>
        <a @click="addfriend()" v-if="itfriend=='subsyou'" class="w-50 text-center text-white rounded butt mr-2">Добавить в друзья</a>
        <a @click="dellfriend()" v-if="itfriend == 'subs'" class="w-50 text-center text-white rounded butt mr-2">Отменить запрос</a>
        <a @click="dellfriend()" v-if="(itfriend != 'subs') && (itfriend != 'subsyou') && (itfriend)" class="w-50 text-center text-white rounded butt mr-2">Удалить из друзей</a>
        <router-link :to="'messages/'+profile.id" class="w-50 text-center text-white rounded btsend butt ml-2">Написать</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    profile: {
      type: Object
    },
    myprofile: {
      type: Object
    },
    namepage: {
      type: String
    }
  },
  mounted() {},
  data() {
    return {
      itfriend: false
    };
  },
  methods: {
    itsFriend() {
      axios.post("/itsfriend", { iduser: this.profile.id }).then(res => {
        this.itfriend = res.data.status;
      }).catch(err=>{});;
    },
    addfriend() {
      axios.post("/addfriend", { iduser: this.profile.id }).then(res => {
        this.itsFriend();
      }).catch(err=>{});;
    },
    dellfriend() {
      axios.post("/dellfriend", { iduser: this.profile.id }).then(res => {
        this.itsFriend();
      }).catch(err=>{});;
    }
  },
  watch: {
    profile(newvalue) {
      if (newvalue != null && newvalue.id != this.myprofile.id) {
        this.itsFriend();
      }
    }
  }
};
</script>


<style media="screen" scoped>
.bggr {
  background: #00dcff1a;
}
.btsend {
  background: #26cbe2;
}
.butt {
  border: none;
  background: #00bcd4;
  font-weight: normal;
  padding: 10px 0;
  box-shadow: 0 0 2px #0000001a;
}
.profile-stat {
  color: #00bcd4;
  font-size: 25px;
  font-weight: normal;
}
.profile-stat > p {
  color: rgba(79, 86, 98, 0.75);
  font-size: 13px;
  margin-top: -10px;
}
.pl-44 {
  padding-left: 35px;
}
hr {
  border: 1px solid rgba(163, 177, 198, 0.5);
}
.profile_data {
  font-weight: normal;
  font-size: 15px;
  color: #4f5662;
}
.profile_data > b {
  color: black;
  margin-left: 10px;
}
p {
  font-weight: 500;
}
.name {
  color: #393e48 !important;
  font-size: 25px;
  font-weight: 500;
}
.profile__img {
  margin-top: 20px;
  width: 360px;
  box-shadow: 0px 0px 6px rgba(0, 0, 0, 0.28);
  margin-left: -40px;
  border-radius: 5px;
}
.profile__box {
  background: #f4f4f4;
  box-shadow: -4px -4px 10px rgba(255, 255, 255, 0.5),
    2px 2px 10px rgba(163, 177, 198, 0.5);
  border-radius: 5px;
}
</style>
