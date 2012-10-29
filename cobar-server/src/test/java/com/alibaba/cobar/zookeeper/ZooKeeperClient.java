/*
 * Copyright 1999-2012 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.cobar.zookeeper;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import org.apache.log4j.Logger;

import com.alibaba.cobar.net.BackendConnection;
import com.alibaba.cobar.zookeeper.config.ZooKeeperConfig;
import com.alibaba.cobar.zookeeper.handler.ZookeeperHandler;

/**
 * ZooKeeper Client
 * 
 * @author xianmao.hexm
 */
public final class ZooKeeperClient extends BackendConnection {
    private static final Logger LOGGER = Logger.getLogger(ZooKeeperClient.class);

    protected ZooKeeperConfig config;
    protected ZooKeeperStatus status;

    public ZooKeeperClient(SocketChannel channel) throws IOException {
        super(channel);
        this.handler = new ZookeeperHandler(this);
    }

    public ZooKeeperConfig getConfig() {
        return config;
    }

    public void setConfig(ZooKeeperConfig config) {
        this.config = config;
    }

    public ZooKeeperStatus getStatus() {
        return status;
    }

    public void setStatus(ZooKeeperStatus status) {
        this.status = status;
    }

    @Override
    public void handleError(int errCode, Throwable t) {
        LOGGER.warn(toString(), t);
        close();
    }

}
