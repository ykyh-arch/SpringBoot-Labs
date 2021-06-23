#基础镜像
FROM java:8-jre
#项目挂载的/tmp目录
VOLUME /tmp
#踩坑：路径即Dockerfile所在目录的相对路径
COPY target/lib ./lib/
#将项目的jar包拷贝并命名
#ADD target/lab-72-docker-demo-1.jar app.jar
ARG JAR_FILE
ADD target/${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8079 8078
#ENV JAVA_OPTS="-Duser.timezone=Asia/Shanghai"
ENV APP_OPTS="--spring.profiles.active=dev"
#设置时间，踩坑：这里的 \，注意结尾不能添加 \，\是拼接的意思
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
  && echo 'Asia/Shanghai' >/etc/timezone

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar $APP_OPTS" ]