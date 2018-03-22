# SelectTagView
### 博客地址点击：https://www.jianshu.com/p/dfa662a7b07a
**使用方法：**

gradle ： ```compile 'com.stormKid:selecttagview:1.0.2'```

maven : 
```
<dependency>
  <groupId>com.stormKid</groupId>
  <artifactId>selecttagview</artifactId>
  <version>1.0.2</version>
  <type>pom</type>
</dependency>
```
### API


|  属性 | 类型 | 默认值 | 注释说明 |
| --- | --- | --- | --- |
|  tagTextSize   |   Integer  |  8   |  为计算后的字体大小，不推荐修改，并非px,dp计算，使用屏幕比例计算  |
| tagTextColor    |   ColorRes  |  #666666   |   标签字体默认颜色   |
| tagBgNoSelectRes    |  DrawableRes   |     |   标签非选择时的背景  |
| tagBgSelectRes    | DrawableRes    |     |   标签选择时的背景  |
| tagSelectType    |   Enum  |  NORMAL   |  Enum 标签类型 分别为 ‘SINGLE -- 单选‘ ，’MORE -- 多选’ ，‘NORMAL’  -- 普通勾选   |
| tagTextSelectColor    |  ColorRes   |  tagTextColor   |  标签字体选择的颜色   |

