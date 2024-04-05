# lanzuan-website

七年前的一个项目，用mongoDB和angularJS做的，七年以后的今天重新使用mongoDB,发现忘光了，这个可以拿来参考一下。

想跑起来试试，发现把红波浪修复后，居然找不到程序入口。隐约记得当年的web app是要启动Tomcat，用钩子启动spring容器，
和今天的springboot程序构建方式已经云泥之别了。

Maven构建仍然很主流，但是我早改用gradle了，angularJS也早被angular2取代，angular2现在又有被更强的跨平台如flutter取代的趋势。

记录一些tips：

* MongoTemplate中的DBObject现在改为了的Document。
