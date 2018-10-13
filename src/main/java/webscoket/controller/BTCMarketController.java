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
        JSONObject jsonObject = JSON.parseObject(orderInfo);
        Object data = jsonObject.get("data");
        ResponseBo responseBo = new ResponseBo();
        responseBo.setData(data);
        return  responseBo;
    }


}
