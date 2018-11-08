package com.impv.comm.Utils;

import com.impv.comm.pojo.FileSaveType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: Created by Admin on 2017/3/10.
 * 文件上传工具类
 */
public class EHFSClient {
    private static final Log logger = LogFactory.getLog(EHFSClient.class);
    private CloseableHttpClient client;
    private CloseableHttpResponse response;
    private HttpPost post;
    private String SAMPLE_URL;

    private EHFSClient() {
    }

    public static EHFSClient instance(String url) {
        EHFSClient fsclient = new EHFSClient();
        fsclient.SAMPLE_URL = url;
        fsclient.before();
        return fsclient;
    }

    private final void before() {
        this.client = HttpClientBuilder.create().build();
        this.post = new HttpPost(this.SAMPLE_URL);
    }

    public String[] uploadFile(String fileName, String filePath, File file, FileSaveType fileSaveType) {
        String[] returnStat = new String[2];

        try {
            MultipartEntityBuilder e = MultipartEntityBuilder.create();
            e.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            e.setCharset(CharsetUtils.get("UTF-8"));
            FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);
            StringBody stringBodyFileName = new StringBody(fileName, ContentType.create("multipart/form-data", Consts.UTF_8));
            StringBody stringBodyFilePath = new StringBody(filePath, ContentType.create("multipart/form-data", Consts.UTF_8));
            StringBody stringBodyFileSaveType = new StringBody(fileSaveType.getValue(), ContentType.create("multipart/form-data", Consts.UTF_8));
            e.addPart("upFile", fileBody);
            e.addPart("fileName", stringBodyFileName);
            e.addPart("filePath", stringBodyFilePath);
            e.addPart("fileSaveType", stringBodyFileSaveType);
            HttpEntity entity = e.build();
            this.post.setEntity(entity);
            this.response = this.client.execute(this.post);
            int statusCode = this.response.getStatusLine().getStatusCode();
            returnStat[0] = String.valueOf(statusCode);
            if (200 == statusCode) {
                try {
                    String e1 = EntityUtils.toString(this.response.getEntity());
                    returnStat[1] = e1;
                } catch (ParseException var28) {
                    logger.error("Entity转换错误", var28);
                } catch (IOException var29) {
                    logger.error("IO输出错误", var29);
                }
            }
        } catch (UnsupportedEncodingException var30) {
            logger.error("编码格式错误", var30);
        } catch (ClientProtocolException var31) {
            logger.error("httpclient协议端异常", var31);
        } catch (IOException var32) {
            logger.error("IO异常", var32);
        } finally {
            try {
                this.after();
            } catch (IOException | IllegalStateException var27) {
                logger.error("关闭client异常", var27);
            }

        }

        return returnStat;
    }

    private final void after() throws IllegalStateException, IOException {
        this.post.completed();

        try {
            this.client.close();
        } catch (IOException var6) {
            throw var6;
        }

        try {
            HttpEntity entity = this.response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                instream.close();
            }
        } finally {
            this.response.close();
        }

    }

    private final String getContentTypeHeader() throws IOException {
        return this.post.getEntity().getContentType().toString();
    }

    public static List<String> getFileLength(List<String> filList) throws IOException {
        ArrayList lengthList = new ArrayList();
        Iterator i$ = filList.iterator();

        while (i$.hasNext()) {
            String filePath = (String) i$.next();
            URL url = new URL(filePath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10000);
            int fileLength = conn.getContentLength();
            if (conn.getResponseCode() >= 400) {
                logger.info("获取文件size时 服务器响应错误");
                lengthList.add("-1");
            } else {
                lengthList.add(String.valueOf(fileLength));
            }
        }

        return lengthList;
    }
}
