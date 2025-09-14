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
    public static String removeNDups(String s, int d){
        int n = s.length();
        int[] cnts = new int[n];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append(s.charAt(i));
            int j = sb.length() - 1;
            cnts[j] = 1 + (j > 0 && sb.charAt(j - 1) == s.charAt(i) ? cnts[j - 1] : 0);
            if (cnts[j] == d) {
                sb.setLength(sb.length() - d);
            }
        }
        return sb.toString();
    }

//MinCore Problem
    public static int minCoresNeeded(int[] startTimes, int[] endTimes){
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);
        int s = 0, e = 0, best = 0, current = 0;
        while(s < startTimes.length){
            if(startTimes[s] < endTimes[e]){
                current++;
                s++;
                best = Math.max(best, current);
            } else {
                current--;
                e++;
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
