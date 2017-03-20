package com.lanzuan.common.web;

import com.lanzuan.common.util.ReflectUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.entity.support.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
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
        printItem();
    }
    private void printItem() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {

        for(Field field:itemClass.getDeclaredFields()){
            printField( field);
        }
    }

    private void printField(Field field) throws ClassNotFoundException {

        if (field.isAnnotationPresent(Id.class)) return;
        if (field.isAnnotationPresent(Transient.class)) return;
        if(Item.class.isAssignableFrom(field.getType())){
            System.out.println("find an item "+field.getName());
        }
        if(List.class.isAssignableFrom(field.getType())){
            System.out.println("find a list "+field.getName());
            Type typeCls=field.getGenericType();
            ParameterizedType parameterizedType=(ParameterizedType)typeCls;
            Type actualType=parameterizedType.getActualTypeArguments()[0];
            System.out.println("actual type :"+actualType.getTypeName());
            Class clazz=Class.forName(actualType.getTypeName());
            if(field.isAnnotationPresent(Naming.class)){
                Naming naming=field.getAnnotation(Naming.class);
                System.out.println("has naming:"+naming.value());
            }
            for (Field field1:clazz.getDeclaredFields()){
                printField(field1);
            }

        }
        Naming fieldNaming=field.getAnnotation(Naming.class);
        if(field.isAnnotationPresent(Transient.class) ) return;
        if (fieldNaming==null) return;
        field.setAccessible(true);
        String ngRepeatVar=fieldNaming.ngRepeatVar();
        Editable editable=field.getAnnotation(Editable.class);
        String fieldName=field.getName();
//        String ngModelInContext=context+"."+fieldName;
    }

    private void commonOperationsHtml() throws NoSuchFieldException, IllegalAccessException {
            Field fangAnField=itemClass.getDeclaredField("name");
            fangAnField.setAccessible(true);
            stringBuffer.append("\n<label class=\"label label-default large-180 m-t-md\">编辑"+itemNaming.value()+"</label>");
            stringBuffer.append("\n<div class=\"col-xs-12 m-a-0 p-a-0\">");
            stringBuffer.append("\n   <div class=\"btn-group p-b-10\">");
            stringBuffer.append("\n       <label class=\"btn btn-info cursor-auto\">当前方案：" + fangAnField.get(rootItem)+"</label>");
            stringBuffer.append("\n       <button class=\"btn btn-danger fa fa-save \" type=\"button\" ng-click=\"save"+pageComponent.getVarU()+"()\" >保存</button>");
            stringBuffer.append("\n       <button class=\"btn btn-primary fa fa-copy\" type=\"button\" ng-click=\"new"+pageComponent.getVarU()+"()\" >方案另存为</button>");
            stringBuffer.append("\n       <a class=\"btn btn-primary fa fa-download white-link\" ng-href=\""+pageComponent.getListOperationUri()+pageComponent.getId()+"\">应用方案</a>");
            stringBuffer.append("\n       <a class=\"btn btn-primary fa fa-camera white-link\" ng-href=\""+pageComponent.getMaterialUploadUri()+"/"+pageComponent.getId()+"\"> 上传素材</a>");
            stringBuffer.append("\n       <button class=\"btn btn-primary fa fa-refresh\" type=\"button\" ng-click=\"reset"+pageComponent.getVarU()+"()\">重置</button>");
            stringBuffer.append("\n   </div>");
            stringBuffer.append("\n</div>");


            stringBuffer.append("\n<div class=\"col-xs-12\">");
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
