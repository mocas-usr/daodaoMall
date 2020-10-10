package portal.controller;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/10/8 下午4:21
 * @email: wangyuhang_mocas@163.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import portal.pojo.CartItem;
import portal.service.CartService;
import portal.utils.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *@program: daodao
 *@description:购物车
 *@author: mocas_wang
 *@create: 2020-10-08 16:21
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * @Description:购物车增加商品
     * @Author: mocas_wang
     * @Date: 下午3:50 2020/10/10
     * @Param: [itemId, num, request, response]
     * @return: java.lang.String
    **/
    @RequestMapping("/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId,
                              @RequestParam(defaultValue="1")Integer num,
                              HttpServletRequest request, HttpServletResponse response) {
        TaotaoResult result = cartService.addCartItem(itemId, num, request, response);
        return "cartSuccess";
    }

    /**
     * @Description:成功页面
     * @Author: mocas_wang
     * @Date: 下午3:51 2020/10/10
     * @Param: []
     * @return: java.lang.String
    **/

    @RequestMapping("/success")
    public String showSuccess() {
        return "cartSuccess";
    }

    /**
     * @Description:显示购物车
     * @Author: mocas_wang
     * @Date: 下午3:51 2020/10/10
     * @Param: [request, response, model]
     * @return: java.lang.String
    **/

    @RequestMapping("/cart")
    public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<CartItem> list = cartService.getCartItemList(request, response);
        model.addAttribute("cartList", list);
        return "cart";
    }

    /**
     * @Description:删除购物车
     * @Author: mocas_wang
     * @Date: 下午3:51 2020/10/10
     * @Param: [itemId, request, response]
     * @return: java.lang.String
    **/

    @RequestMapping("/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
        cartService.deleteCartItem(itemId, request, response);
        return "redirect:/cart/cart.html";
    }

}