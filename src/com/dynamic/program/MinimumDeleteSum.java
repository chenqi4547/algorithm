package com.dynamic.program;

import java.util.Arrays;

/**
 * @Description : 712.最小 ASCII 删除和
 * @Author : chen qi
 * @Date: 2021-10-13 17:52
 */
public class MinimumDeleteSum {
    public static int[][] memo;

    public static int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(s1, 0, s2, 0);
    }

    // 定义：将 s1[i..] 和 s2[j..] 删除成相同字符串，
    public static int dp(String s1, int i, String s2, int j) {
        int res = 0;

        if (i == s1.length()) {
            // 如果 s1 到头了，那么 s2 剩下的都得删除
            for (; j < s2.length(); j++) {
                res += s2.charAt(j);
            }
            return res;
        }

        if (j == s2.length()) {
            // 如果 s2 到头了，那么 s1 剩下的都得删除
            for (; i < s1.length(); i++) {
                res += s1.charAt(i);
            }
            return res;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            // s1[i] 和 s2[j] 都是在 lcs 中的，不用删除
            memo[i][j] = dp(s1, i + 1, s2, j + 1);
        } else {
            // s1[i] 和 s2[j] 至少有一个不在 lcs 中，删一个
            memo[i][j] = Math.min(
                    s1.charAt(i) + dp(s1, i + 1, s2, j),
                    s2.charAt(j) + dp(s1, i, s2, j + 1)
            );
        }
        return memo[i][j];
    }

    public static void main(String[] args) {
        int i = minimumDeleteSum("sea", "eat");
        System.out.println(i);
        System.out.println('s' + 't');
    }
}
