package com.deeperdesign.teamcity.xcodeplugin.agent;

import com.deeperdesign.teamcity.xcodeplugin.common.PluginConstants;

import jetbrains.buildServer.agent.*;

public class XCodeAgentBuildRunnerInfo implements AgentBuildRunnerInfo
{

    public boolean canRun(BuildAgentConfiguration agentConfiguration) {
        BuildAgentSystemInfo info = agentConfiguration.getSystemInfo();
        if(info.isMac())
        {
            return true;
        }     
        
        return false;
    }

    public String getType() {
        return PluginConstants.RUNNER_TYPE;
    }
}