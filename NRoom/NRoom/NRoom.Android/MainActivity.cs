
using Android.App;
using Android.Widget;
using Android.OS;
using Android.Gms.Maps;
using Android.Gms.Maps.Model;
using Com.Google.Maps.Android.Clustering;
using static Com.Google.Maps.Android.Clustering.ClusterManager;
using System.Collections.Generic;
using Com.Google.Maps.Android.Clustering.Algo;
using Android.Locations;
using Android.Content.PM;
using System;
using PushPageFromNative;
using Xamarin.Forms;
using NRoom.Droid;
using Java.Util;

[assembly: Dependency(typeof(MainActivity))]
namespace NRoom.Droid
{
    [Activity(Label = "Phoneword",
             Icon = "@mipmap/icon",
             Theme = "@style/MainTheme",
             MainLauncher = true,
             ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
    public class MainActivity : global::Xamarin.Forms.Platform.Android.FormsAppCompatActivity, IOnMapReadyCallback, IOnClusterClickListener, IOnClusterItemClickListener, IShowForm
    {
        internal static MainActivity Instance { get; private set; }
        static GoogleMap map;
        static MapFragment mapFragment;
        static ClusterManager clusterManager;
        public void addMarker(LatLng location)
        {
            clusterManager.AddItem(new Home(location.Latitude, location.Longitude, "하나로", "마트"));
        }

        public void OnMapReady(GoogleMap googleMap)
        {
            map = googleMap;

            if (map != null)
            {
                LatLng location = new LatLng(35, 128);
                CameraPosition.Builder builder = CameraPosition.InvokeBuilder();
                builder.Target(location);
                builder.Zoom(18);
                map.MoveCamera(CameraUpdateFactory.NewCameraPosition(builder.Build()));

                clusterManager = new ClusterManager(Instance, map);
                
                clusterManager.SetOnClusterClickListener(Instance);
                clusterManager.SetOnClusterItemClickListener(Instance);
                clusterManager.Algorithm = new GridBasedAlgorithm();
                clusterManager.Renderer = new TClusterRenderer(Instance, map, clusterManager);
                map.SetOnCameraIdleListener(clusterManager);
                map.SetOnMarkerClickListener(clusterManager);
                for (int i = 0; i < 500; i++)
                    addMarker(new LatLng(Java.Lang.Math.Random()/10 + 35, Java.Lang.Math.Random()/10 + 128));

            }
        }
        private List<Address>GetAddress(string name)
        {
            Geocoder geocoder = new Geocoder(Instance, Locale.Default);
            List<Address> address = geocoder.GetFromLocationName(name, 5) as List<Address>;
            return address;
        }
        private void InitMap()
        {
            mapFragment = FragmentManager.FindFragmentByTag("map") as MapFragment;
            if (mapFragment == null)
            {
                GoogleMapOptions mapOptions = new GoogleMapOptions()
                .InvokeMapType(GoogleMap.MapTypeNormal)
                .InvokeZoomControlsEnabled(false)
                .InvokeCompassEnabled(true);
                FragmentTransaction fragTx = FragmentManager.BeginTransaction();
                mapFragment = MapFragment.NewInstance(mapOptions);
                fragTx.Add(Resource.Id.map, mapFragment, "map");
                fragTx.CommitAllowingStateLoss();
            }
            mapFragment.GetMapAsync(Instance);
        }
        protected override void OnCreate(Bundle bundle)
        {
               TabLayoutResource = Resource.Layout.Tabbar;
               ToolbarResource = Resource.Layout.Toolbar;

               base.OnCreate(bundle);
               Instance = this;
               global::Xamarin.Forms.Forms.Init(this, bundle);

               LoadApplication(new App());


        }
        public bool OnClusterClick(ICluster cluster)
        {
            Toast.MakeText(Instance, cluster.Items.Count + " items in cluster", ToastLength.Short).Show();
            return false;
        }

        public bool OnClusterItemClick(Java.Lang.Object marker)
        {
            Toast.MakeText(this, GetAddress("Seoul")[0].ToString(), ToastLength.Short).Show();
            return false;
        }

        public void PushPage()
        {
            Instance.InitMap();
            Instance.SetContentView(Resource.Layout.main);
        }
    }
}