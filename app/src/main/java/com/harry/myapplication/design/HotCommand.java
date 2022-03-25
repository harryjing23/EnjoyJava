package com.harry.myapplication.design;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
public class HotCommand implements Command {
    private HotHandler mHandler = new HotHandler();

    @Override
    public void execute() {
        mHandler.getList();
    }

    static class HotHandler {
        public void getList() {
            // 获取列表数据
        }
    }
}
