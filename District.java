import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class District 
{
	
	String District;

	ArrayList<Road> Roadlist;
	
	public District( String DistrictName)
	{
		
		District = DistrictName;
		//System.out.println(District);
		
		Roadlist = new ArrayList<Road>();
		
	}
	
	public Road matchRoad(String address,int YearMonth,int price)
	{
		/*
		int end = address.indexOf("路");
		
		if(end == -1)
			end = address.indexOf("街");
		
		if(end == -1)
			end = address.indexOf("巷");
		
		if(end == -1)
			end = address.indexOf("大道");
		if(end == -1)
			return null;
	
		String road = address.substring(0, end+1);
*/
		
		Pattern roadFormat = Pattern.compile(".*" + District +"[^路街]*(?:[路街]|大道|[^0-9]巷)"); //.*XX區.*(?:[路街巷]|大道)
		Matcher match_road = roadFormat.matcher(address);
		String road;

		if(match_road.find())
			road = address.substring(match_road.start(),match_road.end());
		else 
			return null;

	//	System.out.println(road);
		
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