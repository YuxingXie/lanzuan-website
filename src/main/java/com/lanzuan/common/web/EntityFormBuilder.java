package com.lanzuan.common.web;

import com.lanzuan.common.base.annotation.entity.FormAttributes;
import com.lanzuan.entity.support.Item;
import com.lanzuan.common.base.annotation.entity.Naming;
import com.lanzuan.common.code.Expression;
import com.lanzuan.common.code.InputType;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.CardGroup;
import net.sf.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


public class EntityFormBuilder {
    private StringBuffer formHtml;
    private Class<?> itemClass;

    private  String itemNaming;
    private String itemVar;
    private EntityFormBuilder(){

    }
    public EntityFormBuilder(Class<?> itemClass) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        this.formHtml =new StringBuffer();
        this.itemClass=itemClass;
        this.itemVar=StringUtils.firstLowerCase(itemClass.getSimpleName());
        Assert.isTrue(itemClass.isAnnotationPresent(Document.class));
        if (itemClass.isAnnotationPresent(Naming.class)){
            itemNaming=itemClass.getDeclaredAnnotation(Naming.class).value();
        }else {
            itemNaming=itemClass.getSimpleName();
        }
        printItem(1);
    }
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        EntityFormBuilder builder=new EntityFormBuilder(Class.forName("com.lanzuan.entity.User"));
        System.out.println(builder.getFormHtml());
    }



    private void printItem(int level) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        String context=itemVar;
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


        FormAttributes formAttributes =field.getAnnotation(FormAttributes.class);
        String fieldName=field.getName();
        field.setAccessible(true);
        if(formAttributes !=null){
            InputType inputType= formAttributes.inputType();
            if(InputType.IMAGE==inputType){
                printImageChooserDiv(context,absoluteContext, field,fieldInScope,fieldNaming);
            }else if(InputType.SELECT==inputType){
                printSelectDiv(context,fieldInScope,field, fieldNaming, formAttributes);
            }else if(InputType.URL==inputType){
                printUrlInputGroup(context,fieldInScope, field, fieldNaming);
            }else if(InputType.PASSWORD==inputType){
                printPasswordInputGroup(context, fieldInScope, field, fieldNaming);
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
                formHtml.append("<div class='row p-t-xs p-b-md'  ng-repeat='" + ngRepeatVar + " in " + context + "." + field.getName() + "'>");

                String itemBorderCss="";
                if (level==1)   itemBorderCss="solid-silver-border p-a-lg";
                if (level==2)   itemBorderCss="solid-silver-border";
                formHtml.append("<div class='col-xs-12 " + itemBorderCss + "'>");
                String toggleVarName=printItemOperationButtons(context,fieldName,level,absoluteContext);
                formHtml.append("<div ng-if=\"" + toggleVarName + "\">");
                for (Field childField : clazz.getDeclaredFields()) {
                    printField(ngRepeatVar,ngRepeatVar, childField,level+1,false);
                }
                formHtml.append("\n</div>");
                formHtml.append("\n</div>");
                formHtml.append("</div>");

            }else {

            }

        }


