<template>
  <div class="container-fluid panel__box py-2">
    <div v-if="dialogs.length != 0">
      <div
      @click="setId(dialog.id)"
        v-for="dialog in dialogs"
        :key="dialog.id"
        :class="'d-flex p-2 align-items-center '+ (id==dialog.id?'activated':'')"
      >
        <a class="avatar" :style="'background: url('+dialog.avatar+'); background-position: center; background-size: cover;'"></a>
        <a class="col">
          <p class="m-0">{{dialog.name}} {{dialog.famile}}</p>
        </a>
        <div class="ml-auto"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  mounted() {
    this.getDialogs();
  },
  data() {
    return {
      id: this.$route.params.id,
      dialogs: []
    };
  },
  methods: {
    setId(id){
      if(id != this.id)
      this.$router.push("/messages/"+id);
    },
    getDialogs() {
      axios.post("/getdiaogs").then(res => {
        // console.log(res.data);
        this.dialogs = res.data;
        if(this.id == undefined && Object.keys(res.data).length != 0)
          this.$router.push("messages/"+res.data[0].id);
      });
    }
  },
  watch:{
    '$route.params.id'(val){
      this.id = val;
    }
  },
};
</script>


<style media="screen" scoped>
.activated {
  border-radius: 5px;
  background: rgba(61, 202, 220, 0.2);
}
.avatar {
  height: 50px;
  width: 50px;
  border-radius: 50px;
}
p {
  font-size: 17px;
  font-weight: 500;
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
  max-height: 300px;
  background: #f4f4f4;
  box-shadow: -4px -4px 10px rgba(255, 255, 255, 0.5),
    2px 2px 10px rgba(163, 177, 198, 0.5);
  border-radius: 5px;
}
</style>
