var route = require('express').Router()
var api = require('../api')
var urls = require('../api/apiurl').Air

route.get('/CtpvnList', function(req, res){
    var query = req.query
    if (!query.sido || !query.search){
        return res.send("Parameter Error, require sido, search")
    }
    api(urls.getCtprvnList(query.sido, query.search, 200), function(data){
        return res.json(data)
    })
})

route.get('/CtpvnRltmMesure', function(req, res){
    var query = req.query
    if (!query.sido){
        return res.send("Parameter Error, require sido")
    }
    api(urls.getCtprvnRltmMesureDnsty(query.sido), function(data){
        return res.json(data)
    })
})


module.exports = route