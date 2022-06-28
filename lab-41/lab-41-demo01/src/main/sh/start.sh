#!/usr/bin/env bash

# 定义应用组名
group_name='lab'
# 定义应用名称
app_name='lab-41-demo01'
# 定义应用版本
app_version='1.0-SNAPSHOT'
# 定义应用环境
profile_active='local'
# 服务器远程拷贝文件
echo '----> 服务器远程拷贝文件成功 <----'
# 停止服务容器
if [[ "$(docker inspect ${app_name} 2> /dev/null | grep '"Name": "'/$app_name'"')" != "" ]];
	then
		docker stop ${app_name}
		echo '----> 服务容器已停止 <----'
		# 删除服务容器
		docker rm ${app_name}
		echo '----> 服务容器已删除 <----'
	else
		echo '----> 服务处理请稍后... <----'
fi
# 删除服务镜像
if [[ "$(docker images -q ${group_name}/${app_name}:${app_version} 2> /dev/null)" != "" ]]; 
	then
		docker rmi ${group_name}/${app_name}:${app_version}
fi		
# 打包编译 docker 镜像
docker build -t ${group_name}/${app_name}:${app_version} .
echo '----> 打包编译 docker 镜像 <----'
# 启动服务容器
docker run -p 8080:8079 --name ${app_name} \
-e 'spring.profiles.active'=${profile_active} \
-e TZ="Asia/Shanghai" \
-v /etc/localtime:/etc/localtime \
-v /work/projects/lab-41-demo/${app_name}/logs:/var/logs \
-d ${group_name}/${app_name}:${app_version}
# --link mysql:db \ # 容器互通
echo '----> 服务容器启动 <----'