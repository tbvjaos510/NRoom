
using Android.Animation;
using Android.Annotation;
using Android.Content;
using Android.Graphics;
using Android.Graphics.Drawables;
using Android.OS;
using Android.Util;
using Android.Views;
using Com.Google.Maps.Android.Clustering;
using Com.Google.Maps.Android.Projection;
using Com.Google.Maps.Android.Data;
using Com.Google.Maps.Android;
using Com.Google.Maps.Android.Geometry;
using Com.Google.Maps.Android.UI;
using Com.Google.Maps.Android.Quadtree;
using Com.Google.Maps.Android.Heatmaps;
using Com.Google.Maps.Android.Clustering.View;
using Java.Util;
using Java.Util.Concurrent.Locks;
using Android.Runtime;
using System;
using Java.Lang;

namespace NRoom.Droid
{
    internal class ClusterRenderer : DefaultClusterRenderer
    {
        protected ClusterRenderer(IntPtr javaReference, JniHandleOwnership transfer) : base(javaReference, transfer) { }
        [Override()]
        protected Java.Lang.String getClusterText(int bucket)
        {
            return base.GetClusterText(bucket).replace("+", "");
        }

        [Override()]
        protected int getBucket(Cluster<Data> cluster)
        {
            return cluster.getSize();
        }
    }
}