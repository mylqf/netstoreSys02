package cn.e3mall.service.impl;

import cn.e3mall.mapper.TbItemCatMapper;

import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemCatService;
import common.pojo.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<TreeNode> getItemCatList(long parentId) {

        //根据parentId查询分类列表
        TbItemCatExample example=new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria=example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list=itemCatMapper.selectByExample(example);
        //分类列表转换成treeNOde的列表
        List<TreeNode> resultList=new ArrayList<>();
        for (TbItemCat tbItemCat:list){
            //创一个TreeNode对象
            TreeNode node=new TreeNode(tbItemCat.getId(),tbItemCat.getName(),tbItemCat.getIsParent()?"closed":"open");
            resultList.add(node);
        }

        return  resultList;
    }
}
