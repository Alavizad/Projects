/* Ashley Lavizadeh
   Alavizad
   Pa2
   C file for Linked List ADT 
   
*/
#include <stdlib.h>
#include <stdio.h>
#include "List.h"


typedef struct NodeObj {
       int data;
       struct NodeObj* next;
       struct NodeObj* prev;
} NodeObj;

//private Node type
typedef NodeObj* Node;

typedef struct ListObj {
     Node front;
     Node back;
     Node cursor;
     int length;
      int index;
}ListObj;



//typedef ListObj* List;


//Deconstructors---------------------------------------------
 
List newList(void){
    
     List list = calloc(1,sizeof(ListObj));
     list->front = NULL;
     list->back = NULL;
     list->cursor = NULL;
     list->index = -1;
     list->length = 0;
     return list;
}




Node newNode(int data){
     Node node = calloc(1,sizeof(NodeObj));
     node->data = data;
     node->next = NULL;
     node->prev = NULL;
     return node;
}





void freeNode(Node* pN){ 
      if(pN != NULL && *pN != NULL){
	       free(*pN);
               *pN = NULL;
                
	}
}

void freeList(List* pL){
        if(pL != NULL && *pL != NULL){
            while(length(*pL) != 0){
                deleteFront(*pL);
            }
            clear(*pL);
            free(*pL); 
            *pL = NULL;
        }
}

//Accsses Functions---------------------------------------------------------
  

int length(List L){
	return L->length;
}

int index(List L){
	if (L->cursor == NULL){  
		return -1;
	}
	
	   return L->index;
	
}

int front(List L){
	if(L->length == 0 ||  NULL){
		printf("List Error: Tried to call front() on an undefined list");
		exit(0);
	}
	return L->front->data;
}

int back(List L){
	if(L->length == 0){
		printf("List Error: Tried to call back() on empty list");
		exit(0);
	}
	return L->back->data;
}

int get(List L){
       if(L->index == -1){
       printf("List Error: cursor not positioned");
       exit(0);
	}
	return L->cursor-> data;
}
int equals(List A, List B){
    if(length(A) != length(B))
    	return 0; 
    Node Acurrent = A->front;
    Node Bcurrent = B->front;
    int i = 0;
    while(i < length(A)){
        if(Acurrent->data != Bcurrent->data) return 0;
        Acurrent = Acurrent->next;
        Bcurrent = Bcurrent->next;
        i++;
      }
    return 1;
    }

 

//Manipulation procedures -----------------------------------------------------

void clear(List L){   //change cursor
    if(L == NULL){
      printf("List Error: called clear() on null List reference\n");
      exit(1);
    }
    while(L->length > 0){
        deleteFront(L);
    }
      L->length = 0;
      L->index = -1;
      L->front = NULL;
      L->back = NULL;
      L->cursor = NULL;
   
}
void moveFront(List L){
  if(L == NULL){
      printf("List Error: called moveFront() on empty or undefined list\n");
      exit(0);
     }
   if(L->length >0 ){
      L->cursor = L->front;
      L->index = 0;  
   }
      
       
}
	

void moveBack(List L){
  if(L->length == 0 || L == NULL){
      printf("List Error: called moveBack() on empty or undefined list\n");
      exit(1);
    }
      L->cursor = L->back;
      L->index = L->length - 1;
}


void movePrev(List L){
   if(L->length == 0 || L == NULL){
      printf("List Error: called movePrev() on empty or undefined list\n");
      exit(0);
    }
   if (L->cursor != NULL && L->cursor == L->front){ 
      L->cursor = NULL;
      L->index = -1; 
   }else if (L->cursor != NULL && L->cursor != L->front){
      L->cursor = L->cursor->prev;
      L->index--;
   }

}
void moveNext(List L){
   if(L->length == 0 || L == NULL){
      printf("List Error: called moveNext() on empty or undefined list\n");
      exit(0);
  }

   if (L->cursor != NULL && L->cursor != L->back){
      L->cursor = L->cursor->next;
      L->index++;
   }else if(L->cursor !=NULL && L->cursor == L->back){
      L->cursor = NULL;
      L->index = -1;
   }
}


