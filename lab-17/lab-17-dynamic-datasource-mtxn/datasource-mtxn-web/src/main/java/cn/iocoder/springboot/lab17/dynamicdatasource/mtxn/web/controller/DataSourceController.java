package cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.controller;

import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.entity.DataSource;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.entity.enums.DataSourceStatus;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.entity.enums.DataSourceType;
import cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.service.DataSourceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

import static cn.iocoder.springboot.lab17.dynamicdatasource.mtxn.web.service.OrderService.STOCK_DATASOURCE_ID;

@RestController
@RequestMapping(value = "/dataSource")
@AllArgsConstructor
public class DataSourceController {

    private DataSourceService dataSourceService;

    /**
     * 列出当前所有的数据源
     *
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public List<DataSource> list() {
        return dataSourceService.getAll();
    }

    /**
     * 新增一个新数据源，仅仅是为了测试
     * 这里数据源 id 指定为 10001，在实际的应用种会把数据源 id 跟业务绑定
     *
     * @return DataSource
     */
    @GetMapping("/add")
    @ResponseBody
    public DataSource add() {
        DataSource dataSource = DataSource.builder().name("lab-17-mtxn-test").jdbcUrl("jdbc:mysql://localhost:3306/lab-17-mtxn-test?useSSL=false&allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8")
                .username("root").password("123456").disabled(false).creator("mtxn").createTime(new Timestamp(System.currentTimeMillis()))
                .type(DataSourceType.MYSQL).description("init by mTxn")
                .minIdle(1).maxPoolSize(8).id(1).dbName("lab-17-mtxn-test").id(STOCK_DATASOURCE_ID).build();
        dataSourceService.insert(dataSource);
        return dataSource;
    }

    @GetMapping("/getById")
    @ResponseBody
    public DataSource getById(Integer id) {
        return dataSourceService.getById(id);
    }

    @GetMapping("/getByName")
    @ResponseBody
    public DataSource getByName(String name) {
        return dataSourceService.getByName(name);
    }

    @PostMapping("/update")
    @ResponseBody
    public void update(@RequestBody DataSource dataSource) {
        dataSourceService.update(dataSource);
    }

    @PostMapping("/new")
    @ResponseBody
    public void insert(@RequestBody DataSource dataSource) {
        dataSourceService.insert(dataSource);
    }

    @GetMapping("/deleteById")
    @ResponseBody
    public int deleteById(String id) {
        return dataSourceService.deleteById(id);
    }

    @GetMapping("/modify")
    @ResponseBody
    public void modifyStatus(Integer id, DataSourceStatus status) {
        dataSourceService.modifyStatus(id, status);
    }

}
