(function(){
    var drawCanvas=function(id,fn){
        fn(id);
    }
    function draw(id){
        var drawing=document.getElementById(id);
        //确定浏览器支持Canvas元素
        if(drawing.getContext){
            var context=drawing.getContext("2d");
            //开始路径
            context.beginPath();
            //绘制外圈
            context.arc(100,100,99,0,2*Math.PI,false);
            //绘制内圈
            context.moveTo(194,100);
            context.arc(100,100,94,0,2*Math.PI,false);
            //绘制分钟
            context.moveTo(100,100);
            context.lineTo(100,15);
            //绘制始终
            context.moveTo(100,100);
            context.lineTo(55,100);
            //描边路径
            context.stroke();

        }

    }
    function draw(id){
        var drawing=document.getElementById(id);
        //确定浏览器支持Canvas元素
        if(drawing.getContext){
            var context=drawing.getContext("2d");
            //开始路径
            context.beginPath();
            //绘制外圈
            context.arc(100,100,99,0,2*Math.PI,false);
            //绘制内圈
            //context.moveTo(194,100);//
            context.arc(100,100,94,0,2*Math.PI,false);
            //绘制分钟
            context.moveTo(100,100);
            context.lineTo(100,15);
            //绘制始终
            context.moveTo(100,100);
            context.lineTo(55,100);
            //描边路径
            context.stroke();

        }

    }
})();