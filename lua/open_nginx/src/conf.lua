--
-- Created by IntelliJ IDEA.
-- User: 89468
-- Date: 2019/2/13
-- Time: 15:54
-- To change this template use File | Settings | File Templates.
--
local cjson = require ('cjson')
local method = ngx.req.get_method()
--ngx.log(ngx.INFO,'method = '..method)

if method == 'POST' then
    ngx.req.read_body()
    local postData = ngx.req.get_post_args()
--    ngx.log(ngx.INFO,'postData = '..postData)
--    return ngx.exec('/road1')
elseif method == 'GET' then
    local requsetData = ngx.req.get_uri_args()
--    ngx.log(ngx.INFO,cjson.encode(requsetData))
--    return ngx.exec('/road2'..ngx.var.request_uri)
end


