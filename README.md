# B2B2C_SHOP
[1.前端界面介绍

**一、前端展示**

1.  **前端界面介绍**

整体设计:主页加载时即从数据库中请求商品数据，渲染于界面上

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/9aa1c5e2e5eef433a72060c721a3cd39.png)

>   5+2商前端执行流程图jpg

>   **图1.1 执行逻辑图**

>   **1.2 主界面展示**

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/3ca2aff0cd0f24f70b6ae7f337f74376.png)

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/0e72ed1f8392bda87046e9420c43343b.png)

>   **图1.2商城主界面**

**2. 系统操作手册**

**2.1登陆**

点击登陆按钮，弹出登陆框，选择身份类型，输入账户密码进行登陆，如果是买家登陆后即可购买商品。是卖家将会跳转到自己的商铺，可对商铺信息进行修改。

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/a75aff253de23b47ce248f0907717d09.png)

>   **图2.1登陆界面图**

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/d5b610ab21ba78c9910240c76b90a3dd.png)

>   **图2.2登陆后用户信息更新图**

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/857750320d789fb07f2394eb45dc215b.png)

>   **图2.3退出后导航栏内容更新**

>   功能执行说明：输入账户密码后请求后台验证，若信息与数据库匹配，则隐藏登陆框，更新导航栏登陆注册按钮区域内容为用户头像、昵称及退出按钮。

**2.2注册**

点击注册按钮弹出注册页面

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/6c0365c19aef84065f5d2a616dd792e7.png)

**图2.4注册弹框**

注册执行流程：用户注册后将信息提交至java后台，后台将新用户数据存储至数据库。

**2.3个人信息修改**

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/29d747eaa2c9dc084cef4eb79b2b1d90.png)

**图2.5个人信息详情展示及修改**

>   功能执行说明：用户更改信息后，将数据组织为json形式,提交给后台，后台判断信息条目是否为空，空则不修改原信息，不空则对数据库进行修改。

**2.4 商品介绍**

用户在首页点击商品图片即可查看对应商品详细信息

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/eaf594a46a9b595aa210dd93aaf3ac4e.png)

**图2.6 商品详细信息页面**

①加入购物车：

>   点击加入购物车按钮即可将该商品加入购物车，等待统一结算

②立即购买：

>   点击立即购买，即可调出付款界面

**2.5 商品修改**

商家登陆后加入自己的店铺可对自己的商品点击进行修改

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/e7dabb16e372e963833e83586469aaa6.png)

**图2.6 商品详细信息修改页面**

①删除商品：

>   点击按钮即可修改

②提交修改：

>   编辑商品信息之后进行修改

**2.6首页商品操作**

①加购物车：

点击商品图片上的购物车图片即可将商品加入购物车

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/5cfb40129456fb1295017e317f56d191.png)

**图2.7加入购物车**

②直接购买：点击购买图标即可弹出付款界面

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/5e5857d02c3a6fddc0a6e92a3da0083d.png)

**图2.8 直接购买**

**2.7购物车**

①加载购物车页面：

主页即商品详情页面有直达购物车的按钮，位于界面右下角，点击即达

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/8800b7cb8ca798cec37978933f0ad60e.png)

**图2.9直达购物车按钮**

②购物车界面展示

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/41b064abef5abc5eb2f98bd6f03c23ea.png)

**图2.10购物车界面**

**2.8购物车商品数量选择**

>   可以更改商品数量同时总价会实时显示在底部

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/35c10383ec601e91e9f96af8ebc58b89.png)

**图2.11商品数量选择**

**2.9收货地址填写、付款方式选择**

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/767154732757187455caed2eac2bd290.png)

**图2.12填写收获地址及付款方式**

**2.10付款**

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/442545d242722db60d9d92f051b00323.png)

**图2.13 付款界面展示**

**2.11搜索商品**

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/d13979c111d00c63ffd956021cfab410.png)

![](https://github.com/keepsmilingm/md-image/blob/master/media-B2b2c/f00e41d62a8847df6169b858fc0e7751.png)

**图2.14搜索功能展示**

