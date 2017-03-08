import java.io.*;
import java.util.LinkedList;
import java.util.Arrays;


public class trFormat
{	
	
	private static LinkedList<String> ipdata = new LinkedList<String>(); 
	
	
	public static void main(String[] args)
	{
		
		//outfn = args[2];

		readFile(args[0]);
		
		outf(ipdata, args[1]);	 
	
		
	}

	private static void readFile(String fn){
		try{
			FileReader fr = new FileReader(fn);
			BufferedReader br = new BufferedReader(fr);
			
			String line; 
			br = new BufferedReader(new FileReader(fn));
			
			while((line = br.readLine())!= null){
				//System.out.printf("currentline : %s\n", line);
				getipf(line);
			}
			
		}
		catch(IOException e){
			e.printStackTrace();
			
		}
			
	}

	private static void getipf(String line){
		String[] data = line.split("  ");

		for(int i = 0;i<data.length;i++)data[i] = data[i].trim();
		
		if(!data[0].matches("[-+]?\\d*\\.?\\d+")){
			System.out.println("NaN");
			return;
		}
		else if(data[1].equals("*")){
			System.out.println("'*' found");
			return;			
		}
		else{
			System.out.printf("'%s' added\n",data[1]);
			ipdata.add(data[1]);
		}
		
	}

	
	
	private static void outf(LinkedList<String> data, String outfn){
		outfn+=".txt";
		try{
			PrintWriter writer = new PrintWriter(outfn,"UTF-8");
			int c = 0;
			String tuple = "'";

			for(String ip : ipdata){
			
				if(c == 0){
				System.out.println("first");
					tuple+=ip;
					tuple+="'";
					tuple+="  -- '";
					c++;
				}
				if(c==1){
					tuple+=ip;
					tuple+="'";
					c++;
				}
	
				else if(c ==2 ){
					System.out.printf("%s added to file\n",tuple);
					writer.println(tuple);
					tuple="'";
					c = 0;
					
				}
				
						
			}
			writer.close();

		}catch(IOException e){
			System.out.println("its fukd m8");
		}
	

	}
} 



