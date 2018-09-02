using System;
using System.Collections.Generic;
using System.Text;
using NRoom.Network.Data;

namespace NRoom.Network
{
    public class NResponse<T>
    {
        public int Status { get; set; }
        public string Message { get; set; }
        public T Items { get; set; }
    }
}
