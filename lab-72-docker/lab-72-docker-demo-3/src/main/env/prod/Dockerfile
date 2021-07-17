#基础镜像
FROM frolvlad/alpine-oraclejdk8:slim
#作者
MAINTAINER Jaquez@email.com
#根目录
RUN mkdir -p /soft/work/application/
#工作目录
WORKDIR /soft/work/application/
#踩坑：相对路径
COPY ./target/lib ./lib/
#将项目的JAR包拷贝并命名
ARG JAR_FILE
ADD ./target/${JAR_FILE} ./
EXPOSE 8079
ENV APP_OPTS="--spring.profiles.active=prod"
ENV JAVA_OPTS="-Xmx1024m"
#设置时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
#运行服务
CMD java ${JJAVA_OPTS} $APP_OPTS -Djava.security.egd=file:/dev/./urandom -jar ${JAR_FILE}