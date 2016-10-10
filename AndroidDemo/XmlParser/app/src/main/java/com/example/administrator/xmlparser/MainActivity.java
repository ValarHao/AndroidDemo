package com.example.administrator.xmlparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    private String str = "<apps>\n" +
            "<app>\n" +
            "<id>1</id>\n" +
            "<name>Google Maps</name>\n" +
            "<version>1.0</version>\n" +
            "</app>\n" +
            "<app>\n" +
            "<id>2</id>\n" +
            "<name>Chrome</name>\n" +
            "<version>2.1</version>\n" +
            "</app>\n" +
            "<app>\n" +
            "<id>3</id>\n" +
            "<name>Google Play</name>\n" +
            "<version>2.3</version>\n" +
            "</app>\n" +
            "</apps>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Pull解析方式
         * 解析到的事件类型：
         * START_DOCUMENT => 读取到xml的开始
         * END_DOCUMENT => 读取到xml的结束
         * START_TAG => 读取到xml的开始标签
         * END_TAG => 读取到xml的结束标签
         * TEXT => 读取到xml的文本
         */
        /**try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance(); //创建Pull解析器工厂
            XmlPullParser xmlPullParser = factory.newPullParser(); //工厂生产一个Pull解析器
            xmlPullParser.setInput(new StringReader(str)); //将xml数据放入xml解析器
            int eventType = xmlPullParser.getEventType(); //获取解析到的事件类型
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName(); //获取当前节点名称
                switch (eventType) {
                    //开始解析某个节点
                    case XmlPullParser.START_TAG: {
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText(); //获取该节点下的内容
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    }
                    //完成解析某个节点
                    case XmlPullParser.END_TAG: {
                        if ("app".equals(nodeName)) {
                            Log.d("xml", "id is " + id);
                            Log.d("xml", "name is " + name);
                            Log.d("xml", "version is " + version);
                        }
                        break;
                    }
                }
                eventType = xmlPullParser.next(); //获取下一个解析事件类型
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /**
         * SAX解析方式
         */
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance(); //创建SAX解析器工厂
            XMLReader xmlReader = factory.newSAXParser().getXMLReader(); //产生一个XMLReader
            ContentHandler handler = new ContentHandler();
            xmlReader.setContentHandler(handler); //将解析类实例传入xmlReader
            xmlReader.parse(new InputSource(new StringReader(str))); //执行解析
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ContentHandler extends DefaultHandler {

        private String nodeName;
        private StringBuilder id;
        private StringBuilder name;
        private StringBuilder version;

        @Override
        public void startDocument() throws SAXException {
            id = new StringBuilder();
            name = new StringBuilder();
            version = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            //记录当前节点名
            nodeName = localName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            //根据当前的节点名判断将内容添加到哪一个StringBuilder对象中
            if ("id".equals(nodeName)) {
                id.append(ch, start, length);
            } else if ("name".equals(nodeName)) {
                name.append(ch, start, length);
            } else if ("version".equals(nodeName)) {
                version.append(ch, start, length);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("app".equals(localName)) {
                //处理后的String是带回车键的，因此trim()将回车去除
                Log.d("xml", "id is " + id.toString().trim());
                Log.d("xml", "name is " + name.toString().trim());
                Log.d("xml", "version is " + version.toString().trim());
                //将StringBuilder清空掉
                id.setLength(0);
                name.setLength(0);
                version.setLength(0);
            }
        }

        @Override
        public void endDocument() throws SAXException {
            Log.d("xml", "parser is end!");
        }
    }
}
