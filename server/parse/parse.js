var mysql = require('mysql')
var api = require('../api')
var url = require('../api/apiurl')

var conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'nroom'
})
function getData(code) {
    return new Promise((resolve, reject) => {
        api(url.Home.ApartMentRealEstateURL(code, 201809), (data) => {
            data = data.item;
            if (typeof (data) == Array) {
                for (var i in data) {
                    conn.query(`insert into HomeTrade (지역번호, 법정동, 집종류, 건물명, 전용면적, 층, 건축년도, 일, 지번) values ('${i.지역코드}', '${i.법정동}', '1', '${i.아파트}', '${i.전용면적}', '${i.층}', '${i.건축년도}', '${i.일}', '${i.지번}')`)
                }
            } else if (typeof (data) == Object) {
                var i = data
                conn.query(`insert into HomeTrade (지역번호, 법정동, 집종류, 건물명, 전용면적, 층, 건축년도, 일, 지번) values ('${i.지역코드}', '${i.법정동}', '1', '${i.아파트}', '${i.전용면적}', '${i.층}', '${i.건축년도}', '${i.일}', '${i.지번}')`)

            }
            return resolve()
        })
    })
}
function Parse() {
    conn.connect()
    conn.query('select * from lcode', function (err, value, cb) {
        if (!err) {
            for (var i of value) {
                getData(i).then(() => { console.log('end') });
            }
        }
    })

}

Parse()
