/*
* Ashley Lavizadeh
* alavizad
*  Tests list ADT for some reason even though we already did this assignment 
* pa3
*/

class ListTest{
	public static void main(String[] args){
		List List = new List();
        List.append(1);
        List.append(32);
        List.append(87);
        List.append(67);
        List.moveFront();
        List.insertBefore(55);
        List.moveNext();
        List.insertAfter(888);
        List newList = List.copy();
        System.out.print(newList);

        List.deleteBack();
        System.out.print(List);
	}
}