package com.pay.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jubaobar.pay.JubaoPay;
import com.jubaobar.pay.RSA;
import com.pay.service.OrderService;
import com.pay.util.JsonHelper;
import com.pay.util.MD5;

@Controller
@RequestMapping("/order")
public class OrderController extends BasicController{
	
	@Autowired
	private OrderService orderService;
	
	//海豚
	@RequestMapping("/orderCallBack")
	public void orderCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String memberID = request.getParameter("Sjt_MerchantID");
		String terminalID = request.getParameter("Sjt_Sign");
		String transID = request.getParameter("Sjt_TransID");
		String result = request.getParameter("Sjt_Return");
		String resultDesc = request.getParameter("Sjt_Error");
		String factMoney = request.getParameter("Sjt_factMoney");
		String additionalInfo = request.getParameter("Sjt_UserName");
		String succTime = request.getParameter("Sjt_SuccTime");
		String bankID = request.getParameter("Sjt_BType");

		
		Map<String,String> map = new HashMap<String,String>();
		map.put("memberID", memberID);
		map.put("terminalID", terminalID);
		map.put("transID", transID);
		map.put("result", result);
		map.put("resultDesc", resultDesc);
		map.put("factMoney", factMoney);
		map.put("additionalInfo", additionalInfo);
		map.put("succTime", succTime);
		map.put("bankID", bankID);
		orderService.saveOrder(map);
		writeStr(response, "ok");
	}
	
	//老江平
	@RequestMapping("/jporderCallBack")
	public void jporderCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String transID = request.getParameter("orderno");
		String factMoney = request.getParameter("fee");
		String bankID = request.getParameter("pay_type");
		String terminalID = request.getParameter("app_id");
		String additionalInfo = request.getParameter("attach");
		String resultdesc = request.getParameter("state");
		String result = "0";
		String succTime = "-1";
		if("success".equals(resultdesc) && orderService.getOrderByTransID(transID) == 0)
		{
			result = "1";
			succTime = String.valueOf(System.currentTimeMillis()/1000);
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("transID", transID);
			map.put("bankID", bankID);
			map.put("factMoney", factMoney);
			map.put("additionalInfo", additionalInfo);
			map.put("terminalID", terminalID);
			map.put("result", result);
			map.put("resultdesc", resultdesc);
			map.put("succTime", succTime);
			
			orderService.saveOrderJP(map);
			writeStr(response, "ok");
		}
		else
		{
			writeStr(response, "false");
		}
	}
	
	//旧 顺手云 大班班
	@RequestMapping("/gongdapengCallBack")
	public void gongdapengCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
