<template>
  <div class="order-list">
    <van-empty
      class="custom-image"
      image="https://img01.yzcdn.cn/vant/custom-empty-image.png"
      description="您还没有相关订单"
      v-if="!list.length"
    />
    <van-swipe-cell
      v-for="(item, index) in list"
      :key="index"
      class="pay-goods-card"
      v-if="list.length"
    >
      <div class="status">
        <span>{{status[item.status]}}</span>
      </div>
      <van-card
        :num="item.num"
        :price="' ' + item.price + ' / ' + item.leaseTime + '天'"
        :title="item.goodsName"
        :thumb="item.picUrl"
      >
        <template #tags>
          <van-tag
            plain
            type="danger"
          >{{'押金：￥' + item.deposit}}</van-tag>
          </van-tag>
          <van-tag
            plain
            type="danger"
            class="return"
            v-show="item.return < item.deposit"
          >{{'可退押金：￥' + item.return}}</van-tag>
          </van-tag>
        </template>
      </van-card>
      <div class="price">
        <p
          class="time"
          v-show="item.status == 5"
        >{{'到期时间：' + time(toTimeStamp(item.reTime) + item.leaseTime * 86400 + 39600000)}}</p>
        <p class="title">合计：</p>
        <p class="text">
          {{ "￥" + ((item.price + item.deposit) * item.num).toFixed(2) }}
        </p>
      </div>
      <div
        class="handleButton"
        v-if='item.status != 7 && item.status != 3 && item.status != 8'
      >
        <div
          class="btn_2"
          @click="onHandle(item)"
          v-if='item.status != 9'
        >
          <van-button
            plain
            type="primary"
            round
          >{{btn2[item.status]}}</van-button>
        </div>
        <div class="btn_1">
          <van-button
            plain
            type="primary"
            round
          >{{btn1[item.status]}}</van-button>
        </div>
        <div class="btn_0">
          <van-button
            plain
            type="primary"
            round
            v-show="(item.mode == 2) && (item.status == 5 || item.status == 6)"
            @click="onBuyout(item.orderId)"
          >买断商品</van-button>
        </div>
      </div>
    </van-swipe-cell>
  </div>
</template>

<script>
import { Dialog, Toast } from "vant";
import { timestampToTime } from '../util/util.js'
export default {
  props: ['list'],
  inject: ['reload'],
  data() {
    return {
      status: [
        '0',
        '待发货', // 1
        '已发货', // 2
        '已寄出', // 3
        '已完成', // 4
        '使用中', // 5
        '超时', // 6
        '退款中', // -2
        '交易取消', // -4
        '已完成'
      ],
      btn1: [
        '0',
        '提醒发货', // 1 提醒发货
        '查看物流', // 2 查看物流
        '', // 3
        '再来一单', // 4 再来一单
        '申请售后', // 5 联系客服
        '', // 6
        '', // -2
        '', // -4
        '再来一单'
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
    onHandle(item) {
      console.log(item)
      if (item.status == 1) {
        Dialog.confirm({
        message: "确认申请退款吗？",
      })
        .then(() => {
          this.$ajax
          .post(`api/goods_order/modifyOrderStatus`,
            this.$qs.stringify({
              id: item.orderId,
              order_status: -2
            }))
            .then(res => {
              if (res.data.port == '200') {
                console.log('已申请退款')
                Toast.success(`已申请退款!`)
                this.reload()
              }
            })
            .catch(err => {
              console.log('失败', err.data)
            })
        })
        .catch(() => {

        })
        
      } else if (item.status == 2) {
        Dialog.confirm({
        message: "已确认收货吗？",
      })
        .then(() => {
          this.$ajax
          .post(`api/goods_order/modifyOrderStatus`,
            this.$qs.stringify({
              id: item.orderId,
              order_status: 5
            }))
            .then(res => {
              if (res.data.port == '200') {
                console.log('已申请退款')
                Toast.success(`已确认收货!`)
                this.reload()
              }
            })
            .catch(err => {
              console.log('失败', err.data)
            })
        })
        .catch(() => {

        })
      } else if (item.status == 5) {
        Dialog.confirm({
        message: "确认寄回商品吗？",
      })
        .then(() => {
          this.$ajax
          .post(`api/goods_order/modifyOrderStatus`,
            this.$qs.stringify({
              id: item.orderId,
              order_status: 3
            }))
            .then(res => {
              if (res.data.port == '200') {
                console.log('已寄回商品')
                Toast.success(`已寄回商品!`)
                this.reload()
              }
            })
            .catch(err => {
              console.log('失败', err.data)
            })
        })
        .catch(() => {

        })
      } else if (item.status == 4) {
        this.$router.push({
          path: '/comment',
          query: {
            orderId: item.orderId
          }
        })
      } 
    },
    onBuyout(id) {
      Dialog.confirm({
        message: "确认买断该商品吗？",
      })
      .then(() => {
        this.$ajax
          .post(`api/goods_order/modifyOrderStatus`,
            this.$qs.stringify({
              id: id,
              order_status: 7
            }))
            .then(res => {
              if (res.data.port == '200') {
                console.log('已买断')
                this.reload()
              }
            })
            .catch(err => {
              console.log('失败', err.data)
            })
      })
      .catch(() => {

      })
      
    }
  },
  computed: {
    time() {
      return stamp => {
        let date = new Date(stamp) //时间戳为10位需*1000，时间戳为13位的话不需乘1000
        let Y = date.getFullYear() + '-'
        let M =
          (date.getMonth() + 1 < 10
            ? '0' + (date.getMonth() + 1)
            : date.getMonth() + 1) + '-'
        let D = date.getDate() + ' '
        let h = date.getHours() + ':'
        let m = date.getMinutes() + ':'
        let s = date.getSeconds()
        return Y + M + D + h + m + s
      }
    },
    toTimeStamp() {
      return time => {
        let date = new Date(time)
        return date.getTime()
      }
    }
  }
}
</script>

<style lang="less" scoped>
.order-list {
  margin-top: 8px;
  padding-bottom: 16px;
  .van-empty {
    padding-bottom: 320px;
  }
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
      .return {
        margin-left: 5px;
        color: #ee0a24;
      }
    }
    .price {
      .time {
        font-size: 12px;
        color: gray;
        padding-right: 32px;
      }
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
    .btn_0 {
      .van-button--normal {
        color: #199153;
        border: 1px solid #08bd60;
        padding: 0 8px;
      }
    }
    .btn_1 {
      margin-left: 8px;
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
</style>