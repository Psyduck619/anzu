<template>
  <div class="home">
    <!-- <div class="title">
      <van-nav-bar title="安租" />
    </div> -->
    <!-- 搜索栏 -->
    <van-sticky>
      <van-search
        v-model="value"
        placeholder="想体验什么？搜搜看"
      />
    </van-sticky>
    <van-pull-refresh
      v-model="isLoading"
      loosing-text="松开刷新"
      success-text="刷新成功"
      @refresh="onRefresh"
    >
      <!-- 轮播图 -->
      <van-swipe
        :autoplay="3000"
        class="swipe"
      >
        <van-swipe-item
          v-for="(image, index) in swipeImages"
          :key="index"
        >
          <img
            height="150"
            v-lazy="image"
          />
        </van-swipe-item>
      </van-swipe>
      <!-- 分类提示 -->
      <van-grid
        :gutter="0"
        :column-num="5"
        :border="false"
        clickable
      >
        <van-grid-item
          icon="http://yuan619.xyz/anzu/icons/%E6%95%B0%E7%A0%81.png"
          text="数码"
          @click="onOne"
        />
        <van-grid-item
          icon="http://yuan619.xyz/anzu/icons/%E6%89%8B%E8%A1%A8.png"
          text="手表"
          @click="onTwo"
        />
        <van-grid-item
          icon="http://yuan619.xyz/anzu/icons/%E5%AE%B6%E5%85%B7.png"
          text="家具"
          @click="onThree"
        />
        <van-grid-item
          icon="http://yuan619.xyz/anzu/icons/%E7%8E%A9%E5%85%B7.png"
          text="玩具"
          @click="onFour"
        />
        <van-grid-item
          icon="http://yuan619.xyz/anzu/icons%E7%94%B5%E6%B1%A0.png"
          text="更多"
        />
      </van-grid>
      <!-- 尝鲜好物 -->
      <fresh-thing></fresh-thing>
      <!-- 精品推荐 -->
      <Recommend></Recommend>
    </van-pull-refresh>
  </div>
</template>

<script>
import FreshThing from '../components/FreshThing.vue'
import Recommend from '../components/Recommend'
export default {
  inject: ['reload'],
  components: { FreshThing, Recommend },
  data() {
    return {
      isLoading: false,
      value: '',
      swipeImages: [
        'https://cdn.cnbj1.fds.api.mi-img.com/product-images/mi11ultra/section1-1.jpg',
        'https://www1.djicdn.com/cms/uploads/b52000038fe78dcaf559bf9d7bfe5284@1x.jpg',
        '//www-file.huawei.com/-/media/corp2020/home/cbg/0602/matepad_pro_cn2.jpg',
        'https://www.longines.cn/media/wysiwyg/new-home/20200921-pc-1.jpg'
      ],
      freshList: []
    }
  },
  methods: {
    onRefresh() {
      setTimeout(() => {
        this.isLoading = false
      }, 1000)
      setTimeout(() => {
        this.reload()
      }, 2000)
    },
    onOne() {
      this.$store.commit('setNowSort', 1);
      this.$router.push({
        path: '/sort'
      })
    },
    onTwo() {
      this.$store.commit('setNowSort', 2);
      this.$router.push({
        path: '/sort'
      })
    },
    onThree() {
      this.$store.commit('setNowSort', 3);
      this.$router.push({
        path: '/sort'
      })
    },
    onFour() {
      this.$store.commit('setNowSort', 4);
      this.$router.push({
        path: '/sort'
      })
    }
  },
  mounted() {
    // 获取默认地址
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
h3 {
  display: flex;
  margin-left: 20px;
}
.home {
  width: 375px;
  height: 100vh;
  background-color: white;
}
.van-grid-item__text {
  color: black;
}
::v-deep .cont-dest[data-v-61705290] {
  font-size: 12px;
}
.swipe img {
  overflow: hidden;
  width: 100%;
  border-top: 1px solid #f4f4f4;
  border-bottom: 1px solid #f4f4f4;
}
</style>
