package com.yicen.flutter_service_demo.controller.wallet;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yicen.flutter_service_demo.config.NeedLogin;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletTransactionVo;
import com.yicen.flutter_service_demo.controller.wallet.service.Impl.TransactionServiceImpl;
import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.utils.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("wallet/transaction/")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionServiceImpl service;

    @GetMapping("deposit")
    @NeedLogin
    public Result getDeposit(HttpServletRequest request, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNum) {
        User user = JwtUtil.getUser(request.getHeader("token"));
        IPage<WalletTransactionVo> page = service.selectTransactionByUserId(0, user.getUsername(), pageSize, pageNum);
        return Result.ok(page);
    }

    @ApiOperation("插入存款数据")
    @PostMapping("addDeposit")
    @NeedLogin
    public Result addDeposit(@RequestBody WalletTransactionVo vo) {
        log.info("插入数据的参数 ---->> {}",vo);
        boolean b = service.insertRecord(vo);
        return b ? Result.ok("插入数据成功") : Result.error("插入数据失败");
    }

    @GetMapping("getWithdraw")
    public Result getWithdraw(HttpServletRequest request, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNum) {
        User user = JwtUtil.getUser(request.getHeader("token"));
        IPage<WalletTransactionVo> page = service.selectTransactionByUserId(1, user.getUsername(), pageSize, pageNum);
        return Result.ok(page);
    }

    @GetMapping("getCoupon")
    public Result getCoupon(HttpServletRequest request, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNum) {
        User user = JwtUtil.getUser(request.getHeader("token"));
        IPage<WalletTransactionVo> page = service.selectTransactionByUserId(2, user.getUsername(), pageSize, pageNum);
        return Result.ok(page);
    }


    @PostMapping("insertCoupon")
    public Result insertCoupon(HttpServletRequest request, @RequestBody WalletTransactionVo vo) {
        boolean b = service.insertRecord(vo);
        return b ? Result.ok("插入数据成功") : Result.error("插入数据失败");
    }

    @GetMapping("getPrize")
    public Result getPrize(HttpServletRequest request,@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNum) {
        User user = JwtUtil.getUser(request.getHeader("token"));
        IPage<WalletTransactionVo> page = service.selectTransactionByUserId(3, user.getUsername(), pageSize, pageNum);
        return Result.ok(page);
    }

    @GetMapping("getAmount")
    public Result getAmount(HttpServletRequest request,@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNum) {
        User user = JwtUtil.getUser(request.getHeader("token"));
        IPage<WalletTransactionVo> page = service.selectTransactionByUserId(4, user.getUsername(), pageSize, pageNum);
        return Result.ok(page);
    }
}
