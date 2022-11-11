package eapli.base.common.util;

import java.awt.*;

public class SelectFileAction {
    public static String selectTextFile() {
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        StringBuilder stringBuilder = new StringBuilder();

        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        stringBuilder.append(dialog.getDirectory()).append(dialog.getFile());

        return stringBuilder.toString();
    }
}
