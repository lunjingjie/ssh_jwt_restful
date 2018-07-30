package core.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayUtil {

	/**
	 * 去掉字符串数组中重复的值  
	 * @param array
	 * @return
	 */
    public static Object[] removeDuplicated(Object[] array) {  
        Set<Object> set = new HashSet<Object>();  
        for (int i=0; i<array.length; i++) {  
        	set.add(array[i]);  
        }  
        Object[] newArray =  set.toArray(); 
        return newArray;
    } 
    
    /**
     *  判断某个字符串是否存在于数组中
     *  @param stringArray 原数组
     *  @param source 查找的字符串
     *  @return 是否找到
     */
   public static boolean arryContains(String[] stringArray, String source) {
       // 转换为list
       List<String> tempList = Arrays.asList(stringArray);
       // 利用list的包含方法,进行判断
       if(tempList.contains(source)){
           return true;
       } else {
           return false;
       }
   }
}
