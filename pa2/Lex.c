/*------------------------------------------------------------------------
 * Ashley Lavizadeh
 * Alavizad
 * pa2
 * Lex.c
 * file that uses LIst ADT to aplphabetize arrays
 */



#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "List.h"


#define MAX_LEN 160

int main(int argc, char * argv[]){

   int count=0;
   FILE *in, *out;
   char line[MAX_LEN];
   


   if( argc != 3 ){
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(1);
   }

   // open files for reading and writing 
   in = fopen(argv[1], "r");
   out = fopen(argv[2], "w");
   if( in==NULL ){
      printf("Unable to open file %s for reading\n", argv[1]);
      exit(1);
   }
   if( out==NULL ){
      printf("Unable to open file %s for writing\n", argv[2]);
      exit(1);
   }

   /* read each line of input file, then count and print tokens */
   
   while( fgets(line, MAX_LEN, in) != NULL)  {
      count++;
   }
   
   fclose(in);
   in = fopen(argv[1], "r");
   
   char** charArray = calloc(count, sizeof(char**));
   int i =0;
   while(i<count){
       fgets(line, MAX_LEN, in);
       char *copy = malloc(strlen(line)+1);
       strcpy(copy,line);
       charArray[i] = copy;
        i++;
   }                                       
  
  
  List list = newList();
  int j = 0;
  while(j < count){
        if(length(list) ==0){
                append(list, j);
       }else if(strcmp(charArray[j], charArray[front(list)]) <= 0 ){
  		prepend(list, j);
               

       }else if(strcmp(charArray[j], charArray[back(list)]) >= 0 ){
  		append(list, j);
                
       }else{
  		moveFront(list);
  		while(index(list) != -1){
  			if(strcmp(charArray[j], charArray[get(list)]) <= 0 ){
  				insertBefore(list, j);
  				break;
  			}else{
  				moveNext(list); 			} 		
  		}
  	}
       j++;
  }
 
 
  //print the list 

  moveFront(list);
  while(index(list) != -1){
       fprintf(out, "%s", charArray[get(list)]);
       moveNext(list);
       

  }

//free memory
  freeList(&list); 
  while(j<count){
       free(charArray[k]);
       k++;
  }
  free(charArray);



 // close files 
  fclose(in);
  fclose(out);

  return(0);
}
