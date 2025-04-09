// Problem1 Remove Invalid Parentheses(https://leetcode.com/problems/remove-invalid-parentheses/)

// Time Complexity : O(n*n) 
// Space Complexity : O(n*n) 
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach in three sentences only
/*
 * Here, take a queue and hashset. Initially add s to both q and set and flag = false. While q is not empty take size of q and iterate until
 * size of q and take curr as top element and check if its valid if yes add it to result and make flag true. Find subStrings when flag is false
 * if char is alphabet continue if not take substring from 0 to j and j+1 to end and if is not in set add to set and queue. Finally return
 * result.
 */
// 1
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        q.add(s);
        set.add(s);
        boolean flag = false;
        while(!q.isEmpty() && !flag){
            int size = q.size();
            for(int i = 0; i < size; i++){
                String curr = q.poll();
                if(isValid(curr)){
                    result.add(curr);
                    flag = true;
                }
                // make babies
                if(!flag){
                    for(int j = 0; j < curr.length(); j++){
                        char ch = s.charAt(j);
                        if(Character.isAlphabetic(ch)) continue;
                        String baby = curr.substring(0, j) + curr.substring(j+1);
                        if(!set.contains(baby)){
                            q.add(baby);
                            set.add(baby);
                        }                        
                    }
                }
            }
        }

        return result;
    }
    private boolean isValid(String s){
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(Character.isAlphabetic(ch)) continue;
            if(ch == '('){
                count++;
            }
            else{
                if(count == 0) return false;
                count--;
            }
        }
        return count == 0;
    }
}
// 2
class Solution {
    boolean flag = false;
    int max = 0;
    List<String> result;
    public List<String> removeInvalidParentheses(String s) {
        result = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        set.add(s);
        dfs(s, set);
        return result;
    }
    private void dfs(String s, HashSet<String> set){
        if(s.length() < max) return;
        // logic
        if(isValid(s)){
            if(s.length() > max){
                max = s.length();
                result = new ArrayList<>();
            }
            result.add(s);
        }
        if(s.length() >= max){
            //make babies
            for(int i = 0; i < s.length(); i++){
                char ch = s.charAt(i);
                if(Character.isAlphabetic(ch)) continue;
                String baby = s.substring(0,i) + s.substring(i+1);
                if(!set.contains(baby)){
                    set.add(baby);
                    dfs(baby, set);
                }
            }
        }
    }
    private boolean isValid(String s){
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(Character.isAlphabetic(ch)) continue;
            if(ch == '('){
                count++;
            }
            else{
                if(count == 0) return false;
                count--;
            }
        }
        return count == 0;
    }
}