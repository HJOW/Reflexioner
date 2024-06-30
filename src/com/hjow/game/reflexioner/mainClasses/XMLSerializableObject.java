/*
Copyright 2024 HJOW (hujinone22@naver.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.hjow.game.reflexioner.mainClasses;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLSerializableObject implements XMLSerializable {
    private static final long serialVersionUID = -4805345756979889703L;
    public Document toXMLDocument()
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.setXmlStandalone(true);
            
            String className = this.getClass().getName();
            
            Element root = doc.createElement(this.getClass().getSimpleName());
            root.setAttribute("class", className);
            doc.appendChild(root);
            
            String classType = "class";
            if(className.equals("byte") || className.equals("char") || className.equals("double") || className.equals("float")
            || className.equals("int" ) || className.equals("long") || className.equals("short" ) || className.equals("boolean")) classType = "primitive";
            
            boolean islist = false;
            boolean ismap  = false;
            
            if(this instanceof List<?>)
            {
                islist = true;
                classType = "list";
            }
            
            if(this instanceof Map<?, ?>)
            {
                ismap = true;
                classType = "map";
            }
            
            root.setAttribute("classType", classType);
            
            if(islist)
            {
                Element child;
                List<?> listObj = (List<?>) this;
                int i = 0;
                for(Object obj : listObj)
                {
                    child = createXMLChild(doc, obj);
                    child.setAttribute("listindex", String.valueOf(i));
                    if(child != null) root.appendChild(child);
                    i++;
                }
            }
            else if(ismap)
            {
                Element child;
                Map<?, ?> mapObj = (Map<?, ?>) this;
                Set<?> set = mapObj.keySet();
                for(Object k : set)
                {
                    Object obj = mapObj.get(k);
                    child = createXMLChild(doc, obj);
                    child.setAttribute("mapkey", String.valueOf(k));
                    if(child != null) root.appendChild(child);
                }
            }
            else
            {
                Element child = null;
                Class<?> classes = this.getClass();
                Field[] fields = classes.getFields();
                for(Field f : fields)
                {
                    if(f.isAccessible())
                    {
                        try
                        {
                            Object obj = f.get(this);
                            
                            child = createXMLChild(doc, obj);
                            if(child != null) root.appendChild(child);
                        }
                        catch (IllegalArgumentException e) { }
                    }
                }
            }
            
            return doc;
        } 
        catch (ParserConfigurationException e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
        catch (IllegalArgumentException e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
        catch (IllegalAccessException e) 
        {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    public static Element createXMLChild(Document doc, Object obj)
    {
        Element child = null;
        if(obj instanceof XMLSerializable)
        {
            Document docObj = ((XMLSerializable) obj).toXMLDocument();
            child = docObj.getDocumentElement();
        }
        else
        {
            if((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Byte)
            || (obj instanceof Float)   || (obj instanceof Double)
            || (obj instanceof Boolean)
            || (obj instanceof Character))
            {
                String primitiveName = "";
                if(obj instanceof Byte     ) primitiveName = "byte";
                if(obj instanceof Integer  ) primitiveName = "int";
                if(obj instanceof Long     ) primitiveName = "long";
                if(obj instanceof Float    ) primitiveName = "float";
                if(obj instanceof Double   ) primitiveName = "double";
                if(obj instanceof Boolean  ) primitiveName = "boolean";
                if(obj instanceof Character) primitiveName = "char";
                
                child = doc.createElement(primitiveName);
                child.setAttribute("class", primitiveName);
                child.setAttribute("classType", "primitive");
                child.setNodeValue(String.valueOf(obj));
            }
        }
        return child;
    }
}
