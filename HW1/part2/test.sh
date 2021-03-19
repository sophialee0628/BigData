#!/bin/sh
../../start.sh
/usr/local/hadoop/bin/hdfs dfs -rm -r /part2/input/
/usr/local/hadoop/bin/hdfs dfs -rm -r /part2/output/
/usr/local/hadoop/bin/hdfs dfs -mkdir -p /part2/input/
/usr/local/hadoop/bin/hdfs dfs -copyFromLocal ../../mapreduce-test-data/access.log /part2/input/
/usr/local/hadoop/bin/hadoop jar /usr/local/hadoop/share/hadoop/tools/lib/hadoop-streaming-2.9.2.jar \
-file ../../mapreduce-test-python/part2/mapper.py -mapper "../../mapreduce-test-python/part2/mapper.py 0 1" \
-file ../../mapreduce-test-python/part2/reducer.py -reducer ../../mapreduce-test-python/part2/reducer.py \
-input /part2/input/* -output /part2/output/
/usr/local/hadoop/bin/hdfs dfs -cat /part2/output/part-00000
/usr/local/hadoop/bin/hdfs dfs -rm -r /part2/input/
/usr/local/hadoop/bin/hdfs dfs -rm -r /part2/output/
../../stop.sh
