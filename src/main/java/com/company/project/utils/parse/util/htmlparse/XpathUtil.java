package com.company.project.utils.parse.util.htmlparse;


import org.htmlcleaner.*;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

/**
 * Created by rock on 4/9/15.
 */
public class XpathUtil {
    String rawText;

//    String  baseUri;
//    private Document doc;

    public XpathUtil(String rawText) {
        this.rawText = rawText;
    }

    //    public XpathUtil(    String rawText,String  baseUri){
//        this.rawText=rawText;
//        this.baseUri=baseUri;
//    }
    public String xpath(String XPath) {
        if (XPath.endsWith("/text()")) {
            return xpathwithText(XPath.substring(0, XPath.length() - 7));
        } else if (XPath.endsWith("/allText()")) {
            return xpathwithAllText(XPath.substring(0, XPath.length() - 10));
        } else if (XPath.endsWith("/@value")) {
            return xpathwithvalue(XPath.substring(0, XPath.length() - 7), "value");
        } else {
            int lindex = XPath.lastIndexOf("/@");
            if (lindex < 0) {
                return xpathwithText(XPath);
            } else {
                return xpathwithvalue(XPath.substring(0, lindex), XPath.substring(lindex + 2, XPath.length()));
            }
        }
    }

    public Object[] _xpath(String XPath) {
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        TagNode tagNode = htmlCleaner.clean(rawText);
        Object[] tbodyNodeArray = null;
        try {
            tbodyNodeArray = tagNode.evaluateXPath(XPath);

        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return tbodyNodeArray;
    }

    public String xpathwithvalue(String Xpath, String attributename) {
//        List<String> list = new ArrayList<String>();
        Object[] tbodyNodeArray = this._xpath(Xpath);
        for (Object on : tbodyNodeArray) {
            TagNode n = (TagNode) on;
            //System.out.println("\ttext="+
            if (n.getAttributeByName(attributename) != null)
                return n.getAttributeByName(attributename);
//            list.add(n.getAttributeByName(attributename).toString());
        }
//        return list.toArray(new String[list.size()]);
        return "";
    }

    public String xpathwithText(String Xpath) {
//        List<String> list = new ArrayList<String>();
        Object[] tbodyNodeArray = this._xpath(Xpath);
        for (Object on : tbodyNodeArray) {
            TagNode n = (TagNode) on;
            //System.out.println("\ttext="+n.getText());
            if (n.getText() != null)
                return n.getText().toString();
//            list.add(n.getText().toString());
        }
//        return list.toArray(new String[list.size()]);
        return "";
    }

    public String xpathwithAllText(String Xpath) {
        StringBuffer bf = new StringBuffer();
        Object[] tbodyNodeArray = this._xpath(Xpath);
        for (Object on : tbodyNodeArray) {
            TagNode n = (TagNode) on;
            if (n.getText() != null)
                bf.append(n.getText().toString());
            bf.append(getchildtext(n));
        }
        return bf.toString();
    }

    private String getchildtext(TagNode n) {
        StringBuffer bf = new StringBuffer();
        TagNode[] tbodyNodeArray = n.getChildTags();
        for (TagNode on : tbodyNodeArray) {
            bf.append(on.getText().toString());
        }
        return bf.toString();
    }

    //http://www.cnblogs.com/kavlez/p/4049210.html
    public void expath(String html, String exp) throws Exception {
        HtmlCleaner hc = new HtmlCleaner();
        TagNode tn = hc.clean(html);
        org.w3c.dom.Document dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
        XPath xPath = XPathFactory.newInstance().newXPath();
        Object result;
        result = xPath.evaluate(exp, dom, XPathConstants.NODESET);
        if (result instanceof NodeList) {
            NodeList nodeList = (NodeList) result;
            System.out.println(nodeList.getLength());
            for (int i = 0; i < nodeList.getLength(); i++) {
                org.w3c.dom.Node node = nodeList.item(i);
                System.out.println(node.getNodeValue() == null ? node.getTextContent() : node.getNodeValue());
            }
        }
    }

    public static void main(String[] argx) throws Exception {
//        File file=new File("/tmp/1.html");
//        System.out.println("file is"+ file.getName());
//        StringBuffer buffer=new StringBuffer();
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        String line = null;
//        while ((line = reader.readLine()) != null) {
//            buffer.append(line);
//        }
////        System.out.println(buffer.toString());
//        for(int i=0;i<20;i++) {
//            String href = new Html(buffer.toString()).xpath("//tr[@id='aj_view\"+i+\"']/td[9]/span/allText()").toString();
//            System.out.println("href:"+ href);
//    }

        XpathUtil xpathutil = new XpathUtil("<span class=\"icon-pit icon-service-duliang\"><b>0.82</b>元/片</span>");
        String href = xpathutil.xpath("//span/text()").toString();
        System.out.println("href:" + href);

    }
}
