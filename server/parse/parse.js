var mysql = require('mysql')
var api = require('../api')
var url = require('../api/apiurl')

var conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'nroom'
})
conn.connect()
function getData(code, idx) {
    return new Promise((resolve, reject) => {
        api(url.Home.TownhouseRealEstateURL(code, 201809), (data) => {

            data = data.items.item
            if (!data)
                return resolve(code, 0)
            // if (typeof (data) == Array) {
            if (!data[0])
                data = new Array(data)
            for (var i of data) {
                conn.query(`insert into HomeTrade (지역번호, 법정동, 집종류, 건물명, 전용면적, 층, 건축년도, 일, 지번, 거래금액) values ('${i.지역코드.trim()}', '${i.법정동.trim()}', '2', '${i.연립다세대.trim()}', '${i.전용면적}', '${i.층}', '${i.건축년도}', '${i.일}', '${i.지번}', '${i.거래금액.trim()}')`)
            }
            // } else if (typeof (data) == Object) {
            // var i = data
            // conn.query(`insert into HomeTrade (지역번호, 법정동, 집종류, 건물명, 전용면적, 층, 건축년도, 일, 지번) values ('${i.지역코드}', '${i.법정동}', '1', '${i.아파트}', '${i.전용면적}', '${i.층}', '${i.건축년도}', '${i.일}', '${i.지번}')`)

            // }
            return resolve(code, idx)
        })
    })
}
var index = 0
var ids, interval
function Parse() {
    conn.query('select * from lcode', function (err, value, cb) {
        if (!err) {
            // for (var i of value) {
            //     // getData(i).then(() => { console.log('end') });
            // console.log(i.시군구코드)     
            // }
            ids = value
            interval = setInterval(() => {
                getData(ids[index].시군구코드)
                .then((code, length) => { 
                    console.log('end ', code, length) 
                }).catch(() => {
                    console.log('error')
                })
                index++
                console.log('start ', index)
                if (index >= ids.length)
                    clearInterval(interval)
            }, 100)
        }
        // conn.end()
    })

}

  Parse()
//getData(11530).then(() => conn.end())
// conn.end()