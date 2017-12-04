package abc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Teste{
	
	

	    public static void main(String args[]) {

	    	List<String> list1 = Arrays.asList("10", "1", "20", "11", "21", "12");
	    	
	    	List<String> list2 = Arrays.asList("pedro", "ana", "aaron");
	    	
	    	List<Aluno> list3 = new LinkedList<Aluno>();
	    	list3.add(new Aluno("pedro"));
	    	list3.add(new Aluno("ana"));
	        
	    	
	    	//Strategy
	    	Comparator<String> cmp1 = new Comparator<String>() {
	        	public int compare(String o1, String o2) {
	        		return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
	        	}
	        };
	        
	        
	        //Strategy
	        Comparator<String> cmp2 = new Comparator<String>() {
	        	public int compare(String o1, String o2) {
	        		return o1.compareTo(o2);
	        	}
	        };
	        
	        
	        //Strategy
	        Comparator<Aluno> cmp3 = new Comparator<Aluno>() {
	        	public int compare(Aluno a1, Aluno a2) {
	        		return a1.getNome().compareTo(a2.getNome());
	        	}
	        };
	        
	        Collections.sort(list1, cmp1);
	        
	        for(String s : list1){
	        	System.out.println(s);
	        }
	        
	        
	        System.out.println("--------------------------");
	        
	        Collections.sort(list2, cmp2);
	        
	        for(String s : list2){
	        	System.out.println(s);
	        }
	        
	        System.out.println("--------------------------");
	        
	        Collections.sort(list3, cmp3);
	        
	        for(Aluno aluno : list3){
	        	System.out.println(aluno.getNome());
	        }
	    }

	
	

}
