package com.harry.myapplication.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
public class HomeList {

    private List<Command> mCommands = new ArrayList<>();

    public void setCommand(Command command) {
        mCommands.add(command);
    }

    private void getList() {
        for (Command command : mCommands) {
            command.execute();
        }
    }

    private void test() {
        HomeList homeList = new HomeList();
        // 根据需求可自由设置Command
        homeList.setCommand(new HotCommand());
        homeList.setCommand(new NewCommand());
        homeList.getList();
    }
}
