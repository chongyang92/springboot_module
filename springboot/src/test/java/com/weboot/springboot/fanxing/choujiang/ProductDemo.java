package com.weboot.springboot.fanxing.choujiang;

public class ProductDemo {
    public static void main(String[] args) {
        ProductGetter<String> productGetter = new ProductGetter<>();
        String[] strings = {"苹果手机","华为手机","扫地机器人","咖啡机"};
        for (int i = 0; i < strings.length; i++) {
            productGetter.addProduct(strings[i]);
        }

        String product1 = productGetter.getProduct();
        System.out.println("恭喜，抽中了"+product1);

        System.out.println("--------------换一个-------------");
        ProductGetter<Integer> integerProductGetter = new ProductGetter<>();
        int[] intProduct = {10000,5000,300,30000};
        for (int i = 0; i < strings.length; i++) {
            integerProductGetter.addProduct(intProduct[i]);
        }
        Integer produce2 = integerProductGetter.getProduct();
        System.out.println("恭喜，抽中了"+produce2);
    }
}
