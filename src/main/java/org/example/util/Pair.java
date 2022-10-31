package org.example.util;

import lombok.Setter;

@Setter
public class Pair {
    private Double n;
    private Double d;

    public Pair(Double n, Double d) {
        this.n = n;
        this.d = d;
    }

    public Double getN() {
        return Double.isNaN(this.n) ? 0.0 : this.n;
//        return this.n;
    }

    public Double getD() {
        return Double.isNaN(this.d) ? 0.0 : this.d;
//        return this.d;
    }
}
