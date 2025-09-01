local completeKey = KEYS[1];

local bitmapKey = KEYS[2];

local value = ARGV[1];

local loginCount = tonumber(ARGV[2]); --获取限制登录人数

local expireTime = redis.call('ttl', KEYS[1]);

if
expireTime == -2 --如果key不存在
then
    local bitcount = redis.call("bitcount", KEYS[2]);--获取指定机构、产品下的登陆人数
    if
    bitcount >= loginCount --如果登陆人数已经满限制人数
    then
        return "0"--根据redisTemplate的泛型决定返回的数据类型，可以返回字符、数字
    else --不满足人数
        redis.call("set", KEYS[1], value)
        redis.call("expire", KEYS[1], 1800)
        redis.call("setbit", KEYS[2], value, 1)
        return "1"
    end
elseif
expireTime > 0 -- and expireTime < 600 --过期时间小于10分钟，更新过期时间
then
    redis.call("expire", KEYS[1], 1800)
    return "1"
--elseif --过期时间大于10分钟 不更新过期时间
--expireTime >= 60
--then
--    return "1"
end