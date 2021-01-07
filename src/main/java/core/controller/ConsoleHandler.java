package core.controller;

import core.model.Action;
import java.io.File;
import java.util.Scanner;

public class ConsoleHandler {
    public Action handle() {
        Action action = null;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            String[] pathData = command.split(" ");
            String pathFrom = pathData[0];
            String pathTo = pathData[1];
            action = new Action(new File(pathFrom), new File(pathTo));
        }
        return action;
    }
}
