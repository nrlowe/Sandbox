package com.lowe.Sandbox.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyReviews {

    static final long MOD = 1_000_000_007L;
    static final int ALPH = 128;
    static final char[] DNA = {'A', 'C', 'G', 'T'};

    //Finding sets of palindromes (length 4) in a string with mod behavior (for long strings)
    public static long count(String s) {
        return 0 % MOD;
    }

    //Stable Gene Problem
    public static int stableGene(String gene){
        return 0;
    }

    //MinCore Problem
    public static int minCoresNeeded(int[] startTimes, int[] endTimes){
       int best = 0, current = 0;
       return best;
    }

    public static String removeNDups(String s, int d){
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    //DNA from DNA fragments (no overlap)
    public static boolean dnaFragments(String dna, List<String> fragments){
        int n = dna.length();
        Map<Character, List<String>> naFirst = new HashMap<>();
        for(String fragment : fragments){
            naFirst.computeIfAbsent(fragment.charAt(0), k -> new ArrayList<>()).add(fragment);
        }
        boolean[] index = new boolean[n + 1];
        index[n] = true;
        for(int i = n - 1; i >= 0; i--){
            List<String> list = naFirst.get(dna.charAt(i));
            for(String f : list){
                int end = i + f.length();
            }
        }
        return index[0];
    }

    //Smallest Set of Numbers for a Sum
    public static int smallestSetForSum(int sum, List<Integer> nums){
        int best = 0;
        return best;
    }
    
}
