$(function (){
    var html="<div class='mapView' data-columns='200' data-rows='200' data-cellsize='10' data-legendId='legend'" +
        "data-textClass='text' data-gridnumbers='true' data-grid='true' data-lineWidth='8'>" +
        "<ul  data-color=\"#ff4db2\" data-label=\"jQuery Widgets\">" +
        "<li data-coords='32.7,117.6'>阎村东</li>" +
        "<li data-coords='36.7,117.6'>苏庄</li>" +
        "<li data-coords='40.2,117.6'>良乡南关</li>" +
        "<li data-coords='43.9,117.6'>良乡大学城西</li>" +
        "<li data-coords='47.3,117.5'>良乡大学城</li>" +
        "<li data-coords='51.1,114.9'>良乡大学城北</li>" +
        "<li data-coords='52.9,112.0'>广阳城</li>" +
        "<li data-coords='54.8,109.1'>篱笆房</li>" +
        "<li data-coords='56.6,106.1'>长阳</li>" +
        "<li data-coords='58.5,103.3'>稻田</li>" +
        "<li data-coords='60.2,100.5'>大葆台</li>" +
        "<li data-coords='62.0,97.7'>郭公庄</li>" +
        "</ul>" +
        "</div>"+
        "<div id='legend'></div>"
    $(".message_show").html(html);
    $(".mapView").subwayMap({debug:true});


})