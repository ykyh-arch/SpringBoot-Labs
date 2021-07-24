测试Docker
1.利用Maven插件打包时构建镜像并运行容器
2.利用Maven插件将boot项目打包推送到Harbor私有仓库
参考：https://www.cnblogs.com/andrew3/p/13043042.html
     docker打包调优：https://www.cnblogs.com/sanduzxcvbnm/p/13807767.html
     Dockerfile保留关键字：https://www.cnblogs.com/ZCQ123456/p/11918470.html，https://www.cnblogs.com/andrew3/p/13033061.html
     私服相关：https://www.cnblogs.com/binghe001/p/12810675.html，https://www.cnblogs.com/bhfdz/p/13402288.html
     安装Harbor：https://www.pianshen.com/article/61221458983/，https://github.com/spotify/dockerfile-maven
     利用fabric8插件推送到私服：https://www.cnblogs.com/aliases/p/14333208.html，https://blog.csdn.net/qq_40942490/article/details/114652237
     推送到私服指令：mvn clean package docker:build && mvn docker:push
