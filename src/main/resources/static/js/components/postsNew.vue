<template>
  <div class="container-fluid panel__box" :style="(showInput?'background: white;':'')" ref="postInput">
    <div class="row" v-if="param.image">
      <img
        v-for="img in param.image"
        :key="img.id"
        :src="img"
        class="flex-item col p-1 imgSave"
        alt
      />
    </div>
    <div :class="'row py-2 px-4 '+(!showInput?'align-items-center':'')">
      <a
        class="avatar"
        v-if="profileInfo.about"
        :style="'background: url('+profileInfo.about.avatar+'); background-position: center; background-size: cover;'"
        :class="(showInput?'mt-1':'')"
      ></a>
      <a class="col mt-1">
        <form>
          <textarea
            placeholder="Как у вас дела?"
            v-model="param.text"
            :class="(showInput?'textareaclick':'')"
          ></textarea>
        </form>
      </a>
      <div class="ml-auto d-flex flex-column">
        <div :class="(showInput?'mt-1':'')">
          <a>
            <input
              type="file"
              name="file"
              id="file"
              ref="file"
              @change="handleFileUpload()"
              class="inputfile"
            />
            <label for="file">
              <img src="/images/photograph.png" height="30px" alt />
            </label>
          </a>
        </div>
        <div v-if="showInput" class="mt-auto mb-3">
          <!-- <a> -->
          <!-- <img src="/images/message.png" height="23px" alt /> -->
          <!-- </a> -->
        </div>
      </div>
    </div>
    <div v-if="showInput" class="row pl-3 pr-3">
      <!-- <hr class="col-12 p-0 m-0 mb-3"> -->
      <div class="col-6 pt-4">
        <div class="custom-control custom-checkbox">
          <input
            v-model="param.only_friends"
            type="checkbox"
            class="custom-control-input"
            id="defaultUnchecked"
          />
          <label class="custom-control-label" for="defaultUnchecked">Только для друзей</label>
        </div>
      </div>
      <div class="col-6 text-right px-0 pb-2">
        <a class="butt rounded waves-effect" @click="sendpost()">
          Отправить
          <!-- <i class="far fa-paper-plane"></i> -->
          <img src="/images/message.png" height="15px" alt />
        </a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  // props: {
  //   getPosts: {
  //     type: Function
  //   }
  // },
  mounted() {
    this.$refs.postInput.addEventListener("click", e => {
      this.showInput = true;
      e.stopPropagation();
    });
    document.body.addEventListener("click", e => {
      this.show();
    });
    this.getprofile();
  },
  data() {
    return {
      showInput: false,
      disabled: false,
      param: {
        only_friends: false,
        image: []
      },
      file: "",
      profileInfo: {}
    };
  },
  methods: {
    sendpost() {
      if (this.param.text || (this.param.image.length > 0)) {
        this.disabled = true;
        axios.post("/newpost", this.param).then(response => {
          this.disabled = false;
          // console.log(response.data);
          this.$emit("getPosts", "");
        });
        this.param = {
          only_friends: false,
          image: []
        };
        // this.getPosts();
      }
    },
    getprofile() {
      axios.post("/getprofile", {}).then(response => {
        this.profileInfo = response.data;
      });
    },
    show() {
      this.showInput = false;
    },
    handleFileUpload() {
      let formData = new FormData();
      formData.append("file", this.$refs.file.files[0]);
      // console.log(formData);
      this.file = this.$refs.file.files[0];
      axios
        .post("/uploudhoto", formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(response => {
          this.sleep(500).then(() => {
            this.param.image.push(response.data.url);
          });
          // console.log(response.data);
        })
        .catch(error => {
          // console.log(error);

          alert("Возможно вы пытаетесь загрузить слишком большое изображение.");
        });
    },
    sleep(time) {
      return new Promise(resolve => setTimeout(resolve, time));
    }
  }
};
</script>


<style media="screen" scoped>
.imgSave {
  min-width: 50%;
  max-width: 100%;
}
.butt {
  box-shadow: 1px 1px 4px 0px #00000014;
  background: #e3f8fa;
  font-weight: 400;
  /* border: 1px solid #8ccef2; */
  padding: 10px 30px;
}
.butt:active {
  background: #8ccef2;
}
label {
  padding-top: 1px;
}
textarea {
  background: #f4f4f4;
  width: 100%;
  outline: none;
  resize: none;
  overflow: hidden;
  height: 45px;
  border: 0px solid #a3b1c6;
  padding: 10px;
  box-sizing: border-box;
  border-radius: 5px;
  padding-right: 70px !important;
}
.textareaclick {
  /* background: #fff; */
  min-height: 150px;
  /* resize: vertical; */
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
.inputfile {
  width: 0.1px;
  height: 0.1px;
  opacity: 0;
  overflow: hidden;
  position: absolute;
  z-index: -1;
}
.inputfile + label {
  font-size: 1.25em;
  font-weight: 700;
  color: white;
  display: inline-block;
  cursor: pointer; /* "hand" cursor */
}
</style>
