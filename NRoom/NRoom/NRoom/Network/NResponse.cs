using System;
using System.Collections.Generic;
using System.Text;

namespace NRoom.Network
{
    public class NResponse<T>
    {
        public int Status { get; set; }
        public string Message { get; set; }
        public T Data { get; set; }
    }
}
