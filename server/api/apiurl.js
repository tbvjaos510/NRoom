var apikey = require('../private/key.json').key
var locale = require('./locale.json')
var Error = require('./ErrorHandler')

/**
 * 실거래가 정보 오픈API
 */
class Home {
    // 실거래자료 URL 기본링크
    static get RealTradeURL() {
        return `http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/`
    }

    // 신고정보 URL 기본링크
    static get RTMSDataURL() {
        return `http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/`
    }

    static get Parameter(){
        return `&numOfRows=500`
    }

    /**
     * 아파트 매매 실거래자료 URL
     * @param {String} Area 지역 이름
     * @param {number} YMD 계약월
     */
    static ApartMentRealEstateURL(Area, YMD) {
        if (!locale[Area])
            throw Error.AreaNotFoundException()
        return this.RealTradeURL + `getRTMSDataSvcAptTrade?LAWD_CD=${locale[Area]}&DEAL_YMD=${YMD}&serviceKey=${apikey}` + this.Parameter
    }

    /**
     * 연립다세대 매매 실거래자료 URL
     * @param {String} Area 지역 이름
     * @param {number} YMD 계약월
     */
    static TownhouseRealEstateURL(Area, YMD) {
        if (!locale[Area])
            throw Error.AreaNotFoundException()
        return this.RealTradeURL + `getRTMSDataSvcRHTrade?LAWD_CD=${locale[Area]}&DEAL_YMD=${YMD}&serviceKey=${apikey}`+ this.Parameter
    }


    /**
     * 단독/다가구 매매 실거래자료 URL
     * @param {String} Area 지역 이름
     * @param {number} YMD 계약월
     */
    static DetachedRealEstateURL(Area, YMD) {
        if (!locale[Area])
            throw Error.AreaNotFoundException()
        return this.RealTradeURL + `getRTMSDataSvcSHTrade?LAWD_CD=${locale[Area]}&DEAL_YMD=${YMD}&serviceKey=${apikey}`+ this.Parameter
    }


    /**
     * 오피스텔 매매 신고정보 URL
     * @param {String} Area 지역 이름
     * @param {number} YMD 계약월
     */
    static RTMSOffTradeURL(Area, YMD) {
        if (!locale[Area])
            throw Error.AreaNotFoundException()
        return this.RTMSDataURL + `getRTMSDataSvcOffiTrade?LAWD_CD=${locale[Area]}&DEAL_YMD=${YMD}&serviceKey=${apikey}`+ this.Parameter
    }

    /**
     * 토지매매 신고정보 URL
     * @param {String} Area 지역 이름
     * @param {number} YMD 계약월
     */
    static RTMSLandTradeURL(Area, YMD) {
        if (!locale[Area])
            throw Error.AreaNotFoundException()
        return this.RTMSDataURL + `getRTMSDataSvcLandTrade?LAWD_CD=${locale[Area]}&DEAL_YMD=${YMD}&serviceKey=${apikey}`+ this.Parameter
    }


    /**
     * 아파트 전월세 자료 URL
     * @param {String} Area 지역 이름
     * @param {number} YMD 계약월
     */
    static ApartRentDataURL(Area, YMD) {
        if (!locale[Area])
            throw Error.AreaNotFoundException()
        return this.RealTradeURL + `getRTMSDataSvcAptRent?LAWD_CD=${locale[Area]}&DEAL_YMD=${YMD}&serviceKey=${apikey}`+ this.Parameter
    }

    /**
     * 연립다세대 전,월세 신고정보 URL
     * @param {String} Area 지역 이름
     * @param {number} YMD 계약월
     */
    static TownhouseRealRentURL(Area, YMD) {
        if (!locale[Area])
            throw Error.AreaNotFoundException()
        return this.RealTradeURL + `getRTMSDataSvcRHRent?LAWD_CD=${locale[Area]}&DEAL_YMD=${YMD}&serviceKey=${apikey}`+ this.Parameter
    }

    /**
     * 단독/다가구 전,월세 신고정보 URL
     * @param {String} Area 지역 이름
     * @param {number} YMD 계약월
     */
    static DetachedRealRentURL(Area, YMD) {
        if (!locale[Area])
            throw Error.AreaNotFoundException()
        return this.RealTradeURL + `getRTMSDataSvcSHRent?LAWD_CD=${locale[Area]}&DEAL_YMD=${YMD}&serviceKey=${apikey}`+ this.Parameter
    }

    /**
     * 오피스텔 전,월세 신고정보 URL
     * @param {String} Area 지역 이름
     * @param {number} YMD 계약월
     */
    static DetachedRealRentURL(Area, YMD) {
        if (!locale[Area])
            throw Error.AreaNotFoundException()
        return this.RealTradeURL + `getRTMSDataSvcOffiRent?LAWD_CD=${locale[Area]}&DEAL_YMD=${YMD}&serviceKey=${apikey}`+ this.Parameter
    }

    /**
     * 아파트매매 실거래 상세자료 조회 URL
     * @param {String} Area 지역 이름
     * @param {number} YMD 계약월
     */
    static DetailApartEstateURL(Area, YMD) {
        if (!locale[Area])
            throw Error.AreaNotFoundException()
        return this.RTMSDataURL + `getRTMSDataSvcAptTradeDev?LAWD_CD=${locale[Area]}&DEAL_YMD=${YMD}&serviceKey=${apikey}`+ this.Parameter
    }

}

class Air {
    static get airKoreaAPI() { return "http://openapi.airkorea.or.kr/openapi/services/rest/" }
    static getCtprvnList(sido, searchCondition, rows) {
        return this.airKoreaAPI + `ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?sidoName=${encodeURIComponent(sido)}&searchCondition=${searchCondition}&pageNo=1&numOfRows=${rows}&ServiceKey=${apikey}`
    }

}
module.exports.Home = Home

module.exports.Air = Air