package com.ubs.wma.cdx.account.summary.utils;

public class BoxPermutation implements Comparable<BoxPermutation> {

    public int w;
    public int d;
    public int h;

    public BoxPermutation(int w, int d, int h) {
        this.w = w;
        this.d = d;
        this.h = h;
    }


    @Override
    public int compareTo(BoxPermutation o) {
        return -Integer.compare(w * d, o.w * o.d);
    }

    @Override
    public String toString() {
        return "BoxPermutation{" +
                "w=" + w +
                ", d=" + d +
                ", h=" + h +
                '}';
    }
}
