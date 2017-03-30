import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class StudentDataStructure 
{  		
		private int next;
	    private int size;
	    private Student[ ] data;

	    public StudentDataStructure ()
	    {  	next = 0;
	        size = 100;
	        data = new Student[size];
	     }//end of constructor

	    public StudentDataStructure (int s)
	     {  next = 0;
	        data = new Student[s];
	        size = s;
	     }//end of constructor

	     public boolean insert(Student newNode)
	     {  if(next >= size)  // the structure is full
	     	{
	    	 	return false;
	     	}
	         data[next]= newNode.deepCopy( );  // store a deep copy of the client�s node
	         FileOutputStream fos = null;
	         ObjectOutputStream oos = null;
	         try {
	           fos = new FileOutputStream("Data.dat");
	         } catch (FileNotFoundException e) {
	             e.printStackTrace();
	         }
	         try {
	             oos = new ObjectOutputStream(fos);
	         } catch (IOException e){
	             e.printStackTrace();
	         } 
	         try{
	             oos.writeObject(data[next]);
	         } catch (IOException e){
	             e.printStackTrace();
	         }
	         try {
	             oos.close();
	         } catch (IOException e) {
	             e.printStackTrace();
	         }

	         if(data[next] == null)
	            return false;
	         next = next + 1; // prepare for the next insert
	           return true;
	     }// end of insert method

	     public Student fetch(String targetKey)
	     {  
	    	 Student node;
	    	 Student temp;
	       // access the node using a sequential search
	         int i = 0;
	         while ( i < next && !(data[i].compareTo(targetKey) == 0))
	         {  
	        	 i++;
	         }
	       if(i== next) // node not found
	           return null;
	     
	          node = data[i].deepCopy( );  //deep copy the node's information into the client's node
	          try {
		             FileInputStream fis = null;
		             ObjectInputStream ois = null;
		        	 fis = new FileInputStream("Data.dat");
		             ois = new ObjectInputStream(fis);
		             node =(Student) ois.readObject();
		             ois.close();
		              
		         } catch (FileNotFoundException e) {
		             e.printStackTrace();
		         } catch (ClassNotFoundException e) {
		             e.printStackTrace();
		         } catch (IOException e) {
		             e.printStackTrace();
		         }
	     
	          if(i != 0) // bubble-up accessed node
	         {    
	        	  // move the node up one position in the array, unless it is the first node
	        	  temp = data[i-1];
	              data[i-1] = data[i];
	              data[i] = temp;
	         }
	         return node;
				

	     } // end of fetch method

	     public boolean delete(String targetKey)
	     {
	    	 // access the node using a sequential search
	         int i = 0;
	         
	         while (i < next && !(data[i].compareTo(targetKey) == 0))
	         {  
	        	 i++;
	         }
	         if(i == next) // node not found
	             return false;
	         //move the last node into the deleted node's position
	          data[ i] = data[ next -1];
	          data[next-1] = null;
	          FileOutputStream fos = null;
		         ObjectOutputStream oos = null;
		         try {
		           fos = new FileOutputStream("Data.dat");
		         } catch (FileNotFoundException e) {
		             e.printStackTrace();
		         }
		         try {
		             oos = new ObjectOutputStream(fos);
		         } catch (IOException e){
		             e.printStackTrace();
		         } 
		         try{
		             oos.writeObject(data[next-1]);
		         } catch (IOException e){
		             e.printStackTrace();
		         }
		         try {
		             oos.close();
		         } catch (IOException e) {
		             e.printStackTrace();
		         }
	          next = next - 1;
	          return true; // node found and deleted

	          
	     }//end of the delete method

	      public boolean update(String targetKey, Student newNode)
	     { 
	    	  if(delete(targetKey) == false)  // node not in the structure
	             return false;
	         else if( insert(newNode ) == false)  // insufficient memory
	             return false;
	         else
	             return true;  // node found and updated
	      }// end of update method
	      
	      public boolean expand()
	      {
	    	  Student [] temp = new Student[size *2];
	    	  
	    	  if (temp == null)
	    		  return false;
	    	  else
	    	  {
	    		  System.arraycopy(data, 0, temp, 0,size);
	    		  size = size*2;
	    		  data = temp;
	        	  return true; 
	    	  }

	      }
	      
	      
	      public void showAll( )
	      {  
	    	  for(int i = 0; i< next; i++)
	              System.out.println(data[i].toString( ));
	      }// end showAll method
	   }//end of class UOAUtilities

