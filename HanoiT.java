import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class SolveHanoiTower {

	static ArrayList<String> Acts;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Please write the number of disks:");
		Acts = new ArrayList<String>();
		
		try{
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    String s = bufferRead.readLine();
		    int n = Integer.parseInt(s);
		    	        		    
		    recursiveHanoi(n,"A","B","C");
		    
		    PrintWriter pw = new PrintWriter("HT.txt", "UTF-8");
		    
		    WriteToFile(n, Acts, pw);
		    
		    //System.out.println(n);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	private static void WriteToFile(int n, ArrayList<String> actions, PrintWriter pw)
	{	
		List<List<Integer>> helper = new ArrayList<List<Integer>>();
		
		for(int i=0; i<3; i++)
		{
			List<Integer> tmp = new ArrayList<Integer>();
			helper.add(tmp);
		}
		
		for(int i=n; i>0; i--)
		{
			helper.get(0).add(i);
		}
		
		int cnt=0;
		while(actions.size() > 0)
		{
			cnt++;
			pw.println("("+ cnt + ")");
			char from = actions.get(0).charAt(0);
			char to = actions.get(0).charAt(actions.get(0).length() - 1);
			
			if(from == 'A'){
				int xx = helper.get(0).get(helper.get(0).size()-1);
				helper.get(0).remove(helper.get(0).size()-1);
				if(to == 'B')
					helper.get(1).add(xx);
				else
					helper.get(2).add(xx);
			}
			
			if(from == 'B'){
				int xx = helper.get(1).get(helper.get(1).size()-1);
				helper.get(1).remove(helper.get(1).size()-1);
				if(to == 'A')
					helper.get(0).add(xx);
				else
					helper.get(2).add(xx);
			}
			
			if(from == 'C'){
				int xx = helper.get(2).get(helper.get(2).size()-1);
				helper.get(2).remove(helper.get(2).size()-1);
				if(to == 'A')
					helper.get(0).add(xx);
				else
					helper.get(1).add(xx);					
			}
			
			//Iterate through the three towers
			for(int i = 0; i < helper.size(); i++)
			{
				for(int j=0; j<n-helper.get(i).size(); j++){
					//Write an empty line
					pw.println(WriteLine(n));					
				}
					
				for(int j=helper.get(i).size() - 1; j>=0; j--){
					//write the disk
					pw.println(WriteDisk(n, helper.get(i).get(j)));
				}
				pw.println(WriteBase(n));
				pw.println();
				
			}
			
			pw.println();
			actions.remove(0);
		}
		
		/*
		 * int cnt=0;
		pw.println("(" + cnt + ")");
					
	    for(int i=0; i<=n; i++)
	    {
	       	String ttt ="";
	        	
		    for(int j=0; j<= n-i-1; j++)
		    {
		      	ttt += " ";
		    }
		        
		    for(int j=0; j<= i; j++)
		    {
		      	ttt += "_";
		    }
		    ttt += "|";
	        	
		        ttt += reverse(ttt);
		        
		        for(int k=0; k<2; k++)
		        {	        		
		        	for(int l=0; l < 2*(n+1) + 1 ; l++)
		        	{
		        		if(l == n + 1){
		        			ttt += "|";
		        			continue;
		        		}
		        		if(i == n)
		        		{
		        			ttt += "_";
		        			continue;
		        		}
		        		ttt += " ";
		        	}	        	
		        	ttt+=" ";
		        }	        
	        	pw.println(ttt);
	        }*/
	        
		pw.close();
	}
	
	
	private static void recursiveHanoi(int n, String from, String temp, String to)
    {		
        if (n == 0)
        	return;        	
            
        
        recursiveHanoi(n - 1, from, to, temp);
        System.out.println("Move ring from " + from + " to " + to + ".");
        Acts.add(from + " " + to);
        recursiveHanoi(n - 1, temp, from, to);
        
    }
	
	public static String reverse(String s) {
	    char[] reverseStringArray = new char[s.length()];
	    for (int i = s.length() - 2, j = 0; i != -1; i--, j++) {
	        reverseStringArray[j] = s.charAt(i);
	    }
	    return new String(reverseStringArray);
	}
	
	private static String WriteLine(int n)
	{
		String tt = "";
		
		for(int l=0; l < 2*(n+1) + 1 ; l++)
    	{
    		if(l == n+1){
    			tt += "|";
    			continue;
    		}		        		
    		tt += " ";
    	}
		tt += " ";
		
		return tt;		
	}
	

	private static String WriteDisk(int n, int length)
	{
		String ttt ="";
    	
	    for(int j=0; j <= n-length; j++)
	    {
	      	ttt += " ";
	    }
	        
	    for(int j=0; j < length; j++)
	    {
	      	ttt += "_";
	    }
	    ttt += "|";
        	
	    ttt += reverse(ttt);
	    
	    return ttt;
	}
	
	private static String WriteBase(int n)
	{
		String ttt ="";
    	
		for(int l=0; l < 2*(n+1) + 1 ; l++)
    	{
    		if(l == n + 1){
    			ttt += "|";
    			continue;
    		}
    		ttt += "_";
    	}	  
		ttt += " ";
	    
	    return ttt;
	}
		

}

