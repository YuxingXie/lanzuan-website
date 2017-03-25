package com.lanzuan.common.web;

import com.lanzuan.common.code.Expression;
import com.lanzuan.common.code.InputType;
import com.lanzuan.common.util.ReflectUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.entity.support.*;
import net.sf.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;


public class AngularEntityEditorBuilder {
    private StringBuffer editorHtml;
    private StringBuffer listOperationHtml;
    private StringBuffer listOperationJavaScript;
    private StringBuffer javaScript;
    private Class<? extends Item> itemClass;

    private  Naming itemNaming;
    private PageComponent<RootItem> pageComponent;
    private List<PageComponent> pageComponents;
    private AngularEntityEditorBuilder(){

    }
    public AngularEntityEditorBuilder(PageComponent pageComponent){
        this.editorHtml=new StringBuffer();

        this.javaScript=new StringBuffer();
        this.itemClass=pageComponent.getData().getClass();
        this.itemNaming= itemClass.getAnnotation(Naming.class);
        this.pageComponent=pageComponent;
        this.listOperationHtml=new StringBuffer();
        this.listOperationJavaScript=new StringBuffer();
    }
    public AngularEntityEditorBuilder(List<PageComponent> pageComponents){
        this.editorHtml=new StringBuffer();
        this.javaScript=new StringBuffer();
        this.pageComponents=pageComponents;

    }
    public void buildHtml() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        commonOperationsHtml();
        printItem(1);
    }


    private void buildAdminJavaScript() {
        buildGetMethod();
        buildResetMethod();
        buildGetMaterialMethod();
        buildUpdateMethod();
        buildSaveAsMethod();
        buildForwardItemMethod();
        buildBackwardItemMethod();
        buildInsertItemTopMethod();
        buildRemoveItemMethod();
        buildGetListMethod();
        buildToggleMethod();
        buildDeleteItemMethod();
        buildInitAdminMethod();
    }

    private void buildInitAdminMethod() {
        javaScript.append("\n;$scope.initAdmin=function(){");
        javaScript.append("\n$scope.getMenu();");
        javaScript.append("\n$scope.editable=false;");
        javaScript.append("\n}");
        javaScript.append("\n$scope.getMenu=function(){");
        javaScript.append("\n$http.get(\"/statics/json/menu.json\").success(function (data) {");
        javaScript.append("\n$scope.menuItems=data;");
        javaScript.append("\n});");
        javaScript.append("\n}");
    }

    private void buildDeleteItemMethod() {
        javaScript.append("\n;$scope.delete"+pageComponent.getVarU()+"=function(component){");
        javaScript.append("\nif(!confirm(\"确定删除?\")) return ;");
        javaScript.append("\n$http.post(\""+pageComponent.getDeleteUri()+"\"+component.id,JSON.stringify(component)).success(function (data) {");
        javaScript.append("\n$scope."+pageComponent.getVar()+"List=data;");
        javaScript.append("\n});}");
    }

    private void buildToggleMethod() {
        javaScript.append("\n;$scope."+pageComponent.getVar()+"Toggle=function(component){");
        javaScript.append("\n $http.post(\""+pageComponent.getToggleUri()+"\",JSON.stringify(component)).success(function (data) {");
        javaScript.append("\n $scope."+pageComponent.getVar()+"List=data;");
        javaScript.append("\n}); }");
    }

    private void buildGetListMethod() {
        javaScript.append("\n;$scope.get"+pageComponent.getVarU()+"List=function(){");
        javaScript.append("\n $http.get(\""+pageComponent.getListDataUri()+"\").success(function (data) {");
        javaScript.append("\n $scope."+pageComponent.getVar()+"List=data;");
        javaScript.append("\n});}");

    }

    private void buildRemoveItemMethod() {
        javaScript.append("\n;$scope.remove"+pageComponent.getVarU()+"Item=function(index){");
        javaScript.append("\n$scope."+pageComponent.getVar()+".items.splice(index,1);");
        javaScript.append("\n}");
    }

    private void buildInsertItemTopMethod() {
        javaScript.append("\n;$scope.insert"+pageComponent.getVarU()+"ItemBefore=function(index){");
        javaScript.append("\n$scope."+pageComponent.getVar()+".items.splice(index,0,{});");
        javaScript.append("\n}");
    }

    private void buildBackwardItemMethod() {
        javaScript.append("\n;$scope.backward"+pageComponent.getVarU()+"Item=function(index){");
        javaScript.append("\nvar item=$scope."+pageComponent.getVar()+".items[index];");
        javaScript.append("\n $scope."+pageComponent.getVar()+".items.splice(index,1);");
        javaScript.append("\n $scope."+pageComponent.getVar()+".items.splice(index+1,0,item);");
        javaScript.append("\n }");
    }

    private void buildForwardItemMethod() {
        javaScript.append("\n;$scope.forward"+pageComponent.getVarU()+"Item=function(index){");
        javaScript.append("\nvar item=$scope."+pageComponent.getVar()+".items[index];");
        javaScript.append("\n$scope."+pageComponent.getVar()+".items.splice(index,1);");
        javaScript.append("\n$scope."+pageComponent.getVar()+".items.splice(index-1,0,item);");
        javaScript.append("\n}");
    }

    private void buildSaveAsMethod() {
        javaScript.append("\n;$scope.new"+pageComponent.getVarU()+"=function(){");
        javaScript.append("\nvar name = window.prompt(\"请给方案命名\",\"新方案名\");");
        javaScript.append("\nif(!name) return;");
        javaScript.append("\n$scope."+pageComponent.getVar()+".name=name;");
        javaScript.append("\n$http.post(\""+pageComponent.getSaveAsUri()+"\",JSON.stringify( $scope."+pageComponent.getVar()+")).success(function (message) {");
        javaScript.append("\n$scope."+pageComponent.getVar()+"=message.data;");
        javaScript.append("\nif(message.success){");
        javaScript.append("\nalert(\"方案保存成功！\");");
        javaScript.append("\n }});}");

    }

    private void buildUpdateMethod() {
        javaScript.append("\n;$scope.save"+pageComponent.getVarU()+"=function(){");
        javaScript.append("\n$http.post(\""+pageComponent.getSaveUri()+"\",JSON.stringify($scope."+pageComponent.getVar()+")).success(function (message) {");
        javaScript.append("\n$scope."+pageComponent.getVar()+"=message.data;");
        javaScript.append("\nif(message.success){");
        javaScript.append("\nalert(\"保存成功！\");");
        javaScript.append("\n }");
        javaScript.append("\n});");
        javaScript.append("\n}");
    }

    private void buildGetMaterialMethod() {
        javaScript.append("\n;$scope.get"+pageComponent.getVarU()+"Material=function(){");
        javaScript.append("\n $http.get('"+pageComponent.getMaterialUri()+"').success(function (data) {");
        javaScript.append("\n $scope."+pageComponent.getVar()+"Images=data;");
        javaScript.append("\n});");
        javaScript.append("\n}");
    }

    private void buildResetMethod() {
        javaScript.append("\n;$scope.reset"+pageComponent.getVarU()+"=function(){");
        javaScript.append("\n $scope.get"+pageComponent.getVarU()+"();");
        javaScript.append("\n}");
    }

    private void buildGetMethod() {
          javaScript.append("\n;$scope.get"+pageComponent.getVarU()+"=function(){");
        javaScript.append("\n $http.get('"+pageComponent.getDataUri()+"').success(function (data) {");
        javaScript.append("\n$scope."+pageComponent.getVar()+"=data;");
        javaScript.append("\n});");
        javaScript.append("\n}");

    }

    public String getAdminJavaScript() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        buildAdminJavaScript();
        StringBuffer ret = new StringBuffer();
        ret.append("\n<script>")
                .append("\n(function () {\"use strict\"; var app = angular.module('app', []);")
                .append("\napp.controller('AdminController', [\"$rootScope\", \"$scope\", \"$http\", \"$location\",\"$window\",function ($rootScope, $scope, $http, $location, $window) {")
                .append(javaScript)
                .append("\n}])")
                .append("\n})()")
                .append("\n</script>");

        return ret.toString();
    }
    public String getWebsiteJavaScript() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        for(PageComponent pageComponent1:pageComponents){
            this.pageComponent=pageComponent1;
            buildGetMethod();
        }

        StringBuffer ret = new StringBuffer();
        ret.append("\n<script>")
                .append("\n(function () {\"use strict\"; var app = angular.module('app', []);")
                .append("\napp.controller('HomeController', [\"$rootScope\", \"$scope\", \"$http\", \"$location\",\"$window\",function ($rootScope, $scope, $http, $location, $window) {")
                .append(javaScript)
                .append("\n}])")
                .append("\n})()")
                .append("\n</script>");

        return ret.toString();
    }
    private void printItem(int level) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        String context=pageComponent.getVar();
        boolean fieldInScope=true;

        for(Field field:itemClass.getDeclaredFields()){
            printField(context,context, field,level,fieldInScope);
        }

    }

    private void printField(String context,String absoluteContext,Field field,int level,boolean fieldInScope) throws ClassNotFoundException {
        if (field.isAnnotationPresent(Id.class)) return;
        if (field.isAnnotationPresent(Transient.class)) return;
        if (!field.isAnnotationPresent(Naming.class)) return;
        Naming fieldNaming=field.getAnnotation(Naming.class);
        if (fieldNaming==null) return;
//        System.out.println(absoluteContext);


        Editable editable=field.getAnnotation(Editable.class);
        String fieldName=field.getName();
        field.setAccessible(true);
        if(editable!=null){
            InputType inputType=editable.inputType();
            if(InputType.IMAGE==inputType){
                printImageChooserDiv(context,absoluteContext, field,fieldInScope,fieldNaming);
            }else if(InputType.SELECT==inputType){
                printSelectDiv(context,fieldInScope,field, fieldNaming,editable);
            }else if(InputType.URL==inputType){
                printUrlInputGroup(context,fieldInScope, field, fieldNaming);
            }else {
                printTextInputGroup(context,fieldInScope,field, fieldNaming);
            }
        }else {//不可编辑的naming field,可能是Item或List,或者是只读文本
            if (Item.class.isAssignableFrom(field.getType())) {//field is an Item
                printItemHeader(fieldNaming);
                Class childItemClass=field.getType();
                for (Field childItemField : childItemClass.getDeclaredFields()) {
                    printField(context+"."+field.getName(),absoluteContext+"."+field.getName(), childItemField,level+1,true);
                }
            }else if (List.class.isAssignableFrom(field.getType())) {
                printItemListHeader(fieldNaming,fieldName,context,level);
                Type typeCls = field.getGenericType();
                ParameterizedType parameterizedType = (ParameterizedType) typeCls;
                Type actualType = parameterizedType.getActualTypeArguments()[0];
                Class clazz = Class.forName(actualType.getTypeName());
                String ngRepeatVar = fieldNaming.ngRepeatVar();
                if(StringUtils.isBlank(ngRepeatVar)){
                    ngRepeatVar=StringUtils.firstLowerCase(clazz.getSimpleName());
                }
                editorHtml.append("<div class='row p-t-xs p-b-md'  ng-repeat='" + ngRepeatVar + " in " + context + "." + field.getName() + "'>");

                String itemBorderCss="";
                if (level==1)   itemBorderCss="solid-silver-border p-a-lg";
                if (level==2)   itemBorderCss="solid-silver-border";
                editorHtml.append("<div class='col-xs-12 " + itemBorderCss + "'>");
                printItemOperationButtons(context,fieldName,level,absoluteContext);

                for (Field childField : clazz.getDeclaredFields()) {
                    printField(ngRepeatVar,ngRepeatVar, childField,level+1,false);
                }
                editorHtml.append("\n</div>");
                editorHtml.append("</div>");

            }else {//

                printReadOnlyField(context, fieldNaming, fieldName);

            }

        }
        if (Item.class.isAssignableFrom(field.getType())) {
//            editorHtml.append("\n</div>");//for 3
        }

//

    }

    private void printSelectDiv(String context,boolean inScope,Field field, Naming fieldNaming, Editable editable) {
        String[] options=editable.optionValues();
        if(options==null) return;
        String ng_if=getNgIfExpression(context,inScope,fieldNaming);
        if(ng_if==null){
            editorHtml.append("<div class=\"col-xs-12 p-b-xs\">");
        }else {
            editorHtml.append("<div class=\"col-xs-12 p-b-xs\" ng-if=\"" + ng_if + "\">");
        }

        editorHtml.append("<div class=\"input-group input-group-sm\">");
        editorHtml.append("<label class=\"input-group-addon fa fa-reorder\">");
        editorHtml.append(fieldNaming.value());
        editorHtml.append("</label>");
        editorHtml.append("<select type='select' ng-model=\"" + context + "." + field.getName() + "\" class='form-control'>");
        for(String option:options){
            JSONObject jsonObject=JSONObject.fromObject(option);
            editorHtml.append("<option value='" + jsonObject.get("value") + "'>" + jsonObject.get("text") + "</option>");

        }
        editorHtml.append("</select>");
        editorHtml.append("</div> ");
        editorHtml.append("</div> ");
        /**
          "{value:\"link\",text:\"链接\"}","{value:\"text\",text:\"文字\"}"



         */
    }

    private void printReadOnlyField(String context, Naming fieldNaming, String fieldName) {
        editorHtml.append("<div class='text-left label label-info large-110 fa fa-info-circle m-t-md m-b-md'> ");
        editorHtml.append(fieldNaming.value()).append(":{{"+context+"."+fieldName+"}}");
        editorHtml.append("</div>");
    }

    private void printItemHeader(Naming fieldNaming) {
        editorHtml.append("<div class='text-left label label-info col-xs-12 large-150 fa fa-edit m-t-md m-b-md'>编辑 ");
        editorHtml.append(fieldNaming.value());
        editorHtml.append(" </div>");
    }
    private void printItemListHeader(Naming fieldNaming,String fieldName,String context,int level) {
        String fontSizeCss="";
        if(level==1) fontSizeCss="large-150";
        else fontSizeCss="large-120";
        String addItemFunctionName="add"+StringUtils.firstUpperCase(context)+"Item";
//        System.out.println(addItemFunctionName);
        editorHtml.append("<div class='text-left label label-info col-xs-12 " + fontSizeCss + " fa fa-list m-t-md m-b-md'> ");
        editorHtml.append(fieldNaming.value());
        editorHtml.append("<span ng-if='!" + context + "." + fieldName + "' class='label label-info label-pill'>暂无数据</span>");
        editorHtml.append("<button class='btn btn-info btn-sm  pull-right' ng-click='" + addItemFunctionName + "(" + context + ")'>增加一项 <i class='fa fa-plus-circle'></i></button>");
        editorHtml.append("</div>");
        buildAddItemFunction(fieldName, context, addItemFunctionName);


    }

    private void buildAddItemFunction(String fieldName, String context, String addItemFunctionName) {
        javaScript.append("\n$scope."+addItemFunctionName+"=function("+context+"){");
        javaScript.append("\n   if(!"+context+"."+fieldName+"){");
        javaScript.append("\n       "+context+"."+fieldName+"=[]");
        javaScript.append("\n   }");
        javaScript.append("\n var item={}");
        javaScript.append("\n "+context+"."+fieldName+".splice(0,0,item);");
        javaScript.append("\n}");
    }


    private void printItemOperationButtons(String context,String fieldName,int level,String absoluteContext) {
        String deleteText="";
        String forwardText="";
        String backwardText="";
        String paddingRightCss="";
        String btnCss="";
        String btnGroupCss="";
        String deleteFunctionName="";
        String forwardFunctionName="";
        String backwardFunctionName="";
        if(level==1) {
            deleteText="删除整块";
            deleteFunctionName="remove"+StringUtils.firstUpperCase(context)+"Item($index)";
            forwardFunctionName="forward"+StringUtils.firstUpperCase(context)+"Item($index)";
            backwardFunctionName="backward"+StringUtils.firstUpperCase(context)+"Item($index)";
            forwardText="整块前移";
            backwardText="整块后移";
            paddingRightCss="p-r-0";
            btnCss="btn-primary";
        }else {
            deleteText="删除";
            forwardText="前移";
            backwardText="后移";
            paddingRightCss="p-r-xl";
            btnCss="btn-info";
            btnGroupCss="btn-group-sm";
            deleteFunctionName="remove"+StringUtils.firstUpperCase(context)+"Item("+context+",$index)";
            forwardFunctionName="forward"+StringUtils.firstUpperCase(context)+"Item("+context+",$index)";
            backwardFunctionName="backward"+StringUtils.firstUpperCase(context)+"Item("+context+",$index)";
            buildForwardSubItemMethod(context,fieldName);
            buildBackwardSubItemMethod(context, fieldName);
            buildRemoveSubItemMethod(context, fieldName);
        }
        editorHtml.append("<div class='btn-group " + btnGroupCss + " pull-right p-b-xs p-t-xs " + paddingRightCss + "'>");
        editorHtml.append("<button class='fa fa-trash btn " + btnCss + "' ng-click=\"" + deleteFunctionName + "\" >" + deleteText + "</button>");
        editorHtml.append("<button class='fa fa-caret-up btn " + btnCss + "' ng-click='" + forwardFunctionName + "' ng-if='$index!==0'>" + forwardText + "</button>");
        editorHtml.append("<button class='fa fa-caret-down btn " + btnCss + "' ng-click='" + backwardFunctionName + "' ng-if='$index!==" + context + ".items.length-1'>" + backwardText + "</button>");
//        editorHtml.append("<button ng-init=\"showItems=true\" class='fa btn " + btnCss + "'")
//                .append(" ng-click=\"showItems=!showItems\" >{{showItems?'收起':'展开'}}<i ng-class=\"{'fa-plus-square-o':!showItems,'fa-minus-square-o':showItems}\" ></i></button>");
        editorHtml.append("   </div>");
    }
    private void buildForwardSubItemMethod(String context, String fieldName) {

        javaScript.append("\n$scope.forward"+StringUtils.firstUpperCase(context)+"Item=function("+context+",index){");
        javaScript.append("\n var item="+context+"."+fieldName+"[index];");
        javaScript.append("\n "+context+"."+fieldName+".splice(index,1);");
        javaScript.append("\n "+context+"."+fieldName+".splice(index-1,0,item);");
        javaScript.append("\n}");


    }
    private void buildRemoveSubItemMethod(String context, String fieldName) {
        javaScript.append("\n$scope.remove"+StringUtils.firstUpperCase(context)+"Item=function("+context+",index){");
        javaScript.append("\n "+context+"."+fieldName+".splice(index,1);");
        javaScript.append("\n}");
    }

    private void buildBackwardSubItemMethod(String context, String fieldName) {
        javaScript.append("\n$scope.backward"+StringUtils.firstUpperCase(context)+"Item=function("+context+",index){");
        javaScript.append("\n var item="+context+"."+fieldName+"[index];");
        javaScript.append("\n "+context+"."+fieldName+".splice(index,1);");
        javaScript.append("\n "+context+"."+fieldName+".splice(index+1,0,item);");
        javaScript.append("\n}");
    }


    private void printTextInputGroup(String context,boolean inScope,Field field, Naming fieldNaming) {
        String ng_if=getNgIfExpression(context,inScope,fieldNaming);
        if(ng_if==null){
            editorHtml.append("\n<div class='col-xs-12 p-b-xs'>");
        }else {
            editorHtml.append("\n<div class='col-xs-12 p-b-xs' ng-if=\"" + ng_if + "\">");
        }

        editorHtml.append("\n<div class='input-group input-group-sm'>");
        editorHtml.append("\n    <label class=\"input-group-addon fa fa-edit\">" + fieldNaming.value() + "</label>");
        editorHtml.append("\n<input class='form-control' type='text' ng-model='" + context + "." + field.getName() + "'/>");
        editorHtml.append("\n</div>");
        editorHtml.append("\n</div>");
    }
    private void printUrlInputGroup(String context,boolean inScope,Field field, Naming fieldNaming) {
        String ng_if=getNgIfExpression(context,inScope,fieldNaming);
        if(ng_if==null){
            editorHtml.append("\n<div class='col-xs-12 p-b-xs'>");
        }else {
            editorHtml.append("\n<div class='col-xs-12 p-b-xs' ng-if=\"" + ng_if + "\">");
        }

        editorHtml.append("\n<div class='input-group input-group-sm'>");
        editorHtml.append("\n    <label class=\"input-group-addon fa fa-anchor\">" + fieldNaming.value() + "</label>");
        editorHtml.append("\n<input class='form-control' type='url' ng-model='" + context + "." + field.getName() + "'/>");
        editorHtml.append("\n    <a class=\"input-group-addon fa fa-question\" ng-href=\"/admin/article/list\" target=\"_blank\"></a>");
        editorHtml.append("\n</div>");
        editorHtml.append("\n</div>");
    }
    private void printImageChooserDiv(String context, String absoluteContext, Field field, boolean fieldInScope,Naming fieldNaming) {
        String ng_condition = getNgIfExpression(context,fieldInScope,fieldNaming);

        String clearImageFunction="clear"+StringUtils.firstUpperCase(context)+StringUtils.firstUpperCase(field.getName());
        if (ng_condition==null){
            editorHtml.append("\n<div class='col-xs-12 p-b-xs'>");
        }else {
            editorHtml.append("\n<div class='col-xs-12 p-b-xs' ng-if=\"" + ng_condition + "\">");
        }

        editorHtml.append("\n <div class=\"btn-group col-xs-12  p-l-0 m-l-0\" >");
//        editorHtml.append("\n     <button type=\"button\" class=\"btn btn-secondary btn-sm m-l-0 fa fa-times\" ng-click=\""+clearImageFunction+"("+context+")\">清除图片</button>");
        editorHtml.append("\n     <button type=\"button\" class=\"btn btn-secondary btn-sm m-l-0 fa fa-image\">更换图片</button>");
        editorHtml.append("\n     <button type=\"button\" class=\"btn btn-secondary dropdown-toggle btn-sm\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">");
        editorHtml.append("\n         <span class=\"sr-only\">Toggle Dropdown</span>");
        editorHtml.append("\n     </button>");

        if(fieldInScope){
            editorHtml.append("\n     <img ng-if='" + absoluteContext + "." + field.getName() + "' ng-src='{{" + absoluteContext + "." + field.getName() + "}}' class='img-ico-md'/>");
        }else {
            editorHtml.append("\n     <img ng-if='" + context + "." + field.getName() + "' ng-src='{{" + context + "." + field.getName() + "}}' class='img-ico-md'/>");
        }


        editorHtml.append("\n <div class=\"dropdown-menu bg-light-grey\">");
        if(fieldInScope){
            editorHtml.append("\n     <span ng-repeat=\"icon in " + pageComponent.getVar() + "Images\" class=\"dropdown-item-inline\" ng-click=\"" + absoluteContext + "." + field.getName() + "=icon\">");
        }else {
            editorHtml.append("\n     <span ng-repeat=\"icon in " + pageComponent.getVar() + "Images\" class=\"dropdown-item-inline\" ng-click=\"" + context + "." + field.getName() + "=icon\">");
        }

        editorHtml.append("\n         <img type=\"text\" ng-src=\"{{icon}}\" class=\"img-ico-larger img-rounded\"/>");
        editorHtml.append("\n     </span>");
        editorHtml.append("\n </div>");
        editorHtml.append("\n </div>");
        editorHtml.append("\n</div>");

//        if(fieldInScope){
//            javaScript.append("\n$scope."+clearImageFunction+"=function(){");
//            javaScript.append("\n$scope." + absoluteContext + "." + field.getName() + "='';");
//        }else{
//            javaScript.append("\n$scope."+clearImageFunction+"=function("+context+"){");
//            javaScript.append("\n" + context + "." + field.getName() + "='';");
//        }

//        javaScript.append( "\n}");
    }

    private String getNgIfExpression(String context,boolean fieldInScope , Naming fieldNaming) {
        String ng_field=fieldNaming.when();
        Expression expression=fieldNaming.expression();
        String[] params=fieldNaming.params();
        String ng_condition=null;
        if (StringUtils.isNotBlank(ng_field)){
            if (Expression.IS_NOT_EMPTY ==expression){
                ng_condition=context+"."+ng_field;
            }else if (Expression.IS_EMPTY ==expression){
                ng_condition="!"+context+"."+ng_field+"||!"+context+"."+ng_field+".length";
            }else if (Expression.EQ==expression){
                if (params!=null&&params.length>0){
                    ng_condition=context+"."+ng_field+"==='"+params[0]+"'";
                }

            }else if (Expression.NE==expression){
                if (params!=null&&params.length>0){
                    ng_condition=context+"."+ng_field+"!=='"+params[0]+"'";
                }

            }else if (Expression.GET==expression){
                if (params!=null&&params.length>0){
                    ng_condition=context+"."+ng_field+">="+params[0];
                }

            }else if (Expression.GT==expression){
                if (params!=null&&params.length>0){
                    ng_condition=context+"."+ng_field+">"+params[0];
                }

            }else if (Expression.LET==expression){
                if (params!=null&&params.length>0){
                    ng_condition=context+"."+ng_field+"<="+params[0];
                }

            }else if (Expression.LT==expression){
                if (params!=null&&params.length>0){
                    ng_condition=context+"."+ng_field+"<"+params[0];
                }

            }else if (Expression.WITH_LENGTH==expression){
                ng_condition=context+"."+ng_field+"&&"+context+"."+ng_field+".length";
            }else if (Expression.WITHOUT_LENGTH==expression){
                ng_condition="!"+context+"."+ng_field+"||!"+context+"."+ng_field+".length";
            }
        }
            return ng_condition;
    }

    private void commonOperationsHtml() throws NoSuchFieldException, IllegalAccessException {
            Field fangAnField=itemClass.getDeclaredField("name");
            fangAnField.setAccessible(true);

            editorHtml.append("\n<div class=\"row m-a-0 p-a-0\" ng-init=\"get" + pageComponent.getVarU() + "Material()\">");
            editorHtml.append("\n   <div class=\"btn-group p-b-10\">");
        String itemName=itemNaming==null?StringUtils.firstLowerCase(itemClass.getSimpleName()):itemNaming.value();
        editorHtml.append("\n<label class=\"btn btn-info cursor-auto\">编辑" + itemName + "</label>");
//            editorHtml.append("\n       <label class=\"btn btn-info cursor-auto\">当前方案：" + fangAnField.get(rootItem)+"</label>");
            editorHtml.append("\n       <button class=\"btn btn-danger fa fa-save \" type=\"button\" ng-click=\"save" + pageComponent.getVarU() + "()\" >保存</button>");
            editorHtml.append("\n       <button class=\"btn btn-primary fa fa-copy\" type=\"button\" ng-click=\"new" + pageComponent.getVarU() + "()\" >方案另存为</button>");
            editorHtml.append("\n       <a class=\"btn btn-primary fa fa-download white-link\" ng-href=\"/admin/list-page/" +  pageComponent.getId() + "\">应用方案</a>");
            editorHtml.append("\n       <a class=\"btn btn-primary fa fa-camera white-link\" ng-href=\"" + pageComponent.getMaterialUploadUri() + "/" + pageComponent.getId() + "\"> 上传素材</a>");
            editorHtml.append("\n       <button class=\"btn btn-primary fa fa-refresh\" type=\"button\" ng-click=\"reset" + pageComponent.getVarU() + "()\">重置</button>");
            editorHtml.append("\n   </div>");
            editorHtml.append("\n</div>");
            editorHtml.append("\n<div class=\"row\">");
            editorHtml.append("\n   <div class=\"alert alert-warning\">");
            editorHtml.append("\n       <ul class=\"list-unstyled\">");
//            editorHtml.append("\n           <li><i class=\"fa fa-warning\"></i> “另存方案”后，如果想应用该方案，可点击“应用其它方案”；</li>");
//            editorHtml.append("\n           <li><i class=\"fa fa-warning\"></i> 修改导航项名称，链接，更换图标以及“前面插入一条”、“删除词条”仅在客户端修改，点击上方的“保存”按钮才会保存修改。</li>");
        if (pageComponent.getEditNotes()!=null)
        for(String note:pageComponent.getEditNotes()){
                editorHtml.append("\n           <li><i class=\"fa fa-warning\"></i>" + note + "</li>");
            }
            editorHtml.append("\n       </ul>");
            editorHtml.append("\n   </div>");
            editorHtml.append("\n</div>");

    }
    public String getEditHtml(){
        return editorHtml.toString();
    }

    public String getListOperationHtml() {
        listOperationHtml.append("<div class=\"container-" +
                "fluid\" ng-controller=\"AdminController\" ng-init=\"get"+pageComponent.getVarU()+"List()\">");
        listOperationHtml.append("<div class=\"row\">");
        printListPageHeader();
        printTable();

        listOperationHtml.append("</div>");
        listOperationHtml.append("</div>");
        return listOperationHtml.toString();
    }
    public String getListOperationJavascript() {

        listOperationJavaScript.append("\n$scope.get"+pageComponent.getVarU()+"List=function(){");
        listOperationJavaScript.append("\n$http.get(\""+pageComponent.getListDataUri()+"\").success(function (data) {");
        listOperationJavaScript.append("\n$scope."+pageComponent.getVar()+"List=data;");
        listOperationJavaScript.append("\n});");
        listOperationJavaScript.append("\n}");
        //启用/禁用
        listOperationJavaScript.append("\n$scope."+pageComponent.getVar()+"Toggle=function("+pageComponent.getVar()+"){");
        listOperationJavaScript.append("\n$http.post(\""+pageComponent.getToggleUri()+"\",JSON.stringify("+pageComponent.getVar()+")).success(function (data) {");
        listOperationJavaScript.append("\n$scope."+pageComponent.getVar()+"List=data;");
        listOperationJavaScript.append("\n });");
        listOperationJavaScript.append("\n}");
        //删除
        listOperationJavaScript.append("\n$scope.delete"+pageComponent.getVarU()+"=function("+pageComponent.getVar()+"){");
        listOperationJavaScript.append("\nif(!confirm(\"确定删除?\"))");
        listOperationJavaScript.append("\nreturn ;");
        listOperationJavaScript.append("\n$http.post(\"" + pageComponent.getDeleteUri() +"\"+"+ pageComponent.getVar()+".id" + ",JSON.stringify("+pageComponent.getVar()+")).success(function (data) {");
        listOperationJavaScript.append("\n$scope." + pageComponent.getVar()+"List=data;");
        listOperationJavaScript.append("\n});");
        listOperationJavaScript.append("\n}");
        StringBuffer ret = new StringBuffer();
        ret.append("\n<script>")
                .append("\n(function () {\"use strict\"; var app = angular.module('app', []);")
                .append("\napp.controller('AdminController', [\"$rootScope\", \"$scope\", \"$http\", \"$location\",\"$window\",function ($rootScope, $scope, $http, $location, $window) {")
                .append(listOperationJavaScript)
                .append("\n}])")
                .append("\n})()")
                .append("\n</script>");

        return ret.toString();

    }
    private void printTable() {
        listOperationHtml.append("<table class=\"table table-hover col-xs-12\">");
        printTableHeader();
        printTableData();
        listOperationHtml.append("</table>");
    }

    private void printTableData() {
        listOperationHtml.append("<tr ng-repeat=\""+pageComponent.getVar() +" in "+pageComponent.getVar()+"List\">");
        for(Field field:itemClass.getDeclaredFields()){
            if(!field.isAnnotationPresent(ListColumn.class)){
                continue;
            }
            ListColumn column=field.getAnnotation(ListColumn.class);
            String columnName=column.columnName();

            listOperationHtml.append("<td>");
            InputType inputType=column.inputType();
            if (field.getType().isPrimitive() || ReflectUtil.isWrapClass(field.getType()) ||field.getType()==String.class|| field.getType()==Date.class){
                if (inputType== InputType.DATE||field.getType()==Date.class){
                    listOperationHtml.append("{{"+pageComponent.getVar()+"."+field.getName()+"|date:'yy-MM-dd'}}");
                }else if(field.getType()==boolean.class||field.getType()==Boolean.class){
                    if(!field.getName().equals("enabled")){
                        listOperationHtml.append("{{"+pageComponent.getVar()+"."+field.getName()+"?\"是\":\"否\"}}");
                    }else {
                        listOperationHtml.append("<span ng-class=\"{'text-danger':!"+pageComponent.getVar()+"."+field.getName()+",'text-primary':"+pageComponent.getVar()+"."+field.getName()+"}\">{{"+pageComponent.getVar()+"."+field.getName()+"?\"启用\":\"禁用\"}}</span>");
                    }

                }else{
                    listOperationHtml.append("{{"+pageComponent.getVar()+"."+field.getName()+"}}");
                }

            }else {
                String fieldOfValue=column.fieldOfValue();



                if (List.class.isAssignableFrom(field.getType())){
                    listOperationHtml.append("<div ng-repeat=\"item in "+pageComponent.getVar()+"."+field.getName()+"\">");

                    if (inputType==InputType.IMAGE){
                        listOperationHtml.append("<img ng-src=\"{{item."+fieldOfValue+"}}\" class=\"img-ico-md col-xs-3 p-a-1 m-a-0\"/>");
                    }else{
                        listOperationHtml.append("{{item."+fieldOfValue+"}}");
                    }

                    listOperationHtml.append("</div>");
                }else{
                    listOperationHtml.append("{{"+pageComponent.getVar()+"."+field.getName()+"."+fieldOfValue+"}}");
                }

            }

            listOperationHtml.append("</td>");
        }
        listOperationHtml.append("<td>");
        listOperationHtml.append("  <div class='btn-group btn-group-sm'>");
        listOperationHtml.append("      <button class=\"btn btn-danger p-t-0 p-b-0 fa fa-trash\" ng-click=\"delete" + pageComponent.getVarU()+"("+pageComponent.getVar()+")\">");
        listOperationHtml.append("          删除");
        listOperationHtml.append("      </button>");
        listOperationHtml.append("      <button class=\"btn no-bg p-t-0 p-b-0 fa \" ")
                .append("ng-class=\"{'btn-danger fa-toggle-off':!" + pageComponent.getVar() + ".enabled" + ",'btn-primary fa-toggle-on':" + pageComponent.getVar()+".enabled"+"}\"")
                .append(" ng-click=\""+pageComponent.getVar()+"Toggle("+pageComponent.getVar()+")\">");
        listOperationHtml.append("          开关");
        listOperationHtml.append("      </button>");
        listOperationHtml.append("  </div>");
        listOperationHtml.append("</td>");
    }

    private void printTableHeader() {
        listOperationHtml.append("<tr>");
        for(Field field:itemClass.getDeclaredFields()){
            if(!field.isAnnotationPresent(ListColumn.class)){
                continue;
            }
            ListColumn column=field.getAnnotation(ListColumn.class);
            String columnName=column.columnName();
            String fieldOfValue=column.fieldOfValue();
            listOperationHtml.append("<th>");
            listOperationHtml.append(columnName);
            listOperationHtml.append("</th>");
        }
        listOperationHtml.append("<th>");
        listOperationHtml.append("操作");
        listOperationHtml.append("</th>");
        listOperationHtml.append("</tr>");
    }

    private void printListPageHeader() {

        listOperationHtml.append("<div class=\"alert alert-info\">");
        listOperationHtml.append(" <h5 class=\"text-center\">应用 "+pageComponent.getName()+" 方案</h5>");
        listOperationHtml.append("<a class=\"fa fa-reply btn btn-primary btn-sm white-link\" href=\"/admin/page-component/edit/"+pageComponent.getId()+"\">返回编辑页</a>");
        listOperationHtml.append("</div>");



        listOperationHtml.append("<div class=\"alert alert-warning\">");
        listOperationHtml.append("<ul class=\"list-unstyled\">");
        listOperationHtml.append("<li><i class=\"fa fa-graduation-cap fa-fw\"></i>如果多个方案都为“可用”状态，我们只会应用查到的第一个方案，为了确保使用到正确的方案，请把不用的其它方案设为“禁用”。</li>");
        listOperationHtml.append("</ul>");
        listOperationHtml.append("</div>");
    }
}
