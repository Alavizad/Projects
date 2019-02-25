/* Ashley Lavizadeh
 * alavizad
 * pa3
 * client program for Matrix ADT
 */
import java.io.*;
import java.util.Scanner;

class Sparse{
	public static void main(String[] args) throws IOException{
		
		Scanner in = null; 
        PrintWriter out = null;

        
		if (args.length != 2){
			System.err.println("Usage: FileIO infile outfile");
			System.exit(0);
		}

       in = new Scanner(new File(args[0]));
       out = new PrintWriter(new FileWriter(args[1]));
                                                         
       int n = Integer.parseInt(in.next()) + 1;
       int aLen = Integer.parseInt(in.next());
       int bLen = Integer.parseInt(in.next());
       Matrix A = new Matrix(n);
       Matrix B = new Matrix(n);
       int i = 0, j = 0;
       while(i< aLen){
            int row = Integer.parseInt(in.next());
            int column = Integer.parseInt(in.next());
            double data = Double.parseDouble(in.next());
            A.changeEntry(row, column, data);
            i++;

       }
       while(j < bLen){
            int row = Integer.parseInt(in.next());
            int column = Integer.parseInt(in.next());
            double data = Double.parseDouble(in.next());
            B.changeEntry(row, column, data);
            j++;

       }

        
        int size = A.getSize();

        int aNNZ = A.getNNZ();
        out.println("A has " +  aNNZ  + " non-zero entries:\r\n" + A );

        int bNNZ = B.getNNZ(); 
        out.println("B has " + bNNZ + " non-zero entries:\r\n" + B);

        Matrix scalarA = new Matrix(size);
        scalarA = A.scalarMult(1.5);
        out.println("(1.5)*A =\r\n" + scalarA);

        Matrix addAB = new Matrix(size);
        addAB = A.add(B);
        out.println("A+B =\r\n" + addAB);

        Matrix aCopy = A.copy();
        Matrix addAA = new Matrix(size);
        addAA = A.add(aCopy);
        out.println("A+A =\r\n" + addAA);

        Matrix bSubA = new Matrix(size);
        bSubA = B.sub(A);
        out.println("B-A =\r\n" + bSubA);

        Matrix aSubA = new Matrix(size);
        aSubA = A.sub(aCopy);
        out.println("A-A =\r\n" + aSubA);

        Matrix transA = new Matrix(size);
        transA = A.transpose();
        out.print("Transpose(A) = \r\n" + transA);

        Matrix aMultB = new Matrix(size);
        aMultB = A.mult(B);
        out.println("A*B =\r\n" + aMultB);

        Matrix bCopy = B.copy();
        Matrix bMultB = new Matrix(size);
        bMultB= B.mult(bCopy);
        out.println("B*B =\r\n" + bMultB);

        in.close();
        out.close();

	}
}