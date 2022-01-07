package cn.iocoder.springboot.lab83.demo.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * PersonFactoryBean 类
 *
 * @author jaquez
 * @date 2022/01/07 15:03
 **/
public class PersonFactoryBean implements FactoryBean<Person4> {

    @Override
    public Person4 getObject() throws Exception {
        return new Person4();
    }

    @Override
    public Class<?> getObjectType() {
        return Person4.class;
    }

}
