<template>
  <div class="order">
    <top-nav :title="title"></top-nav>
    <van-tabs
      v-model="active"
      swipeable
      animated
      color="#199153"
    >
      <!-- 全部 -->
      <van-tab title="全部">
        <van-pull-refresh
          v-model="isLoading"
          @refresh="onRefresh"
          loosing-text="松开刷新"
          success-text="刷新成功"
        >
          <order-list :list="list1"></order-list>
        </van-pull-refresh>
      </van-tab>
      <van-tab title="待发货">
        <order-list :list="list2"></order-list>
      </van-tab>
      <van-tab title="待收货">
        <order-list :list="list3"></order-list>
      </van-tab>
      <van-tab title="使用中">
        <order-list :list="list4"></order-list>
      </van-tab>
      <van-tab title="返还中">
        <order-list :list="list5"></order-list>
      </van-tab>
      <van-tab title="已完成">
        <order-list :list="list6"></order-list>
      </van-tab>
    </van-tabs>
  </div>
</template>

<script>
import topNav from '../components/TopNav.vue'
import OrderList from '../components/OrderList.vue'
export default {
  inject: ['reload'],
  components: {
    topNav,
    OrderList,
  },
  data() {
    return {
      title: '我的订单',
      active: 0,
      list1: [],
      list2: [],
      list3: [],
      list4: [],
      list5: [],
      list6: [],
      isLoading: false,
      status: [
        '0',
        '待发货', // 1
        '已发货', // 2
        '已寄出', // 3
        '已完成', // 4
        '使用中', // 5
        '超时', // 6
        '退款中', // -2
        '交易取消' // -4
      ],
      btn1: [
        '0',
        '提醒发货', // 1 提醒发货
        '查看物流', // 2 查看物流
        '', // 3
        '再来一单', // 4 再来一单
        '联系客服', // 5 联系客服
        '', // 6
        '', // -2
        '', // -4
      ],
      btn2: [
        '0',
        '申请退款', // 1 提醒发货
        '确认收货', // 2 查看物流
        '', // 3
        '评价订单', // 4 再来一单
        '寄回商品', // 5 联系客服
        '', // 6
        '', // -2
        '', // -4
      ]
    }
  },
  methods: {
    onRefresh() {
      setTimeout(() => {
        this.isLoading = false
      }, 1000)
    },
    onHandle(item) {
      console.log(item)
      if (item.status == 1) {
        this.$ajax
          .post(`api/goods_order/modifyOrderStatus`,
            this.$qs.stringify({
              id: item.orderId,
              order_status: -2
            }))
            .then(res => {
              if (res.data.port == '200') {
                console.log('已申请退款')
                this.reload()
              }
            })
            .catch(err => {
              console.log('失败', err.data)
            })
      } else if (item.status == 2) {
        this.$ajax
          .post(`api/goods_order/modifyOrderStatus`,
            this.$qs.stringify({
              id: item.orderId,
              order_status: 5
            }))
            .then(res => {
              if (res.data.port == '200') {
                console.log('已确认收货')
                this.reload()
              }
            })
            .catch(err => {
              console.log('失败', err.data)
            })
      } else if (item.status == 5) {
        this.$ajax
          .post(`api/goods_order/modifyOrderStatus`,
            this.$qs.stringify({
              id: item.orderId,
              order_status: 5
            }))
            .then(res => {
              if (res.data.port == '200') {
                console.log('已寄出商品')
                this.reload()
              }
            })
            .catch(err => {
              console.log('失败', err.data)
            })
      } else if (item.status == 4) {
        this.$router.push({
          path: '/comment',
          query: {
            orderId: item.orderId
          }
        })
      } 
    }
  },
  beforeCreate() {
    if (this.$store.state.user.id === -1) {
      this.$toast('请先登录!')
      this.$router.push('/login')
    }
  },
  mounted() {
    // 全部
    this.$ajax
        .get(`/api/goods_order/findAllByUserId?user_id=${this.$store.state.user.id}`)
        .then((res) => {
          if (res.data.port == "200") {
            let list = res.data.data;
            for (let info of list) {
              this.$ajax
              .get(`api/goods/findById?id=${info.goodsId}`)
              .then((res) => {
                if ((res.data.port = "200")) {
                  let index = res.data.data
                  index.leaseTime /= 86400;
                  index.num = info.goodsNum;
                  index.goodsId = info.goodsId;
                  index.orderId = info.id;
                  index.status = info.orderStatus
                  index.reTime = info.receivingTime
                  index.return = info.deposit
                  if (index.status == -2) {
                    index.status = 7
                  } else if (index.status == -4) {
                    index.status = 8
                  }
                  this.list1.push(index);
                  switch(index.status) {
                    case 1:
                      this.list2.push(index)
                      break
                    case 2:
                      this.list3.push(index)
                      break
                    case 5:
                      this.list4.push(index)
                      break
                    case 6:
                      this.list4.push(index)
                      break
                    case 3:
                      this.list5.push(index)
                      break
                    case 4:
                      this.list6.push(index)
                      break
                    case 8:
                      this.list6.push(index)
                      break
                    case 9:
                      this.list6.push(index)
                      break
                  }
                  // console.log(this.list6)
                }
              })
              .catch(err => {
                console.log(`商品信息获取失败`, err.data)
              })
            }
          }
        })
        .catch((err) => {
          console.log("获取失败", err.data);
        });
  }
}
</script>

<style lang="less" scoped>
.order {
  height: 100vh;
  background-color: #f8f8f8;
  .order-list {
    // height: 610px;
    margin-top: 8px;
    .pay-goods-card {
      background-color: white;
      margin: 0px 8px 8px 8px;
      border-radius: 8px;
      padding: 8px 12px;
      text-align: start;
      border: solid 1px #f6f6f6;
      ::v-deep .van-card {
        background-color: white;
        ::v-deep .van-card__title {
          font-size: 14px;
          font-weight: bold;
          line-height: 16px;
        }
        ::v-deep .van-card__desc {
          padding-top: 5px;
          line-height: 16px;
          color: #323233;
        }
        .van-tag--danger {
          margin-top: 8px;
          padding: 3px 4px 2px 4px;
          color: #323233;
        }
        .van-card__price {
          color: #323233;
          font-size: 14px;
          font-weight: 500;
        }
        ::v-deep .van-card__bottom {
          font-size: 14px;
        }
      }
      .price {
        display: flex;
        flex-direction: row;
        justify-content: flex-end;
        font-size: 12px;
        font-weight: 500;
        .text {
          color: #323233;
        }
      }
    }
    .status {
      display: flex;
      flex-direction: row-reverse;
      font-size: 14px;
      font-weight: bold;
      color: #199153;
    }
    .handleButton {
      display: flex;
      flex-direction: row-reverse;
      margin-top: 3px;
      .van-button {
        height: 32px;
      }
      .btn_1 {
        .van-button--normal {
          color: #666;
          border: 1px solid #e8e8e8;
          padding: 0 8px;
        }
      }
      .btn_2 {
        margin-left: 8px;
        .van-button--normal {
          color: #199153;
          border: 1px solid #08bd60;
          padding: 0 8px;
        }
      }
    }
  }
}
</style>
