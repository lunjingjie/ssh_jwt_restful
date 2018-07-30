package core.util;

import java.util.Iterator;
import java.util.List;

public class ListUtils {

	/**
	 * 判断集合
	 * @param ids
	 * @param id
	 * @return
	 */
	public static boolean isContains(List<Integer> ids,Integer id){
		for(Iterator<Integer> it = ids.iterator();it.hasNext();){
			Integer _id = it.next();
			if(id == _id){
				return true;
			}
		}
		return false;
	}
	
}
