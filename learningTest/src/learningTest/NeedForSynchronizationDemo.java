package learningTest;


class NeedForSynchronizationDemo
{
   public static void main (String [] args)
   {
      FinTrans ft = new FinTrans ();
      FinTrans f2 = new FinTrans ();
      TransThread tt1 = new TransThread (ft, f2,"Deposit Thread");
      TransThread tt2 = new TransThread (ft, f2, "Withdrawal Thread");
      tt1.start ();
      tt2.start ();
   }
}
class FinTrans
{
   public static String transName;
   public static double amount;
}
class TransThread extends Thread
{
   private FinTrans ft;
   private FinTrans f2;
   TransThread (FinTrans ft, FinTrans f2, String name)
   {
      super (name); // Save thread's name
      this.ft = ft; // Save reference to financial transaction object
      this.f2 = f2;
   }
   public void run ()
   {
      for (int i = 0; i < 100; i++)
      {
           if (getName ().equals ("Deposit Thread"))
           {
        	   synchronized("aaa"){
        		   
        	   
               // Start of deposit thread's critical code section
               ft.transName = "Deposit";
               try
               {
                  Thread.sleep ((int) (Math.random () * 1000));
               }
               catch (InterruptedException e)
               {
               }
               ft.amount = 2000.0;
               System.out.println (ft.transName + " " + ft.amount);
               System.out.println(Thread.holdsLock("aaa") + "Deposit");
               // End of deposit thread's critical code section
           }
           }
           else
           {
               // Start of withdrawal thread's critical code section
        	   System.out.println(Thread.holdsLock("aaa") + "XXXXX");
        	   synchronized("aaa"){
               ft.transName = "Withdrawal";
               try
               {
                  Thread.sleep ((int) (Math.random () * 1000));
               }
               catch (InterruptedException e)
               {
               }
               ft.amount = 250.0;
               System.out.println (ft.transName + " " + ft.amount);
               // End of withdrawal thread's critical code section
        	   }
           }
      }
   }
}