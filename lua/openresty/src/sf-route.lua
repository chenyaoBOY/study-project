#!  /usr/bin/lua
-- local redis = require "resty.redis"
local cjson = require "cjson.safe"
local redis = require "resty.redis"
local uid = 'xiaoming'
local orderId = 120010
local configKey = "order_switch_beta"
local configBkKey = "order_switch_bk"
local order_dict = ngx.shared.ngx_shared_order
local dict_timeout = 60
function queryConfig()
    local resCache = order_dict:get(configKey)
    if (resCache ~= nil) then
        ngx.log(ngx.INFO, "query from ngx cache")
        --    return resCache
    end
    local cache = redis.new()
    --local ok, err = cache.connect(cache, '10.103.16.77', '6379')
    local ok, err = cache.connect(cache, '10.102.32.143', '16379')
    cache:set_timeout(6000)
    if not ok then
        ngx.log(ngx.ERR, "failed to connect!,query config from bk cache.", err)
        -- 防止redis 挂掉
        local resCacheBk = order_dict:get(configBkKey)
        return resCacheBk
    end

    -- 访问授权
    local okA, errA = cache:auth("LfpGAmFJJUxf")
    if not okA then
        ngx.log(ngx.ERR, "failed to auth: ", errA)
        local resCacheBk = order_dict:get(configBkKey)
        return resCacheBk
    end

    local res, err = cache:get(configKey)

    if not res then
        ngx.log(ngx.ERR, "failed to get data!", err)
        return nil
    else
        order_dict:set(configKey, res, dict_timeout)
        order_dict:set(configBkKey, res)
        --    ngx.log(ngx.ERR,"query from redis, resutl=",res)
        local ok, err = cache:close()
        if not ok then
            ngx.log(ngx.ERR, "failed to close!", err)
            return nil
        end

        return res;
    end
end

function postCopy()
    local res1, res2, action, array
    action = ngx.var.request_method
    if action == "POST" then
        ngx.req.read_body()
        array = { method = ngx.HTTP_POST, body = ngx.req.get_body_data() }
    else
        array = { method = ngx.HTTP_GET }
    end

    --local cjson= require "cjson.safe"
    --ngx.log(ngx.INFO,"action:"..action)
    --ngx.log(ngx.INFO,"array:"..cjson.encode(array))
    --ngx.log(ngx.INFO,"ngx.var.request_uri:"..ngx.var.request_uri)
    res1, res2 = ngx.location.capture_multi {
        { "/old" .. ngx.var.request_uri, array },
        { "/new" .. ngx.var.request_uri, array },
    }
    --if(res1==nil)then ngx.log(ngx.INFO,"res1 is null") else ngx.log(ngx.INFO,"res1=",cjson.encode(res1))  end
    if (res2 == nil) then ngx.log(ngx.INFO, "res2 is null") else ngx.log(ngx.INFO, "res2=", cjson.encode(res2)) end

    ngx.log(ngx.ERR, "new order sys response status:" .. res2.status)
    if res1.status == ngx.HTTP_OK then
        --ngx.log(ngx.INFO,"res1.header="..cjson.encode(res1.header))
        local header_list = { "Content-Length", "Content-Type", "Content-Encoding", "Accept-Ranges" }
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

function orderRedirect(open)

    if (open == 2) then
        postCopy()

    else
        --proxy_pass 'http://old_order';
        return ngx.exec("/new" .. ngx.var.request_uri)
        --	return ngx.exec("/new")
        --return ngx.redirect("/new",301)
        -- ngx.location.capture("/new")
    end
end

startFlag = "0xEE"
endFlag = "0xFF"
function parseBody(bodyStr)
    local dataStart = string.find(bodyStr, startFlag, 1)
    local dataEnd = string.find(bodyStr, endFlag, 1)
    if (dataStart == nil or dataEnd == nil or dataStart == dataEnd - 4) then

        --  ngx.log(ngx.ERR,"cannot find routeInfo from request body")
        return nil
    end
    ngx.log(ngx.INFO, "star:" .. dataStart)
    --ngx.log(ngx.INFO,"star:"..dataStart..",end:"..dataEnd)
    local routeStr = string.sub(bodyStr, dataStart + 4, dataEnd - 1)
    local dataLenStr = string.sub(routeStr, 0, 4)
    --turn str to number
    local dataLen = dataLenStr + 0

    --validate data length
    if (string.len(routeStr) ~= dataLen + 4) then
        ngx.log(ngx.ERR, "route chars less,routeStr:" .. routeStr)
        --not need return 有中文内容 字符数算不准
        --- - return nil
    end
    local routeJson = string.sub(routeStr, 5, -1)
    --routeJson="{\"username\":\"qunge\",\"orderId\":\"qunge"
    local routeTable = cjson.decode(routeJson)
    return routeTable
end

function goToOld()

    return ngx.exec("/old" .. ngx.var.request_uri)
end

function goToNew()

    return ngx.exec("/new" .. ngx.var.request_uri)
