FROM registry.cn-shanghai.aliyuncs.com/gaia_public/java:debian1013-openJDK8-382

MAINTAINER yann

# 以下挪到了基础镜像 debian1013-openJDK8-382 中-----------------------begin
# RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# RUN apt-get update
# RUN apt-get install -y procps
# RUN apt-get install -y vim
# RUN apt-get install -y curl
# RUN apt-get install -y xinetd
# RUN apt-get install -y telnet
# RUN apt-get install -y less
# RUN apt-get install -y net-tools

# RUN echo "# define ll alias" >> /root/.bashrc
# RUN echo "alias ll='ls --color=auto -lrth'" >> /root/.bashrc
# ----------------------------------------------------------------end

RUN touch /etc/locale.conf
RUN echo "LANG='C.UTF-8'" >> /etc/locale.conf

RUN echo "  " >> /root/.bashrc
RUN echo "export LANG='UTF-8'" >> /root/.bashrc
RUN echo "export LC_ALL='C.UTF-8'" >> /root/.bashrc
RUN echo "export LC_CTYPE='C.UTF-8'" >> /root/.bashrc

RUN mkdir -p /opt/microservice/grpc-server

RUN mkdir -p /opt/microservice/log/grpc-server

COPY grpc-server.jar /opt/microservice/grpc-server

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

EXPOSE 9911

EXPOSE 9822

# 目前这个镜像里面添加了 skywalking 的依赖，但是用不用可以根据传入的 JVM_OPTS 来决定
# 如果需要使用 skywalking，那么 JVM_OPTS 添加参数：-DSW_AGENT_NAME=指定SK名字 -DSW_AGENT_COLLECTOR_BACKEND_SERVICES=指定SK地址 -javaagent:/opt/microservice/skywalking/agent/skywalking-agent.jar

# JVM_OPTS ：-Xms2048m -Xmx2048m -Duser.timezone=GMT+08 -Dreactor.netty.pool.leasingStrategy=lifo -Dspring.cloud.gateway.httpclient.pool.maxIdleTime=19000
# ADD_PROFILE_PATH ：classpath:config/gateway/
# RUN_ENV ：gateway-s-k8s
# DEPLOYED_IN_CLUSTER ：ali-cn，给日志用的
# DEPLOYED_POD_NAME ：给日志用的

CMD ["sh","-c","java ${JVM_OPTS} -jar /opt/microservice/grpc-server/grpc-server.jar --deployed.in.cluster=${DEPLOYED_IN_CLUSTER} --deployed.pod.name=${DEPLOYED_POD_NAME}"]
