package com.lanzuan.common.web;

import com.lanzuan.common.code.InputType;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.entity.support.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


public class AngularEntityEditorBuilder {
    private StringBuffer html;
    private StringBuffer javaScript;
    private Class<? extends Item> itemClass;

    private  Naming itemNaming;
    private PageComponent<RootItem> pageComponent;
    private AngularEntityEditorBuilder(){

    }
    public AngularEntityEditorBuilder(PageComponent pageComponent){
        this.html=new StringBuffer();
        this.javaScript=new StringBuffer();
        this.itemClass=pageComponent.getData().getClass();
        this.itemNaming= itemClass.getAnnotation(Naming.class);

        this.pageComponent=pageComponent;


    }
    public void buildHtml() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        commonOperationsHtml();
        printItem(1);
    }


    private void buildJavaScript() {
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

    public String getJavaScript() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        buildJavaScript();
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
    private void printItem(int level) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        String context=pageComponent.getVar();

        for(Field field:itemClass.getDeclaredFields()){
            printField(context,context, field,level);
        }

    }

    private void printField(String context,String absoluteContext,Field field,int level) throws ClassNotFoundException {
        if (field.isAnnotationPresent(Id.class)) return;
        if (field.isAnnotationPresent(Transient.class)) return;
        if (!field.isAnnotationPresent(Naming.class)) return;
        Naming fieldNaming=field.getAnnotation(Naming.class);
        if (fieldNaming==null) return;
        System.out.println(absoluteContext);

        Class fieldClass=field.getClass();
        Editable editable=field.getAnnotation(Editable.class);
        String fieldName=field.getName();
        field.setAccessible(true);

        if(editable!=null){
            InputType inputType=editable.inputType();

            if(InputType.IMAGE==inputType){
                printImageChooserDiv(context,absoluteContext, field);

            }else if(InputType.SELECT==inputType){
                printSelectDiv(context, field, fieldNaming,editable);
            }else{
                printTextInputGroup(context, field, fieldNaming);
            }

        }else {//不可编辑的naming field,可能是Item或List,或者是只读文本

            if (Item.class.isAssignableFrom(field.getType())) {//field is an Item
                printItemHeader(fieldNaming);
                Class childItemClass=field.getType();
                for (Field childItemField : childItemClass.getDeclaredFields()) {
                    printField(context,absoluteContext+"."+field.getName(), childItemField,level+1);

                }
            }else if (List.class.isAssignableFrom(field.getType())) {
                printItemListHeader(fieldNaming,fieldName,context,level);

                String ngRepeatVar = fieldNaming.ngRepeatVar();
                if(StringUtils.isBlank(ngRepeatVar)){
                    ngRepeatVar=fieldName;
                }
                Type typeCls = field.getGenericType();
                ParameterizedType parameterizedType = (ParameterizedType) typeCls;
                Type actualType = parameterizedType.getActualTypeArguments()[0];
                Class clazz = Class.forName(actualType.getTypeName());

                html.append("<div class='row p-t-xs p-b-md'  ng-repeat='" + ngRepeatVar + " in " + context + "." + field.getName() + "'>");

                String itemBorderCss="";
                if (level==1)   itemBorderCss="solid-silver-border p-a-lg";
                if (level==2)   itemBorderCss="solid-silver-border";
                html.append("<div class='col-xs-12 " + itemBorderCss + "'>");
                printItemOperationButtons(context,fieldName,level,absoluteContext);

                for (Field childField : clazz.getDeclaredFields()) {
                    printField(ngRepeatVar,absoluteContext+"."+ngRepeatVar, childField,level+1);
                }
                html.append("\n</div>");
                html.append("</div>");

            }else {//

                printReadOnlyField(context, fieldNaming, fieldName);

            }

        }
        if (Item.class.isAssignableFrom(field.getType())) {
//            html.append("\n</div>");//for 3
        }

//

    }

    private void printSelectDiv(String context, Field field, Naming fieldNaming, Editable editable) {
        String[] options=editable.optionValues();
        if(options==null) return;
        html.append("<div class=\"col-xs-12 p-b-xs\">");
        html.append("<div class=\"input-group input-group-sm\">");
        html.append("<label class=\"input-group-addon\">");
        html.append(fieldNaming.value());
        html.append("</label>");
        html.append("<select type='select' ng-model=\""+context+"."+field.getName()+"\" class='form-control'>");
        for(String option:options){
            JSONObject jsonObject=JSONObject.fromObject(option);
            html.append("<option value='" + jsonObject.get("value")+"'>"+jsonObject.get("text")+"</option>");

        }
        html.append("</select>");
        html.append("</div> ");
        html.append("</div> ");
        /**
          "{value:\"link\",text:\"链接\"}","{value:\"text\",text:\"文字\"}"



         */
    }

    private void printReadOnlyField(String context, Naming fieldNaming, String fieldName) {
        html.append("<div class='text-left label label-info large-110 fa fa-info-circle m-t-md m-b-md'> ");
        html.append(fieldNaming.value()).append(":{{"+context+"."+fieldName+"}}");
        html.append("</div>");
    }

    private void printItemHeader(Naming fieldNaming) {
        html.append("<div class='text-left label label-info col-xs-12 large-150 fa fa-edit m-t-md m-b-md'>编辑 ");
        html.append(fieldNaming.value());
        html.append(" </div>");
    }
    private void printItemListHeader(Naming fieldNaming,String fieldName,String context,int level) {
        String fontSizeCss="";
        if(level==1) fontSizeCss="large-150";
        else fontSizeCss="large-120";
        String addItemFunctionName="add"+StringUtils.firstUpperCase(context)+"Item";
        System.out.println(addItemFunctionName);
        html.append("<div class='text-left label label-info col-xs-12 " + fontSizeCss + " fa fa-list m-t-md m-b-md'> ");
        html.append(fieldNaming.value());
        html.append("<span ng-if='!"+context+"."+fieldName+"' class='label label-info label-pill'>暂无数据</span>");
        html.append("<button class='btn btn-info btn-sm  pull-right' ng-click='"+addItemFunctionName+"("+context+")'>增加一项 <i class='fa fa-plus-circle'></i></button>");
        html.append("</div>");
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
        html.append("<div class='btn-group " + btnGroupCss + " pull-right p-b-xs p-t-xs " + paddingRightCss + "'>");
        html.append("<button class='fa fa-trash btn " + btnCss + "' ng-click=\"" + deleteFunctionName + "\" >" + deleteText + "</button>");
        html.append("<button class='fa fa-caret-up btn " + btnCss + "' ng-click='" + forwardFunctionName + "' ng-if='$index!==0'>" + forwardText + "</button>");
        html.append("<button class='fa fa-caret-down btn " + btnCss + "' ng-click='" + backwardFunctionName + "' ng-if='$index!==" + context + ".items.length-1'>" + backwardText + "</button>");
//        html.append("<button ng-init=\"showItems=true\" class='fa btn " + btnCss + "'")
//                .append(" ng-click=\"showItems=!showItems\" >{{showItems?'收起':'展开'}}<i ng-class=\"{'fa-plus-square-o':!showItems,'fa-minus-square-o':showItems}\" ></i></button>");
        html.append("   </div>");
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


    private void printTextInputGroup(String context, Field field, Naming fieldNaming) {

        html.append("\n<div class='col-xs-12 p-b-xs'>");
        html.append("\n<div class='input-group input-group-sm'>");
        html.append("\n    <label class=\"input-group-addon fa fa-edit\">" + fieldNaming.value() + "</label>");
        html.append("\n<input class='form-control' type='text' ng-model='" + context + "." + field.getName() + "'/>");
        html.append("\n</div>");
        html.append("\n</div>");
    }

    private void printImageChooserDiv(String context,String absoluteContext, Field field) {
        String clearImageFunction="clear"+StringUtils.firstUpperCase(context)+StringUtils.firstUpperCase(field.getName());
        html.append("\n<div class='col-xs-12 p-b-xs'>");
        html.append("\n <div class=\"btn-group col-xs-12  p-l-0 m-l-0\" >");
        html.append("\n     <button type=\"button\" class=\"btn btn-secondary btn-sm m-l-0 fa fa-times\" ng-click=\""+clearImageFunction+"("+context+")\">清除图片</button>");
        html.append("\n     <button type=\"button\" class=\"btn btn-secondary btn-sm m-l-0 fa fa-image\">更换图片</button>");
        html.append("\n     <button type=\"button\" class=\"btn btn-secondary dropdown-toggle btn-sm\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">");
        html.append("\n         <span class=\"sr-only\">Toggle Dropdown</span>");
        html.append("\n     </button>");
        html.append("\n     <img ng-if='"+ absoluteContext + "." + field.getName()+"' ng-src='{{" + absoluteContext + "." + field.getName() + "}}' class='img-ico-md'/>");

        html.append("\n <div class=\"dropdown-menu bg-light-grey\">");
        html.append("\n     <span ng-repeat=\"icon in " + pageComponent.getVar() + "Images\" class=\"dropdown-item-inline\" ng-click=\"" + context + "." + field.getName() + "=icon\">");
        html.append("\n         <img type=\"text\" ng-src=\"{{icon}}\" class=\"img-ico-larger img-rounded\"/>");
        html.append("\n     </span>");
        html.append("\n </div>");
        html.append("\n </div>");
        html.append("\n</div>");
        javaScript.append("\n$scope."+clearImageFunction+"=function(){\nalert('fuccccc');$scope."+absoluteContext+"."+field.getName()+"='';\n}");
    }

    private void commonOperationsHtml() throws NoSuchFieldException, IllegalAccessException {
            Field fangAnField=itemClass.getDeclaredField("name");
            fangAnField.setAccessible(true);

            html.append("\n<div class=\"row m-a-0 p-a-0\" ng-init=\"get" + pageComponent.getVarU() + "Material()\">");
            html.append("\n   <div class=\"btn-group p-b-10\">");
        String itemName=itemNaming==null?StringUtils.firstLowerCase(itemClass.getSimpleName()):itemNaming.value();
        html.append("\n<label class=\"btn btn-info cursor-auto\">编辑" + itemName + "</label>");
//            html.append("\n       <label class=\"btn btn-info cursor-auto\">当前方案：" + fangAnField.get(rootItem)+"</label>");
            html.append("\n       <button class=\"btn btn-danger fa fa-save \" type=\"button\" ng-click=\"save" + pageComponent.getVarU() + "()\" >保存</button>");
            html.append("\n       <button class=\"btn btn-primary fa fa-copy\" type=\"button\" ng-click=\"new" + pageComponent.getVarU() + "()\" >方案另存为</button>");
            html.append("\n       <a class=\"btn btn-primary fa fa-download white-link\" ng-href=\"" + pageComponent.getListOperationUri() + pageComponent.getId() + "\">应用方案</a>");
            html.append("\n       <a class=\"btn btn-primary fa fa-camera white-link\" ng-href=\"" + pageComponent.getMaterialUploadUri() + "/" + pageComponent.getId() + "\"> 上传素材</a>");
            html.append("\n       <button class=\"btn btn-primary fa fa-refresh\" type=\"button\" ng-click=\"reset" + pageComponent.getVarU() + "()\">重置</button>");
            html.append("\n   </div>");
            html.append("\n</div>");
            html.append("\n<div class=\"row\">");
            html.append("\n   <div class=\"alert alert-warning\">");
            html.append("\n       <ul class=\"list-unstyled\">");
            html.append("\n           <li><i class=\"fa fa-warning\"></i> 如果没有合适的图标，您可以先<a href='" + pageComponent.getMaterialUploadUri() + "/" + pageComponent.getId() + "' style=\"text-decoration: underline;\"><i>上传素材</i></a>；</li>");
            html.append("\n           <li><i class=\"fa fa-warning\"></i> “另存方案”后，如果想应用该方案，可点击“应用其它方案”；</li>");
            html.append("\n           <li><i class=\"fa fa-warning\"></i> 修改导航项名称，链接，更换图标以及“前面插入一条”、“删除词条”仅在客户端修改，点击上方的“保存”按钮才会保存修改。</li>");
            html.append("\n       </ul>");
            html.append("\n   </div>");
            html.append("\n</div>");

    }
    public String getHtml(){
        return html.toString();
    }



}