void prepend(List L, int data){
    if(L->index != -1){
       L->index++;
    }
    if(L->length == 0){
      Node node = newNode(data);
      L->front = node;
     L->back = node; 
    }else{
      Node node = newNode(data);
      node->next = L->front;   
      L->front->prev = node; 
      L->front = node;
   
    }

  
    L->length++; 
}
void append(List L, int data){
    if(L->length == 0){
      Node node = newNode(data);
      node->next = L->front;
      L->front = node;      
       L->back = L->front; 
    }else{
      Node node = newNode(data);
      node->prev = L->back;
      L->back->next = node;
      L->back = node;
    }
    L->length++;
}
void insertBefore(List L, int data){  
    
    if( L->length == 0 ){
       printf("List Error: tried to insert on empty list");
       exit(0);
    }
    if( index(L) == -1 ){
         printf ("List Error: tried to insert with no index"); 
         exit(0);
    }
    if (L->index == 0) {
        prepend(L,data);
       // L->index++;
        return;
    } 
        Node  temp = L->cursor;
        Node  node = newNode(data);
        temp->prev->next = node;
        node->prev = temp->prev;
        node->next = temp;
        temp->prev = node;
        L->index++; 
    

    L->length++;
   
  
}
void insertAfter(List L, int data){
	if( L->length == 0 ){
        printf("List Error: tried to insert on empty list");
        exit(0);
    }
    if( L->index == -1 ){
        printf("List Error: tried to insert with no index");
        exit(0);
    }
    
    if (L->index == L->length-1) {
        // Adding to the back of the list
        Node node = newNode(data);
        L->back->next = node;
        node->prev = L->back; 
        L->back = node; 
        if (L->front == NULL)
            L->front = node; 
    }else{
        Node  temp = L->cursor;
        Node  node = newNode(data);
        temp->next->prev = node;
        node->next = temp->next;
        node->prev = temp;
        temp->next = node;
        
    }

    L->length++;
}
//could error be coming from index here? 
void deleteFront(List L){                                    
     if( L == NULL ){
      printf("List Error: tried to delete on empty list\n");
       exit(0);
    }
    
 
    if (L->index == 0){

      L->cursor = NULL;
      L->index = -1;
    }
    else if(L->index > -1){
        L->index--;
    }
    
    Node N = L->front;
    L->front = L->front->next;
  //  L->front->prev = NULL; 
    L->length--;
    freeNode(&N);
    
   


}
void deleteBack(List L){
   if( L == NULL ){
      printf("List Error: tried to delete on empty list");
       exit(0);
    }
     if(L->cursor == L->back){
        L->cursor = NULL;
        L->index = -1;
    }
    Node N = L->back; 
    L->back = L->back->prev;
   //L->back->next = NULL;
    freeNode(&N);
    L->length--;
   }





void delete(List L){
    if( L == NULL){
       printf("List Error: tried to delete on empty list");
       exit(0);
    }
    if (L->index == 0){
         deleteFront(L);
         return;
    } if (L->index == L->length-1){
        deleteBack(L);
        return;
    }else{
      
        Node N = L->cursor;
        L->cursor->prev->next = L->cursor->next;
        L->cursor->next->prev = L->cursor->prev; 
        L->cursor = NULL;
        L->index = -1;
        freeNode(&N);
        L->length--;
    }
     printf("Front:%d\n",front(L));
    

}


void printList(FILE* out, List L){
    if ( L == NULL){
      printf("List Error: calling printList on NULL reference\n");
    }
    Node N = NULL;
    for(N = L->front; N != NULL; N = N->next){
      fprintf(out, "%d ", N->data);
    }
}
        

List copyList(List L){
  List copy = newList();
  Node current = L->front; 
  int i = 0;
  while(i<L->length){
    append(copy, current->data);
    current = current->next;
    i++;
  }
  return copy;
}




