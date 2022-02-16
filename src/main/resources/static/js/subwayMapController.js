
var cityId ="";
$(".submitCity").click(function (){
    cityId = $("#city option:selected").val()
    $("#legend").html("")
    $.ajax({
        url:"/getCitySubwayData",
        method:"post",
        data:{"cityId":cityId},
        success:function (result){
            var html ="";
            for(var i=0;i<result.length;i++){
                var line = result[i].line;
                if(line.lineStatus!=1)continue;
                var stations = result[i].stations;
                var subwayMap = "<ul data-color=\"#"+line.lineColor+"\" data-label=\""+line.metroName+"\">";
                for(var j=0;j<stations.length;j++){
                    var xy = stations[j].coordinateX+","+stations[j].coordinateY;
                    subwayMap +="<li data-coords=\""+xy+"\">"+stations[j].name+"</li>"
                }
                if(line.ifRing=="1"){
                    var xy = stations[0].coordinateX+","+stations[0].coordinateY;
                    subwayMap +="<li data-coords=\""+xy+"\">"+stations[0].name+"</li>"
                }
                subwayMap +="</ul>"
                html+=subwayMap;
            }
            $(".mapView").html(html);
            $(".mapView").subwayMap({debug:true});
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
        data:{"cityId":cityId},
        success:function (result){
            var html ="";
            for(var i=0;i<result.length;i++){
                var line = result[i].line;
                if(line.lineStatus!=1)continue;
                var stations = result[i].stations;
                var subwayMap = "<ul data-color=\"#"+line.lineColor+"\" data-label=\""+line.metroName+"\">";
                for(var j=0;j<stations.length;j++){
                    var xy = stations[j].coordinateX+","+stations[j].coordinateY;
                    subwayMap +="<li data-coords=\""+xy+"\">"+stations[j].name+"</li>"
                }
                if(line.ifRing=="1"){
                    var xy = stations[0].coordinateX+","+stations[0].coordinateY;
                    subwayMap +="<li data-coords=\""+xy+"\">"+stations[0].name+"</li>"
                }
                subwayMap +="</ul>"
                html+=subwayMap;
            }
            $(".mapView").html(html);
            $(".mapView").subwayMap({debug:true});
        }
    })

})