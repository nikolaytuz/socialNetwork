d<template>
  <div class="container-fluid panel__box">
    <div class="row py-2 px-2 align-items-center">
      <input @keyup.enter="sendMess()"  class="col" rows="1" v-model="message" placeholder="Напишите сообщение...">
      <a class="px-2" @click="sendMess()">
        <img src="/images/message.png" class="ml-1" height="20px" alt />
      </a>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  props: {
    getMessage: {
      type: Function
    }
  },
  data() {
    return {
      message: ""
    };
  },
  methods: {
    sendMess() {
      if (this.message && this.$route.params.id )
        axios
          .post("/sendmessage", {
            iduser: this.$route.params.id,
            message: this.message
          })
          .then(res => {
            this.message = "";
            this.getMessage();
          });
    }
  }
};
</script>


<style media="screen" scoped>
input {
  width: 100%;
  outline: none;
  resize: none;
  overflow: hidden;
  /* height: 45px !important; */
  background: #ffffff;
  border: 0px solid #a3b1c6;
  padding: 10px;
  box-sizing: border-box;
  border-radius: 5px;
  padding-right: 70px !important;
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
  margin-top: 21px;
  background: #f4f4f4;
  box-shadow: -4px -4px 10px rgba(255, 255, 255, 0.5),
    2px 2px 10px rgba(163, 177, 198, 0.5);
  border-radius: 5px;
}
</style>