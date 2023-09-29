# magua

**Magua**框架包括 `IOC`、`AOP`、`ORM`和`MVC` 框架基础实现，本框架适用于初学者，可以帮助初学者了解 Spring `IOC`、`AOP`、`MVC` 和 `ORM`的工作原理。

框架只是简单的实现了 `IOC`、`AOP`、`ORM`和`MVC` 基础原理部分，没有经过系统性的测试，所以，请不要在生产环境使用本框架。

# magua-orm
一个非常简单的`ORM`实现，以下示例：

示例代码在`magua-demo` 工程下的 `com.maguasoft.orm`包中
```java
public class Demo {
    public static void main(String[] args) {
        QueryDemo queryDemo = new QueryDemo();
        queryDemo.query();

        UpdateDemo updateDemo = new UpdateDemo();
        updateDemo.update();

        DeleteDemo deleteDemo = new DeleteDemo();
        deleteDemo.delete();

        InsertDemo insertDemo = new InsertDemo();
        insertDemo.insert();
    }
}
```
