using Newtonsoft.Json.Linq;
using NRoom.Model;
using RestSharp.Portable;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace NRoom.Network
{
    public partial class NetworkManager
    {
        public const string TOKEN_REFRESH_URL = "/token/refresh";
        public const int TOKEN_EXPIRED = 410;

        public async Task<TokenInfo> TokenRefresh(TokenInfo tokenInfo)
        {
            JObject jObj = new JObject();
            jObj["refresh_token"] = tokenInfo.RefreshToken;
            var resp = await GetResponse<string>(TOKEN_REFRESH_URL, Method.POST, jObj);
            if (resp.Status == (int)System.Net.HttpStatusCode.OK)
            {
                tokenInfo.Token = JObject.Parse(resp.Data)?["data"]?["new_token"]?.ToString();
            }
            else
            {
               /* if (resp.Status == TOKEN_EXPIRED)
                {
                    var loginResp = await Login();
                    tokenInfo = new TokenInfo()
                    {
                        RefreshToken = loginResp.Data.RefreshToken,
                        Token = loginResp.Data.Token
                    };
                }*/
            }

            return tokenInfo;
        }

        public bool CheckTokenExpired(IRestResponse response)
        {
            if ((int)response.StatusCode == TOKEN_EXPIRED)
            {
                return true;
            }
            return false;
        }
    }
}
