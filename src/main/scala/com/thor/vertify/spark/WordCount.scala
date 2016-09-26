package com.thor.vertify.spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

/**
  * Created by 1b-dell on 2016/9/21.
  */
object WordCount {
    def FILE_NAME:String = "word_count_results_";
    var file = "C:\\Users\\1b-dell\\Desktop\\fresh_data_history_table_1.txt";
    def main(args:Array[String]) {
        if (args.length < 1) {
            println("Usage:SparkWordCount FileName");
        }else{
            file = args(0);
        }
        val conf = new SparkConf().setAppName("Spark Exercise: Spark Version Word Count Program").setMaster("local");
        val sc = new SparkContext(conf);
        val textFile = sc.textFile(file);
        val wordCounts = textFile.flatMap(line => line.split(" ")).map( word => (word, 1)).reduceByKey((a, b) => a + b)
        val count = wordCounts.count();
        println("-----------------------------------" + count)
        //print the results,for debug use.
        //println("Word Count program running results:");
        //wordCounts.collect().foreach(e => {
        //val (k,v) = e
        //println(k+"="+v)
        //});
//        wordCounts.saveAsTextFile(FILE_NAME+System.currentTimeMillis());
        println("Word Count program running results are successfully saved.");
    }
}
