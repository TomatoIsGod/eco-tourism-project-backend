package com.zillionwon.spider;

import com.zillionwon.spider.util.JsonUtils;
import com.zillionwon.spider.util.ParserUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 携程测试爬虫
 *
 * @author InwardFlow
 * @see <a href="https://you.ctrip.com/sight/suzhou11/s0-p1.html#sightname">用于抓取苏州景点攻略</a>
 */
public class CtripDataProcessor {
    private final static String BASE_URL = "https://you.ctrip.com";
    private final static String OUTPUT_PATH = "output.txt";
    public static void main(String[] args) throws IOException, InterruptedException {
        for (URL url : getUrls(new URL(BASE_URL))) {
            process(url);
        }
    }

    /**
     * 从首页获取所有城市信息，并转化为对应 [景点页] 的 URL，例如<a href="https://you.ctrip.com/sight/suzhou11/s0-p1.html">苏州景区</a>
     * @param baseUrl 首页地址
     * @return URL 集合
     */
    private static List<URL> getUrls(URL baseUrl) throws IOException {
        Document doc = Jsoup.connect(baseUrl.toString()).get();
        return doc.getElementsByClass("city-selector-tab-main-city-list-item").stream()
                .map(element -> Optional.ofNullable(element.attr("href")))
                .filter(Optional::isPresent)
                .map(url -> {
                    try {
                        return new URL(baseUrl, url.get()
                                .replace("place", "sight")
                                .replace(".html", "/s0-p1.html"));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList()); // 收集结果到列表
    }

    public static void process(URL url) throws IOException, InterruptedException {
        // 起始页
        Document doc = Jsoup.connect(url.toString()).get();
        // 计数器，采集前 N 页的数据，当前已到第 -count 页
        int count = -1;
        // 是否还有下一页
        boolean hasNext;

        do {
            hasNext = !JsonUtils.toAjax(doc.getElementsByAttributeValue("title", "Next Page").get(0).attr("aria-disabled"));
            Elements sightElements = doc.getElementsByClass("_39IWXatdWDBeX-dBcPkTnh ");
            for (Element e : sightElements) {
                String content = (JsonUtils.toJson(new CtripField.Builder()
                        .name(e.getElementsByClass("_3XufXOGBqMUKZwTEMtDvr5").get(0).select("a").text())
                        .level(e.getElementsByClass("_1gkV_2zLZ1MV0YXnnO3XxD _3BoqeUpaMH9hRRjMc--5zi").text())
                        .rankTag(e.getElementsByClass("_2CrTlg05GVCB_A7oBEurDt").text())
                        .heat(ParserUtils.keepDigit(e.getElementsByClass("_2-f9WAzGqSB1X8OjQnCsaD").text()))
                        .score(ParserUtils.keepDigit(e.getElementsByClass("_2nX2IUMtjcHyj2bbI23aXV _3DJ_Ng1rSYx19hPf71hW-1").text()))
                        .raterCount(e.getElementsByClass("_2nX2IUMtjcHyj2bbI23aXV").last().text())
                        .address(ParserUtils.splitAddress(e.getElementsByClass("_3CIR0seeyY8j14XCMZj1jz"))[0])
                        .distanceFromCity(ParserUtils.splitAddress(e.getElementsByClass("_3CIR0seeyY8j14XCMZj1jz"))[1])
                        .price(ParserUtils.keepDigit(e.getElementsByClass("_1Rdxx7xQcoWliTlGQvnGj3").text()))
                        .smallImageUrl(new URL(e.getElementsByClass("_3FFJ2DrCb82G9j3KtlMAQU").get(0).attr("src").replace("?proc=autoorient", "")))
                        .tags(new ArrayList<>(e.getElementsByClass("_3axTd5Yy3ILLzK4EzvlYBa").stream().map(Element::text).collect(Collectors.toList())))
                        .closeTime(e.getElementsByClass("_3X5L8EpkeI5-I7ss4SCFYB").text())
                        .build()));
                new FilePrinter(OUTPUT_PATH).printToFile(content);
            }
            url = new URL(new URL(BASE_URL), doc.getElementsByAttributeValue("title", "Next Page").get(0).getElementsByAttribute("href").attr("href"));
            doc = Jsoup.connect(url.toString()).get();
            count--;
            // 停顿 1 秒翻页
            Thread.sleep(1000);
        } while (hasNext && count != 0);
    }

    @Deprecated
    public void process(Page<CtripField> page) {
        String url = page.getUrl();
        try {
            Document doc = Jsoup.connect(url).get();
            page.setHasNext(JsonUtils.toAjax(doc.getElementsByAttributeValue("title", "Next Page").get(0).attr("aria-disabled")));
            Elements sightElements = doc.getElementsByClass("_39IWXatdWDBeX-dBcPkTnh ");
            for (Element e : sightElements) {
                page.add(new CtripField.Builder()
                        .name(e.getElementsByClass("_3XufXOGBqMUKZwTEMtDvr5").get(0).select("a").text())
                        .level(e.getElementsByClass("_1gkV_2zLZ1MV0YXnnO3XxD _3BoqeUpaMH9hRRjMc--5zi").text())
                        .rankTag(e.getElementsByClass("_2CrTlg05GVCB_A7oBEurDt").text())
                        .heat(Double.valueOf(e.getElementsByClass("_2-f9WAzGqSB1X8OjQnCsaD").text()))
                        .score(Double.valueOf(e.getElementsByClass("_2nX2IUMtjcHyj2bbI23aXV").get(1).text()))
                        .raterCount(e.getElementsByClass("_2nX2IUMtjcHyj2bbI23aXV").get(2).text())
                        .address(e.getElementsByClass("_3CIR0seeyY8j14XCMZj1jz").get(0).text())
                        .distanceFromCity(e.getElementsByClass("_3CIR0seeyY8j14XCMZj1jz").get(2).text().replace("距市中心", ""))
                        .price(ParserUtils.keepDigit(e.getElementsByClass("_1Rdxx7xQcoWliTlGQvnGj3").text()))
                        .smallImageUrl(new URL(e.getElementsByClass("_3FFJ2DrCb82G9j3KtlMAQU").get(0).attr("src").replace("?proc=autoorient", "")))
                        .tags(new ArrayList<>(e.getElementsByClass("_3axTd5Yy3ILLzK4EzvlYBa").stream().map(Element::text).collect(Collectors.toList())))
                        .closeTime(e.getElementsByClass("_3X5L8EpkeI5-I7ss4SCFYB").text())
                        .build());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
