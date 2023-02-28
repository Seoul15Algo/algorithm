import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//이해가 많이 필요합니다
public class Main_16934 {
	static int N;
	static class Node{
		Map<Character,Node> child = new HashMap<>();
		int isEnd = 0; // 해당 노드에서 insert가 종료된 갯수를 저장
	}
	static class Trie{
		Node root = new Node();
		
		public String insert(String in) {
			StringBuilder result = new StringBuilder();
			Node node = root;
			boolean endFlag = false;
			for (int i = 0; i < in.length(); i++) {
				if(node.child.get(in.charAt(i))!=null) { //노드가 존재한다면 
					node = node.child.get(in.charAt(i));
					result.append(in.charAt(i));
				}
				else {//노드가 존재하지 않는다면 새로 만들어줌
					Node next = new Node();
					node.child.put(in.charAt(i), next);
					node = next;
					if(!endFlag) {
						result.append(in.charAt(i));
						endFlag = true;
					}
				}
			}
			if(node.isEnd == 0) {
				node.isEnd = 1;
				return result.toString();
			} else {
				node.isEnd +=1;
				return result.append(node.isEnd).toString();
			}
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		Trie trie = new Trie();
		for (int i = 0; i < N; i++) {
			String str = trie.insert(br.readLine());
			sb.append(str+"\n");
		}
		System.out.println(sb.toString());
	}

}