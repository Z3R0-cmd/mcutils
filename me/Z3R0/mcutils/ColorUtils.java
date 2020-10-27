package me.Z3R0.mcutils;

import java.util.ArrayList;

public class ColorUtils {
	
	private ArrayList<Integer> CurrentRainbowIndexes = new ArrayList<Integer>();
    private ArrayList<Integer> RainbowArrayList = new ArrayList<Integer>();
    public int GetRainbowColorAt(int index)
    {
        if (index > CurrentRainbowIndexes.size() - 1)
            index = CurrentRainbowIndexes.size() - 1;

        return RainbowArrayList.get(CurrentRainbowIndexes.get(index));
    }
}
