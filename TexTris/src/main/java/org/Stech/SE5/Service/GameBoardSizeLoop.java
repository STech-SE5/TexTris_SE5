package org.Stech.SE5.Service;


import org.Stech.SE5.Model.ConfigModel;

import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.List;

public class GameBoardSizeLoop {

    
    ArrayList<ConfigModel.BoardSize> boardSizeList;

    // Constructor for GameBoardSizeLoop
    // Initializes the boardSizeList with the three possible board sizes
    public GameBoardSizeLoop() {
        boardSizeList = new ArrayList<>();

        boardSizeList.add(ConfigModel.BoardSize.SMALL);
        boardSizeList.add(ConfigModel.BoardSize.MEDIUM);
        boardSizeList.add(ConfigModel.BoardSize.LARGE);
    }

    public ConfigModel.BoardSize traverseListOfSize(ConfigModel.BoardSize currentSetting) {

        // Switches the current setting to the next setting in the list
        
        switch (currentSetting) {
            case ConfigModel.BoardSize.SMALL:
                currentSetting = ConfigModel.BoardSize.MEDIUM;
                break;
            case ConfigModel.BoardSize.MEDIUM:
                currentSetting = ConfigModel.BoardSize.LARGE;
                break;
            // If the current setting is the last setting in the list, it will switch to the first setting
            case ConfigModel.BoardSize.LARGE:
                currentSetting = ConfigModel.BoardSize.SMALL;
                break;
        }

        return currentSetting;

    }

}
