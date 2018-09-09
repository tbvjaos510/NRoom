var router = require('express').Router()
var conn = require('mysql').createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'nroom'
})
conn.connect()
router.get('/realtrade', (req, res) => {
    if (!req.query.시도명)
        return res.json({success : false, message : '인자값이 전달되지 않았습니다. (시도명)'}).status(101)
    conn.query(`select lcode.*, hometrade.* from hometrade join lcode on hometrade.지역번호 = lcode.시군구코드 where lcode.시도명 = '${req.query.시도명}' ${req.query.시군구명? `and lcode.시군구명='${req.query.시군구명}'`:''} ${req.query.법정동?`and hometrade.법정동='${req.query.법정동}'` : ''} `, (err, results)=>{
        if (err){
            console.log(err)
        return res.json({success:false, message:'MySQL Query Error'}).status(500)
        }
        return res.json({success:true, message:'성공', length:results.length,  data:results}).status(200)
    })
})

router.get('/juntrade', (req, res) => {
    if (!req.query.시도명)
        return res.json({success : false, message : '인자값이 전달되지 않았습니다. (시도명)'}).status(101)
    conn.query(`select lcode.*, hometrade2.* from hometrade2 join lcode on hometrade2.지역번호 = lcode.시군구코드 where lcode.시도명 = '${req.query.시도명}' ${req.query.시군구명? `and lcode.시군구명='${req.query.시군구명}'`:''} ${req.query.법정동?`and hometrade2.법정동='${req.query.법정동}'` : ''} `, (err, results)=>{
        if (err){
            console.log(err)
        return res.json({success:false, message:'MySQL Query Error'}).status(500)
        }
        return res.json({success:true, message:'성공', length:results.length,  data:results}).status(200)
    })
})
router.get('/getEnvir', (req, res) => {
    if (!req.query.시도명 || !req.query.시군구명){
        return res.json({success:false, message:'인자값이 전달되지 않았습니다. (시도명, 시군구명)'}).status(101)
    }
    conn.query(`select * from envir where sido like '%${req.query.시도명}%' and city = '${req.query.시군구명}'`, (err, results)=>{
        if (err){
            console.log(err)
            return res.json({success:false, message:'MySQL Query Error'}).status(500)
        }
        return res.json({success:true, message:'성공',  data:results[0]}).status(200)
    })
})


router.get('/avgEnvir', (req, res) => {
    conn.query(`select avg(so2Value) as so2Value, avg(coValue) as coValue, avg(o3Value) as o3Value, avg(no2Value) as no2Value, avg(pm10Value) as pm10Value, avg(pm25Value) as pm25Value from envir ${req.query.시도명 ? `where sido like '%${req.query.시도명}%'`:''}`, (err, results)=>{
        if (err){
            console.log(err)
            return res.json({success:false, message:'MySQL Query Error'}).status(500)
        }
        return res.json({success:true, message:'성공',  data:results[0]}).status(200)
    })
})

module.exports = router