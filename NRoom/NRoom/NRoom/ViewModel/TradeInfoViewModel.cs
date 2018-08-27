using NRoom.Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text;

namespace NRoom.ViewModel
{
    public class TradeInfoViewModel
    {
        private ObservableCollection<TradeInfo> Items;

        public TradeInfoViewModel()
        {
            Items = new ObservableCollection<TradeInfo>();
            InitDate();
        }
        private void InitDate()
        {

        }
        private void AddDate(TradeInfo tradeInfo)
        {
            Items.Add(tradeInfo);
        }
    }
}
