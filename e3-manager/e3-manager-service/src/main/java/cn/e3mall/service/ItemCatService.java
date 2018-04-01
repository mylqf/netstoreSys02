package cn.e3mall.service;


import cn.e3mall.mapper.TbItemMapper;
import common.pojo.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemCatService {

    public List<TreeNode> getItemCatList(long parentId);


}
