using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace NRoom.View
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class HomeInfoView : ContentView
	{
		public HomeInfoView()
		{
			InitializeComponent();
		}

        private void Back_Tapped(object sender, EventArgs e)
        {
            this.IsVisible = false;
        }

        private void Positive_Tapped(object sender, EventArgs e)
        {
            ViewDetailInfo.IsVisible = true;
        }
    }
}