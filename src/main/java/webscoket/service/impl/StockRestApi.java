package webscoket.service.impl;

import org.apache.http.HttpException;
import org.springframework.stereotype.Service;
import webscoket.service.IStockRestApi;
import webscoket.util.HttpUtilManager;
import webscoket.util.StringUtil;

import java.io.IOException;

/**
 * @author pc
 * @date 2018/9/30 14:16
 */
@Service
public class StockRestApi implements IStockRestApi {

    private String api_key = "b9b79dbc-3c66-43a3-96ac-5bb00bc77a73";  //OKCoin申请的apiKey
    private String secret_key = "EB11658CC89ECE8704217B387BC2C9DC";  //OKCoin 申请的secret_key
    private String url_prex = "https://www.okcoin.com";  //注意：请求URL 国际站https://www.okcoin.com ; 国内站https://www.okcoin.cn


    /**
     * 现货行情URL
     */
    private final String TICKER_URL = "/api/v1/ticker.do?";

    /**
     * 现货市场深度URL
     */
    private final String DEPTH_URL = "/api/v1/depth.do?";

    /**
     * 获取OKCoin的K线数据
     */
    private final String KLINE_URL = "/api/v1/kline.do?";


    @Override
    public String ticker(String symbol) throws HttpException, IOException {
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String param = "";
        if(!StringUtil.isEmpty(symbol )) {
            if (!param.equals("")) {
                param += "&";
            }
            param += "symbol=" + symbol;
        }
        String result = httpUtil.requestHttpGet(url_prex, TICKER_URL, param);
        return result;
    }

    @Override
    public String depth(String symbol) throws HttpException, IOException {
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String param = "";
        if(!StringUtil.isEmpty(symbol )) {
            if(!param.equals("")) {
                param += "&";
            }
            param += "symbol=" + symbol;
        }
        String result = httpUtil.requestHttpGet(url_prex, this.DEPTH_URL, param);
        return result;
    }

    @Override
    public String kline(String symbol, String type) throws HttpException, IOException {
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String param = "";
        if(!StringUtil.isEmpty(symbol )) {
            if (!param.equals("")) {
                param += "&";
            }
            param += "symbol=" + symbol;
        }
        if(!StringUtil.isEmpty(type )) {
            if (!param.equals("")) {
                param += "&";
            }
            param += "type=" + type;
        }
        String result = httpUtil.requestHttpGet(url_prex, this.KLINE_URL, param);
        return result;
    }

}

