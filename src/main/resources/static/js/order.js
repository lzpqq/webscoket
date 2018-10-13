layui.use(['layer', 'table' , 'form' ], function () {
    var layer = layui.layer,
        table = layui.table,
        form = layui.form,
        $ = layui.jquery;

    var orderObject = {

        onBar : function(){



		}

	};

    var initData = {
        order : function(){
            orderObject.onBar();
        }
    };
    var init = $('body').attr('data-type');
    for ( key in initData ) {
        if ( key == init ) {
            initData[key]();
        }
    }

});
