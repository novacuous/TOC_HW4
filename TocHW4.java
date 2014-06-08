/*
 * �m�W�G�L����
 * �Ǹ��GF84006004
 * ²�z�G�u�ݵ����@�Ӹ�ƨӷ�json�ɮת�URL�s���@���ѼơA�{���|�۰ʧ�X���Ӧa�}�b�̦h���P���������������C
 * 
 */

import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.*;

import java.io.*;
public class TocHW4 {

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
			Citylist.add(new District(tmp.getString("�m����")));
			
			
				
			for(int i = 0;i < jsonRealPrice.length(); i++)
			{
				JSONObject jsontmp = jsonRealPrice.getJSONObject(i);
				
				int j = 0;
				for(j = 0 ; j < Citylist.size() && !Citylist.get(j).District.equals(jsontmp.getString("�m����")) ;j++);
				
				if(j == Citylist.size())
					Citylist.add(new District(jsontmp.getString("�m����")));
				
				Road temp = Citylist.get(j).matchRoad(jsontmp.getString("�g�a�Ϭq��m�Ϋت��Ϫ��P"), jsontmp.getInt("����~��"), jsontmp.getInt("�`����"));
				
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
				System.out.println(Max.get(i).Name+", �̰������:"+Max.get(i).maxprice+", �̧C�����:"+Max.get(i).minprice );
			
		
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
		//System.out.println("���b�U�����......");
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
			
			
			//System.out.println("�U������!!");
			
			
			
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
