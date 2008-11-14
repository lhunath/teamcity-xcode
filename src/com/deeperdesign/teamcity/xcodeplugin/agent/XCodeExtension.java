/*
 * Copyright (c) 2008 Oliver Jones.  All rights reserved.
 */

package com.deeperdesign.teamcity.xcodeplugin.agent;

import jetbrains.buildServer.agent.*;
import jetbrains.buildServer.util.EventDispatcher;

/**
 *
 * @author oliver
 */
public class XCodeExtension extends AgentLifeCycleAdapter {
 
    public XCodeExtension(final EventDispatcher<AgentLifeCycleListener> events) {
        events.addListener(this);
    }
    
    
    
}