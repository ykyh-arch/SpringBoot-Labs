package cn.iocoder.springboot.lab23.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.iocoder.springboot.lab23.springmvc.domain.MiaoshaUser;
import cn.iocoder.springboot.lab23.springmvc.domain.OrderInfo;
import cn.iocoder.springboot.lab23.springmvc.redis.RedisService;
import cn.iocoder.springboot.lab23.springmvc.result.CodeMsg;
import cn.iocoder.springboot.lab23.springmvc.result.Result;
import cn.iocoder.springboot.lab23.springmvc.service.GoodsService;
import cn.iocoder.springboot.lab23.springmvc.service.MiaoshaUserService;
import cn.iocoder.springboot.lab23.springmvc.service.OrderService;
import cn.iocoder.springboot.lab23.springmvc.vo.GoodsVo;
import cn.iocoder.springboot.lab23.springmvc.vo.OrderDetailVo;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	MiaoshaUserService userService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	GoodsService goodsService;
	
    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model,MiaoshaUser user,
    		@RequestParam("orderId") long orderId) {
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	OrderInfo order = orderService.getOrderById(orderId);
    	if(order == null) {
    		return Result.error(CodeMsg.ORDER_NOT_EXIST);
    	}
    	long goodsId = order.getGoodsId();
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	OrderDetailVo vo = new OrderDetailVo();
    	vo.setOrder(order);
    	vo.setGoods(goods);
    	return Result.success(vo);
    }
    
}
