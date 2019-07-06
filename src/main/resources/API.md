###商品详情
```
http://127.0.0.1:8082/sell/buyer/product/detail?productId=123456
```
参数
```
productId: 123456
```
返回
```json

{
    "code": 200,
    "msg": "success",
    "data": {
        "productId": "123456",
        "categoryId": 3,
        "productName": "泡面",
        "productPrice": 6.5,
        "productStock": 4,
        "productDescription": "这是个好东西",
        "productIcon": "http://127.0.0.1:8082/sell/img/222.jpg",
        "productStatus": 0,
        "createTime": "2019-04-28T01:39:08.190+0000",
        "updateTime": "2019-05-06T06:51:41.123+0000"
    }
}

```

###订单列表

```GET http://127.0.0.1:8081/sell/buyer/order/list?openId=15565408894931775170```

参数

```$xslt
openId: 15565408894931775170
```

返回
```json
{
    "code": 200,
    "msg": "success",
    "data": [
        {
            "orderId": "15565339618181311700",
            "userName": "xc",
            "phone": "1234567890",
            "openId": "15565408894931775170",
            "addressDetail": "杭州电子科技大学",
            "orderAmount": 3.5,
            "orderStatus": 0,
            "payStatus": 0,
            "createTime": "2019-04-29T10:32:41.803+0000",
            "updateTime": "2019-05-05T11:07:29.220+0000",
            "orderDetailVOS": [
                {
                    "productName": "辣条",
                    "productIcon": "http://xxxx.jpg",
                    "productPrice": 3.5,
                    "productQuantity": 1
                }
            ]
        },
        {
            "orderId": "15570546922091158515",
            "userName": "xc",
            "phone": "1234567890",
            "openId": "15565408894931775170",
            "addressDetail": "杭州电子科技大学",
            "orderAmount": 18,
            "orderStatus": 0,
            "payStatus": 0,
            "createTime": "2019-05-05T11:11:32.201+0000",
            "updateTime": "2019-05-05T11:11:32.201+0000",
            "orderDetailVOS": [
                {
                    "productName": "肥仔快乐水",
                    "productIcon": "http://xxxx.jpg",
                    "productPrice": 2.5,
                    "productQuantity": 2
                },
                {
                    "productName": "泡面",
                    "productIcon": "http://xxxx.jpg",
                    "productPrice": 6.5,
                    "productQuantity": 2
                }
            ]
        }
    ]
}
```
###查询订单详情
```
GET /sell/buyer/order/detail
```
参数
```
openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "orderId": "15570546922091158515",
        "userName": "xc",
        "phone": "1234567890",
        "openId": "15565408894931775170",
        "addressDetail": "杭州电子科技大学",
        "orderAmount": 18,
        "orderStatus": 0,
        "payStatus": 0,
        "createTime": "2019-05-05T11:11:32.201+0000",
        "updateTime": "2019-05-05T11:11:32.201+0000",
        "orderDetailVOS": [
            {
                "productName": "肥仔快乐水",
                "productIcon": "http://xxxx.jpg",
                "productPrice": 2.5,
                "productQuantity": 2
            },
            {
                "productName": "泡面",
                "productIcon": "http://xxxx.jpg",
                "productPrice": 6.5,
                "productQuantity": 2
            }
        ]
    }
}
```
###取消订单
```
POST /sell/buyer/order/cancel
```
参数
```
openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```
返回
```json
{
    "code": 200,
    "msg": "success",
    "data": null
}
```

###购物车
```

```