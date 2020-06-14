d<template>
  <div class="container-fluid panel__box">
    <div class="row" v-if="post.image != null">
      <div class="col-12 p-0">
        <img
          v-for="(image, index) in post.image"
          :key="image.id"
          :src="image"
          :class="'w-100 '+ (index!=0?'mt-2':'')"
          alt
        />
      </div>
    </div>
    <div class="row" v-if="post.text">
      <div class="col pt-3">
        <p class="text-post">{{post.text}}</p>
      </div>
    </div>
    <div class="row">
      <hr class="w-100 my-0" />
    </div>
    <div class="row pt-3 pb-2 grcolor pl-4 pr-2 align-items-center">
      <a
        v-if="user.about"
        class="avatar"
        :style="' background-image: url('+user.about.avatar+'); background-position: center;  background-size: cover;'"
      ></a>
      <div class="col comentariy">
        <textarea
          v-model="commentInp"
          name
          id
          cols="1"
          class="w-100 comentariy__innput form-control"
          placeholder="Ваш коментарий..."
        ></textarea>
        <div class="comentariy__send my-auto">
          <a @click="newComment()">
            <img src="/images/message.png" height="30px" alt />
          </a>
        </div>
      </div>
    </div>

    <div v-if="comentShow">
      <div v-for="comment in coments" :key="comment.id" class="row grcolor justify-content-end pb-3">
        <a
          v-if="user.about"
          class="avatar-s"
          :style="' background-image: url('+comment.user.about.avatar+'); background-position: center;  background-size: cover;'"
        ></a>
        <div class="col-9 comentpole ml-2 mr-5">
          <p class="m-0">{{comment.user.name}} {{comment.user.famile}}</p>
          <p class="normal m-0">{{comment.text}}</p>
        </div>
      </div>
    </div>

    <div class="d-flex row px-4 grcolor justify-content-between pb-3">
      <div class>
        <a @click="putlike()" v-if="!alreadyLike">
          <img src="/images/heart.png" class="icon" alt />
        </a>
        <a @click="dellike()" v-if="alreadyLike">
          <img src="/images/heart_like.png" class="icon" alt />
        </a>
        <span>{{post.likes}}</span>
        <a class="ml-2" v-if="!comentShow" @click="showComment()">
          <img src="/images/interface.png" class="icon" alt />
        </a>
          <a class="ml-2" v-if="comentShow" @click="comentShow = !comentShow">
          <img src="/images/interface.png" class="icon" alt />
        </a>
        <span>{{post.comments}}</span>
      </div>
      <div class="d-flex align-items-center">
        <a
          v-if="user.about"
          class="avatar-m"
          :style="' background: url('+post.user.avatar+'); background-position: center;  background-size: cover;'"
        ></a>
        <p class="p-0 ml-1 m-0"> {{post.user.name}} {{post.user.famile}}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  props: {
    user: {
      type: Object
    },
    myprofile: {
      type: Object
    },
    post: {
      type: Object
    }
  },
  mounted() {
    this.alrlike();
  },
  data() {
    return {
      alreadyLike: null,
      comentShow: false,
      coments: [],
      commentInp: "",
      inpdis: false
    };
  },
  methods: {
    putlike() {
      axios.post("/putlike", { idpost: this.post.id }).then(res => {
        // console.log(res.data);
        this.alreadyLike = true;
        if (res.data.status) this.post.likes += 1;
      });
    },
    dellike() {
      axios.post("/putlike", { idpost: this.post.id }).then(res => {
        // console.log(res.data);
        this.alreadyLike = false;
        this.post.likes -= 1;
      });
    },
    alrlike() {
      this.post.alreadyLike[this.myprofile.id] == true
        ? (this.alreadyLike = true)
        : (this.alreadyLike = false);
    },
    showComment() {
      axios.post("/getcoments", { idpost: this.post.id }).then(res => {
        this.post.comments = res.data.length
        // console.log(res.data);
        this.coments = res.data;
        this.comentShow = true;
      });
    },
    newComment() {
      if (this.commentInp && !this.inpdis) {
        this.inpdis = true;
        axios
          .post("/newcomment", { idpost: this.post.id, text: this.commentInp })
          .then(res => {
            this.commentInp = "";
            this.showComment();
            // console.log(res.data);
            this.inpdis = false;
          })
          .catch(err => {
            // console.log(err);
            this.inpdis = false;
          });
      }
    }
  },
  watch: {
    post(value) {
      this.alrlike();
    },
    myprofile(value) {
      this.alrlike();
    }
  }
};
</script>


<style media="screen" scoped>
.comentpole {
  background: white;
  padding: 10px;
  border-radius: 5px;
}
.grcolor {
  background: #f4f4f4;
}
.text-post {
  font-size: 23px;
  color: 2f3033;
}
.icon {
  height: 25px;
}
.comentariy {
  position: relative;
}
.comentariy__send {
  /* height: 70%; */
  padding: 5px 5px;
  top: 50%;
  transform: translate(-50%, -50%);
  right: -10px;
  border-left: 1px solid #a3b1c6a6;
  /* background: black; */
  position: absolute;
  border-radius: 0px 10px 10px 0;
  position: absolute;
}
.comentariy__innput {
  outline: none;
  resize: none;
  overflow: hidden;
  padding: 12px;
  height: 50px !important;
  background: #ffffff;
  border: 1px solid #a3b1c6;
  box-sizing: border-box;
  border-radius: 15px;
  padding-right: 70px !important;
}
.avatar-m {
  height: 30px;
  width: 30px;
  border-radius: 50px;
}
.avatar-s {
  height: 40px;
  width: 40px;
  border-radius: 50px;
}
.avatar {
  height: 50px;
  width: 50px;
  border-radius: 50px;
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
.normal {
  font-weight: 300;
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
  background: #fff;
  box-shadow: -4px -4px 10px rgba(255, 255, 255, 0.5),
    2px 2px 10px rgba(163, 177, 198, 0.5);
  border-radius: 5px;
}
</style>
