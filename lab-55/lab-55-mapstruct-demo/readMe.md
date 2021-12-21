⟶⟶⟶⟶⟶
平时我们经常使用Hutool中的BeanUtil类来实现对象转换，用多了之后就发现有些缺点：

对象属性映射使用反射来实现，性能比较低；

对于不同名称或不同类型的属性无法转换，还得单独写Getter、Setter方法；

对于嵌套的子对象也需要转换的情况，也得自行处理；

集合对象转换时，得使用循环，一个个拷贝。

对于这些不足，MapStruct都能解决，不愧为一款功能强大的对象映射工具！

↓
**基本对象映射**

参考使用 UserBOTest 类

**集合对象映射**

参考使用 UserBOListTest 类，入参为集合转化也是集合

**子对象映射**

参考使用 OrderBOTest 类，目标对象里有子对象（单个对象或集合）

**合并映射**

参考使用 MemberOrderBOTest 类，A类B类部分字段组合起来AB类

⟶⟶⟶⟶⟶
进阶

**依赖注入使用方式**

参考使用 MemberBOSpringTest 类

**默认值、常量、表达式使用**

参考使用 ProductConvert 类

**自定义处理，类似于 AOP 切面、异常处理**

参考使用 ProductRoundConvert 类，对映射前后做一些工作

参考使用 ProductBOExceptionTest 类

更多示例参考官方文档：<https://mapstruct.org/documentation/stable/reference/html/>



