<template>
  <div class="add-address">
    <top-nav :title="title"></top-nav>
    <van-address-edit
      :address-info="addressInfo"
      :area-list="areaList"
      tel-maxlength="11"
      :area-columns-placeholder="['请选择', '请选择', '请选择']"
      @save="onSave"
    />
  </div>
</template>

<script>
import { areaList } from '@vant/area-data'
import topNav from '../components/TopNav.vue'
import { Toast } from 'vant'
export default {
  components: { topNav },
  data() {
    return {
      title: '新增收货地址',
      areaList,
      addressInfo: {
        name: '', //姓名
        tel: '', //电话
        // areaCode: "110000", //北京市
        // areaCode: "110100", //北京市
        areaCode: '', //西城区
        addressDetail: '' //详细地址
      },
      info: {}
    }
  },
  methods: {
    onSave(val) {
      this.info = val
      if (this.$route.query.mode === '1') {
        this.$ajax
          .post(
            '/api/user_address/add',
            this.$qs.stringify({
              address_code: val.areaCode,
              area: val.county,
              city: val.city,
              detail: val.addressDetail,
              name: val.name,
              prov: val.province,
              tel: val.tel,
              user_id: this.$store.state.user.id
            })
          )
          .then(res => {
            if (res.data.port === '200') {
              Toast('添加成功')
              // this.$store.state.user.login = true;
              // this.$store.state.user.id = res.data.id;
              this.$router.back(-1)
            }
            console.log(res.data)
          })
          .catch(err => {
            console.log('新增地址失败结果:', err)
          })
      } else {
        this.$ajax
          .post(
            '/api/user_address/modify',
            this.$qs.stringify({
              address_code: val.areaCode,
              area: val.county,
              city: val.city,
              detail: val.addressDetail,
              name: val.name,
              prov: val.province,
              tel: val.tel,
              id: this.$route.query.addressId,
              user_id: this.$store.state.user.id
            })
          )
          .then(res => {
            if (res.data.port === '200') {
              Toast('修改成功')
              // this.$store.state.user.login = true;
              // this.$store.state.user.id = res.data.id;
              this.$router.back(-1)
            }
            console.log(res.data)
          })
          .catch(err => {
            console.log('修改地址失败结果:', err)
          })
      }
    }
  },
  mounted() {
    // console.log(this.$route.query.mode);
    if (this.$route.query.mode === '1') {
      this.title = '新增收货地址'
      this.addressInfo = {}
    } else {
      this.title = '修改收货地址'
      this.$ajax
        .get('/api/user_address/findById?id=' + this.$route.query.addressId)
        .then(res => {
          console.log(res.data.data)
          this.addressInfo.name = res.data.data.name
          this.addressInfo.tel = res.data.data.tel
          this.addressInfo.areaCode = res.data.data.addressCode
          this.addressInfo.addressDetail = res.data.data.detail
          // this.reload();
        })
        .catch(err => {
          console.log('修改地址失败:', err)
        })
    }
  }
}
</script>

<style scoped>
.add-address {
  width: 375px;
}
::v-deep .van-button--danger {
  background-color: #07c160;
  border: 1px solid #07c160;
}
</style>
