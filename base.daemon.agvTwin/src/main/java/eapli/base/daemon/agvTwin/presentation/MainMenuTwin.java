/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.daemon.agvTwin.presentation;

import eapli.base.Application;
import eapli.base.app.common.console.presentation.authz.UserMenu;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * Data Structure which represents the Administrative Menu, which is thus responsible for presenting the user the
 * available functionalities to him;
 *
 * @author Gonçalo Monteiro
 */
public class MainMenuTwin extends AbstractUI {
    private static final String SEPARATOR_LABEL = "--------------";

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    private Menu mainMenu;
    private MenuRenderer menuRenderer;

    public MainMenuTwin() {
        mainMenu = buildMainMenu();
        menuRenderer = getMenuRenderer(mainMenu);
    }

    @Override
    protected boolean doShow() {
        return menuRenderer.render();
    }

    @Override
    public String headline() {
        return String.format("AGV Digital Twin Client");
    }

    private Menu buildMainMenu() {
        Menu mainMenu = new Menu();

        //mainMenu.addItem(MenuItem.of(1,"Update status",new UpdateStatusAction(agvProxy)::execute));

        if (!Application.settings().isMenuLayoutHorizontal())
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Goodbye"));

        return mainMenu;
    }

    private MenuRenderer getMenuRenderer(Menu mainMenu) {
        if (Application.settings().isMenuLayoutHorizontal())
            return new HorizontalMenuRenderer(mainMenu, MenuItemRenderer.DEFAULT);
        else
            return new VerticalMenuRenderer(mainMenu, MenuItemRenderer.DEFAULT);
    }


}
