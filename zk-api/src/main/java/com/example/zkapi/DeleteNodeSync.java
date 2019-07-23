package com.example.zkapi;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class DeleteNodeSync implements Watcher {
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws InterruptedException,IOException {
        try {
            zooKeeper = new ZooKeeper("192.168.74.114:2181",500,new DeleteNodeSync());
            System.out.println(zooKeeper.getState());
            Thread.sleep(Integer.MAX_VALUE);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
            if(watchedEvent.getType() == Event.EventType.None && null ==watchedEvent.getPath()){
                executeZK();
            }
        }
    }


    public void executeZK(){
        System.out.println("执行zookeeper操作");
        try{
            zooKeeper.delete("/node1",-1);
        }catch (KeeperException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
