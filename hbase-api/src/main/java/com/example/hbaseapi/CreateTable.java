package com.example.hbaseapi;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.HBaseAdmin;


import java.io.IOException;

public class CreateTable {
    public static void main(String[] args) throws IOException {
        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "192.168.74.114");
        config.set("hbase.zookeeper.property.clientPort", "2182");
        HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf("phoneurl"));
        HColumnDescriptor columnDesc1 = new HColumnDescriptor("baseInfo");
        columnDesc1.setMaxVersions(5);
        tableDesc.addFamily(columnDesc1);
        HBaseAdmin hBaseAdmin = new HBaseAdmin(config);
        hBaseAdmin.createTable(tableDesc);
        hBaseAdmin.close();
    }
}
