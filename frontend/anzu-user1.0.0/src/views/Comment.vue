<template>
  <div class="comment">
    <top-nav :title="title"></top-nav>
    <div class="content">
      <!-- 评价主页 -->
      <div class="comment-card">
        <div class="rate">
          <div class="title-rate">
            <p class="text">商品评价</p>
          </div>
          <div class="radio">
            <van-radio-group
              v-model="radio"
              direction="horizontal"
            >
              <van-radio
                name="0"
                checked-color="#ee0a24"
                icon-size="14px"
              >好评</van-radio>
              <van-radio
                name="1"
                checked-color="#ee0a24"
                icon-size="14px"
              >中评</van-radio>
              <van-radio
                name="2"
                checked-color="#ee0a24"
                icon-size="14px"
              >差评</van-radio>
            </van-radio-group>
          </div>
        </div>
        <div class="words">
          <van-field
            v-model="message"
            rows="4"
            autosize
            left-icon="edit"
            type="textarea"
            maxlength="140"
            placeholder="感觉怎么样？和大家分享一下吧~"
            show-word-limit
          />
        </div>
        <div class="anony">
          <van-checkbox
            v-model="checked"
            checked-color="#ee0a24"
            icon-size="14px"
          >匿名评价</van-checkbox>
        </div>
      </div>
      <!-- 评价打星 -->
      <div class="star">
        <div class="title">
          <p>给本次购物打个星吧~</p>
        </div>
        <div class="desc line">
          <p class="text">描述相符</p>
          <van-rate
            v-model="star1"
            size="24px"
            @change="onChange1"
          />
          <p class="tip">{{ tip1 }}</p>
        </div>
        <div class="logistics line">
          <p class="text">物流服务</p>
          <van-rate
            v-model="star2"
            size="24px"
            @change="onChange2"
          />
          <p class="tip">{{ tip2 }}</p>
        </div>
        <div class="attitude line">
          <p class="text">服务态度</p>
          <van-rate
            v-model="star3"
            size="24px"
            @change="onChange3"
          />
          <p class="tip">{{ tip3 }}</p>
        </div>
      </div>
    </div>
    <!-- 提交按钮 -->
    <div class="footer">
      <my-button
        :text="btnText"
        :onClick="onClick"
      ></my-button>
    </div>
  </div>
</template>

<script>
import commentCard from '../components/CommentCard'
import topNav from '../components/TopNav.vue'
import myButton from '../components/MyButton'
import { Toast } from 'vant'
export default {
  components: {
    commentCard,
    topNav,
    myButton
  },
  data() {
    return {
      title: '发表评价',
      radio: '0',
      message: '',
      checked: true,
      star1: 5,
      star2: 5,
      star3: 5,
      tip1: '非常好',
      tip2: '非常好',
      tip3: '非常好',
      btnText: '提交'
    }
  },
  methods: {
    onChange1(val) {
      if (val === 0) {
        this.tip1 = ''
      } else if (val === 1) {
        this.tip1 = '非常差'
      } else if (val === 2) {
        this.tip1 = '差'
      } else if (val === 3) {
        this.tip1 = '一般'
      } else if (val === 4) {
        this.tip1 = '好'
      } else {
        this.tip1 = '非常好'
      }
    },
    onChange2(val) {
      if (val === 0) {
        this.tip2 = ''
      } else if (val === 1) {
        this.tip2 = '非常差'
      } else if (val === 2) {
        this.tip2 = '差'
      } else if (val === 3) {
        this.tip2 = '一般'
      } else if (val === 4) {
        this.tip2 = '好'
      } else {
        this.tip2 = '非常好'
      }
    },
    onChange3(val) {
      if (val === 0) {
        this.tip3 = ''
      } else if (val === 1) {
        this.tip3 = '非常差'
      } else if (val === 2) {
        this.tip3 = '差'
      } else if (val === 3) {
        this.tip3 = '一般'
      } else if (val === 4) {
        this.tip3 = '好'
      } else {
        this.tip3 = '非常好'
      }
    },
    onClick() {
      console.log('我要提交了!!!', this.$route.query.orderId)
      this.$ajax
        .post(
          '/api/comment_statistic/add',
          this.$qs.stringify({
            order_id: this.$route.query.orderId,
            rate: Number(this.radio),
            content: this.message,
            desc_star: this.star1,
            logistics_star: this.star2,
            attitude_star: this.star3
          })
        )
        .then(res => {
          if (res.data.port === '200') {
            this.$ajax
          .post(`api/goods_order/modifyOrderStatus`,
            this.$qs.stringify({
              id: this.$route.query.orderId,
              order_status: 9
            }))
            .then(res => {
              
            })
            .catch(err => {
              console.log('失败', err.data)
            })
          console.log(res.data)
          Toast.success('感谢您的评价!')
          this.$router.back(-1)
          }
        })
        .catch(err => {
          console.log('评价:', err)
        })
    }
  }
}
</script>

<style scoped>
.comment {
  width: 375px;
  background-color: #f8f8f8;
  height: 100vh;
}
.comment-card {
  background-color: white;
  margin: 8px;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  font-size: 14px;
  padding: 12px 0;
}
.comment-card .rate {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  padding: 0px 0 8px 16px;
}
.comment-card .rate .title-rate {
  margin-right: 12px;
}
.title-rate .text {
  font-weight: bold;
}
.anony {
  padding-left: 16px;
}

.star {
  background-color: white;
  margin: 8px;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  font-size: 14px;
  padding: 12px 0;
}
.star .title {
  display: flex;
  padding-left: 16px;
}
.star .line {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  padding: 12px 16px 0 16px;
}
.star .line .text {
  font-weight: bold;
  margin-right: 12px;
}
.star .tip {
  /* padding-top: 3px; */
  margin-left: 12px;
  color: #bcbccc;
  font-size: 10px;
}

.footer {
  position: fixed;
  left: 0px;
  bottom: 0px;
  width: 100%;
  height: 100px;
  background-color: white;
  z-index: 9999;
}
</style>
