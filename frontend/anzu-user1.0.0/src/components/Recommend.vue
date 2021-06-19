<template>
  <div class="recommend">
    <h3>精品推荐</h3>
    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多了"
      @load="onLoad"
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
        >
          <template #tags>
            <van-tag
              plain
              type="primary"
            >{{
              modeName(item.categoryId)
            }}</van-tag>
            <van-tag
              style="margin-left:5px"
              plain
              type="primary"
              v-show="item.mode == 2 ? true : false"
            >支持买断</van-tag>
          </template>
        </van-card>
      </van-cell>
    </van-list>
  </div>
</template>

<script>
import util from '../util/util.js'
export default {
  name: 'Recommend',
  data() {
    return {
      list1: [],
      list: [],
      loading: false,
      finished: false,
      index: 0
    }
  },
  methods: {
    onLoad() {
      // 异步更新数据
      // setTimeout 仅做示例，真实场景中一般为 ajax 请求
      setTimeout(() => {
        for (let i = 0; i < 5; i++) {
          if (this.index === this.list1.length) {
            break
          } else {
            this.list.push(this.list1[this.index++])
          }
        }

        // 加载状态结束
        this.loading = false

        // 数据全部加载完成
        if (this.index === this.list1.length) {
          this.finished = true
        }
      }, 1500)
    },
    onDetail(id) {
      this.$router.push({
        path: '/detail',
        query: {
          goodsId: id
        }
      })
    }
  },
  computed: {
    modeName() {
      return id => {
        return id <= 2 ? (id == 1 ? '数码' : '手表') : id == 3 ? '家具' : '玩具'
      }
    }
  },
  mounted() {
    this.$ajax
      .get('api/goods/findAllShuffle')
      .then(res => {
        if (res.data.port == '200') {
          this.list1 = res.data.data.slice(0, 20)
          for (let item of this.list1) {
            item.leaseTime = Math.ceil(item.leaseTime / 86400)
            // console.log(item.leaseTime);
          }
        }
      })
      .catch(err => {
        console.log('精品推荐获取失败', err)
      })
  }
}
</script>

<style scoped>
.recommend {
  padding-bottom: 50px;
}
h3 {
  display: flex;
  padding: 15px 0 5px 12px;
  background-color: white;
}
.van-cell {
  padding-left: 12px;
  padding-right: 12px;
}
.van-card {
  border-radius: 8px;
  padding: 8px 12px;
}
.van-card__content {
  padding-left: 5px;
}
.van-card__title {
  line-height: 24px;
  font-weight: bold;
  font-size: 12px;
  text-align: start;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  line-clamp: 2;
}
.van-card__price {
  color: #ee0a24;
}
.van-card__title[data-v-dfab6a88] {
  padding-bottom: 5px;
}
.van-tag--plain {
  padding: 3px 4px 2px 4px;
}
.van-card__title[data-v-dfab6a88][data-v-dfab6a88] {
  font-size: 12px;
  padding-bottom: 0;
}
.van-card__title[data-v-dfab6a88] {
  line-height: 16px;
}
</style>
