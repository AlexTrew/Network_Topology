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
		//outfn+=".txt";
		try{
			FileWriter fw = new FileWriter(outfn, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter writer = new PrintWriter(bw);
			int c = 0;
			int q = 0;
			String tuple = "";
			
			int i = 0;

			while(c < data.size()){

				//System.out.println(data.get(i));
				if(i == 0){
				System.out.println("first");
					tuple+=("\""+ data.get(c) +"\"");;
					
					tuple+="  -- ";
					c++;
					q++;
					i++;
					continue;
				}

				if(q==0){
				//System.out.println("first");
					tuple+=("\""+ data.get(c) +"\"");;
					tuple+="  -- ";
					c++;
					q++;
					continue;
				}
				if(q==1){
					tuple+=("\""+ data.get(c) +"\"");
					q++;
					continue;
				}
	
				else if(q ==2 ){
					System.out.printf("%s added to file\n",tuple);
					writer.println(tuple);
					q = 0;
					tuple="";
					
				}
				
						
			}
			
			writer.close();

		}catch(IOException e){

		}
	

	}
} 



