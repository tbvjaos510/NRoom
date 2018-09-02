using Com.Google.Maps.Android.Clustering.View;
using Com.Google.Maps.Android.Clustering;
using Android.Content;
using Android.Gms.Maps;

namespace NRoom.Droid
{
    internal class TClusterRenderer : DefaultClusterRenderer
    {
        public TClusterRenderer(Context p0, GoogleMap p1, ClusterManager p2) : base(p0, p1, p2) { }

        protected override int GetBucket(ICluster cluster)
        {
            return cluster.Size;          
        }
        protected override string GetClusterText(int p0)
        {
            return base.GetClusterText(p0).Replace("+", "");
        }
    }
}