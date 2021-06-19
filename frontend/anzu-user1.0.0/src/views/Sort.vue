<template>
  <div class="sort">
    <div class="sidebar">
      <van-sidebar
        v-model="activeKey"
        @change="onChange"
      >
        <van-sidebar-item title="数码" />
        <van-sidebar-item title="手表" />
        <van-sidebar-item title="家具" />
        <van-sidebar-item title="玩具" />
      </van-sidebar>
    </div>
    <div class="content">
      <van-list
        v-model="loading"
        :finished="finished"
        finished-text="已全部加载完毕"
        @load="onLoad"
        :key="componentKey"
      >
        <van-cell
          v-for="(item, index) in list"
          :key="index"
        >
          <van-card
            :price="' ' + item.price + ' / ' + item.leaseTime + '天'"
            :title="item.goodsName"
            :thumb="item.picUrl"
            @click="onDetail(item.id)"
          ><template #tags>
              <van-tag
                plain
                type="primary"
              >{{
                mode(item.categoryId)
              }}</van-tag>
              <van-tag
                style="margin-left:5px"
                plain
                type="primary"
                v-show="item.mode == 2 ? true : false"
              >支持买断</van-tag>
            </template></van-card>
        </van-cell>
      </van-list>
    </div>
  </div>
</template>

<script>
import { Toast } from 'vant'
export default {
  inject: ['reload'],
  data() {
    return {
      activeKey: 0,
      componentKey: 0,
      list: [],
      listAll: [],
      listAll0: [],
      listAll1: [],
      loading: false,
      finished: false,
      index: 0
    }
  },
  methods: {
    onDetail(id) {
      console.log(id)
      this.$router.push({
        path: '/detail',
        query: {
          goodsId: id
        }
      })
    },
    onChange(index) {
      if (index === 0) {
        this.componentKey = index
        this.$ajax
          .get('api/goods/findAllByCategoryId?category_id=1')
          .then(res => {
            if (res.data.port === '200') {
              for (let item of res.data.data) {
                item.leaseTime = Math.ceil(item.leaseTime / 86400)
                // console.log(item.leaseTime);
              }
              this.list = res.data.data
            } else {
              this.list = []
            }
          })
          .catch(err => {
            console.log('分类1获取失败', err)
          })
      } else if (index === 1) {
        this.componentKey = index
        this.$ajax
          .get('api/goods/findAllByCategoryId?category_id=2')
          .then(res => {
            if (res.data.port === '200') {
              for (let item of res.data.data) {
                item.leaseTime = Math.ceil(item.leaseTime / 86400)
                // console.log(item.leaseTime);
              }
              this.list = res.data.data
            } else {
              this.list = []
            }
          })
          .catch(err => {
            console.log('分类2获取失败', err)
          })
      } else if (index === 2) {
        this.componentKey = index
        this.$ajax
          .get('api/goods/findAllByCategoryId?category_id=3')
          .then(res => {
            if (res.data.port === '200') {
              for (let item of res.data.data) {
                item.leaseTime = Math.ceil(item.leaseTime / 86400)
                // console.log(item.leaseTime);
              }
              this.list = res.data.data
            } else {
              this.list = []
            }
          })
          .catch(err => {
            console.log('分类3获取失败', err)
          })
      } else if (index === 3) {
        this.componentKey = index
        this.$ajax
          .get('api/goods/findAllByCategoryId?category_id=4')
          .then(res => {
            if (res.data.port === '200') {
              for (let item of res.data.data) {
                item.leaseTime = Math.ceil(item.leaseTime / 86400)
                // console.log(item.leaseTime);
              }
              this.list = res.data.data
            } else {
              this.list = []
            }
          })
          .catch(err => {
            console.log('分类3获取失败', err)
          })
      }
    },
    onLoad() {
      // 异步更新数据
      // setTimeout 仅做示例，真实场景中一般为 ajax 请求
      setTimeout(() => {
        Toast.loading({
          message: '加载中...',
          forbidClick: true,
          loadingType: 'spinner',
          duration: 300
        })
        for (let i = 0; i < 8; i++) {
          if (this.index === this.listAll.length) {
            break
          } else {
            this.list.push(this.listAll[this.index++])
          }
        }

        // 加载状态结束
        this.loading = false

        // 数据全部加载完成
        if (this.index === this.listAll.length) {
          this.finished = true
        }
      }, 800)
    }
  },
  computed: {
    mode() {
      return mode => {
        return mode <= 2
          ? mode == 1
            ? '数码'
            : '手表'
          : mode == 3
          ? '家具'
          : '玩具'
      }
    }
  },
  mounted() {
    this.activeKey = this.$store.state.sys.nowSort - 1
    this.$ajax
      .get(
        `api/goods/findAllByCategoryId?category_id=${this.$store.state.sys.nowSort}`
      )
      .then(res => {
        for (let item of res.data.data) {
          item.leaseTime = Math.ceil(item.leaseTime / 86400)
        }
        this.listAll = res.data.data
      })
      .catch(err => {
        console.log('分类页获取失败', err)
      })
  },
  watch: {
    activeKey() {
      this.$store.commit('setNowSort', this.activeKey + 1);
    }
  }
}
</script>

<style scoped>
.sort {
  background-color: #f7f8fa;
  display: flex;
  flex-direction: row;
  padding-left: 80px;
  height: 100vh;
  width: 375px;
}
.sidebar {
  position: fixed;
  float: left;
  background-color: #f7f8fa;
  width: 80px;
  margin-left: -80px;
  height: 100%;
}
.content {
  /* flex-grow: 1; */
  /* width: 70%; */
  float: right;
  width: 100%;
  /* padding-bottom: 100px; */
}
.van-list {
  margin-bottom: 50px;
  padding-bottom: 50px;
}
.van-card {
  border-radius: 5px;
  padding: 8px 12px;
}
.van-cell {
  padding: 10px 12px;
}
::v-deep .van-sidebar {
  position: fixed;
}
::v-deep .van-list__finished-text {
  background-color: white;
}
.van-card__title[data-v-dfab6a88] {
  padding-bottom: 5px;
}
.van-tag--plain {
  padding: 3px 4px 2px 4px;
}
.van-card__title {
  text-align: start;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  line-clamp: 2;
}
::v-deep .van-sidebar-item__text {
  font-weight: bold;
  font-size: 16px;
}
/* .van-sidebar-item--select::before {
  background-color: #07c160;
} */
</style>
