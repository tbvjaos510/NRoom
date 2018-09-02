using System;

using Android.App;
using Android.Content.PM;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using Com.Nhn.Android.Maps;
using Com.Nhn.Android.Mapviewer.Overlay;
using Java.Lang;
using Com.Nhn.Android.Maps.Overlay;
using Android.Util;

namespace NRoom.Droid
{
    [Activity(Label = "NRoom", Icon = "@mipmap/icon", Theme = "@style/MainTheme", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
    public class MainActivity : NMapActivity
    {
        private const string TAG = "MainActivity";
        private ViewGroup mapLayout;
        private NMapView mMapView;
        private NMapController mMppController;
        private NMapResourceProvider nMapResourceProvider;
        private NMapOverlayManager mapOverlayManager;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);

            Mapinit();

            
        }

        private void Mapinit()
        {
            mMapView = new NMapView(this);
            SetContentView(mMapView);
            mMapView.SetClientId("qxSxM3dHkv_nQodnLE34"); // 클라이언트 아이디 값 설정
            mMapView.Clickable = true;
            mMapView.Enabled = true;
            mMapView.Focusable = true;
            mMapView.FocusableInTouchMode = true;
            mMapView.RequestFocus();

        }

        private void setMarker()
        {

            int markerId = NMapPOIflagType.PIN;

            // set POI data
            NMapPOIdata poiData = new NMapPOIdata(2, nMapResourceProvider);
            poiData.BeginPOIdata(2);
            poiData.AddPOIitem(127.0630205, 37.5091300, "말풍선 클릭시 뿅", markerId, 0);
            poiData.AddPOIitem(127.061, 37.51, "네이버맵 입니다", markerId, 0);
            poiData.EndPOIdata();

            // create POI data overlay
            NMapPOIdataOverlay poiDataOverlay = mapOverlayManager.CreatePOIdataOverlay(poiData, null);
            poiDataOverlay.ShowAllPOIdata(0);
        }

    }
}