//

    }

    private void printPasswordInputGroup(String context, boolean fieldInScope, Field field, Naming fieldNaming) {

        formHtml.append("\n<div class='input-group input-group-sm p-a-xs'>");
        formHtml.append("\n    <label class=\"input-group-addon fa fa-lock\">" + fieldNaming.value() + "</label>");
        formHtml.append("\n<input class='form-control' type='password' ng-model='" + context + "." + field.getName() + "' name='"+field.getName()+"'/>");

        formHtml.append("\n</div>");



    }

    private void printSelectDiv(String context,boolean inScope,Field field, Naming fieldNaming, FormAttributes formAttributes) {
        String[] options= formAttributes.optionValues();
        if(options==null) return;
        String ng_if=getNgIfExpression(context,inScope,fieldNaming);
        if(ng_if==null){
            formHtml.append("<div class=\"col-xs-12 p-b-xs\">");
        }else {
            formHtml.append("<div class=\"col-xs-12 p-b-xs\" ng-if=\"" + ng_if + "\">");
        }

        formHtml.append("<div class=\"input-group input-group-sm\">");
        formHtml.append("<label class=\"input-group-addon fa fa-reorder\">");
        formHtml.append(fieldNaming.value());
        formHtml.append("</label>");
        formHtml.append("<select type='select' ng-model=\"" + context + "." + field.getName() + "\" class='form-control' name='"+field.getName()+"'>");
        for(String option:options){
            JSONObject jsonObject=JSONObject.fromObject(option);
            formHtml.append("<option value='" + jsonObject.get("value") + "'>" + jsonObject.get("text") + "</option>");

        }
        formHtml.append("</select>");
        formHtml.append("</div> ");
        formHtml.append("</div> ");
        /**
          "{value:\"link\",text:\"链接\"}","{value:\"text\",text:\"文字\"}"



         */
    }



    private void printItemHeader(Naming fieldNaming) {
        formHtml.append("<div class='text-left label label-info col-xs-12 large-150 fa fa-edit m-t-md m-b-md'>编辑 ");
        formHtml.append(fieldNaming.value());
        formHtml.append(" </div>");
    }
    private void printItemListHeader(Naming fieldNaming,String fieldName,String context,int level) {
        String fontSizeCss="";
        if(level==1) fontSizeCss="large-150";
        else fontSizeCss="large-120";
        String addItemFunctionName="add"+StringUtils.firstUpperCase(context)+"Item";
//        System.out.println(addItemFunctionName);
        formHtml.append("<div class='text-left label label-info col-xs-12 " + fontSizeCss + " fa fa-list m-t-md m-b-md'> ");
        formHtml.append(fieldNaming.value());
        formHtml.append("<span ng-if='!" + context + "." + fieldName + "' class='label label-info label-pill'>暂无数据</span>");
        formHtml.append("<button class='btn btn-info btn-sm  pull-right' ng-click='" + addItemFunctionName + "(" + context + ")'>增加一项 <i class='fa fa-plus-circle'></i></button>");
        formHtml.append("</div>");



    }



    private String  printItemOperationButtons(String context,String fieldName,int level,String absoluteContext) {
        String deleteText="";
        String forwardText="";
        String backwardText="";
        String paddingRightCss="";
        String btnCss="";
        String btnGroupCss="";
        String deleteFunctionName="";
        String forwardFunctionName="";
        String backwardFunctionName="";
        String toggleVarName="show"+StringUtils.firstUpperCase(context)+"Items";
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

        }
        formHtml.append("<div class='btn-group " + btnGroupCss + " pull-right p-b-xs p-t-xs " + paddingRightCss + "' ng-init=\"" + toggleVarName + "=true\">");
        formHtml.append("<button class='fa fa-trash btn " + btnCss + "' ng-click=\"" + deleteFunctionName + "\" >" + deleteText + "</button>");
        formHtml.append("<button class='fa fa-caret-up btn " + btnCss + "' ng-click='" + forwardFunctionName + "' ng-if='$index!==0'>" + forwardText + "</button>");
        formHtml.append("<button class='fa fa-caret-down btn " + btnCss + "' ng-click='" + backwardFunctionName + "' ng-if='$index!==" + context + "." + fieldName + ".length-1'>" + backwardText + "</button>");
        formHtml.append("<button class='fa btn " + btnCss + "' ng-class=\"{'fa-folder-o':!" + toggleVarName + ",'fa-folder-open-o':" + toggleVarName + "}\" ng-click='" + toggleVarName + "=!" + toggleVarName + "' ></button>");
