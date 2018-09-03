using NRoom.Common;
using NRoom.Model;
using NRoom.Network;
using NRoom.View;
using PushPageFromNative;
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

           // GetTradeInfoAsync();
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

        private void SearchMap_Clicked(object sender, EventArgs e)
        {
            Debug.WriteLine("클릭");
        }

        public void HomePage()
        {
            pageHome.IsVisible = true;
        }

        private void Map_Tapped(object sender, EventArgs e)
        {
            DependencyService.Get<IShowForm>().PushPage();
            //pageHome.IsVisible = true;
        }

        private void List_Tapped(object sender, EventArgs e)
        {
            pageHome.IsVisible = true;
        }
    }
}
