package com.impv.controller.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.impv.comm.Utils.EHFSClient;
import com.impv.comm.Utils.ImageHelper;
import com.impv.comm.pojo.FileSaveType;
import com.impv.comm.serice.PropertieService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @Description: 文件上传 Created by Admin on 2017/3/10.
 */
@Controller
@RequestMapping("/fileupload")
public class FileUploadController extends BaseController {

    @Autowired
    private PropertieService propertieService;

    private Log logger = LogFactory.getLog(FileUploadController.class);

    /**
     * 上传图片
     * :图片容量，单位kb，只需写数值(0 为不限制图片容量)
     * :图片大小，格式：长度#宽度(0#0 为不限制图片尺寸)
     * @return returnData.fileUploadPath 图片上传路径
     */
    @RequestMapping(value = "/savePicture")
    public void savePicture(HttpServletRequest request, HttpServletResponse response) {
        FileOutputStream outputStream;
        try {
            response.setContentType("text/html;charset=UTF-8");
        } catch (Exception e) {
            logger.error("相应设置编码失败，失败原因:" + e);
        }
        String fileUploadPath = "";
        String imageTemp = "";
        DefaultMultipartHttpServletRequest defaultRequest = (DefaultMultipartHttpServletRequest) request;
        MultiValueMap<String, MultipartFile> fileMap = defaultRequest.getMultiFileMap();
        List<MultipartFile> fileList = fileMap.get("UploadBtn");
        MultipartFile file = fileList.get(0);
        InputStream inputStream = null;
        String imageSetName = request.getParameter("imageSetName");
        String extName = "";
        String messages = "";
        String storeName = "";
        String savePathAll = "";
        String imageBasePath = request.getParameter("imageBPath");
        boolean flag = true;
        // String uploadDir = File.separator + "images" + File.separator + "user" + File.separator;
        String uploadDir = "images" + File.separator + "user" + File.separator;
        // 图片同步路径
        String originalName = file.getOriginalFilename();
        int widthImg = 0, heightImg = 0;
        String dir = "sysorganization";
        String[] ret2 = null;
        if (originalName != null && !"".equals(originalName.trim())) {
            if (originalName.lastIndexOf(".") >= 0) {
                extName = originalName.substring(originalName.lastIndexOf("."));
                originalName = originalName.substring(0, originalName.indexOf("."));
            }
            List<String> fileTypes = new ArrayList<>();
            fileTypes.add(".bmp");
            fileTypes.add(".jpg");
            fileTypes.add(".jpeg");
            fileTypes.add(".png");
            fileTypes.add(".gif");
            if (!fileTypes.contains(extName.toLowerCase())) {
                messages = "只允许上传jpg、jpeg、gif、png格式的图片";
                flag = false;
            }
            if (file.getSize() > 1024 * 1024 * 2) {
                logger.info("文件过大: " + file.getSize());
                messages = "文件过大，请小于2M！";
                flag = false;
            }

            if (flag) {
                savePathAll = getProjectPath() + uploadDir;
                savePathAll = savePathAll.replaceAll("\\\\", "/");
                logger.info("------上传路径：" + savePathAll);
                File f1 = new File(savePathAll);
                if (!f1.exists()) {
                    f1.mkdirs();
                }
                try {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(imageSetName) && !"null".equals(imageSetName.trim()) && !"undefined".equals(imageSetName.trim())) {
                        storeName = imageSetName.trim() + new Date().getTime();
                    } else {
                        storeName = "" + new Date().getTime() + UUID.randomUUID();
                    }
                    imageTemp = storeName;
                    storeName += extName;
                    inputStream = file.getInputStream();
                    outputStream = new FileOutputStream(savePathAll + storeName);
                    byte[] buf = new byte[1024];
                    int length = 0;
                    while ((length = inputStream.read(buf)) != -1) {
                        outputStream.write(buf, 0, length);
                    }
                    inputStream.close();
                    outputStream.flush();
                    messages = "上传成功！";
                    logger.info("上传图片成功！");
                } catch (Exception e) {
                    messages = "保存文件出错 ，请检查上传图片是否有误！";
                    logger.error("上传图片失败，失败原因：" + e);
                }

                EHFSClient client = EHFSClient.instance(propertieService.UPLOADFILE_URL);
                File file2 = new File(savePathAll + storeName);
                String[] ret = null;
                if ("banner".equals(imageBasePath)) {
                    ret = client.uploadFile(imageTemp, "banner", file2, FileSaveType.otherfile);
                } else {
                    try {// 图片缩进
                        ImageHelper.zoomImage(savePathAll + storeName, savePathAll + "img_" + storeName, 200, 200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ret = client.uploadFile(imageTemp, "sysorganization", file2, FileSaveType.product);
                }
                if (!"200".equals(ret[0])) {
                    file2.delete();
                }
                EHFSClient client2 = EHFSClient.instance(propertieService.UPLOADFILE_URL);
                File file3 = null;
                if ("banner".equals(imageBasePath)) {
                    file3 = new File(savePathAll + storeName);
                    ret2 = client2.uploadFile(imageTemp, imageBasePath, file3, FileSaveType.otherfile);
                } else {
                    file3 = new File(savePathAll + "img_" + storeName);
                    ret2 = client2.uploadFile("img" + imageTemp, dir, file3, FileSaveType.product);
                }
                if (!"200".equals(ret2[0])) {
                    file3.delete();
                }
            }
        }
        Map<String, Object> returnMap;
        if (flag) {
            returnMap = getMessage(messages, flag, ret2[1], propertieService.UPLOADFILE_URL, widthImg, heightImg);
        } else {
            returnMap = getMessage(messages, flag, null, null, widthImg, heightImg);
        }
        logger.info("后台图片访问路径：" + fileUploadPath);
        String dataStr = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dataStr = objectMapper.writeValueAsString(returnMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            getRequest().setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        getResponse().setContentType("text/html;charset=utf-8");
        try {
            PrintWriter pw = getResponse().getWriter();
            pw.write(dataStr);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            logger.error("向客户端写错误。", e);
        }
    }


    /**
     * 返回文件信息
     * @return
     */
    private Map<String, Object> getMessage(String message, boolean flag, String imgName, String fileUploadPath, int width, int height) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("flag", flag);
        map.put("imgName", imgName);
        map.put("fileUploadPath", fileUploadPath);
        map.put("height", height);
        map.put("width", width);
        return map;
    }
}
