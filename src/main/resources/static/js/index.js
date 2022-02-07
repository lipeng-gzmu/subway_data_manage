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

    //echart_map();
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

    //echart_2湖南省地图
    function echart_2() {
           // 基于准备好的dom，初始化echarts实例
           var myChart = echarts.init(document.getElementById('chart_2'));
           function showProvince() {
                   myChart.setOption(option = {
                       // backgroundColor: '#ffffff',
                       visualMap: {
                           show: false,
                           min: 0,
                           max: 100,
                           left: 'left',
                           top: 'bottom',
                           text: ['高', '低'], // 文本，默认为数值文本
                           calculable: true,
                           inRange: {
                               color: ['yellow', 'lightskyblue', 'orangered']
                           }
                       },
                       series: [{
                           type: 'map',
                           mapType: 'hunan',
                           roam: true,
                           label: {
                               normal: {
                                   show: true
                               },
                               emphasis: {
                                   textStyle: {
                                       color: '#fff'
                                   }
                               }
                           },
                           itemStyle: {
                               normal: {
                                   borderColor: '#389BB7',
                                   areaColor: '#fff',
                               },
                               emphasis: {
                                   areaColor: '#389BB7',
                                   borderWidth: 0
                               }
                           },
                           animation: false,
                           data: [{
                               name: '长沙市',
                               value:  100
                           }, {
                               name: '株洲市',
                               value: 96
                           }, {
                               name: '湘潭市',
                               value: 98
                           }, {
                               name: '衡阳市',
                               value: 80
                           }, {
                               name: '邵阳市',
                               value: 88
                           }, {
                               name: '岳阳市',
                               value: 79
                           }, {
                               name: '常德市',
                               value: 77,
                           }, {
                               name: '张家界市',
                               value: 33
                           }, {
                               name: '益阳市',
                               value: 69,
                           }, {
                               name: '郴州市',
                               value: 66
                           }, {
                               name: '永州市',
                               value: 22
                           },{
                                name: '娄底市',
                                value: 51
                           },{
                                name: '湘西土家族苗族自治州',
                                value: 44
                           },{
                                name: '怀化市',
                                value: 9
                           }]
                       }]
                   });
           }
   
           var currentIdx = 0;
           showProvince();
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
                    {min:16, max:20, color: '#FFA74D'},
                    {min:11, max:15, color: '#FFEA51'},
                    {min:4, max:10, color:  '#4BF0FF'},
                    {min:1, max:3, color: '#44AFF0'},
                ],
                textStyle: {
                    color: '#000000'
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









    //echart_3货物周转量
    function echart_3() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_3'));
        myChart.clear();
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
                    color:'#4BF0FF',
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
                    color:'#288588',
                    tooltip: {
                        valueFormatter: function (value) {
                            return value + ' km';
                        },
                    },
                    data: [435.24 ,518.50 ,589.40 ,761.69 ,829.60],
                },
            ]
        };
        myChart.setOption(option);
    }
    //湖南高速公路
    function echart_4() {
          // 基于准备好的dom，初始化echarts实例
          var myChart = echarts.init(document.getElementById('chart_4'));

          myChart.setOption({
              series: [{
                  type: 'map',
                  mapType: 'hunan'
              }]
          });

          var geoCoordMap = {
              '怀化': [109.999867,27.518949],
              '吉首': [109.741528,28.332629],
              '张家界': [110.491722,29.112001],
              '常德': [111.701486,29.076683],
              '益阳': [112.348741,28.544124],
              '岳阳': [113.126486,29.382401],
              '长沙': [113.019455,28.200103],
              '株洲': [113.163141,27.8418],
              '湘潭': [112.91977,27.882141],
              '邵阳': [111.467859,27.21915],
              '娄底': [112.012438,27.745506],
              '衡阳': [112.63809,26.895225],
              '永州': [111.577632,26.460144],
              '郴州': [113.039396,25.81497]
          };

          var goData = [
              [{
                  name: '张家界'

              }, {
                  id: 1,
                  name: '常德',
                  value: 86
              }],
              [{
                  name: '吉首'

              }, {
                  id: 1,
                  name: '常德',
                  value: 86
              }],
              [{
                  name: '常德'

              }, {
                  id: 1,
                  name: '益阳',
                  value: 70
              }],
              [{
                  name: '益阳'

              }, {
                  id: 1,
                  name: '长沙',
                  value: 95
              }],
              [{
                  name: '长沙'

              }, {
                  id: 1,
                  name: '岳阳',
                  value: 70
              }],
              [{
                  name: '长沙'

              }, {
                  id: 1,
                  name: '湘潭',
                  value: 80
              }],
              [{
                  name: '长沙'

              }, {
                  id: 1,
                  name: '株洲',
                  value: 80
              }],
              [{
                  name: '长沙'

              }, {
                  id: 1,
                  name: '衡阳',
                  value: 80
              }],
              [{
                  name: '衡阳'

              }, {
                  id: 1,
                  name: '郴州',
                  value: 70
              }],
              [{
                  name: '衡阳'

              }, {
                  id: 1,
                  name: '永州',
                  value: 70
              }],
              [{
                  name: '湘潭'

              }, {
                  id: 1,
                  name: '娄底',
                  value: 60
              }],
              [{
                  name: '娄底'

              }, {
                  id: 1,
                  name: '邵阳',
                  value: 75
              }],
              [{
                  name: '邵阳'

              }, {
                  id: 1,
                  name: '怀化',
                  value: 75
              }],
          ];
          //值控制圆点大小
          var backData = [
              [{
                  name: '常德'

              }, {
                  id: 1,
                  name: '张家界',
                  value: 80
              }],
              [{
                  name: '常德'

              }, {
                  id: 1,
                  name: '吉首',
                  value: 66
              }],
              [{
                  name: '益阳'

              }, {
                  id: 1,
                  name: '常德',
                  value: 86
              }],
              [{
                  name: '长沙'

              }, {
                  id: 1,
                  name: '益阳',
                  value: 70
              }],
              [{
                  name: '岳阳'

              }, {
                  id: 1,
                  name: '长沙',
                  value: 95
              }],
              [{
                  name: '湘潭'

              }, {
                  id: 1,
                  name: '长沙',
                  value: 95
              }],
              [{
                  name: '株洲'

              }, {
                  id: 1,
                  name: '长沙',
                  value: 95
              }],
              [{
                  name: '衡阳'

              }, {
                  id: 1,
                  name: '长沙',
                  value: 95
              }],
              [{
                  name: '郴州'

              }, {
                  id: 1,
                  name: '衡阳',
                  value: 80
              }],
              [{
                  name: '永州'

              }, {
                  id: 1,
                  name: '衡阳',
                  value: 80
              }],
              [{
                  name: '娄底'

              }, {
                  id: 1,
                  name: '湘潭',
                  value: 80
              }],
              [{
                  name: '邵阳'

              }, {
                  id: 1,
                  name: '娄底',
                  value: 60
              }],
              [{
                  name: '怀化'

              }, {
                  id: 1,
                  name: '邵阳',
                  value: 75
              }],
          ];

          var planePath = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';
          var arcAngle = function(data) {
              var j, k;
              for (var i = 0; i < data.length; i++) {
                  var dataItem = data[i];
                  if (dataItem[1].id == 1) {
                      j = 0.2;
                      return j;
                  } else if (dataItem[1].id == 2) {
                      k = -0.2;
                      return k;
                  }
              }
          }

          var convertData = function(data) {
              var res = [];
              for (var i = 0; i < data.length; i++) {
                  var dataItem = data[i];
                  var fromCoord = geoCoordMap[dataItem[0].name];
                  var toCoord = geoCoordMap[dataItem[1].name];
                  if (dataItem[1].id == 1) {
                      if (fromCoord && toCoord) {
                          res.push([{
                              coord: fromCoord,
                          }, {
                              coord: toCoord,
                              value: dataItem[1].value //线条颜色

                          }]);
                      }
                  } else if (dataItem[1].id == 2) {
                      if (fromCoord && toCoord) {
                          res.push([{
                              coord: fromCoord,
                          }, {
                              coord: toCoord
                          }]);
                      }
                  }
              }
              return res;
          };

          var color = ['#fff', '#FF1493', '#0000FF'];
          var series = [];
          [
              ['1', goData],
              ['2', backData]
          ].forEach(function(item, i) {
              series.push({
                  name: item[0],
                  type: 'lines',
                  zlevel: 2,
                  symbol: ['arrow', 'arrow'],
                  //线特效配置
                  effect: {
                      show: true,
                      period: 6,
                      trailLength: 0.1,
                      symbol: 'arrow', //标记类型
                      symbolSize: 5
                  },
                  lineStyle: {
                      normal: {
                          width: 1,
                          opacity: 0.4,
                          curveness: arcAngle(item[1]), //弧线角度
                          color: '#fff'
                      }
                  },
                  edgeLabel: {
                      normal: {
                          show: true,
                          textStyle: {
                              fontSize: 14
                          },
                          formatter: function(params) {
                              var txt = '';
                              if (params.data.speed !== undefined) {
                                  txt = params.data.speed;
                              }
                              return txt;
                          },
                      }
                  },
                  data: convertData(item[1])
              }, {
                  type: 'effectScatter',
                  coordinateSystem: 'geo',
                  zlevel: 2,
                  //波纹效果
                  rippleEffect: {
                      period: 2,
                      brushType: 'stroke',
                      scale: 3
                  },
                  label: {
                      normal: {
                          show: true,
                          color: '#fff',
                          position: 'right',
                          formatter: '{b}'
                      }
                  },
                  //终点形象
                  symbol: 'circle',
                  //圆点大小
                  symbolSize: function(val) {
                      return val[2] / 8;
                  },
                  itemStyle: {
                      normal: {
                          show: true
                      }
                  },
                  data: item[1].map(function(dataItem) {
                      return {
                          name: dataItem[1].name,
                          value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
                      };
                  })

              });

          });

          option = {
              title: {
                  text: '',
                  subtext: '',
                  left: 'center',
                  textStyle: {
                      color: '#fff'
                  }
              },
              tooltip: {
                  trigger: 'item',
                  formatter: '{b}'
              },
              //线颜色及飞行轨道颜色
              visualMap: {
                  show: false,
                  min: 0,
                  max: 100,
                  color: ['#31A031','#31A031']
              },
              //地图相关设置
              geo: {
                  map: 'hunan',
                  //视角缩放比例
                  zoom: 1,
                  //显示文本样式
                  label: {
                      normal: {
                          show: false,
                          textStyle: {
                              color: '#fff'
                          }
                      },
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
                          // shadowColor: 'rgba(255, 255, 255, 1)',
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
              series: series
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
