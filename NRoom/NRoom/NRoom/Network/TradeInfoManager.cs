using Newtonsoft.Json.Linq;
using NRoom.Network.Data;
using RestSharp.Portable;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace NRoom.Network
{
    public partial class NetworkManager
    {
        private const string TRADE_URL = "/home/DetailApartEstate";

        public async Task<NResponse<TradeInfoData>> getTradeInfo()
        {
            string Area = "대구광역시 수성구";
            string YMD = "201808";
            string parameter = "?Area=" + Area+"&YMD=" + YMD;
            var resp = await GetResponse<TradeInfoData>(TRADE_URL + parameter, Method.GET);

            return resp;
        }
    }
}
