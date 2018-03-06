# SelectTagView
### 博客地址点击：https://www.jianshu.com/p/dfa662a7b07a
**使用方法：**

gradle ： ```compile 'com.stormKid:selecttagview:1.0.1'```

maven : 
```
<dependency>
  <groupId>com.stormKid</groupId>
  <artifactId>selecttagview</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```
### API
>  属性 --- 类型 --- 注释
> 1、tagTextSize  ---  Integer  为计算后的字体大小，不推荐修改，并非px,dp计算，使用屏幕比例计算；
> 2、tagTextColor --- ColorRes 标签字体大小；
> 3、tagBgNoSelectRes --- DrawableRes 标签非选择时的背景
> 4、tagBgSelectRes --- DrawableRes 标签选择时的背景
> 5、tagSelectType --- Enum 标签类型 分别为 ‘SINGLE -- 单选‘ ，’MORE -- 多选’ ，‘NORMAL’  -- 普通勾选
