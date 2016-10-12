import java.util.*;
import java.util.regex.Pattern;
public class HW1 {
	public static int q;
	//check if the formula is in the correct form, return true if there is unknown number(e.g. x,y), else false
	public static boolean Check(String s){
		boolean flag = true;
		int len = s.length();
		//System.out.println(len)锛 
		char sign;
		//take out the last number and check if it is correct
		int lastNum = len - 1;
		if(Character.isDigit(s.charAt(lastNum))){
			while(Character.isDigit(s.charAt(lastNum))){
				lastNum--;
			}
		}
		else if(s.charAt(lastNum) >= 'A' && s.charAt(lastNum) <='z'){
			lastNum--;
		}
		else{
			System.out.println("Wrong Format!");

			flag = true;
		}
		//check out the rest formula
		for(int i = 0; i < lastNum + 1; i++){
			if(Character.isDigit(s.charAt(i))){
				while(Character.isDigit(s.charAt(i))){
					i++;
				}
			}
			else if(s.charAt(i) >= 'A' && s.charAt(i) <='z'){
				i++;
			}
			else{
				System.out.println("Wrong Format!");
				flag = false;
			}
			sign = s.charAt(i);
			if(sign == '+' || sign =='*'){
				
			}
			else{
				System.out.println("Wrong Format!");
				flag = false;
			}
		}
		return flag;
	}

	
	
	public Stack<String> Expression(String s){
		//take out all the parts and put them separtely into the list
	int i;
	ArrayList inOrder = new ArrayList<String>();
	String num = "";		
	int lastLen = s.length() - 1;
	if(Character.isDigit(s.charAt(lastLen))){		//take out the last num
		while(Character.isDigit(s.charAt(lastLen))){
			lastLen--;
		}
	}
	for(i = 0; i < lastLen + 1; i++){	//store the rest(without the last) into the list
		if(Character.isDigit(s.charAt(i))){
			while(Character.isDigit(s.charAt(i))){
				num = num + s.charAt(i);
				i++;
			}
			i--;
			inOrder.add(num);
			num = "";
		}
		else{
			inOrder.add(s.charAt(i));
		}
	}
	for(i = lastLen + 1; i < s.length(); i++){		//add the last num into the list
		num = num + s.charAt(i);
	}
	inOrder.add(num);
		//change that list into a string form
	String[] inOrder1 = new String[inOrder.size()];
	for(i = 0; i < inOrder.size(); i++){
		inOrder1[i] = inOrder.get(i).toString();
	}
	//for test
	System.out.println(inOrder);

		//store numbers into stack1 and operators into stack2, and clear stack2 afterwards
	Stack<String> stack1 = new Stack<String>();
	Stack<String> stack2 = new Stack<String>();
	for (i = 0; i < inOrder.size(); i++){
		//System.out.println(inOrder1[i]);
		if(inOrder1[i].charAt(0) >= '0' && inOrder1[i].charAt(0) <= '9'){
			stack1.push(inOrder1[i]);
		}
		else if(inOrder1[i].charAt(0) == '*'){
			stack2.push(inOrder1[i]);
		}
		else if(inOrder1[i].charAt(0) == '+'){
			while(!stack2.isEmpty() && stack2.lastElement().charAt(0) == '*'){
					stack1.push(stack2.pop());
			}
			stack2.push(inOrder1[i]);
		}	
		
	}
	
}

	public static Integer Simplify(Stack<String> postOrder){
		Stack stack = new Stack();
		for(int i = 0; i < postOrder.size(); i++){
			if(postOrder.get(i).charAt(0) >= '0' && postOrder.get(i).charAt(0) <= '9'){
				stack.push(Integer.parseInt(postOrder.get(i)));
			}
			else{
				Integer a = (Integer)stack.pop();
				Integer b = (Integer)stack.pop();
				Integer result = 0;
				if(postOrder.get(i).charAt(0) == '+')
					result = a + b;
				else if(postOrder.get(i).charAt(0) == '*')
					result = a * b;
				stack.push(result);
			}
		}
		return (Integer)stack.pop();
	}
	
	public static String Sm(String s){
		boolean flag = false,flag1 = false;
		String part = "";
		String all = "";
		for(int i = 0; i < s.length(); i++){
			int num = 1;
			char[] a = new char[26];		//to save the characters
			int[] b = new int[26];		//to save the number of characters
			int y = 0;		//count the num of different characters
			int temp = 0;
			while(s.charAt(i) != '+' && i != s.length()){
				
				if(Character.isDigit(s.charAt(i))&& i != s.length())
				{
					temp=i;
					while(Character.isDigit(s.charAt(i))&& i < s.length() - 1){
						i++;
					}
					if(Character.isDigit(s.charAt(i))&& i == s.length() - 1)
						i++;
					String t=s.substring(temp, i);
					num = num * (Integer.parseInt(s.substring(temp, i)));
					i--;
					if(i != s.length()-1) i++;
					else{
						
						break;
					}
				}
				else if(Character.isLetter(s.charAt(i))&& i != s.length())
				{
					
					for(int k = 0;k < y;k++)
					{
						if(a[k] == s.charAt(i))
						{
							b[k]=b[k]+1;
							flag = true;
						}
					}
					if(!flag&& i != s.length())
					{
						a[y] = s.charAt(i);
						b[y] = 1;
						y +=1;
					}
					flag = false;
					i++;
				}
				else i++; //*
				q=y;
				if(i == s.length()) break;
			}
			if(num == 1)
				{
				part = "";
				flag1=true;
				}
			else part = ""+num;
			for(int k = 0;k <q;k++)
			{
				for(int x =0;x<b[k];x++)
				{
					if(x!=0 || !flag1) part = ""+part+"*";
					part = ""+part  + a[k];
				}

			}
			if(all != "") all = "" + all +'+'+ part;
			else all = "" + all + part;
			a = null;
			b = null;
			
		}
		return all;
	}
	
