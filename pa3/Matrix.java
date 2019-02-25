/*
* Ashley Lavizadeh
* alavizad
* Matrix ADT
* pa3
*/

public class Matrix{

    public class Entry{
    	int column;
    	double data;

        Entry(double data, int column){
        	this.column = column;
        	this.data = data;
        }

        public boolean equals(Object E){
        	Entry entry = (Entry)E;
        	return(entry.column == column && entry.data == data);
        }

        public String toString(){
            String str = "(" + column + "," + data + ")";
            return str;
        }

    }
   

   //matrix fields
   List[] row;
   int NNZ;
   
   Matrix(int n){ // Makes a new n x n zero Matrix. pre: n>=1 
        if(n<1){
        	throw new RuntimeException(
        	  "Matrix Error: Matrix size cannot be less than 1"
        	);
        }    
        row = new List[n+1];
        NNZ = 0;
        for(int i = 0; i < n+1; i++){
        	row[i] = new List();

        }
       
   }       
   

 // Access functions----------------------------------------------------
   int getSize(){       
   	    return row.length-1;
   }       
   
   // Returns the number of non-zero entries in this Matrix      
   int getNNZ(){
   	    int NNZ = 0;
   	    for(int i = 1; i < row.length ; i++){
   	        NNZ = NNZ + row[i].length();
   	    }

   	    return NNZ;
   } 
   
   // overrides Object's equals() method
   public boolean equals(Object x){
      if(!(x instanceof Matrix)){
         return false;
      }
      boolean equals = false;
      Matrix xMatrix = (Matrix) x;       
      if(getSize() != xMatrix.getSize())
            return false;
      if( this.getNNZ() != xMatrix.getNNZ() ) return equals;
         for(int i = 1; i <= getSize() - 1; i++) {
            equals = (row[i].equals(xMatrix.row[i]));
        
         }
      
      return equals; 	   
   } 
// Manipulation procedures-----------------------------------------------------------------------------------------------------------
   // sets this Matrix to the zero state
   void makeZero(){
   	     for(int i = 0; i<row.length; i++){
   	     	row[i].clear();
   	     }
   } 
  

   // returns a new Matrix having the same entries as this Matrix
   Matrix copy(){
   	     Matrix newMatrix = new Matrix(getSize());
   	     for(int i = 0; i<row.length; i++){
   	     	row[i].moveFront();
   	     	for(int j = 0; j < row[i].length(); j++){
   	     		Entry newEntry = (Entry)row[i].get();
   	     		newMatrix.changeEntry(i, newEntry.column, newEntry.data);
   	     		row[i].moveNext();
   	     	}
   	     }
   	     return newMatrix; 

   }
  
   // changes ith row, jth column of this Matrix to x
   // pre: 1<=i<=getSize(), 1<=j<=getSize()
   void changeEntry(int i, int j, double x){
   	     if(i < 1 || i > getSize() || j < 1 || j > getSize()){
   	     	throw new RuntimeException(
   	     		"changeEntry() Eror: 1<=i<=getSize(), 1<=j<=getSize()"
   	     	);
         } 
         //find if element exists
         row[i].moveFront();
         boolean here = false;
         Entry thisEntry;
         while(row[i].index() != -1){
         	thisEntry = (Entry)row[i].get();
         	if(j == thisEntry.column){
         		here = true;
         		
         	}else{
         		here = false;
         	}
            if(x == 0 && here){   
            	row[i].delete();
              NNZ--;
            	return;
            }if(x >0 && here){
                thisEntry.data = x;
            	return; 
            }
            row[i].moveNext();   
         }
         //if x isn't 0 value and if the element does not exist
         if( x != 0 && !here){
         	Entry newEntry = new Entry(x,j);                             
            if(row[i].length() == 0){                                    //if if the list is empty, prepend and exit
                row[i].prepend(newEntry);
                NNZ++;
                return;
            }

            if(j == 1){                                                  //if j is at the front, prepend
         	   row[i].prepend(newEntry);
             NNZ++;
         	   return; 
         	}

         	 if(j == row[i].length()+1){                                    //if j is at the back, append
         	   row[i].append(newEntry);
             NNZ++;
         	   return; 
         	}

                                        
            row[i].moveFront();                                           //else, iterate to the given index                                        
         	int k = 1;
         	while(k<j){
         		row[i].moveNext();
         		k++;
         	}
         	if(row[i].index() == -1){
         		if(x == 420.0 && j == 2){System.out.println("true");}
                row[i].append(newEntry);     
                NNZ++;  
                return;
         	}else{
            		
         		row[i].insertBefore(newEntry);

         		return;
         	}

                    
        
         }

   }




