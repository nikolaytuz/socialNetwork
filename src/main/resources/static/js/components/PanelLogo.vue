d<template>
  <div class="container-fluid panel__box">
    <div class="row py-3 px-4 align-items-center">
      <div class>
        <Logo position="0" />
      </div>
      <div class="ml-auto">
        <a>
          <img src="/images/bell.png" height="30px" alt />
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import Logo from "components/Logo.vue";

export default {
  components: {
    Logo
  },
  data() {
    return {};
  }
};
</script>


<style media="screen" scoped>
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
.panel__box {
  background: #f4f4f4;
  box-shadow: -4px -4px 10px rgba(255, 255, 255, 0.5),
    2px 2px 10px rgba(163, 177, 198, 0.5);
  border-radius: 5px;
}
</style>