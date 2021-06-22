<template>
  <div class="detail">
    <!-- <div class="nav">
      <van-nav-bar title="商品详情" left-arrow @click-left="onClickLeft" />
    </div> -->
    <TopNav :title="title"></TopNav>
    <!-- 轮播图 -->
    <van-swipe
      @change="onChange"
      class="my-swipe"
      :autoplay="3000"
    >
      <van-swipe-item
        v-for="(image, index) in swipeImages"
        :key="index"
      >
        <img
          height="300"
          v-lazy="image"
        />
      </van-swipe-item>
      <template #indicator>
        <div class="custom-indicator">{{ current + 1 }}/4</div>
      </template>
    </van-swipe>
    <!-- 商品信息 -->
    <!-- <GoodsInfo v-bind="info"></GoodsInfo> -->
    <div class="goods-info">
      <van-card
        :price="' ' + goods.price + ' / ' + goods.leaseTime + '天'"
        :title="goods.goodsName"
        :desc="goods.intro"
        v-if="isShow"
      >
        <template #tags>
          <van-tag
            plain
            type="primary"
          >{{
            '押金: ￥' + goods.deposit
          }}</van-tag>
          &nbsp;
          <van-tag
            plain
            type="primary"
          >{{ buyout }}</van-tag>
        </template>
        <template #num>
          {{ `月销：` + goods.sales }}
        </template>
      </van-card>
    </div>
    <div class="second">
      <div class="shop-comment">
        <div class="my-title">
          <span class="text">商家评价</span>
        </div>
        <div class="shop">
          <span>{{'商品描述：' + goodsComment.desc_star}}</span>
          <span>{{'卖家服务：' + goodsComment.attitude_star}}</span>
          <span>{{'物流服务：' + goodsComment.logistics_star}}</span>
        </div>
      </div>
      <div class="comment">
        <div class="my-title">
          <span class="text">商品评价</span>
        </div>
        <div class="line-one">
          <div class="couple">
            <van-icon name="http://yuan619.xyz/anzu/icons/face0.png" />
            <span>{{'好评：' + goodsComment.good_rate}}</span>
          </div>
          <div class="couple">
            <van-icon name="http://yuan619.xyz/anzu/icons/face1.png" />
            <span>{{'中评：' + goodsComment.mid_rate}}</span>
          </div>
          <div class="couple">
            <van-icon name="http://yuan619.xyz/anzu/icons/fave2.png" />
            <span>{{'差评：' + goodsComment.bad_rate}}</span>
          </div>
        </div>
        <div class="content">
          <book-comment-card :commentList="commentList"></book-comment-card>
        </div>
      </div>
    </div>
    <!-- 底部 -->
    <van-goods-action>
      <van-goods-action-icon
        icon="home-o"
        text="首页"
        to="/home"
      />
      <van-goods-action-icon
        icon="cart-o"
        text="购物车"
        to="/cart"
      />
      <van-goods-action-button
        color="#be99ff"
        type="warning"
        text="加入购物车"
        @click="addCart"
      />
      <!-- <van-goods-action-button
        color="#7232dd"
        type="danger"
        text="立即下单"
      /> -->
    </van-goods-action>
    <!-- 底部弹出 -->
    <div class="pop">
      <van-popup
        class="pop-card"
        v-model="show"
        closeable
        position="bottom"
        :style="{ height: '65%' }"
      >
        <van-card
          :desc="cartDes"
          :title="'￥' + goods.price + ' / ' + goods.leaseTime + '天'"
          :thumb="goods.picUrl"
        >
          <template #tags>
            <van-tag
              plain
              type="primary"
            >{{ '库存：' + goods.stock }}</van-tag>
          </template>
        </van-card>
        <van-divider />
        <!-- 选择数量和租期 -->
        <div class="count">
          <span>数量 :</span>
          <div class="number">
            <van-stepper
              v-model="countValue"
              integer
            />
            <span>(件)</span>
          </div>
        </div>
        <!-- 确定 -->
        <div class="btn">
          <van-button
            :disabled="onSure"
            type="primary"
            round
            size="large"
            @click="sure"
          >确定</van-button>
        </div>
      </van-popup>
    </div>
    <!-- 确认支付 -->
  </div>
</template>

