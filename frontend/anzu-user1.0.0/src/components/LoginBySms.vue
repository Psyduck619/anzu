<template>
  <div class="input">
    <div class="logo">
      <van-image
        width="10rem"
        height="10rem"
        fit="contain"
        src="http://yuan619.xyz/anzu/images/mylogo.png"
      />
    </div>
    <van-cell-group>
      <van-field
        v-model="tel"
        type="tel"
        clearable
        label="手机号码"
        placeholder="请输入手机号"
        maxlength="11"
      />
      <van-field
        v-model="sms"
        center
        clearable
        label="短信验证码"
        placeholder="请输入短信验证码"
        maxlength="4"
      >
        <template #button>
          <van-button
            size="small"
            type="primary"
            round
            :disabled="smsFlag"
            @click="onSms"
          >{{smsText}}</van-button>
        </template>
      </van-field>
    </van-cell-group>
    <div class="btn">
      <van-button
        type="primary"
        round
        size="large"
        :disabled="loginFlag"
        @click="login"
      >立即登录</van-button>
    </div>
  </div>
</template>

<script>
import { Toast } from "vant";
export default {
  data() {
    return {
      tel: "",
      sms: "",
      smsFlag: true,
      loginFlag: true,
      active: 2,
      smsText: '获取验证码'
    };
  },
  methods: {
    onSms() {
      this.smsFlag = true
      this.smsText = '已发送'
    },
    onClickLeft() {
      this.$router.push(this.$store.state.sys.lastPath);
    },
    login() {
      console.log("我要登录了!!!");
      this.$ajax
        .get("/api/user/findByAccount?account=" + this.tel)
        .then((res) => {
          if (res.data.port === "500" || res.data.port === "400") {
            Toast("用户不存在");
          } else {
            if (res.data.port === "200") {
              this.$store.commit("setLogin", true)
              this.$store.commit("setUserId", res.data.data.id)
              this.$store.commit("setAccount", res.data.data.account)
              Toast.success(`登录成功!`)
              this.$router.push('/my');
            }
          }
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  computed: {
    address() {
      const { tel, sms } = this;
      return {
        tel,
        sms,
      };
    },
  },
  watch: {
    tel(nval, oval) {
      if (this.tel.length < 11) {
        this.smsFlag = false;
      } else {
        this.smsFlag = false;
      }
    },
    address(nval, oval) {
      if (this.tel.length && this.sms.length) {
        this.loginFlag = false
      } else {
        this.loginFlag = true
      }
    },
  },
};
</script>

<style scoped>
.van-cell::after {
  border-bottom: 2px solid #ebedf0;
}
.btn {
  padding: 30px;
}
</style>
