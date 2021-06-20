module.exports = {
  devServer: {
    host: '192.168.123.54', // 172.20.10.2 // 192.168.123.54
    // host: '172.20.10.2', // 172.20.10.2 // 192.168.123.54
    port: 8080,
    https: false,
    open: false,
    proxy: {
      '/api': {
        target: 'http://47.111.10.102:8085',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
}
