<template>
  <div :class="'d-flex '+ (position==1?'logo__box':'')">
    <div class="logo text-center d-flex justify-content-center">
      <span class="logo__text align-self-center">
        D<span style="color:#8CE1EB;">R</span>
      </span>
    </div>
    <div class="ml-3 align-self-center">
      <p class="m-0 logo__name">Социальная сеть</p>
    </div>
  </div>
</template>


<style scoped>
.logo {
  height: 50px;
  width: 50px;
  border-radius: 5px;
  background: #e3f8fa;
}
.logo__text {
  color: #26c6da;
  font-size: 25px;
  font-weight: 500;
}
.logo__name {
  font-size: 25px;
  color: #26c6da;
  font-weight: normal;
}
.logo__box {
  position: absolute;
  top: 30px;
  left: 30px;
}
</style>



  
<script type="text/javascript">
export default {
  props:{
    position:{
      type: String,
      default: 0,
    },
  },
  data() {
    return {};
  }
  
};
</script>