package week30;

class 스킬트리 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        boolean[] isSkill = new boolean[26];

        for(int i = 0; i < skill.length(); i++) {
            char c = skill.charAt(i);
            isSkill[c-'A'] = true;
        }

        for(String skillTree : skill_trees) {
            int count = 0;
            boolean flag = true;

            for(int i = 0; i < skillTree.length(); i++) {
                char c = skillTree.charAt(i);
                if(count == skill.length()) break;

                if(!isSkill[c-'A']) continue;
                if(c == skill.charAt(count++)) continue;

                flag = false;
                break;
            }

            if(flag) answer++;

        }


        return answer;
    }
}