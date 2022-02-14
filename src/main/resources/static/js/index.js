$(function () {
    //获取城市地铁数量top10
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

    //中国地铁在各个省份的数量
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

    //echart_2 中国城市轨道占比
    function echart_2() {
           // 基于准备好的dom，初始化echarts实例

        var myChart = echarts.init(document.getElementById('chart_2'));
        option = {
            tooltip: {
                trigger: 'item'
            },
            series: [
                {
                    type: 'pie',
                    radius: '80%',
                    fontSize:10,
                    center: ["50%", "56.2%"],
                    itemStyle: {
                        normal: {
                            color: function(params) {
                                //自定义颜色
                                var colorList = [
                                    '#FF4949','#FFA74D','#FFEA51','#4BF0FF', '#44AFF0','#288588', '#4E82FF','#584BFF','#BE4DFF','#F845F1'
                                ];
                                return colorList[params.dataIndex]
                            }
                        }
                    },
                    label:{            //饼图图形上的文本颜色设置
                        normal:{
                            show:true,
                            //position:'inner', //标签的位置
                            textStyle : {
                                fontWeight : 300 ,
                                fontSize : 8    //文字的字体大小
                            },
                            formatter:'{d}%'  //设置百分比


                        }
                    },
                    data: [
                        { value: 6302.79, name: '地铁' },
                        { value: 217.6, name: '轻轨' },
                        { value: 805.7, name: '市城快轨' },
                        { value: 485.7, name: '现代有轨电车' },
                        { value: 57.7, name: '磁悬浮交通' },
                        { value: 108.7, name: '其他' },
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
                        areaColor: '#BCE6FF',
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



    //echart_3
    function echart_3() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_3'));
        myChart.clear();
        var color = ['#FF4949','#FFA74D','#FFEA51','#4BF0FF', '#44AFF0','#288588', '#4E82FF','#584BFF','#BE4DFF','#F845F1'];
        /*option = {
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
        };*/
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
                        fontSize:8,
                        color:'#fff'
                    },
                    data: ['2012','2013', '2014', '2015', '2016','2017','2018','2019','2020','2021'],
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    min: 0,
                    max: 7500,
                    interval: 1500,
                    axisLabel: {
                        formatter: '{value}',
                        fontSize: 8,
                        color:'#fff'
                    }
                },
                {
                    type: 'value',
                    min: 0,
                    max: 25,
                    interval: 5,
                    axisLabel: {
                        formatter: '{value}',
                        fontSize:9,
                        color:'#fff'
                    }
                }
            ],
            series: [
                {
                    name: '里程',
                    type: 'bar',
                    barWidth:20,
                    itemStyle:{
                        color:function(p){
                            return color[p.dataIndex]
                        }

                    },
                    tooltip: {
                        valueFormatter: function (value) {
                            return value + '公里';
                        }
                    },
                    data: [
                        1726, 2073, 2361, 2658, 3168.7, 3883.6, 4354.3, 5187, 6302.79, 7253.73
                    ]
                },
                {
                    name: '增长比例',
                    type: 'line',
                    yAxisIndex: 1,
                    itemStyle:{
                        normal : {
                            lineStyle:{
                                color:'#FF4949'
                            },
                            color:function(p){
                                return color[p.dataIndex]
                            }
                        }

                    },
                    tooltip: {
                        valueFormatter: function (value) {
                            return value + ' %';
                        }
                    },
                    symbolSize: 7,   //设定中心点的大小
                    data: [
                        18.83, 16.74, 13.97, 11.17, 16.12, 18.41, 10.81, 16.05, 17.70, 13.11
                    ]
                }
            ]
        };
        myChart.setOption(option);
    }
    //
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
        window.addEventListener("resize", function () {
            myChart.resize();
        });

    }
    /*//点击跳转
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
    });*/
});
