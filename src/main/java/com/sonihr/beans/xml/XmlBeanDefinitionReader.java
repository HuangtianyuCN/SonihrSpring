package com.sonihr.beans.xml;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 16:28
**/

import com.sonihr.beans.AbstractBeanDefinitionReader;
import com.sonihr.beans.BeanDefinition;
import com.sonihr.beans.BeanReference;
import com.sonihr.beans.PropertyValue;
import com.sonihr.beans.ConstructorArgument;
import com.sonihr.beans.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = this.getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinition(inputStream);
    }

    private void doLoadBeanDefinition(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(inputStream);
        //解析bean
        registerBeanDefinition(doc);
        inputStream.close();
    }

    private void registerBeanDefinition(Document doc){
        Element root = doc.getDocumentElement();
        parseBeanDefinition(root);
    }

    private void parseBeanDefinition(Element root){
        NodeList nl = root.getChildNodes();
        for(int i=0;i<nl.getLength();i++){
            Node node = nl.item(i);
            if(node instanceof Element){
                Element element = (Element)node;
                String packagName = null;
                if((packagName = element.getAttribute("base-package"))!=null&&packagName!=""){
                    this.setPackageName(packagName);
                }else{
                    processBeanDefinitionele(element);
                }
            }
        }
    }


    private void processBeanDefinitionele(Element element){
        String name = element.getAttribute("id");
        String className = element.getAttribute("class");
        String scope = element.getAttribute("scope");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(element,beanDefinition);
        processConstructorArgument(element,beanDefinition);
        beanDefinition.setBeanClassName(className);
        if(scope==null||scope.length()==0||scope.equals("singleton")){
            beanDefinition.setSingleton(true);
        }else{
            beanDefinition.setSingleton(false);
        }
        getRegistry().put(name,beanDefinition);

    }
    private void processProperty(Element element,BeanDefinition beanDefinition){
        NodeList propertyNodes = element.getElementsByTagName("property");
        for(int i=0;i<propertyNodes.getLength();i++){
            Node node = propertyNodes.item(i);
            if(node instanceof Element){
                Element propertyElement = (Element)node;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                if(value!=null&&value.length()>0){//有value标签
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,value));
                }else{
                    String ref = propertyElement.getAttribute("ref");
                    if(ref==null||ref.length()==0){
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,beanReference));
                }
            }
        }
    }

    private void processConstructorArgument(Element element,BeanDefinition beanDefinition){
        NodeList constructorNodes = element.getElementsByTagName("constructor-arg");
        for(int i=0;i<constructorNodes.getLength();i++){
            Node node = constructorNodes.item(i);
            if(node instanceof Element){
                Element constructorElement = (Element)node;
                String name = constructorElement.getAttribute("name");
                String type = constructorElement.getAttribute("type");
                String value = constructorElement.getAttribute("value");
                if(value!=null&&value.length()>0){//有value标签
                    beanDefinition.getConstructorArgument().addArgumentValue(new ConstructorArgument.ValueHolder(value,type,name));
                }else{
                    String ref = constructorElement.getAttribute("ref");
                    if(ref==null||ref.length()==0){
                        throw new IllegalArgumentException("Configuration problem: <constructor-arg> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getConstructorArgument().addArgumentValue(new ConstructorArgument.ValueHolder(beanReference,type,name));
                }
            }
        }
    }
}
