using NRoom.Network.Data;
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
            return null;
        }
    }
}
