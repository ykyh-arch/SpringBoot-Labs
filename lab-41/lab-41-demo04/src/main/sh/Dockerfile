# 该镜像需要依赖的基础镜像
FROM openjdk:8-jdk-alpine
# 指定维护者的名字
MAINTAINER jaquez
# 容器卷挂载
VOLUME /tmp
# lib 包复制到容器里去
COPY target/lib/ ./lib/
# 将当前目录下的 jar 包复制到 docker 容器的/目录下
ADD target/lab-41-demo04.jar /lab-41-demo04.jar
# RUN sh -c 'touch /lab-41-demo03.jar'
# 声明服务运行在 8079 端口
EXPOSE 8079
# 环境设置
ENV JAVA_OPTS="-XX:MaxRAMPercentage=80.0 -Duser.timezone=Asia/Shanghai -Xmx128M -Xms128M -XX:MaxPermSize=50m"
ENV APP_OPTS=""
# 指定 docker 容器启动时运行 jar 包
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /lab-41-demo04.jar $APP_OPTS" ]