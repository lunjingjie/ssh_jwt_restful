package core.util;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 读取xml文件
 * 暂只支持两层
 * @author Administrator
 *
 */
public class XmlUtil {

	private static XmlUtil instance =null;
	
	private String file_pageconfig_path = null;
	
	private XmlUtil(){
		file_pageconfig_path = this.getClass().getResource("/").getPath()+"base_config.xml";
		//乱码问题
		file_pageconfig_path = file_pageconfig_path.replaceAll("%20", " ");
		//本地测试
		//file_pageconfig_path = "C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 6.0\\webapps\\create_shishan\\WEB-INF\\classes\\base_config.xml";
	}
	
	public static synchronized XmlUtil getInstance(){
	        if (instance == null){  
	            instance = new XmlUtil();
	        }  
	        return instance;   
	} 
	
	public List<String> getFirstNodeNames(String parentNodeName){
		List<String> nodeNameLists = new ArrayList<String>();
		try {
	    	SAXReader reader = new SAXReader();
			Document document = reader.read(new File(file_pageconfig_path));
			
		    //获取文档的根节点(即最大那个节点)
		    Element rootElement = document.getRootElement();
		    //对某节点下得所有子节点（不包括孙节点）进行遍历（这里是对根节点）,
		    for ( Iterator i = rootElement.elementIterator(); i.hasNext(); ) {
		    	   Element element = (Element) i.next();
	    	        if(parentNodeName.equals(element.getName())){
	    	        	for(Iterator b = element.elementIterator(); b.hasNext();){
		    	        	Element elementd = (Element) b.next();
			    	        String nodeName = elementd.getName();
			    	        nodeNameLists.add(nodeName);
		    	        }
	    	        }             
		   }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return nodeNameLists;
	}
	
	/**
	 * 获取xml一级节点值
	 * @param node
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getOneValue(String node){     
	    try {
	    	SAXReader reader = new SAXReader();
			Document document = reader.read(new File(file_pageconfig_path));
			
		    //获取文档的根节点(即最大那个节点)
		    Element rootElement = document.getRootElement();
		    
		    //对某节点下得所有子节点（不包括孙节点）进行遍历（这里是对根节点）,
    	    for ( Iterator i = rootElement.elementIterator(); i.hasNext(); ) {
    	        Element element = (Element) i.next();
    	        if(node.equals(element.getName())){
    	        	return element.getText();
    	        }
    	    }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取xml二级节点值
	 * @param firstNode
	 * @param secondNode
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getTwoValue(String firstNode,String secondNode){     
	    try {
	    	SAXReader reader = new SAXReader();
			Document document = reader.read(new File(file_pageconfig_path));
			
		    //获取文档的根节点(即最大那个节点)
		    Element rootElement = document.getRootElement();
		    
		    for ( Iterator i = rootElement.elementIterator(); i.hasNext(); ) {
		    	   Element element = (Element) i.next();
	    	        if(firstNode.equals(element.getName())){
	    	        	for(Iterator b = element.elementIterator(); b.hasNext();){
		    	        	Element elementd = (Element) b.next();
			    	        if(secondNode.equals(elementd.getName())){
			    	        	return elementd.getText();
			    	        }
		    	        }
	    	        }             
		   }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据xpath直接查找
	 * @param xpath
	 * @return
	 */
	public String getValueByXpath(String xpath){     
		String str = null;
		try {
	    	SAXReader reader = new SAXReader();
			Document document = reader.read(new File(file_pageconfig_path));
			Node node = document.selectSingleNode(xpath);
			if(node == null){
				str = null;
			}else{
				str = node.getText();
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 根据传入的xml字符串，查找特定的节点值
	 * @param xml xml字符串
	 * @param node 查找的节点名称
	 * @return
	 */
	public String getTargetValueByStringXml(String xml,String node){
		try {
			Document document = DocumentHelper.parseText(xml);
		    Element rootElement = document.getRootElement();
		    
    	    for ( Iterator i = rootElement.elementIterator(); i.hasNext(); ) {
    	        Element element = (Element) i.next();
    	        if(node.equals(element.getName())){
    	        	return element.getText();
    	        }
    	    }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Object[] areas = XmlUtil.getInstance().getFirstNodeNames("zipCode").toArray();
		String area = (String) areas[0];
		System.out.println(area);
	}
}
