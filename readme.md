

#Project Value Proposition

这个APP是为了减少设计移动应用时在配色方案上花费的时间。在这个APP上，你可以创造自己的配色方案，并且预览这一套配色方案在几种常见的界面的表现效果，而不需要写代码来实现。在确定了方案后，程序可以把对应的`color.xml`分享至你的电脑，以便你导入到Android Studio。除了创造自己的方案外，你还可以获取其他人提供的配色方案。你可以对任何配色方案点赞，点踩或者收藏。

# UI SKETCH

 ![colorPreview.png](https://github.com/zhouzhouyou/ColorPreview/blob/master/assets/colorPreview.png) 

这是用户的主要操作界面（当然不是实际情况，UI界面还有待商榷）

# System Functions

##1.  主题风格设置

功能简述: 

可以让用户或者开发者一边选择颜色同时还能实时预览效果图，并且用户只需要选择4个颜色后系统会自己匹配出一组配色方案，并且可以以xml文件的格式导出颜色。从而可以提前预览界面风格，节省界面设计时间。

### 1.1 设置主题风格颜色（primary color）

通过选择提供的颜色，或者用户输入颜色来确定primary color，在生成主题颜色primary color的同时会生成dark primary color 以及 light primary color.

### 1.2 设置二级颜主体色(secondary color)

通过选择提供的颜色，或者用户输入颜色来确定primary color，在生成主题颜色primary color的同时会生成dark secondary color 以及 light secondary color.

### 1.3 设置主字体风格

通过选择提供的颜色，或者用户输入颜色来确定primary text color. 

### 1.4 设置二级字体风格

通过选择提供的颜色，或者用户输入颜色来确定secondary text color. 

### 1.5 导出颜色xml文件

将生成的一组配色方案由xml的格式导出。



## 2. 用户管理

### 2.1 注册

用户可以注册获得账号。注册时需要填写账号名称、性别、邮件地址等信息。

### 2.2 登陆

用户可以通过注册的账号登陆网站。

### 2.3 个人信息

用户可以查看自己的个人信息，并可以对账号名称以外的信息进行修改。



## 3. 功能简介

### 3.1 社区浏览

用户可以浏览不同的主题模板

### 3.2 社区分享

用户可以在论坛中展示分享自己的模板设计。

### 3.3 社区模板点赞

用户可以对自己喜欢的模板设计点赞。

### 3.4 XML导出

将配色方案导出为Android Studio可以直接使用的格式