package edu.umsl;

/**
 *
 * @author arpdz2
 */
import java.io.*;
import java.util.*;
import java.text.*;
//import java.math.*;


//Programmer: Austin Paul (WROTE IN 3806)

//This program calculates the interest for an individual account for the current
//year. It allows for depsits and withdrawals to be made during that year.

public class Account implements Serializable
{
	protected double balance;
	protected int firstdate;
	protected int seconddate;
	private Calendar cal1 = new GregorianCalendar();
	private Calendar cal2 = new GregorianCalendar();
	private boolean dateflag = false;
	protected double rate;

	// Initial Constructor
	// Once called the Account1 constructor presents a menu for users to make
	// a deposit, withdrawal, check balance or exit.
	// The menu is a loop that based on the selection will call either the
	// deposit method, withdrawal method or exit the program.
      
        public Account (double begin_balance) 
        {
		balance = (begin_balance);
		
	} // End Initial Constructor

        public void menu() throws IOException, ParseException
        {
            Scanner sc = new Scanner(System.in);
            int chooser = 0;
            
            ATM atm = new ATM();
            if(atm.inputtime == 1)
            {
            getDate1();
            returnBalance();
            }
            else if(atm.inputtime == 2)
            {
             readInDate();
             returnBalance(); 
            String dateinput = String.valueOf(firstdate);
            DateFormat fmt1 = new SimpleDateFormat("DDD");  
            Date newdate = fmt1.parse(dateinput);  
            DateFormat fmt2 = new SimpleDateFormat("MM/dd");  
            String output = fmt2.format(newdate);  
            System.out.println("The last time you used this atm was on " + output + " (Date Reset).");
                }
            else
            {
               System.out.println("Error, Restart Program.");
            }
            
            
        while(chooser != 4)
                {  
                    System.out.println("Please choose: ");
                    System.out.println("1) Deposit");
                    System.out.println("2) Withdraw");
                    System.out.println("3) Check Balance");
                    System.out.println("4) Exit");
                    chooser = sc.nextInt();
                    
                    if(chooser == 1)
                    {
                        if(dateflag == true)
                        {
                            seconddate = firstdate;
                        }
                        else
                        {
                        getDate2();
                        }
                        dateflag = false;
                        getInterest();
                        deposit();
                      
                        returnBalance();
                    }
                    else if(chooser == 2)
                    {
                        if(dateflag == true)
                        {
                            seconddate = firstdate;
                        }
                        else
                        {
                        getDate2();
                        }
                        dateflag = false;
                        getInterest();
                        withdraw();
                        
                        returnBalance();
                    }
                    else if(chooser == 3)
                    {
                        if(dateflag == true)
                        {
                            seconddate = firstdate;
                        }
                        else
                        {
                        getDate2();
                        }
                        dateflag = false;
                        getInterest();
                     
                        returnBalance();
                    }
                    else if(chooser == 4)
                    {
                       //writeDate();
                        break;
                        
                    }
                    else
                    {
                        System.out.println("Invalid Entry, try again.");
                        continue;
                    }
                }
            
        }

        
	
	// This method retrieves the balance field and returns it in currency format
	/*public String getBalance()
        {
		NumberFormat currencyFormatter;
                String currencyOut;
                
                currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
                currencyOut = currencyFormatter.format(balance);
                return currencyOut;
             
	}*/
        
        public String getBalancee()
        {
            
            String convertedString = String.format("%.2f", balance);
            return convertedString;
            
            
        }
        
        //This method returns the balance in US dollars
        public void returnBalance() throws IOException
        {
            NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
            
            System.out.println("Your current balance is " + fmt.format(balance) + ".");
        }
        
            public String returnType()
            {
                return "a";
            }

	// This method prompts the user for the deposit and then adds it to the
	// balance field.

	public void deposit() throws IOException 
        {
                Scanner sc = new Scanner(System.in);
                //String entered_amount;
                double deposit;
                
                System.out.print("How much would you like to deposit: ");
                deposit = sc.nextDouble();
                
                balance = balance + deposit;

	}

	// This method prompts the user for the withdraw amount and then subtracts
	// it from the balance field.

	public void withdraw() throws IOException
        {
		Scanner sc = new Scanner(System.in);
                double withdraw;
                
                System.out.print("How much would you like to withdraw: ");
                withdraw = sc.nextDouble();
                
                if(withdraw > balance)
                {
                    System.out.println("Sorry, Insufficient Funds. Please try again.");
                    withdraw();
                }
                else
                {
                    balance = balance - withdraw;
                }
                
            
	}


	// This function is only called on the first transaction after the
	// account has been initialized to set the first time a transaction
	// occurs for the account for the current year.

	public void getDate1() throws IOException
        {

		System.out.print("Enter todays date(mm/dd/yyyy): ");
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(System.in));
		String inputText = br.readLine();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		ParsePosition pos = new ParsePosition(0);
                //Date date= new Date();
		Date date = formatter.parse(inputText, pos);

		cal1.setTime(date);

		firstdate = cal1.get(GregorianCalendar.DAY_OF_YEAR);
		dateflag = true;

        }

	// This method is called for every date entered after the first date.
	// The previous second date is passed to the first date to keep track of
	// time.

	 public void getDate2() throws IOException 
         {
            System.out.print("Enter todays date(mm/dd/yyyy): ");
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(System.in));
            String inputText = br.readLine();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            ParsePosition pos = new ParsePosition(0);
            Date date = formatter.parse(inputText, pos);

          
            cal2.setTime(date);
            seconddate = cal2.get(GregorianCalendar.DAY_OF_YEAR);
            
            if(seconddate >= firstdate)
            {
                
            }
            else
            {
                System.out.println("Please enter a future date.  Try again. ");
                getDate2();
            }
            

	 }

	// This method calulates the interest based on the previous date and the
	// current date

	public void getInterest()
        {
		int datediff = seconddate - firstdate;
                rate = .05/365;
                double ratetime = Math.pow(1 + rate, datediff);
                balance = balance * ratetime;
                firstdate = seconddate;

                
	}
        
 public void writeDate () throws IOException
   {
       File file = new File("DATE.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) 
                        {
				file.createNewFile();
			}
  BufferedWriter outputWriter = null;
  outputWriter = new BufferedWriter(new FileWriter("DATE.txt"));
  
  String StringDate = String.valueOf(firstdate);
  
  outputWriter.write(StringDate);
  outputWriter.newLine();
  
  outputWriter.flush();  
  outputWriter.close();  
  
  System.out.println("Done");
    }
 
 public void readInDate() throws IOException
    {
     BufferedReader br = new BufferedReader(new FileReader("DATE.txt"));
		
 
            String DateLine;

            while ((DateLine = br.readLine()) != null) 
            {

                    int intString = Integer.parseInt(DateLine);
                    firstdate = intString;
            }
      
            
    }
}
