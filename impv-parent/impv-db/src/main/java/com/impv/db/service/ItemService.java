package com.impv.db.service;

import com.impv.comm.serice.ApiService;
import com.impv.db.mapper.ItemMapper;
import com.impv.db.pojo.Item;
import com.impv.db.pojo.ItemDesc;
import com.impv.db.pojo.ItemParamItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemService extends BaseService<Item> {

    @Autowired
    private ItemDescService itemDescService;

    @Autowired
    private ItemParamItemService itemParamItemService;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ApiService apiService;

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    @Value("${TAOTAO_WEB_URL}")
    private String TAOTAO_WEB_URL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public void saveItem(Item item, String desc, String itemParams) {
        // 设置初始数据
        item.setStatus(1);

        item.setId(null);// 强制设置id为null

        super.save(item);

        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        // 保存描述数据
        this.itemDescService.save(itemDesc);

        // 保存规格参数数据
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(item.getId());
        itemParamItem.setParamData(itemParams);
        this.itemParamItemService.save(itemParamItem);

        // 发送消息
        sendMsg(item.getId(), "insert");
    }

    public PageInfo<Item> queryPageList(Integer page, Integer rows) {
        Example example = new Example(Item.class);
        example.setOrderByClause("updated DESC");

        // 设置分页参数
        PageHelper.startPage(page, rows);

        List<Item> list = this.itemMapper.selectByExample(example);
        return new PageInfo<Item>(list);
    }

    public void updateItem(Item item, String desc, String itemParams) {
        // 强制设置不能修改的字段为null
        item.setStatus(null);
        item.setCreated(null);
        super.updateSelective(item);

        // 修改商品描述数据
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        this.itemDescService.updateSelective(itemDesc);

        // 修改商品规格参数数据
        this.itemParamItemService.updateItemParamItem(item.getId(), itemParams);

        // try {
        // // 通知其它系统，该商品已经更新
        // String url = TAOTAO_WEB_URL + "/item/cache/" + item.getId() + ".html";
        // this.apiService.doPost(url, null);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        // 发送消息
        sendMsg(item.getId(), "update");

    }

    private void sendMsg(Long itemId, String type) {
        try {
            // 发送MQ消息通知其他系统
            Map<String, Object> msg = new HashMap<String, Object>();
            msg.put("itemId", itemId);
            msg.put("type", type);
            msg.put("date", System.currentTimeMillis());
//            this.rabbitTemplate.convertAndSend("item." + type, MAPPER.writeValueAsString(msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
