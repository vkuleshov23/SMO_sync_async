package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.util.Pair;
import org.example.util.Poisson;

import java.util.LinkedList;

@Getter
@Setter
public abstract class QueueM {

    protected double lambda;
    protected Pair theory = new Pair(0.0, 0.0);
    protected Pair practice = new Pair(0.0, 0.0);

    protected Pair result(boolean isPractice) {
        if(isPractice)
            return practice;
        else
            return theory;
    }

    protected double calculateN(double lambda) {
        if (lambda == 1) {
            return 1;
        }
        return (lambda * ((2 - lambda))) / (2 * (1 - lambda));
    }

    protected double calculateD(double lambda) {
        return this.calculateN(lambda) / lambda;
    }

    protected LinkedList<Integer> generateNewTasks(double lambda, int k) {
        Poisson poisson = new Poisson(lambda);
        LinkedList<Integer> newTasks = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            newTasks.addLast(poisson.next());
        }
        return newTasks;
    }


    public abstract void modeling();
}
