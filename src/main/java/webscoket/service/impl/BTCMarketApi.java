package webscoket.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import webscoket.service.IBTCMarketApi;

import java.util.HashMap;

/**
 * @author pc
 * @date 2018/10/12 16:36
 */
@Service
public class BTCMarketApi  implements IBTCMarketApi {

    @Value("${huobi.token}")
    private String token;

    @Value("${huobi.pay.pasd}")
    private String password;

    /**
     * 市场行情
     * @return
     */
    @Override
    public String getBTCMarket() {

        HttpRequest get = HttpUtil.createGet("https://otc-api.hbg.com/v1/data/trade-market?country=37&currency=1&payMethod=0&currPage=1&coinId=2&tradeType=sell&blockType=general&online=1");
        HttpRequest token = get.header("token", this.token);
        HttpResponse response = token.execute();
        String body = response.body();
        return body;
    }

    /**
     * 下单
     *
     */
    @Override
    public String order(String tradeId, String amount) {
        HttpRequest get = HttpUtil.createGet("https://otc-api.eiijo.cn/v1/otc/order/ticket?tradeId=" + tradeId);
        HttpRequest request = get.header("token", this.token);
        HttpResponse response = request.execute();
        String body = response.body();
        JSONObject jsonObject = JSON.parseObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        String ticket = (String) data.get("ticket");

        HttpRequest post = HttpUtil.createPost("https://otc-api.hbg.com/v1/otc/order");
        HttpRequest token = post.header("token", this.token);
        HashMap<String, Object> map = new HashMap<>();
        map.put("amount",amount);
        map.put("tradeId",tradeId);
        map.put("ticket",ticket);
        map.put("password",this.password);
        HttpRequest form = token.form(map);
        HttpResponse execute = form.execute();
        String body1 = execute.body();
        return body1;
    }

    @Override
    public String orderInfo(String number) {
        HttpRequest get = HttpUtil.createGet("https://otc-api.hbg.com/v1/otc/order/"+number);
        HttpRequest token = get.header("token", this.token);
        HttpResponse response = token.execute();
        String body = response.body();
        return body;
    }

}
