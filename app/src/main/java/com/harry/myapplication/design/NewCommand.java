package com.harry.myapplication.design;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
public class NewCommand implements Command {
    private NewHandler mHandler = new NewHandler();

    @Override
    public void execute() {
        mHandler.getList();
    }

    static class NewHandler {
        public void getList() {
            // 获取列表数据
        }
    }
}
