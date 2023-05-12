public class BankersAlgorithum {
    
    int Num_proc= 5; // Number of processes
    int Num_res = 3; // Number of resources
    int need[][] = new int[Num_proc][Num_res];
    int [][]max;
    int [][]allocation_table;
    int []available;
    int safeSequence[] = new int[Num_proc];
 
    void initializeValues()
    {
    // P0, P1, P2, P3, P4 are the Process names here
    // Allocation Matrix
    allocation_table = new int[][] { { 0, 1, 0 }, //P0  
                  { 2, 0, 0 }, //P1
                  { 3, 0, 2 }, //P2
                  { 2, 1, 1 }, //P3
                  { 0, 0, 2 } }; //P4
           
    // MAX Matrix
    max = new int[][] { { 7, 5, 3 }, //P0
             { 3, 2, 2 }, //P1
             { 9, 0, 2 }, //P2
             { 2, 2, 2 }, //P3
             { 4, 3, 3 } }; //P4
     
    // Available Resources 
     available = new int[] { 3, 3, 2 };
    }
 
    void isSafe()
    {
    int count=0;
     
    //visited array to find the already allocated process
    boolean visited[] = new boolean[Num_proc];
    for (int i = 0;i < Num_proc; i++)
    {
        visited[i] = false;
    }
         
    //work array to store the copy of available resources
    int work[] = new int[Num_res];   
    for (int i = 0;i < Num_res; i++)
    {
        work[i] = available[i];
    }
 
    while (count<Num_proc)
    {
        boolean flag = false;
        for (int i = 0;i < Num_proc; i++)
        {
            if (visited[i] == false)
             {
            int j;
            for (j = 0;j < Num_proc; j++)
            {       
                if (need[i][j] > work[j])
                break;
            }
            if (j == Num_res)
            {
               safeSequence[count++]=i;
               visited[i]=true;
               flag=true;
                         
                for (j = 0;j < Num_res; j++)
                {
                work[j] = work[j]+allocation_table[i][j];
                }
            }
             }
        }
        if (flag == false)
        {
            break;
        }
    }
    if (count < Num_proc)
    {
        System.out.println("The System is UnSafe!");
    }
    else
    {
        //System.out.println("The given System is Safe");
        System.out.println("Following is the SAFE Sequence");
                for (int i = 0;i < Num_proc; i++)
        {
            System.out.print("P" + safeSequence[i]);
            if (i != Num_proc-1)
            System.out.print(" -> ");
        }
    }
    }
     
    void calculateNeed()
    {
    for (int i = 0;i < Num_proc; i++)
    {
        for (int j = 0;j < Num_res; j++)
         {
        need[i][j] = max[i][j]-allocation_table[i][j];
         }
    }       
    }
 
    public static void main(String[] args)
    { 
  
      BankersAlgorithum BA = new BankersAlgorithum();
          
      BA.initializeValues();   
      //Calculate the Need Matrix   
      BA.calculateNeed();           
             
       // Check whether system is in safe state or not
      BA.isSafe();       
    }
    
}
