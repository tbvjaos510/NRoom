var request = require('request')
var apikey = require('../private/key.json').jusoKey
module.exports = (bname, bno, bname2, cb) => {
    var search = bname +' ' + bname2
    request(`http://www.juso.go.kr/addrlink/addrLinkApi.do?confmKey=${apikey}&currentPage=1&countPerPage=10&keyword=${encodeURIComponent(search)}&resultType=json`, (err, request, body)=>{
    body = JSON.parse(body)    
    cb(body.results.juso[0])
    })
}