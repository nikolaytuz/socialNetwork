d<template>
  <div class="container-fluid panel__box py-2">
    
    <div @click="setParam('allfriend')" :class="'d-flex p-2 align-items-center '+(param=='allfriend'?'activated':'')">
      <a class="col">
        <p class="m-0">Мои друзья</p>
      </a>
    </div>
    <div @click="setParam('bestFriend')" :class="'d-flex p-2 pl-4 align-items-center '+(param=='bestFriend'?'activated':'')">
      <a class="col">
        <p class="m-0 sm">- Лучшие друзья</p>
      </a>
    </div>
    <div @click="setParam('relatives')" :class="'d-flex p-2 pl-4 align-items-center '+(param=='relatives'?'activated':'')">
      <a class="col">
        <p class="m-0 sm">- Родственники</p>
      </a>
    </div>
    <div @click="setParam('colleag')" :class="'d-flex p-2 pl-4 align-items-center '+(param=='colleag'?'activated':'')">
      <a class="col">
        <p class="m-0 sm">- Коллеги</p>
      </a>
    </div>
    

    <div @click="setParam('subs')" :class="'d-flex p-2 align-items-center '+(param=='subs'?'activated':'')">
      <a class="col">
        <p class="m-0">Подписчики</p>
      </a>
    </div>
    <div @click="setParam('alluser')" :class="'d-flex p-2 align-items-center '+(param=='alluser'?'activated':'')">
      <a class="col">
        <p class="m-0">Поиск новых</p>
      </a>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  props:["setParam", "param"],
  data() {
    return {};
  },
};
</script>


<style media="screen" scoped>
.sm{
  font-size: 15px;
}
.activated {
  border-radius: 5px;
  background: rgba(61, 202, 220, 0.2);
}
.avatar {
  height: 50px;
  width: 50px;
  border-radius: 50px;
  background: url("/images/gerl1.jpg");
  background-position: center;
  background-size: cover;
}
p {
  font-size: 17px;
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
.panel__box {
  background: #f4f4f4;
  box-shadow: -4px -4px 10px rgba(255, 255, 255, 0.5),
    2px 2px 10px rgba(163, 177, 198, 0.5);
  border-radius: 5px;
}
</style>