package org.example.util;

import cern.jet.random.engine.DRand;
import cern.jet.random.engine.RandomEngine;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Poisson {

    private double lambda;
    private cern.jet.random.Poisson poisson;

    public Poisson(double lambda) {
        this.lambda = lambda;
        this.poisson = new cern.jet.random.Poisson(this.lambda, new DRand());
    }

    public int next() {
        return this.poisson.nextInt();
    }
}
