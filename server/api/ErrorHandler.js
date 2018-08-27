function AreaNotFoundException(message) {
    this.message = message || "해당 지역명이 존재하지 않습니다."
    this.name = "AreaNotFoundExceptpon"
}
AreaNotFoundException.prototype = new Error()
AreaNotFoundException.prototype.constructor = AreaNotFoundException