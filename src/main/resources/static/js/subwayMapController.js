
var cityId ="";
var lastLineId="";
$("#city").change(function (){
    cityId = $("#city option:selected").val()
    var lineId="";
    $.ajax({
        url:"/getCitySubwayData",
        method:"post",
        data:{"cityId":cityId,"lineId":lineId},
        success:function (result){
            var list = result.list;
            var clickLineId = result.lineId;
            lastLineId = clickLineId;
            var html = loaddingMap(list,clickLineId);
            $(".mapView").html(html);
            $(".mapView").subwayMap({debug:false});
        }
    })
})

$(function (){

    if(cityId===""){
        cityId="1100";
    }
    $.ajax({
        url:"/getCitySubwayData",
        method:"post",
        data:{"cityId":cityId,"lineId":""},
        success:function (result){
            var list = result.list;
            var clickLineId = result.lineId;
            lastLineId=clickLineId;
            var html = loaddingMap(list,clickLineId);
            $(".mapView").html(html);
            $(".mapView").subwayMap({debug:false});
        }
    })

})
//加载路线图
function loaddingMap(result,lineId){
    var html ="";
    var linesName ="";
    $("#legend").html("");
    for(var i=0;i<result.length;i++){
        var line = result[i].line;
        //将没在运营的站点排出
        if(line.lineStatus!=1)continue;
        linesName+="<span lineId='"+line.id+"'>"+line.metroName+"</span>";
        var stations = result[i].stations;
        var subwayMap
        if(lineId!=""){
            if(lineId===line.id){
                subwayMap = "<ul data-color=\"#"+line.lineColor+"\" data-label=\""+line.metroName+"\">";
            }else{
                subwayMap = "<ul data-color='#5a6268' data-label=\""+line.metroName+"\">";
            }
        }else{
            subwayMap = "<ul data-color=\"#"+line.lineColor+"\" data-label=\""+line.metroName+"\">";
        }

        for(var j=0;j<stations.length;j++){
            //var xy = stations[j].coordinateX+","+stations[j].coordinateY;
            var x = stations[j].coordinateX<=0.5?0.5:(stations[j].coordinateX>=199.5?199.5:stations[j].coordinateX);
            var y = stations[j].coordinateY<=0.5?0.5:(stations[j].coordinateY>=199.5?199.5:stations[j].coordinateY);
            var xy = x+","+y;
            //如果线路状态不为1则将图画出来
            if(stations[j].stationStatus!=1){
                //alert("站点建设中:"+stations[j].name);
                continue;
            };
            if(stations[j].ifTransfer==1){
                subwayMap +="<li data-coords=\""+xy+"\" data-marker=\"interchange\">"+stations[j].name+"</li>"
            }else{
                subwayMap +="<li data-coords=\""+xy+"\">"+stations[j].name+"</li>"
            }

        }
        if(line.ifRing=="1"){
            var xy = stations[0].coordinateX+","+stations[0].coordinateY;
            subwayMap +="<li data-coords=\""+xy+"\">"+stations[0].name+"</li>"
        }
        subwayMap +="</ul>"
        html+=subwayMap;
    }
    $(".lines").html(linesName);
    $(".lines span").click(function(){
        var cityId = $("#city option:selected").val();
        var lineId = $(this).attr("lineId");
        $.ajax({
            url:"/getCitySubwayData",
            method:"post",
            data:{"cityId":cityId,"lineId":lineId},
            success:function (result){
                var list = result.list;
                var clickLineId = result.lineId;
                lastLineId= clickLineId;
                var html = loaddingMap(list,clickLineId);
                $(".mapView").html(html);
                $(".mapView").subwayMap({debug:false});
            }
        })

    })
    return html;
}
function showLine(e){
    var id = $(e.target).val();
    alert(id)
}
//使用鼠标拖动代替滚动条
$(document).ready(function (){
    //鼠标移动操作
    var drag = function drag(){
        this.dragWrap = $(".collect_message");
        this.init.apply(this,arguments);
    };
    drag.prototype = {
        constructor:drag,
        _dom : {},
        _x : 0,
        _y : 0,
        _top :0,
        _left: 0,
        move : false,
        down : false,
        init : function () {
            this.bindEvent();
        },
        bindEvent : function () {
            var t = this;
            $('body').on('mousedown','.collect_message',function(e){
                e && e.preventDefault();
                if ( !t.move) {
                    t.mouseDown(e);
                }
            });
            $('body').on('mouseup','.collect_message',function(e){
                t.mouseUp(e);
            });

            $('body').on('mousemove','.collect_message',function(e){
                if (t.down) {
                    t.mouseMove(e);
                }
            });
        },
        mouseMove : function (e) {
            e && e.preventDefault();
            this.move = true;
            var x = this._x - e.clientX,
                y = this._y - e.clientY,
                dom = $('.collect_message');
            dom.scrollLeft(this._left + x);
            dom.scrollTop(this._top + y);
        },
        mouseUp : function (e) {
            e && e.preventDefault();
            this.move = false;
            this.down = false;
            this.dragWrap.css('cursor','');
        },
        mouseDown : function (e) {
            this.move = false;
            this.down = true;
            this._x = e.clientX;
            this._y = e.clientY;
            this._top = $('.collect_message').scrollTop();
            this._left = $('.collect_message').scrollLeft();
            this.dragWrap.css('cursor','move');
        }
    };
    var drag = new drag();

});