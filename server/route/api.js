var router = require('express').Router()
var request = require('../api')
var apiurl = require('../api/apiurl')
var conn = require('mysql').createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'nroom'
})
conn.connect()

router.get('/realtrade', (req, res) => {
    if (!req.query.시도명)
        return res.json({ success: false, message: '인자값이 전달되지 않았습니다. (시도명)' }).status(101)
    if (req.query.검색조건 && req.query.검색조건 == '미세먼지') {
        return conn.query(`select lcode.*, hometrade.*, envir.* from hometrade join lcode on lcode.시군구코드 = hometrade.지역번호 join envir on hometrade.지역번호 = envir.시군구코드  where 지역번호 in  (select e.시군구코드 from envir e, (select avg(coValue) as a, avg(pm10Value) as b, avg(pm25Value) as c from envir where sido = '${req.query.시도명}') as a where e.coValue <= a.a and e.pm10Value <= a.b and e.pm25Value <= a.c and e.sido = '${req.query.시도명}')`, (err, results) => {
            if (err) {
                console.log(err)
                return res.json({ success: false, message: 'MySQL Query Error' }).status(500)
            }
            return res.json({ success: true, message: '성공', length: results.length, data: results }).status(200)
        })
    }
    if (req.query.검색조건 && req.query.검색조건 == '대피소수') {
        return conn.query(`select lcode.*, hometrade.* from hometrade join lcode on hometrade.지역번호 = lcode.시군구코드 where lcode.시도명 = '${req.query.시도명}' ${req.query.시군구명 ? `and lcode.시군구명='${req.query.시군구명}'` : ''} ${req.query.법정동 ? `and hometrade.법정동='${req.query.법정동}'` : ''} and hometrade.대피소수 > 0 `, (err, results) => {
            if (err) {
                console.log(err)
                return res.json({ success: false, message: 'MySQL Query Error' }).status(500)
            }
            return res.json({ success: true, message: '성공', length: results.length, data: results }).status(200)
        })
    }
    return conn.query(`select lcode.*, hometrade.* from hometrade join lcode on hometrade.지역번호 = lcode.시군구코드 where lcode.시도명 = '${req.query.시도명}' ${req.query.시군구명 ? `and lcode.시군구명='${req.query.시군구명}'` : ''} ${req.query.법정동 ? `and hometrade.법정동='${req.query.법정동}'` : ''} `, (err, results) => {
        if (err) {
            console.log(err)
            return res.json({ success: false, message: 'MySQL Query Error' }).status(500)
        }
        return res.json({ success: true, message: '성공', length: results.length, data: results }).status(200)
    })
})
router.get('/juntrade', (req, res) => {
    if (!req.query.시도명)
        return res.json({ success: false, message: '인자값이 전달되지 않았습니다. (시도명)' }).status(101)
    if (req.query.검색조건 && req.query.검색조건 == '미세먼지') {
        return conn.query(`select lcode.*, hometrade2.*, envir.* from hometrade2 join lcode on hometrade2.지역번호 = lcode.시군구코드 join envir on hometrade2.지역번호 = envir.시군구코드 where 지역번호  in  (select e.시군구코드 from envir e, (select avg(coValue) as a, avg(pm10Value) as b, avg(pm25Value) as c from envir where sido = '${req.query.시도명}') as a where e.coValue <= a.a and e.pm10Value <= a.b and e.pm25Value <= a.c and e.sido = '${req.query.시도명}');`, (err, results) => {
            if (err) {
                console.log(err)
                return res.json({ success: false, message: 'MySQL Query Error' }).status(500)
            }
            return res.json({ success: true, message: '성공', length: results.length, data: results }).status(200)
        })
    }
    if (req.query.검색조건 && req.query.검색조건 == '대피소수') {
        return conn.query(`select lcode.*, hometrade2.* from hometrade2 join lcode on hometrade2.지역번호 = lcode.시군구코드 where lcode.시도명 = '${req.query.시도명}' ${req.query.시군구명 ? `and lcode.시군구명='${req.query.시군구명}'` : ''} ${req.query.법정동 ? `and hometrade2.법정동='${req.query.법정동}'` : ''} and hometrade2.대피소수 > 0 `, (err, results) => {
            if (err) {
                console.log(err)
                return res.json({ success: false, message: 'MySQL Query Error' }).status(500)
            }
            return res.json({ success: true, message: '성공', length: results.length, data: results }).status(200)
        })
    }
    return conn.query(`select lcode.*, hometrade2.* from hometrade2 join lcode on hometrade2.지역번호 = lcode.시군구코드 where lcode.시도명 = '${req.query.시도명}' ${req.query.시군구명 ? `and lcode.시군구명='${req.query.시군구명}'` : ''} ${req.query.법정동 ? `and hometrade2.법정동='${req.query.법정동}'` : ''} `, (err, results) => {
        if (err) {
            console.log(err)
            return res.json({ success: false, message: 'MySQL Query Error' }).status(500)
        }
        return res.json({ success: true, message: '성공', length: results.length, data: results }).status(200)
    })
})
router.get('/Alltrade', (req, res) => {
    return conn.query('select lcode.*, hometrade.* from hometrade join lcode on hometrade.지역번호 = lcode.시군구코드', (err, results) => {
        if (err) {
            console.log(err)
            return res.json({ success: false, message: 'MySQL Query Error' }).status(500)
        }
        return conn.query('select lcode.*, hometrade2.* from hometrade2 join lcode on hometrade2.지역번호 = lcode.시군구코드', (err, results2) => {
            if (err) {
                console.log(err)
                return res.json({ success: false, message: 'MySQL Query Error' }).status(500)
            }
            return res.json({ success: true, message: '성공', realCnt: results.length,  junCnt: results2.length,real: results, jun: results2 }).status(200)
        })
    })
})

router.get('/getEnvir', (req, res) => {
    if (!req.query.시도명 || !req.query.시군구명) {
        return res.json({ success: false, message: '인자값이 전달되지 않았습니다. (시도명, 시군구명)' }).status(101)
    }
    conn.query(`select * from envir where sido like '%${req.query.시도명}%' and city = '${req.query.시군구명}'`, (err, results) => {
        if (err) {
            console.log(err)
            return res.json({ success: false, message: 'MySQL Query Error' }).status(500)
        }
        return res.json({ success: true, message: '성공', data: results[0] }).status(200)
    })
})
// 시도명을 주면 해당 시도의 평균 값, 안주면 전국 평군값
router.get('/avgEnvir', (req, res) => {
    if (!req.query.시도명)
        return res.json({ success: false, message: '인자값이 전달되지 않았습니다. (시도명)' }).status(101)
    conn.query(`select avg(so2Value) as so2Value, avg(coValue) as coValue, avg(o3Value) as o3Value, avg(no2Value) as no2Value, avg(pm10Value) as pm10Value, avg(pm25Value) as pm25Value from envir ${req.query.시도명 ? `where sido like '%${req.query.시도명}%'` : ''}`, (err, results) => {
        if (err) {
            console.log(err)
            return res.json({ success: false, message: 'MySQL Query Error' }).status(500)
        }
        return res.json({ success: true, message: '성공', data: results[0] }).status(200)
    })
})
// 버스정류소 목록 조회
router.get('/busPos', (req, res) => {
    if (!req.query.lat || !req.query.lng)
        return res.json({ success: false, message: '인자값이 전달되지 않았습니다. (lat, lng)' }).status(101)

    return request(apiurl.Bus.getBusInPos(req.query.lat, req.query.lng), (data) => res.json({ success: true, message: '성공', data: data.items.item }).status(200))
})
module.exports = router