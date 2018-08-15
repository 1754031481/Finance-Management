# LCN分布式事务框架v4.0

  "LCN并不生产事务，LCN只是本地事务的搬运工"

## 框架介绍   

  LCN分布式事务框架是一款事务协调性的框架，框架本身并不创建事务，只是对本地事务做协调控制。因此该框架与其他第三方的框架兼容性强，支持所有的关系型数据库事务，支持多数据源，支持与第三方数据库框架一块使用（例如 sharding-jdbc），在使用框架的时候只需要添加分布式事务的注解即可，对业务的侵入性低。LCN框架主要是为微服务框架提供分布式事务的支持，在微服务框架上做了进一步的事务机制优化，在一些负载场景上LCN事务机制要比本地事务机制的性能更好，4.0以后框架开方了插件机制可以让更多的第三方框架支持进来。


## 官方网址

[https://www.txlcn.org](https://www.txlcn.org)


## 框架特点

1. 支持各种基于spring的db框架
2. 兼容SpringCloud、Dubbo
3. 使用简单，低依赖，代码完全开源
4. 基于切面的强一致性事务框架
5. 高可用，模块可以依赖RPC模块做集群化，TxManager也可以做集群化
6. 支持本地事务和分布式事务共存
7. 支持事务补偿机制，增加事务补偿决策提醒
8. 添加插件拓展机制


## 原理介绍

[原理介绍](https://github.com/codingapi/tx-lcn/wiki)  [视频讲解](https://www.txlcn.org/v4/index.html)

## 目录说明

transaction-dubbo LCN dubbo rpc框架扩展支持

tx-client 是LCN核心tx模块端控制框架

tx-manager 是LCN 分布式事务协调器

tx-plugins-db 是LCN 对关系型数据库的插件支持

## 主要配置

mybatis数据源配置:

```xml

    <!--连接池配置-->
    <bean name="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
        <property name="url" value="${db.url}"/>
        <property name="username" value="${db.username}"/>
        <property name="password" value="${db.password}"/>
    </bean>


    <!-- spring和MyBatis完美整合，不需要mybatis的配置映射文件 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <!-- 自动扫描mapping.xml文件 -->
        <property name="mapperLocations" value="classpath:com/mapping/*.xml"></property>
    </bean>


    <!-- DAO接口所在包名，Spring会自动查找其下的类 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.dao" />
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
    </bean>



    <bean id="transactionManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource" />
    </bean>
    
    <!--事物执行顺序配置-->

    <tx:annotation-driven order="2" transaction-manager="transactionManager"/>

```

dubbo提供端配置:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://code.alibabatech.com/schema/dubbo
        http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

    <dubbo:application name="mybatis_provider_demo"   />

    <!--所有参与分布式事务的模块以及TxManager都必须要在同一个服务下-->
    <dubbo:registry protocol="zookeeper" address="127.0.0.1:2181" />

    <dubbo:provider delay="-1" timeout="6000"  port="20883" retries="0"/>

    <dubbo:protocol accesslog="true" name="dubbo"   host="127.0.0.1" />
    <!--过滤器配置-->
    <dubbo:consumer  filter="transactionFilter" />

    <dubbo:service  interface="com.demo.service.Test2Service" ref="test2ServiceImpl"  />


</beans>

```

dubbo 消费端配置:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://code.alibabatech.com/schema/dubbo
        http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

    <dubbo:application name="mybatis_consumer_demo"   />

    <!--所有参与分布式事务的模块以及TxManager都必须要在同一个服务下-->
    <dubbo:registry protocol="zookeeper" address="127.0.0.1:2181" />
    
    <!--过滤器配置-->
    <dubbo:consumer  filter="transactionFilter" />

    <dubbo:provider delay="-1" timeout="6000" port="20881"  retries="0"/>

    <dubbo:reference loadbalance="lcn_random" interface="com.demo.service.Test2Service" id="test2Service" />

    <dubbo:protocol accesslog="true" name="dubbo"  host="127.0.0.1" />

</beans>
```
更多demo,参考demo演示。
## 使用说明

分布式事务发起方：

```

    @Override
    @TxTransaction
    @Transactional
    public boolean hello() {
        //本地调用
        testDao.save();
        //远程调用方
        boolean res =  test2Service.test();
        //模拟异常
        int v = 100/0;
        return true;
    }
    
    
```

分布式事务被调用方(test2Service的业务实现类)
```

    @Override
    @Transactional
    public boolean test() {
        //本地调用
        testDao.save();
        return true;
    }

```

如上代码执行完成以后两个模块都将回滚事务。

说明：在使用LCN分布式事务时，只需要将事务的开始方法添加`@TxTransaction`注解即可。详细见demo教程

## 关于@TxTransaction 使用说明

  @TxTransaction注解是分布式事务的标示。
  
  若存在业务方法：a->b b->c b->d，那么开启分布式事务注解的话，只需要在a方法上添加@TxTransaction即可。
  
```
    @TxTransaction
    @Transactional
    public void a(){
        b();
    }

    public void b(){
        c();
        d();
    }

    public void c(){}

    public void d(){}
```

## maven 中心库地址



```


<dependency>
    <groupId>com.codingapi</groupId>
    <artifactId>tx-client</artifactId>
    <version>${lcn.last.version}</version>
</dependency>


<dependency>
    <groupId>com.codingapi</groupId>
    <artifactId>tx-plugins-db</artifactId>
    <version>${lcn.last.version}</version>
</dependency>


<dependency>
    <groupId>com.codingapi</groupId>
    <artifactId>tx-plugins-nodb</artifactId>
    <version>${lcn.last.version}</version>
</dependency>


<dependency>
    <groupId>com.codingapi</groupId>
    <artifactId>transaction-dubbo</artifactId>
    <version>${lcn.last.version}</version>
</dependency>      

<dependency>
    <groupId>com.codingapi</groupId>
    <artifactId>transaction-motan</artifactId>
    <version>${lcn.last.version}</version>
</dependency>  


<dependency>
    <groupId>com.codingapi</groupId>
    <artifactId>transaction-springcloud</artifactId>
    <version>${lcn.last.version}</version>
</dependency>    
        
```

依赖gradle等形式，见中心库   

[http://mvnrepository.com/search?q=codingapi](http://mvnrepository.com/search?q=codingapi)


## demo演示教程

每个demo下有区分为 jdbc/hibernate/mybatis不同框架的版本demo

[dubbo版本](https://github.com/codingapi/dubbo-lcn-demo)

