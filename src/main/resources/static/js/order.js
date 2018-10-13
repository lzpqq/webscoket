layui.use(['layer', 'table' , 'form' ], function () {
    var layer = layui.layer,
        table = layui.table,
        form = layui.form,
        $ = layui.jquery;

    var orderObject = {

        onBar : function(){

            var data = $('#data').val();

            $.ajax({
                type : 'GET',
                url : '/orderInfo',
                data : {number : data},
                success : function (rep) {
                    var html = '';
                    var bank = '';
                    var zfb = '';
                    var bankInfo = rep.data.bankInfo;
                    for(var i = 0;i < bankInfo.length;i ++){
                        console.log(bankInfo[i]);
                        html += '<input type="radio" class="radio" name="bankType" value="' + bankInfo[i].bankType + '" />';
                        if(bankInfo[i].bankType == 1){
                            html += '银行卡<br/>';
                            bank += '<input type="hidden" id="bankId" value="' + bankInfo[i].id + '" />';
                            bank += '用户名:' + bankInfo[i].userName + '<br/>';
                            bank += '卡号:' + bankInfo[i].bankNumber + '<br/>';
                            bank += '银行:' + bankInfo[i].bankName + '<br/>';
                            bank += '所属分行:' + bankInfo[i].bankAddress;
                        }
                        if(bankInfo[i].bankType == 2){
                            html += '支付宝<br/>';
                            zfb += '<input type="hidden" id="bankId" value="' + bankInfo[i].id + '" />';
                            zfb += '用户名:' + bankInfo[i].userName + '<br/>';
                            zfb += '备注:' + bankInfo[i].bankNumber + '<br/>';
                            zfb += '二维码:' + '<br/>';
                            zfb += '<img src="' + bankInfo[i].qrCode + '" width="225px" height="399px">';
                        }
                    }
                    $('#orderInfo').append(html);

                    $('.radio').unbind('click').click(function(){
                        $('#zfType').empty();
                        var bankType = this.value;
                        if(bankType == 1){
                            $('#zfType').append(bank);
                        }
                        if(bankType == 2){
                            $('#zfType').append(zfb);
                        }
                        if(bankType == 3){
                            $('#zfType').append(zfb);
                        }
                    });
                },
                error : function (rep) {
                    console.log(rep);
                }
            });

            return orderObject;
		},

        myFn : function () {
            $('#ok').unbind('click').click(function () {
                var bankId = $('#bankId').val();
                var name = $('#name').val();
                console.log(bankId);
                console.log(name);
            });
        }

	};

    var initData = {
        order : function(){
            orderObject.onBar().myFn();
        }
    };
    var init = $('body').attr('data-type');
    for ( key in initData ) {
        if ( key == init ) {
            initData[key]();
        }
    }

});