//		 BufferedReader in=new BufferedReader(new InputStreamReader(request.getInputStream()));
//		 StringBuilder sb = new StringBuilder();   
//         String line = null;  
//         while ((line = in.readLine()) != null) {   
//         sb.append(line);   
//           } 
//		
//		String jsonString = new String ((sb.toString()).getBytes("iso-8859-1"), "UTF-8");//"{\"Error\":1,\"Message\":\"Success\",\"MchId\":\"50000096\",\"MchTradeNo\":\"14835158765919btC4J\",\"OutTradeNo\":\"2017010421001004710257657718\",\"TradeAttach\":\"UmengTestAiv\",\"ActuallyMoney\":2,\"TimeEnd\":1483515890,\"Sign\":\"19ace257429edd0e844e4545785a31fd\"}";
////		System.out.println("jsonString = " + jsonString);
//		try {
//			String key = "BB1C32373A9BB3F0";
//			Map mapJson = JsonHelper.toMap(jsonString);
//			String result = (String)mapJson.get("Error");
//			String resultDesc = (String)mapJson.get("Message");
//			String memberID = (String)mapJson.get("MchId"); 
//			String transID = (String)mapJson.get("MchTradeNo"); 
//			String terminalID = (String)mapJson.get("OutTradeNo"); 
//			String additionalInfo = (String)mapJson.get("TradeAttach"); 
//			String factMoney = (String)mapJson.get("ActuallyMoney"); 
//			String succTime = (String)mapJson.get("TimeEnd"); 
//			String sign = (String)mapJson.get("Sign");
//			
//			if(result == null)
//				result = "";
//			if(resultDesc == null)
//				resultDesc = "";
//			if(memberID == null)
//				memberID = "";
//			if(transID == null)
//				transID = "";
//			if(terminalID == null)
//				terminalID = "";
//			if(additionalInfo == null)
//				additionalInfo = "";
//			if(factMoney == null)
//				factMoney = "";
//			if(succTime == null)
//				succTime = "";
//			
//			
////			System.out.println("result = "+result);
////			System.out.println("resultDesc = " + resultDesc);
////			System.out.println("memberID = " + memberID);
////			System.out.println("transID = " + transID);
////			System.out.println("terminalID = " + terminalID);
////			System.out.println("additionalInfo = " + additionalInfo);
////			System.out.println("factMoney = " + factMoney);
////			System.out.println("succTime = " + succTime);
////			System.out.println("sign = " + sign);
//			
////			System.out.println("codesrc = " + result+resultDesc+memberID+transID+terminalID+additionalInfo+factMoney+succTime+key);
//			String code = MD5.getMD5(result+resultDesc+memberID+transID+terminalID+additionalInfo+factMoney+succTime+key);
////			System.out.println("code = " + code);
//			if("1".equals(result) && code.equalsIgnoreCase(sign) && orderService.getOrderByTransID(transID) == 0)
//			{
//				Map<String,String> map = new HashMap<String,String>();
//				map.put("memberID", memberID);
//				map.put("terminalID", terminalID);
//				map.put("transID", transID);
//				map.put("result", result);
//				map.put("resultDesc", resultDesc);
//				float money = Float.parseFloat(factMoney)/100;
//				map.put("factMoney", String.valueOf(money));
//				map.put("additionalInfo", additionalInfo);
//				map.put("succTime", succTime);
//				map.put("bankID", "1");
//				orderService.saveOrdergongdapeng(map);
//				
//				writeStr(response, "success");
//			}
//			else
//			{
//				writeStr(response, "fail");
//			}
//			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String result = request.getParameter("result");
		String transID = request.getParameter("other_order");
		String memberID = request.getParameter("order_no");
		String bankID = request.getParameter("pay_type");
		String factMoney = request.getParameter("pay_amt");
		
		String resultDesc = "";
		if(request.getParameter("goods_name") !=  null)
			resultDesc = new String (request.getParameter("goods_name").getBytes("iso-8859-1"), "UTF-8");
		
		String additionalInfo = request.getParameter("custom");
		String terminalID = request.getParameter("sign");
		String key = "776c5b6a1808dd7a13f9d70a0d6dccbc";
		
		String code = MD5.getMD5(memberID+factMoney+result+key).substring(10, 22);
		System.out.println("result = " + result);
		System.out.println("code = " + code);
		System.out.println("terminalID = " + terminalID);
		
		if("1".equals(result) && code.equalsIgnoreCase(terminalID) && orderService.getOrderByMemberID(memberID) == 0)
		{
			Map<String,String> map = new HashMap<String,String>();
			map.put("memberID", memberID);
			map.put("terminalID", terminalID);
			map.put("transID", transID);
			map.put("result", result);
			map.put("resultDesc", resultDesc);
			map.put("factMoney", factMoney);
			map.put("additionalInfo", additionalInfo);
			map.put("bankID", bankID);
			orderService.saveOrdergongdapeng(map);
			
			writeStr(response, "ok");
		}
		else
		{
			writeStr(response, "fail");
		}
	}
	
	//中兴
	@RequestMapping("/hzylCallBack")
	public void hzylCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String key = "hzyl";
		String memberID = request.getParameter("order_id"); 
		String order_id = request.getParameter("order_id"); 
		String transID = request.getParameter("orderNo"); 
		String succTime = request.getParameter("time"); 
		String terminalID = request.getParameter("sign");
		String factMoney = request.getParameter("money"); 
		String bankID =  request.getParameter("pay_type"); 
		String additionalInfo = request.getParameter("extra"); 
		
		String[] sourceStrArray = memberID.split("_", 2);
		if(sourceStrArray.length == 2){
			memberID = sourceStrArray[0];
			additionalInfo = sourceStrArray[1];
		}
		else
		{
			System.out.println("memberID = " + memberID);
			writeStr(response, "fail");
			return;
		}
		
		System.out.println("memberID = " + memberID);
		System.out.println("transID = " + transID);
		System.out.println("bankID = " + bankID);
		System.out.println("additionalInfo = " + additionalInfo);
		System.out.println("factMoney = " + factMoney);
		System.out.println("succTime = " + succTime);
		System.out.println("terminalID = " + terminalID);
		
		System.out.println("codesrc = " + key+order_id+succTime);
		String code = MD5.getMD5(key+order_id+succTime);
		System.out.println("code = " + code);
		if(code.equalsIgnoreCase(terminalID))
		{
			Map<String,String> map = new HashMap<String,String>();
			map.put("memberID", memberID);
			map.put("terminalID", terminalID);
			map.put("transID", transID);
			map.put("result", "1");
			float money = Float.parseFloat(factMoney)/100;
			map.put("factMoney", String.valueOf(money));
			map.put("additionalInfo", additionalInfo);
			map.put("succTime", succTime);
			map.put("bankID", bankID);
			orderService.saveOrderhzyl(map);
			
			writeStr(response, "success");
		}
		else
		{
			writeStr(response, "fail");
		}
		
		
		
