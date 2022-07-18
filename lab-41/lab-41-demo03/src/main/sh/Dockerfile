# 该镜像需要依赖的基础镜像
FROM openjdk:8-jdk-alpine
# 容器卷挂载
VOLUME /tmp
# lib 包复制到容器里去
COPY target/lib/ ./lib/
# 将当前目录下的 jar 包复制到 docker 容器的/目录下
ADD target/lab-41-demo03.jar /lab-41-demo03.jar
# RUN sh -c 'touch /lab-41-demo03.jar'
# 声明服务运行在8079端口
EXPOSE 8079
# 环境设置
ENV JAVA_OPTS="-XX:MaxRAMPercentage=80.0 -Duser.timezone=Asia/Shanghai -Xmx500M -Xms500M -XX:MaxPermSize=102m"
ENV APP_OPTS=""
# 指定docker容器启动时运行jar包
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /lab-41-demo03.jar $APP_OPTS" ]
# 指定维护者的名字
MAINTAINER jaquez