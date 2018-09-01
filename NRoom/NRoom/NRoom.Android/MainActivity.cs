using System;

using Android.App;
using Android.Content.PM;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using Com.Nhn.Android.Maps;

namespace NRoom.Droid
{
    [Activity(Label = "NRoom", Icon = "@mipmap/icon", Theme = "@style/MainTheme", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
    public class MainActivity : NMapActivity
    {
        private NMapView mMapView;
        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);

            mMapView = new NMapView(this);
            SetContentView(mMapView);
            mMapView.SetClientId("qxSxM3dHkv_nQodnLE34"); // 클라이언트 아이디 값 설정
            mMapView.Clickable = true;
            mMapView.Enabled = true;
            mMapView.Focusable = true;
            mMapView.FocusableInTouchMode = true;
            mMapView.RequestFocus();
        }
    }
}