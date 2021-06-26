/*const isPro = Object.is(process.env.NODE_ENV, 'production')
 
module.exports = {
    baseUrl: isPro ? 'http://localhost:8888/api/' : 'api/',
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',
    proxyTable: {
      '/api/**': {
            target: 'http://localhost:8088',//设置调用的接口域名和端口号（默认端口号80）
            changeOrigin: true, //设置请求头
            pathRewrite: { //路径重写
                '^/api': '/' 
              }
              //这里理解成用‘/api’代替target里面的地址，
              //后面组件中我们掉接口时直接用api代替 
              //比如我要调用'http://40.00.100.100:3002/user/add',
              //直接写‘/api/user/add’即可
        }
    },
}*/

