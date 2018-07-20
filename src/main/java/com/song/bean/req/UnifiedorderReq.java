package com.song.bean.req;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Song on 2018/06/14.
 */
@XmlRootElement(name = "xml")
@Data
public class UnifiedorderReq {

    @ApiModelProperty(value = "小程序ID", required = true)
    private String appid;


    @ApiModelProperty(value = "商户号")
    private String mch_id;


    @ApiModelProperty(value = "设备号")
    private String device_info;

    /**
     * 随机字符串，长度要求在32位以内
     */
    @ApiModelProperty(value = "随机字符串")
    private String nonce_str;

    /**
     * 通过签名算法计算得出的签名值
     */
    @ApiModelProperty(value = "签名")
    private String sign;

    /**
     * 签名类型，默认为MD5，支持HMAC-SHA256和MD5
     */
    @ApiModelProperty(value = "签名类型")
    private String sign_type;


    @ApiModelProperty(value = "商品描述")
    private String body;


    @ApiModelProperty(value = "商品详情")
    private String detail;


    /**
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     */
    @ApiModelProperty(value = "附加数据")
    private String attach;


    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
     */
    @ApiModelProperty(value = "商户订单号")
    private String out_trade_no;


    /**
     * 符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    @ApiModelProperty(value = "标价币种")
    private String fee_type;


    @ApiModelProperty(value = "标价金额(单位:分)")
    private Integer total_fee;


    /**
     * APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
     */
    @ApiModelProperty(value = "终端IP")
    private String spbill_create_ip;

    /**
     * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    @ApiModelProperty(value = "交易起始时间")
    private String time_start;

    /**
     * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。
     * 订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，
     * 所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id
     */
    @ApiModelProperty(value = "交易结束时间")
    private String time_expire;


    @ApiModelProperty(value = "订单优惠标记")
    private String goods_tag;

    /**
     * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
     */
    @ApiModelProperty(value = "通知地址")
    private String notify_url;

    /**
     * 小程序取值如下：JSAPI
     */
    @ApiModelProperty(value = "交易类型")
    private String trade_type;

    /**
     * trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义
     */
    @ApiModelProperty(value = "商品ID")
    private String product_id;

    @ApiModelProperty(value = "指定支付方式")
    private String limit_pay;

    @ApiModelProperty(value = "用户标识")
    private String openid;
}
