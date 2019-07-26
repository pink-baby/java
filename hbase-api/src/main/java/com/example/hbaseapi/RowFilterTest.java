package com.example.hbaseapi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class RowFilterTest {


    public static void main(String[] args) throws IOException {
        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "192.168.74.114");
        config.set("hbase.zookeeper.property.clientPort", "2182");
        HTable hTable = new HTable(config,"user");
        Scan scan =  new Scan();
        System.out.println("row key 小于rk0003 的行");
        Filter filter = new RowFilter(CompareFilter.CompareOp.LESS_OR_EQUAL,
                new BinaryComparator("rk0003".getBytes()));
        scan.setFilter(filter);
        ResultScanner scanner = hTable.getScanner(scan);
        for (Result result : scanner) {
            System.out.println(result+"==>"+ Bytes.toString(result.getValue(Bytes.toBytes("info"),Bytes.toBytes("name"))));
        }
        scanner.close();
    }
}
