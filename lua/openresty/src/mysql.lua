local lua_mysql = require('resty.mysql')

--打开数据库连接
local function connect()

    local mysql,err = lua_mysql:new()
    if not mysql then
        ngx.say('</br>')
        ngx.say('mysql init error:',err)
        return
    end
    mysql:set_timeout(1000)  --数据库连接超时时间

    --数据库基本配置
    local config = {
        host = '192.168.1.132',
        port= 3306,
        database = 'open_resty',
        user = 'root',
        password = 'mysql'
    }

    --开始连接
    local res,err,errno,sqlState = mysql:connect(config)
    if not res then
        ngx.say("connect to mysql error : ", err, " , errno : ", errno, " , sqlstate : ", sqlstate)
    end


    ngx.log(ngx.INFO,'mysql init success')

    return mysql
end

local function query(sql)
    local jdbc = connect()
    local result = jdbc:query(sql)
--    jdbc:close()
    return result
end

local _mysql = {
    query  = query
}

return _mysql;