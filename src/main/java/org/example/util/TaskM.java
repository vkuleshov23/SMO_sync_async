package org.example.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.model.QueueM;

@Getter
@Setter
@RequiredArgsConstructor
public class TaskM extends Thread {

    private final QueueM model;

    @Override
    public void run() {
        model.modeling();
    }
}
