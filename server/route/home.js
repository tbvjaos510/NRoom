var route = require('express').Router()
var api = require('../api')
var urls = require('../api/apiurl').Home

route.get('/ApartMentRealEstate', function (req, res) {
    var query = req.query;
    if (!query.Area || !query.YMD) {
        return res.send("Parameter Error, require Area, YMD")
    }
    try {
        api(urls.ApartMentRealEstateURL(query.Area, query.YMD), function (data) {
            res.json(data)
        })
    } catch (e) {
        return res.send("지역 이름을 잘못 적었습니다.")
    }
})

route.get('/TownhouseRealEstate', function (req, res) {
    var query = req.query;
    if (!query.Area || !query.YMD) {
        return res.send("Parameter Error, require Area, YMD")
    }
    try {
        api(urls.TownhouseRealEstateURL(query.Area, query.YMD), function (data) {
            res.json(data)
        })
    } catch (e) {
        return res.send("지역 이름을 잘못 적었습니다.")
    }
})

route.get('/DetachedRealEstate', function (req, res) {
    var query = req.query;
    if (!query.Area || !query.YMD) {
        return res.send("Parameter Error, require Area, YMD")
    }
    try {
        api(urls.DetachedRealEstateURL(query.Area, query.YMD), function (data) {
            res.json(data)
        })
    } catch (e) {
        return res.send("지역 이름을 잘못 적었습니다.")
    }
})
route.get('/RTMSOffTrade', function (req, res) {
    var query = req.query;
    if (!query.Area || !query.YMD) {
        return res.send("Parameter Error, require Area, YMD")
    }
    try {
        api(urls.RTMSOffTradeURL(query.Area, query.YMD), function (data) {
            res.json(data)
        })
    } catch (e) {
        return res.send("지역 이름을 잘못 적었습니다.")
    }
})
route.get('/RTMSLandTrade', function (req, res) {
    var query = req.query;
    if (!query.Area || !query.YMD) {
        return res.send("Parameter Error, require Area, YMD")
    }
    try {
        api(urls.RTMSLandTradeURL(query.Area, query.YMD), function (data) {
            res.json(data)
        })
    } catch (e) {
        return res.send("지역 이름을 잘못 적었습니다.")
    }
})
route.get('/ApartRentData', function (req, res) {
    var query = req.query;
    if (!query.Area || !query.YMD) {
        return res.send("Parameter Error, require Area, YMD")
    }
    try {
        api(urls.ApartRentDataURL(query.Area, query.YMD), function (data) {
            res.json(data)
        })
    } catch (e) {
        return res.send("지역 이름을 잘못 적었습니다.")
    }
})

route.get('/TownhouseRealRent', function (req, res) {
    var query = req.query;
    if (!query.Area || !query.YMD) {
        return res.send("Parameter Error, require Area, YMD")
    }
    try {
        api(urls.TownhouseRealRentURL(query.Area, query.YMD), function (data) {
            res.json(data)
        })
    } catch (e) {
        return res.send("지역 이름을 잘못 적었습니다.")
    }
})
route.get('/DetachedRealRent', function (req, res) {
    var query = req.query;
    if (!query.Area || !query.YMD) {
        return res.send("Parameter Error, require Area, YMD")
    }
    try {
        api(urls.DetachedRealRentURL(query.Area, query.YMD), function (data) {
            res.json(data)
        })
    } catch (e) {
        return res.send("지역 이름을 잘못 적었습니다.")
    }
})
route.get('/DetailApartEstate', function (req, res) {
    
    var query = req.query;
    if (!query.Area || !query.YMD) {
        return res.send("Parameter Error, require Area, YMD")
    }
    try {
        api(urls.DetailApartEstateURL(query.Area, query.YMD), function (data) {
            res.json(data)
        })
    } catch (e) {
        return res.send("지역 이름을 잘못 적었습니다.")
    }
})

module.exports = route
