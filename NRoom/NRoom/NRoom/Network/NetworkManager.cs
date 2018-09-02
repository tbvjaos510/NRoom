using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json.Serialization;
using NRoom.Common;
using NRoom.Model;
using RestSharp.Portable;
using RestSharp.Portable.HttpClient;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;
using System.Threading.Tasks;

namespace NRoom.Network
{
    public partial class NetworkManager
    {
        private static RestClient CreateClient()
        {
            //var restClient = new RestClient(Setting.ServerURL) { Timeout = new TimeSpan(ComDef.TIME_OUT) };
            var restClient = new RestClient(Setting.ServerURL);
            return restClient;
        }
        private async Task<NResponse<T>> GetResponse<T>(string resource, Method method, object parameterJson = null, TokenInfo token = null, UrlSegment[] urlSegments = null, Header[] headers = null)
        {
            var client = CreateClient();    
            var restRequest = CreateRequest(resource, method, parameterJson, token, urlSegments, headers);
            var response = await client.Execute<NResponse<T>>(restRequest);

            var resp = DeserializeSnakeCase<NResponse<T>>(response.Content);
            Debug.WriteLine(response.Content);
            return resp;
        }

        private class UrlSegment
        {
            public string Name { get; set; }
            public object Value { get; set; }
        }

        private class Header
        {
            public string Name { get; set; }
            public string Value { get; set; }
        }

        private static T DeserializeSnakeCase<T>(string json)
        {
            DefaultContractResolver contractResolver = new DefaultContractResolver
            {
                NamingStrategy = new SnakeCaseNamingStrategy()
            };
            try
            {
                var resp = JsonConvert.DeserializeObject<T>(json, new JsonSerializerSettings
                {
                    ContractResolver = contractResolver,
                    Formatting = Formatting.Indented
                });
                return resp;
            }
            catch (Exception e)
            {
                Debug.WriteLine(e);
            }

            return default(T);
        }
        private static RestRequest AddToRequest(RestRequest restRequest, TokenInfo token, JObject json, UrlSegment[] urlSegments = null, Header[] headers = null)
        {

            restRequest.AddHeader("Content-Type", "application/json");
            restRequest.AddParameter("application/json", json, ParameterType.RequestBody);

            /*if (token != null)
            {
                restRequest.AddHeader("x-access-token", token.Token);
            }
            */
            return restRequest;
        }

        private static RestRequest CreateRequest(string resource, Method method, object parameterJson, TokenInfo token = null, UrlSegment[] urlSegments = null, Header[] headers = null)
        {
            var restRequest = new RestRequest(resource, method);
            if (method == Method.POST)
            {
                restRequest.AddHeader("Content-Type", "application/json");
                restRequest.AddParameter("application/json", parameterJson, ParameterType.RequestBody);
            }
            //restRequest = AddToRequest(restRequest, token, parameterJson, urlSegments, headers);

            return restRequest;
        }
    }
}
