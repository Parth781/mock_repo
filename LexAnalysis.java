import java.util.*;
import java.io.*;

class Main
{
	public static ArrayList<String> key = new ArrayList<String>(); //keywords
  public static ArrayList<String> fun = new ArrayList<String>(); //predefined functions
	public static ArrayList<Character> op = new ArrayList<Character>(); //opertators
	public static ArrayList<Character> sp = new ArrayList<Character>(); //special Symbols
	public static ArrayList<String> id = new ArrayList<String>(); //identifiers/variables
	public static ArrayList<String> hf = new ArrayList<String>(); //header files
	public static ArrayList<String> charConstant = new ArrayList<String>(); //character constants
	public static ArrayList<String> intConstant = new ArrayList<String>(); //integer constants
	public static ArrayList<Character> delim = new ArrayList<Character>(); //delimiters(operator + sp char + space)
	
	public static void main(String args[])
	{
    //double startTime = System.nanoTime();

		createTables(); 
		FileReader f = null;
		try
		{
			f = new FileReader("test.txt");
			//PrintStream o = new PrintStream(new File("output.txt"));
			//PrintStream console = System.out;
			//System.setOut(o); 
		}
		catch(FileNotFoundException e)
		{System.out.println("file not found");}
		
		int ch = 0, tb=0, te=0;
		String temp = "";
		
		try
		{
			while((ch = f.read()) != -1)
			{
			
				if(tb == te)
				{
					//check operator or special symbol;
					
					if(sp.contains((char)ch))	//contains special symbol
					{
						
						System.out.print("'<SP#"+sp.indexOf((char)ch)+">' ");
						tb++;
					}
					else if(op.contains((char)ch)) //contains operator
					{
						System.out.print("'<OP#"+op.indexOf((char)ch)+">' ");
						tb++;
					}
					else if(ch == 32 || ch == 9) //space or tab space
					{
						System.out.print("# ");
						temp = "";
						tb++;
					}	
					else   //neither operator nor special symbol
						temp = temp + (char)ch;
					te++;					

				}
				else
				{
					if(delim.contains((char)ch))
					{
						
						if(ch == 34) //if quote found then character constant
						{
							if(!charConstant.contains(temp)) //if not existing make an entry
								charConstant.add(temp);
							System.out.print("'<CHAR_CONST#"+charConstant.indexOf(temp)+">' ");
							
						}							
						//check for key or identifier using temp
						
						else if(key.contains(temp)) //contains keyword 
							System.out.print("'<KEY#"+key.indexOf(temp)+">' ");
            else if(fun.contains(temp)) //contains function 
							System.out.print("'<FUN#"+fun.indexOf(temp)+">' ");
						else if(hf.contains(temp)) //contains header file
							System.out.print("'<HF#"+hf.indexOf(temp)+">' ");
						else if((temp.charAt(0) >=65 && temp.charAt(0) <=90) ||(temp.charAt(0) >=97 && temp.charAt(0) <=122))// identifier condition 
						{	
							if(!id.contains(temp)) //if not present create new entry
								id.add(temp);
							System.out.print("'<ID#"+id.indexOf(temp)+">' ");
						}
						else // numeric constant
						{
							if(!intConstant.contains(temp)) //if not existing make an entry
								intConstant.add(temp);
							System.out.print("'<NUM_CONST#"+intConstant.indexOf(temp)+">' ");
							
						}
						
						//output for delimiter
						
						if(op.contains((char)ch)) 
							System.out.print("'<OP#"+op.indexOf((char)ch)+">' ");
						if(sp.contains((char)ch))
							System.out.print("'<SP#"+sp.indexOf((char)ch)+">' ");	
						if(ch == 32 || ch == 9)
							System.out.print("# ");							
						temp = "";
						tb=te;
					}
					else
					{
						temp = temp + (char)ch;
						te++;
					}
				}
				
				if(ch==10) //end of line
				{
					System.out.println();
					tb=0;
					te=0;
					temp="";
				}	
			}
			f.close();

      //double endTime   = System.nanoTime();
       		
      System.out.println("\nliterals/integer constants : " + intConstant);
      System.out.println("literals/character constants : " + charConstant);
      System.out.println("Symbols : " + id);
      
      //double totalTime = (endTime - startTime)/1000000000;
      //System.out.println(totalTime);
      

		}
		catch(Exception e){System.out.print(e);}		
		
	}
	
	public static void createTables()
	{
		key.add("int");
		key.add("float");
		key.add("char");
		key.add("String");
    key.add("for");
		key.add("void");
		key.add("include");

    fun.add("main");
		fun.add("printf");
		fun.add("scanf");

		op.add('+');
		op.add('-');
		op.add('*');
		op.add('/');
		op.add('=');
    op.add('&');
    op.add('!');
		
		sp.add(';');
		sp.add('#');
		sp.add('{');
		sp.add('}');
		sp.add('(');
		sp.add(')');
		sp.add('<');
		sp.add('>');
		sp.add('"');
    sp.add(',');
		
		hf.add("stdio.h");
		hf.add("conio.h");
		hf.add("math.h");
		
		delim.addAll(op);
		delim.addAll(sp);
		delim.add(' ');
		delim.add((char)9);
		
    System.out.println("\nOutput : \n");
	}
}

