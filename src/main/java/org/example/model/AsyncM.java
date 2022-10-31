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
        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<Integer> members = new LinkedList<>();
        LinkedList<Integer> taskTime = new LinkedList<>();
        LinkedList<Integer> newTasks = this.generateNewTasks(this.lambda, Consts.selection);

        while (newTasks.size() > 0 || queue.size() > 0) {
            int newTaskCount = 0;
            if(newTasks.size() > 0) newTaskCount = newTasks.removeFirst();
            for (int i = 0; i < newTaskCount; i++) queue.addLast(0);
            queue.replaceAll(integer -> integer + 1);
            members.addLast(queue.size());
            if (queue.size() > 0) taskTime.addLast(queue.removeFirst());
        }
        practice.setN(members.stream().mapToDouble(x -> x).sum() / members.size());
        practice.setD(taskTime.stream().mapToDouble(x -> x).sum() / taskTime.size());
    }

}