<script>
import TopNav from '../components/TopNav.vue'
import GoodsInfo from '../components/GoodsInfo.vue'
import { Dialog, Toast } from 'vant'
import BookCommentCard from '../components/BookCommentCard.vue'
export default {
  components: { TopNav, GoodsInfo, BookCommentCard },
  data() {
    return {
      title: '商品详情',
      current: 0,
      goods: {
        id: 0,
        goodsName: '',
        price: 1.0,
        categoryId: 1,
        merchantId: 1,
        intro: '',
        status: 1,
        picUrl: '1',
        mode: 1,
        deposit: 1213.0,
        stock: 0,
        sales: 0,
        leaseTime: 123
      },
      isShow: false,
      swipeImages: [],
      show: false,
      countValue: 1,
      cartOrBuy: 0,
      goodsComment: {
        desc_star: 0,
        logistics_star: 0,
        attitude_star: 0,
        good_rate: 0,
        mid_rate: 0,
        bad_rate: 0
      },
      commentList: [
        // {
        //   rate: 4,
        //   content: '看完后觉得受益匪浅, 中医的智慧令人印象深刻!!!'
        // },
        // {
        //   rate: 5,
        //   content: '真的给我解决了特别多的疑惑, 感谢作者的付出!'
        // }
      ]
    }
  },
  methods: {
    onChange(index) {
      this.current = index
    },
    onClickIcon() {},
    onClickButton() {},
    addCart() {
      this.show = true
    },
    sure() {
      this.show = false
      console.log(this.$store.state.user.id)
      this.$ajax
        .post(
          '/api/cart/add',
          this.$qs.stringify({
            goods_id: this.$route.query.goodsId,
            user_id: this.$store.state.user.id,
            goods_num: this.countValue
          })
        )
        .then(res => {
          console.log(res.data)
          Toast.success('已添加至购物车!')
        })
        .catch(err => {
          console.log('加入购物车失败:', err)
        })
    }
  },
  computed: {
    buyout() {
      return this.goods.mode == 2 ? '支持买断' : '不支持买断'
    },
    cartDes() {
      return this.goods.mode == 2
        ? '押金：￥' + this.goods.deposit // +
        : // "   买断价：￥" +
          // Math.ceil((this.goods.deposit - this.goods.price) * 0.9)
          '押金：￥' + this.goods.deposit
    },
    onSure() {
      return this.goods.stock < this.countValue ? true : false
    }
  },
  created() {
    this.$ajax
      .get('api/goods/findById?id=' + this.$route.query.goodsId)
      .then(res => {
        if (res.data.port === '200') {
          this.goods = res.data.data
          this.goods.leaseTime = Math.ceil(this.goods.leaseTime / 86400)
          this.isShow = true
          for (let i = 0; i < 4; i++) {
            this.swipeImages.push(this.goods.picUrl)
          }
        } else {
          this.goods = {}
        }
      })
      .catch(err => {
        console.log('商品详情获取失败', err)
      })
  },
  mounted() {
    // /comment_statistic/findByGoodsId
    this.$ajax
      .get('api/goods_statistic/findByGoodsId?goods_id=' + this.$route.query.goodsId)
      .then(res => {
        if (res.data.port === '200' && res.data.data.rateNum !== 0) {
          let info = res.data.data
          console.log(info)
          this.goodsComment = {
            desc_star: (info.descStar / info.rateNum).toFixed(1),
            logistics_star: (info.logisticsStar / info.rateNum).toFixed(1),
            attitude_star: (info.attitudeStar / info.rateNum).toFixed(1),
            good_rate: info.goodRate,
            mid_rate: info.midRate,
            bad_rate: info.badRate
          }
        }
      })
      .catch(err => {
        console.log('商家评价获取失败', err)
      })
    this.$ajax
      .get('api/comment_statistic/findByGoodsId?goods_id=' + this.$route.query.goodsId)
      .then(res => {
        if (res.data.port === '200') {
          let info = res.data.data
          console.log(info)
          for (let item of info) {
            this.commentList.push({
            rate: item.rate,
            content: item.content
          })
          }
        }
      })
      .catch(err => {
        console.log('商品评价获取失败', err)
      })
  }
}
</script>

<style scoped>
.detail {
  background-color: #f9f9f9;
  /* height: 100vh; */
  padding-bottom: 50px;
  width: 100%;
}
.custom-indicator {
  position: absolute;
  right: 5px;
  bottom: 5px;
  padding: 2px 5px;
  margin-right: 8px;
  font-size: 12px;
  background: rgba(0, 0, 0, 0.4);
}
.count {
  margin: 5px 30px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.number {
  width: 130px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.count span {
  height: 30px;
  line-height: 30px;
}
.pop-card {
  padding-top: 10px;
  text-align: start;
}
::v-deep .van-swipe {
  color: white;
}
::v-deep .custom-indicator[data-v-40f4caea] {
  border-radius: 6px;
}
::v-deep .van-card {
  background-color: white;
}
.van-card__content {
  line-height: 50px;
}
.van-card__desc {
  font-size: 14px;
  margin-top: 8px;
}
.btn {
  margin: 80px 0 0 0;
  padding: 0 20px;
}
::v-deep .van-dialog__header {
  font-weight: bold;
}
::v-deep .van-dialog__message--has-title {
  color: #ee0a24;
}
.van-divider::after,
.van-divider::before {
  border-width: 2px 0 0;
}
.goods-info {
  text-align: start;
  /* margin-top: 2px; */
}
::v-deep .van-card {
  line-height: 36px;
  background-color: white;
}
::v-deep .van-card__title {
  max-height: 100px;
  font-size: 16px;
  line-height: 20px;
  font-weight: bold;
  text-align: start;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  line-clamp: 2;
}
::v-deep .van-card__price {
  color: #ee0a24;
  font-weight: bold;
  font-size: 18px;
}
::v-deep .van-card__price-integer {
  font-size: 20px;
}
::v-deep .van-tag--plain {
  padding: 3px;
}
.pop [data-v-40f4caea] .van-card__title {
  color: #ee0a24;
  font-weight: bold;
  font-size: 20px;
}
::v-deep .van-goods-action-button--warning {
  background-color: #07c160 !important;
}
/* .van-tag--primary.van-tag--plain {
  color: #07c160;
} */
[data-v-40f4caea] .van-tag--plain {
  padding: 3px 5px;
}
.van-swipe-item {
  background-color: white;
}
img {
  object-fit: fill;
}
.second {
  padding: 10px 16px;
  background-color: white;
  margin: 6px 0;
  text-align: start;
}
.second .intro {
  margin-bottom: 20px;
}
.my-title {
  border-bottom: 4px solid #07c160;
  margin: 8px 0;
  font-size: 14px;
  font-weight: bold;
}
.my-title .text {
  background-color: #07c160;
  padding: 4px 25px;
  padding-right: 35px;
  color: white;
  border-radius: 3px 50px 3px 3px;
}
.intro .content {
  text-indent: 1.6em;
  font-size: 12px;
  line-height: 24px;
}
.shop {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  font-size: 14px;
  margin: 16px 0;
}
.comment .line-one {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 8px 0;
}
.comment .line-one .couple {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  font-size: 14px;
}
.comment .line-one .couple span {
  padding-left: 4px;
}
</style>
