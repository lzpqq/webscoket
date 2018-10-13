package webscoket.controller;

import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import webscoket.domain.ResponseBo;
import webscoket.service.IStockRestApi;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author pc
 * @date 2018/9/30 14:51
 */
@Controller
public class StockClient {

    @Autowired
    private IStockRestApi iStockRestApi;

    @RequestMapping(value = "/index")
    public String index (){
        return "/index";
    }

    @RequestMapping(value = "/list")
    public String list (){
        return "/list";
    }

    @RequestMapping(value = "/orderPage")
    public ModelAndView order(HttpServletRequest request){
        String data = request.getParameter("data");
        String name = request.getParameter("name");
        System.out.println("------------------------------");
        System.out.println(data);
        System.out.println(name);
        System.out.println("------------------------------");
        ModelAndView mv = new ModelAndView();
        mv.addObject("name",name);
        mv.addObject("data",data);
        mv.setViewName("/order");

        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/kline",method = RequestMethod.POST)
    public ResponseBo kline(@RequestParam("symbol") String symbol,@RequestParam("type") String type){
        ResponseBo responseBo = new ResponseBo();
        try {
            String ticker = iStockRestApi.kline(symbol,type);
            responseBo.setData(ticker);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseBo;
    }
}
