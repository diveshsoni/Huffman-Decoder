import java.util.Scanner;


public class HuffmanDecoder {

	public static void main(String[] args) {
		
		HuffmanDecoder hfTree = new HuffmanDecoder();
		Scanner in = new Scanner(System.in);
		int k = in.nextInt();
		String[][] codes = new String[k][2];
		
		for(int i = 0; i < k; i++){
			codes[i][0] = in.next();
			codes[i][1] = in.next();
		}
		
		int n = in.nextInt();
		in.nextLine();
		char[] bitStream = in.nextLine().toCharArray();
		in.close();
		
//		Print stored code-sym pairs
//		for(int i = 0; i < k; i++ ){
//			System.out.println(codes[i][0] +": "+ codes[i][1]);
//		}
//		System.out.println(bitStream);
		
		decode(codes, k, hfTree, n, bitStream);
		
	}
	
	private static void decode(String[][] codes, int k, HuffmanDecoder hfTree, int n, char[] bitStream) {
		
		Symbol root = hfTree.new Symbol();
		Symbol temp = null;
		Symbol current = null;
		String thisBit = "";
		
		for(int j = 0; j < k; j++){
			
			String code = codes[j][1];
			current = root;
			for(int i = 0; i < code.length() - 1; i++){
				
				thisBit = "";
				thisBit += code.charAt(i);
//				System.out.print(thisBit+": ");
				if(thisBit.equals("1")){
//					System.out.println(thisBit);
					if(current.right == null){
						temp = hfTree.new Symbol();
						current.right = temp;
					}
					current = current.right;
				}
				else{
//					System.out.println(thisBit);
					if(current.left == null){
						temp = hfTree.new Symbol();
						current.left = temp;
					}
					current = current.left;
				}
			}
			
			thisBit = "";
			thisBit += code.charAt(code.length() - 1);
//			System.out.print(thisBit+": ");
			if(thisBit.equals("1")){
//				System.out.println(thisBit+"in");
				if(current.right == null){
					temp = hfTree.new Symbol(codes[j][0]);
					current.right = temp;
				}
				temp = current.right;
				temp.symbol = codes[j][0];
//				System.out.println(current.symbol);
			}
			if(thisBit.equals("0")){
//				System.out.print(thisBit+"in");
				if(current.left == null){
					temp = hfTree.new Symbol(codes[j][0]);
					current.left = temp;
				}
				temp = current.left;
				temp.symbol = codes[j][0];
//				System.out.println(current.symbol);
			}
		}
		
		int i = 0;
		thisBit = "";
		
		i = 0;
		current = root;
		while(i < n){
			thisBit = "";
			thisBit += bitStream[i++];
//			System.out.println(thisBit);
			if(thisBit.equals("1")){
				current = current.right;
			}
			else{
				current = current.left;
			}
			if(current.left == null && current.right == null){
				System.out.print(current.symbol);
				current = root;
			}
			
		}
		
	}
	
	class Symbol{
		String symbol;
		Symbol left;
		Symbol right;
	
		public Symbol(String symbol) {
			this.symbol = symbol;
			this.right = null;
			this.left = null;
		}
		public Symbol() {
			this.symbol = "DefaultValue";
			this.right = null;
			this.left = null;
		}	
	}
}
