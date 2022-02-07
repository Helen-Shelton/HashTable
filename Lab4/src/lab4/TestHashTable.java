package lab4;

public class TestHashTable {
    public static void main(String[] args) {
        
        //HashTableLin()
        
        HashTableLin A1 = new HashTableLin(5,0.4);
        System.out.println("size of table is: " +A1.getSizeT());
        System.out.println("number of keys are: "+A1.getSizeKey());
        
        System.out.println();
        A1.insert(2);//max num of keys is 5
        A1.insert(3);
        A1.insert(4);
        A1.insert(7);
        System.out.println("number of keys after insertion: "+A1.getSizeKey());
        System.out.println("after insertion, the table is: ");
        A1.printKeysAndIndexes();
        
        System.out.println();
        A1.insert(2);//repeated key, should not enter in the table
        System.out.println("size of table after repeated key (should not rehash): " +A1.getSizeT());//should be 4 keys only
        System.out.println("number of keys after repeated key (should not increase): "+A1.getSizeKey());
        A1.printKeys();//shows what keys are
        
        System.out.println();
        A1.insert(15);//collision with position 2, should insert at position 5
        System.out.println("size of table after collision (should stay the same): " +A1.getSizeT());
        System.out.println("number of keys after collision(should increase by 1): "+A1.getSizeKey());
        A1.printKeysAndIndexes();
        
        System.out.println();
        System.out.println(A1.isIn(1));//should print false
        System.out.println(A1.isIn(15));//should print true
        
        System.out.println();
        A1.insert(6);//goes over max Load, should rehash
        System.out.println("size of table after rehashing: " +A1.getSizeT());//should be a prime number greater than 2*sizeT (in this case >26)
        A1.printKeysAndIndexes();
        
        //HashTableQuad()
        
        HashTableQuad A2 = new HashTableQuad(5,0.4);
        System.out.println("size of table is: " +A2.getSizeT());
        System.out.println("number of keys are: "+A2.getSizeKey());
        
        System.out.println();
        A2.insert(2);//max num of keys is 5
        A2.insert(3);
        A2.insert(6);
        A2.insert(8);
        System.out.println("number of keys after insertion: "+A2.getSizeKey());
        System.out.println("after insertion, the table is: ");
        A2.printKeysAndIndexes();
        
        System.out.println();
        A2.insert(6);//repeated key, should not enter in the table
        System.out.println("size of table after repeated key (should not rehash): " +A2.getSizeT());//should be 4 keys only
        System.out.println("number of keys after repeated key (should not increase): "+A2.getSizeKey());
        A2.printKeys();//shows what keys are
        
        System.out.println();
        A2.insert(15);//collision with position 2, should insert at position 11
        System.out.println("size of table after collision (should stay the same): " +A2.getSizeT());
        System.out.println("number of keys after collision(should increase by 1): "+A2.getSizeKey());
        A2.printKeysAndIndexes();
        
        System.out.println();
        System.out.println(A2.isIn(13));//should print false
        System.out.println(A2.isIn(15));//should print true
        
        System.out.println();
        A2.insert(20);//goes over max Load, should rehash
        System.out.println("size of table after rehashing: " +A2.getSizeT());//should be a prime number greater than 2*sizeT (in this case >26)
        A2.printKeysAndIndexes();

        double Lambda = 0.1;
        while (Lambda<=0.9 )
        {
            double TotalS = 0;//total average after 100 iterations
            for(int j = 0; j<100; j++)//100 times for every lambda
            {
                HashTableLin LinTable = new HashTableLin(100000,Lambda);
                int TotalP = 0;//summation of number of probes needed after 100,000 insertions
                for(int i=0; i<100000; i++)//100000 different keys
                {
                    int x = (int)(Math.random() * Math.pow(2,30));//make a randomly generated number any possible number that is a valid integer
                    TotalP += LinTable.insert1(x);//insert it into hash function, accumulate the number  of probes
                }//end inner for loop
                //System.out.println(TotalP);
                TotalS += ((double)TotalP)/100000;//calculate average (number of probes divided by size of table)
            }//end middle for loop
            TotalS = TotalS/100;//compute the average of the average number of successful probes
            double Stheo = 0.5*(1.0+(1.0/(1.0-Lambda)));
            System.out.println("The experimental average with Lambda = "+Lambda+" is: "+TotalS);
            System.out.println("The theoretical average with Lambda = "+Lambda+" is: "+Stheo);
            Lambda+=0.1;//increment lambda/load factor
        }//end outer for loop
        
        double Lambda2 = 0.1;
        while (Lambda2<=0.9 )
        {
            double TotalS = 0;//total average after 100 iterations
            for(int j = 0; j<100; j++)//100 times for every lambda
            {
                HashTableQuad QuadTable = new HashTableQuad(100000,Lambda2);
                int TotalP = 0;//summation of number of probes needed after 100,000 insertions
                for(int i=0; i<100000; i++)//100000 different keys
                {
                    int x = (int)(Math.random() * Math.pow(2,30));//make a randomly generated number any possible number that is a valid integer
                    TotalP += QuadTable.insert2(x);//insert it into hash function, accumulate the number  of probes
                }//end inner for loop
                TotalS += ((double)TotalP)/100000;//calculate average (number of probes divided by size of table)
            }//end middle for loop
            TotalS = TotalS/100;//compute the average of the average number of successful probes
            System.out.println("The experimental average with Lambda = "+Lambda2+" is: "+TotalS);
            Lambda2+=0.1;//increment lambda/load factor
        }//end outer for loop


    }//END MAIN
}//End TestHashTable()
