
/*Ashley Lavizadeh
  alavizad
  pa1
 */ 


 public class List{
  
    private class Node{
        int data;
        Node next;
        Node prev;
        Node(int d){ 
            data = d; 
            next = null;
            prev = null;
        }

        public String toString(){
          String str = "" + data;
          return str;
        }   
    
    
 
    }
  
  private Node front;
  private Node back;
  private Node cursor;
  
  private int length;
  private int index = -1;
  
  public List(){
    front = null;
    back = null;
    length = 0;
  }
  
  //--- Accsess Functions --------
  int length(){
    return length;
  }
  
  int index(){
    if ( cursor == null ){
      return -1;
    }
    return index; 
  }
  
 
  int front(){
    if (length == 0){
      throw new RuntimeException(
        "List Error: front() called on an empty list");
    }
    return front.data;
  }
    
  int back(){
    if (length == 0){
      throw new RuntimeException(
        "List Error: back() called on an empty list");
    }
    return back.data;
  }
  
  int get(){
    if (length == 0){
      throw new RuntimeException(
        "List Error: get() called on an empty list");
    }
    return cursor.data;
  }
      
  boolean equals(List L){
    if(L.length() != length) {return false;}
    Node lList = L.front;
    Node thisList = front; 
    int i = 0;
    while(i < length){
        if(lList.data != thisList.data) return false;
        lList = lList.next;
        thisList = thisList.next; 
        i++;
      }
    return true;
    }
      
 //---Manipulation prcedures------
  
 
  void moveFront(){
    if(length !=0){
      cursor = front;
    }
    index = 0;
  }
  
  
  void moveBack(){
    if(length !=0){
      cursor = back;
      index = length - 1;
    }
  }
  
  
  void movePrev(){
   if (cursor != null && cursor != front){
     cursor = cursor.prev;
     index--;
   }
   else if (cursor != null && cursor == front){ 
     cursor = null; 
   }
  }
  
  
  void moveNext(){
   if (cursor != null && cursor != back){
     cursor = cursor.next;
     index++;
   }
   else if (cursor !=null && cursor == back){
     cursor = null; 
   }
  }

  
  void clear(){
    front = null; 
    back = null;
    cursor = null;
    length = 0;
    }
      
  
  void prepend(int data){ //conditions for adding at the end?
    if(length == 0){
      Node newNode = new Node(data);
      newNode.next = front;
      front = newNode;
      if(back == null)
        back = front; 
    }else{
      Node newNode = new Node(data);
      newNode.next = front;
      front.prev = newNode;
      front = newNode;
    }
    if(cursor != null){
       index++;
    }
    length ++; 
  }
    
  
  void append(int data){
    if(length == 0){
      Node newNode = new Node(data);
      newNode.next = front;
      front = newNode;
      if(back == null)
        back = front; 
    }else{
      Node newNode = new Node(data);
      newNode.prev = back;
      back.next = newNode;
      back = newNode;
    }
    length++;
  }
  
  //insert element before cursor 
  void insertBefore(int data){    //question, do I want the cursor position to stay the
    if( length == 0 ){
      throw new RuntimeException(
        "List Error: tried to insert on empty list");
    }
    if( index == -1 ){
      throw new RuntimeException(
        "List Error: tried to insert with no index"); //update proper error message
    }
   
    if (index == 0) {
        // Adding to the front of the list
        Node newNode = new Node(data);
        cursor.prev = newNode;
        newNode.next = cursor;
        newNode.prev = null;  
        front = newNode; 
        cursor = front.next;
        index++; 
    }
    else{
        Node temp = cursor;
        Node newNode = new Node(data);
        temp.prev.next = newNode;
        newNode.prev = temp.prev;
        newNode.next = temp;
        temp.prev = newNode;
        index++;
         
    }
   
    length++;
  
   
  }
  
  
  void insertAfter(int data){
    if( length == 0 ){
      throw new RuntimeException(
        "List Error: tried to insert on empty list");
    }
    if( index == -1 ){
      throw new RuntimeException(
        "List Error: tried to insert with no index"); 
    }
    
    if (index == length-1) {
        // Adding to the back of the list
        Node newNode = new Node(data);
        back.next = newNode;
        newNode.prev = back; 
        back = newNode; 
        if (front == null)
          front = newNode; 
    }else{
        Node temp = cursor;
        Node newNode = new Node(data);
        temp.next.prev = newNode;
        newNode.next = temp.next;
        newNode.prev = temp;
        temp.next = newNode;
        
    }

    length++;
 }


 void deleteFront(){
   if( length <= 0 ){
      throw new RuntimeException(
        "List Error: tried to delete node in empty list");
    }
    
    if (cursor == front){
        cursor = null;
        index = -1;
    }
    else if(cursor != null){
        index --;
    }
    front.next.prev = null;
    front = front.next; 
    length--;
 }

 void deleteBack(){
    if( length <= 0 ){
      throw new RuntimeException(
        "List Error: tried to delete node in empty list");
    }
    if(length == 1){
      this.clear();
    }else{
    if(cursor == back){
        cursor = null;
        index = -1;
    }
    
    back = back.prev;
    back.next = null;
    length--;
   }

 }

 void delete(){ 

    if( length <= 0 ){
      throw new RuntimeException(
        "List Error: tried to delete node in empty list");
    }
    if( index == -1 ){
      throw new RuntimeException(
        "List Error: no index selected");
    }
    //when the index is at the front
    if (index == 0){
        Node current = front;
        front = current.next;
        front.prev = null;
        current.next = null;
        cursor = null;
    //when the index is at the back     
    }else if (index == length-1){
        Node B = back;
        back = B.prev;
        back.next = null;
        B.prev = null;
        cursor = null;
    }else{
       /* cursor.prev.next = cursor.next;
        cursor.next.prev = cursor.prev; 
        cursor = null;
       */

       Node b = cursor.prev;
       Node a = cursor.next;
       b.next = a;
       a.prev = b;
       cursor = null;
    }
    length--;
 }

 List copy(){
    List copy = new List();
    Node current = front;
    int i = 0;
    while(i < length){
      copy.append(current.data);
      current = current.next;
      i++;
    }
    return copy;
 }




 public String toString() {
    String s = "";
    Node current = front;
    while (current != null) {
      s = s + current.data;
      if (current.next != null)
        s = s + " ";
      current = current.next;
    }
    s = s + " ";
    return s;   
  }
  
      
  
}
