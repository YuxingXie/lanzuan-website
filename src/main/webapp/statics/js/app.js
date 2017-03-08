(function () {
    "use strict";
    var app = angular.module('app', []);

    app.controller('HomeController', ["$rootScope", "$scope", "$http", "$location","$window",function ($rootScope, $scope, $http, $location, $window) {
        $scope.getArticleSection=function(){
            $http.get("/articleSection/data").success(function (data) {
                console.log(JSON.stringify(data));
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
                                        if(articleInArr.id===article.id){
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
            $http.get("/statics/json/navbar.json").success(function (data) {
                $scope.navbar=data;
            });
        }

        $scope.removeNavItems=function(index){
            $scope.navbar.navItems.splice(index,1)
        }
        $scope.insertNavItemsBefore=function(index){

            $scope.navbar.navItems.splice(index,0,{"name":"百度","link":"http://baidu.com","faClass":"","navItemCass":"col-xs-3 col-md-2 col-lg-1 m-a-0 p-l-0 p-r-0 padding-top-10 text-center"})
        }

        $scope.submitNavbar=function(form){
            form.reset();
        }
        $scope.newNavbar=function(form){

        }
        $scope.resetNavbar=function(form){
            $scope.getNavbar();
        }
        $scope.resetArticleSections=function(form){
            $scope.getArticleSection();
        }
        $scope.renameArticleSection=function(articleSection){
            $http.post("/admin/article_section/rename",JSON.stringify(articleSection)).success(function (message) {
                articleSection=message.data;
                if(message.success){
                    alert("修改成功！");
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
            $http.get("/statics/json/carousel.json").success(function (data) {
                $scope.carousel=data;
            });
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
        $scope.getMenu=function(){
            $http.get("/statics/json/menu.json").success(function (data) {
                $scope.menuItems=data;
            });
        }

    }])

})();

