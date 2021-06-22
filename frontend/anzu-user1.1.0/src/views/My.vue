<template>
  <div class="my">
    <!-- "我的"标题 -->
    <!-- <h3 class="title">我的</h3> -->
    <div class="title">
      <van-nav-bar
        title="我的"
        right-text="退出登录"
        placeholder
        safe-area-inset-top
        @click-right="onClickRight"
      />
    </div>
    <!-- 用户信息 -->
    <div class="user-info">
      <!-- 头像 -->
      <div class="head">
        <van-image
          round
          fit="contain"
          width="80px"
          height="80px"
          src="http://yuan619.xyz/anzu/images/mylogo.png"
        />
      </div>
      <!-- 余额 -->
      <div class="balance">
        <p>账户余额：</p>
        <p class="num">{{ '￥' + balance }}</p>
      </div>
      <!-- icon -->
      <div class="icon">
        <van-icon
          name="arrow"
          size="20"
        />
      </div>
    </div>
    <!-- 列表 -->
    <van-cell-group>
      <van-cell
        title="我的订单"
        icon="orders-o"
        is-link
        size="large"
        @click="toOrder"
      />
      <van-cell
        title="收货地址"
        icon="location-o"
        is-link
        size="large"
        to="/address"
      />
      <van-cell
        title="我的优惠券"
        icon="coupon-o"
        is-link
        size="large"
      />
      <van-cell
        title="帮助中心"
        icon="smile-comment-o"
        is-link
        size="large"
      />
    </van-cell-group>
    <!-- 在线客服 -->
    <div class="btn">
      <van-button
        icon="service-o"
        type="danger"
        round
        color="linear-gradient(to right, #eb4e5d, #da5045)"
        size="large"
      >
        在线客服
      </van-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'my',
  data() {
    return {
      balance: '??.??'
    }
  },
  methods: {
    toLogin: function() {
      this.$router.push('/login')
    },
    toOrder: function() {
      this.$router.push({
        path: '/order'
        // query: {
        //   id: this.$store.state.user.id,
        // },
      })
    },
    onClickRight() {
      this.$router.push('/login')
      this.$store.state.user.id = -1
    }
  },
  watch: {
    '$store.state.user.balance': (newVal, oldVal) => {
      this.balance = newVal
    }
  },
  mounted() {
    if (this.$store.state.user.id != -1) {
      this.$ajax
        .get("/api/user/findByAccount?account=" + this.$store.state.user.account)
        .then((res) => {
            if (res.data.port === "200") {
              this.balance = res.data.data.balance
            }
        })
        .catch((err) => {
          console.log(err);
        });
    }
    this.$ajax
      .get(
        `/api/user_address/findAllByUserId?user_id=${this.$store.state.user.id}`
      )
      .then(res => {
        if ((res.data.port === '200')) {
          this.$store.state.user.nowAddress = res.data.data[0].id
        }
      })
      .catch(err => {
        console.log(`地址获取失败`, err.data)
      })
  }
}
</script>

<style scoped>
.my {
  height: 100vh;
  width: 375px;
  background-color: #f8f8f8;
  text-align: start;
}
.title {
  font-size: 28px;
  font-weight: bold;
  width: 100%;
  /* padding: 10px 0 10px 20px; */
  margin-bottom: 2px;
  /* margin: 10px 0 5px 20px; */
  background-color: white;
}
::v-deep .van-nav-bar__content {
  width: 100%;
  /* background-image: linear-gradient(to right, #07c160 , #73dc37); */
  /* background-color: #07c160; */
}
::v-deep .van-nav-bar__title {
  font-size: 16px;
  font-weight: bold;
}
.btn {
  margin-top: 30px;
  text-align: center;
  font-size: 30px;
  padding: 0 110px;
}
.user-info {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  padding: 16px 16px 16px 16px;
  margin: 6px 0 6px 0;
  border-top: solid 1px #f2f2f2;
  border-bottom: solid 1px #f2f2f2;
}
.balance {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  font-size: 20px;
  font-weight: bold;
}
.balance .num {
  color: #ee0a24;
}

::v-deep .van-nav-bar__text {
  color: #646566;
}
::v-deep .van-image--round img {
  border: 2px solid #f4f4f4;
}
</style>
