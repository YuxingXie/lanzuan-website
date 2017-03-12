(function () {
    "use strict";
    var app = angular.module('app', []);

    app.controller('HomeController', ["$rootScope", "$scope", "$http", "$location","$window",function ($rootScope, $scope, $http, $location, $window) {
        $scope.getArticleSection=function(){
            $http.get("/articleSection/data").success(function (data) {
                //console.log(JSON.stringify(data));
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
        $scope.getSortLinkSection=function(){
            $http.get("/statics/json/sortLinkSection.json").success(function (data) {
                $scope.sortLinkSection=data;
            });
        }

        $scope.getNavbar=function(){
            $http.get("/admin/navbar/home/data").success(function (data) {
                $scope.navbar=data;
            });
        }

        $scope.removeNavItems=function(index){
            $scope.navbar.navItems.splice(index,1)
        }
        $scope.insertNavItemsBefore=function(index){

            $scope.navbar.navItems.splice(index,0,{"name":"百度","link":"http://baidu.com","faClass":"","navItemCass":"col-xs-3 col-md-2 col-lg-1 m-a-0 p-l-0 p-r-0 padding-top-10 text-center"})
        }

        $scope.saveNavbar=function(){

            $http.post("/admin/navbar/update",JSON.stringify($scope.navbar)).success(function (message) {
                $scope.navbar=message.data;
                if(message.success){
                    alert("保存成功！");
                }
            });
        }
        $scope.newNavbar=function(){
            var name=prompt("输入导航条名称");
            $scope.navbar.name=name;
            $http.post("/admin/navbar/save-as",JSON.stringify($scope.navbar)).success(function (message) {
                $scope.navbar=message.data;
                if(message.success){
                    alert("另存成功！");
                }
            });
        }
        $scope.resetNavbar=function(form){
            $scope.getNavbar();
        }
        $scope.resetCarousel=function(){
            $scope.getCarousel();
        }
        $scope.resetArticleSections=function(form){
            $scope.getArticleSection();
            $scope.addArticleSection=false;
            $scope.addArticleSectionSaved=true;
        }
        $scope.newArticleSection=function(){
            $scope.addArticleSection=true;
            $scope.addArticleSectionSaved=false;
            if(!$scope.articleSections){
                $scope.articleSections=[];
            }
            var articleSection={};
            articleSection.name="请重命名我*"
            //if(!$scope.articleSections.length){
                $scope.articleSections.splice(0,0,articleSection);
            //}
            //else{
            //    $scope.articleSections[ $scope.articleSections.length]=articleSection;
            //}
        }
        $scope.saveNewArticleSection=function(articleSection){
            $http.post("/admin/article_section/new",JSON.stringify($scope.articleSections)).success(function (message) {
                $scope.articleSections=message.data;
                if(message.success){
                    alert("保存成功！");
                    $scope.addArticleSection=false;
                    $scope.addArticleSectionSaved=true;
                }
            });
        }
        $scope.renameArticleSection=function(articleSection){
            $http.post("/admin/article_section/rename",JSON.stringify(articleSection)).success(function (message) {
                articleSection=message.data;
                if(message.success){
                    alert("重命名成功！");
                }
            });
        }

        $scope.removeArticleSection=function(articleSection,index){
            if(!articleSection.id){
                $scope.articleSections.splice(index,1);
                if($scope.articleSections&&$scope.articleSections.length){
                    var allNotSavedRemoved=true;
                    for(var i=0;i<$scope.articleSections.length;i++){
                        var articleSection=$scope.articleSections[i];
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
        $scope.saveCarousel=function(){
            $http.post("/admin/carousel/insert-all",JSON.stringify( $scope.carousel)).success(function (message) {
                $scope.carousel=message.data;
                if(message.success){
                    alert("保存成功！");
                }
            });
        }
        $scope.newCarousel=function(){
            var name = window.prompt("请给方案命名","在此输入方案名");
            $scope.carousel.name=name;
            $http.post("/admin/carousel/save-as",JSON.stringify( $scope.carousel)).success(function (message) {
                $scope.carousel=message.data;
                if(message.success){
                    alert("方案保存成功！");
                }
            });
        }
        $scope.removeArticle=function(article){
            $http.post("/admin/article/remove",JSON.stringify(article)).success(function (message) {
                $scope.articleSections=message.data;
                if(message.success){
                    alert("删除成功！");
                }
            });
        }
        $scope.getCarousel=function(){
            $http.get("/carousel/home/data").success(function (data) {
                $scope.carousel=data;
            });
        }
        $scope.removeCarouselItem= function (index) {

            if($scope.carousel&&$scope.carousel.carouselItems&&$scope.carousel.carouselItems.length
            &&$scope.carousel.carouselItems[index].id){

                $http.post("/admin/carousel/item/remove/"+$scope.carousel.carouselItems[index].id,JSON.stringify($scope.carousel)).success(function (message) {
                    $scope.carousel=message.data;
                    alert("删除成功！");
                });
            }else{
                $scope.carousel.carouselItems.splice(index,1);
            }

        }
        $scope.insertCarouselItem= function () {
            var item={
                "type": "image",
                "value": "/statics/image/lanzuan/home/cloud.jpg",
                "carouselCaption": {
                    "type": "text",
                    "text": "我是白云。。。。。。"
                }
            }
            if(!$scope.carousel){
                $scope.carousel={};
            }
            if(!$scope.carousel.carouselItems){
                $scope.carousel.carouselItems=[];

            }


            $scope.carousel.carouselItems.splice(0,0,item);
        }
        $scope.forwardCarouselItem=function(index){
            var item=$scope.carousel.carouselItems[index];
            $scope.carousel.carouselItems.splice(index,1);
            $scope.carousel.carouselItems.splice(index-1,0,item);
        }
        $scope.backwardCarouselItem=function(index){
            var item=$scope.carousel.carouselItems[index];
            $scope.carousel.carouselItems.splice(index,1);
            $scope.carousel.carouselItems.splice(index+1,0,item);
        }
        $scope.getCardGroup=function(){
            $http.get("/statics/json/img-card-group.json").success(function (data) {
                $scope.cardGroup=data;
            });
        }
        $scope.getCollapseImageTitleText=function(){
            $http.get("/statics/json/collapse-img-title-text-block.json").success(function (data) {
                $scope.collapseBlock=data;
                $scope._active=0;
                //console.log(JSON.stringify(data))
            });
        }
    }])
    app.controller('AdminController', ["$rootScope", "$scope", "$http", "$location","$window",function ($rootScope, $scope, $http, $location, $window) {
        $scope.initAdmin=function(){

            $scope.getMenu();
            $scope.editable=false;
        }
        $scope.getIcons=function(){
            console.log("icons");
            $http.get("/admin/icons/data").success(function (data) {
                $scope.icons=data;
            });
        }
        $scope.getArticles=function(){
            $http.get("/admin/article/list/data").success(function (data) {
                $scope.articles=data;
            });
        }
        $scope.getAllCarousels=function(){
            $http.get("/admin/carousel/list/data").success(function (data) {
                $scope.carousels=data;
            });
        }
        $scope.changeEnabledStatus=function(carousel){
            $http.post("/admin/carousel/update",JSON.stringify(carousel)).success(function (data) {
                $scope.carousels=data;

            });
        }

        $scope.changeNavbarEnabledStatus=function(navbar){
            $http.post("/admin/navbar/status-change",JSON.stringify(navbar)).success(function (data) {
                $scope.navbarList=data;
            });
        }
        $scope.deleteCarousel=function(carousel){
            if(!confirm("确定删除?"))
                return ;
            $http.post("/admin/carousel/delete/"+carousel.id,JSON.stringify(carousel)).success(function (data) {
                $scope.carousels=data;

            });
        }
        $scope.deleteNavbar=function(navbar){
            if(!confirm("确定删除?"))
                return ;
            $http.post("/admin/navbar/delete/"+navbar.id,JSON.stringify(navbar)).success(function (data) {
                $scope.navbarList=data;

            });
        }
        $scope.getMenu=function(){
            $http.get("/statics/json/menu.json").success(function (data) {
                $scope.menuItems=data;
            });
        }

        $scope.getNavbarList=function(){
            $http.get("/admin/navbar/list/data").success(function (data) {
                $scope.navbarList=data;
            });
        }

    }])

})();

