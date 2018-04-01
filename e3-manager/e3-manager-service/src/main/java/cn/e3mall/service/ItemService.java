package cn.e3mall.service;

import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import common.pojo.EasyUIDataGridResult;
import common.pojo.NetstoreResult;

public interface ItemService {


    public TbItem getItemById(long id);
    public EasyUIDataGridResult getItemList(Integer page, Integer rows);
    NetstoreResult addItem(TbItem item, TbItemDesc itemDesc,String itemParams);
}
