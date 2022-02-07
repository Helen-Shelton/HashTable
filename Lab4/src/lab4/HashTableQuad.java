package lab4;

public class HashTableQuad {
    private Integer[] Table;
    private int sizeT;
    private int sizeKey;
    private double maxLoad;
    
    public HashTableQuad(int maxNum, double load){
        int length = (int)Math.ceil(maxNum/load);//need Math.ceil() in case maxNum/load turns out to be a fraction
        length = findPrime(length);//find prime number closest to the current length
        Table = new Integer[length];
        for (int i = 0; i<length; i++)
            Table[i] = -1;//set as empty
        sizeT = length;
        sizeKey = 0;
        maxLoad = load;
    }
    
    private int findPrime(int p)//finds smallest prime number either equal or greater than argument
    {
        boolean flag = false;
        if(p==2)
            return p;
        if(p%2==0)//turns the number into an odd number (all primes after 2 are odd)
            p++;
        while(flag==false)//will break when prime is found
        {
            for(int i = 2; i <= p/2; i++)//must check at most half of all values before p, to see if it is prime
            {
                if(p % i == 0)//if i is found to be a multiple of p
                {
                    p+=2;//increment to the next odd number
                    break;//break for loop to check if new p value is a prime;
                }
                if(i==(p/2))//if it is not a multiple (checked by previous condition) and its the last index in the set
                {
                    flag = true;//it is a prime number
                    break;//exit to while statement
                }
            }
        }
        return p;
    }
    
    public void insert(int n)//linear probing
    {
        if(n<0)
            throw new IllegalArgumentException("Not a valid key./n");//n must be a positive integer
        int iFirst = n%sizeT;
        int index = n%sizeT;//calculates starting index key (n) will have in table (hash function)
        int p = 1;//number of probes, first index we look at is first probe
        boolean empty = false;//assume we have not found an empty space
        if (isIn(n) == false)//if n is not already in the table
        {
            while((p <= sizeT) & (empty == false))//while we have not fully probed the table and we have not found an empty position
            {
                if (Table[index] == -1)//if there is an empty space
                {
                    Table[index] = n;//assign n to that index
                    sizeKey++;//increase the number of keys
                    if((((double)sizeKey)/sizeT) <= maxLoad)//if we have not reached the maximum number of keys allowed in the table
                        empty = true;//flag true for position found
                    else//if we have reached the maxLoad
                    {
                        rehash();//must rehash to adhere to maxLoad ratio
                        empty = true;//flag true to exit while loop after rehashing
                    }
                }
                else //else if there is no empty space
                {
                    index = (iFirst + (p*p))%sizeT;//recalculate index using hash function and quadratic probing to loop to beginning of table
                    p++;
                }
            }//end while
            if ((p>sizeT) && (empty==false))//if we have exceeded the amount of probes in the table but we have not found an empty space
            {
                rehash();//rehash the table
                insert(n);//inserts n based on the new hash table
            }
        }//end if
    }
    
    private void rehash()
    {
        int maxKeys = (int)Math.ceil(2*sizeT*maxLoad);//the number of max keys needed using 2* the size of the original table (as specified in lab requirements)
        HashTableQuad Table2 = new HashTableQuad(maxKeys,maxLoad);//make new HashTable object that is at least twice the size of original table
        for(int i = 0; i<sizeT;i++)//for every element in the original hash table
        {
            if(Table[i] != -1)//if a nonempty space is found
                Table2.insert(Table[i]);//insert it into the new hash table. Insert function calclates new Hash function
        }
        Table = Table2.getTable();
        sizeT = Table2.getSizeT();
    }
    
    public boolean isIn(int n)
    {
        int iFirst = n%sizeT, i= n%sizeT, p = 1;//first index to search at, first probe 
        while ((Table[i] != -1) && (p<sizeT))//while we have not probbed through the whole table or come across an empty index
        {
            if (Table[i] == n)//if we find n
                return true;//return true
            else //if we have not fully probbed the table and found n
            {
                i = (iFirst + (p*p)) % sizeT;//move to next index in table
                p++;//increment number of probes
            }
        }
        return false;//when we have probbed the whole table
    }
    
    public void printKeys()
    {
        System.out.print("[ ");
        for (int i = 0; i<sizeT; i++)
        {
            if (Table[i] != -1)
                System.out.print(Table[i]+" ");
        }
        System.out.print("]\n");
    }
    
    public void printKeysAndIndexes()
    {
        System.out.print("[ ");
        for (int i = 0; i<sizeT; i++)
        {
            if (Table[i] != -1)
                System.out.print("("+Table[i]+", "+i+") ");
        }
        System.out.print("]\n");
    }
    
    public Integer[] getTable()
    {
        return Table;
    }
    
    public int getSizeT()
    {
        return sizeT;
    }
    
    public int getSizeKey()
    {
        return sizeKey;
    }
    
    public double getMaxLoad()
    {
        return maxLoad;
    }
    
    
    
    public int insert2(int n)//Quadratic probing
    {
        int iFirst = n%sizeT;
        int index = n%sizeT;//calculates starting index key (n) will have in table (hash function)
        int p = 1;//number of probes, first index we look at is first probe
        boolean empty = false;//assume we have not found an empty space
        if (isIn(n) == false)//if n is not already in the table
        {
            while((p <= sizeT) & (empty == false))//while we have not fully probed the table and we have not found an empty position
            {
                if (Table[index] == -1)//if there is an empty space
                {
                    Table[index] = n;//assign n to that index
                    sizeKey++;//increase the number of keys
                    empty = true;//flag true for position found
                }
                else //else if there is no empty space
                {
                    index = (iFirst + (p*p))%sizeT;//recalculate index using hash function and quadratic probing to loop to beginning of table
                    p++;
                }
            }//end while
        }//end if
        return p;
    }
    
}//End HashTableQuad()
