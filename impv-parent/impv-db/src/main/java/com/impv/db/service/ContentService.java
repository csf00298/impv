package com.impv.db.service;

import com.impv.comm.pojo.EasyUIResult;
import com.impv.db.mapper.ContentMapper;
import com.impv.db.pojo.Content;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService extends BaseService<Content> {

    @Autowired
    private ContentMapper contentMapper;

    public EasyUIResult queryList(Long categoryId, Integer page, Integer rows) {
        // 设置分页参数
        PageHelper.startPage(page, rows);

        List<Content> contents = this.contentMapper.queryList(categoryId);
        PageInfo<Content> pageInfo = new PageInfo<Content>(contents);
        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

}
