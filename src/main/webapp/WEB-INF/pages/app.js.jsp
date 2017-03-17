<%@ page import="com.lanzuan.common.util.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<c:set var="word" value="hello"/>
<c:if test="false">
    <script></c:if>
(function () {
"use strict";

var app = angular.module('app', []);

app.controller('HomeController', ["$rootScope", "$scope", "$http", "$location","$window",function ($rootScope, $scope, $http, $location, $window) {
<c:if test="${not empty page}">
    <c:forEach var="component" items="${page.pageComponents}">
        $scope.get${component.varU}=function(){
            $http.get('${component.dataUri}').success(function (data) {
//                console.log(JSON.stringify(data));
                $scope.${component.var}=data;
            });
        }
    </c:forEach>
</c:if>
<c:if test="${not empty component}">
    $scope.get${component.varU}=function(){
            $http.get('${component.dataUri}').success(function (data) {
                $scope.${component.var}=data;
        });
    }

</c:if>


}])
app.controller('AdminController', ["$rootScope", "$scope", "$http", "$location","$window",function ($rootScope, $scope, $http, $location, $window) {


<c:if test="${not empty component}">
    $scope.get${component.varU}=function(){
        $http.get('${component.dataUri}').success(function (data) {
            $scope.${component.var}=data;
        });
    }
    $scope.reset${component.varU}=function(){
    $scope.get${component.varU}();
    $scope.${component.var}Resetted=true;
    }
    //update
    $scope.save${component.varU}=function(){
        <%--console.log(JSON.stringify($scope.${component.var}));--%>
        $http.post("${component.saveUri}",JSON.stringify($scope.${component.var})).success(function (message) {
            $scope.${component.var}=message.data;
            if(message.success){
                alert("保存成功！");
            }
        });
    }
    //方案另存
    $scope.new${component.varU}=function(){
        var name = window.prompt("请给方案命名","新方案名");
        if(!name) return;
        $scope.${component.var}.name=name;
        $http.post("${component.saveAsUri}",JSON.stringify( $scope.${component.var})).success(function (message) {
            $scope.${component.var}=message.data;
            if(message.success){
                alert("方案保存成功！");
            }
        });
    }

    /**
    *条目操作begin
    */
    //条目前移
    $scope.forward${component.varU}Item=function(index){
        var item=$scope.${component.var}.items[index];
        $scope.${component.var}.items.splice(index,1);
        $scope.${component.var}.items.splice(index-1,0,item);
    }
    //条目后移
    $scope.backward${component.varU}Item=function(index){
        var item=$scope.${component.var}.items[index];
        $scope.${component.var}.items.splice(index,1);
        $scope.${component.var}.items.splice(index+1,0,item);
    }
    //指定位置插入条目
    $scope.insert${component.varU}Item= function (index) {
        if(index===undefined){
            index=0;
        }
        var item={};
        <%--var item=$scope.${component.var}.items.shift();--%>
        <%--console.log(JSON.stringify(item))--%>
        <%--$scope.${component.var}.items.splice(0,0,item,item);--%>
        <%--return;--%>
        if(!$scope.${component.var}){
            $scope.${component.var}={};
        }
        if(!$scope.${component.var}.items){
            $scope.${component.var}.items=[];
        }
        $scope.${component.var}.items.splice(index,0,item);
    }

    //前面插入条目
    $scope.insert${component.varU}ItemBefore=function(index){
        $scope.${component.var}.items.splice(index,0,{})
    }
    //移除条目
    $scope.remove${component.varU}Item=function(index){

        $scope.${component.var}.items.splice(index,1)
    }
    /**
    *条目操作end
    */

        //列表操作
        $scope.get${component.varU}List=function(){
            $http.get("${component.listDataUri}").success(function (data) {
                $scope.${component.var}List=data;
            });
        }
        //启用/禁用
        $scope.${component.var}Toggle=function(component){
                $http.post("${component.toggleUri}",JSON.stringify(component)).success(function (data) {
                $scope.${component.var}List=data;
            });
        }
        //删除
        $scope.delete${component.varU}=function(component){
        if(!confirm("确定删除?"))
            return ;
        $http.post("${component.deleteUri}"+component.id,JSON.stringify(component)).success(function (data) {
            $scope.${component.var}List=data;
        });
        }
</c:if>

    $scope.initAdmin=function(){

    $scope.getMenu();
    $scope.editable=false;
    }
    $scope.getArticleSectionsAddDuplicated=function(uri){
        $http.get(uri).success(function (data) {

        if(!data ||!data.length){
        $scope.articleSections=null;
        return;
        }
        var articleArr=new Array();
        if(data&&data.length){
        //console.log("data.length "+data.length)
        for(var i=0;i<data.length;i++){
            var section=data[i];
            //console.log("section.enabled" + section.enabled)
            var articles=section.articles;
            if(articles && articles.length){
                //console.log("articles.length "+articles.length)
                for(var j=0;j<articles.length;j++){
                var article=articles[j];
                //console.log(article.title);
                var duplicated=false;
                if(articleArr.length){
                    for(var k=0;k<articleArr.length;k++){
                        var articleInArr=articleArr[k];
                        if(article&&articleArr&&articleInArr.id&&article.id&&articleInArr.id===article.id){
                            duplicated=true;
                        }
                    }
                }
                if(!duplicated){
                articleArr.push(article);
                }else{
                    article.duplicated=true;
                    //console.log("duplicated")
                }
                }
            }

        }
        }

        $scope.articleSections=data;
        });
    }

    $scope.getIcons=function(){
    $http.get("/admin/icons/data").success(function (data) {
    $scope.icons=data;
    });
    }
$scope.getCardGroupImage=function(){
    $http.get("/admin/card-group/images/data").success(function (data) {
    $scope.icons=data;

});
}
    $scope.getArticleCovers=function(){
        $http.get("/admin/article/cover-images/data").success(function (data) {
        $scope.icons=data;

    });
    }
$scope.getCarouselImages=function(){
$http.get("/admin/carousel-images/data").success(function (data) {
    $scope.carouselImages=data;
});
}
    $scope.getFullWidthImages=function(){
    $http.get("/admin/full-width-image/image/data").success(function (data) {
    $scope.fullWidthImages=data;
    });
    }
    $scope.getImageTextBlockGroupImages=function(){
    $http.get("/admin/image-text-block-group/image/data").success(function (data) {
    $scope.icons=data;
    });
    }
    $scope.getArticles=function(){
        $http.get("/admin/article/list/data").success(function (data) {
            $scope.articles=data;
        });
    }


$scope.getMenu=function(){
$http.get("/statics/json/menu.json").success(function (data) {
$scope.menuItems=data;
});
}



$scope.removeArticleSection=function(articleSections,articleSection,index){
if(!articleSection.id){
articleSections.splice(index,1);
if(articleSections&&articleSections.length){
var allNotSavedRemoved=true;
for(var i=0;i<articleSections.length;i++){
var articleSection=articleSections[i];
if(!articleSection.id){
allNotSavedRemoved=false;
break;
}
}
if(allNotSavedRemoved){
$scope.addArticleSection=false;
$scope.addArticleSectionSaved=true;
}
}
return;
}
$http.post("/admin/article_section/remove",JSON.stringify(articleSection)).success(function (message) {
$scope.articleSections=message.data;
if(message.success){
alert("修改成功！");
}
});
}


$scope.removeArticle=function(id){
$http.post("/admin/article/remove/"+id).success(function (message) {
    $scope.articles=message.data;
if(message.success){
alert("删除成功！");
}
});
}
//以下方法覆盖自动生成的方法，一定要注意此方法在js中的顺序

    $scope.insertBlockItemBefore=function(imageTextBlock,index){

        imageTextBlock.imageTextItems.splice(index,0,{
            "image": "/statics/image/lanzuan/home/logo-bg.jpg",
            "text": "baidu",
            "link": "http://baidu.com",
            "title":"百度一下"
        });
    }
    $scope.removeBlockItem=function(imageTextBlock,index){
        imageTextBlock.imageTextItems.splice(index,1);
    }
    $scope.forwardBlockItem=function(imageTextBlock,index){
        var item=imageTextBlock.imageTextItems[index];
        imageTextBlock.imageTextItems.splice(index,1);
        imageTextBlock.imageTextItems.splice(index-1,0,item);
    }
    $scope.backwardBlockItem=function(imageTextBlock,index){
        var item=imageTextBlock.imageTextItems[index];
        imageTextBlock.imageTextItems.splice(index,1);
        imageTextBlock.imageTextItems.splice(index+1,0,item);
    }
    $scope.insertImageTextBlockGroupItem=function(index){
        if(index===undefined){
            index=0;
            }

            if(!$scope.imageTextBlockGroup){
            $scope.imageTextBlockGroup={};
            }
            if(!$scope.imageTextBlockGroup.items){
                $scope.imageTextBlockGroup.items=[];
            }
            var item={};
            item.name="输入名称";
            var imageTextItem={};
            imageTextItem.text="文字";
            imageTextItem.link="链接";
            imageTextItem.title="标题";
            item.imageTextItems=[];
            item.imageTextItems.push(imageTextItem);
//            $scope.imageTextBlockGroup.items.push(item);
//            $scope.imageTextBlockGroup.items[0]=item;
            $scope.imageTextBlockGroup.items.splice(0,0,item);
            }

    $scope.newSortLinkAddArticle=function(sortLinkIndex){
        <c:if test="${not empty component}">
            $scope.${component.var}.items[sortLinkIndex]={sortName:"分类名称",links:[{text:"文章标题"}]};
        </c:if>
}

}])
})()
<c:if test="false"></script></c:if>