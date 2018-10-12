package webscoket.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import webscoket.service.IBTCMarketApi;

/**
 * @author pc
 * @date 2018/10/12 16:36
 */
@Service
public class BTCMarketApi  implements IBTCMarketApi {

    @Value("${huobi.token}")
    private String token;


    @Override
    public String getBTCMarket() {

        HttpRequest get = HttpUtil.createGet("https://otc-api.eiijo.cn/v1/data/trade-market?country=37&currency=1&payMethod=0&currPage=1&coinId=2&tradeType=sell&blockType=general&online=1");
        HttpRequest token = get.header("token", this.token);
        HttpResponse response = token.execute();
        String body = response.body();
        return body;


    }
}
