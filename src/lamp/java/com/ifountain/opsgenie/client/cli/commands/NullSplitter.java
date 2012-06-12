package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.converters.IParameterSplitter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 4:45 PM
 */
public class NullSplitter implements IParameterSplitter {

    @Override
    public List<String> split(String s) {
        List<String> split = new ArrayList<String>();
        split.add(s);
        return split;
    }
}
