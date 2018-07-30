package core.util;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * 对List集合中的对象进行排序
 * @author panrongzan
 *
 */
public class ListSort implements Comparator
{
	private static Logger log =Logger.getLogger("currentData");
	
	public ListSort(String sortType) {
		super();
		this.sortType = sortType;
	}
	/***
	 * @param 比较对象的属性用String[]的形式传过来 比较的对象一定要符合javaBean，即要有Set,Get方法
	 * */
	String[] fields_bean = null;
	
	/**
	 * 排序方式 desc asc
	 */
	String sortType = null;
	
	public String[] getFields_bean() {
		return fields_bean;
	}

	public void setFields_bean(String[] fields_bean) {
		this.fields_bean = fields_bean;
	}
	
	/**
	 * 定义排序规则 如果按照不止一个属性进行排序 这按照属性的顺序进行排序,类是sql order by 即只要比较出同位置的属性就停止
	 * */
	public int compare(Object obj1, Object obj2) {
		// 没有属性，则不排序
		if (fields_bean == null || fields_bean.length <= 0) 
		{
			return 2;// 不比较
		}
		for (int i = 0; i < fields_bean.length; i++) {
			//根据sortType进行排序方法重写
			if(("desc").equals(sortType)){
				return compareFieldDesc(obj1, obj2, fields_bean[i]);
			}else{
				return compareFieldAsc(obj1, obj2, fields_bean[i]);
			}
		}
		// 相等返回0
		return 0;
	}
	
	private static int compareFieldDesc(Object o1, Object o2, String fieldName) {
		try {
			String value1 = getFieldValueByName(fieldName, o1).toString();
			String value2 = getFieldValueByName(fieldName, o2).toString();
			
			String typeMsg = getFieldAttribute(fieldName,o1);
			
			if(("nostr").equals(typeMsg))
			{
				if ((Double.parseDouble(value1) - Double.parseDouble(value2)) > 0) {
					return -1;
				}else if((Double.parseDouble(value1) - Double.parseDouble(value2)) < 0){
					return 1;
				}else{
					return 0;
				}
			}else{
				return value1.compareTo(value2);
			}	
		} catch (Exception e) {
			log.error("对象的该属性不存在或者不允许在此安全级别上反射该属性");
			e.printStackTrace();
		}
		return 0;
	}
	
	private static int compareFieldAsc(Object o1, Object o2, String fieldName) {
		try {
			String value1 = getFieldValueByName(fieldName, o1).toString();
			String value2 = getFieldValueByName(fieldName, o2).toString();
			
			String typeMsg = getFieldAttribute(fieldName,o1);
			
			if(("nostr").equals(typeMsg))
			{
				if ((Double.parseDouble(value1) - Double.parseDouble(value2)) > 0) {
					return 1;
				}else if((Double.parseDouble(value1) - Double.parseDouble(value2)) < 0){
					return -1;
				}else{
					return 0;
				}
			}else{
				return -value1.compareTo(value2);
			}	
		} catch (Exception e) {
			log.error("对象的该属性不存在或者不允许在此安全级别上反射该属性");
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * @param fieldName
	 * 属性名 obj 对象 反射获得该属性的值
	 * */
	private static Object getFieldValueByName(String fieldName, Object obj) {
		try {
			String Letter = fieldName.substring(0, 1).toUpperCase();
			String methodStr = "get" + Letter + fieldName.substring(1);
			Method method = obj.getClass().getMethod(methodStr, new Class[] {});

			Object value = method.invoke(obj, new Object[] {});
			return value;
		} catch (Exception e) {
			System.out.println("---------该" + fieldName + "属性不存在----------------------");
			return null;
		}
	}
	
	/**
	 * 获取属性的类型
	 * @param fieldName
	 * @return 属性类型
	 */
	private static String getFieldAttribute(String fieldName, Object obj)
	{
		try {			
			Field[] f = obj.getClass().getDeclaredFields();
						
			for(int i=0;i<f.length;i++){
				String name = f[i].getName();
				Class<?> type = f[i].getType();
				if((fieldName).equals(name)){
					if(("int").equals(type.getSimpleName()) || ("double").equals(type.getSimpleName()) || ("float").equals(type.getSimpleName())){
						return "nostr";
					}else{
						return "str";
					}
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
