package org.example.model;

import lombok.Getter;
import org.example.util.Consts;

import java.util.LinkedList;

@Getter
public class AsyncM extends QueueM {

    public AsyncM(double lambda) {
        this.lambda = lambda;
        this.theory.setN(calculateN(lambda));
        this.theory.setD(calculateD(lambda));
    }

    @Override
    public void modeling() {
        LinkedList<Double> queue = new LinkedList<>();
        LinkedList<Integer> members = new LinkedList<>();
        LinkedList<Double> taskTime = new LinkedList<>();
        LinkedList<Integer> newTasks = this.generateNewTasks(this.lambda, Consts.selection);

        while (!newTasks.isEmpty()|| !queue.isEmpty()) {
            int newTaskCount = 0;
            if(!newTasks.isEmpty()) newTaskCount = newTasks.removeFirst();
//            boolean isEmpty = queue.isEmpty();
            for (int i = 0; i < newTaskCount; i++) {
//                if(isEmpty) {
                    queue.addLast(1.0);
//                } else {
//                    queue.addLast(Math.random());
//                }
            }
            members.addLast(queue.size());
            if (!queue.isEmpty()) taskTime.addLast(queue.removeFirst());
            queue.replaceAll(n -> n + 1.0);
        }
        practice.setN(members.stream().mapToDouble(x -> x).sum() / members.size());
        practice.setD(taskTime.stream().mapToDouble(x -> x).sum() / taskTime.size());
    }

}
