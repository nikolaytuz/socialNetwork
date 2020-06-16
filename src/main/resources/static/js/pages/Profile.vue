<template id="">
  <div class="container-fluid poln back">
    <div class="row m-0">
      <div class="mx-auto d-flex align-items-stretch align-items-start">
        <div class="left-col">
          <ProfileVidjet :profile="profileInfo" :myprofile="myprofile" :namepage="namepage"></ProfileVidjet>
          <div class="styk">
            <Menu :namePage="namepage" />
          </div>
        </div>

        <div class="right-col">
          <PanelLogo />
          <postsNew v-on:getPosts="getPosts()" v-if="namepage=='Моя страница'" />
          <lentaProfile v-for="post in posts" :post="post" :myprofile="myprofile" :user="profileInfo" :key="post.id" />
        </div>
      </div>
    </div>
    <div class="aler" v-if="aler">
      Вы не подтвердили почту. Поэтому видите данное окно. Пожалуйста подтвердите.
    </div>
  </div>
</template>



<script type="text/javascript">
import ProfileVidjet from "components/profileVidjet.vue";
import Menu from "components/Menu.vue";
import Logo from "components/Logo.vue";
import PanelLogo from "components/PanelLogo.vue";
import postsNew from "components/postsNew.vue";
import lentaProfile from "components/lentaProfile.vue";

export default {
  components: {
    ProfileVidjet,
    Menu,
    Logo,
    PanelLogo,
    postsNew,
    lentaProfile
  },
  created() {
    if (this.$route.params.id) {
      this.iduser = this.$route.params.id;
      this.namepage = "Профиль";
    } else this.namepage = "Моя страница";
  },
  mounted() {
    // this.getprofile();
    this.getMyProfile();
  },
  data() {
    return {
      namepage: "Моя страница",
      profileInfo: {},
      myprofile: {},
      posts: [],
      iduser: null,
      aler: false,
    };
  },
  methods: {
    getPosts() {
      axios.post("/getposts", { iduser: this.iduser }).then(response => {
        this.posts = response.data;
        // console.log(response.data);
      }).catch(err=>{
      });
    },
    getprofile() {
      this.getPosts();
      axios.post("/getprofile", { iduser: this.iduser }).then(response => {
        // console.log(response.data);
        this.profileInfo = response.data;
      }).catch(err=>{
      });
    },
    getMyProfile() {
      axios.post("/getprofile", {}).then(response => {
        // console.log(response.data);
        this.myprofile = response.data;
        if (response.data.activated != null) {
          this.aler = true
        }
      }).catch(err=>{
        // console.log("Ошибка");
      });
    }
  },
  watch: {
    myprofile(newvalue) {
      if (newvalue.id != this.$route.params.id) {
        this.getprofile();
      } else {
        this.profileInfo = this.myprofile;
        this.$router.push("/");
      }
    },
    "$route.params.id"(newid) {

      if (newid) {
        this.iduser = newid;
        this.namepage = "Профиль";
      } else {
        this.iduser = null;
        this.namepage = "Моя страница";
      }
      this.getMyProfile();
    },
    profileInfo(newvalue) {
      if (newvalue.name && !newvalue.about && this.$route.params.id == null) {
        console.log("Тут нет картинки!");
        // this.$router.push("/registration");
      } else if (newvalue.name && !newvalue.about) {
        console.log("У него нет картинки!");
        this.$router.go(-1);
      }
    }
  }
};
</script>


<style media="screen" scoped>
.aler{
  position: fixed;
  bottom: 10px;
  right: 10px;
  width: 98%;
  height: 100px;
  background: pink;
  border-radius: 5px;
  padding: 32px;
  text-align: center;
  font-size: 23px;
}
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
