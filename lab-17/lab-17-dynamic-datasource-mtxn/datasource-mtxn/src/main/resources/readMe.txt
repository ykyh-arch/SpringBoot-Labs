项目启动
1.AutoDataSourceConfiguration 配置默认数据源（主数据源）hikari/druid，DynamicDataSource 设置默认数据源
2.SelfDataSourceStartup 注册配置文件数据源信息到 com_data_source
3.DataSourceTask[定时] 加载 com_data_source 表中的数据源信息，在 Cache<String, DataSource> dataSourceCache 缓存起来，减少对 com_data_source 的查询，同时维护 DataSourceManager 类，put() 存数据源，目的是供 DynamicDataSource#dataSources 使用 
重要的类
DynamicDataSource - 动态数据源
1.对 DataSourceManager 实现，利用 ConcurrentMap<String, DataSource> dataSources 具体维护起来，维护的是 com_data_source 数据源信息
2.重写 getConnection() 方法，获取 Connection 或 ConnectionProxy
3.维护了默认数据源以及动态数据源
4.determineTargetDataSource 找目标数据源，dataSources lookuokey 查找得来。
DataSourceManager - 提供动态维护数据源，在 DynamicDataSource 维护 dataSources 具体缓存起来
DataSourceCacheOperator - 数据源缓存操作类，在 DataSourceTask 体现
MultiTransactionManager - 多数据源事务管理器，提供维护 TransactionHolder 信息、bindConnection 信息
TransactionHolder - 事务上下文环境，维护事务与 ConnectionProxy、executeStack、datasourceKeyStack 关联关系
DynamicDataSourceBuilder - 创建对应 hikari/druid 数据源，在 DataSourceTask#load()用到，配置 com_data_source 数据源信息
@MultiTransaction - 多数据源注解，datasourceId 对应数据源 id
MultiTransactionAop - 多事务切面，通过 MultiTransactionManager 提供数据源切换、多事务的开启、提交、回滚、关闭功能
DataSourceContextHolder - 数据源上下文环境，维护数据源的 Key
AbstractTransactionManager - 抽象事务管理类，提供切换数据源能力，DataSourceContextHolder.setKey（dataSourceId），后续通过 lookupkey 实现切换
ConnectionProxy - 屏蔽原生的 commit 和 close 方法