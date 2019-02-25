class MatrixTest{
   public static void main(String[] args){
   Matrix M = new Matrix(3);
     
      M.changeEntry(1,1, 1.0);
      M.changeEntry(1,2, 2.0);
      M.changeEntry(1,3, 3.0);
      M.changeEntry(2,1, 0.0);
      M.changeEntry(2,2, 0.0);
      M.changeEntry(2,3, 6.0);;
      M.changeEntry(3,1, 7.0);
      M.changeEntry(3,2, 8.0);
      M.changeEntry(3,3, 0.0);
      System.out.println(M);

      M.changeEntry(1,1, 9.0);
      M.changeEntry(1,2, 8.0);
      M.changeEntry(1,3, 7.0);
      M.changeEntry(2,1, 6.0);
      M.changeEntry(2,2, 5.0);
      M.changeEntry(2,3, 4.0);;
      M.changeEntry(3,1, 3.0);
      M.changeEntry(3,2, 2.0);
      M.changeEntry(3,3, 1.0);
      
      
       
      Matrix r = new Matrix(M.getSize());
      r.changeEntry(1,1, 0.0);
      r.changeEntry(1,2, 2.0);
      r.changeEntry(1,3, 0.0);
      r.changeEntry(2,1, 2.0);
      r.changeEntry(2,2, 2.0);
      r.changeEntry(2,3, 0.0);;
      r.changeEntry(3,1, 2.0);
      r.changeEntry(3,2, 2.0);
      r.changeEntry(3,3, 2.0);

      System.out.println("M:\n" + M);
      Matrix Q =M.sub(r);
      System.out.println("Q = \n" + Q);


      Matrix J = new Matrix(M.getSize());
      J = M.mult(Q);
       
      System.out.print("Q * M = \n" + J);


      Matrix B = new Matrix(3);
      B.changeEntry(1,1, 0.23);
      B.changeEntry(1,2, 44.0);
      B.changeEntry(1,3, 99.0);
      B.changeEntry(2,1, 75.0);
      B.changeEntry(2,2, 23.0);
      B.changeEntry(2,3, 0.0);;
      B.changeEntry(3,1, 211.0);
      B.changeEntry(3,2, 200.0);
      B.changeEntry(3,3, 2.0);

      System.out.println(B.add(Q));


      Matrix W = B.copy();

      System.out.println(W);

      System.out.println(B.equals(W));

      System.out.print(B.scalarMult(2));

   }



}