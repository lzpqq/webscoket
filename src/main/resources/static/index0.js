var myChart = echarts.init(document.getElementById('main'));
var upColor = '#ec0000';
var upBorderColor = '#8A0000';
var downColor = '#00da3c';
var downBorderColor = '#008F28';




$(function () {
	$('input[type ="button"]').click(function () {
		var type =this.value;
		console.log(this.value);
		$.ajax({
			url:'/kline',
			data:{symbol:'btc_usd',type:type},
			method:'post',
			success:function (ret) {
				rawData = ret.data;
				rawData = eval('('+rawData+')');
				// 数据意义：开盘(open)，收盘(close)，最低(lowest)，最高(highest)
				var data0 = splitData(rawData);


				function splitData(rawData) {
					var categoryData = [];
					var values = [];
					for (var i = 0; i < rawData.length; i++) {
						categoryData.push(timestampToTime(rawData[i].splice(0, 1)[0]));
						values.push(rawData[i])
					}
					return {
						categoryData: categoryData,
						values: values
					};
				}
				function timestampToTime(timestamp) {
					var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
					var Y = date.getFullYear() + '-';
					var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
					var D = date.getDate() + ' ';
					var h = date.getHours() + ':';
					var m = date.getMinutes() + ':';
					var s = date.getSeconds();
					return Y+M+D+h+m+s;
				}
				function calculateMA(dayCount) {
					var result = [];
					for (var i = 0, len = data0.values.length; i < len; i++) {
						if (i < dayCount) {
							result.push('-');
							continue;
						}
						var sum = 0;
						for (var j = 0; j < dayCount; j++) {
							sum += data0.values[i - j][1];
						}
						result.push(sum / dayCount);
					}
					return result;
				}



				option = {
					title: {
						text: 'BTC_USD',
						left: 0
					},
					tooltip: {
						trigger: 'axis',
						axisPointer: {
							type: 'cross'
						}
					},
					legend: {
						data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30']
					},
					grid: {
						left: '10%',
						right: '10%',
						bottom: '15%'
					},
					xAxis: {
						type: 'category',
						data: data0.categoryData,
						scale: true,
						boundaryGap : false,
						axisLine: {onZero: false},
						splitLine: {show: false},
						splitNumber: 20,
						min: 'dataMin',
						max: 'dataMax'
					},
					yAxis: {
						scale: true,
						splitArea: {
							show: true
						}
					},
					dataZoom: [
						{
							type: 'inside',
							start: 50,
							end: 100
						},
						{
							show: true,
							type: 'slider',
							y: '90%',
							start: 50,
							end: 100
						}
					],
					series: [
						{
							name: '日K',
							type: 'candlestick',
							data: data0.values,
							itemStyle: {
								normal: {
									color: upColor,
									color0: downColor,
									borderColor: upBorderColor,
									borderColor0: downBorderColor
								}
							},
							markPoint: {
								label: {
									normal: {
										formatter: function (param) {
											return param != null ? Math.round(param.value) : '';
										}
									}
								},
								data: [
									{
										name: 'XX标点',
										coord: ['2013/5/31', 2300],
										value: 2300,
										itemStyle: {
											normal: {color: 'rgb(41,60,85)'}
										}
									},
									{
										name: 'highest value',
										type: 'max',
										valueDim: 'highest'
									},
									{
										name: 'lowest value',
										type: 'min',
										valueDim: 'lowest'
									},
									{
										name: 'average value on close',
										type: 'average',
										valueDim: 'close'
									}
								],
								tooltip: {
									formatter: function (param) {
										return param.name + '<br>' + (param.data.coord || '');
									}
								}
							},
							markLine: {
								symbol: ['none', 'none'],
								data: [
									[
										{
											name: 'from lowest to highest',
											type: 'min',
											valueDim: 'lowest',
											symbol: 'circle',
											symbolSize: 10,
											label: {
												normal: {show: false},
												emphasis: {show: false}
											}
										},
										{
											type: 'max',
											valueDim: 'highest',
											symbol: 'circle',
											symbolSize: 10,
											label: {
												normal: {show: false},
												emphasis: {show: false}
											}
										}
									],
									{
										name: 'min line on close',
										type: 'min',
										valueDim: 'close'
									},
									{
										name: 'max line on close',
										type: 'max',
										valueDim: 'close'
									}
								]
							}
						},
						{
							name: 'MA5',
							type: 'line',
							data: calculateMA(5),
							smooth: true,
							lineStyle: {
								normal: {opacity: 0.5}
							}
						},
						{
							name: 'MA10',
							type: 'line',
							data: calculateMA(10),
							smooth: true,
							lineStyle: {
								normal: {opacity: 0.5}
							}
						},
						{
							name: 'MA20',
							type: 'line',
							data: calculateMA(20),
							smooth: true,
							lineStyle: {
								normal: {opacity: 0.5}
							}
						},
						{
							name: 'MA30',
							type: 'line',
							data: calculateMA(30),
							smooth: true,
							lineStyle: {
								normal: {opacity: 0.5}
							}
						},

					]
				};
				myChart.setOption(option);
			}
		})

	})

});
