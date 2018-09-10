var request = require('request')
var apikey = require('../private/key.json')
module.exports = (bname, bno, bname2, cb) => {
    var search = bname + ' ' + bname2
    // request(`http://www.juso.go.kr/addrlink/addrLinkApi.do?confmKey=${apikey}&currentPage=1&countPerPage=10&keyword=${encodeURIComponent(search)}&resultType=json`, (err, request, body)=>{
    // body = JSON.parse(body)    
    // cb(body.results.juso[0])
    // })
    request({ uri: `https://openapi.naver.com/v1/map/geocode?query=${encodeURIComponent(search)}&encoding=utf-8&coordType=1`, headers: { "X-Naver-Client-Id": apikey.naver.id, "X-Naver-Client-Secret": apikey.naver.secret }, method: "GET" }, (err, res, data) => {
        data = JSON.parse(data)
        if (data.result)
            cb(data.result.items[0])
        else {
            cb("no data " + search)
        }
    })

}