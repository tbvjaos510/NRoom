using NRoom.Model;
using NRoom.Network;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace NRoom
{
    public partial class MainPage : ContentPage
    {
        private NetworkManager networkManager = new NetworkManager();
        public MainPage()
        {
            InitializeComponent();

            GetTradeInfoAsync();
        }

        private async Task GetTradeInfoAsync()
        {
            NResponse<TradeInfo> lstTradeInfo = null;

            try
            {
             //   lstTradeInfo = await networkManager.getTradeInfo();
            }
            catch (Exception e)
            {
                Debug.WriteLine(e.Message);
            }
        }
    }
}
