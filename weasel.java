import java.util.Random;
import java.util.Arrays;  
public class weasel
{

  public static void main( String[] args )
	{
	  Random rand = new Random(); 
      int upperbound = 27;
	  int upperbound2 = 27;
      int int_random = rand.nextInt(upperbound); 
	  int rando = rand.nextInt(upperbound2); 
	 String target = "METHINKS IT IS LIKE A WEASEL";
	 char[] arr1 = target.toCharArray();	
	 char[] pool = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};
	 String generations = "";
	 String[] children = new String[100];
	 int i =0;
	 char a = pool[int_random];
	  while(i <= 27) 
	   {
		 int_random = rand.nextInt(upperbound); 
		 a = pool[int_random];
		 generations += a;
		 i++;
	   }
	  
	  System.out.println("Generation 0: "+generations);
	  int u = 0;
	  i = 0;
	  char[] arr = generations.toCharArray(); 
	  upperbound = 100;
	  StringBuffer sb = new StringBuffer();
      String str = sb.toString();
	  int max = 0;
	  String compare = "";

	  while(max != 5)
	  {
		children = new String[100];
		u= 0;
	   while(u <= 99) 
	   {
		  while(i <= 27) 
	      {
			rando = rand.nextInt(upperbound2); 
			a = pool[rando];			
			if(rand.nextInt(upperbound) < 5)
			{
			 arr[i] = a;
			}
			i++;
	      }
			sb.append(arr);
			str = sb.toString();
		  children[u]= str;
		  sb = new StringBuffer();
		  u++;
		  i=0;
	   }
	    compare = ""; 
		int tally = 0;
		int[] tallyArr = new int[100];
		
   	    for(int L = 0; L < children.length; L++) 
		 {
		   compare = children[L]; 
		   System.out.println("compare: " +compare);
		   char[] arr2 = compare.toCharArray();
		   
		   for(int r = 0; r <= 27; r++) 
			{
				  					  
			  if(arr1[r] == arr2[r])
			  {
			    tally += 1;
				//System.out.println(" arr1: " +arr1[r] + "; arr2: " +arr2[r]);
			  }
			  
			  else
			  {
				 tally += 0;
			  }
			 
			}
			tallyArr[L] = tally;
			
			tally = 0;
		 }
		max = 0;
		int num2 = 0;
		 for (int num = 0; num < 98; num++) 
		 {  
           if(tallyArr[num] > max)
		   {			   
               max = tallyArr[num];
			   num = num2;
		   }
         }  
		
		//System.out.println("max similarity: " +max);
		generations = children[num2];
		System.out.println("parent: " +children[num2]);
	  }  
		System.out.println("parent: " +generations);
	}
}