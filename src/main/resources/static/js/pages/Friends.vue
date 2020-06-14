<template id="">
  <div class="container-fluid poln back">
    <div class="row m-0">
      <div class="mx-auto d-flex align-items-stretch align-items-start">
        <div class="left-col">
          <FriendsUI :setParam="setParam" :param="param" />
          <div class="styk">
            <Menu namePage="Друзья" />
          </div>
        </div>

        <div class="right-col">
          <PanelLogo />
          <FriendsList  :friendList="friendList" :getFriends="friendsNow" :param="param" />
        </div>
      </div>
    </div>
  </div>
</template>



<script type="text/javascript">
import MessageInput from "components/MessageInput.vue";
import Menu from "components/Menu.vue";
import Logo from "components/Logo.vue";
import PanelLogo from "components/PanelLogo.vue";
import FriendsUI from "components/FriendsUI.vue";
import FriendsList from "components/FriendsList.vue";

export default {
  components: {
    MessageInput,
    Menu,
    Logo,
    PanelLogo,
    FriendsUI,
    FriendsList
  },
  mounted() {
    this.getFriends();
    this.getProfile();
  },
  data() {
    return {
      param: "allfriend",
      profile: {},
      friendList: {}
    };
  },
  methods: {
    setParam(param) {
      this.param = param;
    },
    getFriends() {
      axios.post("/getfriends", {}).then(res => {
        this.friendList = res.data;
        // console.log(res.data);
      }).catch();
    },
    getParamFriends() {
      axios.post("/getparamfriend", { status: this.param }).then(res => {
        this.friendList = res.data;
        // console.log(res.data);
      }).catch();
    },
    getAllUser() {
      axios.post("/getalluser", {}).then(res => {
        // console.log(res.data);
        this.friendList = res.data;
      }).catch();
    },
    getProfile() {
      axios.post("/getprofile", {}).then(res => {
        // console.log(res.data);
      }).catch();
    },
    friendsNow(){
      if(this.param == "allfriend") this.getFriends()
      else if(this.param != "alluser" && this.param != "allfriend") this.getParamFriends()
      else this.getAllUser()
    },
  },
  watch: {
    param(newcount){
      this.friendsNow()
    }
  }
};
</script>


<style media="screen" scoped>
.styk {
  position: sticky;
  top: 13px;
}
.poln {
  padding: 13px;
  min-height: 100vh;
}
.back {
  background: #e1e6ec;
}
.left-col {
  width: 360px;
}
.right-col {
  margin-left: 21px;
  width: 800px;
}
</style>
