package com.impv.db.service;

import com.impv.db.mapper.ItemParamItemMapper;
import com.impv.db.pojo.ItemParamItem;
import com.github.abel533.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ItemParamItemService extends BaseService<ItemParamItem>{
    
    @Autowired
    private ItemParamItemMapper itemParamItemMapper;

    public void updateItemParamItem(Long itemId, String itemParams) {
        //更新的对象
        ItemParamItem record = new ItemParamItem();
        record.setParamData(itemParams);
        record.setUpdated(new Date());
        
        //更新条件
        Example example = new Example(ItemParamItem.class);
        example.createCriteria().andEqualTo("itemId", itemId);
        this.itemParamItemMapper.updateByExampleSelective(record, example);
    }

}
