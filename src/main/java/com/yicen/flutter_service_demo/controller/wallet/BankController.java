package com.yicen.flutter_service_demo.controller.wallet;

import com.yicen.flutter_service_demo.config.NeedLogin;
import com.yicen.flutter_service_demo.controller.wallet.entity.InsertBankVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletBankVo;
import com.yicen.flutter_service_demo.controller.wallet.service.Impl.WalletBankServiceImpl;
import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("wallet/bank")
public class BankController {

    @Autowired
    private WalletBankServiceImpl walletBankService;

    @PostMapping("add")
    @NeedLogin
    public Result addBank(HttpServletRequest request,@RequestBody InsertBankVo walletBankVo) {
        WalletBankVo queryByCardNumber = walletBankService.queryByCardNumber(walletBankVo.getNumber());
        if (queryByCardNumber != null){
            if (queryByCardNumber.getIsDelete() == true){
                int i = walletBankService.updateDelete(walletBankVo.getNumber());
                return i > 0 ? Result.ok("添加银行卡成功") : Result.error("添加银行卡失败");
            }else {
                return Result.error("添加银行卡失败,此卡已经添加过了");
            }
        }else {
            User user = JwtUtil.getUser(request.getHeader("token"));
            WalletBankVo bankVo = new WalletBankVo();
            BeanUtils.copyProperties(walletBankVo, bankVo);
            bankVo.setUserId(user.getId());
            bankVo.setUserName(user.getUsername());
            int i = walletBankService.insertBank(bankVo);
            return i > 0 ? Result.ok("添加银行卡成功") : Result.error("添加银行卡失败");
        }
    }

    @GetMapping("list")
    @NeedLogin
    public Result queryByUserId(HttpServletRequest request) {
        User user = JwtUtil.getUser(request.getHeader("token"));
        return Result.ok(walletBankService.getBankList(user.getId().toString()));
    }

    @PostMapping("delete")
    @NeedLogin
    public Result deleteBank(HttpServletRequest request, @RequestParam Integer bankId) {
        int i = walletBankService.deleteBank(bankId);
        return i > 0 ? Result.ok("删除银行卡成功") : Result.error("删除银行卡失败");
    }

}