	  private static String simplify2(String s)//merge similar iterms
	    {
	    	ArrayList<String> a = new ArrayList<String>();
	    	String news = "";
	    	for(int i = 0;i < s.length();i++)
	    	{
	    		int temp = i;  // sinvar
	    		while(s.charAt(i) != '+') 
	    		{
	    			i++; //
	    			if(i == s.length()) break;
	    		}
	    		a.add(s.substring(temp, i));//cutting and storage
	    	}
	    	int sum = 0;
			for(int j = 0;j < a.size();j++)
			{
				boolean singvar = false;
				singvar = Pattern.compile("(?i)[a-z]").matcher(a.get(j)).find();//judge of contains digit
				//false => not include letter
				if(!singvar) sum +=  Integer.parseInt(a.get(j));//String tranlate into int
				else if(j == a.size()-1) news = "" + news + a.get(j);
				else news = "" + news + a.get(j) + '+';
			}
			
			if(sum != 0) news = "" + sum + '+' + news;
	    	if(news.charAt(news.length()-1) == '+') news = news.substring(0, news.length()-1);//delete the end of '+'
	    	String[] merge = null;
			merge = news.split("\\+");
			String newmerge = "";
			for(int i = 0;i < merge.length;i++)
			{
				for(int j = i+1;j<merge.length;j++)
				{
					if(merge[i] != null && merge[j] != null)
					{
						if((merge[i].substring(1)).equals(merge[j].substring(1)))//match the suffix
						{
						
							int addsum = Integer.parseInt(merge[i].substring(0, 1))+Integer.parseInt(merge[j].substring(0, 1));
							merge[i] =  String.valueOf(addsum)+merge[i].substring(1);
							merge[j] = null;
						}
						
					}
				}
				if(i!= 0 && merge[i] != null) newmerge = newmerge+'+'+merge[i];
				if(i == 0) newmerge = merge[0];
			}
	    	return newmerge;
	    }
	
	
	public static void main(String[] args){
			//scan the formula	
		boolean flag = false;
		Scanner in = new Scanner(System.in);
		String s =" ";
		//System.out.println(s);
			//check if the formula is in the correct form
		do{//the loop of input
			s = in.nextLine();
			flag = Check(s);	
		}while(!flag);
		//calculate the formula
		//check out if there's charater
		boolean flag1 = true;		//flag1=true if there's only digit in s
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) >= 'A' && s.charAt(i) <='z')
				flag1 = false;
		}
		System.out.println(s);
		if(flag1 == true){
			HW1 hw1 = new HW1();
			Stack postOrder = hw1.Expression(s);
			Integer result = Simplify(postOrder);
			System.out.println(result);
		}
		else{
			String s1 =" ";
			String[] a = null;
			boolean flag2 = false;//judge of simplify 
			boolean flag3 = true;//judge of assignment
			do//赋值输错判断循环
			{
				s1 = in.nextLine();
				a = s1.split(" ");
				if(a[0].equals("!simplify"))
				{
					String temp = " ";//assignment string
					String value = " ";
					for(int i = 1;i <a.length;i++)
					{
						temp = a[i];
						if(temp.length() < 2)
						{
							flag3 = false;
							break;
						}
						value = temp.substring(2);
						//letter=digit && if has var which  does not exit
						if(!value.matches("[0-9]{1,}") || temp.charAt(1) != '=' || !Character.isLetter(temp.charAt(0)) || !s.contains(temp.substring(0,1))) flag3 = false;
					}
					if(flag3) flag2 = true;
					if(!flag2) System.out.println("wrong format!");
					flag3 = true;
				}
				else  System.out.println("wrong format!");
			}while(!flag2);
			//judge of assignment finish
			
			String temp = " ";
			String value = " ";
			for(int i = 1;i <a.length;i++)
			{
					temp = a[i];
					value = temp.substring(2);
					s = s.replaceAll(temp.substring(0,1),value);//replace var 
					System.out.println(s);
			}
			
			//assignment finish	
			if(Check(s) == false && flag2){
				HW1 hw1 = new HW1();
				Stack postOrder = hw1.Expression(s);
				Integer result = Simplify(postOrder);
				System.out.println(result);
				//System.exit(-1);
			}
			else if(flag2){
				String all1 = "";
				String all2 = "";
				all1 = Sm(s);
				all2 = simplify2(all1);
				System.out.println(all2);
			}
		}
		
	}	
}


