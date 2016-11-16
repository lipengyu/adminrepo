package com.lauparr.wrapper.output;

import com.lauparr.websocket.project.ProjectLogHandler;
import org.apache.commons.exec.LogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lauparr on 15/11/2016.
 */
public class CollectingLogOutputStream extends LogOutputStream {

    private final List<String> lines = new LinkedList<String>();

    @Override
    protected void processLine(String line, int level) {
        lines.add(line);
    }

    public List<String> getLines() {
        return lines;
    }
}