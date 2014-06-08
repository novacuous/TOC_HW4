
public class Road 
{
	public String Name;
	int No;
	static int RoadNum = 0;
	final int offset = 95;
	boolean yearmonth[][];
	int count;
	int maxprice;
	int minprice;
	
	public Road(String name,int YearMonth,int price)
	{
		Name = name;
		No = RoadNum++;
		//System.out.println(Name+" Y&M"+YearMonth);
		yearmonth = new boolean[9][12];
		
		for(int i = 0 ; i < 9 ;i++)
			for(int j = 0; j < 12 ; j++)
				yearmonth[i][j] = false;
		int year =(int)( YearMonth / 100) - offset;
		int month = (int)(YearMonth % 100) - 1;
		if (year < 0 || month < 0)
			return;
		yearmonth[year][month] = true;
		count = 1;
		maxprice = minprice = price;
	}
	
	
	public void count(int YearMonth,int price)
	{
		int year =(int)( YearMonth / 100) - offset;
		int month = (int)(YearMonth % 100) - 1;
		//System.out.println(Name+"  year:"+(year+offset)+"month:"+(month+1));
		if( !yearmonth[year][month] )
		{
			yearmonth[year][month] = true;
			count++;
		}
		
		if(price > maxprice)
			maxprice = price;
		else if(price < minprice)
			minprice = price;
		
	}
		
}
