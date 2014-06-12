/*
 * 姓名：林楷翔
 * 學號：F84006004
 * 簡述：只需給予一個資料來源json檔案的URL連結作為參數，程式會自動找出哪個地址在最多不同月份有交易的紀錄。
 * 
 */

import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.*;

import java.io.*;
public class TocHw4 {

	public static void main(String[] args) 
	{
		download(args[0]);
		
		File inFile = new File("downFile.json");
		ArrayList<District> Citylist = new ArrayList<District>();
		ArrayList<Road> Max = new ArrayList<Road>();
		
		try 
		{
			JSONArray jsonRealPrice = new JSONArray(new JSONTokener(new InputStreamReader(new FileInputStream(inFile),"UTF-8")));
			
			JSONObject tmp = jsonRealPrice.getJSONObject(0);
			Citylist.add(new District(tmp.getString("鄉鎮市區")));
			
			
				
			for(int i = 0;i < jsonRealPrice.length(); i++)
			{
				JSONObject jsontmp = jsonRealPrice.getJSONObject(i);
				
				int j = 0;
				for(j = 0 ; j < Citylist.size() && !Citylist.get(j).District.equals(jsontmp.getString("鄉鎮市區")) ;j++);
				
				if(j == Citylist.size())
					Citylist.add(new District(jsontmp.getString("鄉鎮市區")));
				
				Road temp = Citylist.get(j).matchRoad(jsontmp.getString("土地區段位置或建物區門牌"), jsontmp.getInt("交易年月"), jsontmp.getInt("總價元"));
				
				if(temp != null)
				{
					if(Max.isEmpty())
						Max.add(temp);
					else if(temp.count > Max.get(0).count)
					{
						Max.clear();
						Max.add(temp);
					}
					else if(temp.count == Max.get(0).count)
					{
						int k;
						for(k = 0 ; k < Max.size() && !Max.get(k).Name.equals(temp.Name);k++);
						
						if(k == Max.size())
							Max.add(temp);
						else
							Max.set(k, temp);
														
					}
				}
			}
			
			Collections.sort(Max,new Comparator<Road>()
			{
				@Override
				public int compare(Road arg0, Road arg1) 
				{	return arg0.No-arg1.No;}
			});
			
			for(int i = 0; i < Max.size();i++)
				System.out.println(Max.get(i).Name+", 最高成交價:"+Max.get(i).maxprice+", 最低成交價:"+Max.get(i).minprice );
			
		
		} 
		catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	static void download(String url)
	{
		//System.out.println("正在下載資料......");
		try 
		{
			URL sourse = new URL(url);
			
			HttpURLConnection consrc = (HttpURLConnection) sourse.openConnection();
		
			consrc.connect();
			
			BufferedInputStream rStream = new BufferedInputStream(sourse.openStream());
			
			File downFile = new File("downFile.json");
			
			BufferedOutputStream oStream = new BufferedOutputStream(new FileOutputStream(downFile));
			
			byte[] temp = new byte[4096];
			int rlength;
			while( (rlength = rStream.read(temp,0,4096)) != -1)
			{
				oStream.write(temp, 0, rlength);
			}
			oStream.flush();
			rStream.close();
			oStream.close();
			
			
			//System.out.println("下載完成!!");
			
			
			
		} 
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
