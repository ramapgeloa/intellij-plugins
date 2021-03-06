/*
 * Copyright 2000-2006 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.communicator.idea.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import jetbrains.communicator.commands.ToggleFileAccessCommand;
import jetbrains.communicator.core.Pico;
import org.jetbrains.annotations.NotNull;

/**
 * @author Kir
 */
public class SelectedUserCanReadMyFiles extends ToggleAction {

  public SelectedUserCanReadMyFiles() {
  }

  public boolean isSelected(AnActionEvent anActionEvent) {
    ToggleFileAccessCommand command = getCommand(anActionEvent);
    return command != null && command.isSelected();
  }

  public void setSelected(AnActionEvent anActionEvent, boolean b) {
    getCommand(anActionEvent).execute();
  }

  public void update(@NotNull AnActionEvent e) {
    super.update(e);
    if (e.getProject() == null) {
      e.getPresentation().setEnabled(false);
      return;
    }
    ToggleFileAccessCommand command = getCommand(e);
    boolean enabled = command.isEnabled();
    e.getPresentation().setEnabled(enabled);
    e.getPresentation().setText(command.getText());
  }

  private static ToggleFileAccessCommand getCommand(AnActionEvent e) {
    return Pico.getCommandManager().getCommand(ToggleFileAccessCommand.class, BaseAction.getContainer(e));
  }
}
