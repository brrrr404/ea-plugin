package su.serviceit.ea;

import su.serviceit.ea.component.DialogComponentAddAlias;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AliasAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        DialogComponentAddAlias dialogComponent = new DialogComponentAddAlias(e);
        dialogComponent.show();

    }

    private FileChooserDescriptor showFileDialog(AnActionEvent event) {
        FileChooserDescriptor fileChooserDescriptor = new FileChooserDescriptor(false,
                true,
                false,
                false,
                false,
                false);
        fileChooserDescriptor.setTitle("PICK");
        fileChooserDescriptor.setDescription("BIMP");

        FileChooser.chooseFile(fileChooserDescriptor, event.getProject(), event.getData(PlatformDataKeys.VIRTUAL_FILE));

        return fileChooserDescriptor;
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
