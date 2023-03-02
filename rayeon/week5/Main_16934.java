package week5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main_16934 {	
	static class Node {
		Map<Character, Node> child = new HashMap<>(); // 자식 노드
		int dupCount = 0; // 동일한 문자열의 개수
	}
	
	static class Trie {
		// 가장 상위 노드
		Node root = new Node();
		
		// 닉네임 추가 및 별칭 반환
		public String insert(String nickname) {
			Node node = root; // 맨 처음엔 가장 상위 노드 가리키기
			StringBuilder result = new StringBuilder();
			boolean endFlag = false; // 반환할 별칭 완성 여부
			
			for (int i = 0; i < nickname.length(); i++) {
				// 현재 노드가 nickname의 i번째 문자를 자식으로 가지고 있는 경우 (연속으로 일치하는 경우)
				if (node.child.get(nickname.charAt(i)) != null) {
					// 해당 자식 노드로 이동
					node = node.child.get(nickname.charAt(i));
					// 별칭에 해당 문자 추가
					result.append(nickname.charAt(i));
					
					continue;
				} 
				
				// 현재 노드가 nickname의 i번째 문자를 자식으로 가지고 있지 않은 경우 (연속으로 일치하지 않는 경우)
				Node next = new Node(); // 새로운 자식 노드 생성
				node.child.put(nickname.charAt(i), next); // 해당 문자를 현재 노드에 자식 노드로 추가
				node = next; // 자식 노드로 이동
				
				// 별칭을 완성한 경우 더 이상 문자를 추가할 필요가 없으므로 자식 노드만 추가
				if (!endFlag) { // 문자가 일치하지 않으므로 별칭 완성 가능
					result.append(nickname.charAt(i)); 
					endFlag = true; // 별칭 완성 
				}
			}
			
			if (node.dupCount == 0) { // 동일한 문자열이 없는 경우
				node.dupCount = 1; // 현재 노드까지 일치하는 문자열은 자기 자신 밖에 없으므로 1
				
				return result.toString();
			} else { // 동일한 문자열이 있는 경우
				node.dupCount++; // 현재 노드까지 일치하는 문자열이 있는 것이므로 중복 개수 증가
				
				return result.append(node.dupCount).toString();
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		Trie trie = new Trie();
		
		for (int i = 0; i < N; i++) {
			// 닉네임으로 trie 구성
			String input = trie.insert(br.readLine());
			
			sb.append(input + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
