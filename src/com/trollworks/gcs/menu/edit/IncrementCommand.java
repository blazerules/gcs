/*
 * Copyright (c) 1998-2014 by Richard A. Wilkes. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * version 2.0. If a copy of the MPL was not distributed with this file, You
 * can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * This Source Code Form is "Incompatible With Secondary Licenses", as defined
 * by the Mozilla Public License, version 2.0.
 */

package com.trollworks.gcs.menu.edit;

import com.trollworks.toolkit.annotation.Localize;
import com.trollworks.toolkit.utility.Localization;


import com.trollworks.toolkit.ui.menu.Command;
import com.trollworks.toolkit.ui.widget.outline.OutlineProxy;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;

/** Provides the "Increment" command. */
public class IncrementCommand extends Command {
	@Localize("Increment")
	private static String INCREMENT;

	static {
		Localization.initialize();
	}

	/** The action command this command will issue. */
	public static final String				CMD_INCREMENT	= "Increment";				//$NON-NLS-1$

	/** The singleton {@link IncrementCommand}. */
	public static final IncrementCommand	INSTANCE		= new IncrementCommand();

	private IncrementCommand() {
		super(INCREMENT, CMD_INCREMENT, KeyEvent.VK_EQUALS);
	}

	@Override
	public void adjustForMenu(JMenuItem item) {
		Component focus = getFocusOwner();
		if (focus instanceof OutlineProxy) {
			focus = ((OutlineProxy) focus).getRealOutline();
		}
		if (focus instanceof Incrementable) {
			Incrementable inc = (Incrementable) focus;
			setTitle(inc.getIncrementTitle());
			setEnabled(inc.canIncrement());
		} else {
			setTitle(INCREMENT);
			setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Component focus = getFocusOwner();
		if (focus instanceof OutlineProxy) {
			focus = ((OutlineProxy) focus).getRealOutline();
		}
		((Incrementable) focus).increment();
	}
}