var express = require('express')
var app = express()


app.get('/', function (req, res) {
    res.send("Test")
})

app.use("/Home", require('./route/home'))
app.use("/Air", require('./route/Air'))
app.use('/api', require('./route/api'))

app.listen(80, function () {  
    console.log("Server On 80 Port")
})