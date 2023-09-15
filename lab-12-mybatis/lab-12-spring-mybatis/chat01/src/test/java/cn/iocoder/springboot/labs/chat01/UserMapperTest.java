package cn.iocoder.springboot.labs.chat01;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * UserMapperTest
 *
 * @author fw001
 * @date 2023/09/15 17:02
 **/
@Slf4j
public class UserMapperTest {

    // 动态插入
    @Test
    public void insert() throws Exception {
        UserModel userModel1 = UserModel.builder().name("路人甲Java").build();
        // 用具类实现数据库操作
        UserUtil.callMapper(UserMapper.class, mapper -> {
            mapper.insert(userModel1);
            return null;
        });
        log.info("插入结果：{}", this.getModelById(userModel1.getId()));
        log.info("---------------------");
        UserModel userModel2 = UserModel.builder().name("路人").age(30).salary(50000.00).build();
        UserUtil.callMapper(UserMapper.class, mapper -> {
            mapper.insert(userModel2);
            return null;
        });
        log.info("插入结果：{}", this.getModelById(userModel2.getId()));
    }

    // 批量插入
    @Test
    public void insertBatch() throws Exception {
        List<UserModel> userModelList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            userModelList.add(UserModel.builder().name("路人甲Java-" + i).age(30 + i).salary(10000.00 * i).build());
            userModelList.add(UserModel.builder().name("javacode2018-" + i).age(30 + i).salary(10000.00 * i).build());
        }
        UserUtil.callMapper(UserMapper.class, mapper -> {
            mapper.insertBatch(userModelList);
            return null;
        });

        List<UserModel> userModelList1 = UserUtil.callMapper(UserMapper.class, mapper -> mapper.getModelList(null));
        log.info("结果:{}", userModelList1);
    }

    // 根据用户id删除数据
    @Test
    public void delete() throws Exception {
        Map<String, Object> map = new HashMap<>();
        //需要删除的用户id
        map.put("id", 1);
        Integer count = UserUtil.callMapper(UserMapper.class, mapper -> mapper.delete(map));
        log.info("删除行数：{}", count);
    }

    // 动态更新
    @Test
    public void update() throws Exception {
        //将userId=2的name修改为：路人
        Long userId1 = 2L;
        Integer count = UserUtil.callMapper(UserMapper.class, mapper -> mapper.update(UserModel.builder().id(userId1).name("ready").build()));
        log.info("更新行数：{}", count);

        log.info("---------------------");
        //将userId=3的name修改为：路人,薪水为：1000.88
        Long userId2 = 3L;
        count = UserUtil.callMapper(UserMapper.class, mapper -> mapper.update(UserModel.builder().id(userId2).name("ready").salary(1000.88D).build()));
        log.info("更新行数：{}", count);
    }

    // 按用户id查询
    public UserModel getModelById(Long userId) throws Exception {
        // 查询指定id的数据
        Map<String, Object> map = new HashMap<>();
        map.put("id", userId);
        return UserUtil.callMapper(UserMapper.class, mapper -> {
            List<UserModel> userModelList = mapper.getModelList(map);
            if (userModelList.size() == 1) {
                return userModelList.get(0);
            }
            return null;
        });
    }

    // 查询所有数据
    @Test
    public void getModelList1() throws Exception {
        List<UserModel> userModelList = UserUtil.callMapper(UserMapper.class, mapper -> mapper.getModelList(null));
        log.info("结果:{}", userModelList);
    }

    // 查询多个用户id对应的数据
    @Test
    public void getModelListByIds() throws Exception {
        List<Integer> idList = Arrays.asList(2, 3, 4).stream().collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("idList", idList);

        List<UserModel> userModelList = UserUtil.callMapper(UserMapper.class, mapper -> mapper.getModelList(map));
        log.info("结果:{}", userModelList);
    }

    // 多条件 & 指定返回的列
    @Test
    public void getModelList2() throws Exception {
        // 查询姓名中包含路人甲java以及薪资大于3万的用户id、姓名
        Map<String, Object> map = new HashMap<>();
        map.put("nameLike", "路人甲java");
        map.put("salaryGte", 30000.00D);
        // 需要返回的列
        List<String> tableColumnList = new ArrayList<>();
        tableColumnList.add("id");
        tableColumnList.add("name");
        map.put("tableColumnList", tableColumnList);

        List<UserModel> userModelList = UserUtil.callMapper(UserMapper.class, mapper -> mapper.getModelList(map));
        log.info("结果:{}", userModelList);
    }

    // 条件过滤 & 排序 & 分页查询数据 & 只返回用户id、salary
    @Test
    public void getPage() throws Exception {
        // 查询姓名中包含路人甲java以及薪资大于3万的用户id，按照薪资倒叙，每页5条取第1页
        Map<String, Object> map = new HashMap<>();
        map.put("nameLike", "路人甲java");
        map.put("salaryGte", 30000.00D);

        // 加入排序参数
        map.put("sort", "salary desc");

        // 加入分页参数
        int page = 1;
        int pageSize = 5;
        map.put("skip", (page - 1) * pageSize);
        map.put("pageSize", pageSize);

        //加入需要返回的列
        List<String> tableColumnList = new ArrayList<>();
        tableColumnList.add("id");
        tableColumnList.add("salary");
        map.put("tableColumnList", tableColumnList);

        List<UserModel> userModelList = UserUtil.callMapper(UserMapper.class, mapper -> mapper.getModelList(map));
        log.info("结果:{}", userModelList);
    }
}
