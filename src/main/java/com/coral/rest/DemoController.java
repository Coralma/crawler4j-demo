package com.coral.rest;

import com.coral.service.AccountCommand;
import com.coral.service.CreditCardCommand;
import com.coral.service.CustomerCommand;
import com.coral.service.EstimatingCommand;
import com.coral.service.ItemPriceCommand;
import com.coral.service.OperatorCommand;
import com.coral.service.OrderCommand;
import com.coral.service.PaymentCommand;
import com.coral.service.TotalPriceCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CCC on 2015/10/27.
 */
@Controller
@RequestMapping("demo")
public class DemoController {

    @RequestMapping(value = "getEstimating")
    public String getEstimating() {
        EstimatingCommand command = new EstimatingCommand("EstimatingCommand");
        return command.execute();
    }

    @RequestMapping(value = "getCustomer")
    public String getCustomer() {
        CustomerCommand command = new CustomerCommand("CustomerCommand");
        return command.execute();
    }

    @RequestMapping(value = "getAccount")
    public String getAccount() {
        AccountCommand command = new AccountCommand("AccountCommand");
        return command.execute();
    }

    @RequestMapping(value = "getPayment")
    public String getPayment() {
        PaymentCommand command = new PaymentCommand("PaymentCommand");
        return command.execute();
    }

    @RequestMapping(value = "getOrder")
    public String getOrder() {
        OrderCommand command = new OrderCommand("OrderCommand");
        return command.execute();
    }

    @RequestMapping(value = "getCreditCard")
    public String getCreditCard() {
        CreditCardCommand command = new CreditCardCommand("CreditCardCommand");
        return command.execute();
    }

    @RequestMapping(value = "getOperator")
    public String getOperator() {
        OperatorCommand command = new OperatorCommand("OperatorCommand");
        return command.execute();
    }

    @RequestMapping(value = "getItemPrice")
    public String getItemPrice() {
        ItemPriceCommand command = new ItemPriceCommand("ItemPriceCommand");
        return command.execute();
    }

    @RequestMapping(value = "getTotalPrice")
    public String getTotalPrice() {
        TotalPriceCommand command = new TotalPriceCommand("TotalPriceCommand");
        return command.execute();
    }

}
