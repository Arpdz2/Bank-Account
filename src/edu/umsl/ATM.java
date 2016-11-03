/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umsl;
import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author arpdz2
 */
public class ATM implements Serializable
{
    public static int inputtime;
    public static int accountph;
    public static int notfirsttime;
    
    Account AcctArray[] = new Account[3];
    
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException
    {
            
        
               	ATM atm=new ATM();
                atm.populateaccts();
               
                atm.startMenu();
                
                atm.write();
     
     
    }
    
    public void populateaccts() throws IOException
    {
         
       Scanner sc = new Scanner(System.in);
        System.out.println("Is this your first time running the program: 1 = yes, 2 = no");
        inputtime = sc.nextInt();
   
        if (inputtime == 1)
      {          
          for(int i=0; i <AcctArray.length; i++)
            {
                AcctArray[i] = new Account(100.00);
            }
          System.out.println("Type 1 to open a checking account.  Type 2 to open a savings account.");
          int input = sc.nextInt();
                    if (input == 1)
                    {
                        System.out.println("What account do you want to use: [0, 1, 2]");
                        accountph = sc.nextInt();
                        AcctArray[accountph] = new Checking(100.00);
                      
                    }
                    else if (input == 2)
                    {
                        System.out.println("What account do you want to use: [0, 1, 2]");
                        accountph = sc.nextInt();
                        AcctArray[accountph] = new Savings(100.00);
                      
                    }
                    else
                    {
                        System.out.println("Invalid Entry.  Please try again.");
                        populateaccts();
                    }
   
       }
        else if (inputtime == 2)
       {
            readAccts();
            readIn();
            System.out.println("Press 1 to use an existing account. Press 2 to overwrite an existing account. (Uncreated accounts default to checking, press 2 to overwrite those accounts)");
           notfirsttime = sc.nextInt();
            if (notfirsttime == 1)
            {
                
            }
            else if (notfirsttime == 2)
            {
                 System.out.println("Type 1 to overwrite with a checking account.  Type 2 to overwrite with a savings account.");
                    int reader = sc.nextInt();
                    if (reader == 1)
                    {
                        System.out.println("What account do you want to overwrite: [0, 1, 2]");
                        accountph = sc.nextInt();
                        AcctArray[accountph] = new Checking(100.00);
                      
                    }
                    else if (reader == 2)
                    {
                        System.out.println("What account do you want to overwrite: [0, 1, 2]");
                        accountph = sc.nextInt();
                        AcctArray[accountph] = new Savings(100.00);
                      
                    }
                    else
                    {
                        System.out.println("Invalid Entry.  Please try again.");
                        populateaccts();
                    }
                    
            }
            else
            {
                System.out.println("Invalid Entry, try again.");
                populateaccts();
            }
       }
        else
        {
            System.out.println("Invalid Entry, try again.");
            populateaccts();
        }
    }
    
    
    
    
    public void startMenu() throws IOException, ParseException
            {
                if (inputtime == 1 || notfirsttime == 2)
                {
                    AcctArray[accountph].menu();
                }
                else if(inputtime == 2)
                {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("What account do you want to use: [0, 1, 2]");
                    int input = sc.nextInt();
                    AcctArray[input].menu();
                }
                else
                {
                    populateaccts();
                }
             }
    
   
    
    
    public void readAccts() throws IOException
    {
     BufferedReader br = new BufferedReader(new FileReader("ACCTDATA.txt"));
		
                        System.out.println("Your current list of accounts in $USD are: ");
                        
                        int number = 0;
			String CurrentLine;
                        
			
                        while ((CurrentLine = br.readLine()) != null) 
                        {
				System.out.println("$" + CurrentLine + " | Account " + number  );
                                number++;
                                if (number >= 3)
                                {
                                    break;
                                }
                                
			}
                        
                      
                         
                      
    }
                        
    
    public void readIn() throws IOException
    {
     BufferedReader br = new BufferedReader(new FileReader("ACCTDATA.txt"));
		
 
			String CurrentLine;
                        double balanceget = 0;
                        int i = 0;
                        double accountone = 0;
                        double accounttwo = 0;
                        double accountthree = 0;
 
			while ((CurrentLine = br.readLine()) != null) 
                        {
                            
				Double doubleString = Double.parseDouble(CurrentLine);
                                balanceget = doubleString;
                                        if (i == 0) 
                                         {
                                             accountone = balanceget; 
                                         }
                                        else if (i == 1)
                                        {
                                            accounttwo = balanceget;
                                        }
                                        else if (i == 2)
                                        {
                                            accountthree = balanceget;
                                        }
                                        i++;
                                        
                            if (i >= 3)
                            {
                                break;
                            }
                            
			}
                        
                        int x = 0;
                        int z = 0;
                        String CurrentLinee;
                        while ((CurrentLinee = br.readLine()) != null)
                        {
                            String placeholder = null;
                            if (x < 1)
                            {
                                while(x < 1)
                                {
                                br.readLine();
                                x++;
                                }
                            }
                            else if (x >= 1)
                            {
                                placeholder = CurrentLinee;
                                if (placeholder.equalsIgnoreCase("c") && z == 0)
                                {
                                    AcctArray[z] = new Checking(accountone);
                                }
                                else if (placeholder.equalsIgnoreCase("s") && z == 0)
                                {
                                    AcctArray[z] = new Savings(accountone);
                                }
                                else if (placeholder.equalsIgnoreCase("a") && z == 0)
                                {
                                    AcctArray[z] = new Account(accountone);
                                }
                                else if (placeholder.equalsIgnoreCase("c") && z == 1)
                                {
                                    AcctArray[z] = new Checking(accounttwo);
                                }
                                else if (placeholder.equalsIgnoreCase("s") && z == 1)
                                {
                                    AcctArray[z] = new Savings(accounttwo);
                                }
                                else if (placeholder.equalsIgnoreCase("a") && z == 1)
                                {
                                    AcctArray[z] = new Account(accounttwo);
                                }
                                else if (placeholder.equalsIgnoreCase("c") && z == 2)
                                {
                                    AcctArray[z] = new Checking(accountthree);
                                }
                                else if (placeholder.equalsIgnoreCase("s") && z == 2)
                                {
                                    AcctArray[z] = new Savings(accountthree);
                                }
                                else if (placeholder.equalsIgnoreCase("a") && z == 2)
                                {
                                    AcctArray[z] = new Account(accountthree);
                                }
                                z++;
                            }
                        }
      
            
    }
    
