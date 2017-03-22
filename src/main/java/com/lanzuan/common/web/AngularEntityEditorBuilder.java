package com.lanzuan.common.web;

import com.lanzuan.common.code.InputType;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.entity.support.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
public class AngularEntityEditorBuilder {
    private StringBuffer stringBuffer;
    private Class<? extends Item> itemClass;
    private RootItem rootItem;
    private  Naming itemNaming;
    private PageComponent<RootItem> pageComponent;
    private AngularEntityEditorBuilder(){

    }
    public AngularEntityEditorBuilder(PageComponent pageComponent){
        this.stringBuffer=new StringBuffer();
        this.itemClass=pageComponent.getData().getClass();
        this.itemNaming= itemClass.getAnnotation(Naming.class);
        this.pageComponent=pageComponent;
        this.rootItem =pageComponent.getData();

    }
    public void build() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        commonOperationsHtml();
        printItem(1);
    }
    private void printItem(int level) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        String context=pageComponent.getVar();
        stringBuffer.append("<div class='list-group'>");//for 1
        for(Field field:itemClass.getDeclaredFields()){
            printField(context, field,level);
        }
        stringBuffer.append("</div>");//for 1 end
    }

    private void printField(String context,Field field,int level) throws ClassNotFoundException {
        if (field.isAnnotationPresent(Id.class)) return;
        if (field.isAnnotationPresent(Transient.class)) return;
        if (!field.isAnnotationPresent(Naming.class)) return;
        Naming fieldNaming=field.getAnnotation(Naming.class);
        if (fieldNaming==null) return;
        Class fieldClass=field.getClass();
        Editable editable=field.getAnnotation(Editable.class);
        String fieldName=field.getName();
        field.setAccessible(true);
        stringBuffer.append("<div class='list-group-item'>");
        if(editable!=null){
            InputType inputType=editable.inputType();

            if(InputType.IMAGE==inputType){
                printImageChooserDiv(context, field);

            }else{
                printTextInputGroup(context, field, fieldNaming);
            }

        }else {//不可编辑的naming field,可能是Item或List,或者是只读文本

            if (Item.class.isAssignableFrom(field.getType())) {//field is an Item
                printItemHeader(fieldNaming);
                Class childItemClass=field.getType();
                for (Field childItemField : childItemClass.getDeclaredFields()) {
                    printField(context+"."+fieldName, childItemField,level+1);
                }
            }else if (List.class.isAssignableFrom(field.getType())) {
                printItemListHeader(fieldNaming,level);

                String ngRepeatVar = fieldNaming.ngRepeatVar();
                Type typeCls = field.getGenericType();
                ParameterizedType parameterizedType = (ParameterizedType) typeCls;
                Type actualType = parameterizedType.getActualTypeArguments()[0];
//                System.out.println("actual type :" + actualType.getTypeName());
                Class clazz = Class.forName(actualType.getTypeName());

                stringBuffer.append("<div class='row p-t-xs p-b-md' ng-repeat='" + ngRepeatVar + " in " + context + "." + field.getName() + "'>");
//                stringBuffer.append("<hr/>");
                stringBuffer.append("<div class='col-xs-12'>");
                if(level==1) {
                    printRootItemOperationButtons(context);
                }else {
                    printNonRootItemOperationButtons(context);
                }
                for (Field childField : clazz.getDeclaredFields()) {
                    printField(ngRepeatVar, childField,level+1);
                }
                stringBuffer.append("</div>");
                stringBuffer.append("</div>");
            }else {//

                stringBuffer.append("<div class='text-left label label-info large-110 fa fa-info-circle m-t-md m-b-md'> ");
                stringBuffer.append(fieldNaming.value()).append(":{{"+context+"."+fieldName+"}}");
                stringBuffer.append("</div>");
                stringBuffer.append("<label></label>");
            }

        }
        if (Item.class.isAssignableFrom(field.getType())) {
//            stringBuffer.append("\n</div>");//for 3
        }


        stringBuffer.append("</div>");
    }

    private void printItemHeader(Naming fieldNaming) {
        stringBuffer.append("<div class='text-left label label-info col-xs-12 large-150 fa fa-edit m-t-md m-b-md'>编辑 ");
        stringBuffer.append(fieldNaming.value());
        stringBuffer.append(" </div>");
    }
    private void printItemListHeader(Naming fieldNaming,int level) {
        String fontSizeCss="";
        if(level==1) fontSizeCss="large-150";
        if(level==2) fontSizeCss="large-120";
        stringBuffer.append("<div class='text-left label label-info col-xs-12 "+fontSizeCss+" fa fa-list m-t-md m-b-md'> ");
        stringBuffer.append(fieldNaming.value());
        stringBuffer.append("列表</div>");
    }


    private void printNonRootItemOperationButtons(String context) {
        stringBuffer.append("<div class='btn-group btn-group-sm pull-right'>");
        stringBuffer.append("<button class='fa fa-trash btn  btn-primary' ng-click='remove")
                .append(StringUtils.firstUpperCase(context))
                .append("Item(" + pageComponent.getVar() + "," + context + ",$index)' >删除</button>");
        stringBuffer.append("<button class='fa fa-caret-up btn btn-primary' ng-click='forward")
                .append(StringUtils.firstUpperCase(context))
                .append("Item(" + pageComponent.getVar() + "," + context + ",$index)'ng-if='$index!==0'>前移</button>");
        stringBuffer.append("<button class='fa fa-caret-down btn btn-primary' ng-click='backward")
                .append(StringUtils.firstUpperCase(context))
                .append("Item(" + pageComponent.getVar() + "," + context + ",$index)' ng-if='$index!==" + context + ".items.length-1'>后移</button>");
        stringBuffer.append("   </div>");
    }

    private void printRootItemOperationButtons(String context) {
        stringBuffer.append("<div class='btn-group btn-group-sm pull-right'>");
        stringBuffer.append("<button class='fa fa-trash btn  btn-primary' ng-click='remove" + StringUtils.firstUpperCase(context) + "Item($index)' >删除整块</button>");
        stringBuffer.append("<button class='fa fa-caret-up btn btn-primary' ng-click='forward" + StringUtils.firstUpperCase(context) + "Item($index)'ng-if='$index!==0'>整块前移</button>");
        stringBuffer.append("<button class='fa fa-caret-down btn btn-primary' ng-click='backward" + StringUtils.firstUpperCase(context) + "Item($index)' ng-if='$index!==" + context + ".items.length-1'>整块后移</button>");
        stringBuffer.append("   </div>");
    }

    private void printTextInputGroup(String context, Field field, Naming fieldNaming) {

        stringBuffer.append("\n<div class='col-xs-12 p-b-xs'>");
        stringBuffer.append("\n<div class='input-group input-group-sm'>");
        stringBuffer.append("\n    <label class=\"input-group-addon fa fa-edit\">"+fieldNaming.value()+"</label>");
        stringBuffer.append("\n<input class='form-control' type='text' ng-model='"+context+"."+field.getName()+"'/>");
        stringBuffer.append("\n</div>");
        stringBuffer.append("\n</div>");
    }

    private void printImageChooserDiv(String context, Field field) {
        stringBuffer.append("\n<div class='col-xs-12 p-b-xs'>");
        stringBuffer.append("\n<div class=\"btn-group col-xs-12  p-l-0 m-l-0\" >");
        stringBuffer.append("\n <button type=\"button\" class=\"btn btn-secondary btn-sm p-l-0 m-l-0\">更换图片</button>");
        stringBuffer.append("\n <button type=\"button\" class=\"btn btn-secondary dropdown-toggle btn-sm\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">");
        stringBuffer.append("\n     <span class=\"sr-only\">Toggle Dropdown</span>");
        stringBuffer.append("\n </button>");
        stringBuffer.append("\n <div class=\"dropdown-menu bg-light-grey\">");
        stringBuffer.append("\n     <span ng-repeat=\"icon in "+pageComponent.getVar()+"Images\" class=\"dropdown-item-inline\" ng-click=\""+context+"."+field.getName()+"=icon\">");
        stringBuffer.append("\n         <img type=\"text\" ng-src=\"{{icon}}\" class=\"img-ico-larger img-rounded\"/>");
        stringBuffer.append("\n     </span>");
        stringBuffer.append("\n </div>");
        stringBuffer.append("\n</div>");
        stringBuffer.append("\n</div>");
    }

    private void commonOperationsHtml() throws NoSuchFieldException, IllegalAccessException {
            Field fangAnField=itemClass.getDeclaredField("name");
            fangAnField.setAccessible(true);

            stringBuffer.append("\n<div class=\"row m-a-0 p-a-0\" ng-init=\"get"+pageComponent.getVarU()+"Material()\">");
            stringBuffer.append("\n   <div class=\"btn-group p-b-10\">");
        stringBuffer.append("\n<label class=\"btn btn-info cursor-auto\">编辑"+itemNaming.value()+"</label>");
//            stringBuffer.append("\n       <label class=\"btn btn-info cursor-auto\">当前方案：" + fangAnField.get(rootItem)+"</label>");
            stringBuffer.append("\n       <button class=\"btn btn-danger fa fa-save \" type=\"button\" ng-click=\"save"+pageComponent.getVarU()+"()\" >保存</button>");
            stringBuffer.append("\n       <button class=\"btn btn-primary fa fa-copy\" type=\"button\" ng-click=\"new"+pageComponent.getVarU()+"()\" >方案另存为</button>");
            stringBuffer.append("\n       <a class=\"btn btn-primary fa fa-download white-link\" ng-href=\""+pageComponent.getListOperationUri()+pageComponent.getId()+"\">应用方案</a>");
            stringBuffer.append("\n       <a class=\"btn btn-primary fa fa-camera white-link\" ng-href=\""+pageComponent.getMaterialUploadUri()+"/"+pageComponent.getId()+"\"> 上传素材</a>");
            stringBuffer.append("\n       <button class=\"btn btn-primary fa fa-refresh\" type=\"button\" ng-click=\"reset"+pageComponent.getVarU()+"()\">重置</button>");
            stringBuffer.append("\n   </div>");
            stringBuffer.append("\n</div>");


            stringBuffer.append("\n<div class=\"row\">");
            stringBuffer.append("\n   <div class=\"alert alert-warning\">");
            stringBuffer.append("\n       <ul class=\"list-unstyled\">");
            stringBuffer.append("\n           <li><i class=\"fa fa-warning\"></i> 如果没有合适的图标，您可以先<a href='"+pageComponent.getMaterialUploadUri()+"/"+pageComponent.getId()+"' style=\"text-decoration: underline;\"><i>上传素材</i></a>；</li>");
            stringBuffer.append("\n           <li><i class=\"fa fa-warning\"></i> “另存方案”后，如果想应用该方案，可点击“应用其它方案”；</li>");
            stringBuffer.append("\n           <li><i class=\"fa fa-warning\"></i> 修改导航项名称，链接，更换图标以及“前面插入一条”、“删除词条”仅在客户端修改，点击上方的“保存”按钮才会保存修改。</li>");
            stringBuffer.append("\n       </ul>");
            stringBuffer.append("\n   </div>");
            stringBuffer.append("\n</div>");

    }
    public String getHtml(){
        return stringBuffer.toString();
    }

    private String itemVar(Item item,PageComponent pageComponent) {
        if (item instanceof RootItem){
            return pageComponent.getVar();
        }
        return StringUtils.firstLowerCase(item.getClass().getSimpleName());

    }

}
