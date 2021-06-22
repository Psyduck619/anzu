<template>
  <div class="pay">
    <!-- top-nav -->
    <top-nav :title="title"></top-nav>
    <!-- select-address -->
    <address-select-card></address-select-card>
    <!-- goods-info -->
    <div class="order-list">
      <van-swipe-cell
        v-for="(item, index) in list"
        :key="index"
        class="pay-goods-card"
      >
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
          </template>
        </van-card>
        <div class="note">
          <van-field
            v-model="note"
            label="订单备注"
            placeholder="选填"
          />
        </div>
        <div class="price">
          <p class="title">小计：</p>
          <p class="text">
            {{ "￥" + ((item.price + item.deposit) * item.num).toFixed(2) }}
          </p>
        </div>
      </van-swipe-cell>
    </div>
    <!-- submit-bar -->
    <div class="submit">
      <van-submit-bar
        :price="total"
        button-text="提交订单"
        @submit="onSubmit"
      >
        <template #default>
          <span class="num">{{'共 ' + list.length + ' 件，'}}</span>
        </template>
      </van-submit-bar>
    </div>
  </div>
</template>

<script>
import topNav from "../components/TopNav.vue";
import addressSelectCard from "../components/AddressSelectCard.vue";
import { Dialog, Toast } from "vant";
export default {
  components: { topNav, addressSelectCard },
  data() {
    return {
      title: "确认订单",
      total: 0,
      list: [],
      note: "",
    };
  },
  methods: {
    onSubmit() {
      if (this.$store.state.user.nowAddress == -1) {
        Toast(`请选择一个收货地址！`);
      } else {
        Dialog.confirm({
        message: "确认支付订单吗？",
      })
        .then(() => {
          for (let item of this.list) {
        this.$ajax
          .post(`/api/goods_order/add`,
          this.$qs.stringify({
              user_id: this.$store.state.user.id,
              goods_id: item.goodsId,
              goods_num: item.num,
              user_address: this.$store.state.user.nowAddress,
            })
          )
          .then((res) => {
            if (res.data.port == '200') {
              console.log(`订单添加成功`, res.data)
            }
          }).catch((err) => {
            console.log(`订单添加失败`, err.data)
          })
        this.$ajax
      .get(
        `/api/cart/deletebyid?id=${item.cartId}`
      )
      .then((res) => {
        if (res.data.port == '200') {
          console.log("删除购物车信息成功:", res.data.data);
        }
        
      })
      .catch((err) => {
        console.log("删除购物车信息失败:", err);
      });
      }
      Toast.success(`您已成功下单!`);
      this.$router.push(`/my`)
        })
        .catch(() => {
          // on cancel
        });
      }
    },
  },

  mounted() {
    // console.log(this.$store.state.user.totalPrice)
    // console.log(this.$store.state.user.checkedList)
    this.total = Number.parseInt(this.$store.state.user.totalPrice);
    let checkList = this.$store.state.user.checkedList;
    for (let item of checkList) {
      this.$ajax
        .get(`/api/cart/findById?id=${item}`)
        .then((res) => {
          if (res.data.port == "200") {
            let info = res.data.data
            this.$ajax
              .get(`api/goods/findById?id=${info.goodsId}`)
              .then((res) => {
                if ((res.data.port = "200")) {
                  res.data.data.leaseTime /= 86400;
                  res.data.data.num = info.goodsNum;
                  res.data.data.goodsId = info.goodsId;
                  res.data.data.cartId = info.id;
                  this.list.push(res.data.data);
                }
              });
          }
        })
        .catch((err) => {
          console.log("获取失败", err.data);
        });
    }
  },
};
</script>

<style lang="less" scoped>
.pay {
  background-color: #f9f9f9;
  height: 100vh;
  .order-list {
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
        ::v-deep .van-card__price {
          font-size: 14px;
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
        .text {
          color: #ee0a24;
        }
      }
    }
  }
  .submit {
    .num {
      color: #646566;
      padding-top: 3px;
      // padding-right: 12px;
    }
    .van-submit-bar__text {
      flex: none;
      text-align: left;
    }
    .van-submit-bar__button--danger {
      background: #07c160;
    }
  }
}
</style>
