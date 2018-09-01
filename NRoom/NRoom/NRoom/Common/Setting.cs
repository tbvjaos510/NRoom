using System;
using System.Collections.Generic;
using System.Text;

namespace NRoom.Common
{
    public static class Setting
    {
        public static string ServerURL { get; set; }

        public static void Load()
        {
            ServerURL = ComDef.SERVER_URL;
        }
    }
}
