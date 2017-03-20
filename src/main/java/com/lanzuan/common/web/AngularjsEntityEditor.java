package com.lanzuan.common.web;

import com.lanzuan.common.util.ReflectUtil;
import com.lanzuan.common.util.StringUtils;
import com.lanzuan.entity.PageComponent;
import com.lanzuan.entity.support.Editable;
import com.lanzuan.entity.support.Item;
import com.lanzuan.entity.support.Naming;
import com.lanzuan.entity.support.RootItem;
import org.springframework.data.annotation.Transient;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
public class AngularjsEntityEditor {
    public static StringBuffer printItem(StringBuffer stringBuffer,Item item,PageComponent pageComponent,String context) throws IllegalAccessException, NoSuchFieldException {
        Class<? extends Item> itemClass=item.getClass();
        Naming itemNaming= itemClass.getAnnotation(Naming.class);




            //包住整个编辑区域 的外围div

        //for 3:item级别的顶部信息
        stringBuffer.append("\n <div class=\"col-xs-12 alert alert-info\">");//for 3 begin
        stringBuffer.append("\n       <label class='btn btn-primary btn-sm'>编辑"+itemNaming.value()+"</label>");
        stringBuffer.append("\n </div>");//for 3 end
        for(Field field:itemClass.getDeclaredFields()){

            Naming fieldNaming=field.getAnnotation(Naming.class);
            if(field.isAnnotationPresent(Transient.class) )continue;
            if (fieldNaming==null) continue;
            field.setAccessible(true);

            String fieldName=field.getName();
            Object fieldValue=field.get(item);


//            System.out.println("-------------------------------" + field.getName() + "--------------------------------------");

            stringBuffer.append("\n <div class=\"col-xs-12\">");//for 2 begin
            if(!(item instanceof RootItem))
                stringBuffer.append("<label class='label label-primary'>编辑"+fieldNaming.value()+"</label>");
            stringBuffer.append("\n </div>");//for 2 end

            Editable editable=field.getAnnotation(Editable.class);
            String ngModelInContext=context+"."+fieldName;
            System.out.println(itemClass.getSimpleName()+"."+fieldName+ " ng-model="+ngModelInContext);
            if(editable!=null){
                stringBuffer.append("\n       <div class='input-group input-group-sm'>");
                stringBuffer.append("\n         <span class=\"input-group-addon bg-info\">"+fieldNaming.value()+"</span>");
                stringBuffer.append("\n         <input type=\"text\" class=\"form-control\" ng-model=\""+ngModelInContext+"\"/>");
                stringBuffer.append("\n       </div>");
            }

            //////////////////////////////////////////////////////////////////////////////////////

//            System.out.println("==list?" +(fieldType== ArrayList.class));


            if (item.children()!=null){
//                Class parameterType= ReflectUtil.getParameterizedType(itemClass);

                String ngRepeatVar=fieldNaming.ngRepeatVar();
                List<? extends Item> items=item.children();
                stringBuffer.append("\n     <div class='col-xs-12' ng-repeat='"+ngRepeatVar+" in "+ngModelInContext+"'>");
                if(item instanceof RootItem){
                    stringBuffer.append("\n     <div class='col-xs-12 solid-silver-border'>");//for 4

                }
                for(Item childItem:items){
//                    if(true)break;
                    if(StringUtils.isNotBlank(ngRepeatVar)){
                        printItem(stringBuffer, childItem, pageComponent, ngRepeatVar);
                    }else {
                        printItem(stringBuffer, childItem, pageComponent, StringUtils.firstLowerCase(field.getType().getSimpleName()));
                    }
                }
                if(item instanceof RootItem){
                    stringBuffer.append("\n    </div>");//for 4 end

                }
                stringBuffer.append("\n     </div>");

            }
            //////////////////////////////////////////////////////////////////////////////////////

            stringBuffer.append("\n     <div class='col-xs-6'></div>");

        }//for end


        return stringBuffer;
    }

    public static StringBuffer commonOperationsHtml(StringBuffer stringBuffer, RootItem item, PageComponent pageComponent) throws NoSuchFieldException, IllegalAccessException {

            Class<? extends Item> itemClass=item.getClass();
            Naming itemNaming= itemClass.getAnnotation(Naming.class);
            Field fangAnField=itemClass.getDeclaredField("name");
            fangAnField.setAccessible(true);
            stringBuffer.append("\n<label class=\"label label-default large-180 m-t-md\">编辑"+itemNaming.value()+"</label>");
            stringBuffer.append("\n<div class=\"col-xs-12 m-a-0 p-a-0\">");
            stringBuffer.append("\n   <div class=\"btn-group p-b-10\">");
            stringBuffer.append("\n       <label class=\"btn btn-info cursor-auto\">当前方案："+fangAnField.get(item)+"</label>");
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
      return stringBuffer;
    }


    private static String itemVar(Item item,PageComponent pageComponent) {
        if (item instanceof RootItem){
            return pageComponent.getVar();
        }
        return StringUtils.firstLowerCase(item.getClass().getSimpleName());

    }

}
