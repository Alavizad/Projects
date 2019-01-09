
/****************************************************************************************
*  ListClient.c
*  Test client for List ADT
*****************************************************************************************/

#include<stdio.h>
#include<stdlib.h>
#include"List.h"

int main(int argc, char* argv[]){
   
   List A = newList();
   List B = newList();
   List C = NULL;




  
        append(A, 1);
        append(A, 2);
        append(A, 3);
        append(A, 5);

        prepend(A, 6);
        prepend(A, 4);
        prepend(A, 2);
        prepend(A, 1);

        append(A, 1);
        append(A, 2);
        append(A, 3);
        append(A, 5);
        moveFront(A);
        printf("index = %d\n" + index(A));
        insertAfter(A, 12);

        prepend(A, 76);
        prepend(A, 4);
        prepend(A, 3);
        prepend(A, 1);
        moveFront(A);
        printf("index = %d\n" + index(A));
        insertBefore(A, 115);


        prepend(A, 76);
        prepend(A, 4);
        deleteFront(A);
        prepend(A, 3);
        prepend(A, 1);
        moveFront(A);
         printf("index = %d\n" + index(A));
                printf("length  = %d\n", length(A));
        insertBefore(A, 115);
                printf("length  = %d\n", length(A));
        deleteFront(A);
                printf("length  = %d\n", length(A));
  
        append(A, 1);
        printf("length  = %d\n", length(A));
        deleteBack(A);
        printf("length 22 = %d\n", length(A));
        append(A, 2);
        printf("length 22 = %d\n", length(A));
        append(A, 3);
        printf("length 22 = %d\n", length(A));
        append(A, 5);
        printf("length 22 = %d\n", length(A));
        moveFront(A);
        printf("length 22 = %d\n", length(A));
        insertAfter(A, 12);
        printf("length 22 = %d\n", length(A));
        deleteBack(A);
        printf("length 22 = %d\n", length(A));

   freeList(&A);
   freeList(&B);
   freeList(&C);

   return(0);
}

/*
Output of this program:
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
false
false
true
1 2 3 4 5 -1 6 7 8 9 11 12 13 14 15 -2 16 17 18 19 20
21
0
*/