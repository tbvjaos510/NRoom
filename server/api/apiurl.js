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

    static get Parameter() {
        return `&numOfRows=500`
    }

    /**
     * 아파트 매매 실거래자료 URL
     * @param {String} Area 지역 번호
     * @param {number} YMD 계약월
     */
    static ApartMentRealEstateURL(Area, YMD) {
        return this.RealTradeURL + `getRTMSDataSvcAptTrade?LAWD_CD=${Area}&DEAL_YMD=${YMD}&serviceKey=${apikey}` + this.Parameter
    }

    /**
     * 연립다세대 매매 실거래자료 URL
     * @param {String} Area 지역 번호
     * @param {number} YMD 계약월
     */
    static TownhouseRealEstateURL(Area, YMD) {
        return this.RealTradeURL + `getRTMSDataSvcRHTrade?LAWD_CD=${Area}&DEAL_YMD=${YMD}&serviceKey=${apikey}` + this.Parameter
    }


    /**
     * 단독/다가구 매매 실거래자료 URL
     * @param {String} Area 지역 번호
     * @param {number} YMD 계약월
     */
    static DetachedRealEstateURL(Area, YMD) {
        return this.RealTradeURL + `getRTMSDataSvcSHTrade?LAWD_CD=${Area}&DEAL_YMD=${YMD}&serviceKey=${apikey}` + this.Parameter
    }


    /**
     * 오피스텔 매매 신고정보 URL
     * @param {String} Area 지역 번호
     * @param {number} YMD 계약월
     */
    static RTMSOffTradeURL(Area, YMD) {
        return this.RTMSDataURL + `getRTMSDataSvcOffiTrade?LAWD_CD=${Area}&DEAL_YMD=${YMD}&serviceKey=${apikey}` + this.Parameter
    }

    /**
     * 토지매매 신고정보 URL
     * @param {String} Area 지역 번호
     * @param {number} YMD 계약월
     */
    static RTMSLandTradeURL(Area, YMD) {
        return this.RTMSDataURL + `getRTMSDataSvcLandTrade?LAWD_CD=${Area}&DEAL_YMD=${YMD}&serviceKey=${apikey}` + this.Parameter
    }


    /**
     * 아파트 전월세 자료 URL
     * @param {String} Area 지역 번호
     * @param {number} YMD 계약월
     */
    static ApartRentDataURL(Area, YMD) {
        return this.RealTradeURL + `getRTMSDataSvcAptRent?LAWD_CD=${Area}&DEAL_YMD=${YMD}&serviceKey=${apikey}` + this.Parameter
    }

    /**
     * 연립다세대 전,월세 신고정보 URL
     * @param {String} Area 지역 번호
     * @param {number} YMD 계약월
     */
    static TownhouseRealRentURL(Area, YMD) {
        return this.RealTradeURL + `getRTMSDataSvcRHRent?LAWD_CD=${Area}&DEAL_YMD=${YMD}&serviceKey=${apikey}` + this.Parameter
    }

    /**
     * 단독/다가구 전,월세 신고정보 URL
     * @param {String} Area 지역 번호
     * @param {number} YMD 계약월
     */
    static DetachedRealRentURL(Area, YMD) {
        return this.RealTradeURL + `getRTMSDataSvcSHRent?LAWD_CD=${Area}&DEAL_YMD=${YMD}&serviceKey=${apikey}` + this.Parameter
    }

    /**
     * 오피스텔 전,월세 신고정보 URL
     * @param {String} Area 지역 번호
     * @param {number} YMD 계약월
     */
    static DetachedRealRentURL(Area, YMD) {
        return this.RealTradeURL + `getRTMSDataSvcOffiRent?LAWD_CD=${Area}&DEAL_YMD=${YMD}&serviceKey=${apikey}` + this.Parameter
    }

    /**
     * 아파트매매 실거래 상세자료 조회 URL
     * @param {String} Area 지역 번호
     * @param {number} YMD 계약월
     */
    static DetailApartEstateURL(Area, YMD) {
        return this.RTMSDataURL + `getRTMSDataSvcAptTradeDev?LAWD_CD=${Area}&DEAL_YMD=${YMD}&serviceKey=${apikey}` + this.Parameter
    }

}

class Air {
    static get airKoreaAPI() { return "http://openapi.airkorea.or.kr/openapi/services/rest/" }
    /**
     * 시군구별 실시간 평균대기 농도 조회 URL
     * @param {String} sido 시 이름  
     * @param {String} searchCondition 검색할 범위
     * @param {number} rows 찾을 값의 갯수
     */
    static getCtprvnList(sido, searchCondition, rows) {
        return this.airKoreaAPI + `ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?sidoName=${encodeURIComponent(sido)}&searchCondition=${searchCondition}&pageNo=1&numOfRows=${rows}&ServiceKey=${apikey}`
    }

    /**
     * 시도별 실시간 측정정보 조회 URL
     * @param {String}} sido 시 이름
     */
    static getCtprvnRltmMesureDnsty(sido) {
        return this.airKoreaAPI + `ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?sidoName=${encodeURIComponent(sido)}&pageNo=1&numOfRows=10&ServiceKey=${apikey}&ver=1.3`
    }

}

class Bus {
    static get BusAPI() { return "http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/" }

    static getBusInPos(lat, lng) {
        return this.BusAPI+`getCrdntPrxmtSttnList?serviceKey=${apikey}&gpsLati=${lng}&gpsLong=${lat}`
    }
}
module.exports.Home = Home

module.exports.Air = Air

module.exports.Bus = Bus