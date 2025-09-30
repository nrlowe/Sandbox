package com.lowe.Sandbox.business;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class DailyReviews {

    static final long MOD = 1_000_000_007L;
    static final int ALPH = 128;
    static final char[] DNA = {'A', 'C', 'G', 'T'};

    //Finding sets of palindromes (length 4) in a string with mod behavior (for long strings)
    public static long palindromeCount(String s) {
        int[] left = new int[ALPH];
        int[] right = new int[ALPH];
        int n = s.length();
        int ans = 0;
        int[][] index = new int[ALPH][ALPH];
        for(int i = 0; i < n; i++){
            right[s.charAt(i)]++;
        }
        for(int k = 0; k < n; k++){
            char c = s.charAt(k);
            right[c]--;
            int[] indexB = index[c];
            for(int a = 0; a < ALPH; a++){
                if(right[a] > 0 && indexB[a] > 0){
                    ans += indexB[a] * right[a];
                }
            }
            for(int a = 0; a < ALPH; a++){
                if(left[a] > 0){
                    indexB[c] += left[a];
                }
            }
            left[c]++;
        }
        return ans;

    }

    //Stable Gene Problem
    public static int stableGene(String gene){
        int n = gene.length();
        int t = n / 4;
        int[] freq = new int[ALPH];
        int[] extra = new int[ALPH];
        int tE = 0;
        for(int i = 0; i < n; i++){
            freq[gene.charAt(i)]++;
        }
        for(int i = 0; i < DNA.length; i++){
            extra[i] = Math.max(0, freq[i] - t);
            tE += extra[i];
        }
        int ans = n, l = 0;
        for(int i = 0; i < n; i++){
            char ch = gene.charAt(i);
            if(extra[ch] > 0){
                extra[ch]--;
            }
            tE--;
            while(tE == 0){
                ans = Math.min(ans, i - l + 1);
                char cl = gene.charAt(l++);
                extra[cl]++;
                if(extra[cl] > 0){
                    tE++;
                }
            }
        }
        return ans;
    }

    //MinCore Problem
    public static int minCoresNeeded(int[] startTimes, int[] endTimes){
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);
        int s = 0, e = 0, best = 0, current = 0;
        int sn = startTimes.length;
        while(s < sn){
            if(startTimes[s] < endTimes[e]){
                current++;
                best = Math.max(best, current);
                s++;
            } else {
                e++;
                current--;
            }
        }
        return best;
    }

    public static String removeNDups(String s, int d){
        int[] counts = new int[s.length()];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            sb.append(ch);
            int j = sb.length() - 1;
            counts[j] = (j > 0 && sb.charAt(j) == ch ? counts[j - 1] : 0) + 1;
            if(counts[j] == d){
                sb.setLength(sb.length() - d);
            }
        }
        return sb.toString();
    }

    //DNA from DNA fragments (no overlap)
    public static boolean dnaFragments(String dna, List<String> fragments){
        int n = dna.length();
        Map<Character, List<String>> lk = new HashMap<>();
        boolean[] flag = new boolean[n + 1];
        flag[n] = true;
        for(int i = n; i >= 0; i--){
            char ch = dna.charAt(i);
            List<String> y = lk.get(ch);
            if(y == null){
                continue;
            }
            for(String f : y){
                int end = i + f.length();
                if(end <= n && dna.startsWith(f, i) && flag[end]){
                    flag[i] = true;
                    break;
                }
            }
        }
        return flag[0];
    }

    //Smallest Set of Numbers for a Sum
    public static int smallestSetForSum(int sum, List<Integer> nums){
        int[] dp = new int[sum + 1];
        int t = sum + 1;
        Arrays.fill(dp, t);
        dp[0] = 0;
        for(int num : nums){
            for(int a = num; a <= t; a++){
                dp[a] = Math.min(dp[a], dp[a - num] + 1);
            }
        }
        return dp[t] > t ? -1 : dp[t];
    }

    //Smallest Set of Numbers for a Sum BFS
    public static int smallestSetForSumBFS(int sum, List<Integer> nums){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] flag = new boolean[sum + 1];
        int step = 0;
        flag[0] = true;
        q.add(0);
        while(!q.isEmpty()){
            int sz = q.size();
            int x = q.poll();
            step++;
            for(int n : nums){
                int e = x + n;
                if(e == sum){
                    return step;
                } else if (e < sum && !flag[n]){
                    q.add(n);
                    flag[n] = true;
                } else {
                    break;
                }
            }
        }
        return -1;
    }

    //Minimum DNA Fragment Coverage TODO:: Verify
    public static List<String> buildOptimalCover(String dna, List<String> frags){
        List<String> optCover = new ArrayList<>();
        Map<Character, List<String>> fMap = new HashMap<>();
        PriorityQueue<List<String>> q = new PriorityQueue<>(Comparator.comparing(k -> k.size()));
        if(dna.length() <= 0 || frags.size() <= 0){
            return optCover;
        }
        char first = dna.charAt(0);
        for(String f : frags){
            if(f.charAt(0) == first){
                fMap.computeIfAbsent(first, l -> new ArrayList<>()).add(f);
                if(dna.startsWith(f)){
                    List<String> temp = new ArrayList<>();
                    temp.add(f);
                    q.add(temp);
                }
            }
        }
        while(!q.isEmpty()){
            List<String> combo = q.poll();
            int end = combo.stream().mapToInt(String::length).sum();
            List<String> temp = new ArrayList<>();
            if(end == dna.length()){
                if(optCover.isEmpty() || temp.size() < optCover.size()){
                    optCover.clear();
                    optCover.addAll(temp);
                }
                continue;
            }
            
            for(String f : frags){
                List<String> t = new ArrayList<>();
                t.addAll(combo);
                if(dna.regionMatches(end, f, 0, f.length())){
                    t.add(f);
                    q.add(t);
                }
            }
            
        }
        return optCover;
    }

    public static int[] minCountAndWays(int T, int[] nums){
        nums = Arrays.stream(nums).filter(x -> x > 0 && x < T).distinct().sorted().toArray();
        int INF = T + 1;
        int[] min = new int[INF];
        Arrays.fill(min, INF);
        min[0] = 0;
        int[] ways = new int[INF];
        ways[0] = 1;
        for(int x : nums){
            for(int i = x; i <= T; i++){
                min[i] = Math.min(min[i], min[i - x] + 1);
            }
        }
        if(min[T] > T){
            return new int[]{-1, 0};
        }
        return new int[]{min[INF], ways[INF]};
    }

    //Shortest Path 2-D Array

    //Directions Up, right, down, left
    private final int[] dx = {0, 1, 0, -1};
    private final int[] dy = {-1, 0, 1, 0};

    public class Cell {
        int x, y, cost;
        Cell(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public int shortestPath(int[][] grid) {
        
        return -1;
    }
    
}
