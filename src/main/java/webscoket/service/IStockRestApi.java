package webscoket.service;


import org.apache.http.HttpException;

import java.io.IOException;

/**
 * 现货行情，交易 REST API
 * @author zhangchi
 *
 */
public interface IStockRestApi {

    /**
     * 行情
     * @param symbol btc_usd:比特币    ltc_usd :莱特币
     * @return
     * @throws IOException
     * @throws HttpException
     */
    String ticker(String symbol) throws HttpException, IOException;

    /**
     * 市场深度
     * @param symbol btc_usd:比特币    ltc_usd :莱特币
     * @return
     * @throws IOException
     * @throws HttpException
     */
    String depth(String symbol) throws HttpException, IOException;

    /**
     * k线数据
     * @param symbol btc_usd:比特币    ltc_usd :莱特币
     * @param type K线类型
     * @return
     * @throws IOException
     * @throws HttpException
     */
    String kline(String symbol, String type) throws HttpException, IOException;

}