end

local res = queryConfig()
if (res == nil) then
    return goToOld()
end
local config = cjson.decode(res)
local rate = config["rate"]
local open = config["open"]
local mode = config["mode"]
local whiteList = config["whiteIdList"]
local orderIdRangeList = config["orderIdRangeList"]
--    local methodWhiteList=config["methodWhiteList"]
--********************validate config start***************************

ngx.log(ngx.ERR, "*********************switch start********************************")
local paramError = 0
if (open == nil) then
    ngx.log(ngx.ERR, "open value cannot be null")
    paramError = 1
    -- open val cannot greater than 2 ,now
elseif (open == 0 or open > 2) then
    ngx.log(ngx.ERR, "order db switch closed!")
    paramError = 1
end
if (paramError == 1) then
    return goToOld()
end
--ngx.log(ngx.INFO,"enter param validate")
if (mode == nil or mode == 0) then
    ngx.log(ngx.ERR, "mode value cannot be null mode=" .. mode)
    paramError = 1
elseif (type(mode) == 'number' and mode == 3) then
    if (rate == nil) then
        ngx.log(ngx.ERR, "rate is null")
        paramError = 1
    end
elseif (mode == 1) then
    if (whiteList == nil or #whiteList == 0) then
        ngx.log(ngx.ERR, "whiteList is null or size is zero")
        paramError = 1
    end
elseif (mode == 2) then
    if (orderIdRangeList == nil or #orderIdRangeList == 0) then
        ngx.log(ngx.ERR, "orderIdRangeList is null or size is zero")
        paramError = 1
    end
end
-- mode  val cannot bigger than 4
if (mode > 4) then
    paramError = 1
end
if (paramError == 1) then
    return goToOld()
end
-- rate style location this line, Beause:  if mode=3
-- no need parse  userName and orderId
if (mode == 3) then
    local randomNum = math.random(100)
    if (randomNum <= tonumber(rate)) then
        ngx.log(ngx.INFO, "match rate style randomNum:" .. randomNum .. ",rate:" .. rate)
        --ngx.say("do order db switch")
        orderRedirect(open)
        return
        --return ngx.redirect("http://new.order.sfbest.com:8080/order/test")
    else
        ngx.log(ngx.INFO, "less than rate go to old ")
        return goToOld()
    end
    --end
elseif (mode == 4) then
    --     ngx.log(ngx.INFO," all cut ")
    -- all cut to new Order system style
    orderRedirect(open)
    return
    -- return goToNew()
end

--********************validate config end***************************
ngx.req.read_body()
local bodyStr = ngx.req.get_body_data()
if (bodyStr == nil) then
    ngx.log(ngx.ERR, " body is nil,invoke goToOld() ")
    return goToOld()
end

--ngx.log(ngx.ERR,"body:"..bodyStr)
local routeInfo = parseBody(bodyStr)
if (routeInfo == nil) then
    ngx.log(ngx.ERR, "parse body error go to old")
    return goToOld()
end
uid = routeInfo["userName"]
orderId = routeInfo["orderId"]
orderSn = routeInfo["orderSn"]
if (orderSn ~= nil) then
    --orderId=string.sub(orderSn,3,13)
    -- orderId=orderSn
    orderId = string.sub(orderSn, 7, 16)
end

if (uid == nil and orderId == nil) then
    ngx.log(ngx.ERR, "userName and orderId  both null cannot route")
    return goToOld()
end
if (orderId ~= nil and type(orderId + 0) ~= "number") then

    ngx.log(ngx.ERR, " orderId must be number type ,plase check ")
    return goToOld()
end

if (mode == 1) then
    local canDo = 0;
    for i, v in ipairs(whiteList) do
        if (v == uid) then
            ngx.log(ngx.ERR, "match whiteList rule  name=" .. v)
            canDo = 1
            orderRedirect(open)
            return
            --break
        end
    end
    if (canDo == 0) then
        ngx.log(ngx.INFO, " cannot match whiteList")
    end

    --end

    --orderId range style
elseif (mode == 2) then
    -- ngx.say("enter range style")
    if (orderIdRangeList == nil or #orderIdRangeList == 0) then
        ngx.log(ngx.ERR, "orderIdRangeList is null or size is zero")
        return
    end
    local canDo2 = 0
    for i, v in ipairs(orderIdRangeList) do
        local startVal = v["start"]
        local endVal = v["end"]
        orderId = tonumber(orderId)
        if (orderId > startVal and orderId < endVal) then
            ngx.log(ngx.ERR, "mathch orderId Range,orderId= " .. orderId .. ",start=" .. startVal .. ",end=" .. endVal)
            canDo2 = 1
            orderRedirect(open)
            return
            --break
        end
    end
    if (canDo2 == 0) then
        ngx.log(ngx.INFO, " cannot match orderIdRangeList")
    end
end

-- invoke original style
ngx.log(ngx.INFO, "enter final  invoke")
return goToOld()
--return ngx.exec("@order_old")

