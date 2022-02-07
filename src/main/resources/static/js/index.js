$(function () {
    $.ajax({
        url:"/findLineCount",
        method: "post",
        success: function (result) {
            var city_name = new Array(10);
            var num = new Array(10);
            for(var i=0;i<10;i++){
                city_name[i] = result[i].city.name_cn;
                num[i] = result[i].num;

            }
            echart_1(city_name,num)
        }
    })
    $.ajax({
        url:"/findLineCountAll",
        method: "post",
        success: function (result) {
            echart_map(result)
        }
    })
    echart_2();

    echart_3();
    echart_4();

    echart_5();

    //echart_1 地铁数量top10
    function echart_1(ciytName,linenum) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_1'));
        option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'

                }
            },
            grid: {
                left: '0',
                right: '1%',
                bottom: '1%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: ciytName,
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color: '#fff',//左边线的颜色
                            width:'1'//坐标线的宽度
                        }
                    },
                    axisLabel: {
                        show: true,
                        textStyle: {
                            color: '#fff',  //更改坐标轴文字颜色
                            fontSize : 10      //更改坐标轴文字大小
                        }
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color: '#fff',//左边线的颜色
                            width:'1'//坐标线的宽度
                        }
                    },
                }
            ],
            series: [
                {
                    name: '数量',
                    type: 'bar',
                    barWidth: '60%',
                    data: linenum,
                    itemStyle: {
                        normal: {
                            //设置柱子的圆角
                            barBorderRadius:[3,3,0,0],
                            //每根柱子颜色设置
                            color: function(params) {
                                let colorList = [
                                    '#FF4949',
                                    '#FFA74D',
                                    '#FFEA51',
                                    '#4BF0FF',
                                    '#44AFF0',
                                    "#288588",
                                    '#4E82FF',
                                    '#584BFF',
                                    '#BE4DFF',
                                    '#F845F1'
                                ];
                                return colorList[params.dataIndex];
                            }
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize", function () {
            myChart.resize();
        });
    }

    //echart_2 中国地铁近十年的里程变化
    function echart_2() {
           // 基于准备好的dom，初始化echarts实例

        var myChart = echarts.init(document.getElementById('chart_2'));
        option = {
            tooltip: {
                trigger: 'item'
            },
            legend: {
                top:'6%',
                left: 'center',
                textStyle: {
                    fontSize: 8,
                    color:'white'
                }
            },
            series: [
                {
                    type: 'pie',
                    radius: '75%',
                    fontSize:10,
                    center: ["50%", "58.5%"],
                    itemStyle: {
                        normal: {
                            color: function(params) {
                                //自定义颜色
                                var colorList = [
                                    '#FF4949', '#FFA74D', '#FFEA51', '#4BF0FF', '#44AFF0'
                                ];
                                return colorList[params.dataIndex]
                            }
                        }
                    },
                    label:{            //饼图图形上的文本颜色设置
                        normal:{
                            show:true,
                            position:'inner', //标签的位置
                            textStyle : {
                                fontWeight : 300 ,
                                fontSize : 8    //文字的字体大小
                            },
                            formatter:'{d}%'  //设置百分比


                        }
                    },
                    data: [
                        { value: 1048, name: 'Search Engine' },
                        { value: 735, name: 'Direct' },
                        { value: 580, name: 'Email' },
                        { value: 484, name: 'Union Ads' },
                        { value: 300, name: 'Video Ads' }
                    ],
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
           // 使用刚指定的配置项和数据显示图表。
           window.addEventListener("resize", function () {
               myChart.resize();
           });
    }

    // echart_map中国地图
    function echart_map(data) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('map'));

// 指定图表的配置项和数据
        var name_title = ""
        var mapName = 'china'

        var option = {
            title: {
                text: name_title,
                x: 'center',
                textStyle: {
                    fontSize: 24
                },
            },
            tooltip: {
                trigger: 'item',
                formatter: function(params) {
                    var toolTiphtml = ''
                    if (isNaN(params.value)){
                        toolTiphtml = params.name + '地铁数量: 0'
                    }else{
                        toolTiphtml = params.name + '地铁数量: ' + params.value
                    }
                    return toolTiphtml;
                }
            },

            visualMap: {
                show: true,
                left: 'left',
                top: 'bottom',
                seriesIndex: [0],
                type:'piecewise',
                pieces:[
                    {min:21, color: '#FF4949'},
                    {min:15, max:20, color: '#FFA74D'},
                    {min:9, max:14, color: '#FFEA51'},
                    {min:4, max:8, color:  '#4E82FF'},
                    {min:0, max:3, color: '#44AFF0'},
                ],
                textStyle: {
                    color: 'white'
                }
            },
            geo: {
                show: true,
                map: mapName,
                label: {
                    normal: {
                        show: true,
                        fontSize:12,
                    },
                    emphasis: {
                        show: false,
                    }
                },
                roam: false,
                itemStyle: {
                    normal: {
                        areaColor: '#FFFFFF',
                        borderColor: '#666666',
                    },
                    emphasis: {
                        areaColor: '#0099CC',
                    }
                }
            },
            series: [
                {
                    type: 'map',
                    map: mapName,
                    geoIndex: 0,
                    animation: false,
                    data: data
                },
            ]
        };

// 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize", function () {
            myChart.resize();
        });

    }









    //echart_3  2020-2021年部分城市地铁数量变化
    function echart_3() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_3'));
        myChart.clear();
        var color = ['#FF4949','#FFEA51', '#44AFF0', '#4E82FF','#BE4DFF'];
        var color1 = ['#FFA74D','#4BF0FF','#288588','#584BFF','#F845F1'];
        option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            grid: {
                left: '0.5%',
                right: '0.5%',
                bottom: '0',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    axisLabel: {
                        formatter: '{value}',
                        fontSize:9,
                        color:'#fff'
                    },
                    data: ['武汉','成都', '广州', '北京', '上海'],
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: 'km',
                    min: 300,
                    max: 900,
                    interval:100,
                    axisLabel: {
                        formatter: '{value}',
                        fontSize:9,
                        color:'#fff'
                    }
                }
            ],
            series: [
                {
                    name: '2020',
                    type: 'bar',
                    itemStyle:{
                        color:function(p){
                            return color[p.dataIndex]
                        }

                    },
                    tooltip: {
                        valueFormatter: function (value) {
                            return value + ' km';
                        }
                    },
                    data: [338.94 ,518.50 ,531.10 ,704.69 ,728.00 ],
                },
                {
                    name: '2021',
                    type: 'bar',
                    itemStyle:{
                        color:function(p){
                            return color1[p.dataIndex]
                        }

                    },
                    tooltip: {
                        valueFormatter: function (value) {
                            return value + ' km';
                        },
                    },
                    data: [435.24 ,557.50 ,589.40 ,761.69 ,829.60],
                },
            ]
        };
        myChart.setOption(option);
    }
    //湖南高速公路
    function echart_4() {
          // 基于准备好的dom，初始化echarts实例
          var myChart = echarts.init(document.getElementById('chart_4'));

        option = {
            legend: {
                top:'6%',
                left: 'center',
                textStyle: {
                    fontSize: 8,
                    color:'white'
                }
            },
            toolbox: {
                show: true,
                feature: {
                    mark: { show: true },
                    dataView: { show: true, readOnly: false },
                    restore: { show: true },
                    saveAsImage: { show: true }
                }
            },
            series: [
                {
                    name: 'Nightingale Chart',
                    type: 'pie',
                    radius: [20, 88],
                    center: ['50%', '61%'],
                    roseType: 'area',
                    itemStyle: {
                        borderRadius: 8
                    },
                    label:{            //饼图图形上的文本颜色设置
                        normal:{
                            show:true,
                            position:'inner', //标签的位置
                            textStyle : {
                                fontWeight : 300 ,
                                fontSize : 8    //文字的字体大小
                            },
                            formatter:'{d}%'  //设置百分比


                        }
                    },
                    data: [
                        { value: 40, name: 'rose 1' },
                        { value: 40, name: 'rose 2' },
                        { value: 40, name: 'rose 3' },
                        { value: 40, name: 'rose 4' },
                        { value: 40, name: 'rose 5' },
                        { value: 40, name: 'rose 6' },
                        { value: 40, name: 'rose 7' },
                        { value: 40, name: 'rose 8' }
                    ]
                }
            ]
        };
          myChart.setOption(option);

    }
    //湖南省飞机场
    function echart_5() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_5'));

        function showProvince() {
                var geoCoordMap = {
                    '长沙黄花国际机场': [113.226512,28.192929],
                    '张家界荷花机场': [110.454598,29.107223],
                    '常德桃花源机场': [111.651508,28.921516],
                    '永州零陵机场': [111.622869,26.340994],
                    '怀化芷江机场': [109.714784,27.44615],
                };
                var data = [{
                        name: '长沙黄花国际机场',
                        value: 100
                    },
                    {
                        name: '张家界荷花机场',
                        value: 100
                    },
                    {
                        name: '常德桃花源机场',
                        value: 100
                    },
                    {
                        name: '永州零陵机场',
                        value: 100
                    },
                    {
                        name: '怀化芷江机场',
                        value: 100
                    }
                ];
                var max = 480,
                    min = 9; // todo 
                var maxSize4Pin = 100,
                    minSize4Pin = 20;
                var convertData = function (data) {
                    var res = [];
                    for (var i = 0; i < data.length; i++) {
                        var geoCoord = geoCoordMap[data[i].name];
                        if (geoCoord) {
                            res.push({
                                name: data[i].name,
                                value: geoCoord.concat(data[i].value)
                            });
                        }
                    }
                    return res;
                };

                myChart.setOption(option = {
                    title: {
                        top: 20,
                        text: '',
                        subtext: '',
                        x: 'center',
                        textStyle: {
                            color: '#ccc'
                        }
                    },
                    legend: {
                        orient: 'vertical',
                        y: 'bottom',
                        x: 'right',
                        data: ['pm2.5'],
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    visualMap: {
                        show: false,
                        min: 0,
                        max: 500,
                        left: 'left',
                        top: 'bottom',
                        text: ['高', '低'], // 文本，默认为数值文本
                        calculable: true,
                        seriesIndex: [1],
                        inRange: {
                        }
                    },
                    geo: {
                        show: true,
                        map:'hunan',
                        mapType: 'hunan',
                        label: {
                            normal: {
                            },
                            //鼠标移入后查看效果
                            emphasis: {
                                textStyle: {
                                    color: '#fff'
                                }
                            }
                        },
                        //鼠标缩放和平移
                        roam: true,
                        itemStyle: {
                            normal: {
                                //          	color: '#ddd',
                                borderColor: 'rgba(147, 235, 248, 1)',
                                borderWidth: 1,
                                areaColor: {
                                    type: 'radial',
                                    x: 0.5,
                                    y: 0.5,
                                    r: 0.8,
                                    colorStops: [{
                                        offset: 0,
                                        color: 'rgba(175,238,238, 0)' // 0% 处的颜色
                                    }, {
                                        offset: 1,
                                        color: 'rgba(	47,79,79, .2)' // 100% 处的颜色
                                    }],
                                    globalCoord: false // 缺省为 false
                                },
                                shadowColor: 'rgba(128, 217, 248, 1)',
                                shadowOffsetX: -2,
                                shadowOffsetY: 2,
                                shadowBlur: 10
                            },
                            emphasis: {
                                areaColor: '#389BB7',
                                borderWidth: 0
                            }
                        }
                    },
                    series: [{
                            name: 'light',
                            type: 'map',
                            coordinateSystem: 'geo',
                            data: convertData(data),
                            itemStyle: {
                                normal: {
                                    color: '#F4E925'
                                }
                            }
                        },
                        {
                            name: '点',
                            type: 'scatter',
                            coordinateSystem: 'geo',
                            symbol: 'pin',
                            symbolSize: function(val) {
                                var a = (maxSize4Pin - minSize4Pin) / (max - min);
                                var b = minSize4Pin - a * min;
                                b = maxSize4Pin - a * max;
                                return a * val[2] + b;
                            },
                            label: {
                                normal: {
                                    // show: true,
                                    // textStyle: {
                                    //     color: '#fff',
                                    //     fontSize: 9,
                                    // }
                                }
                            },
                            itemStyle: {
                                normal: {
                                    color: '#F62157', //标志颜色
                                }
                            },
                            zlevel: 6,
                            data: convertData(data),
                        },
                        {  
                            name: 'light',
                            type: 'map',
                            mapType: 'hunan',
                            geoIndex: 0,
                            aspectScale: 0.75, //长宽比
                            showLegendSymbol: false, // 存在legend时显示
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: false,
                                    textStyle: {
                                        color: '#fff'
                                    }
                                }
                            },
                            roam: true,
                            itemStyle: {
                                normal: {
                                    areaColor: '#031525',
                                    borderColor: '#FFFFFF',
                                },
                                emphasis: {
                                    areaColor: '#2B91B7'
                                }
                            },
                            animation: false,
                            data: data
                        },
                        {
                            name: ' ',
                            type: 'effectScatter',
                            coordinateSystem: 'geo',
                            data: convertData(data.sort(function (a, b) {
                                return b.value - a.value;
                            }).slice(0, 5)),
                            symbolSize: function (val) {
                                return val[2] / 10;
                            },
                            showEffectOn: 'render',
                            rippleEffect: {
                                brushType: 'stroke'
                            },
                            hoverAnimation: true,
                            label: {
                                normal: {
                                    formatter: '{b}',
                                    position: 'right',
                                    show: true
                                }
                            },
                            itemStyle: {
                                normal: {
                                    color: '#05C3F9',
                                    shadowBlur: 10,
                                    shadowColor: '#05C3F9'
                                }
                            },
                            zlevel: 1
                        },

                    ]
                });
        }
        showProvince();

        // 使用刚指定的配置项和数据显示图表。
        // myChart.setOption(option);
        window.addEventListener("resize", function () {
            myChart.resize();
        });
    }
    //点击跳转
    $('#chart_map').click(function(){
        window.location.href = './page/index.html';
    });
    $('.t_btn2').click(function(){
        window.location.href = "./page/index.html?id=2";
    });
    $('.t_btn3').click(function(){
        window.location.href = "./page/index.html?id=3";
    });
    $('.t_btn4').click(function(){
        window.location.href = "./page/index.html?id=4";
    });
    $('.t_btn5').click(function(){
        window.location.href = "./page/index.html?id=5";
    });
    $('.t_btn6').click(function(){
        window.location.href = "./page/index.html?id=6";
    });
    $('.t_btn7').click(function(){
        window.location.href = "./page/index.html?id=7";
    });
    $('.t_btn8').click(function(){
        window.location.href = "./page/index.html?id=8";
    });
    $('.t_btn9').click(function(){
        window.location.href = "./page/index.html?id=9";
    });
});