    /*public void readIn() throws IOException, ClassNotFoundException
    {
         
       
       
	 
	for(int i = 0; i < AcctArray.length; i++)
        {
                FileInputStream fis = new FileInputStream(i + ".out");
		ObjectInputStream ois = new ObjectInputStream(fis);
		AcctArray[i] = (Account)ois.readObject();
		fis.close();
	}
    }*/
    
    
    
 
    public void write () throws IOException
   {
       File file = new File("ACCTDATA.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) 
                        {
				file.createNewFile();
			}
  BufferedWriter outputWriter = null;
  outputWriter = new BufferedWriter(new FileWriter("ACCTDATA.txt"));
  for (int i = 0; i < AcctArray.length; i++) 
  {
      
    outputWriter.write(AcctArray[i].getBalancee());
    outputWriter.newLine();
  }
 

  outputWriter.newLine();
  outputWriter.newLine();
  
  for (int i = 0; i < AcctArray.length; i++) 
  {
      
    outputWriter.write(AcctArray[i].returnType());
    outputWriter.newLine();
  }
  outputWriter.flush();  
  outputWriter.close();  
  
  System.out.println("Done");
    }
    
   
    
    /*public void writeObject() throws IOException
    {
       for(int i = 0; i < AcctArray.length; i++)
        {
                FileOutputStream fos = new FileOutputStream(i + ".out");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(AcctArray[i].getBalance());
        	oos.flush();
        	fos.close();
        }
        System.out.println("Done");
    }*/

    

   
    
}
