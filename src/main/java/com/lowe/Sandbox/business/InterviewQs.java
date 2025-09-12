package com.lowe.Sandbox.business;

import java.text.Collator;
import java.util.Arrays;
import java.util.Stack;

public class InterviewQs {
//Minimum Cost of Equalizing an Array
    public static int findMinimumCost(int[] array) {
        // Sort the array to find the median
        Arrays.sort(array);
        int n = array.length;

        // Find the median
        int median;
        if (n % 2 == 0) {
            median = (array[n / 2 - 1] + array[n / 2]) / 2;
        } else {
            median = array[n / 2];
        }

        // Calculate the cost to convert all elements to the median
        int cost = 0;
        for (int num : array) {
            cost += Math.abs(num - median);
        }

        return cost;
    }

//Remove n number of duplicates from a string 
    public static String removeNDups(String entry, int dups){
        Stack<String> s = new Stack<String>();
        String[] c = entry.split("");
        for(int i = 0; i < c.length; i++){
            s.add(c[i]);
            if(s.size() >= dups){
                boolean equals = true;
                for(int x = s.size() - dups; x < s.size(); x++){
                    if(c[i] != s.pop()){
                        equals = false;
                    }
                }
                if(equals){
                    for(int x = 0; x < dups; x++){
                        s.pop();
                    }
                }
            }
        }
        return s.stream().toString();
    }

//MinCore Problem
    public static int minCoresNeeded(int[] startTimes, int[] endTimes){
        int n = startTimes.length;
        int best = 0, current = 0;
        int s = 0, e = 0;
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);
        while(s < n){
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

//Stable Gene Problem
    public static int stableGene(String gene){
        char[] d = {'A', 'C', 'G', 'T'};
        int n = gene.length();
        int target = n / 4;
        int[] freq = new int[128];
        for(int i = 0; i < gene.length(); i++){
            freq[gene.charAt(i)]++;
        }
        
        int[] needs = new int[128];
        int totalN = 0;

        for(char c : d){
            needs[c] = Math.max(0, freq[c] - target);
            totalN += needs[c];
        }

        int ans = 0, l = 0;

        for(int i = 0; i < gene.length(); i++){
            char ch = gene.charAt(i);
            if(needs[ch] > 0){
                needs[ch]--;
            }
            totalN--;
            while(totalN == 0){
                ans = Math.min(ans, i - l + 1);
                char cl = gene.charAt(l++);
                needs[cl]++;
                if(needs[cl] > 0){
                    totalN++;
                }
            }
        }
        return ans;


    }
}
