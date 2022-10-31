package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.AsyncM;
import org.example.model.SyncM;
import org.example.util.Consts;
import org.example.util.TaskM;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Modeling {

    private final Plotter plotter;

    @PostConstruct
    public void modeling() {

        List<TaskM> syncMS = generateSync();
        List<TaskM> asyncMS = generateAsync();

        asyncMS.forEach(TaskM::run);
        syncMS.forEach(TaskM::run);

        asyncMS.forEach(task -> {try {task.join();} catch (Exception ignored) {}});
        syncMS.forEach(task -> {try {task.join();} catch (Exception ignored) {}});

        this.plotA(asyncMS);
        this.plotS(syncMS);
    }

    private void plotA(List<TaskM> queueMS) {
        plotter.plotAD(queueMS.stream().skip(1).map(TaskM::getModel).toList());
        plotter.plotAN(queueMS.stream().map(TaskM::getModel).toList());
    }

    private void plotS(List<TaskM> queueMS) {
        plotter.plotSD(queueMS.stream().skip(1).map(TaskM::getModel).toList());
        plotter.plotSN(queueMS.stream().map(TaskM::getModel).toList());
    }

    private List<TaskM> generateSync() {
        List<TaskM> syncMS = new LinkedList<>();
        double lambda = Consts.lambdaMin;
        while (lambda <= Consts.lambdaMax) {
            syncMS.add(new TaskM(new SyncM(lambda)));
            lambda += Consts.lambdaStep;
        }
        return syncMS;
    }

    private List<TaskM> generateAsync() {
        List<TaskM> asyncMS = new LinkedList<>();
        double lambda = Consts.lambdaMin;
        while (lambda <= Consts.lambdaMax) {
            asyncMS.add(new TaskM(new AsyncM(lambda)));
            lambda += Consts.lambdaStep;
        }
        return asyncMS;
    }
}
