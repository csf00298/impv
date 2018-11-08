package com.impv.comm.serice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * SpringMVC - Spring 父子容器联通
 * 父容器 Spring 不可见子容器 不能使用子容器中的资源
 * 子容器 SpringMVC 可以看到并使用 父容器中的资源，但是Spring读取配置文件的数据 子容器不可见
 * 使用Spring读取property文件 为了在子容器中读取父容器的配置文件信息，将配置信息内容放入Spring容器的对象中
 * 在子容器SpringMVC中注入父容器对象 从而获取数据
 */
@Service
public class PropertieService {

    @Value("${REPOSITORY_PATH}")
    public String REPOSITORY_PATH;

    @Value("${IMAGE_BASE_URL}")
    public String IMAGE_BASE_URL;

    @Value("${APP_URL}")
    public String APP_URL;

    @Value("${UPLOADFILE_URL}")
    public String UPLOADFILE_URL;
}
