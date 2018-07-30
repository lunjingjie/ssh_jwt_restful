package core.util;

import java.util.List;

/**
 * 求方差和标准差
 * @author panrongzan
 *
 */
public class VarianceAndStandardDiviation {

		//方差s^2=[(x1-x)^2 +...(xn-x)^2]/n
		public static double Variance(List<Double> x) { 
			int m=x.size();
			double sum=0;
			for(int i=0;i<m;i++){//求和
			    sum+=x.get(i);
			}
			double dAve=sum/m;//求平均值
			double dVar=0;
			for(int i=0;i<m;i++){//求方差
				dVar+=(x.get(i)-dAve)*(x.get(i)-dAve);
			}
			return dVar/m;					
		}
		
		//标准差σ=sqrt(s^2)
		public static double StandardDiviation(List<Double> x) { 
			int m=x.size();
			double sum=0;
			for(int i=0;i<m;i++){//求和
			    sum+=x.get(i);
			}
			double dAve=sum/m;//求平均值
			double dVar=0;
			for(int i=0;i<m;i++){//求方差
				dVar+=(x.get(i)-dAve)*(x.get(i)-dAve);
			}
			return Math.sqrt(dVar/m);
		}
	
}
