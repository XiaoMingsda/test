package com.atguigu.pojo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//购物车对象
public class Cart {

    //private Integer totalCount;
    //private Double totalPrice;
    //不要用List，不方便
    //private List<CartItem> items = new ArrayList<CartItem>();
    /**
     * key是商品编号，value是商品信息
     */
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();

    /**
     * 添加商品项
     * @return
     */
    public void addItem(CartItem cartItem) {
        //先查看购物车中是否已经添加过此商品，如果已添加，则数量累加，总金额更新
        //如果没有添加过，直接放到集合中即可
        CartItem item = items.get(cartItem.getId());

        if (item == null) {
            //之前没添加过此商品
            items.put(cartItem.getId(), cartItem);
        } else {
            //已经添加过的情况
            item.setCount( item.getCount() + 1 );//数量 累加
            item.setTotalPrice(item.getTotalPrice() + item.getPrice() );//更新总金额
        }
    }

    /**
     * 删除商品项
     * @return
     */
    public void deleteItem(Integer cartItemId) {
        items.remove(cartItemId);
    }

    /**
     * 清空购物车
     * @return
     */
    public void clear() {
        items.clear();
    }

    /**
     * 修改商品数量
     * @return
     */
    public void updateCount(Integer id, Integer count) {
        //先查看购物车中是否有此商品。如果有，修改商品数量，更新总金额
        CartItem item = items.get(id);

        if (item != null) {
            item.setCount( count );
            item.setTotalPrice( item.getCount() * item.getPrice() );
        }
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public Double getTotalPrice() {
        Double totalPrice = 0.0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice += entry.getValue().getTotalPrice();
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
