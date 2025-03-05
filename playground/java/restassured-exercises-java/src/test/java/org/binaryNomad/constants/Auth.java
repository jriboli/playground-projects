package org.binaryNomad.constants;

import com.binaryNomad.utils.EnvUtils;

public class Auth {
    public static final String CONSUMER_KEY = EnvUtils.getEnv("TWITTER_CONSUMER_KEY");
    public static final String CONSUMER_SECRET = EnvUtils.getEnv("TWITTER_CONSUMER_SECRET");
    public static final String ACCESS_TOKEN = EnvUtils.getEnv("TWITTER_ACCESS_TOKEN");
    public static final String ACCESS_SECRET = EnvUtils.getEnv("TWITTER_ACCESS_TOKEN_SECRET");
}
