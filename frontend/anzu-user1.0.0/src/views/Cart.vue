<template>
  <div class="cart">
    <TopNav :title="title"></TopNav>
    <div class="content">
      <div
        class="show-null"
        v-if="showNull"
      >
        <van-empty
          class="custom-image"
          image="https://img01.yzcdn.cn/vant/custom-empty-image.png"
          description="购物车是空的"
        />
      </div>
      <van-checkbox-group
        v-model="checkList"
        ref="checkboxGroup"
      >
        <van-checkbox
          v-for="(item, index) in list"
          :key="index"
          :name="item.id"
          @click="getIndex(index)"
        >
          <van-swipe-cell>
            <van-card
              :num="item.num"
              :price="' ' + item.price + ' / ' + item.leaseTime + '天'"
              :title="item.goodsName"
              :thumb="item.picUrl"
              :desc="'保证金: ￥' + item.deposit"
              class="goods-card"
            >
              <!-- <template #footer>
                <span>租期： 10 个月</span>
              </template> -->
            </van-card>
            <template #right>
              <van-button
                square
                text="删除"
                type="danger"
                class="delete-button"
                @click="onDelete(item.id)"
              />
            </template>
          </van-swipe-cell>
        </van-checkbox>
      </van-checkbox-group>
    </div>
    <div class="submit">
      <van-submit-bar
        :price="total"
        button-text="结算"
        @submit="onSubmit"
        :disabled="showSubmit"
      >
        <van-checkbox
          v-model="checked"
          @click="checkAll"
        >全选</van-checkbox>
      </van-submit-bar>
    </div>
  </div>
</template>

<script>
import TopNav from '../components/TopNav.vue'
import { Dialog, Toast } from 'vant'
export default {
  inject: [`reload`],
  components: {
    TopNav
  },
  data() {
    return {
      title: '购物车',
      checked: false,
      checkList: [],
      list: [],
      showNull: true,
      showSubmit: true
      // total: 0
    }
  },
  computed: {
    total() {
      let price = 0.0
      for (let item of this.list) {
        if (item.checked) {
          price += item.deposit * item.num
          price += item.price * item.num
        }
      }
      return price * 100
    }
  },
  methods: {
    onSubmit() {
      let price = this.total.toFixed(0)
      this.$store.commit('setCheckedList', this.checkList)
      this.$store.commit('setTotalPrice', price)
      this.$router.push(`/pay`)
    },
    checkAll() {
      if (!this.checked) {
        this.$refs.checkboxGroup.toggleAll(false)
        for (let item of this.list) {
          item.checked = false
        }
      }
      if (this.checked) {
        this.$refs.checkboxGroup.toggleAll(true)
        for (let item of this.list) {
          item.checked = true
        }
      }
    },
    getIndex(index) {
      this.list[index].checked = !this.list[index].checked
    },
    onDelete(id) {
      Dialog.confirm({
        message: '确定删除吗？'
      })
        .then(() => {
          this.$ajax
            .get(`/api/cart/deletebyid?id=${id}`)
            .then(res => {
              if (res.data.port == `200`) {
                console.log(res.data)
                this.reload()
              }
            })
            .catch(res => {
              console.log(`删除失败`, res)
            })
        })
        .catch(() => {
          // on cancel
        })
    },
    beforeClose({ position, instance }) {
      switch (position) {
        case 'left':
        case 'cell':
        case 'outside':
          instance.close()
          break
        case 'right':
          Dialog.confirm({
            message: '确定删除吗？'
          })
            .then(() => {
              instance.close()
              console.log()
            })
            .catch(() => {
              // on cancel
            })
          break
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
    if (this.$store.state.user.id !== -1) {
      this.$ajax
      .get('/api/cart/findAllByUserId?user_id=' + this.$store.state.user.id)
      .then(res => {
        // console.log(res.data);
        if (res.data.port === '200') {
          this.checked = true
          this.showSubmit = false
          this.showNull = false
          for (let item of res.data.data) {
            this.$ajax
              .get('/api/goods/findById?id=' + item.goodsId)
              .then(val => {
                let info = val.data.data
                this.list.push({
                  id: item.id,
                  goods_id: info.id,
                  goodsName: info.goodsName,
                  price: info.price,
                  mode: info.mode,
                  picUrl: info.picUrl,
                  deposit: info.deposit,
                  leaseTime: Math.ceil(info.leaseTime / 86400),
                  num: Number.parseInt(item.goodsNum),
                  checked: true
                })
              })
          }
        } else {
          // Toast.fail("请刷新重试!");
        }
      })
      .catch(res => {
        console.log('购物车信息获取失败', res.data)
        Toast.fail('请刷新重试!')
      })
    }
    
  },
  watch: {
    checkList(oval, nval) {
      if (
        this.checkList.length != this.list.length ||
        this.checkList.length == 0
      ) {
        this.checked = false
      } else {
        this.checked = true
      }
      if (this.checkList.length == 0) {
        this.showSubmit = true
      } else {
        this.showSubmit = false
      }
    },
    list() {
      this.checkList.length = 0
      for (let item of this.list) {
        this.checkList.push(item.id)
      }
    }
  }
}
</script>

<style scoped>
.cart {
  background-color: #f8f8f8;
  height: 100vh;
  width: 375px;
}

.content {
  text-align: start;
  background-color: #f8f8f8;
}

::v-deep .van-checkbox {
  padding-left: 16px;
  margin: 5px 0;
  margin-top: 10px;
  background-color: white;
  /* line-height: 16px; */
}

::v-deep .van-checkbox__label {
  width: 90%;
}

::v-deep .van-card__desc {
  height: 40px;
  /* line-height: 20px; */
  padding: 10px 0 0 0;
  max-height: 40px;
}

::v-deep .van-card__num {
  color: #323233;
}

::v-deep .van-card {
  padding-left: 5px;
}

.van-card__title {
  font-weight: bold;
}

.van-submit-bar__button--danger {
  background: #07c160;
}

.goods-card {
  margin: 0;
  background-color: white;
}

.delete-button {
  height: 100%;
}
</style>
