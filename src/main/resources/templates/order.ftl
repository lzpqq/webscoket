<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>list</title>
    <#--<script type="text/javascript" src="echarts.min.js"></script>-->
    <link rel="stylesheet" href="layui/css/layui.css">
    <script type="text/javascript" src="jquery-3.3.1.js"></script>
    <script type="text/javascript" src="layui/layui.js"></script>
</head>
<body data-type="order">
<input type="hidden" id="name" value="${RequestParameters.name}" />
<input type="hidden" id="data" value="${RequestParameters.data}" />
<div style="float: left">
    <span style="font-size: 20px" id="orderInfo">支付方式：<br/></span>
    <div>
        <input type="button" id="ok" value="确认已付款"/>
    </div>
</div>
<div id="zfType" style="padding-left: 300px;float: left"></div>

<script type="text/javascript" src="js/order.js"></script>
</body>
</html>
