package com.demo.tspring.htmlToPdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFilesImpl;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Entities;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Html 转PDF工具类
 * <p/>
 * 支持简单的html格式,对css解析不够好
 * @author bangis.wangdf
 * @date 16/6/22.20:15
 */
public class Html2PdfUtil {

    /**
     * 静态css文件
     */
    public static String cssFile;
    /**
     * 图片路径
     */
    public static String imagePath;

    /**
     * html转PDF
     * @param htmlFle
     * @param pdfFile
     */
    public static void parse2Pdf(String htmlFle, String pdfFile) {
        try {
            parse2Pdf(new FileInputStream(htmlFle), new FileOutputStream(pdfFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("转换失败", e);
        }
    }

    /**
     * html转PDF
     * @param htmlFle
     * @param pdfFile
     */
    public static void parse2Pdf(InputStream htmlFle, OutputStream pdfFile) {
        try {
            // step 1
            Document document = new Document();
            // step 2
            PdfWriter writer = PdfWriter.getInstance(document, pdfFile);
            // step 3
            document.open();
            // step 4
            MyXMLParser.getInstance(document, writer).parse(tidyHtml(htmlFle),
                    Charset.forName("UTF-8"));
            //step 5
            document.close();
        } catch (Exception e) {
            throw new RuntimeException("转换失败", e);
        }
    }

    /**
     * html转PDF
     * @param htmlContext html 字符串
     * @param pdfFile
     */
    public static void parseHtml2Pdf(String htmlContext, OutputStream pdfFile) {
        try {
            // step 1
            Document document = new Document();
            // step 2
            PdfWriter writer = PdfWriter.getInstance(document, pdfFile);
            // step 3
            document.open();

            MyXMLParser.getInstance(document, writer).parse(tidyHtml(htmlContext),
                    Charset.forName("UTF-8"));
            //step 5
            document.close();
        } catch (Exception e) {
            throw new RuntimeException("转换失败", e);
        }
    }

    /**
     * html转PDF
     * @param htmlContext html 字符串
     * @param pdfFile
     */
    public static void parseHtml2Pdf(String htmlContext, File pdfFile) {
        try {
            parseHtml2Pdf(htmlContext, new FileOutputStream(pdfFile));
        } catch (Exception e) {
            throw new RuntimeException("转换失败", e);
        }
    }

    /**
     * 闭合HTML标签
     * @param htmlStream
     * @return
     */
    private static InputStream tidyHtml(InputStream htmlStream) {
        try {
            org.jsoup.nodes.Document doc = Jsoup.parse(htmlStream, "UTF-8", "");
            doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
            doc.outputSettings().prettyPrint(true);
            doc.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
            return new ByteArrayInputStream(doc.toString().getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 闭合HTML标签
     * @param htmlContext
     * @return
     */
    private static InputStream tidyHtml(String htmlContext) {
        try {
            org.jsoup.nodes.Document doc = Jsoup.parse(htmlContext, "");
            doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
            doc.outputSettings().prettyPrint(true);
            doc.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
            return new ByteArrayInputStream(doc.toString().getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected static class MyXMLParser {
        public static XMLParser getInstance(Document doc, PdfWriter pdfWriter) throws Exception {

            //固定css
            CssFilesImpl cssFiles = new CssFilesImpl();
            cssFile="C:\\Users\\Administrator\\Desktop\\Apache Tomcat_8.5.16_files\\tomcat.css";
            if (StringUtils.isNotBlank(cssFile)) {
                cssFiles.add(XMLWorkerHelper.getCSS(new FileInputStream(new File(cssFile))));
            } else {

                cssFiles.add(XMLWorkerHelper.getInstance().getDefaultCSS());
            }
            StyleAttrCSSResolver cssResolver = new StyleAttrCSSResolver(cssFiles);

            //宋体支持

            HtmlPipelineContext hpc = new HtmlPipelineContext(new CssAppliersImpl(
                    new SongFontsProvider()));

            imagePath="C:\\Users\\Administrator\\Desktop\\Apache Tomcat_8.5.16_files\\tomcat.png";
            //图片加载
            if (StringUtils.isNotBlank(imagePath)) {
                hpc.setImageProvider(new ImageProvider(imagePath));
            }
            hpc.setAcceptUnknown(true).autoBookmark(true)
                    .setTagFactory(Tags.getHtmlTagProcessorFactory());
            HtmlPipeline htmlPipeline = new HtmlPipeline(hpc, new PdfWriterPipeline(doc, pdfWriter));
            Pipeline<?> pipeline = new CssResolverPipeline(cssResolver, htmlPipeline);
            return new XMLParser(true, new XMLWorker(pipeline, true));
        }
    }

    /**
     * 找不到的字体一律改为宋体
     */
    protected static class SongFontsProvider extends XMLWorkerFontProvider {
        public SongFontsProvider() {
            super(null, null);
        }

        @Override
        public Font getFont(final String fontname, String encoding, float size, final int style) {

            if (fontname == null) {
                try {
                    final BaseFont baseFont = BaseFont.createFont("STSongStd-Light",
                            "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                    return new Font(baseFont, size, style);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return super.getFont(fontname, encoding, size, style);
        }
    }

    protected static class ImageProvider extends AbstractImageProvider {
        private String imageRootPath;

        public ImageProvider(String imageRootPath) {
            this.imageRootPath = imageRootPath;
        }

        public String getImageRootPath() {
            return imageRootPath;
        }
    }

    public static void main(String[] args) {
        try {
            parse2Pdf("C:\\Users\\Administrator\\Desktop\\Apache Tomcat_8.5.16.html", "C:\\Users\\Administrator\\Desktop\\Apache Tomcat_8.5.16.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

