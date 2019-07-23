package com.example.zkapi;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

public class CreateNodeASync implements Watcher{
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws InterruptedException,IOException {
        try {
            zooKeeper = new ZooKeeper("192.168.74.114:2181",500,new CreateNodeASync());
            System.out.println(zooKeeper.getState());
            Thread.sleep(Integer.MAX_VALUE);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("回调事件类型："+watchedEvent.getType());
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
            if(watchedEvent.getType() == Event.EventType.None && null ==watchedEvent.getPath()){
                try {
                    List<String> list = zooKeeper.getChildren("/",true);
                    System.out.println(list);
                }catch (KeeperException e){
                    e.printStackTrace();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }else {
            if(watchedEvent.getType() == Event.EventType.NodeChildrenChanged){
                try{
                    List<String> list = zooKeeper.getChildren("/",true);
                    System.out.println(list);
                }catch (KeeperException e){
                    e.printStackTrace();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
