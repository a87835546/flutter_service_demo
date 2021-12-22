package com.yicen.flutter_service_demo.controller.wallet;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletTransactionVo;
import com.yicen.flutter_service_demo.controller.wallet.service.Impl.TransactionServiceImpl;
import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.utils.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("wallet/transaction/")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl service;
    @GetMapping("deposit")
    public Result getDeposit(HttpServletRequest request,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNum){
        User user = JwtUtil.getUser(request.getHeader("token"));
        List<WalletTransactionVo> walletTransactionVos = service.queryListByUserName(user.getUsername());
        IPage<WalletTransactionVo> page = service.selectTransactionByUserId(user.getUsername(), pageSize, pageNum);
        return Result.ok(page);
    }

    @ApiOperation("插入存款数据")
    @PostMapping("addDeposit")
    public Result addDeposit(@RequestBody WalletTransactionVo vo){
        boolean b = service.insertRecord(vo);
        return b?Result.ok("插入数据成功"):Result.error("插入数据失败");
    }

    @GetMapping("getWithdraw")
    public Result getWithdraw(){
        return Result.ok();
    }
    @GetMapping("getCoupon")
    public Result getCoupon(){
        return Result.ok();
    }
    @GetMapping("getPrize")
    public Result getPrize(){
        return Result.ok();
    }
    @GetMapping("getAmount")
    public Result getAmount(){
        return Result.ok();
    }
}