//        formHtml.append("<button ng-init=\"showItems=true\" class='fa btn " + btnCss + "'")
//                .append(" ng-click=\"showItems=!showItems\" >{{showItems?'收起':'展开'}}<i ng-class=\"{'fa-plus-square-o':!showItems,'fa-minus-square-o':showItems}\" ></i></button>");
        formHtml.append("   </div>");
        return toggleVarName;
    }



    private void printTextInputGroup(String context,boolean inScope,Field field, Naming fieldNaming) {

        formHtml.append("\n<div class='input-group input-group-sm p-a-xs'>");
        String cssClass=fieldNaming.cssClass();
        if (StringUtils.isBlank(cssClass)) cssClass="fa fa-edit";
        formHtml.append("\n    <label class=\"input-group-addon "+cssClass+"\">" + fieldNaming.value() + "</label>");
        formHtml.append("\n<input class='form-control' type='text' ng-model='" + context + "." + field.getName() + "' name='"+field.getName()+"'/>");
        formHtml.append("\n</div>");

    }
    private void printUrlInputGroup(String context,boolean inScope,Field field, Naming fieldNaming) {
        String ng_if=getNgIfExpression(context,inScope,fieldNaming);
        if(ng_if==null){
            formHtml.append("\n<div class='col-xs-12 p-b-xs'>");
        }else {
            formHtml.append("\n<div class='col-xs-12 p-b-xs' ng-if=\"" + ng_if + "\">");
        }

        formHtml.append("\n<div class='input-group input-group-sm'>");
        formHtml.append("\n    <label class=\"input-group-addon fa fa-anchor\">" + fieldNaming.value() + "</label>");
        formHtml.append("\n<input class='form-control' type='text' ng-model='" + context + "." + field.getName() + "' name='"+field.getName()+"'/>");
        formHtml.append("\n    <a class=\"input-group-addon fa fa-question\" ng-href=\"/admin/article/list\" target=\"_blank\"></a>");
        formHtml.append("\n</div>");
        formHtml.append("\n</div>");
    }
    private void printImageChooserDiv(String context, String absoluteContext, Field field, boolean fieldInScope,Naming fieldNaming) {
        String ng_condition = getNgIfExpression(context,fieldInScope,fieldNaming);

        String clearImageFunction="clear"+StringUtils.firstUpperCase(context)+StringUtils.firstUpperCase(field.getName());
        if (ng_condition==null){
            formHtml.append("\n<div class='col-xs-12 p-b-xs'>");
        }else {
            formHtml.append("\n<div class='col-xs-12 p-b-xs' ng-if=\"" + ng_condition + "\">");
        }

        formHtml.append("\n <div class=\"btn-group col-xs-12  p-l-0 m-l-0\" >");
//        formHtml.append("\n     <button type=\"button\" class=\"btn btn-secondary btn-sm m-l-0 fa fa-times\" ng-click=\""+clearImageFunction+"("+context+")\">清除图片</button>");
        formHtml.append("\n     <button type=\"button\" class=\"btn btn-secondary btn-sm m-l-0 fa fa-image\">更换图片</button>");
        formHtml.append("\n     <button type=\"button\" class=\"btn btn-secondary dropdown-toggle btn-sm\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">");
        formHtml.append("\n         <span class=\"sr-only\">Toggle Dropdown</span>");
        formHtml.append("\n     </button>");

        if(fieldInScope){
            formHtml.append("\n     <img ng-if='" + absoluteContext + "." + field.getName() + "' ng-src='{{" + absoluteContext + "." + field.getName() + "}}' class='img-ico-md'/>");
        }else {
            formHtml.append("\n     <img ng-if='" + context + "." + field.getName() + "' ng-src='{{" + context + "." + field.getName() + "}}' class='img-ico-md'/>");
        }


        formHtml.append("\n <div class=\"dropdown-menu bg-light-grey\">");
        String imageUploadDir=null;
        if (field.isAnnotationPresent(FormAttributes.class)){
            imageUploadDir=field.getDeclaredAnnotation(FormAttributes.class).imageUploadDir();
        }

        if(fieldInScope){
            formHtml.append("\n     <span ng-if=\"" + this.itemVar + "Images&&" + this.itemVar + "Images.length" + "\" ng-repeat=\"icon in " + this.itemVar + "Images\" class=\"dropdown-item-inline\" ng-click=\"" + absoluteContext + "." + field.getName() + "=icon\">");
            formHtml.append("\n         <img type=\"text\" ng-src=\"{{icon}}\" class=\"img-ico-larger img-rounded\"/>");
            formHtml.append("\n     </span>");
            formHtml.append("\n     <span ng-if=\"!" + this.itemVar + "Images||!" + this.itemVar + "Images.length" + "\" class=\"dropdown-item-inline\">")
                    .append("还没有图片素材");
            if (StringUtils.isNotBlank(imageUploadDir)){
                formHtml.append("\n       <a class=\"btn btn-primary btn-sm fa fa-camera white-link\" ng-href=\"uploadImage?dir=" +imageUploadDir + "\"> 上传素材</a>");
            }else {
//                formHtml.append("\n       <label class=\"label label-danger\"> 系统没有指定上传文件路径，无法上传图片</label>");
            }

            formHtml.append("\n     </span>");
        }else {
            formHtml.append("\n     <span ng-if=\"" + this.itemVar + "Images&&" + this.itemVar + "Images.length" + "\" ng-repeat=\"icon in " + this.itemVar + "Images\" class=\"dropdown-item-inline\" ng-click=\"" + context + "." + field.getName() + "=icon\">");
            formHtml.append("\n         <img type=\"text\" ng-src=\"{{icon}}\" class=\"img-ico-larger img-rounded\"/>");
            formHtml.append("\n     </span>");
            formHtml.append("\n     <span ng-if=\"!" + this.itemVar + "Images||!" + this.itemVar + "Images.length" + "\" class=\"dropdown-item-inline\">")
                    .append("还没有图片素材");
            if (StringUtils.isNotBlank(imageUploadDir)){
                formHtml.append("\n       <a class=\"btn btn-primary btn-sm fa fa-camera white-link\" ng-href=\"uploadImage?dir=" +imageUploadDir + "\"> 上传素材</a>");
            }else {
//                formHtml.append("\n       <label class=\"label label-danger\"> 系统没有指定上传文件路径，无法上传图片</label>");
            }
            formHtml.append("\n     </span>");
        }


        formHtml.append("\n </div>");
        formHtml.append("\n </div>");
        formHtml.append("\n</div>");

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

    public String getFormHtml(){
        return formHtml.toString();
    }


}
