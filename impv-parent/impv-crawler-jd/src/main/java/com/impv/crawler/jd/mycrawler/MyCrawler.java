package com.impv.crawler.jd.mycrawler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.impv.crawler.jd.pojo.Item;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class MyCrawler {
    private String baseUrl = "https://list.jd.com/list.html?cat=9987,653,655&page={page}";

    private ObjectMapper mapper = new ObjectMapper();

    public void start () throws IOException {
        //获取总页数
        String url = StringUtils.replace(baseUrl, "{page}", "1");
        String html = this.doGet(url);
        Document document = Jsoup.parse(html);
        String pageText = document.select("#J_topPage").text();
        String[] strs = pageText.split("\\D+");//正则分割数字
        Integer totalPage = Integer.parseInt(strs[1]);

        //获取每个商品信息
        Map<Long,Item> itemsMap = new HashMap<>();
        for (int i = 0; i< 2 ; i++) {
            StringUtils.replace(url,"{page}",String.valueOf(i));
            String content = doGet(url);
            Document root = Jsoup.parse(content);
            Elements lis = root.select("#plist li.gl-item");
            for (Element li : lis) {
                Item item = new Item();
                Element div = li.child(0);
                Long id = Long.valueOf(div.attr("data-sku"));
                String image = li.select(".p-img img").attr("src");
                String title = li.select(".p-name").text();
                item.setId(id);
                item.setImage(image);
                item.setTitle(title);
                itemsMap.put(id,item);
            }

            //获取价格
            String priceUrl ="https://p.3.cn/prices/mgets?skuIds=";
            List<String> ids = new ArrayList<>();
            for(Long id :itemsMap.keySet()){
                ids.add("J_"+id);
            }
            String idsPStr = StringUtils.join(ids, ",");
            String priceJson = doGet(priceUrl + idsPStr);
            ArrayNode arrayNode = (ArrayNode) mapper.readTree(priceJson);
            for (JsonNode jsonNode : arrayNode) {

                Long id = Long.valueOf(StringUtils.substringAfterLast(jsonNode.get("id").asText(), "_"));
                itemsMap.get(id).setPrice(jsonNode.get("p").asLong());
            }

            //获取卖点
            String adUrl = "https://ad.3.cn/ads/mgets?skuIds=";
            ids.clear();
            for(Long id: itemsMap.keySet()){
                ids.add("AD_"+id);
            }
            String idsAdStr = StringUtils.join(ids,",");
            String adJson = doGet(adUrl+idsAdStr);
            arrayNode = (ArrayNode) mapper.readTree(adJson);
            for (JsonNode jsonNode : arrayNode) {
                Long id = Long.valueOf(StringUtils.substringAfter(jsonNode.get("id").asText(),"_"));
                itemsMap.get(id).setSellPoint(jsonNode.get("ad").asText());
            }

            for(Item item : itemsMap.values()){
                System.out.println(item);
            }
        }
    }

    private String doGet(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode()==200){
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                return content;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response!=null){
                response.close();
            }
            httpClient.close();
        }
        return null;
    }
}
