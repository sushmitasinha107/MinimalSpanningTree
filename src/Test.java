import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class Test {

	class node {
		int indexvalue;
		List indices;
	}

	public static void main(String args[]) throws IOException {
		Map<Integer, ArrayList> myMap = new HashMap<Integer, ArrayList>();
		int[] array = { 2, 1, 1, 0, 1 };
		ArrayList<Integer> arrlist=new ArrayList<Integer>();
		arrlist.add(2);
		arrlist.add(1);
		   arrlist.add(1);
		   arrlist.add(0);
		   arrlist.add(1);
		   int retval=arrlist.indexOf(1);
		   System.out.println(retval);
		for (int i = 0; i < array.length; i++) {
			/*aList = new ArrayList<Integer>();
			for (int j = 0; j < array.length; j++) {
				if (array[j] == i)
					aList.add(j);
			}
			myMap.put(i, aList);*/
		}
		System.out.println(myMap);
		String myArray[]= {"23","23","10","22","10"}; 
		int arrlist11=Arrays.asList(myArray).indexOf("23");
		System.out.println(arrlist11);
	}

}
