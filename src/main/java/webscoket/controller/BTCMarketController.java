package webscoket.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webscoket.domain.ResponseBo;
import webscoket.service.IBTCMarketApi;

/**
 * @author pc
 * @date 2018/10/12 16:34
 */
@RestController
public class BTCMarketController {


    @Autowired
    private IBTCMarketApi ibtcMarketApi;

    @RequestMapping(value = "/listMarket",method = RequestMethod.GET)
    public ResponseBo listMarket(){
        String btcMarket = ibtcMarketApi.getBTCMarket();

        JSONObject jsonObject = JSON.parseObject(btcMarket);
        Object data = jsonObject.get("data");
        ResponseBo responseBo = new ResponseBo();
        responseBo.setData(data);
        return  responseBo;
    }

    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public ResponseBo order(@RequestParam("tradeId") String tradeId, @RequestParam("amount")String amount){
        String order = ibtcMarketApi.order(tradeId, amount);
        JSONObject jsonObject = JSON.parseObject(order);
        ResponseBo responseBo = new ResponseBo();
        responseBo.setMsg((String) jsonObject.get("message"));
        responseBo.setData(jsonObject.get("data"));
        return  responseBo;
    }

    @RequestMapping(value = "/orderInfo",method = RequestMethod.GET)
    public ResponseBo orderInfo(@RequestParam("number") String number){
        String orderInfo = ibtcMarketApi.orderInfo(number);
//        String orderInfo = "{\"code\":200,\"message\":\"成功\",\"data\":{\"bankInfo\":[{\"id\":944148,\"userName\":\"钟明洲\",\"bankType\":1,\"bankNumber\":\"6216911705948995\",\"bankName\":\"中国民生银行\",\"bankAddress\":\"汕头潮南支行\",\"qrCode\":null},{\"id\":1097303,\"userName\":\"钟明洲\",\"bankType\":2,\"bankNumber\":\"不要备注敏感字\",\"bankName\":\"\",\"bankAddress\":null,\"qrCode\":\"https://file.eiijo.cn/huobi/otc/account/49913622/81a82ca9a89649efae7aa78f9cbc6154.jpg\"}],\"otherInfo\":{\"uid\":49913622,\"userName\":\"不完美小孩\",\"gmtCreate\":1533650283000,\"merchantLevel\":2,\"marginCoinId\":4,\"marginAmount\":5000.0000000000,\"appealMonthTimes\":104,\"appealMonthWinTimes\":103,\"tradeMonthCount\":11994,\"isOnline\":true},\"trade\":{\"isPayCode\":true},\"kycAuthStatus\":2,\"isTaker\":true,\"appeal\":{\"appealCode\":null,\"isCancle\":null,\"type\":null,\"description\":null},\"isSoonLock\":false,\"legalService\":null,\"order\":{\"id\":8094960,\"orderNo\":\"115393291006995\",\"tradeType\":0,\"coinId\":2,\"currency\":1,\"price\":6.90,\"quantity\":14.492753,\"amount\":100.00,\"fee\":\"0.000000\",\"status\":3,\"payTerm\":15,\"payCode\":\"610045\",\"gmtCreate\":1539329101000,\"gmtModified\":1539329181000,\"gmtResetCancel\":null,\"gmtPay\":1539329143000,\"now\":1539413521132,\"tradeUserName\":\"不完美小孩\",\"tradeRealName\":\"钟明洲\",\"tradeUid\":49913622,\"buyPayAccount\":1097303}},\"success\":true}";
        JSONObject jsonObject = JSON.parseObject(orderInfo);
        Object data = jsonObject.get("data");
        ResponseBo responseBo = new ResponseBo();
        responseBo.setData(data);
        return  responseBo;
    }


}
