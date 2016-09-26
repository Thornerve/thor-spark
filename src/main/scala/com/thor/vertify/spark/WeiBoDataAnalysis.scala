package com.thor.vertify.spark

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by 1b-dell on 2016/9/22.
  */
object WeiBoDataAnalysis {
  def FILE_NAME:String = "C:\\Users\\1b-dell\\Desktop\\word_count_results_";
  var file = "C:\\Users\\1b-dell\\Desktop\\fresh_data_history_table_1.txt";
  def main(args:Array[String]) {
    if (args.length < 1) {
      println("Usage:SparkWordCount FileName");
    }else{
      file = args(0);
    }
    val conf = new SparkConf().setAppName("Spark Test").setMaster("local");
    val sc = new SparkContext(conf);
    val sqlContext = new org.apache.spark.sql.SQLContext(sc);
    val weibo = sqlContext.read.json(file);
    // The inferred schema can be visualized using the printSchema() method.
    weibo.printSchema();
//    root
//    |-- crawler_time: string (nullable = true)
//    |-- crawler_time_stamp: long (nullable = true)
//    |-- device: string (nullable = true)
//    |-- id: long (nullable = true)
//    |-- is_retweet: long (nullable = true)
//    |-- locate: string (nullable = true)
//    |-- nick_name: string (nullable = true)
//    |-- ping: string (nullable = true)
//    |-- r_device: string (nullable = true)
//    |-- r_location: string (nullable = true)
//    |-- r_nick_name: string (nullable = true)
//    |-- r_ping: string (nullable = true)
//    |-- r_time: string (nullable = true)
//    |-- r_time_stamp: string (nullable = true)
//    |-- r_url: string (nullable = true)
//    |-- r_user_id: string (nullable = true)
//    |-- r_user_type: string (nullable = true)
//    |-- r_weibo_content: string (nullable = true)
//    |-- r_weibo_id: string (nullable = true)
//    |-- r_zhan: string (nullable = true)
//    |-- r_zhuan: string (nullable = true)
//    |-- time: string (nullable = true)
//    |-- time_stamp: long (nullable = true)
//    |-- tou_xiang: string (nullable = true)
//    |-- url: string (nullable = true)
//    |-- user_id: string (nullable = true)
//    |-- user_type: string (nullable = true)
//    |-- weibo_content: string (nullable = true)
//    |-- weibo_id: long (nullable = true)
//    |-- zhan: string (nullable = true)
//    |-- zhuan: string (nullable = true)


    // Register this DataFrame as a table.
    weibo.registerTempTable("weibo");

    // SQL statements can be run by using the sql methods provided by sqlContext.
    val results = sqlContext.sql("SELECT nick_name FROM weibo");
    results.map(t => "Name: " + t(0)).collect().foreach(println);


  }
}