   //private helper to return the entry at this spot 
   private double getElement(int i, int j){
   	     if(i < 1 || i > getSize() || j < 1 || j > getSize()){
   	     	throw new RuntimeException(
   	     		"getElement() Eror: <=i<=getSize(), 1<=j<=getSize()"
   	     	);
        }
        row[i].moveFront();
        int k = 0;
        while(k<j){
        	row[i].moveNext();
        	k++;
        }
        Entry entry = (Entry) row[i].get();
   	    return entry.data;

   }


 //helper function that returns the dot product of two matrix rows 
 private double dot(List List1, List List2){
      
  	  double dotProd = 0;
  	  List1.moveFront(); List2.moveFront();
  	  while(List1.index() > -1 && List2.index() > -1){
  	  	    Entry l1Entry = (Entry)List1.get();  
  	  	    Entry l2Entry = (Entry)List2.get();     
  	  	    if(l1Entry.column < l2Entry.column){
  	  	    	List1.moveNext();
  	  	    }else if(l1Entry.column > l2Entry.column){
  	  	    	List2.moveNext();
  	  	    }else{
              
  	  	      dotProd += l1Entry.data*l2Entry.data;
  	  	      List1.moveNext(); List2.moveNext();
             
  	  	    }
  	  }
  	 
      return dotProd;
      
  }


  // returns a new Matrix that is the scalar product of this Matrix with x    
  //shave down and use copy()? 
   Matrix scalarMult(double x){
   	  Matrix newMatrix = new Matrix(getSize());
   	  for(int i=0;i<row.length; i++){
   	  	row[i].moveFront();
   	  	for(int j = 0; j<row[i].length(); j++){
   	  		Entry thisEntry = (Entry)row[i].get();
   	  		double newData = thisEntry.data * x;
   	  		newMatrix.changeEntry(i,thisEntry.column, newData);
   	  		row[i].moveNext();
   	  	}
   	  }
      newMatrix.NNZ = NNZ;
      return newMatrix;
   }
 
   

   // returns a new Matrix that is the sum of this Matrix with M                          //add condition for when an entry is zero 
   // pre: getSize()==M.getSize()
   Matrix add(Matrix M){
   	  if(getSize() != M.getSize()){
   	   	throw new RuntimeException("Matrix add() error: Matrices must be the same size");
   	  }
      Matrix newMatrix = this.copy();
      if(this == M){
         newMatrix.scalarMult(2);
      }else{
         for(int i = 1; i < row.length; i++){
             newMatrix.row[i] = addSubList(row[i], M.row[i], true);
         }
      }

    newMatrix.NNZ = NNZ; 
    
    return newMatrix;
   }


  // returns a new Matrix that is the difference of this Matrix with M 
  // pre: getSize()==M.getSize()

