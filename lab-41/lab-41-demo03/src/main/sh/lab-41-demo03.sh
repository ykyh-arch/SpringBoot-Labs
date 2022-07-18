#!/usr/bin/env bash

echo '----> 服务器远程同步文件成功 <----'

# 定义应用组名
group_name='lab'
# 定义应用名称
app_name='lab-41-demo03'
# 定义应用版本
app_version='v1'

docker build -t ${group_name}/${app_name}:${app_version} .

# 打标签
docker tag ${group_name}/${app_name}:${app_version} 192.168.177.6:5000/${group_name}/${app_name}:${app_version}

# 推送镜像到仓库
docker push 192.168.177.6:5000/${group_name}/${app_name}:${app_version}

kubectl apply -f lab-41-demo03.yaml

echo '----> 部署成功 <----'