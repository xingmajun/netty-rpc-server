package com.netty.test.config;

import com.netty.test.server.NettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2016/11/3.
 */
@Component
public class NetttyAutoStartConfig implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        new NettyServer().run();
    }
}
