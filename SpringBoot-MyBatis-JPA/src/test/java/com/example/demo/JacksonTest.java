package com.example.demo;

import com.example.demo.pojo.Book;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class JacksonTest {

    @Test
    public void testParseXml() throws IOException {
        InputStream input = JacksonTest.class.getResourceAsStream("/book.xml");
        JacksonXmlModule module = new JacksonXmlModule();
        XmlMapper mapper = new XmlMapper(module);
        Book book = mapper.readValue(input, Book.class);

        System.out.println(book);
        // Book{id=1, name='三国演义', author='罗贯中'}

        // 转xml
        String xml = mapper.writeValueAsString(book);
        System.out.println(xml);
        //    <Book><id>1</id><name>三国演义</name><author>罗贯中</author></Book>
    }

    @Test
    public void testParseJson() throws IOException {
        InputStream input = JacksonTest.class.getResourceAsStream("/book.json");
        ObjectMapper mapper = new ObjectMapper();

        // 反序列化时忽略不存在的JavaBean属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Book book = mapper.readValue(input, Book.class);

        System.out.println(book);
        // Book{id=1, name='三国演义', author='罗贯中'}

        //JavaBean变为JSON
        String json = mapper.writeValueAsString(book);
        System.out.println(json);
        //    {"id":1,"name":"三国演义","author":"罗贯中"}
    }
}
