package com.example.zkapi;


import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class CreateSession implements Watcher {

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws InterruptedException,IOException {
        try {
            zooKeeper = new ZooKeeper("192.168.74.114:2181",500,new CreateSession());
            System.out.println(zooKeeper.getState());
            Thread.sleep(Integer.MAX_VALUE);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("接收到的事件：" + watchedEvent);
    }
}
