### Install Redis

``` text
### redis-stack contains redis and redis-insight (GUI)
docker run -d --name redis-stack \
  -p 6379:6379 \
  -p 8001:8001 \
  -e REDIS_ARGS="--requirepass 123456" \
  redis/redis-stack:latest
```


### Install MySql

```text
docker run -d \
  --name mysql \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=123 \
  -v ./mysql/data:/var/lib/mysql \
  -v ./mysql/conf:/etc/mysql/conf.d \
  mysql:8.0.36
```

### Install Nacos

```text
docker  run \
--name nacos -d \
-p 8848:8848 \
-p 9848:9848 \
-p 9849:9849 \
--privileged=true \
--restart=always \
-e JVM_XMS=256m \
-e JVM_XMX=256m \
-e MODE=standalone \
-e PREFER_HOST_MODE=hostname \
-v ./nacos/logs/:/home/nacos/logs \
-v ./nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties \
nacos/nacos-server
```

### Install RabbitMQ

```text
docker run \
 -e RABBITMQ_DEFAULT_USER=itheima \
 -e RABBITMQ_DEFAULT_PASS=123321 \
 -v mq-plugins:/plugins \
 --name mq \
 --hostname mq \
 -p 15672:15672 \
 -p 5672:5672 \
 --network hm-net\
 -d \
 rabbitmq
```