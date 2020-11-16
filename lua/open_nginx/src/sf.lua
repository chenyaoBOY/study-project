#!/usr/bin/lua
-- local redis = require "resty.redis"
local cjson = require "cjson.safe"
local redis = require "resty.redis"
local uid='xiaoming'
local orderId=120010
local configKey="order_switch_beta"
local configBkKey="order_switch_bk"
local order_dict=ngx.shared.ngx_shared_order
local dict_timeout=60

-- 自定义配置 不同的方案策略不同的配置
function queryConfig()
    -- 首先从公共缓存中获取 指定key的value
    local resCache=order_dict:get(configKey)
    if(resCache~=nil)then
        ngx.log(ngx.INFO,"query from ngx cache")
        --    return resCache
    end
    -- 连接redis
    local cache = redis.new()
    --local ok, err = cache.connect(cache, '10.103.16.77', '6379')
    local ok, err = cache.connect(cache, '10.102.32.143', '16379')
    cache:set_timeout(6000)
    if not ok then
        ngx.log(ngx.ERR,"failed to connect!,query config from bk cache.",err)
        -- 防止redis 挂掉  如果redis不可用 则使用最近一次的配置
        local resCacheBk=order_dict:get(configBkKey)
        return resCacheBk
    end

    -- 访问授权
    local okA, errA = cache:auth("LfpGAmFJJUxf")
    if not okA then
        ngx.log(ngx.ERR,"failed to auth: ", errA)
        local resCacheBk=order_dict:get(configBkKey)
        return resCacheBk
    end

    local res, err = cache:get(configKey)

    if not res then
        ngx.log(ngx.ERR,"failed to get data!",err)
        return nil
    else
        -- 将redis中的配置 缓存到openresty的共享缓存中
        order_dict:set(configKey,res,dict_timeout)
        order_dict:set(configBkKey,res)
        --    ngx.log(ngx.ERR,"query from redis, resutl=",res)
        local ok, err = cache:close()
        if not ok then
            ngx.log(ngx.ERR,"failed to close!",err)
            return nil
        end

        return res;
    end

end

-- 流量拷贝核心方法
function postCopy()
    local res1, res2, action,array
    action = ngx.var.request_method
    --判断请求类型
    if action == "POST" then
        ngx.req.read_body()
        array = {method = ngx.HTTP_POST, body = ngx.req.get_body_data()}
    else
        array = {method = ngx.HTTP_GET}
    end

    --local cjson= require "cjson.safe"
    --ngx.log(ngx.INFO,"action:"..action)
    --ngx.log(ngx.INFO,"array:"..cjson.encode(array))
    --ngx.log(ngx.INFO,"ngx.var.request_uri:"..ngx.var.request_uri)
    --同时访问两个url，做到一个请求访问两个url
    res1, res2 = ngx.location.capture_multi {
        { "/old" .. ngx.var.request_uri , array},
        { "/new" .. ngx.var.request_uri , array},
    }
    --if(res1==nil)then ngx.log(ngx.INFO,"res1 is null") else ngx.log(ngx.INFO,"res1=",cjson.encode(res1))  end
    -- 判断new的返回值是否为空
    if(res2==nil)then
        ngx.log(ngx.INFO,"res2 is null")
    else
        ngx.log(ngx.INFO,"res2=",cjson.encode(res2))
    end
    --打印new的返回状态
    ngx.log(ngx.ERR,"new order sys response status:"..res2.status)
    -- 设置请求头参数
    if res1.status == ngx.HTTP_OK then
        --ngx.log(ngx.INFO,"res1.header="..cjson.encode(res1.header))
        local header_list = {"Content-Length", "Content-Type", "Content-Encoding", "Accept-Ranges"}
        for _, i in ipairs(header_list) do
            if res1.header[i] then
                ngx.header[i] = res1.header[i]

            end
        end
        ngx.say(res1.body)
        -- ngx.log(ngx.INFO,"res1.body:"..res1.body)
    else
        ngx.status = ngx.HTTP_NOT_FOUND
    end
end
-- 当参数=2时 执行流量拷贝 否则执行new 流量切出
function  orderRedirect(open)

    if(open==2)then
        postCopy()

    else
        --proxy_pass 'http://old_order';
        return ngx.exec("/new"..ngx.var.request_uri)
        --	return ngx.exec("/new")
        --return ngx.redirect("/new",301)
        -- ngx.location.capture("/new")
    end

end

startFlag="0xEE"
endFlag="0xFF"

--解析请求body  由于hessian形式请求，请求体需要经过特殊处理 所以在发送请求时就需要
--在参数中做些处理，然后在此翻译
function  parseBody(bodyStr)
    local dataStart=string.find(bodyStr,startFlag,1)
    local dataEnd=string.find(bodyStr,endFlag,1)
    if(dataStart==nil or dataEnd==nil or dataStart==dataEnd-4)then

        --  ngx.log(ngx.ERR,"cannot find routeInfo from request body")
        return nil
    end
    ngx.log(ngx.INFO,"star:"..dataStart)
    --ngx.log(ngx.INFO,"star:"..dataStart..",end:"..dataEnd)
    local routeStr=string.sub(bodyStr,dataStart+4,dataEnd-1)
    local dataLenStr=string.sub(routeStr,0,4)
    --turn str to number
    local dataLen=dataLenStr+0

    --validate data length
    if(string.len(routeStr)~=dataLen+4)
    then
        ngx.log(ngx.ERR,"route chars less,routeStr:"..routeStr)
        --not need return 有中文内容 字符数算不准
        ---- return nil
    end
    local routeJson =string.sub(routeStr,5,-1)
    --routeJson="{\"username\":\"qunge\",\"orderId\":\"qunge"
    local routeTable=cjson.decode(routeJson)
    return routeTable
