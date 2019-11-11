package com.spring.boot.example.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@PropertySource(value = {"classpath:properties/product.properties"}, encoding = "utf-8")
@Component
public class Product {

    private String sku;

    private Integer quantity;

    private String description;

    private Float price;

    @Value("#{'${product.skus}'.split(',')}")
    private List<String> skus;

    @Value("#{'${product.quantitys}'.split(',')}")
    private List<Integer> quantitys;

    @Value("#{'${product.prices}'.split(',')}")
    private List<Integer> prices;

    @Value("#{${product.pricesMap}}")
    private Map<String, String> priceMap;

    @Value("#{${product.pricesMap}['one']}")
    private Float priceOne;

    @Value ("#{${product.pricesMap}['five'] ?: 31}")
    private Float priceWithDefault;

    @Value("#{systemProperties['java.home']}")
    private String javaHome;

    @Value("#{systemProperties['user.dir']}")
    private String userDir;

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        // 直接量表达式
        System.out.println(parser.parseExpression("'Hello World'").getValue());

        // 直接使用java代码new/instance of 只能是java.lang 下的类才可以省略包名
        System.out.println(parser.parseExpression("new String('Hello world')").getValue());

        // 使用T(Type)
        System.out.println(parser.parseExpression("T(Integer).MAX_VALUE").getValue());

        /**
         * 获取容器内的变量，可以使用“#bean_id”来获取。有两个特殊的变量，可以直接使用。
         *  #this 使用当前正在计算的上下文
         *  #root 引用容器的root对象
         */
        //创建一个虚拟的容器EvaluationContext
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        //setRootObject并非必须；一个EvaluationContext只能有一个RootObject，引用它的属性时，可以不加前缀
        Product product = new Product();
        ctx.setRootObject(product);
        System.out.println(parser.parseExpression("#root").getValue(ctx, Product.class));
        //向容器内添加bean
        ctx.setVariable("str1", new String("bean1"));
        //取id为str1的bean，然后调用其中的toUpperCase方法
        System.out.println(parser.parseExpression("#str1.toUpperCase()").getValue(ctx, String.class));
        //向容器内添加方法
        try {
            Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
            ctx.registerFunction("parseInt", parseInt);
            //调用方法
            System.out.println(parser.parseExpression("#parseInt('111') == 111").getValue(ctx, Boolean.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // Elvis运算符
        System.out.println(parser.parseExpression("#name?:'Unknown'").getValue(String.class));

        // 为了避免操作对象本身可能为null，取属性时报错，定义语法 “对象?.变量|方法”
        System.out.println(parser.parseExpression("#name?.length").getValue());

        // 集合访问 “集合[索引]”、“map[key]”
        System.out.println(parser.parseExpression("{1,2,3}[0]").getValue(int.class));
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        ctx.setVariable("map", map);
        System.out.println(parser.parseExpression("#map['a']").getValue(ctx, int.class));
    }
}
