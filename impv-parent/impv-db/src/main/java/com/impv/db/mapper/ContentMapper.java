package com.impv.db.mapper;

import com.impv.db.pojo.Content;
import com.github.abel533.mapper.Mapper;

import java.util.List;

public interface ContentMapper extends Mapper<Content>{

    List<Content> queryList(Long categoryId);

}