end
--执行old
function goToOld()

    return ngx.exec("/old"..ngx.var.request_uri)
end
--执行new
function goToNew()

    return ngx.exec("/new"..ngx.var.request_uri)
end

--
--开始解析
--

local res=queryConfig()
--若配置为空 则执行old
if(res==nil)then
    return goToOld()
end

--将配置转换为json形式 便于取值
local config=cjson.decode(res)
-- 流量切出比例
local rate= config["rate"]
--开关参数
local open= config["open"]
--策略参数
local mode= config["mode"]
--白名单
local whiteList= config["whiteIdList"]
--订单号区间
local orderIdRangeList=config["orderIdRangeList"]
--    local methodWhiteList=config["methodWhiteList"]
--********************validate config start***************************

ngx.log(ngx.ERR,"*********************switch start********************************")
local paramError=0
--首先判断开关参数
if(open==nil) then
    ngx.log(ngx.ERR,"open value cannot be null")
    paramError=1
    -- open val cannot greater than 2 ,now
elseif(open==0 or open >2) then
    ngx.log(ngx.ERR,"order db switch closed!")
    paramError=1
end
--如果open不等于1 2  则执行old 相当于关闭
if(paramError==1)then
    return goToOld()
end
--ngx.log(ngx.INFO,"enter param validate")

--然后判断策略 白名单1 订单范围2 流量百分比3 和全部切出4 四种策略
if(mode==nil or mode ==0)then
    ngx.log(ngx.ERR,"mode value cannot be null mode="..mode)
    paramError=1
elseif(type(mode)=='number' and mode==3) then
    if(rate==nil)then
        ngx.log(ngx.ERR,"rate is null")
        paramError=1
    end
elseif(mode==1) then
    if(whiteList==nil or #whiteList==0) then
        ngx.log(ngx.ERR,"whiteList is null or size is zero")
        paramError=1
    end
elseif(mode==2)then
    if(orderIdRangeList==nil or #orderIdRangeList==0) then
        ngx.log(ngx.ERR,"orderIdRangeList is null or size is zero")
        paramError=1
    end
end
-- mode  val cannot bigger than 4
if(mode>4)then
    paramError=1
end
--若策略方案未设置 则执行old
if(paramError==1)then
    return goToOld()
end
-- rate style location this line, Beause:  if mode=3
-- no need parse  userName and orderId

--流浪拷贝或切出

if(mode==3) then
    local randomNum=math.random(100)
    if(randomNum<=tonumber(rate)) then
        ngx.log(ngx.INFO,"match rate style randomNum:"..randomNum..",rate:"..rate)
        --ngx.say("do order db switch")
        -- 如果open=2 则old不影响执行，new相当于复制指定百分比
        -- 如果open!=2 则old和new加一起是总共的流量，new切除了指定百分比
        orderRedirect(open)
        return
        --return ngx.redirect("http://new.order.sfbest.com:8080/order/test")
    else
        ngx.log(ngx.INFO,"less than rate go to old ")
        return goToOld()
    end
    --end
elseif(mode==4)then
    --     ngx.log(ngx.INFO," all cut ")
    -- all cut to new Order system style
    --open=2 则old不影响执行 new全部拷贝  否则old无流量 全部切出至new
    orderRedirect(open)
    return
    -- return goToNew()
end

--********************validate config end***************************
ngx.req.read_body()
local bodyStr=ngx.req.get_body_data()
if(bodyStr==nil)then
    ngx.log(ngx.ERR," body is nil,invoke goToOld() ")
    return goToOld()
end

--ngx.log(ngx.ERR,"body:"..bodyStr)
local routeInfo=parseBody(bodyStr)
if(routeInfo==nil)then
    ngx.log(ngx.ERR,"parse body error go to old")
    return goToOld()
end
uid=routeInfo["userName"]
orderId=routeInfo["orderId"]
orderSn=routeInfo["orderSn"]
if(orderSn~=nil) then
    --orderId=string.sub(orderSn,3,13)
    -- orderId=orderSn
    orderId=string.sub(orderSn,7,16)
end

if(uid==nil and orderId==nil)then
    ngx.log(ngx.ERR,"userName and orderId  both null cannot route")
    return goToOld()
end
if(orderId~=nil and type(orderId+0)~="number")then

    ngx.log(ngx.ERR," orderId must be number type ,plase check ")
    return goToOld()
end

if(mode==1) then
    local canDo=0;
    for i,v in ipairs(whiteList) do
        if(v==uid)then
            ngx.log(ngx.ERR,"match whiteList rule  name="..v)
            canDo=1
            orderRedirect(open)
            return
            --break
        end
    end
    if(canDo==0) then
        ngx.log(ngx.INFO," cannot match whiteList")
    end

    --end

    --orderId range style
elseif(mode==2) then
    -- ngx.say("enter range style")
    if(orderIdRangeList==nil or #orderIdRangeList==0) then
        ngx.log(ngx.ERR,"orderIdRangeList is null or size is zero")
        return
    end
    local canDo2=0
    for i,v in ipairs(orderIdRangeList) do
        local startVal=v["start"]
        local endVal=v["end"]
        orderId=tonumber(orderId)
        if(orderId>startVal and orderId<endVal) then
            ngx.log(ngx.ERR,"mathch orderId Range,orderId= "..orderId..",start="..startVal..",end="..endVal)
            canDo2=1
            orderRedirect(open)
            return
            --break
        end
    end
    if(canDo2==0) then
        ngx.log(ngx.INFO," cannot match orderIdRangeList")
    end
end

-- invoke original style
ngx.log(ngx.INFO,"enter final  invoke")
return  goToOld()
--return ngx.exec("@order_old")

