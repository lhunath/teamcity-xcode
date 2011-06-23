package com.deeperdesign.teamcity.xcodeplugin.agent;

import com.deeperdesign.teamcity.xcodeplugin.common.PluginConstants;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jetbrains.buildServer.agent.runner.*;

/**
 *
 * @author oliver
 */
public class XCodeCommandLineBuildService extends BuildServiceAdapter {


    public ProgramCommandLine makeProgramCommandLine() {
        return createProgramCommandline(getExecutablePath(), getArguments());
    }

    protected List<String> getArguments() {
        List<String> arguments = new Vector<String>();
        final Map<String, String> params = getBuild().getRunnerParameters();

        if(parameterExists(params, PluginConstants.SETTINGS_XCODE_PROJECT)) {
            arguments.add("-project");
            arguments.add(params.get(PluginConstants.SETTINGS_XCODE_PROJECT));
        }

        if(parameterExists(params, PluginConstants.SETTINGS_XCODE_TARGET)) {
            String target = params.get(PluginConstants.SETTINGS_XCODE_TARGET);

            if(target.equals(PluginConstants.SETTINGS_XCODE_TARGET_ACTIVETARGET)) {
                arguments.add("-activetarget");
            }
            else if(target.equals(PluginConstants.SETTINGS_XCODE_TARGET_ALLTARGETS)) {
                arguments.add("-alltargets");
            }
            else if(target.equals(PluginConstants.SETTINGS_XCODE_TARGET_SINGLETARGET)) {
                arguments.add("-target");
                arguments.add(params.get(PluginConstants.SETTINGS_XCODE_TARGETNAME));
            }
        }

        if(isParameterEnabled(params, PluginConstants.SETTINGS_XCODE_PARALLELIZETARGETS)) {
            arguments.add("-parallelizeTargets");
        }

        if(parameterExists(params, PluginConstants.SETTINGS_XCODE_CONFIGURATION)) {
            arguments.add("-configuration");
            arguments.add(params.get(PluginConstants.SETTINGS_XCODE_CONFIGURATION));
        }
        // TODO: support -activeconfiguration ?

        if(parameterExists(params, PluginConstants.SETTINGS_XCODE_SDK)) {
            arguments.add("-sdk");
            arguments.add(params.get(PluginConstants.SETTINGS_XCODE_SDK));
        }

        if(parameterExists(params, PluginConstants.SETTINGS_XCODE_BUILD_ACTION)) {
            String action = params.get(PluginConstants.SETTINGS_XCODE_BUILD_ACTION).trim();
            String[] actions = action.split(" ");
            for(int i = 0; i < actions.length; i++) {
                arguments.add(actions[i]);
            }
        }

        if(parameterExists(params, PluginConstants.SETTINGS_XCODE_BUILD_SETTINGS)) {
            for(String setting : splitSettings(params.get(PluginConstants.SETTINGS_XCODE_BUILD_SETTINGS))) {
                if(setting != null && setting.trim().equals("") == false) {
                    arguments.add(setting);
                }
            }
        }

        if(parameterExists(params, PluginConstants.SETTINGS_XCODE_USER_DEFAULTS)) {
            for(String setting : splitSettings(params.get(PluginConstants.SETTINGS_XCODE_USER_DEFAULTS))) {
                if(setting != null && setting.trim().equals("") == false) {
                    arguments.add(setting);
                }
            }
        }
        return arguments;
    }

    protected String getExecutablePath() {
        return "xcodebuild";
    }

    private static boolean parameterExists(final Map<String, String> params, final String key) {
        return params.containsKey(key) && (params.get(key).trim().equals("") == false);
    }

    private static boolean isParameterEnabled(final Map<String, String> params, final String key) {
        return params.containsKey(key) && params.get(key).equalsIgnoreCase(Boolean.TRUE.toString());
    }

    private static List<String> splitSettings(String settings) {
        List<String> list = new Vector<String>();

        String[] setting = settings.split("\n");
            for(int i = 0; i < setting.length; i++) {
                list.add(setting[i]);
            }

        return list;
    }
}
