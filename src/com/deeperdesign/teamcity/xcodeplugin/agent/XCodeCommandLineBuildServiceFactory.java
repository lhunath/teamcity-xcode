package com.deeperdesign.teamcity.xcodeplugin.agent;

import jetbrains.buildServer.agent.*;
import jetbrains.buildServer.agent.runner.*;

public class XCodeCommandLineBuildServiceFactory implements CommandLineBuildServiceFactory
{
    private AgentBuildRunnerInfo xcodeAgentBuildRunnerInfo;
    
    public XCodeCommandLineBuildServiceFactory() {
        xcodeAgentBuildRunnerInfo = new XCodeAgentBuildRunnerInfo();
    }
    
    public CommandLineBuildService createService() {
        return new XCodeCommandLineBuildService();
    }
    
    public AgentBuildRunnerInfo getBuildRunnerInfo() {
        return xcodeAgentBuildRunnerInfo;
    }
}