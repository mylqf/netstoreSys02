package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.pojo.TbItemDesc;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import common.pojo.EasyUIDataGridResult;
import common.pojo.NetstoreResult;
import common.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemExample.Criteria;
import cn.e3mall.service.ItemService;

/**
 * 商品管理Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		//根据主键查询
		//TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andIdEqualTo(itemId);
		//执行查询
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {

		TbItemExample example=new TbItemExample();

		//设置分页
		PageHelper.startPage(page,rows);
		List<TbItem> list=itemMapper.selectByExample(example);

		//取分页信息
		PageInfo<TbItem> pageInfo=new PageInfo<TbItem>(list);
		long total=pageInfo.getTotal();
		EasyUIDataGridResult result=new EasyUIDataGridResult(total,list);

		return result;
	}

	@Override
	public NetstoreResult addItem(TbItem item, TbItemDesc itemDesc, String itemParams) {

		//生成商品id
		//可以用redis的自增key,在没有redis之前使用时间+随机数策越生成
		Long itemId= IDUtils.genItemId();

		//补全不完整的字段
		item.setId(itemId);
		item.setStatus((byte)1);
		Date date=new Date();
		item.setCreated(date);
		item.setUpdated(date);

		//把数据插入到商品表
		itemMapper.insert(item);
		//添加商品描述
		itemDesc.setItemId(itemId);
		itemDesc.setCreated(date);
		item.setUpdated(date);
		//把数据插入到上商品表
		itemDescMapper.insert(itemDesc);

		return  NetstoreResult.ok();
	}

}
