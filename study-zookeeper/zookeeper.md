# Zookeeper

# Zookeeper in Docker
docker search zookeeper
docker pull 
启动docker容器中的Zookeeper实例：docker run --name myzookeeper -p 2181:2181 --restart always -d zookeeper
从另一个docker容器中的应用程序连接到Zookeeper：docker run --name myapp --link myzookeeper:zookeeper -d application-that-uses-zookeeper
从Zookeeper命令行客户端连接到Zookeeper：docker run -it --rm --link myzookeeper:zookeeper zookeeper zkCli.sh -server zookeeper
(因为刚才我们启动的那个 ZK 容器并没有绑定宿主机的端口, 因此我们不能直接访问它. 但是我们可以通过 Docker 的 link 机制来对这个 ZK 容器进行访问)
查看docker中zookeeper的日志：docker logs -f myzookeeper
# Zookeeper Clusters in Docker 
使用docker-compose.yml 
文件内容如下
version: '2'
services:
    zoo1:
        image: zookeeper
        restart: always
        container_name: zoo1
        ports:
            - "2181:2181"
        environment:
            ZOO_MY_ID: 1
            ZOO_SERVERS: server.1=zoo1:2888:3888 server.2=zoo2:2888:3888 server.3=zoo3:2888:3888

    zoo2:
        image: zookeeper
        restart: always
        container_name: zoo2
        ports:
            - "2182:2181"
        environment:
            ZOO_MY_ID: 2
            ZOO_SERVERS: server.1=zoo1:2888:3888 server.2=zoo2:2888:3888 server.3=zoo3:2888:3888

    zoo3:
        image: zookeeper
        restart: always
        container_name: zoo3
        ports:
            - "2183:2181"
        environment:
            ZOO_MY_ID: 3
            ZOO_SERVERS: server.1=zoo1:2888:3888 server.2=zoo2:2888:3888 server.3=zoo3:2888:3888

然后再docker-compose.yml所在目录运行COMPOSE_PROJECT_NAME=zk_test docker-compose up
使用 Docker 命令行客户端连接 ZK 集群
docker run -it --rm --link zoo1:zk1 --link zoo2:zk2 --link zoo3:zk3 --net zktest_default zookeeper zkCli.sh -server zk1:2181,zk2:2181,zk3:2181
通过本地主机连接 ZK 集群：zkCli.sh -server localhost:2181,localhost:2182,localhost:2183
查看集群：echo stat | nc 127.0.0.1 2181
