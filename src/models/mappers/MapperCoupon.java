package models.mappers;

import models.Coupon;
import models.CouponSimple;

import java.util.ArrayList;
import java.util.List;

public class MapperCoupon {
    public static List<Coupon> toCoupons(List<CouponSimple> couponSimples){
        List<Coupon> coupons = new ArrayList<>();
        couponSimples.forEach(c -> coupons.add(new Coupon(c)));
        return coupons;
    }

    public static List<CouponSimple> toCouponsSimple(List<Coupon> coupons){
        List<CouponSimple> couponsSimple = new ArrayList<>();
        coupons.forEach(c -> couponsSimple.add(new CouponSimple(c)));
        return couponsSimple;
    }
}
