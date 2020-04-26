package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * @author sucre
 * @date 2020-04-26
 * @time 18:21
 * @description jsoup案例
 */
public class JsoupDemo1 {
    public static void main(String[] args) throws IOException {

//      使用类加载器获取xml文件的路径
        String path = JsoupDemo1.class.getClassLoader().getResource("student.xml").getPath();
//      解析文档，返回dom树的document对象
        Document document =  Jsoup.parse(new File(path),"utf-8");
//      获取结果的集合
        Elements elements = document.getElementsByTag("name");

        System.out.println(elements.size());
        //3.1获取第一个name的Element对象
        Element element = elements.get(0);
        //3.2获取数据
        String name = element.text();
        System.out.println(name);
    }
}
