package com.ynov.soap.webservice;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
    }

    ///

    @Bean(name = "author")
    public DefaultWsdl11Definition authorsWsdl11Definition(XsdSchema authorsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AuthorPort");
        wsdl11Definition.setLocationUri("/ws/author");
        wsdl11Definition.setTargetNamespace("http://soap.ynov.com/webservice/author");
        wsdl11Definition.setSchema(authorsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema authorsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/author.xsd"));
    }

    ///

    @Bean(name = "book")
    public DefaultWsdl11Definition booksWsdl11Definition(XsdSchema booksSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BookPort");
        wsdl11Definition.setLocationUri("/ws/book");
        wsdl11Definition.setTargetNamespace("http://soap.ynov.com/webservice/book");
        wsdl11Definition.setSchema(booksSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema booksSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/book.xsd"));
    }
}
