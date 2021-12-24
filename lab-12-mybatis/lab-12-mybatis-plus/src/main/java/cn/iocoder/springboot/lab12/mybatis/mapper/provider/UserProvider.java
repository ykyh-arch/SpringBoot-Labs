package cn.iocoder.springboot.lab12.mybatis.mapper.provider;

import cn.iocoder.springboot.lab12.mybatis.dataobject.UserDO;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 用户提供类
 *
 * @author jaquez
 * @date 2021/12/23 18:08
 **/
public class UserProvider {

    /**
     * 批量插入sql
     * @param list
     * @return
     */
    public String insertListSql(List<UserDO> list) {

        StringBuffer sqlList = new StringBuffer();
        sqlList.append("INSERT INTO users(username, password, create_time)  VALUES ");
        for (int i = 0; i < list.size() ; i++) {
            UserDO user = list.get(i);
            sqlList.append(" (")
                   .append("'").append(user.getUsername()).append("',")
                   .append("'").append(user.getPassword()).append("',")
                   .append("'").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(user.getCreateTime())).append("'")
                   .append(")");
            if (i < list.size()-1) {
                sqlList.append(",");
            }
        }
        // System.out.println("sql 预览：\n"+sqlList.toString());
        return sqlList.toString();
    }

}
