using System;

using Android.App;
using Android.Content.PM;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using Android.Util;
using Android.Gms.Maps;
using Android.Gms.Maps.Model;
using Android.Graphics;
using Android.Content;
using Com.Google.Maps.Android.Data;
using Com.Google.Maps.Android.Clustering;
using static Com.Google.Maps.Android.Clustering.ClusterManager;
using System.Collections.Generic;
using Com.Google.Maps.Android.Clustering.Algo;

namespace NRoom.Droid
{
    [Activity(Label = "NewMapApp", MainLauncher = true)]
    public class MainActivity : Activity, IOnMapReadyCallback, IOnClusterClickListener, IOnClusterItemClickListener
    {
        GoogleMap map;
        MapFragment mapFragment;
        ClusterManager clusterManager;
        public void addMarker(LatLng location)
        {
            clusterManager.AddItem(new Home(location));
        }

        public void OnMapReady(GoogleMap googleMap)
        {
            map = googleMap;

            if (map != null)
            {
                LatLng location = new LatLng(35.863612, 128.574671);
                CameraPosition.Builder builder = CameraPosition.InvokeBuilder();
                builder.Target(location);
                builder.Zoom(18);
                map.MoveCamera(CameraUpdateFactory.NewCameraPosition(builder.Build()));

                clusterManager = new ClusterManager(this, map);
                
                clusterManager.SetOnClusterClickListener(this);
                clusterManager.SetOnClusterItemClickListener(this);
                clusterManager.Algorithm = new GridBasedAlgorithm();
                clusterManager.Renderer = new TClusterRenderer(this, map, clusterManager);
                map.SetOnCameraIdleListener(clusterManager);
                map.SetOnMarkerClickListener(clusterManager);
                for (int i = 0; i < 500; i++)
                    addMarker(new LatLng(Java.Lang.Math.Random()/10 + 35, Java.Lang.Math.Random()/10 + 128));

            }
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
                fragTx.Commit();
            }
            mapFragment.GetMapAsync(this);
        }
        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);
            InitMap();
            SetContentView(Resource.Layout.main);
        }

        public bool OnClusterClick(ICluster cluster)
        {
            Toast.MakeText(this, cluster.Items.Count + " items in cluster", ToastLength.Short).Show();
            return false;
        }

        public bool OnClusterItemClick(Java.Lang.Object marker)
        {
            Toast.MakeText(this, "Marker clicked", ToastLength.Short).Show();
            return false;
        }
    }
}