package com.example.hbaseapi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PutData {
    public static void main(String[] args) throws IOException {
        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "192.168.74.114");
        config.set("hbase.zookeeper.property.clientPort", "2182");
        HTable table = new HTable(config, "phoneurl");
        Put put1 = new Put(Bytes.toBytes("15902345350"));
        put1.add(Bytes.toBytes("baseInfo"), Bytes.toBytes("rul"),
                Bytes.toBytes("www.ifeng.com"));
        Put put2 = new Put(Bytes.toBytes("15902345350"));
        put1.add(Bytes.toBytes("baseInfo"), Bytes.toBytes("rul"),
                Bytes.toBytes("www.so.com"));
        Put put3 = new Put(Bytes.toBytes("15902345350"));
        put1.add(Bytes.toBytes("baseInfo"), Bytes.toBytes("rul"),
                Bytes.toBytes("www.bing.com"));
        List<Put> putlist = new ArrayList<Put>();
        putlist.add(put1);
        putlist.add(put2);
        putlist.add(put3);
        table.put(putlist);
        table.close();
    }
}
