package com.lowe.Sandbox.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HackerRank {

//Save-Humanity - timeout error on larger test cases
    public void virusIndices(String p, String v) {
        char[] geneArr = p.toCharArray();
        char[] virusArr = v.toCharArray();
        List<Integer> returnIndices = new ArrayList<Integer>();
        boolean loop = true;
        int startingIndex = 0;
        while(loop){
            int virusIndex = 0;
            int mismatch = 0;
            int geneLength = virusArr.length;
            int lastIndex = geneArr.length - 1 - (virusArr.length/2);
            if(startingIndex == lastIndex){
                loop=false;
            }
            for(int i = startingIndex; i < geneArr.length; i++){
                if(geneLength > 0){
                    if(geneArr[i] != virusArr[virusIndex]){
                        mismatch++;
                        geneLength--;
                        virusIndex++;
                        if(mismatch > 1){
                            startingIndex++;
                            break;
                        }
                    } else {
                        if(geneLength - 1 <= 0 && mismatch > 1){
                            startingIndex++;
                            break;
                        } else if(virusIndex + 1 >= virusArr.length - 1 && mismatch <= 1 && startingIndex < lastIndex){
                            returnIndices.add(startingIndex);
                            startingIndex++;
                            break;
                        } else {
                            geneLength--;
                            virusIndex++;
                        }
                    }
                }
            }
        }
        if(returnIndices.size() > 0){
            String returnString = "";
            for(int i = 0; i < returnIndices.size(); i++){
                String x = Integer.toString(returnIndices.get(i));
                returnString = returnString + x + " ";
            }
            System.out.println(returnString.trim());
        } else {
            System.out.println("No Match!");
        }
    }

//Save-Humanity Cleanup
    public void virusIndicesRefactor(String p, String v) {
        //check for indexs for each char in v and map where crossovers occur
    }

//Shortest Path 2-D Array
    class Cell {
        int x, y, cost;
        Cell(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    //Directions Up, right, down, left
    private final int[] dx = {0, 1, 0, -1};
    private final int[] dy = {-1, 0, 1, 0};

    public int shortestPath(int[][] grid) {
        int rowLength = grid.length;
        int colLength = grid[0].length;
        int[][] minCost = new int[rowLength][colLength];

        for(int i = 0; i < rowLength; i++){
            for(int j = 0; j < colLength; j++){
                minCost[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Cell> queue = new PriorityQueue<>(Comparator.comparing(cell -> cell.cost));
        queue.offer(new Cell(0,0, grid[0][0]));
        minCost[0][0] = grid[0][0];
        while(!queue.isEmpty()){
            Cell currentCell = queue.poll();
            int currentx = currentCell.x;
            int currenty = currentCell.y;
            if(currentx == rowLength - 1 && currenty == colLength - 1){
                return minCost[currentx][currenty];
            }
            for(int i = 0; i < 4; i++){
                int nextx = dx[i];
                int nexty = dy[i];

                if(nextx >= 0 && nextx < rowLength && nexty >= 0 && nexty < colLength){ 
                    int newCost = minCost[currentx][currenty] + grid[nextx][nexty];
                    if(newCost < minCost[nextx][nexty]){
                        minCost[nextx][nexty] = newCost;
                        queue.offer(new Cell(nextx, nexty, newCost));
                    }
                }
            }
        }
        return -1;
    }

//Dijkstra's Algorithm
    static class DNode implements Comparable<DNode> {
        int id, cost;

        DNode(int id, int cost){
            this.id = id;
            this.cost = cost;
        }

        @Override
        public int compareTo(DNode other){
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static int DijkstraShortestPath(int n, Map<Integer, List<DNode>> graph, int start, int end){
        PriorityQueue<DNode> pq = new PriorityQueue<>();
        pq.offer(new DNode(start, 0));
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[start] = 0;
        while(!pq.isEmpty()){
            DNode current = pq.poll();
            int currentId = current.id;
            int currentCost = current.cost;

            if(currentId == end){
                return currentCost;
            }

            //Explore Neighbors
            if(graph.containsKey(currentId)){
                for(DNode neighbor : graph.get(currentId)){
                    int newCost = currentCost + neighbor.cost;
                    if(newCost < minCost[neighbor.id]){
                        minCost[neighbor.id] = newCost;
                        pq.offer(new DNode(neighbor.id, newCost));
                    }
                }
            }
        }
        return -1;
    }

//Number of Queen Moves
    public int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
        //all directions up, up right, right, down right, down, down left, left, up left
        int[] dx = {0, 1, 0, 1, 0, -1, 0, -1};
        int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1}; 
        for(int i = 0; i < dx.length; i++){
            int xmove = dx[i];
            int ymove = dy[i];
        }
        return 0;
    }

//Climbing Leaderboard Problem
    public List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        List<Integer> returnList = new ArrayList<Integer>();
        ranked.stream().distinct().sorted().collect(Collectors.toList());
        for(int j = 0; j < player.size(); j++){
            int  i = 1;
            for(Integer score : ranked){
                if(player.get(j) >= score){
                   returnList.add(i); 
                   break;
                }
                i++;
            }
        }
        
        return returnList;
    }

//Organizing Containers - moving items to match in a container in n number of swaps
    public static String organizingContainers(List<List<Integer>> container) {
    // Write your code here
        boolean possible = false;
        int containers = container.size();
        int columns = container.get(0).size();
        for(List<Integer> cont : container){

        }
        if (possible) {
            return "Possible";
        } else {
            return "Impossible";
        }
    }

    public static void containerSwap() {

    }

//Array Leap Game
    public boolean canWin(int leap, int[] game){
        boolean inGame = true;
        int index = 0;
        while(inGame){
            if(index + leap >= game.length || index + 1 >= game.length){
                return true;
            }
            if(index + leap < game.length){
                if(game[index + leap] == 0){
                    index = index + leap;
                }
            } else if (index + 1 < game.length){
                if(game[index + 1] == 0){
                    index = index + 1;
                }
            } else if(index - 1 >= 0){
                if(game[index - 1] == 0){
                    index = index - 1;
                }
            } else {
                inGame = false;
            }
        }
        return false;
    }

//Comparator Problem
    class Player{
        String name;
        int score;
        
        Player(String name, int score){
            this.name = name;
            this.score = score;
        }
    }

    public Player[] checker(Player[] players) {
        Player[] sortedArray = new Player[players.length];

        return sortedArray;
    }

//Student Priority Queue 
    class Student {
        int id;
        String name;
        double cgpa;

        Student(int id, String name, double cgpa){
            this.id = id;
            this.name = name;
            this.cgpa = cgpa;
        }

        public int getId(){
            return id;
        }

        public String getName(){
            return name;
        }

        public double getCGPA() {
            return cgpa;
        }
    }

    public List<Student> getStudents(List<String> events) {
        List<Student> returnList = new ArrayList<Student>();
        PriorityQueue<Student> pq = new PriorityQueue<Student>(Comparator.comparingDouble((Student s) -> (Double) s.getCGPA()).thenComparing(s -> s.getName()));
        for(int i = 0; i < events.size(); i++){
            if(events.get(i).startsWith("E")){
                String[] event = events.get(i).split(" ");
                pq.offer(new Student(Integer.parseInt(event[3]), event[1], Double.parseDouble(event[2])));
            } else if (events.get(i).startsWith("S")){
                pq.poll();
            }
        }
        while(!pq.isEmpty()){
            returnList.add(pq.poll());
        }
        return returnList;
    }

//3D Surface Area
    public int surfaceArea(List<List<Integer>> A){
        int surface = 0;
        int maxSurface = 6;
        int length = A.size();
        int width = A.get(0).size();
        int[] dw = {0, 1, -1, 0};
        int[] dl = {1, 0, 0, -1};
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                int block = A.get(i).get(j);
                int suf = 0;
                if(block > 1){
                    suf = (block * maxSurface) - (2 * block);
                } else {
                    suf = maxSurface;
                }
                for(int n = 0; n < 4; n++){
                    int nextw = dw[n] + j;
                    int nextl = dl[n] + i;
                    if(nextw >= 0 && nextw < width && nextl >= 0 && nextl < length){
                        int neighbor = A.get(length).get(width);
                        if(neighbor > block){
                            suf = suf - block;
                        } else if(neighbor <= block) {
                            suf = suf - neighbor;
                        }
                    }
                }
                surface = surface + suf;
            }
        }
        return surface;
    }
    
//Nondivisible Subset
    public int NondivisibleSubset(int k, List<Integer> s){
        Set<Integer> returnSet = new HashSet<Integer>();
        //remove duplicates before iterating? 
        return returnSet.size();
    }

//Largest Region in 2D Array
    public int regionSearch(List<List<Integer>> matrix){
        int regionXL = 0;
        boolean[][] visited = new boolean[matrix.size()][matrix.get(0).size()];
        for(boolean[] row : visited){
            Arrays.fill(row, false);
        }
        for(int x = 0; x < matrix.size(); x++){
            for(int y =0; y < matrix.get(0).size(); y++){
                if(!visited[x][y] && matrix.get(x).get(y) == 1){
                    //check neighbors + count
                    int regionSize = dfs(0, visited, matrix, x, y);
                    if(regionSize > regionXL){
                        regionXL = regionSize;
                    }
                } else {
                    visited[x][y] = true;
                }
            }
        }
        return regionXL;
    }

    public int dfs(int size, boolean[][] visited, List<List<Integer>> matrix, int x, int y){
        for(int n = 0; n < 4; n++){
            int nx = dx[n] + x;
            int ny = dy[n] + y;
            if(nx >= 0 && nx < visited.length && ny >= 0 && ny < visited[0].length){
                if(matrix.get(nx).get(ny) == 1 && !visited[nx][ny]){
                    visited[x][y] = true;
                    size = 1 + dfs(size, visited, matrix, nx, ny);
                }
            }
        }
        return size;
    }

//Train Tracks
    public int gridLandMetro(int n, int m, int k, List<List<Integer>> tracks){
        boolean[][] hasTrack = new boolean[n][m];
        int lights = 0;
        for(boolean[] row : hasTrack){
            Arrays.fill(row, false);
        }
        for(List<Integer> track : tracks){
            int row = track.get(0);
            int start = track.get(1);
            int end = track.get(2);
            for(int x = start; x <= end; x++){
                if(!hasTrack[row - 1][x]){
                    hasTrack[row - 1][x] = true;
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!hasTrack[i][j]){
                    lights++;
                }
            }
        }
        return lights;
    }

//CrabGraphs
    public int crabGraphs(int n, int t, List<List<Integer>> graph) {
        return -1;
    }




    //BFS
    //DFS

}
