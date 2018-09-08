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
        return res.json({success:true, message:'성공', length:results.length,  data:results})
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
        return res.json({success:true, message:'성공', length:results.length,  data:results})
    })
})
module.exports = router