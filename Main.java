package com.ubs.wma.cdx.account.summary.service;

import com.ubs.wma.cdx.account.summary.utils.BoxPermutation;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        BoxPermutation[] generatedBoxes = new BoxPermutation[3*n];
        for (int i = 0; i < n; ++i) {

            int w = scanner.nextInt();
            int d = scanner.nextInt();
            int h = scanner.nextInt();

            BoxPermutation permutation1 = new BoxPermutation(w, d, h);
            BoxPermutation permutation2 = new BoxPermutation(w, h, d);
            BoxPermutation permutation3 = new BoxPermutation(d, h, w);

            generatedBoxes[3*i] = permutation1;
            generatedBoxes[3*i + 1] = permutation2;
            generatedBoxes[3*i + 2] = permutation3;
        }

        Arrays.sort(generatedBoxes);

        System.out.println(Arrays.toString(generatedBoxes));

        calculateHeight(generatedBoxes);
    }

    private static void calculateHeight(BoxPermutation[] boxes) {

        int n = boxes.length;

        int[] memo = new int[n];

        for (int i = 0; i < n; ++i) {
            int maxHeight = -1;
            for (int j = i - 1; j >= 0; --j) {
                if (isBigger(boxes[j], boxes[i])) {
                    if (maxHeight < memo[j]) {
                        maxHeight = memo[j];
                    }
                }
            }
            if (maxHeight == -1) {
                memo[i] = boxes[i].h;
            } else {
                memo[i] = maxHeight + boxes[i].h;
            }
        }

        OptionalInt max = Arrays.stream(memo).max();
        System.out.println(max.orElse(-1));
    }

    private static boolean isBigger(BoxPermutation b1, BoxPermutation b2) {
        int c1 = Integer.compare(b1.w, b2.w);
        int c2 = Integer.compare(b1.d, b2.d);
        int c3 = Integer.compare(b1.w, b2.d);
        int c4 = Integer.compare(b1.d, b2.w);

        if (c1 > 0 && c2 > 0 || c3 > 0 && c4 > 0) {
            return true;
        }

        return false;
    }
}
