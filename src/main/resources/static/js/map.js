
        var chart = echarts.init(
            document.getElementById('map'), 'white', {renderer: 'canvas'});
        var option = {
    "animation": true,
    "animationThreshold": 2000,
    "animationDuration": 1000,
    "animationEasing": "cubicOut",
    "animationDelay": 0,
    "animationDurationUpdate": 300,
    "animationEasingUpdate": "cubicOut",
    "animationDelayUpdate": 0,
    "color": [
        "#c23531",
        "#2f4554",
        "#61a0a8",
        "#d48265",
        "#749f83",
        "#ca8622",
        "#bda29a",
        "#6e7074",
        "#546570",
        "#c4ccd3",
        "#f05b72",
        "#ef5b9c",
        "#f47920",
        "#905a3d",
        "#fab27b",
        "#2a5caa",
        "#444693",
        "#726930",
        "#b2d235",
        "#6d8346",
        "#ac6767",
        "#1d953f",
        "#6950a1",
        "#918597"
    ],
    "series": [
        {
            "type": "map",
            "name": "\u4e2d\u56fd",
            "label": {
                "show": true,
                "position": "top",
                "margin": 8
            },
            "mapType": "china",
            "data": [
                {
                    "name": "台湾",
                    "value": 18325
                },
                {
                    "name": "河南",
                    "value": 2640
                },
                {
                    "name": "上海",
                    "value": 3668
                },
                {
                    "name": "天津",
                    "value": 1012
                },
                {
                    "name": "陕西",
                    "value": 2826
                },
                {
                    "name": "浙江",
                    "value": 2111
                },
                {
                    "name": "香港",
                    "value": 13034
                },
                {
                    "name": "广东",
                    "value": 3724
                },
                {
                    "name": "广西",
                    "value": 695
                },
                {
                    "name": "福建",
                    "value": 1453
                },
                {
                    "name": "云南",
                    "value": 1863
                },
                {
                    "name": "北京",
                    "value": 1274
                },
                {
                    "name": "四川",
                    "value": 1372
                },
                {
                    "name": "山东",
                    "value": 1070
                },
                {
                    "name": "湖南",
                    "value": 1230
                },
                {
                    "name": "辽宁",
                    "value": 810
                },
                {
                    "name": "河北",
                    "value": 1461
                },
                {
                    "name": "湖北",
                    "value": 68320
                },
                {
                    "name": "江苏",
                    "value": 1629
                },
                {
                    "name": "山西",
                    "value": 267
                },
                {
                    "name": "吉林",
                    "value": 590
                },
                {
                    "name": "新疆",
                    "value": 981
                },
                {
                    "name": "澳门",
                    "value": 79
                },
                {
                    "name": "青海",
                    "value": 30
                },
                {
                    "name": "西藏",
                    "value": 1
                },
                {
                    "name": "内蒙古",
                    "value": 1191
                },
                {
                    "name": "安徽",
                    "value": 1009
                },
                {
                    "name": "海南",
                    "value": 190
                },
                {
                    "name": "江西",
                    "value": 959
                },
                {
                    "name": "黑龙江",
                    "value": 2035
                },
                {
                    "name": "重庆",
                    "value": 611
                },
                {
                    "name": "宁夏",
                    "value": 122
                },
                {
                    "name": "甘肃",
                    "value": 356
                },
                {
                    "name": "贵州",
                    "value": 160
                }
            ],
            "roam": true,
            "aspectScale": 0.75,
            "nameProperty": "name",
            "selectedMode": false,
            "zoom": 1,
            "mapValueCalculation": "sum",
            "showLegendSymbol": true,
            "emphasis": {}
        }
    ],
    "legend": [
        {
            "data": [
                "\u4e2d\u56fd"
            ],
            "selected": {
                "\u4e2d\u56fd": true
            },
            "show": true,
            "padding": 0,
            "itemGap": 10,
            "itemWidth": 25,
            "itemHeight": 14
        }
    ],
    "tooltip": {
        "show": true,
        "trigger": "item",
        "triggerOn": "mousemove|click",
        "axisPointer": {
            "type": "line"
        },
        "showContent": true,
        "alwaysShowContent": false,
        "showDelay": 0,
        "hideDelay": 100,
        "textStyle": {
            "fontSize": 14
        },
        "borderWidth": 0,
        "padding": 5
    },
    "title": [
        {
            "padding": 5,
            "itemGap": 10
        }
    ],
    "visualMap": {
        "show": true,
        "type": "piecewise",
        "min": 0,
        "max": 100,
        "inRange": {
            "color": [
                "#50a3ba",
                "#eac763",
                "#d94e5d"
            ]
        },
        "calculable": true,
        "inverse": false,
        "splitNumber": 6,
        "orient": "vertical",
        "top": "top",
        "showLabel": true,
        "itemWidth": 20,
        "itemHeight": 14,
        "borderWidth": 0,
        "pieces": [
            {
                "min": 10000,
                "color": "#7f1818"
            },
            {
                "min": 1000,
                "max": 10000
            },
            {
                "min": 500,
                "max": 999
            },
            {
                "min": 100,
                "max": 499
            },
            {
                "min": 10,
                "max": 99
            },
            {
                "min": 0,
                "max": 5
            }
        ]
    }
};
        chart.setOption(option);
