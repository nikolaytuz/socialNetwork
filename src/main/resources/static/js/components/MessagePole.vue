<template>
  <div class="container-fluid panel__box">
    <div class="row p-2 align-items-center justify-content-between px-4 activated">
      <a class="d-flex align-items-center">
        <img src="/images/social.png" height="20px" alt />
        <span class>+</span>
      </a>
      <a class>
        <p class="m-0 max">{{profileInfo.name}} {{profileInfo.famile}}</p>
      </a>
      <a class="avatar" v-if="profileInfo.about"
        :style="'background-image: url('+profileInfo.about.avatar+');  background-position: center;  background-size: cover;'"
      ></a>
    </div>
    <div
      id="block"
      ref="bl"
      class="row white mess__pole align-items-end flex-row scrollbar-deep-blue"
      :style="'height:'+heightPole"
    >
      <div v-if="messages.length != 0" class="col-12 px-4">
        <div v-for="mess in messages" :key="mess.id" class="d-flex mb-3">
          <div
            class="avatar"
            :style="'background-image: url('+mess.userOne.avatar+');  background-position: center;  background-size: cover;'"
          ></div>
          <div class="col">
            <p class="name m-0">{{mess.userOne.name}}</p>
            <p class="mb-1">{{mess.text}}</p>
          </div>
        </div>
        <div ref="end" id="end"></div>
      </div>
    </div>
  </div>
</template>


<script>
import { addHandler } from ".././util/socet";

export default {

  components: {},
  mounted() {
    this.heightPole = document.body.clientHeight - 350 + "px"
    this.getMessages()
    this.getprofile()
  },
  created() {
    addHandler(message => {
      this.getMessages();
    });
  },
  data() {
    return {
      heightPole: "200px",
      messages: [],
      messFormat: [],
      profileInfo: {},
    };
  },
  methods: {
    getMessages() {
      if (this.$route.params.id != undefined) {
        axios
          .post("/getmessage", { iduser: this.$route.params.id })
          .then(res => {
            this.messages = res.data;
            this.scrollend();
          });
      }
    },
    getprofile(){
      axios.post("/getprofile", { iduser: this.$route.params.id }).then(response => {
        // console.log(response.data);
        this.profileInfo = response.data;
      });
    },
    scrollend() {
      setTimeout(()=>{
        this.$refs.bl.scrollTop = this.$refs.bl.scrollHeight;
      }, 100)
    }
  },
  watch: {
    "$route.params.id"(val) {
      this.getMessages();
      this.getprofile();
    }
  }
};
</script>


<style media="screen" scoped>
.mess__pole {
  overflow-y: auto;
  padding: 30px 0;
  border-top: 2px solid rgba(163, 177, 198, 0.5);
}
.max {
  font-size: 19px;
}
.activated {
  border-radius: 5px;
  background: #f4f4f4;
}
.avatar {
  height: 50px;
  width: 50px;
  border-radius: 50px;
}
p {
  font-size: 14px;
}
hr {
  width: 330px;
  border: 1px solid rgba(163, 177, 198, 0.5);
}
span {
  line-height: 10px;
  font-size: 30px;
  color: #15e4ff;
  font-weight: normal;
}
.name {
  color: #00bcd4 !important;
  font-size: 17px;
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
