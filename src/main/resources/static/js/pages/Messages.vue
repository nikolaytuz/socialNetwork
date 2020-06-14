<template id="">
  <div class="container-fluid poln back">
    <div class="row m-0">
      <div class="mx-auto d-flex align-items-stretch align-items-start">
        <div class="left-col">
          <MessageUi />
          <div class="styk">
            <Menu namePage="Сообщения" />
          </div>
        </div>

        <div class="right-col">
          <PanelLogo />
          <MessagePole ref="messPole" />
          <MessageInput :getMessage="getMessages" />
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
import MessageUi from "components/MessageUi.vue";
import MessagePole from "components/MessagePole.vue";

export default {
  components: {
    MessageInput,
    Menu,
    Logo,
    PanelLogo,
    MessageUi,
    MessagePole
  },
  data() {
    return {};
  },
  methods: {
    getMessages() {
      this.$refs.messPole.getMessages();
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