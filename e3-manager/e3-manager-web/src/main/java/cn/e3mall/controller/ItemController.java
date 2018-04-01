package cn.e3mall.controller;

import cn.e3mall.pojo.TbItemDesc;
import common.pojo.EasyUIDataGridResult;
import common.pojo.NetstoreResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;

/**
 * 商品管理Controller
 * <p>Title: ItemController</p>
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;




	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}

	@RequestMapping("list")
	//设置相应的内容为json数据
	@ResponseBody
	public EasyUIDataGridResult getItemList(@RequestParam(defaultValue = "1")Integer page,
											@RequestParam(defaultValue = "30")Integer rows){
		//查询商品列表
		EasyUIDataGridResult result=itemService.getItemList(page,rows);

		return  result;
	}


	@RequestMapping("/save")
	@ResponseBody
	//添加一个itemParams参数接受规格参数的数据
	public NetstoreResult addItem(TbItem item,String desc,String itemParams){

		TbItemDesc itemDesc=new TbItemDesc();
		itemDesc.setItemDesc(desc);
		NetstoreResult result=itemService.addItem(item,itemDesc,itemParams);
		return result;
	}

}
