// Problem2 Clone graph (https://leetcode.com/problems/clone-graph/)

// Time Complexity : O(V+E) 
// Space Complexity : O(V) 
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach in three sentences only
/*
 * Here, take deep copy of node given and add it to map and queue. While queue is not empty take the top element and for all the neighbors of
 * popped element if its not present make a deep copy and add it to map and queue. Add all neighbors to map.get(curr).neighbors. Finally
 * return newNode.
 */
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
// 1
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        Node newNode = new Node(node.val);
        HashMap<Node, Node> map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        map.put(node, newNode);
        q.add(node);
        while(!q.isEmpty()){
            Node curr = q.poll();
            for(Node neighbor: curr.neighbors){
                if(neighbor != null && !map.containsKey(neighbor)){
                    Node newNeighbor = new Node(neighbor.val);
                    map.put(neighbor, newNeighbor);
                    q.add(neighbor);
                }
                map.get(curr).neighbors.add(map.get(neighbor));
            }   
        }
        return newNode;
    }
}

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
// 2
class Solution {
    HashMap<Node, Node> map;
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        Node newNode = new Node(node.val);
        map = new HashMap<>();
        map.put(node, newNode);
        dfs(node);
        return map.get(node);
    }
    private void dfs(Node node){
        //base
        if(node == null) return;
        //logic
        for(Node neighbor: node.neighbors){
            if(!map.containsKey(neighbor)){
                map.put(neighbor, new Node(neighbor.val));
                dfs(neighbor);
            }
            map.get(node).neighbors.add(map.get(neighbor));
        }
    }
}