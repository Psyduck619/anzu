import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

let store = new Vuex.Store({
  state: {
    user: {
      id: -1,
      account: '',
      tel: '',
      pwd: '',
      login: false,
      balance: '??.??',
      nowGoods: 2,
      nowAddress: -1,
      checkedList: [],
      totalPrice: 0
    },
    sys: {
      lastPath: '/',
      loginBtn: false,
      nowSort: 1
    }
  },
  mutations: {
    setLastPath(state, payload) {
      // payload是接收的参数
      state.sys.lastPath = payload
    },
    setLoginBtn(state, payload) {
      // payload是接收的参数
      state.sys.loginBtn = payload
    },
    setLogin(state, payload) {
      // payload是接收的参数
      state.user.login = payload
    },
    setUserId(state, payload) {
      // payload是接收的参数
      state.user.id = payload
    },
    setNowSort(state, payload) {
      // payload是接收的参数
      state.sys.nowSort = payload
    },
    setNowAddress(state, payload) {
      // payload是接收的参数
      state.user.nowAddress = payload
    },
    setCheckedList(state, payload) {
      // payload是接收的参数
      state.user.checkedList = payload
    },
    setTotalPrice(state, payload) {
      // payload是接收的参数
      state.user.totalPrice = payload
    },
    setAccount(state, payload) {
      // payload是接收的参数
      state.user.account = payload
    }
  },
  actions: {},
  modules: {}
})

export default store
