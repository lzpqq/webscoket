package webscoket.service;

public interface IBTCMarketApi {


    String getBTCMarket ();

    String order(String tradeId, String amount);

    String orderInfo(String number);
}
