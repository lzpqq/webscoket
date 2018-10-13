layui.use(['layer', 'table' , 'form' ], function () {
    var layer = layui.layer,
        table = layui.table,
        form = layui.form,
        $ = layui.jquery;

    var listObject = {

        getList : function(){
            $.ajax({
                url : '/listMarket',
                type : 'get',
                success : function (rep) {

                    var html = '<table border="1">';
                    html += '<tr><td width="200px">商家</td><td width="200px">数量</td><td width="200px">单价(元)</td><td width="200px">下限</td><td width="200px">上限</td><td width="60px"></td></tr>';

                    var arr = rep.data;
                    for(var i = 0;i < arr.length;i ++){
                    	var t = arr[i];
                        //tradeCount(数量) userName(商家) id(hidden) minTradeLimit(下限) maxTradeLimit(上限) price(单价)
						html += '<tr><td width="200px">' + t.userName + '</td><td width="200px">' + t.tradeCount + '</td><td width="200px">' + t.price + '</td><td width="200px">' + t.minTradeLimit + '</td><td width="200px">' + t.maxTradeLimit + '</td>' + '<td width="60px"><span id="' + t.id + '" class="buy">认购</span></td></tr>';
                    }

                    html += '</table>';

                    $('#table').append(html);

                    listObject.onBar();
                },
                error : function (rep) {
                    console.log(rep);
                }
            })
		},

        onBar : function(){

        	$('.buy').unbind('click').click(function(){
        		var id = this.id;
                var html = '';
                html += '<div>金&nbsp;&nbsp;&nbsp;额:<input type="text" id="money" placeholder="请输入金额" /><br/>';
                html += '用户名:<input type="text" id="name" placeholder="请输入用户名" /></div>';

                openId = layer.open({
                    title:'认购',
                    type: 1,
                    skin: 'layui-layer-rim',
                    area: ['260px', '160px'],
                    content: html,
                    btn : ['yes','no'],
                    yes : function(){
                        var money = $('#money').val();
                        var name = $('#name').val();
                        console.log(money);
                        console.log(id);
						$.ajax({
							url : '/order',
							type : 'POST',
							data : {tradeId : id,amount : money},
							success : function (rep) {
								console.log(rep.msg);
                                var data = rep.data;

                                //跳页面，参数:data,name
                                location.href="/orderPage?name=" + name + "&data=" + data;
                            },
							error : function (rep) {
								console.log(rep);
                            }
						})
                    },
                    btnAlign: 'c'
                });
			});

		}

	};

    var initData = {
        list : function(){
            listObject.getList();
        }
    };
    var init = $('body').attr('data-type');
    for ( key in initData ) {
        if ( key == init ) {
            initData[key]();
        }
    }

});
