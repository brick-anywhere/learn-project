local bitmapKey = KEYS[1];

local value = ARGV[1];

redis.call("setbit", KEYS[1], value, 0)