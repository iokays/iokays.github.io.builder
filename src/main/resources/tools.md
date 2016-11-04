## 工具箱


### java

* <http://www.oracle.com/>
* tar -zxvf jdk-8u111-linux-x64.tar.gz
* vi /etc/profile
* export JAVA_HOME=/usr/local/jdk1.8.0_111
* export PATH=$PATH:$JAVA_HOME/bin
* source /etc/profile


### scala

* <http://www.scala-lang.org/>
* tar -zxvf scala-2.11.8.tgz
* vi /etc/profile
* export SCALA_HOME=/usr/local/scala-2.11.8 
* export PATH=$PATH:$SCALA_HOME/bin
* source /etc/profile


### Zookeeper

单机版

* <http://zookeeper.apache.org/>
* 解压直接修改 conf/zoo_simple.conf 为zoo.conf， 运行(bin/zkService.sh start, status, stop)即可.
* 客户端 bin/zkCli.sh 或 bin/zkCli.sh -server 127.0.0.1:2181
* 相关命令 ls,  create /key iokays, get /key 

### kafka

* <http://kafka.apache.org/>
* tar -zxvf kafka_2.11-0.10.1.0.tgz
* bin/zookeeper-server-start.sh -daemon config/zookeeper.properties
* bin/kafka-server-start.sh -daemon config/server.properties
* bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
* bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning

### kafka-manager

* https://github.com/yahoo/kafka-manager
* unzip kafka-manager-master.zip 
* vi conf/application.conf
* kafka-manager.zkhosts="192.168.0.230:2181
* ./sbt clean dist
* /usr/local/kafka-manager-master/target/universal/kafka-manager-1.3.2.1.zip