   Matrix sub(Matrix M){
      if(getSize() != M.getSize()){
        throw new RuntimeException("Matrix sub() error: Matrices must be the same size");
      }
      Matrix newMatrix = this.copy();
      if(this == M){
         newMatrix.scalarMult(0);
      }else{
         for(int i = 1; i < row.length; i++){
             newMatrix.row[i] = addSubList(row[i], M.row[i], false);
         }
      } 
    
    return newMatrix;
   }



  
  //private helper function for add() and sub()
  private List addSubList(List L1, List L2, boolean add){
    List newList = new List();
    L1.moveFront();
    L2.moveFront();
    while(L1.index() != -1 || L2.index() != -1){
        if(L1.index() != -1 && L2.index() != -1){
           
           Entry L1Entry = (Entry)L1.get();
           Entry L2Entry = (Entry)L2.get();
          
           if(L1Entry.column == L2Entry.column){                                                            //if both columns are non-zero, add or subtract together and append
              
              if(add == true){
                 
                 Entry newEntry = new Entry(L1Entry.data+L2Entry.data, L1Entry.column );
                 if(newEntry.data == 0.0){L2.moveNext(); L1.moveNext(); continue;}
                 newList.append(newEntry);
                 L1.moveNext();
                 L2.moveNext();

              }else{
                 
                 double newData = L1Entry.data-L2Entry.data;
                 if(newData == 0.0){L2.moveNext(); L1.moveNext(); continue;}
                 Entry newEntry = new Entry(newData, L1Entry.column);
                 newList.append(newEntry);
                 L1.moveNext();
                 L2.moveNext();

              }

           }else if(L1Entry.column > L2Entry.column){                                                      //if L1 is zero at this index
               if(add == true){
                 
                 Entry newEntry = new Entry(L2Entry.data, L1Entry.column);
                 if(newEntry.data == 0.0){L2.moveNext(); continue;}
                 newList.append(newEntry);
                 L2.moveNext();

              }else{
        

                 Entry newEntry = new Entry( 0.0 - L2Entry.data, L2Entry.column);
                 if(newEntry.data == 0.0){L2.moveNext(); continue;}
                 newList.append(newEntry);
                 L2.moveNext();

              }     
          
           }else if(L1Entry.column < L2Entry.column){                                                       //if L2 is zero at this index
               
               Entry newEntry = new Entry(L1Entry.data, L1Entry.column);
               if(newEntry.data == 0.0){L1.moveNext(); continue;}
               newList.append(newEntry);
               L1.moveNext();

           }

        }else if(L1.index() != -1){

            Entry newEntry = (Entry) L1.get();
            newList.append(newEntry);
            L1.moveNext();

        }else{
            Entry newEntry = (Entry) L2.get();
            if(add == true){
              newList.append(newEntry);
              L2.moveNext();
            }else{
              newList.append(new Entry(0.0-newEntry.data, newEntry.column));
              L2.moveNext();
            }
        }
       
       
    }
    
    return newList;


  }



 
// returns a new Matrix that is the transpose of this Matrix
 Matrix transpose(){
   	Matrix M = new Matrix(getSize());
      for(int i = 1; i <= getSize(); ++i) {
         row[i].moveFront();
         while(row[i].index() >= 0) {
            M.changeEntry(((Entry)row[i].get()).column, i, ((Entry)row[i].get()).data);
            row[i].moveNext();
         }
      }
      return M;
   }
  

 // returns a new Matrix that is the product of this Matrix with M
 // pre: getSize()==M.getSize() 
Matrix mult(Matrix M){
    if(getSize() != M.getSize()){
   	   	throw new RuntimeException("Matrix mult() error: Matrices must be the same size");
   	}	
   	Matrix newMatrix = new Matrix(getSize());
   	Matrix mTransposed = M.transpose();
   	for(int i = 0; i <= getSize(); i++){
   		if(row[i].isEmpty())
   			continue;
   		for(int k = 0; k<=getSize(); k++){
   			if(mTransposed.row[k].isEmpty())
   				continue;
   			double dotProd = dot(mTransposed.row[k], row[i]);
   			newMatrix.changeEntry(i,k,dotProd);

   		}
   	}
    
   	return newMatrix;
   
   }
  
 
// Other functions-------------------------------------------------------------
 public String toString(){
    
	String str = "";
	for(int i = 1; i <= this.getSize(); i++){
		if(row[i].length() != 0){
		    str = str + i + ": " + this.row[i] + "\r\n";
		    
	    }
     }
    
	return str; 
    
 } // overrides Object's toString() method

}