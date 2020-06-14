<template id="">
  <div class="container-fluid poln back">
    <div class="row m-0">
      <div class="mx-auto d-flex align-items-stretch align-items-start">
        <div class="left-col">
          <div class="styk">
            <Menu class="m-0" :namePage="namepage" />
          </div>
        </div>

        <div class="right-col">
          <PanelLogo />
          <lentaProfile v-for="post in posts" :post="post" :myprofile="myprofile" :user="profileInfo" :key="post.id" />
        </div>
      </div>
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
    } else this.namepage = "Новости";
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
      iduser: null
    };
  },
  methods: {
    getPosts() {
      axios.post("/getlenta", { iduser: this.iduser }).then(response => {
        this.posts = response.data;
      }).catch();
    },
    getprofile() {
      this.getPosts();
      axios.post("/getprofile", { iduser: this.iduser }).then(response => {
        this.profileInfo = response.data;
      }).catch();
    },
    getMyProfile() {
      axios.post("/getprofile", {}).then(response => {
        this.myprofile = response.data;
      }).catch();
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
