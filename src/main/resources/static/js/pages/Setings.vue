<template id="">
  <div class="container-fluid poln back">
    <div class="row m-0">
      <div class="mx-auto d-flex align-items-stretch align-items-start">
        <div class="left-col">
          <div class="styk">
            <Menu namePage="Настроки" class="mt-0"/>
          </div>
        </div>

        <div class="right-col">
          <PanelLogo />
          <SettingsVidjet/>
        </div>
      </div>
    </div>
  </div>
</template>



<script type="text/javascript">
import Menu from "components/Menu.vue";
import PanelLogo from "components/PanelLogo.vue";
import SettingsVidjet from "components/SettingsVidjet.vue";

export default {
  components: {
    Menu,
    PanelLogo,
    SettingsVidjet,
  },
  data() {
    return {
    };
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
