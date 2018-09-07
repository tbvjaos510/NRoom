var request = require('request')
var xml2js = require('xml2js')
var parser = new xml2js.Parser({explicitArray : false})

module.exports = function (url, callback) {
    request(url, function (err, re, body) {
        parser.parseString(body, (err, result) => {
            console.log(result)
        })
    })
}