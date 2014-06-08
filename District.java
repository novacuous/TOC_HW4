import java.util.ArrayList;


public class District 
{
	
	String District;

	ArrayList<Road> Roadlist;
	
	public District( String DistrictName)
	{
		
		District = DistrictName;
		
		Roadlist = new ArrayList<Road>();
		
	}
	
	public Road matchRoad(String address,int YearMonth,int price)
	{
		int end = address.indexOf("¸ô");
		
		if(end == -1)
			end = address.indexOf("µó");
		
		if(end == -1)
			end = address.indexOf("«Ñ");
		
		if(end == -1)
			return null;
		
		String road = address.substring(0, end+1);
		
		//System.out.println(road);
		
		int i;
		for( i = 0;i < Roadlist.size() && !Roadlist.get(i).Name.equals(road);i++);
		
		if(i == Roadlist.size())
		{
			Road ret = new Road(road,YearMonth,price);
			Roadlist.add(ret);
			return ret;
		}
		else
		{
			Roadlist.get(i).count(YearMonth, price);
			return Roadlist.get(i);
		}
	}
	
}