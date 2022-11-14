package org.example.service;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PyCommand;
import com.github.sh0nk.matplotlib4j.PythonConfig;
import lombok.SneakyThrows;
import org.example.model.QueueM;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Plotter {

    @SneakyThrows
    public void plotD(List<QueueM> queueMS, String title, String ylabel) {
        List<Double> x = queueMS.stream().map(QueueM::getLambda).collect(Collectors.toList());
        List<Double> Dt = queueMS.stream().map(queueM -> queueM.getTheory().getD()).collect(Collectors.toList());
        List<Double> Dp = queueMS.stream().map(queueM -> queueM.getPractice().getD()).collect(Collectors.toList());

        Plot plt = Plot.create();
        plt.plot().add(x, Dp, "-").label("Practice");
        plt.plot().add(x, Dt, "-").label("Theory");
        plt.title(title);
        plt.xlabel("$lambda$");
        plt.ylabel(ylabel);
        plt.legend();
        plt.show();
    }

    @SneakyThrows
    public void plotN(List<QueueM> queueMS, String title, String ylabel) {
        List<Double> x = queueMS.stream().map(QueueM::getLambda).collect(Collectors.toList());
        List<Double> Nt = queueMS.stream().map(queueM -> queueM.getTheory().getN()).collect(Collectors.toList());
        List<Double> Np = queueMS.stream().map(queueM -> queueM.getPractice().getN()).collect(Collectors.toList());

        Plot plt = Plot.create();
        plt.plot().add(x, Np, "-").label("Practice");
        plt.plot().add(x, Nt, "-").label("Theory");
        plt.title(title);
        plt.xlabel("$lambda$");
        plt.ylabel(ylabel);
        plt.legend();
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