//		 BufferedReader in=new BufferedReader(new InputStreamReader(request.getInputStream()));
//		 StringBuilder sb = new StringBuilder();   
//        String line = null;  
//        while ((line = in.readLine()) != null) {   
//        sb.append(line);   
//          } 
//		
//		String jsonString = new String ((sb.toString()).getBytes("iso-8859-1"), "UTF-8");//"{\"Error\":1,\"Message\":\"Success\",\"MchId\":\"50000096\",\"MchTradeNo\":\"14835158765919btC4J\",\"OutTradeNo\":\"2017010421001004710257657718\",\"TradeAttach\":\"UmengTestAiv\",\"ActuallyMoney\":2,\"TimeEnd\":1483515890,\"Sign\":\"19ace257429edd0e844e4545785a31fd\"}";
//		System.out.println("jsonString = " + jsonString);
//		
//		try {
//			String key = "hzyl";
//			Map mapJson = JsonHelper.toMap(jsonString);
//			String memberID = (String)mapJson.get("order_id"); 
//			String transID = (String)mapJson.get("orderNo"); 
//			String succTime = (String)mapJson.get("time"); 
//			String terminalID = (String)mapJson.get("Sign");
//			String factMoney = (String)mapJson.get("money"); 
//			String bankID =  (String)mapJson.get("pay_type"); 
//			String additionalInfo = (String)mapJson.get("extra"); 
//			
//		
//			if(memberID == null)
//				memberID = "";
//			if(transID == null)
//				transID = "";
//			if(succTime == null)
//				succTime = "";
//			if(terminalID == null)
//				terminalID = "";
//			if(factMoney == null)
//				factMoney = "";
//			if(bankID == null)
//				bankID = "";
//			if(additionalInfo == null)
//				additionalInfo = "";
//
//			System.out.println("memberID = " + memberID);
//			System.out.println("transID = " + transID);
//			System.out.println("bankID = " + bankID);
//			System.out.println("additionalInfo = " + additionalInfo);
//			System.out.println("factMoney = " + factMoney);
//			System.out.println("succTime = " + succTime);
//			System.out.println("terminalID = " + terminalID);
//			
//			System.out.println("codesrc = " + key+memberID+succTime);
//			String code = MD5.getMD5(key+memberID+succTime);
//			System.out.println("code = " + code);
//			if(code.equalsIgnoreCase(terminalID))
//			{
//				Map<String,String> map = new HashMap<String,String>();
//				map.put("memberID", memberID);
//				map.put("terminalID", terminalID);
//				map.put("transID", transID);
//				map.put("result", "1");
//				float money = Float.parseFloat(factMoney)/100;
//				map.put("factMoney", String.valueOf(money));
//				map.put("additionalInfo", additionalInfo);
//				map.put("succTime", succTime);
//				map.put("bankID", bankID);
//				orderService.saveOrderhzyl(map);
//				
//				writeStr(response, "success");
//			}
//			else
//			{
//				writeStr(response, "fail");
//			}
//			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@RequestMapping("/jubaoCallBack")
	public void jubaoCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException{
//		StringBuilder sb = new StringBuilder();
//	    BufferedReader reader = request.getReader();
//	    try {
//	        String line;
//	        while ((line = reader.readLine()) != null) {
//	            sb.append(line).append('\n');
//	        }
//	    } finally {
//	        reader.close();
//	    }
//	    System.out.println("jubao:"+sb.toString());
	    
//	    Map<String, String[]> params = request.getParameterMap();  
//        String queryString = "";  
//        for (String key : params.keySet()) {  
//            String[] values = params.get(key);  
//            for (int i = 0; i < values.length; i++) {  
//                String value = values[i];  
//                queryString += key + "=" + value + "&";  
//            }  
//        }  
//        // 去掉最后一个空格  
//        queryString = queryString.substring(0, queryString.length() - 1);  
//        writer.println("POST " + request.getRequestURL() + " " + queryString); 
        
		//JSONObject json = JSONObject.parseObject(sb.toString());

		String message = request.getParameter("message");
		String signature = request.getParameter("signature");
		System.out.println("jubao message:"+message);
		System.out.println("jubao signature:"+signature);
		RSA.intialize();
		// 解密，校验签名，并处理业务逻辑处理
		JubaoPay jubaopay = new JubaoPay();
		boolean result = jubaopay.decrypt(message, signature);
		String payid=jubaopay.getEncrypt("payid");
		String mobile=jubaopay.getEncrypt("mobile");
		String amount=jubaopay.getEncrypt("amount");
		String remark=jubaopay.getEncrypt("remark");
		String orderNo=jubaopay.getEncrypt("orderNo");
		String state=jubaopay.getEncrypt("state");
		String modifyTime=jubaopay.getEncrypt("modifyTime");
		System.out.println("payid:"+payid);
		System.out.println("mobile:"+mobile);
		System.out.println("amount:"+amount);
		System.out.println("remark:"+remark);
		System.out.println("orderNo:"+orderNo);
		System.out.println("state:"+state);
		System.out.println("modifyTime:"+modifyTime);

		if (state.equals("2")) 
		{
			System.out.println("start save db");
			Map<String,String> map = new HashMap<String,String>();
			map.put("memberID", mobile);
			map.put("terminalID", orderNo);
			map.put("transID", payid);
			map.put("result", state);
			map.put("resultDesc", "");
			map.put("factMoney", amount);
			map.put("additionalInfo", remark);
			map.put("succTime", modifyTime);
			map.put("bankID", "jubao");
			orderService.saveOrderJuBao(map);
		}
		
		System.out.println("result:"+result);
		
		// 输出正确的响应
		if(result){
			writeStr(response, "success");
		} else {
			// 签名验证失败
			writeStr(response, "failed");
		}
	}

	//新江平
	@RequestMapping("/newjporderCallBack")
	public void newjporderCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String mchno =  "1086";
		String key = "a3721a4bfd5faf80f636397ec2b47e83";
		
		String result = request.getParameter("result");
		String factMoney = request.getParameter("money");
		String memberID = request.getParameter("paymentno");
		String transID = request.getParameter("orderno");
		String bankID = request.getParameter("payType");
		String succTime = request.getParameter("paytime");
		String resultDesc = request.getParameter("returnUrl");
		String additionalInfo = request.getParameter("attach");
		String terminalID = request.getParameter("sign");
		String code = MD5.getMD5(factMoney+"|"+mchno+"|"+key);
		
		System.out.println("result = "+result);
		System.out.println("resultDesc = " + resultDesc);
		System.out.println("memberID = " + memberID);
		System.out.println("transID = " + transID);
		System.out.println("terminalID = " + terminalID);
		System.out.println("additionalInfo = " + additionalInfo);
		System.out.println("factMoney = " + factMoney);
		System.out.println("succTime = " + succTime);
		System.out.println("bankID = " + bankID);
		System.out.println("sign = " + code);
		
		if("1".equals(result) && code.equalsIgnoreCase(terminalID) && orderService.getOrderByTransID(transID) == 0)
		{
			Map<String,String> map = new HashMap<String,String>();
			map.put("memberID", memberID);
			map.put("terminalID", terminalID);
			map.put("transID", transID);
			map.put("result", result);
			map.put("resultDesc", resultDesc);
			map.put("factMoney", factMoney);
			map.put("additionalInfo", additionalInfo);
			map.put("bankID", bankID);
			map.put("succTime",succTime);
			
			orderService.saveNewOrderJP(map);
			
			writeStr(response, "ok");
		}
		else
		{
			writeStr(response, "fail");
		}
	}
	
	//微钱宝
	@RequestMapping("/weiqianbaoCallBack")
	public void  orderWeiQianBaoCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String mchno =  "1110";
		String key = "1e6907258e62cca674827271e75b764b";
		
		String result = request.getParameter("result");
		String factMoney = request.getParameter("money");
		String memberID = request.getParameter("paymentno");
		String transID = request.getParameter("orderno");
		String bankID = request.getParameter("payType");
		String succTime = request.getParameter("paytime");
		String resultDesc = request.getParameter("returnUrl");
		String additionalInfo = request.getParameter("attach");
		String terminalID = request.getParameter("sign");
		String sign2 = request.getParameter("sign2");
		String code = MD5.getMD5(factMoney+"|"+mchno+"|"+memberID+"|"+key);
		
		System.out.println("result = "+result);
		System.out.println("resultDesc = " + resultDesc);
		System.out.println("memberID = " + memberID);
		System.out.println("transID = " + transID);
		System.out.println("terminalID = " + terminalID);
		System.out.println("additionalInfo = " + additionalInfo);
		System.out.println("factMoney = " + factMoney);
		System.out.println("succTime = " + succTime);
		System.out.println("bankID = " + bankID);
		System.out.println("code = " + code);
		System.out.println("sign2 = " + sign2);
		
		if("1".equals(result) && code.equalsIgnoreCase(sign2) && orderService.getOrderByTransID(transID) == 0)
		{
			Map<String,String> map = new HashMap<String,String>();
			map.put("memberID", memberID);
			map.put("terminalID", terminalID);
			map.put("transID", transID);
			map.put("result", result);
			map.put("resultDesc", resultDesc);
			map.put("factMoney", factMoney);
			map.put("additionalInfo", additionalInfo);
			map.put("bankID", bankID);
			map.put("succTime",succTime);
			
			orderService.saveOrderWeiQianBao(map);
			
			writeStr(response, "ok");
		}
		else
		{
			writeStr(response, "fail");
		}
	}
	
	
	// 新顺手云
	@RequestMapping("/shunshouyunorderCallBack")
	public void shunshouyunorderCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String key = "eaaa5123ab4a1c4fe1ad0c3c5850f65b";
		
		String result = request.getParameter("result");
		String transID = request.getParameter("other_order");
		String memberID = request.getParameter("order_no");
		String bankID = request.getParameter("pay_type");
		String factMoney = request.getParameter("pay_amt");
		
		String resultDesc = "";
		if(request.getParameter("goods_name") !=  null)
			resultDesc = new String (request.getParameter("goods_name").getBytes("iso-8859-1"), "UTF-8");
		
		String additionalInfo = request.getParameter("custom");
		String terminalID = request.getParameter("sign");
		
		String code = MD5.getMD5(memberID+factMoney+result+key).substring(10, 22);
		System.out.println("result = " + result);
		System.out.println("code = " + code);
		System.out.println("terminalID = " + terminalID);
		
		if(orderService.getOrderByMemberID(memberID) == 0)
		{
			if("1".equals(result) && code.equalsIgnoreCase(terminalID))
			{
				Map<String,String> map = new HashMap<String,String>();
				map.put("memberID", memberID);
				map.put("terminalID", terminalID);
				map.put("transID", transID);
				map.put("result", result);
				map.put("resultDesc", resultDesc);
				map.put("factMoney", factMoney);
				map.put("additionalInfo", additionalInfo);
				map.put("bankID", bankID);
				orderService.saveOrderShunShouYun(map);
				
				writeStr(response, "ok");
			}
			else
			{
				writeStr(response, "fail");
			}
		}
		else
		{
			writeStr(response, "ok");
		}
	}
	
	
	@RequestMapping("/list")
	public void videoList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int startTime = Integer.parseInt(request.getParameter("startTime"));
		int endTime = Integer.parseInt(request.getParameter("endTime"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<Map<String, Object>> list = orderService.getOrderList(map);
		JSONObject jo = new JSONObject();
		jo.put("list", list);
		writeJSONStr(response, jo.toJSONString());
	}
}
