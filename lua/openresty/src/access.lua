ngx.say("access is in")

-- 加载模块
local json = require('cjson')
local db = require('mysql')
local red = require('resty.redis')

local uri = ngx.var.uri  --获取请求URI
ngx.say('</br>  request URI :' .. uri .. ' </br>')
local white_flag=true
--url白名单查询
local white_url = db.query("select url_name from white_url where is_white=1")
if not white_url then
    ngx.say('There is no white_uri in mysql')
    return
end
--判断请求uri是否在白名单内
for index,row in pairs(white_url) do
    for key , value in pairs(row) do
        if value==uri then
            ngx.say('</br>' .. value .. '命中')
            white_flag=false
            break
        end
    end
end
if not white_flag then
    ngx.say('</br>放行')
    return
end

--若不是白名单内的uri需要判断token
-- 获取请求信息
ngx.req.read_body()
local bodys = ngx.req.read_body()
if not bodys then
    ngx.say('</br> 不放行')
--    ngx.redirect('/login')
    --local re = ngx.location.capture('/login')
    --ngx.say(re.body)
    return
end

for k,v in pairs(headers) do
    ngx.say('</br> k:',k,' -- v:',v)
end




