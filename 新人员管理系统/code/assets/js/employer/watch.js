(function () {
  var department_name
  var count
  $.ajax({
    methods: 'GET',
    url: 'http://119.23.241.76:8000/view/department/count?company_name=软件开发科技有限公司',
    success: function (res) {
      department_name = res.res.department_name
      count = res.res.count
      //实例化echarts
      var myChart = echarts.init(document.getElementById('main'));
      //指定配置项
      var app = {}
      var option;
      option = {
        title: [
          {
            text: '查看公司各部门人数',
            textStyle: {
              color: '#fff'
            },
            left: '40%',
            top: '15',
          }
        ],
        polar: {
          radius: [30, '60%']
        },
        angleAxis: {
          max: 4,
          startAngle: 75
        },
        radiusAxis: {
          type: 'category',
          data: department_name
        },
        tooltip: {},
        series: {
          type: 'bar',
          data: count,
          coordinateSystem: 'polar',
          label: {
            show: true,
            position: 'middle',
            formatter: '{b}: {c}'
          }
        }
      };
      //实例化图表
      if(option && typeof option === 'object'){
      myChart.setOption(option);
      };
      window.addEventListener("resize", function() {
        // 让我们的图表调用 resize这个方法
        myChart.resize();
      });
    }
  })

})();
(function () {
  var educational
  var num
  $.ajax({
    methods: 'GET',
    url: 'http://119.23.241.76:8000/view/staff/educational/distribution?company_name=软件开发科技有限公司',
    success: function (res) {
      educational = res.res.educational
      num = res.res.num
      //实例化echarts
      var myChart = echarts.init(document.getElementById('main1'));
      //指定配置项
      var app = {}
      var option;
      option = {
        title: [
          {
            text: '员工教育分布',
            left: '45%',
            top: '15',
            textStyle: {
              color: '#fff'
            }
          }
        ],
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          top: 'bottom',
          data: [
            'rose1',
            'rose2',
            'rose3'
          ]
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
            name: '员工教育分布',
            type: 'pie',
            radius: [20, 140],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 5
            },
            data: [
              { value: num[0], name: educational[0] },
              { value: num[1], name: educational[1] },
              { value: num[2], name: educational[2] }
            ]
          }
        ]
      };
      //实例化图表
      if(option && typeof option === 'object'){
      myChart.setOption(option);
      };
      window.addEventListener("resize", function() {
        // 让我们的图表调用 resize这个方法
        myChart.resize();
      });
    }
  })

})();
(function () {
  var name
  var sta1
  var sta0
  $.ajax({
    methods: 'GET',
    url: 'http://119.23.241.76:8000/view/interview/people/count?company_name=软件开发科技有限公司',
    success: function (res) {
      name = res.阶段名字
      sta1 = res.状态是1
      sta0 = res.状态是0
      // sta11 = sta1
      // console.log(sta1);
      // sta2 = sta1
      // var sta2
      // for (let index = 0; index < sta1.length; index++) {
      //   sta2[index] = sta1[index]+sta0[index];
      // }
      // console.log(sta2);
      // console.log(sta11);
      //实例化echarts
      var myChart = echarts.init(document.getElementById('main2'));
      //指定配置项
      var app = {}
      var option;
      option = {
        title: [
          {
            text: '招聘各阶段人数',
            left: '45%',
            top: '15',
            textStyle: {
              color: '#fff'
            }
          }
        ],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          data: ['状态是1', '状态是0','总数'],
          left: '7%',
          top: '20',
          textStyle: {
            color: '#fff'
          }
        },
        grid: {
          left: '10%',
          right: '4%',
          bottom: '13%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'value'
          }
        ],
        yAxis: [
          {
            type: 'category',
            axisTick: {
              show: false
            },
            data: name
          }
        ],
        series: [
          {
            name: '状态是1',
            type: 'bar',
            stack: 'Total',
            label: {
              show: true,
              position: 'inside'
            },
            emphasis: {
              focus: 'series'
            },
            data: sta1
          },
          // {
          //   name: '总数',
          //   type: 'bar',
          //   label: {
          //     show: true,
          //     position: 'inside'
          //   },
          //   emphasis: {
          //     focus: 'series'
          //   },
          //   data: sta2
          // },
          {
            name: '状态是0',
            type: 'bar',
            
            label: {
              show: true,
              position: 'inside'
            },
            emphasis: {
              focus: 'series'
            },
            data: sta0
          }
        ]
      };
      //实例化图表
      if(option && typeof option === 'object'){
      myChart.setOption(option);
      };
      window.addEventListener("resize", function() {
        // 让我们的图表调用 resize这个方法
        myChart.resize();
      });
    }
  })

})();
(function () {
  var department
  var num
  $.ajax({
    methods: 'GET',
    url: 'http://119.23.241.76:8000/view/project/completion?company_name=软件开发科技有限公司',
    success: function (res) {
      department = res.res.部门名和状态
      console.log(department);
      status0 = res.res.num
      //实例化echarts
      var myChart = echarts.init(document.getElementById('main3'));
      //指定配置项
      var app = {}
      var option;
      option = {
        xAxis: {
          type: 'category',
          data: department
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: status0,
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
              color: 'rgba(180, 180, 180, 0.2)'
            }
          }
        ]
      };
      //实例化图表
      if(option && typeof option === 'object'){
      myChart.setOption(option);
      };
      window.addEventListener("resize", function() {
        // 让我们的图表调用 resize这个方法
        myChart.resize();
      });
    }
  })

})();
(function () {
  var sexName
  var sexNum
  $.ajax({
    methods: 'GET',
    url:'http://119.23.241.76:8000/view/staff/sex?company_name=软件开发科技有限公司',
    success: function (res) {
      sexName = res.res.sex
      sexNum = res.res.num
      //实例化echarts
      var myChart = echarts.init(document.getElementById('main4'));
      //指定配置项
      var app = {}
      var option;
      option = {
        title: [
          {
            text: '公司人员性别占比',
            left: '40%',
            top: '15',
            textStyle: {
              color: '#fff'
            }
          }
        ],
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: '30%',
          top: '80',
          textStyle: {
            color: '#fff'
          }
        },
        series: [
          {
            name: '公司人员性别占比',
            type: 'pie',
            radius: '50%',
            data: [
              { value: sexNum[0], name: sexName[0] },
              { value: sexNum[1], name: sexName[1] }
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
      //实例化图表
      if(option && typeof option === 'object'){
      myChart.setOption(option);
      };
      window.addEventListener("resize", function() {
        // 让我们的图表调用 resize这个方法
        myChart.resize();
      });
    }
  })
  

  

})();
