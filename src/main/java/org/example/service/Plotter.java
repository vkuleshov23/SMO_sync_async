package org.example.service;

import com.github.sh0nk.matplotlib4j.Plot;
import lombok.SneakyThrows;
import org.example.model.AsyncM;
import org.example.model.QueueM;
import org.example.model.SyncM;
import org.example.util.Consts;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class Plotter {

    @SneakyThrows
    public void plotD(List<QueueM> queueMS, String title, String ylabel) {
        List<Double> x = queueMS.stream().map(QueueM::getLambda).toList();
        List<Double> Dt = queueMS.stream().map(queueM -> queueM.getTheory().getD()).toList();
        List<Double> Dp = queueMS.stream().map(queueM -> queueM.getPractice().getD()).toList();

        Plot plt = Plot.create();
        plt.plot().add(x, Dp, "-").label("Practice").linewidth(5);
        plt.plot().add(x, Dt, "-").label("Theory");
        plt.title(title);
        plt.xlabel("$lambda$");
        plt.ylabel(ylabel);
        plt.show();
    }

    @SneakyThrows
    public void plotN(List<QueueM> queueMS, String title, String ylabel) {
        List<Double> x = queueMS.stream().map(QueueM::getLambda).toList();
        List<Double> Nt = queueMS.stream().map(queueM -> queueM.getTheory().getN()).toList();
        List<Double> Np = queueMS.stream().map(queueM -> queueM.getPractice().getN()).toList();

        Plot plt = Plot.create();
        plt.plot().add(x, Np, "-").label("Practice").linewidth(5);
        plt.plot().add(x, Nt, "-").label("Theory");
        plt.title(title);
        plt.xlabel("$lambda$");
        plt.ylabel(ylabel);
        plt.show();
    }

    @SneakyThrows
    public void plotAD(List<QueueM> queueMS) {
        plotD(queueMS, "Asynchronous model", "M[D]");
    }

    @SneakyThrows
    public void plotAN(List<QueueM> queueMS) {
        plotN(queueMS, "Asynchronous model", "M[N]");
    }

    @SneakyThrows
    public void plotSD(List<QueueM> queueMS) {
        plotD(queueMS, "Synchronous model", "M[D]");
    }

    @SneakyThrows
    public void plotSN(List<QueueM> queueMS) {
        plotN(queueMS, "Synchronous model", "M[N]");
    }


}
